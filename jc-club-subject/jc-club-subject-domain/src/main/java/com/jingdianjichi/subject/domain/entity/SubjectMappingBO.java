package com.jingdianjichi.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目分类关系表(SubjectMapping) BO
 *
 * @author jay
 * @since 2024-06-18 10:51:37
 */
@Data
public class SubjectMappingBO implements Serializable {
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
     * 题目id列表
     */
    private List<Long> subjectIdList;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 标签id
     */
    private Long labelId;
    /**
     * 分类标签组合id，eg: 2-13
     */
    private String assembleId;
}

