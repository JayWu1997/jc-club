package com.jingdianjichi.practice.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.jingdianjichi.practice.api.req.PracticeSetDTO;
import com.jingdianjichi.practice.server.common.exception.BusinessErrorEnum;
import com.jingdianjichi.practice.server.common.exception.BusinessException;
import com.jingdianjichi.practice.server.dao.PracticeSetDao;
import com.jingdianjichi.practice.server.dao.PracticeSetDetailDao;
import com.jingdianjichi.practice.server.entity.PracticeSet;
import com.jingdianjichi.practice.server.entity.PracticeSetDetail;
import com.jingdianjichi.practice.server.req.GetSubjectsReq;
import com.jingdianjichi.practice.server.service.PracticeSetService;
import com.jingdianjichi.practice.server.vo.*;
import com.jingdianjichi.subject.api.req.SubjectCategoryDTO;
import com.jingdianjichi.subject.api.req.SubjectInfoDTO;
import com.jingdianjichi.subject.api.req.SubjectLabelDTO;
import com.jingdianjichi.subject.api.resp.Result;
import com.jingdianjichi.subject.api.service.SubjectCategoryFeignService;
import com.jingdianjichi.subject.api.service.SubjectInfoFeignService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套题信息表(PracticeSet)表服务实现类
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Service
public class PracticeSetServiceImpl implements PracticeSetService {
    @Resource
    private PracticeSetDao practiceSetDao;

    @Resource
    private SubjectCategoryFeignService subjectCategoryFeignService;

