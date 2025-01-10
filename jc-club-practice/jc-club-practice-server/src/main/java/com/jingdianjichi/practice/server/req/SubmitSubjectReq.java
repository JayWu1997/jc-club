package com.jingdianjichi.practice.server.req;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/10 下午6:49
 */
@Data
public class SubmitSubjectReq {
    /*
     * 练习id
     */
    private Long practiceId;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 用户答案
     */
    private List<Integer> answerContents;
    /**
     * 题目类型
     */
    private Integer subjectType;
    /**
     * 用时
     */
    private String timeUse;
}
