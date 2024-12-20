package com.jingdianjichi.auth.application.dto;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关联表(AuthRolePermission)实体类DTO
 *
 * @author jay
 * @since 2024-12-21 02:33:36
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = 812522061184127006L;

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

