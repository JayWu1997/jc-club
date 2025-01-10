package com.jingdianjichi.subject.infra.basic.service;

import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author jay
 * @since 2024-06-18 18:19:01
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    int update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 条件查询
     *
     * @param subjectInfo
     * @param categoryId
     * @param labelId
     * @return
     */
    Integer countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId);

    /**
     * 条件查询
     * @param subjectInfo 查询条件
     * @param categoryId
     * @param labelId
     * @param start
     * @param pageSize
     * @return
     */
    List<SubjectInfo> queryByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId, Integer start, Integer pageSize);

    /**
     * 多表条件查询
     *
     * @param subjectType
     * @param labelIdList
     * @param queryCount
     * @return
     */
    List<SubjectInfo> queryByConditionInMultiTable(Integer subjectType, List<Long> labelIdList, Integer queryCount);
}
