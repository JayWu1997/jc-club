package com.jingdianjichi.subject.domain.entity;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2024/12/14 下午8:55
 */
@Data
public class SubjectOptionBO {

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

}
