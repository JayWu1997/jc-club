package com.jingdianjichi.auth.infra.base.dao;

import com.jingdianjichi.auth.infra.base.entity.AuthUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(AuthUser)表数据库访问层
 *
 * @author jay
 * @since 2024-12-21 02:04:39
 */
@Mapper
public interface AuthUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param authUser 查询条件
     * @return 符合条件的查询结果
     */
    List<AuthUser> queryAll(AuthUser authUser);

    /**
     * 统计总行数
     *
     * @param authUser 查询条件
     * @return 总行数
     */
    long count(AuthUser authUser);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 影响行数
     */
    int insert(AuthUser authUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AuthUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AuthUser> entities);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 影响行数
     */
    int update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

