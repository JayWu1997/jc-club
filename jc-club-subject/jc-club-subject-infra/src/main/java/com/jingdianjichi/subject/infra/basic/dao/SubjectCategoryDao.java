package com.jingdianjichi.subject.infra.basic.dao;
import java.util.Date;

import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 题目分类(SubjectCategory)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-02 00:54:33
 */
@Mapper
public interface SubjectCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectCategory queryById(Long id);

    /**
     * 统计总行数
     *
     * @param subjectCategory 查询条件
     * @return 总行数
     */
    long count(SubjectCategory subjectCategory);

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 影响行数
     */
    int insert(SubjectCategory subjectCategory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectCategory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectCategory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectCategory> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectCategory> entities);

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 影响行数
     */
    int update(SubjectCategory subjectCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询所有主题类别。
     *
     * @param subjectCategory 一个主题类别对象，可以为空。如果提供，将按照该对象的属性进行查询过滤。
     * @return 返回一个主题类别列表，可能为空。列表中包含所有匹配查询条件的主题类别对象。
     */
    List<SubjectCategory> queryByAll(SubjectCategory subjectCategory);

}

