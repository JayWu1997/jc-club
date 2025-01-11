package com.jingdianjichi.subject.domain.service;

import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * 题目标签领域服务接口
 * 该接口定义了与题目标签相关的领域业务操作。
 */
public interface SubjectLabelDomainService {

    /**
     * 新增题目标签
     * @param subjectLabelBO 题目标签
     * @return 是否添加成功
     */
    Boolean insert(SubjectLabelBO subjectLabelBO);

    /**
     * 更新题目标签
     * @param subjectLabelBO 待更新的题目标签信息
     * @return 是否修改成功
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除题目标签
     * @param subjectsLabelBO 待删除的题目标签信息
     * @return 是否删除成功
     */
    Boolean delete(SubjectLabelBO subjectsLabelBO);

    /**
     * 根据分类 ID 查询标签
     * @param subjectLabelBO bo
     * @return 标签列表
     */
    List<SubjectLabelBO> queryBatchByCategoryId(SubjectLabelBO subjectLabelBO);

    /**
     * 根据题目 ID 批量查询标签
     * @param subjectLabelBO
     * @return
     */
    List<SubjectLabelBO> queryBatchBySubjectId(SubjectLabelBO subjectLabelBO);
}
