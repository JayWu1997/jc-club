package com.jingdianjichi.circle.domain.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jingdianjichi.circle.domain.entity.ShareCommentReplyBO;
import com.jingdianjichi.circle.domain.service.ShareCommentReplyDomainService;
import com.jingdianjichi.circle.infra.mybatis.model.ShareCommentReply;
import com.jingdianjichi.circle.infra.mybatis.service.IShareCommentReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author jay
 * @since 2025/1/22 下午8:29
 */
@Service
public class ShareCommentReplyDomainServiceImpl implements ShareCommentReplyDomainService {

    @Resource
    private IShareCommentReplyService commentService;

    /**
     * @param bo
     * @return
     */
    @Override
    public Boolean save(ShareCommentReplyBO bo) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<ShareCommentReplyBO> getCommentReplyListByMomentId(Long id) {
        List<ShareCommentReply> list = commentService.list(Wrappers.<ShareCommentReply>lambdaQuery().eq(ShareCommentReply::getMomentId, id));
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }


        return Collections.emptyList();
    }
}
