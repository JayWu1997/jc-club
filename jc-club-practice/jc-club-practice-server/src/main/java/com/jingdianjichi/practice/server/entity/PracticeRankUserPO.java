package com.jingdianjichi.practice.server.entity;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/11 下午4:46
 */
@Data
public class PracticeRankUserPO {

    /**
     * 联系次数
     */
    private Long count;

    /**
     * 用户
     */
    private String createdBy;
}
