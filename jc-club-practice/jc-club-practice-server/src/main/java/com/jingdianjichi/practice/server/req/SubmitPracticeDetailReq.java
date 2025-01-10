package com.jingdianjichi.practice.server.req;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/10 下午4:47
 */
@Data
public class SubmitPracticeDetailReq {

    /**
     * 套题 id
     */
    private Long setId;

    /**
     * 练习 id
     */
    private Long practiceId;

    /**
     * 耗时
     */
    private String timeUse;

    /**
     * 提交时间
     */
    private String submitTime;
}
