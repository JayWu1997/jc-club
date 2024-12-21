package com.jingdianjichi.auth.domain.service;

import com.jingdianjichi.auth.domain.entity.AuthPermissionBO;

/**
 * 权限领域服务
 * @author jay
 * @since 2024/12/21 上午4:13
 */
public interface AuthPermissionDomainService {

    /**
     * 添加权限
     *
     * @param authPermissionBO 权限信息
     */
    void add(AuthPermissionBO authPermissionBO);

    /**
     * 更新权限信息
     *
     * @param authPermissionBO 权限信息
     */
    void update(AuthPermissionBO authPermissionBO);

    /**
     * 删除权限信息
     *
     * @param id 权限id
     */
    void delete(Long id);

    /**
     * 启用或禁用权限
     *
     * @param authPermissionBO 权限信息
     */
    void enableOrDisable(AuthPermissionBO authPermissionBO);

    /**
     * 显示或隐藏权限
     *
     * @param authPermissionBO 权限信息
     */
    void presentOrAbsent(AuthPermissionBO authPermissionBO);

    /**
     * 角色和权限关联
     *
     * @param authPermissionBO 权限信息
     */
    void mappingRoleAndPermission(AuthPermissionBO authPermissionBO);
}
