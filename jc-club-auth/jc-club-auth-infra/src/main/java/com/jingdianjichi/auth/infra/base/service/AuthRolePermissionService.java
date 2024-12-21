package com.jingdianjichi.auth.infra.base.service;

import com.jingdianjichi.auth.infra.base.entity.AuthRolePermission;

import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)表服务接口
 *
 * @author jay
 * @since 2024-12-21 02:16:32
 */
public interface AuthRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param authRolePermission 查询条件
     * @return 查询结果集合
     */
    List<AuthRolePermission> queryAll(AuthRolePermission authRolePermission);

    /**
     * 查询符合条件的结果数量
     *
     * @param authRolePermission 查询条件
     * @return 符合条件的结果数量
     */
    long count(AuthRolePermission authRolePermission);

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    int insert(AuthRolePermission authRolePermission);


    /**
     * 批量新增数据
     *
     * @param authRolePermissionList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<AuthRolePermission> authRolePermissionList);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    int update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
