package com.jingdianjichi.circle.infra.mybatis.service;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareMoment;

import java.util.List;

/**
 * 动态信息(ShareMoment)表服务接口
 *
 * @author jay
 * @since 2025-01-21 18:13:45
 */
public interface ShareMomentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShareMoment queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param shareMoment 查询条件
     * @return 查询结果集合
     */
    List<ShareMoment> queryByPage(ShareMoment shareMoment
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param shareMoment 查询条件
     * @return 符合条件的结果数量
     */
    long count(ShareMoment shareMoment);

    /**
     * 新增数据
     *
     * @param shareMoment 实例对象
     * @return 实例对象
     */
    int insert(ShareMoment shareMoment);

    /**
     * 批量新增数据
     *
     * @param shareMomentList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<ShareMoment> shareMomentList);

    /**
     * 修改数据
     *
     * @param shareMoment 实例对象
     * @return 实例对象
     */
    int update(ShareMoment shareMoment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
