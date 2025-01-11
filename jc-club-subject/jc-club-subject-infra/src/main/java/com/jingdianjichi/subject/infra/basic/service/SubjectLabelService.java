package com.jingdianjichi.subject.infra.basic.service;

import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2024-06-17 12:34:32
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据 id 批量查询
     * @param labelIdList id 列表
     * @return 标签列表
     */
    List<SubjectLabel> queryBatchByIds(List<Long> labelIdList);

    /**
     * 根据分类 id 查询与其标签列表
     *
     * @param categoryIdList 分类 id
     * @return 标签列表
     */
    List<SubjectLabel> queryDistinctLabelListByCategoryIds(List<Long> categoryIdList);

    /**
     * 根据条件查询数量
     * @param subjectLabel
     * @return
     */
    long countByCondition(SubjectLabel subjectLabel);

    /**
     * 根据题目 id 批量查询标签列表
     *
     * @param subjectIdList @return
     */
    List<SubjectLabel> queryBatchBySubjectIds(List<Long> subjectIdList);
}
