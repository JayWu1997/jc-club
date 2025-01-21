package com.jingdianjichi.circle.domain.service.impl;

import com.jingdianjichi.circle.domain.convert.ShareCommentReplyBOConverter;
import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.ShareCommentReplyBO;
import com.jingdianjichi.circle.domain.service.ShareCommentReplyDomainService;
import com.jingdianjichi.circle.infra.mybatis.entity.ShareCommentReply;
import com.jingdianjichi.circle.infra.mybatis.service.ShareCommentReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论及回复信息(ShareCommentReply)领域服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:17:08
 */
@Service
public class ShareCommentReplyDomainServiceImpl implements ShareCommentReplyDomainService {

    @Resource
    private ShareCommentReplyService shareCommentReplyService;

    /**
     * 新增
     *
     * @param shareCommentReplyBO 新增的对象
     * @return 结果
     */
    public Boolean add(ShareCommentReplyBO shareCommentReplyBO) {
        return 1 == shareCommentReplyService.insert(ShareCommentReplyBOConverter.INSTANCE.convertBo2Entity(shareCommentReplyBO));
    }

    /**
     * 更新
     *
     * @param shareCommentReplyBO 更新信息
     * @return 结果
     */
    public Boolean update(ShareCommentReplyBO shareCommentReplyBO) {
        return 1 == shareCommentReplyService.update(ShareCommentReplyBOConverter.INSTANCE.convertBo2Entity(shareCommentReplyBO));
    }

    /**
     * 删除
     *
     * @param shareCommentReplyBO 待删除的对象
     * @return 结果
     */
    public Boolean deleteById(ShareCommentReplyBO shareCommentReplyBO) {
        return 1 == shareCommentReplyService.deleteById(shareCommentReplyBO.getId());
    }

    /**
     * 分页查询
     *
     * @param shareCommentReplyBO bo
     * @return 分页结果
     */
    public PageResult<ShareCommentReplyBO> queryByPage(ShareCommentReplyBO shareCommentReplyBO) {
        shareCommentReplyBO.setIsDeleted(0);

        int start = (shareCommentReplyBO.getPageNo() - 1) * shareCommentReplyBO.getPageSize();
        int pageSize = shareCommentReplyBO.getPageSize();
        int total = (int) shareCommentReplyService.count(ShareCommentReplyBOConverter.INSTANCE.convertBo2Entity(shareCommentReplyBO));
        List<ShareCommentReply> entityList = shareCommentReplyService.queryByPage(ShareCommentReplyBOConverter.INSTANCE.convertBo2Entity(shareCommentReplyBO), start, pageSize);
        List<ShareCommentReplyBO> boList = ShareCommentReplyBOConverter.INSTANCE.convertEntity2Bo(entityList);
        return new PageResult<>(shareCommentReplyBO.getPageNo(), pageSize, total, boList);
    }

    /**
     * 根据 id 查询
     *
     * @param shareCommentReplyBO bo
     * @return 结果
     */
    public ShareCommentReplyBO queryById(ShareCommentReplyBO shareCommentReplyBO) {
        ShareCommentReply entity = shareCommentReplyService.queryById(shareCommentReplyBO.getId());
        return ShareCommentReplyBOConverter.INSTANCE.convertEntity2Bo(entity);
    }
}

