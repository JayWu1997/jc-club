package com.jingdianjichi.circle.domain.service;

import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.ShareCommentReplyBO;

/**
 * 评论及回复信息(ShareCommentReply)领域服务
 *
 * @author jay
 * @since 2025-01-21 18:17:08
 */
public interface ShareCommentReplyDomainService {

    /**
     * 新增
     *
     * @param shareCommentReplyBO 新增的对象
     * @return 结果
     */
    Boolean add(ShareCommentReplyBO shareCommentReplyBO);

    /**
     * 更新
     *
     * @param shareCommentReplyBO 更新信息
     * @return 结果
     */
    Boolean update(ShareCommentReplyBO shareCommentReplyBO);

    /**
     * 删除
     *
     * @param shareCommentReplyBO 待删除的对象
     * @return 结果
     */
    Boolean deleteById(ShareCommentReplyBO shareCommentReplyBO);

    /**
     * 分页查询
     *
     * @param shareCommentReplyBO bo
     * @return 分页结果
     */
    PageResult<ShareCommentReplyBO> queryByPage(ShareCommentReplyBO shareCommentReplyBO);

    /**
     * 根据 id 查询
     *
     * @param shareCommentReplyBO bo
     * @return 结果
     */
    ShareCommentReplyBO queryById(ShareCommentReplyBO shareCommentReplyBO);
}

