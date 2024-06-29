package com.jingdianjichi.subject.infra.basic.service;

import com.jingdianjichi.subject.infra.basic.entity.SubjectBrief;

import java.util.List;

/**
 * 简答题(SubjectBrief)表服务接口
 *
 * @author jay
 * @since 2024-06-18 18:18:34
 */
public interface SubjectBriefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    int insert(SubjectBrief subjectBrief);

    /**
     * 批量新增数据
     *
     * @param subjectBriefList 实例对象 list
     * @return 影响行数
     */
    int insertBatch(List<SubjectBrief> subjectBriefList);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    int update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
