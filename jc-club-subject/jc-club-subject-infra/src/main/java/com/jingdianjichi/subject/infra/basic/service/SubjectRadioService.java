package com.jingdianjichi.subject.infra.basic.service;

import com.jingdianjichi.subject.infra.basic.entity.SubjectRadio;

import java.util.List;

/**
 * 单选题信息表(SubjectRadio)表服务接口
 *
 * @author jay
 * @since 2024-06-18 18:19:21
 */
public interface SubjectRadioService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRadio queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 插入数据条数
     */
    int insert(SubjectRadio subjectRadio);

    /**
     * 批量新增数据
     *
     * @param subjectRadioList 实例对象列表
     * @return 插入数据条数
     */
    int insertBatch(List<SubjectRadio> subjectRadioList);

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    int update(SubjectRadio subjectRadio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据题目信息查询SubjectRadio列表
     *
     * @param subjectId 题目ID，用于查询与题目相关的SubjectRadio信息
     * @return 返回一个SubjectRadio对象列表，这些对象与给定的题目ID相关
     */
    List<SubjectRadio> queryBySubjectId(Long subjectId);

}
