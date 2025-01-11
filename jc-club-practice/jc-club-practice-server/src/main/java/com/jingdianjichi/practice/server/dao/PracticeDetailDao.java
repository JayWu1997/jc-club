package com.jingdianjichi.practice.server.dao;

import com.jingdianjichi.practice.server.entity.PracticeDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 练习详情表(PracticeDetail)表数据库访问层
 *
 * @author jay
 * @since 2025-01-08 18:21:18
 */
@Mapper
public interface PracticeDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PracticeDetail queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param practiceDetail 查询条件
     * @return 符合条件的查询结果
     */
    List<PracticeDetail> queryByPage(@Param("entity") PracticeDetail practiceDetail,
                                     @Param("start") int start,
                                     @Param("size") int size);

    /**
     * 统计总行数
     *
     * @param practiceDetail 查询条件
     * @return 总行数
     */
    long count(PracticeDetail practiceDetail);

    /**
     * 新增数据
     *
     * @param practiceDetail 实例对象
     * @return 影响行数
     */
    int insert(PracticeDetail practiceDetail);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PracticeDetail> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PracticeDetail> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PracticeDetail> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PracticeDetail> entities);

    /**
     * 修改数据
     *
     * @param practiceDetail 实例对象
     * @return 影响行数
     */
    int update(PracticeDetail practiceDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过practiceId删除数据
     *
     * @param practiceId practiceId
     * @return 影响行数
     */
    void deleteByPracticeId(@Param("practiceId")Long practiceId);
}

