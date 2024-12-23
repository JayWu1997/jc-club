package com.jingdianjichi.auth.domain.service;

import com.jingdianjichi.auth.domain.entity.AuthRolePermissionBO;

/**
 * 角色权限关联领域服务
 * @author jay
 * @since 2024/12/23 下午11:38
 */
public interface AuthRolePermissionDomainService {

    /**
     * 新增角色权限关联
     * @param authRolePermissionBO 角色权限关联信息
     */
    void add(AuthRolePermissionBO authRolePermissionBO);

    /**
     * 删除角色权限关联
     * @param id 主键
     */
    void delete(Long id);
}
