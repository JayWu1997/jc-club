package com.jingdianjichi.subject.domain.handler.subject;

import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {

    /**
     * 题目类型识别
     * @return 返回对应的题目类型
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 添加题目
     *
     * @param subjectInfoBO 题目信息
     */
    void insert(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目信息
     *
     * @param subjectId@return 返回查询到的题目信息对象；如果未找到相关信息，则返回null
     */
    SubjectOptionBO querySubjectOptions(Long subjectId);

}
