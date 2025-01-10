package com.jingdianjichi.practice.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.jingdianjichi.practice.server.common.context.UserContextHolder;
import com.jingdianjichi.practice.server.common.exception.BusinessErrorEnum;
import com.jingdianjichi.practice.server.common.exception.BusinessException;
import com.jingdianjichi.practice.server.dao.PracticeDetailDao;
import com.jingdianjichi.practice.server.dao.PracticeInfoDao;
import com.jingdianjichi.practice.server.dao.PracticeSetDao;
import com.jingdianjichi.practice.server.dao.PracticeSetDetailDao;
import com.jingdianjichi.practice.server.entity.PracticeDetail;
import com.jingdianjichi.practice.server.entity.PracticeInfo;
import com.jingdianjichi.practice.server.entity.PracticeSetDetail;
import com.jingdianjichi.practice.server.req.SubmitPracticeDetailReq;
import com.jingdianjichi.practice.server.service.PracticeDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 练习详情表(PracticeDetail)表服务实现类
 *
 * @author jay
 * @since 2025-01-08 18:21:19
 */
@Service
public class PracticeDetailServiceImpl implements PracticeDetailService {
    @Resource
    private PracticeDetailDao practiceDetailDao;
    @Resource
    private PracticeSetDao practiceSetDao;
    @Resource
    private PracticeSetDetailDao practiceSetDetailDao;
    @Resource
    private PlatformTransactionManager platformTransactionManager;
    @Resource
    private PracticeInfoDao practiceInfoDao;

    /**
     * 提交
     *
     * @param req
     * @return
     */
    @Override
    public Boolean submit(SubmitPracticeDetailReq req) {
        // 准备提交数据
        PracticeInfo info = initPracticeInfo(req);

        // 查询所有题目
        PracticeSetDetail practiceSetDetailQuery = new PracticeSetDetail();
        practiceSetDetailQuery.setSetId(req.getSetId());
        List<PracticeSetDetail> allSubjectList = practiceSetDetailDao.queryByPage(practiceSetDetailQuery, 0, 100);

        // 查询已作答的题目
        PracticeDetail practiceDetailQuery = new PracticeDetail();
        practiceDetailQuery.setPracticeId(req.getPracticeId());
        List<PracticeDetail> answeredSubjectList = practiceDetailDao.queryByPage(practiceDetailQuery, 0, 100);

        // 计算正确率
        info.setCorrectRate(calculateCorrectRate(allSubjectList, answeredSubjectList));

        // 使用事务
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            // 更新practiceInfo表
            updateOrInsertPracticeInfo(req, info);

            // 增加套题热度
            practiceSetDao.upHeat(req.getSetId());

            // 将未作答的题目添加至数据库(只有选择了答案的题目才会被存储到数据库)
            storeNotAnsweredSubject(allSubjectList, answeredSubjectList, info.getId());
            platformTransactionManager.commit(status);
        } catch (Exception e) {
            platformTransactionManager.rollback(status);
            throw new BusinessException(BusinessErrorEnum.FAIL, "提交失败！");
        }

        return Boolean.TRUE;
    }

    private void updateOrInsertPracticeInfo(SubmitPracticeDetailReq req, PracticeInfo info) {
        // 检查该practice是否存在
        if (ObjectUtil.isNull(practiceInfoDao.queryById(req.getPracticeId()))) {
            practiceInfoDao.insert(info);
        } else {
            info.setId(req.getPracticeId());
            practiceInfoDao.update(info);
        }
    }

    private static PracticeInfo initPracticeInfo(SubmitPracticeDetailReq req) {
        PracticeInfo info = new PracticeInfo();
        info.setCompleteStatus(1);
        String timeUse = req.getTimeUse().substring(0, 2) + ":" + req.getTimeUse().substring(2, 4) + ":" + req.getTimeUse().substring(4, 6);
        info.setTimeUse(timeUse);
        info.setSubmitTime(DateUtil.parse(req.getSubmitTime()));
        info.setCreatedBy(UserContextHolder.getUserContext().getUserName());
        info.setCreatedTime(new Date());
        info.setIsDeleted(0);
        return info;
    }

    private void storeNotAnsweredSubject(List<PracticeSetDetail> allSubjectList, List<PracticeDetail> answeredSubjectList, Long practiceId) {
        // 1. 筛选出未作答的题目
        List<PracticeSetDetail> notAnsweredPracticeSetDetailList = new ArrayList<>();
        // 已作答的题目为空，表示所有问题都没作答
        if (CollectionUtil.isEmpty(answeredSubjectList)) {
            notAnsweredPracticeSetDetailList.addAll(allSubjectList);
        }
        // 已作答的题目不为空，则将未作答的题目添加 notAnsweredPracticeSetDetailList
        else {
            List<Long> answerSubjectIdList = answeredSubjectList.stream().map(PracticeDetail::getSubjectId).collect(Collectors.toList());
            notAnsweredPracticeSetDetailList.addAll(allSubjectList.stream()
                    .filter(item -> !answerSubjectIdList.contains(item.getSubjectId())).collect(Collectors.toList()));
        }
        // 2. 将未作答的题目添加至 practiceDetail 表中
        if (CollectionUtil.isNotEmpty(notAnsweredPracticeSetDetailList)) {
            List<PracticeDetail> notAnsweredPracticeDetailList = notAnsweredPracticeSetDetailList.stream()
                    .map(item -> convertPracticeSetDetail2PracticeDetail(practiceId, item)).collect(Collectors.toList());
            practiceDetailDao.insertBatch(notAnsweredPracticeDetailList);
        }
    }

    private static PracticeDetail convertPracticeSetDetail2PracticeDetail(Long practiceId, PracticeSetDetail item) {
        PracticeDetail practiceDetail = new PracticeDetail();
        practiceDetail.setPracticeId(practiceId);
        practiceDetail.setSubjectId(item.getSubjectId());
        practiceDetail.setSubjectType(item.getSubjectType());
        practiceDetail.setAnswerStatus(0);
        practiceDetail.setCreatedBy(item.getCreatedBy());
        practiceDetail.setCreatedTime(new Date());
        practiceDetail.setIsDeleted(0);
        return practiceDetail;
    }

    private Double calculateCorrectRate(List<PracticeSetDetail> allSubjectList, List<PracticeDetail> answeredSubjectList) {

        int subjectNum = allSubjectList.size();
        int correctNum = (int) answeredSubjectList.stream().filter(item -> item.getAnswerStatus() == 1).count();

        BigDecimal correctRateBD = new BigDecimal(correctNum).divide(new BigDecimal(subjectNum), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100.00"));
        return correctRateBD.doubleValue();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PracticeDetail queryById(Long id) {
        return this.practiceDetailDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param practiceDetail 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<PracticeDetail> queryByPage(PracticeDetail practiceDetail
            , int start
            , int size) {
        return this.practiceDetailDao.queryByPage(practiceDetail, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceDetail 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(PracticeDetail practiceDetail) {
        return this.practiceDetailDao.count(practiceDetail);
    }

    /**
     * 新增数据
     *
     * @param practiceDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(PracticeDetail practiceDetail) {
        return practiceDetailDao.insert(practiceDetail);
    }

    /**
     * 批量新增数据
     *
     * @param practiceDetailList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<PracticeDetail> practiceDetailList) {
        return practiceDetailDao.insertBatch(practiceDetailList);
    }


    /**
     * 修改数据
     *
     * @param practiceDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int update(PracticeDetail practiceDetail) {
        return practiceDetailDao.update(practiceDetail);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.practiceDetailDao.deleteById(id);
    }
}
