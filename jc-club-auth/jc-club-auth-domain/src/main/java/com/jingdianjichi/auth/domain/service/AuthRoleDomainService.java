package com.jingdianjichi.auth.domain.service;

import com.jingdianjichi.auth.domain.entity.AuthRoleBO;

/**
 * 角色业务
 * @author jay
 * @since 2024/12/21 下午7:44
 */
public interface AuthRoleDomainService {

    /**
     * 添加角色
     * @param authRoleBO 角色信息
     * @return 操作成功标志
     */
    Boolean add(AuthRoleBO authRoleBO);

    /**
     * 更新角色信息
     * @param authRoleBO 角色信息
     * @return 操作成功标志
     */
    Boolean update(AuthRoleBO authRoleBO);

    /**
     * 删除角色
     * @param id 角色id
     * @return 操作成功标志
     */
    Boolean delete(Long id);
}
