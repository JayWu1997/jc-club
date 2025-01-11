package com.jingdianjichi.auth.infra.base.service;

import com.jingdianjichi.auth.infra.base.entity.AuthUser;

import java.util.List;

/**
 * 用户信息表(AuthUser)表服务接口
 *
 * @author jay
 * @since 2024-12-21 02:16:32
 */
public interface AuthUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param authUser 查询条件
     * @return 查询结果集合
     */
    List<AuthUser> queryAll(AuthUser authUser);

    /**
     * 查询符合条件的结果数量
     *
     * @param authUser 查询条件
     * @return 符合条件的结果数量
     */
    long count(AuthUser authUser);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    int insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    int update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据用户名批量查询用户信息
     * @param userNameList
     * @return
     */
    List<AuthUser> batchQueryByUserNames(List<String> userNameList);
}
