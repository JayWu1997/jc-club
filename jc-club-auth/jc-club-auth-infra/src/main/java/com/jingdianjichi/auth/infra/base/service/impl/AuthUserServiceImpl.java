package com.jingdianjichi.auth.infra.base.service.impl;

import com.jingdianjichi.auth.infra.base.entity.AuthUser;
import com.jingdianjichi.auth.infra.base.dao.AuthUserDao;
import com.jingdianjichi.auth.infra.base.service.AuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(AuthUser)表服务实现类
 *
 * @author jay
 * @since 2024-12-21 02:16:32
 */
@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService {
    @Resource
    private AuthUserDao authUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthUser queryById(Long id) {
        return this.authUserDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param authUser 查询条件
     * @return 查询结果集合
     */
    public List<AuthUser> queryAll(AuthUser authUser) {
        return this.authUserDao.queryAll(authUser);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param authUser 查询条件
     * @return 符合条件的结果数量
     */
    public long count(AuthUser authUser) {
        return this.authUserDao.count(authUser);
    }

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(AuthUser authUser) {
        return authUserDao.insert(authUser);
    }

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AuthUser authUser) {
        return authUserDao.update(authUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authUserDao.deleteById(id) > 0;
    }
}
