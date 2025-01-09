package com.jingdianjichi.practice.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jingdianjichi.practice.server.vo.SpecialPracticeCategoryVO;
import com.jingdianjichi.practice.server.vo.SpecialPracticeLabelVO;
import com.jingdianjichi.practice.server.vo.SpecialPracticeVO;
import com.jingdianjichi.practice.server.dao.PracticeSetDao;
import com.jingdianjichi.practice.server.entity.PracticeSet;
import com.jingdianjichi.practice.server.service.PracticeSetService;
import com.jingdianjichi.subject.api.req.SubjectCategoryDTO;
import com.jingdianjichi.subject.api.req.SubjectLabelDTO;
import com.jingdianjichi.subject.api.service.SubjectCategoryFeignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     * @param practiceSet 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(PracticeSet practiceSet) {
        return practiceSetDao.insert(practiceSet);
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