    @Resource
    private SubjectInfoFeignService subjectInfoFeignService;
    @Resource
    private PracticeSetDetailDao practiceSetDetailDao;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PracticeSet queryById(Long id) {
        return this.practiceSetDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param practiceSet 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<PracticeSet> queryByPage(PracticeSet practiceSet
            , int start
            , int size) {
        return this.practiceSetDao.queryByPage(practiceSet, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceSet 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(PracticeSet practiceSet) {
        return this.practiceSetDao.count(practiceSet);
    }

    /**
     * 新增数据
     *
     * @param dto 实例对象
     * @return 实例对象
     */
    @Override
    public PracticeSetVO insert(PracticeSetDTO dto) {
        int total = 5;

        // 查询题目
        List<PracticeSubjectDetailVO> voList = queryPracticeSubjectDetailVO(dto, total);
        if (CollectionUtil.isEmpty(voList) || voList.size() < total) {
            throw new BusinessException(BusinessErrorEnum.FAIL, "题目数量不足" + total);
        }

        // 设置套题名字
        PracticeSet practiceSet = new PracticeSet();
        practiceSet.setSetType(1);
        practiceSet.setSetName(getSetName(dto));

        // 查询岗位名
        practiceSet.setPrimaryCategoryId(getPrimaryCategoryId(dto));

        // 事务提交
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            // 保存套题
            practiceSetDao.insert(practiceSet);
            //保存套题题目
            List<PracticeSetDetail> practiceSetSubjectList = new ArrayList<>();
            voList.forEach(vo -> {
                PracticeSetDetail practiceSetSubject = new PracticeSetDetail();
                practiceSetSubject.setSetId(practiceSet.getId());
                practiceSetSubject.setSubjectId(vo.getSubjectId());
                practiceSetSubject.setSubjectType(vo.getSubjectType());
                practiceSetSubjectList.add(practiceSetSubject);
            });
            practiceSetDetailDao.insertBatch(practiceSetSubjectList);

            platformTransactionManager.commit(status);
        } catch (Exception e) {
            platformTransactionManager.rollback(status);
            throw new BusinessException(BusinessErrorEnum.FAIL, "保存套题失败", e);
        }

        PracticeSetVO vo = new PracticeSetVO();
        vo.setSetId(practiceSet.getId());
        vo.setSetName(practiceSet.getSetName());

        return vo;
    }

    private Long getPrimaryCategoryId(PracticeSetDTO dto) {
        List<Long> categoryIdList = dto.getAssembleIds().stream().map(assembleId -> Long.valueOf(assembleId.split("-")[0])).collect(Collectors.toList());
        SubjectCategoryDTO query = new SubjectCategoryDTO();
        query.setId(categoryIdList.get(0));
        Result<SubjectCategoryDTO> subjectCategoryDTOResult = subjectCategoryFeignService.queryPrimaryCategoryByCategoryId(query);
        if (ObjectUtil.isNotNull(subjectCategoryDTOResult) && ObjectUtil.isNotNull(subjectCategoryDTOResult.getData())) {
            return subjectCategoryDTOResult.getData().getId();
        }
        return null;
    }

    private String getSetName(PracticeSetDTO dto) {


        HashSet<Long> categoryIdSet = new HashSet<>();
        dto.getAssembleIds().forEach(id -> {
            categoryIdSet.add(Long.valueOf(id.split("-")[0]));
        });
        SubjectCategoryDTO categoryQuery = new SubjectCategoryDTO();
        categoryQuery.setIdList(new ArrayList<>(categoryIdSet));
        Result<List<SubjectCategoryDTO>> listResult = subjectCategoryFeignService.queryByIdList(categoryQuery);

        String setName = null;
        if (ObjectUtil.isNotNull(listResult) && CollectionUtil.isNotEmpty(listResult.getData())) {
            List<SubjectCategoryDTO> categoryDTOList = listResult.getData();
            int i = 0;
            StringBuilder sb = new StringBuilder();
            while (i < 2 && i < categoryDTOList.size()) {
                sb.append(categoryDTOList.get(i).getCategoryName());
                i++;
            }
            sb.append("专项练习");
            setName = sb.toString();
        }
        return setName;
    }

    private List<Long> getLabelList(List<String> assembleIds) {
        return assembleIds.stream().map(assembleId -> Long.valueOf(assembleId.split("-")[1])).collect(Collectors.toList());
    }

    private List<PracticeSubjectDetailVO> queryPracticeSubjectDetailVO(PracticeSetDTO dto, int total) {
        List<PracticeSubjectDetailVO> voList = new ArrayList<>();

        SubjectInfoDTO queryReq = new SubjectInfoDTO();
        queryReq.setQueryCount(total);
        queryReq.setLabelIds(getLabelList(dto.getAssembleIds()));
        Result<List<SubjectInfoDTO>> listResult = subjectInfoFeignService.queryByConditionInMultiTable(queryReq);
        if (ObjectUtil.isNotNull(listResult) && CollectionUtil.isNotEmpty(listResult.getData())) {
            voList = listResult.getData().stream().map(subjectInfoDTO -> {
                PracticeSubjectDetailVO vo = new PracticeSubjectDetailVO();
                vo.setSubjectId(subjectInfoDTO.getId());
                vo.setSubjectType(subjectInfoDTO.getSubjectType());
                return vo;
            }).collect(Collectors.toList());
        }
        return voList;
    }

    /**
     * 批量新增数据
     *
     * @param practiceSetList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<PracticeSet> practiceSetList) {
        return practiceSetDao.insertBatch(practiceSetList);
    }


    /**
     * 修改数据
     *
     * @param practiceSet 实例对象
     * @return 实例对象
     */
    @Override
    public int update(PracticeSet practiceSet) {
        return practiceSetDao.update(practiceSet);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.practiceSetDao.deleteById(id);
    }

    /**
     * 获取专项练习标签列表
     *
     * @return
     */
    @Override
    public List<SpecialPracticeVO> getSpecialPracticeContent() {
        List<SpecialPracticeVO> voList = new ArrayList<>();
        // 1. 查询所有一级分类
        List<SubjectCategoryDTO> primaryCategoryList = subjectCategoryFeignService.queryPrimaryCategory().getData();
        if (CollectionUtil.isEmpty(primaryCategoryList)) {
            return new ArrayList<>();
        }

        voList = primaryCategoryList.stream().map(category -> {
            SpecialPracticeVO vo = new SpecialPracticeVO();
            vo.setPrimaryCategoryName(category.getCategoryName());
            vo.setPrimaryCategoryId(category.getId());
            // 2. 根据一级分类查询所有二级分类及其标签
            List<SubjectCategoryDTO> categoryDTOList = subjectCategoryFeignService.queryCategoryAndLabel(category).getData();
            // 2.1. 注入SpecialPracticeVO.categoryList
            injectSecondaryCategoryListIntoPrimaryCategory(categoryDTOList, vo);
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }

    /**
     * 根据 paracticeId 或者 setId 获取套题题目列表
     *
     * @param req
     * @return
     */
    @Override
    public GetSubjectsVO getSubjects(GetSubjectsReq req) {
        GetSubjectsVO resultVO = new GetSubjectsVO();
        List<PracticeSubjectDetailVO> subjectList = Collections.emptyList();

        // 查询套题名称
        PracticeSet practiceSet = practiceSetDao.queryById(req.getSetId());
        if (ObjectUtil.isNull(practiceSet)) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "此套题不存在");
        }
        resultVO.setTitle(practiceSet.getSetName());

        // 查询套题下的所有题目
        Long setId = req.getSetId();
        PracticeSetDetail subjectQuery = new PracticeSetDetail();
        subjectQuery.setSetId(setId);
        subjectQuery.setIsDeleted(0);
        List<PracticeSetDetail> detailList = practiceSetDetailDao.queryByPage(subjectQuery, 0, 100);
        if (CollectionUtil.isNotEmpty(detailList)) {
            subjectList = detailList.stream().map(detail -> {
                PracticeSubjectDetailVO vo = new PracticeSubjectDetailVO();
                vo.setSubjectId(detail.getSubjectId());
                vo.setSubjectType(detail.getSubjectType());
                return  vo;
            }).collect(Collectors.toList());
        }

        resultVO.setSubjectList(subjectList);
        return resultVO;
    }

    private void injectSecondaryCategoryListIntoPrimaryCategory(List<SubjectCategoryDTO> categoryDTOList, SpecialPracticeVO vo) {
        if (CollectionUtil.isNotEmpty(categoryDTOList)) {
            vo.setCategoryList(categoryDTOList.stream().map(categoryDTO -> {
                SpecialPracticeCategoryVO categoryVO = new SpecialPracticeCategoryVO();
                categoryVO.setCategoryName(categoryDTO.getCategoryName());
                categoryVO.setCategoryId(categoryDTO.getId());
                List<SubjectLabelDTO> labelDTOList = categoryDTO.getLabelDTOList();
                // 3. 注入SpecialPracticeCategoryVO.labelList
                injectLabelListIntoSecondaryCategory(labelDTOList, categoryVO);
                return categoryVO;
            }).collect(Collectors.toList()));
        }
    }

    private static void injectLabelListIntoSecondaryCategory(List<SubjectLabelDTO> labelDTOList, SpecialPracticeCategoryVO categoryVO) {
        if (CollectionUtil.isNotEmpty(labelDTOList)) {
            categoryVO.setLabelList(labelDTOList.stream().map(label -> {
                SpecialPracticeLabelVO labelVO = new SpecialPracticeLabelVO();
                labelVO.setLabelName(label.getLabelName());
                labelVO.setAssembleId(categoryVO.getCategoryId() + "-" + label.getId());
                return labelVO;
            }).collect(Collectors.toList()));
        }
    }
}
