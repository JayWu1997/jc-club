package com.jingdianjichi.auth.infra.base.service;

import com.jingdianjichi.auth.infra.base.entity.AuthUserRole;

import java.util.List;

/**
 * 用户角色表(AuthUserRole)表服务接口
 *
 * @author jay
 * @since 2024-12-21 02:16:32
 */
public interface AuthUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUserRole queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param authUserRole 查询条件
     * @return 查询结果集合
     */
    List<AuthUserRole> queryAll(AuthUserRole authUserRole);

    /**
     * 查询符合条件的结果数量
     *
     * @param authUserRole 查询条件
     * @return 符合条件的结果数量
     */
    long count(AuthUserRole authUserRole);

    /**
     * 新增数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    int insert(AuthUserRole authUserRole);

    /**
     * 修改数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    int update(AuthUserRole authUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
