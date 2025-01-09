package com.jingdianjichi.practice.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/8 下午7:01
 */
@Data
public class SpecialPracticeCategoryVO {

    private String categoryName;

    private Long categoryId;

    private List<SpecialPracticeLabelVO> labelList;
    
}
