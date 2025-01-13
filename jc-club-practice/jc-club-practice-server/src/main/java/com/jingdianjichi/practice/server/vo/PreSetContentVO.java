package com.jingdianjichi.practice.server.vo;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/11 下午5:24
 */
@Data
public class PreSetContentVO {

    /**
     * id
     */
    private Long id;

    /**
     * 套题名称
     */
    private String setName;

    /**
     * 套题热度
     */
    private Integer setHeat;

    /**
     * 套题描述
     */
    private String setDesc;
}
