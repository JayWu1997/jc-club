package com.jingdianjichi.auth.infra.base.service.impl;

import com.jingdianjichi.auth.infra.base.entity.AuthRole;
import com.jingdianjichi.auth.infra.base.dao.AuthRoleDao;
import com.jingdianjichi.auth.infra.base.service.AuthRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * (AuthRole)表服务实现类
 *
 * @author jay
 * @since 2024-12-21 02:16:31
 */
@Service("authRoleService")
public class AuthRoleServiceImpl implements AuthRoleService {
    @Resource
    private AuthRoleDao authRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthRole queryById(Long id) {
        return this.authRoleDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param authRole 查询条件
     * @return 查询结果集合
     */
    public List<AuthRole> queryAll(AuthRole authRole) {
        return this.authRoleDao.queryAll(authRole);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param authRole 查询条件
     * @return 符合条件的结果数量
     */
    public long count(AuthRole authRole) {
        return this.authRoleDao.count(authRole);
    }

    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(AuthRole authRole) {
        return authRoleDao.insert(authRole);
    }

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AuthRole authRole) {
        return authRoleDao.update(authRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authRoleDao.deleteById(id) > 0;
    }
}
