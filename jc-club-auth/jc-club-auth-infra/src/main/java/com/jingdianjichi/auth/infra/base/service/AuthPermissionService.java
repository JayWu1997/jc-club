package com.jingdianjichi.auth.infra.base.service;

import com.jingdianjichi.auth.infra.base.entity.AuthPermission;

import java.util.List;

/**
 * (AuthPermission)表服务接口
 *
 * @author jay
 * @since 2024-12-21 02:16:31
 */
public interface AuthPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermission queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param authPermission 查询条件
     * @return 查询结果集合
     */
    List<AuthPermission> queryAll(AuthPermission authPermission);

    /**
     * 查询符合条件的结果数量
     *
     * @param authPermission 查询条件
     * @return 符合条件的结果数量
     */
    long count(AuthPermission authPermission);

    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    int insert(AuthPermission authPermission);

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    int update(AuthPermission authPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据主键批量查询数据
     *
     * @param permissionIdList 主键集合
     * @return 实例对象集合
     */
    List<AuthPermission> queryBatchByIds(List<Long> permissionIdList);

    /**
     * 根据用户名查询权限列表
     * @param userName 用户名
     * @return 权限列表
     */
    List<AuthPermission> queryBatchByUserName(String userName);
}
