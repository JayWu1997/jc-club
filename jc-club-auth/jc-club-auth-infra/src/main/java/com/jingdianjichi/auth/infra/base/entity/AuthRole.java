package com.jingdianjichi.auth.infra.base.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * (AuthRole)实体类
 *
 * @author jay
 * @since 2024-12-21 03:14:20
 */
@Data
public class AuthRole implements Serializable {
    private static final long serialVersionUID = 699006832931875955L;

    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色唯一标识
     */
    private String roleKey;
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
     * 是否被删除 0未删除 1已删除
     */
    private Integer isDeleted;
}

