package com.jingdianjichi.subject.infra.basic.dao;

import com.jingdianjichi.subject.infra.basic.entity.SubjectJudge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 判断题(SubjectJudge)表数据库访问层
 *
 * @author jay
 * @since 2024-06-18 18:13:42
 */
@Mapper
public interface SubjectJudgeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectJudge queryById(Long id);

    /**
     * 统计总行数
     *
     * @param subjectJudge 查询条件
     * @return 总行数
     */
    long count(SubjectJudge subjectJudge);

    /**
     * 新增数据
     *
     * @param subjectJudge 实例对象
     * @return 影响行数
     */
    int insert(SubjectJudge subjectJudge);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectJudge> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectJudge> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectJudge> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectJudge> entities);

    /**
     * 修改数据
     *
     * @param subjectJudge 实例对象
     * @return 影响行数
     */
    int update(SubjectJudge subjectJudge);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据题目ID查询题目判断信息
     *
     * @param subjectId 题目ID，用于唯一标识一个题目
     * @return 返回与题目ID关联的题目判断对象，如果找不到则返回null
     */
    SubjectJudge queryBySubjectId(Long subjectId);
}

