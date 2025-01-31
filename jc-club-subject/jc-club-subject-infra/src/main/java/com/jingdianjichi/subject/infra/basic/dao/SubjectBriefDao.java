package com.jingdianjichi.subject.infra.basic.dao;

import com.jingdianjichi.subject.infra.basic.entity.SubjectBrief;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 简答题(SubjectBrief)表数据库访问层
 *
 * @author jay
 * @since 2024-06-18 18:15:36
 */
@Mapper
public interface SubjectBriefDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 统计总行数
     *
     * @param subjectBrief 查询条件
     * @return 总行数
     */
    long count(SubjectBrief subjectBrief);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 影响行数
     */
    int insert(SubjectBrief subjectBrief);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectBrief> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectBrief> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectBrief> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectBrief> entities);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 影响行数
     */
    int update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据题目 id 查询简答题
     * @param subjectId 题目id
     * @return 简答题信息
     */
    SubjectBrief queryBySubjectId(Long subjectId);
}

