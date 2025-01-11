package com.jingdianjichi.practice.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.jingdianjichi.auth.api.req.AuthUserDTO;
import com.jingdianjichi.auth.api.service.UserFeignService;
import com.jingdianjichi.practice.server.common.context.UserContextHolder;
import com.jingdianjichi.practice.server.common.exception.BusinessErrorEnum;
import com.jingdianjichi.practice.server.common.exception.BusinessException;
import com.jingdianjichi.practice.server.dao.PracticeDetailDao;
import com.jingdianjichi.practice.server.dao.PracticeInfoDao;
import com.jingdianjichi.practice.server.dao.PracticeSetDao;
import com.jingdianjichi.practice.server.dao.PracticeSetDetailDao;
import com.jingdianjichi.practice.server.entity.*;
import com.jingdianjichi.practice.server.req.*;
import com.jingdianjichi.practice.server.service.PracticeDetailService;
import com.jingdianjichi.practice.server.vo.*;
import com.jingdianjichi.subject.api.req.SubjectAnswerDTO;
import com.jingdianjichi.subject.api.req.SubjectInfoDTO;
import com.jingdianjichi.subject.api.req.SubjectLabelDTO;
import com.jingdianjichi.subject.api.req.SubjectMappingDTO;
import com.jingdianjichi.subject.api.resp.Result;
import com.jingdianjichi.subject.api.service.SubjectInfoFeignService;
import com.jingdianjichi.subject.api.service.SubjectLabelFeignService;
import com.jingdianjichi.subject.api.service.SubjectMappingFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
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
    @Qualifier("com.jingdianjichi.subject.api.service.SubjectInfoFeignService")
    @Autowired
    private SubjectInfoFeignService subjectInfoFeignService;
    @Qualifier("com.jingdianjichi.subject.api.service.SubjectLabelFeignService")
    @Autowired
    private SubjectLabelFeignService subjectLabelFeignService;
    @Qualifier("com.jingdianjichi.subject.api.service.SubjectMappingFeignService")
    @Autowired
    private SubjectMappingFeignService subjectMappingFeignService;
    @Qualifier("com.jingdianjichi.auth.api.service.UserFeignService")
    @Autowired
    private UserFeignService userFeignService;

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

    private PracticeInfo initPracticeInfo(SubmitPracticeDetailReq req) {
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

    private PracticeDetail convertPracticeSetDetail2PracticeDetail(Long practiceId, PracticeSetDetail item) {
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

    /**
     * 提交题目
     *
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean submitSubject(SubmitSubjectReq req) {
        String timeUse = req.getTimeUse();
        if ("0".equals(timeUse)) {
            timeUse = "000000";
        }
        timeUse = timeUse.substring(0, 2) + ":" + timeUse.substring(2, 4) + ":" + timeUse.substring(4, 6);
        PracticeInfo infoUpdate = new PracticeInfo();
        infoUpdate.setId(req.getPracticeId());
        infoUpdate.setSubmitTime(new Date());
        infoUpdate.setTimeUse(timeUse);
        practiceInfoDao.update(infoUpdate);

        PracticeDetail practiceDetail = new PracticeDetail();
        practiceDetail.setPracticeId(req.getPracticeId());
        practiceDetail.setSubjectId(req.getSubjectId());
        practiceDetail.setSubjectType(req.getSubjectType());
        // 答案排序(eg: 1,3,4)
        Collections.sort(req.getAnswerContents());
        practiceDetail.setAnswerContent(req.getAnswerContents().toString());

        SubjectInfoDTO subjectQuery = new SubjectInfoDTO();
        subjectQuery.setId(req.getSubjectId());
        SubjectInfoDTO subjectInfoDTO = subjectInfoFeignService.querySubjectInfo(subjectQuery).getData();
        if (ObjectUtil.isNull(subjectInfoDTO)) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "提交的题目不存在");
        }
        if (req.getSubjectType() != 4) {
            List<SubjectAnswerDTO> optionList = subjectInfoDTO.getOptionList();
            List<Integer> correctOptionIdList = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(optionList)) {
                optionList.forEach(item -> {
                    if (item.getIsCorrect() == 1) {
                        correctOptionIdList.add(item.getOptionType());
                    }
                });
            }
            if (CollectionUtil.isEqualList(req.getAnswerContents(), correctOptionIdList)) {
                practiceDetail.setAnswerStatus(1);
            } else {
                practiceDetail.setAnswerStatus(0);
            }
        }
        practiceDetailDao.insertOrUpdateBatch(Collections.singletonList(practiceDetail));

        return Boolean.TRUE;
    }

    /**
     * 获取答题详情
     *
     * @param req
     * @return
     */
    @Override
    public List<ScoreDetailVO> getScoreDetail(GetScoreDetailReq req) {
        List<ScoreDetailVO> scoreDetailVOList = new ArrayList<>();
        PracticeDetail subjectQuery = new PracticeDetail();
        subjectQuery.setPracticeId(req.getPracticeId());
        subjectQuery.setIsDeleted(0);
        List<PracticeDetail> practiceDetailList = practiceDetailDao.queryByPage(subjectQuery, 0, 1000);
        if (CollectionUtil.isNotEmpty(practiceDetailList)) {
            practiceDetailList.forEach(item -> {
                ScoreDetailVO scoreDetailVO = new ScoreDetailVO();
                scoreDetailVO.setSubjectId(item.getSubjectId());
                scoreDetailVO.setIsCorrect(item.getAnswerStatus());
                scoreDetailVO.setSubjectType(item.getSubjectType());
                scoreDetailVOList.add(scoreDetailVO);
            });
        }
        return scoreDetailVOList;
    }

    /**
     * 获取题目解析
     *
     * @param req
     * @return
     */
    @Override
    public SubjectDetailVO getSubjectDetail(GetSubjectDetailReq req) {

        SubjectDetailVO resultVO = new SubjectDetailVO();
        // 1. 填充正确答案信息
        SubjectInfoDTO subjectInfoQuery = new SubjectInfoDTO();
        subjectInfoQuery.setId(req.getSubjectId());
        Result<SubjectInfoDTO> infoResult = subjectInfoFeignService.querySubjectInfo(subjectInfoQuery);
        if (ObjectUtil.isNull(infoResult) || ObjectUtil.isNull(infoResult.getData())) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "该题目不存在或题目查询失败");
        }
        SubjectInfoDTO subjectInfoDTO = infoResult.getData();
        resultVO.setSubjectName(subjectInfoDTO.getSubjectName());
        List<SubjectAnswerDTO> optionList = subjectInfoDTO.getOptionList();
        if (CollectionUtil.isNotEmpty(optionList)) {
            if (subjectInfoDTO.getSubjectType() == 3) {
                List<SubjectOptionVO> optionVOList = new ArrayList<>();
                SubjectOptionVO optionVO = new SubjectOptionVO();
                optionVO.setOptionContent("正确");
                optionVO.setOptionType(1);
                optionVO.setIsCorrect(optionList.get(0).getIsCorrect());
                optionVOList.add(optionVO);
                optionVO = new SubjectOptionVO();
                optionVO.setOptionContent("错误");
                optionVO.setOptionType(2);
                optionVO.setIsCorrect(optionList.get(0).getIsCorrect() == 1 ? 0 : 1);
                optionVOList.add(optionVO);
                resultVO.setOptionList(optionVOList);
                resultVO.setCorrectAnswer(Arrays.asList(optionList.get(0).getIsCorrect() == 1 ? 1 : 2));
            } else {
                resultVO.setOptionList(optionList.stream().map(item -> {
                    SubjectOptionVO optionVO = new SubjectOptionVO();
                    optionVO.setOptionContent(item.getOptionContent());
                    optionVO.setOptionType(item.getOptionType());
                    optionVO.setIsCorrect(item.getIsCorrect());
                    return optionVO;
                }).collect(Collectors.toList()));
                resultVO.setCorrectAnswer(optionList.stream()
                        .filter(item -> item.getIsCorrect() == 1).map(SubjectAnswerDTO::getOptionType).collect(Collectors.toList()));
            }
        }
        resultVO.setLabelNames(subjectInfoDTO.getLabelNames());

        // 2. 填充用户答案信息
        PracticeDetail practiceDetailQuery = new PracticeDetail();
        practiceDetailQuery.setPracticeId(req.getPracticeId());
        practiceDetailQuery.setSubjectId(req.getSubjectId());
        practiceDetailQuery.setIsDeleted(0);
        List<PracticeDetail> practiceDetailList = practiceDetailDao.queryByPage(practiceDetailQuery, 0, 1);
        if (CollectionUtil.isEmpty(practiceDetailList)) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "用户未作答或题目查询失败");
        }
        PracticeDetail practiceDetail = practiceDetailList.get(0);
        if (StrUtil.isNotBlank(practiceDetail.getAnswerContent())) {
            resultVO.setRespondAnswer(JSON.parseArray(practiceDetail.getAnswerContent(), Integer.class));
        }
        return resultVO;
    }

    /**
     * 获取报告
     *
     * @param req practiceId
     * @return
     */
    @Override
    public ReportVO getReport(GetReportReq req) {
        ReportVO resultVO = new ReportVO();
        long correctSubjectCount = 0;
        String setName = "";
        List<ReportSkillVO> skillVOList = new ArrayList<>();

        PracticeInfo practiceInfo = practiceInfoDao.queryById(req.getPracticeId());
        if (ObjectUtil.isNull(practiceInfo)) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "该次练习不存在");
        }
        if (practiceInfo.getCompleteStatus() == 0) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "该次练习还未完成");
        }

        // 获取套题名称
        PracticeSet practiceSet = practiceSetDao.queryById(practiceInfo.getSetId());
        if (ObjectUtil.isNull(practiceSet)) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "该套题不存在");
        }
        setName = practiceSet.getSetName();

        // 查询该次练习中的所有题目
        PracticeDetail practiceDetailQuery = new PracticeDetail();
        practiceDetailQuery.setPracticeId(req.getPracticeId());
        practiceDetailQuery.setIsDeleted(0);
        List<PracticeDetail> allSubjectPracticeDetalList = practiceDetailDao.queryByPage(practiceDetailQuery, 0, 1000);
        if (CollectionUtil.isEmpty(allSubjectPracticeDetalList)) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "该次练习题目查询失败");
        }

        // 计算正确题目数量
        correctSubjectCount = allSubjectPracticeDetalList.stream().filter(item -> item.getAnswerStatus() == 1).count();

        // 生成 skillVOList
        genSkillVOList(allSubjectPracticeDetalList, skillVOList);


        resultVO.setTitle(setName);
        resultVO.setCorrectSubject(String.valueOf(correctSubjectCount));
        resultVO.setSkill(skillVOList);
        return resultVO;
    }

    /**
     * 获取练习排行榜
     *
     * @return
     */
    @Override
    public List<PracticeRankVO> getPracticeRankList() {
        List<PracticeRankVO> rankVOList = new ArrayList<>();
        List<PracticeRankUserPO> practiceRankList = practiceInfoDao.queryPracticeRank();
        if (CollUtil.isNotEmpty(practiceRankList)) {
            List<String> userIdList = practiceRankList.stream().map(PracticeRankUserPO::getCreatedBy).collect(Collectors.toList());
            AuthUserDTO userQuery = new AuthUserDTO();
            userQuery.setUserNameList(userIdList);
            com.jingdianjichi.auth.api.resp.Result<List<AuthUserDTO>> userListResult = userFeignService.batchQueryByUserNames(userQuery);
            if (ObjUtil.isNotNull(userListResult) && CollUtil.isNotEmpty(userListResult.getData())) {
                List<AuthUserDTO> userInfoList = userListResult.getData();
                practiceRankList.forEach(practiceInfo -> {
                    PracticeRankVO rankVO = new PracticeRankVO();
                    AuthUserDTO userInfo = userInfoList.stream().filter(user -> user.getUserName().equals(practiceInfo.getCreatedBy())).findFirst().orElse(null);
                    if (ObjectUtil.isNotNull(userInfo)) {
                        rankVO.setAvatar(userInfo.getAvatar());
                        rankVO.setName(userInfo.getNickName());
                        rankVO.setCount(practiceInfo.getCount().intValue());
                        rankVOList.add(rankVO);
                    }
                });
            }
        }
        return rankVOList;
    }

    /**
     * 放弃练习
     *
     * @param practiceId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean giveUp(Long practiceId) {
        practiceInfoDao.deleteById(practiceId);
        practiceDetailDao.deleteByPracticeId(practiceId);
        return Boolean.TRUE;
    }

    private void genSkillVOList(List<PracticeDetail> allSubjectPracticeDetalList, List<ReportSkillVO> skillVOList) {
        SubjectMappingDTO subjectMappingQuery = new SubjectMappingDTO();
        List<Long> allSubjectIdList = allSubjectPracticeDetalList.stream().map(PracticeDetail::getSubjectId).collect(Collectors.toList());
        subjectMappingQuery.setSubjectIdList(allSubjectIdList);
        Result<List<SubjectMappingDTO>> mappingListResult = subjectMappingFeignService.queryBatchBySubjectIds(subjectMappingQuery);
        if (ObjUtil.isNotNull(mappingListResult) && CollUtil.isNotEmpty(mappingListResult.getData())) {
            List<SubjectMappingDTO> mappingList = mappingListResult.getData();
            Map<Long, List<Long>> labelId2SubjectIdsMap = mappingList.stream().collect(Collectors.groupingBy(SubjectMappingDTO::getLabelId, Collectors.mapping(SubjectMappingDTO::getSubjectId, Collectors.toList())));
            SubjectLabelDTO labelQuery = new SubjectLabelDTO();
            labelQuery.setSubjectIdList(allSubjectIdList);
            Result<List<SubjectLabelDTO>> labelListResult = subjectLabelFeignService.queryBatchBySubjectIds(labelQuery);
            if (ObjUtil.isNotNull(labelListResult) && CollUtil.isNotEmpty(labelListResult.getData())) {
                List<SubjectLabelDTO> labelList = labelListResult.getData();
                labelId2SubjectIdsMap.forEach((labelId, subjectIdList) -> {
                    ReportSkillVO skillVO = new ReportSkillVO();
                    skillVO.setName(labelList.stream().filter(item -> item.getId().equals(labelId)).findFirst().map(SubjectLabelDTO::getLabelName).orElse(""));
                    int correctCount = (int) allSubjectPracticeDetalList.stream().filter(item -> item.getAnswerStatus() == 1 && subjectIdList.contains(item.getSubjectId())).count();
                    skillVO.setStar(new BigDecimal(correctCount).divide(new BigDecimal(subjectIdList.size()), 4, RoundingMode.HALF_UP)
                            .multiply(new BigDecimal("100")));
                    skillVOList.add(skillVO);
                });
            }
        }
    }
}
