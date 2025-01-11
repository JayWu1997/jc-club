package com.jingdianjichi.practice.server.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jay
 * @since 2025/1/11 下午12:49
 */
@Data
public class ReportSkillVO {

    /**
     * 分数
     */
    private BigDecimal star;

    /**
     * 名称
     */
    private String name;
}
