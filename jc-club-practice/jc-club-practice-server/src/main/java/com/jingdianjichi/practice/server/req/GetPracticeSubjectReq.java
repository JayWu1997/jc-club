package com.jingdianjichi.practice.server.req;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/10 下午2:17
 */
@Data
public class GetPracticeSubjectReq {

    /**
     * 题目 id
     */
    private Long subjectId;

    /**
     * 题目类型
     */
    private Integer subjectType;
}
