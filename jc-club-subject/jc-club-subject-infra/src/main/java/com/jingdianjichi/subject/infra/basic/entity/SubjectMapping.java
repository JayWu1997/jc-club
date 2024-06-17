package com.jingdianjichi.subject.infra.basic.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目分类关系表(SubjectMapping)实体类
 *
 * @author jay
 * @since 2024-06-18 10:51:37
 */
@Data
public class SubjectMapping implements Serializable {
    private static final long serialVersionUID = 775501027417965266L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 标签id
     */
    private Long labelId;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 删除标识
     */
    private Integer isDeleted;
}

