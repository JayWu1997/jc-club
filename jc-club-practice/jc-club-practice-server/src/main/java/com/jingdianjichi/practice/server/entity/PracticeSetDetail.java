package com.jingdianjichi.practice.server.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 套题内容表(PracticeSetDetail)实体类
 *
 * @author jay
 * @since 2025-01-08 18:21:22
 */
@Data
public class PracticeSetDetail implements Serializable {
    private static final long serialVersionUID = -94147243484041127L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 套题id
     */
    private Long setId;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 题目类型
     */
    private Integer subjectType;
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

