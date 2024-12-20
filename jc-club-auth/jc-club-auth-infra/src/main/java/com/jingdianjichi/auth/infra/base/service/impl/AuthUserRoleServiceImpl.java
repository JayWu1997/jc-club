package com.jingdianjichi.auth.infra.base.service.impl;

import com.jingdianjichi.auth.infra.base.entity.AuthUserRole;
import com.jingdianjichi.auth.infra.base.dao.AuthUserRoleDao;
import com.jingdianjichi.auth.infra.base.service.AuthUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 用户角色表(AuthUserRole)表服务实现类
 *
 * @author jay
 * @since 2024-12-21 02:16:32
 */
@Service("authUserRoleService")
public class AuthUserRoleServiceImpl implements AuthUserRoleService {
    @Resource
    private AuthUserRoleDao authUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthUserRole queryById(Long id) {
        return this.authUserRoleDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param authUserRole 查询条件
     * @return 查询结果集合
     */
    public List<AuthUserRole> queryAll(AuthUserRole authUserRole) {
        return this.authUserRoleDao.queryAll(authUserRole);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param authUserRole 查询条件
     * @return 符合条件的结果数量
     */
    public long count(AuthUserRole authUserRole) {
        return this.authUserRoleDao.count(authUserRole);
    }

    /**
     * 新增数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(AuthUserRole authUserRole) {
        return authUserRoleDao.insert(authUserRole);
    }

    /**
     * 修改数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AuthUserRole authUserRole) {
        return authUserRoleDao.update(authUserRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authUserRoleDao.deleteById(id) > 0;
    }
}
