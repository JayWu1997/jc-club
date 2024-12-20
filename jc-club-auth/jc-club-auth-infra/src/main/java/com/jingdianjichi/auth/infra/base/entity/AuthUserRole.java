package com.jingdianjichi.auth.infra.base.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色表(AuthUserRole)实体类
 *
 * @author jay
 * @since 2024-12-21 03:14:21
 */
@Data
public class AuthUserRole implements Serializable {
    private static final long serialVersionUID = 899614362532587398L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;
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

    private Integer isDeleted;
}

