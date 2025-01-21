package com.jingdianjichi.circle.infra.mybatis.service;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareCircle;

import java.util.List;

/**
 * 圈子信息(ShareCircle)表服务接口
 *
 * @author jay
 * @since 2025-01-21 18:13:42
 */
public interface ShareCircleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShareCircle queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param shareCircle 查询条件
     * @return 查询结果集合
     */
    List<ShareCircle> queryByPage(ShareCircle shareCircle
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param shareCircle 查询条件
     * @return 符合条件的结果数量
     */
    long count(ShareCircle shareCircle);

    /**
     * 新增数据
     *
     * @param shareCircle 实例对象
     * @return 实例对象
     */
    int insert(ShareCircle shareCircle);

    /**
     * 批量新增数据
     *
     * @param shareCircleList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<ShareCircle> shareCircleList);

    /**
     * 修改数据
     *
     * @param shareCircle 实例对象
     * @return 实例对象
     */
    int update(ShareCircle shareCircle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
