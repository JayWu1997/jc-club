package com.jingdianjichi.auth.infra.base.service;

import com.jingdianjichi.auth.infra.base.entity.AuthRole;

import java.util.List;

/**
 * (AuthRole)表服务接口
 *
 * @author jay
 * @since 2024-12-21 02:16:31
 */
public interface AuthRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRole queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param authRole 查询条件
     * @return 查询结果集合
     */
    List<AuthRole> queryAll(AuthRole authRole);

    /**
     * 查询符合条件的结果数量
     *
     * @param authRole 查询条件
     * @return 符合条件的结果数量
     */
    long count(AuthRole authRole);

    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    int insert(AuthRole authRole);

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    int update(AuthRole authRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
