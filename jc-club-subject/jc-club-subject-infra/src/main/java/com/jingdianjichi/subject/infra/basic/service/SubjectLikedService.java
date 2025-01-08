package com.jingdianjichi.subject.infra.basic.service;

import com.jingdianjichi.subject.infra.basic.entity.SubjectLiked;

import java.util.List;

/**
 * 题目点赞表(SubjectLiked)表服务接口
 *
 * @author jay
 * @since 2025-01-07 14:08:33
 */
public interface SubjectLikedService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLiked queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param subjectLiked 查询条件
     * @return 查询结果集合
     */
    List<SubjectLiked> queryByPage(SubjectLiked subjectLiked
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param subjectLiked 查询条件
     * @return 符合条件的结果数量
     */
    long count(SubjectLiked subjectLiked);

    /**
     * 新增数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    int insert(SubjectLiked subjectLiked);

    /**
     * 批量新增数据
     *
     * @param subjectLikedList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<SubjectLiked> subjectLikedList);

    /**
     * 修改数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    int update(SubjectLiked subjectLiked);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
