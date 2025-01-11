package com.jingdianjichi.practice.server.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/11 上午10:59
 */
@Data
public class SubjectDetailVO {
    private List<Integer> correctAnswer;
    private List<Integer> respondAnswer;
    private List<SubjectOptionVO> optionList;
    private List<String> labelNames;
    private String subjectName;
}
