package com.jingdianjichi.practice.server.service;

import com.jingdianjichi.practice.server.entity.PracticeSetDetail;

import java.util.List;

/**
 * 套题内容表(PracticeSetDetail)表服务接口
 *
 * @author jay
 * @since 2025-01-08 18:21:21
 */
public interface PracticeSetDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PracticeSetDetail queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param practiceSetDetail 查询条件
     * @return 查询结果集合
     */
    List<PracticeSetDetail> queryByPage(PracticeSetDetail practiceSetDetail
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceSetDetail 查询条件
     * @return 符合条件的结果数量
     */
    long count(PracticeSetDetail practiceSetDetail);

    /**
     * 新增数据
     *
     * @param practiceSetDetail 实例对象
     * @return 实例对象
     */
    int insert(PracticeSetDetail practiceSetDetail);

    /**
     * 批量新增数据
     *
     * @param practiceSetDetailList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<PracticeSetDetail> practiceSetDetailList);

    /**
     * 修改数据
     *
     * @param practiceSetDetail 实例对象
     * @return 实例对象
     */
    int update(PracticeSetDetail practiceSetDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
