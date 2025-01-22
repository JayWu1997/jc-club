package com.jingdianjichi.circle.domain.service;

import com.jingdianjichi.circle.domain.entity.ShareCommentReplyBO;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/22 下午8:29
 */
public interface ShareCommentReplyDomainService {
    Boolean save(ShareCommentReplyBO bo);

    List<ShareCommentReplyBO> getCommentReplyListByMomentId(Long id);
}
