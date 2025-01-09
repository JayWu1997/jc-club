package com.jingdianjichi.practice.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/8 下午7:01
 */
@Data
public class SpecialPracticeVO {

    private String primaryCategoryName;

    private Long primaryCategoryId;

    private List<SpecialPracticeCategoryVO> categoryList;
}
