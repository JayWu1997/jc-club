package com.jingdianjichi.subject.domain.service;

import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;

/**
 * 题目信息领域服务接口
 * 该接口定义了题目信息相关的领域业务操作。
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目信息
     *
     * @param subjectInfoBO 题目信息
     */
    void insert(SubjectInfoBO subjectInfoBO);

    /**
     * 更新题目信息
     * @param subjectInfoBO 待更新的题目信息
     * @return 是否修改成功
     */
    Boolean update(SubjectInfoBO subjectInfoBO);

    /**
     * 删除题目信息
     * @param subjectsInfoBO 待删除的题目信息
     * @return 是否删除成功
     */
    Boolean delete(SubjectInfoBO subjectsInfoBO);

    /**
     * 分页查询题目信息
     * @param subjectInfoBO 查询条件
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 获取题目信息
     *
     * @param subjectInfoBO 包含查询条件的主题信息对象，不能为空
     * @return 返回一个填充了详细信息的题目信息对象如果找不到相关信息，则返回null
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}
