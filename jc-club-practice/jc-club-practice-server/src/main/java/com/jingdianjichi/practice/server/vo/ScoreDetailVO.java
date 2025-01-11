package com.jingdianjichi.practice.server.vo;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/11 上午10:39
 */
@Data
public class ScoreDetailVO {

    /**
     * 题目 id
     */
    private Long subjectId;

    /**
     * 是否正确
     */
    private Integer isCorrect;

    /**
     * 题目类型
     */
    private Integer subjectType;
}
