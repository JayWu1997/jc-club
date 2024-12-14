package com.jingdianjichi.subject.infra.basic.dao;

import com.jingdianjichi.subject.infra.basic.entity.SubjectRadio;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 单选题信息表(SubjectRadio)表数据库访问层
 *
 * @author jay
 * @since 2024-06-18 18:14:23
 */
@Mapper
public interface SubjectRadioDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRadio queryById(Long id);

    /**
     * 统计总行数
     *
     * @param subjectRadio 查询条件
     * @return 总行数
     */
    long count(SubjectRadio subjectRadio);

    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 影响行数
     */
    int insert(SubjectRadio subjectRadio);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectRadio> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectRadio> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectRadio> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectRadio> entities);

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 影响行数
     */
    int update(SubjectRadio subjectRadio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据题目ID查询单选题选项
     * 该方法用于从数据库或其他数据源中获取与特定题目ID关联的所有单选题选项
     *
     * @param subjectId 题目ID，用于查询的唯一标识符
     * @return 返回一个单选题选项的列表，这些对象与指定的题目ID相关联
     * 如果没有找到相关的单选题选项返回一个空列表
     */
    List<SubjectRadio> queryBySubjectId(Long subjectId);

}

