package com.jingdianjichi.practice.server.dao;

import com.jingdianjichi.practice.server.entity.PracticeSetDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 套题内容表(PracticeSetDetail)表数据库访问层
 *
 * @author jay
 * @since 2025-01-08 18:21:21
 */
@Mapper
public interface PracticeSetDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PracticeSetDetail queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param practiceSetDetail 查询条件
     * @return 符合条件的查询结果
     */
    List<PracticeSetDetail> queryByPage(@Param("entity") PracticeSetDetail practiceSetDetail,
                                        @Param("start") int start,
                                        @Param("size") int size);

    /**
     * 统计总行数
     *
     * @param practiceSetDetail 查询条件
     * @return 总行数
     */
    long count(PracticeSetDetail practiceSetDetail);

    /**
     * 新增数据
     *
     * @param practiceSetDetail 实例对象
     * @return 影响行数
     */
    int insert(PracticeSetDetail practiceSetDetail);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PracticeSetDetail> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PracticeSetDetail> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PracticeSetDetail> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PracticeSetDetail> entities);

    /**
     * 修改数据
     *
     * @param practiceSetDetail 实例对象
     * @return 影响行数
     */
    int update(PracticeSetDetail practiceSetDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

