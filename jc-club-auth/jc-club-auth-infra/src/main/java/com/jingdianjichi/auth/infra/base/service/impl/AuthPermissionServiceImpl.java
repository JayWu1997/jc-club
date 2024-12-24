package com.jingdianjichi.auth.infra.base.service.impl;

import com.jingdianjichi.auth.infra.base.dao.AuthPermissionDao;
import com.jingdianjichi.auth.infra.base.entity.AuthPermission;
import com.jingdianjichi.auth.infra.base.service.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AuthPermission)表服务实现类
 *
 * @author jay
 * @since 2024-12-21 02:16:31
 */
@Service("authPermissionService")
public class AuthPermissionServiceImpl implements AuthPermissionService {
    @Resource
    private AuthPermissionDao authPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthPermission queryById(Long id) {
        return this.authPermissionDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param authPermission 查询条件
     * @return 查询结果集合
     */
    public List<AuthPermission> queryAll(AuthPermission authPermission) {
        return this.authPermissionDao.queryAll(authPermission);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param authPermission 查询条件
     * @return 符合条件的结果数量
     */
    public long count(AuthPermission authPermission) {
        return this.authPermissionDao.count(authPermission);
    }

    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(AuthPermission authPermission) {
        return authPermissionDao.insert(authPermission);
    }

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AuthPermission authPermission) {
        return authPermissionDao.update(authPermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authPermissionDao.deleteById(id) > 0;
    }

    /**
     * 根据主键批量查询数据
     *
     * @param permissionIdList 主键集合
     * @return 实例对象集合
     */
    @Override
    public List<AuthPermission> queryBatchByIds(List<Long> permissionIdList) {
        return this.authPermissionDao.queryBatchByIds(permissionIdList);
    }

    /**
     * 根据用户名查询权限列表
     *
     * @param userName 用户名
     * @return 权限列表
     */
    @Override
    public List<AuthPermission> queryByUserName(String userName) {
        return authPermissionDao.queryByUserName(userName);
    }
}
