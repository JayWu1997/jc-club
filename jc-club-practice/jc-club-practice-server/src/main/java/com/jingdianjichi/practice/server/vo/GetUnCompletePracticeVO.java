package com.jingdianjichi.practice.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jay
 * @since 2025/1/13 下午6:13
 */
@Data
public class GetUnCompletePracticeVO implements Serializable {

    /**
     * 套题id
     */
    private Long setId;

    /**
     * 练习id
     */
    private Long practiceId;

    /**
     * 练习时间
     */
    private String practiceTime;

    /**
     * 套题名称
     */
    private String title;

}

