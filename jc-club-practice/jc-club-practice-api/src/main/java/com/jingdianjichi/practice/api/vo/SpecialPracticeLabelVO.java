package com.jingdianjichi.practice.api.vo;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/8 下午7:01
 */
@Data
public class SpecialPracticeLabelVO {

    private String labelName;

    /**
     * 分类 id-标签 id
     */
    private String assembleId;
}
