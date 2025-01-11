package com.jingdianjichi.practice.server.req;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/11 上午11:00
 */
@Data
public class GetSubjectDetailReq {

    private Long practiceId;
    private Long subjectId;
    private Integer subjectType;
}
