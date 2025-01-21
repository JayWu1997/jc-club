package com.jingdianjichi.circle.infra.mybatis.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 圈子信息(ShareCircle)实体类
 *
 * @author jay
 * @since 2025-01-21 18:13:43
 */
@Data
public class ShareCircle implements Serializable {
    private static final long serialVersionUID = 683480112406802515L;
    /**
     * 圈子ID
     */
    private Long id;
    /**
     * 父级ID,-1为大类
     */
    private Long parentId;
    /**
     * 圈子名称
     */
    private String circleName;
    /**
     * 圈子图片
     */
    private String icon;
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

