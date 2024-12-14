package com.jingdianjichi.subject.domain.service.impl;

import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.domain.convert.SubjectLabelBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import com.jingdianjichi.subject.domain.service.SubjectLabelDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;
import com.jingdianjichi.subject.infra.basic.service.SubjectLabelService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 题目标签领域服务接口的实现
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    /**
     * 新增题目标签
     *
     * @param subjectLabelBO 题目标签
     * @return 是否添加成功
     */
    @Override
    public Boolean insert(SubjectLabelBO subjectLabelBO) {
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
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(subjectLabelBO.getCategoryId());
        subjectMapping.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        List<Long> labelIdList = subjectMappingService.queryDistinctLabelIdsByCondition(subjectMapping);
        if(CollectionUtils.isEmpty(labelIdList)) {
            return Collections.emptyList();
        }
        List<SubjectLabel> labelList = subjectLabelService.queryBatchByIds(labelIdList);
        return SubjectLabelBOConverter.INSTANCE.convertEntity2BO(labelList);
    }
}
