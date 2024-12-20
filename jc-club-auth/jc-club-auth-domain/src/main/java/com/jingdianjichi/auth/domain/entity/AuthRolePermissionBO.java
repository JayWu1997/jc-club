package com.jingdianjichi.auth.domain.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关联表(AuthRolePermission)实体类BO
 *
 * @author jay
 * @since 2024-12-21 02:33:36
 */
@Data
public class AuthRolePermissionBO implements Serializable {
    private static final long serialVersionUID = 813489396083710680L;

    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;
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

