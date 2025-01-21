package com.jingdianjichi.circle.infra.mybatis.dao;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消息表(ShareMessage)表数据库访问层
 *
 * @author jay
 * @since 2025-01-21 18:13:44
 */
@Mapper
public interface ShareMessageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShareMessage queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param shareMessage 查询条件
     * @return 符合条件的查询结果
     */
    List<ShareMessage> queryByPage(@Param("entity") ShareMessage shareMessage,
                                   @Param("start") int start,
                                   @Param("size") int size);

    /**
     * 统计总行数
     *
     * @param shareMessage 查询条件
     * @return 总行数
     */
    long count(ShareMessage shareMessage);

    /**
     * 新增数据
     *
     * @param shareMessage 实例对象
     * @return 影响行数
     */
    int insert(ShareMessage shareMessage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShareMessage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ShareMessage> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShareMessage> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ShareMessage> entities);

    /**
     * 修改数据
     *
     * @param shareMessage 实例对象
     * @return 影响行数
     */
    int update(ShareMessage shareMessage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

