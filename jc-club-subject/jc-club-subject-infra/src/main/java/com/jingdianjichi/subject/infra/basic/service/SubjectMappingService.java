package com.jingdianjichi.subject.infra.basic.service;

import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;

import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author jay
 * @since 2024-06-18 10:51:37
 */
public interface SubjectMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMapping queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 成功个数
     */
    Integer insert(SubjectMapping subjectMapping);

    /**
     * 批量新增数据
     *
     * @param subjectMappingList 实例对象列表
     * @return 成功个数
     */
    Integer insertBatch(List<SubjectMapping> subjectMappingList);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    Integer update(SubjectMapping subjectMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据分类 ID 查询标签列表
     *
     * @param subjectMapping 包含分类 ID
     * @return 标签列表
     */
    List<Long> queryDistinctLabelIdsByCondition(SubjectMapping subjectMapping);

    /**
     * 根据分类 ID 查询题目数量
     *
     * @param categoryId 分类 ID
     * @return 符合条件的题目数量
     */
    Integer countByCategoryIdDistinctSubjectId(Long categoryId);
}
