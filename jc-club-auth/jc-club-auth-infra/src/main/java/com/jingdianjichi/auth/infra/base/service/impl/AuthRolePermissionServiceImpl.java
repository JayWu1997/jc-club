package com.jingdianjichi.auth.infra.base.service.impl;

import com.jingdianjichi.auth.infra.base.dao.AuthRolePermissionDao;
import com.jingdianjichi.auth.infra.base.entity.AuthRolePermission;
import com.jingdianjichi.auth.infra.base.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)表服务实现类
 *
 * @author jay
 * @since 2024-12-21 02:16:32
 */
@Service("authRolePermissionService")
public class AuthRolePermissionServiceImpl implements AuthRolePermissionService {
    @Resource
    private AuthRolePermissionDao authRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthRolePermission queryById(Long id) {
        return this.authRolePermissionDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param authRolePermission 查询条件
     * @return 查询结果集合
     */
    public List<AuthRolePermission> queryAll(AuthRolePermission authRolePermission) {
        return this.authRolePermissionDao.queryAll(authRolePermission);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param authRolePermission 查询条件
     * @return 符合条件的结果数量
     */
    public long count(AuthRolePermission authRolePermission) {
        return this.authRolePermissionDao.count(authRolePermission);
    }

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(AuthRolePermission authRolePermission) {
        return authRolePermissionDao.insert(authRolePermission);
    }

    /**
     * 批量新增数据
     *
     * @param authRolePermissionList 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<AuthRolePermission> authRolePermissionList) {
        return authRolePermissionDao.insertBatch(authRolePermissionList);
    }

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AuthRolePermission authRolePermission) {
        return authRolePermissionDao.update(authRolePermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authRolePermissionDao.deleteById(id) > 0;
    }
}
