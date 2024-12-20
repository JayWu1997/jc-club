package com.jingdianjichi.auth.domain.entity;

import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色表(AuthUserRole)实体类BO
 *
 * @author jay
 * @since 2024-12-21 02:33:37
 */
@Data
public class AuthUserRoleBO implements Serializable {
    private static final long serialVersionUID = -41091356471101312L;
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

