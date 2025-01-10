package com.jingdianjichi.practice.server.vo;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/9 下午3:58
 */
@Data
public class PracticeSubjectDetailVO {
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 题目类型
     */
    private Integer subjectType;
}
