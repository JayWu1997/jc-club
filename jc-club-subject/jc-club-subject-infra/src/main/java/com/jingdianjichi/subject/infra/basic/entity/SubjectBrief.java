package com.jingdianjichi.subject.infra.basic.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 简答题(SubjectBrief)实体类
 *
 * @author jay
 * @since 2024-06-18 18:15:36
 */
@Data
public class SubjectBrief implements Serializable {
    private static final long serialVersionUID = -97089031085247271L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 题目答案
     */
    private String subjectAnswer;
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
     * 删除标识
     */
    private Integer isDeleted;
}

