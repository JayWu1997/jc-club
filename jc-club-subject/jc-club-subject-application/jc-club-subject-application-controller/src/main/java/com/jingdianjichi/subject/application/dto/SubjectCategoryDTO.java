package com.jingdianjichi.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-04-02 00:54:33
 */
@Data
public class SubjectCategoryDTO implements Serializable {
    private static final long serialVersionUID = -23529837684120359L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 题目数量
     */
    private Integer count;

    /**
     * 标签列表
     */
    private List<SubjectLabelDTO> labelDTOList;
}

