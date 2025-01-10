package com.jingdianjichi.practice.server.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/10 下午2:21
 */
@Data
public class PracticeSubjectVO {
    private String subjectName;
    private int subjectType;
    private List<SubjectOptionVO> optionList;
}
