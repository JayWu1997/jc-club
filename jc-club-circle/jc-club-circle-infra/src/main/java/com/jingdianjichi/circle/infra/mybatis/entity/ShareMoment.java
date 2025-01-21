package com.jingdianjichi.circle.infra.mybatis.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 动态信息(ShareMoment)实体类
 *
 * @author jay
 * @since 2025-01-21 18:13:45
 */
@Data
public class ShareMoment implements Serializable {
    private static final long serialVersionUID = -54590070760856775L;
    /**
     * 动态ID
     */
    private Long id;
    /**
     * 圈子ID
     */
    private Long circleId;
    /**
     * 动态内容
     */
    private String content;
    /**
     * 动态图片内容
     */
    private String picUrls;
    /**
     * 回复数
     */
    private Integer replyCount;
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

