package com.jingdianjichi.auth.infra.base.dao;

import com.jingdianjichi.auth.infra.base.entity.AuthRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关联表(AuthRolePermission)表数据库访问层
 *
 * @author jay
 * @since 2024-12-21 02:04:39
 */
@Mapper
public interface AuthRolePermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param authRolePermission 查询条件
     * @return 符合条件的查询结果
     */
    List<AuthRolePermission> queryAll(AuthRolePermission authRolePermission);

    /**
     * 统计总行数
     *
     * @param authRolePermission 查询条件
     * @return 总行数
     */
    long count(AuthRolePermission authRolePermission);

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 影响行数
     */
    int insert(AuthRolePermission authRolePermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthRolePermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AuthRolePermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthRolePermission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AuthRolePermission> entities);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 影响行数
     */
    int update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

