package com.jingdianjichi.subject.api.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目信息表(SubjectInfo) DTO对象
 *
 * @author jay
 * @since 2024-06-18 18:13:19
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = -92870786840147434L;
    /**
     * 选项类型
     */
    private Integer optionType;
    /**
     * 选项内容
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;
}

