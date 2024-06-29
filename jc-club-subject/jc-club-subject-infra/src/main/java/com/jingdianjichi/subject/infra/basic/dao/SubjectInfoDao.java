package com.jingdianjichi.subject.infra.basic.dao;

import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 题目信息表(SubjectInfo)表数据库访问层
 *
 * @author jay
 * @since 2024-06-18 18:13:19
 */
@Mapper
public interface SubjectInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);

    /**
     * 统计总行数
     *
     * @param subjectInfo 查询条件
     * @return 总行数
     */
    long count(SubjectInfo subjectInfo);

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 影响行数
     */
    Integer insert(SubjectInfo subjectInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectInfo> entities);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 影响行数
     */
    int update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

