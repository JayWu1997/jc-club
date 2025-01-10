package com.jingdianjichi.practice.server.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/10 下午1:47
 */
@Data
public class GetSubjectsVO {

    /**
     * 套题名称
     */
    private String title;

    /**
     * 题目列表
     */
    private List<PracticeSubjectDetailVO> subjectList;

    /**
     * 练习 id
     */
    private Long practiceId;

    /**
     * 已答题时长
     */
    private String timeUse;
}
