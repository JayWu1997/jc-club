package com.jingdianjichi.subject.domain.handler.subject;

import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;

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
}
