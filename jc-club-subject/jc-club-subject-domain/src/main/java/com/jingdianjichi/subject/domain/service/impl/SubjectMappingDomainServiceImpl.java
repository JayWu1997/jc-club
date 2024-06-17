package com.jingdianjichi.subject.domain.service.impl;

import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.domain.convert.SubjectMappingBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectMappingBO;
import com.jingdianjichi.subject.domain.service.SubjectMappingDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 题目、分类、标签映射领域服务接口的实现
 */
@Service
@Slf4j
public class SubjectMappingDomainServiceImpl implements SubjectMappingDomainService {

    @Resource
    private SubjectMappingService subjectMappingService;

    /**
     * 新增题目、分类、标签映射
     *
     * @param subjectMappingBO 题目、分类、标签映射
     * @return 是否添加成功
     */
    @Override
    public Boolean insert(SubjectMappingBO subjectMappingBO) {
        return subjectMappingService.insert(SubjectMappingBOConverter.INSTANCE.convertBO2Entity(subjectMappingBO)) > 0;
    }

    /**
     * 更新题目、分类、标签映射
     *
     * @param subjectMappingBO 待更新的题目、分类、标签映射信息
     * @return 是否修改成功
     */
    @Override
    public Boolean update(SubjectMappingBO subjectMappingBO) {
        return subjectMappingService.update(SubjectMappingBOConverter.INSTANCE.convertBO2Entity(subjectMappingBO)) > 0;
    }

    /**
     * 删除题目、分类、标签映射
     *
     * @param subjectsMappingBO 待删除的题目、分类、标签映射信息
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(SubjectMappingBO subjectsMappingBO) {
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setId(subjectsMappingBO.getId());
        subjectMapping.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        return subjectMappingService.update(subjectMapping) > 0;
    }
}
