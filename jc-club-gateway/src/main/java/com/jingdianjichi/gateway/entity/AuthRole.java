package com.jingdianjichi.gateway.entity;

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
}

