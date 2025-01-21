package com.jingdianjichi.circle.infra.mybatis.service;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareMessage;

import java.util.List;

/**
 * 消息表(ShareMessage)表服务接口
 *
 * @author jay
 * @since 2025-01-21 18:13:44
 */
public interface ShareMessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShareMessage queryById(Integer id);

    /**
     * 通过条件查询所有数据
     *
     * @param shareMessage 查询条件
     * @return 查询结果集合
     */
    List<ShareMessage> queryByPage(ShareMessage shareMessage
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param shareMessage 查询条件
     * @return 符合条件的结果数量
     */
    long count(ShareMessage shareMessage);

    /**
     * 新增数据
     *
     * @param shareMessage 实例对象
     * @return 实例对象
     */
    int insert(ShareMessage shareMessage);

    /**
     * 批量新增数据
     *
     * @param shareMessageList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<ShareMessage> shareMessageList);

    /**
     * 修改数据
     *
     * @param shareMessage 实例对象
     * @return 实例对象
     */
    int update(ShareMessage shareMessage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
