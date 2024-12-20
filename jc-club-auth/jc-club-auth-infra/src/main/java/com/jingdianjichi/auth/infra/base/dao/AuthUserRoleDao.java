package com.jingdianjichi.auth.infra.base.dao;

import com.jingdianjichi.auth.infra.base.entity.AuthUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色表(AuthUserRole)表数据库访问层
 *
 * @author jay
 * @since 2024-12-21 02:04:40
 */
@Mapper
public interface AuthUserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUserRole queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param authUserRole 查询条件
     * @return 符合条件的查询结果
     */
    List<AuthUserRole> queryAll(AuthUserRole authUserRole);

    /**
     * 统计总行数
     *
     * @param authUserRole 查询条件
     * @return 总行数
     */
    long count(AuthUserRole authUserRole);

    /**
     * 新增数据
     *
     * @param authUserRole 实例对象
     * @return 影响行数
     */
    int insert(AuthUserRole authUserRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AuthUserRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthUserRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AuthUserRole> entities);

    /**
     * 修改数据
     *
     * @param authUserRole 实例对象
     * @return 影响行数
     */
    int update(AuthUserRole authUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

