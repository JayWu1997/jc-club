package com.jingdianjichi.practice.server.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 套题信息表(PracticeSet)实体类
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Data
public class PracticeSet implements Serializable {
    private static final long serialVersionUID = -55962344152689613L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 套题名称
     */
    private String setName;
    /**
     * 套题类型 1实时生成 2预设套题
     */
    private Integer setType;
    /**
     * 热度
     */
    private Integer setHeat;
    /**
     * 套题描述
     */
    private String setDesc;
    /**
     * 大类id
     */
    private Long primaryCategoryId;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否被删除 0为删除 1已删除
     */
    private Integer isDeleted;
}

