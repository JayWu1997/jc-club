package com.jingdianjichi.practice.server.service;

import com.jingdianjichi.practice.server.entity.PracticeInfo;

import java.util.List;

/**
 * 练习表(PracticeInfo)表服务接口
 *
 * @author jay
 * @since 2025-01-08 18:21:19
 */
public interface PracticeInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PracticeInfo queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param practiceInfo 查询条件
     * @return 查询结果集合
     */
    List<PracticeInfo> queryByPage(PracticeInfo practiceInfo
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceInfo 查询条件
     * @return 符合条件的结果数量
     */
    long count(PracticeInfo practiceInfo);

    /**
     * 新增数据
     *
     * @param practiceInfo 实例对象
     * @return 实例对象
     */
    int insert(PracticeInfo practiceInfo);

    /**
     * 批量新增数据
     *
     * @param practiceInfoList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<PracticeInfo> practiceInfoList);

    /**
     * 修改数据
     *
     * @param practiceInfo 实例对象
     * @return 实例对象
     */
    int update(PracticeInfo practiceInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
