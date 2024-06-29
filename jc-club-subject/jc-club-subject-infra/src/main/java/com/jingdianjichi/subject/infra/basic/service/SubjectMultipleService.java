package com.jingdianjichi.subject.infra.basic.service;

import com.jingdianjichi.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务接口
 *
 * @author jay
 * @since 2024-06-18 18:19:15
 */
public interface SubjectMultipleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 成功个数
     */
    int insert(SubjectMultiple subjectMultiple);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 成功个数
     */
    int insertBatch(List<SubjectMultiple> subjectMultiple);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    int update(SubjectMultiple subjectMultiple);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
