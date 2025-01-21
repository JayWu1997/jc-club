package com.jingdianjichi.circle.infra.mybatis.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息表(ShareMessage)实体类
 *
 * @author jay
 * @since 2025-01-21 18:13:44
 */
@Data
public class ShareMessage implements Serializable {
    private static final long serialVersionUID = -71478996035166214L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 来自人
     */
    private String fromId;
    /**
     * 送达人
     */
    private String toId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否被阅读 1是 2否
     */
    private Integer isRead;
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

