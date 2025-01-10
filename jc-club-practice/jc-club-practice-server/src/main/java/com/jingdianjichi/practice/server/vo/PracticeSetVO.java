package com.jingdianjichi.practice.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 套题信息表(PracticeSet)实体类VO
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Data
public class PracticeSetVO implements Serializable {
    private static final long serialVersionUID = -31371053431248924L;
    /**
     * 套卷 id
     */
    private Long setId;

    /**
     * 套题名称
     */
    private String setName;

    /**
     * 套题热度
     */
    private Integer setHeat;

    /**
     * 套卷描述
     */
    private String setDesc;
}

