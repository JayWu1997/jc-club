package com.jingdianjichi.subject.domain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.domain.convert.SubjectLabelBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import com.jingdianjichi.subject.domain.service.SubjectLabelDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import com.jingdianjichi.subject.infra.basic.service.SubjectCategoryService;
import com.jingdianjichi.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目标签领域服务接口的实现
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectCategoryService subjectCategoryService;

    /**
     * 新增题目标签
     *
     * @param subjectLabelBO 题目标签
     * @return 是否添加成功
     */
    @Override
    public Boolean insert(SubjectLabelBO subjectLabelBO) {
        SubjectLabel queryReq = new SubjectLabel();
        queryReq.setLabelName(subjectLabelBO.getLabelName());
        queryReq.setCategoryId(subjectLabelBO.getCategoryId());
        if (subjectLabelService.countByCondition(queryReq) > 0) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "该标签已存在");
        }
        return subjectLabelService.insert(SubjectLabelBOConverter.INSTANCE.convertBO2Entity(subjectLabelBO)) > 0;
    }

    /**
     * 更新题目标签
     *
     * @param subjectLabelBO 待更新的题目标签信息
     * @return 是否修改成功
     */
    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        return subjectLabelService.update(SubjectLabelBOConverter.INSTANCE.convertBO2Entity(subjectLabelBO)) > 0;
    }

    /**
     * 删除题目标签
     *
     * @param subjectsLabelBO 待删除的题目标签信息
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(SubjectLabelBO subjectsLabelBO) {
        SubjectLabel subjectLabel = new SubjectLabel();
        subjectLabel.setId(subjectsLabelBO.getId());
        subjectLabel.setIsDeleted(IsDeletedEnum.DELETED.getCode().longValue());
        return subjectLabelService.update(subjectLabel) > 0;
    }

    /**
     * 根据分类 ID 查询标签
     *
     * @param subjectLabelBO bo
     * @return 标签列表
     */
    @Override
    public List<SubjectLabelBO> queryBatchByCategoryId(SubjectLabelBO subjectLabelBO) {
        List<SubjectLabelBO> boList = new ArrayList<>();
        SubjectCategory category = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if (category == null) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "分类不存在");
        }
        List<Long> categoryIdList = new ArrayList<>();
        if (category.getParentId() == 0) {
            SubjectCategory queryChildren = new SubjectCategory();
            queryChildren.setParentId(subjectLabelBO.getCategoryId());
            List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(queryChildren);
            if (CollectionUtil.isNotEmpty(categoryList)) {
                categoryIdList.addAll(categoryList.stream().map(SubjectCategory::getId).collect(Collectors.toList()));
            }
        } else {
            categoryIdList.add(subjectLabelBO.getCategoryId());
        }
        List<SubjectLabel> labelList = subjectLabelService.queryDistinctLabelListByCategoryIds(categoryIdList);
        if (CollectionUtil.isNotEmpty(labelList)) {
            boList = SubjectLabelBOConverter.INSTANCE.convertEntity2BO(labelList);
        }
        return boList;
    }
}
