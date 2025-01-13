package com.jingdianjichi.practice.server.dao;

import com.jingdianjichi.practice.server.entity.PracticeSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 套题信息表(PracticeSet)表数据库访问层
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Mapper
public interface PracticeSetDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PracticeSet queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param practiceSet 查询条件
     * @return 符合条件的查询结果
     */
    List<PracticeSet> queryByPage(@Param("entity") PracticeSet practiceSet,
                                  @Param("start") int start,
                                  @Param("size") int size);

    /**
     * 统计总行数
     *
     * @param practiceSet 查询条件
     * @return 总行数
     */
    long count(PracticeSet practiceSet);

    /**
     * 新增数据
     *
     * @param practiceSet 实例对象
     * @return 影响行数
     */
    int insert(PracticeSet practiceSet);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PracticeSet> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PracticeSet> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PracticeSet> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PracticeSet> entities);

    /**
     * 修改数据
     *
     * @param practiceSet 实例对象
     * @return 影响行数
     */
    int update(PracticeSet practiceSet);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 增加热度
     *
     * @param id
     * @return
     */
    long upHeat(@Param("id") Long id);

    List<PracticeSet> queryPreSetContent(@Param("start") int start,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("orderType") String orderType,
                                         @Param("setName") String setName);
}

