package com.jingdianjichi.subject.domain.service;

import com.jingdianjichi.subject.domain.entity.SubjectMappingBO;

import java.util.List;

/**
 * 题目、分类、标签映射领域服务接口
 * 该接口定义了与三者映射相关的领域业务操作。
 */
public interface SubjectMappingDomainService {

    /**
     * 新增题目、分类、标签映射
     * @param subjectMappingBO 题目、分类、标签映射
     * @return 是否添加成功
     */
    Boolean insert(SubjectMappingBO subjectMappingBO);

    /**
     * 更新题目、分类、标签映射
     * @param subjectMappingBO 待更新的题目、分类、标签映射信息
     * @return 是否修改成功
     */
    Boolean update(SubjectMappingBO subjectMappingBO);

    /**
     * 删除题目、分类、标签映射
     * @param subjectsMappingBO 待删除的题目、分类、标签映射信息
     * @return 是否删除成功
     */
    Boolean delete(SubjectMappingBO subjectsMappingBO);

    /**
     * 根据题目ID批量查询映射
     * @param subjectMappingBO
     * @return
     */
    List<SubjectMappingBO> queryBatchBySubjectIds(SubjectMappingBO subjectMappingBO);
}
