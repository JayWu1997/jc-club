package com.jingdianjichi.circle.infra.mybatis.service;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareCommentReply;

import java.util.List;

/**
 * 评论及回复信息(ShareCommentReply)表服务接口
 *
 * @author jay
 * @since 2025-01-21 18:13:43
 */
public interface ShareCommentReplyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShareCommentReply queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param shareCommentReply 查询条件
     * @return 查询结果集合
     */
    List<ShareCommentReply> queryByPage(ShareCommentReply shareCommentReply
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param shareCommentReply 查询条件
     * @return 符合条件的结果数量
     */
    long count(ShareCommentReply shareCommentReply);

    /**
     * 新增数据
     *
     * @param shareCommentReply 实例对象
     * @return 实例对象
     */
    int insert(ShareCommentReply shareCommentReply);

    /**
     * 批量新增数据
     *
     * @param shareCommentReplyList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<ShareCommentReply> shareCommentReplyList);

    /**
     * 修改数据
     *
     * @param shareCommentReply 实例对象
     * @return 实例对象
     */
    int update(ShareCommentReply shareCommentReply);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
