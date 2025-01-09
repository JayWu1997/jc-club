package com.jingdianjichi.practice.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 练习详情表(PracticeDetail)实体类
 *
 * @author jay
 * @since 2025-01-08 18:21:19
 */
@Data
public class PracticeDetail implements Serializable {
    private static final long serialVersionUID = -16629914097130617L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 练题id
     */
    private Long practiceId;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 题目类型
     */
    private Integer subjectType;
    /**
     * 回答状态
     */
    private Integer answerStatus;
    /**
     * 回答内容
     */
    private String answerContent;
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

