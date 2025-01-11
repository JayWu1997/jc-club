package com.jingdianjichi.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author jay
 * @since 2024-06-17 12:27:50
 */
@Data
public class SubjectLabelBO implements Serializable {
    private static final long serialVersionUID = -21265406446171357L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 分类ID
     */
    private Long categoryId;
    /**
     * 题目 id
     */
    private List<Long> subjectIdList;
}

