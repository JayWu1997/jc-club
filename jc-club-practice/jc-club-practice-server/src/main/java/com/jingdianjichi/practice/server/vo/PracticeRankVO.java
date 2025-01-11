package com.jingdianjichi.practice.server.vo;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/11 下午4:12
 */
@Data
public class PracticeRankVO {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 名字
     */
    private String name;

    /**
     * 数量
     */
    private Integer count;
}
