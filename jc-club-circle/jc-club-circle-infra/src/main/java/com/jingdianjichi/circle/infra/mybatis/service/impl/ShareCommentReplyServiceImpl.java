package com.jingdianjichi.circle.infra.mybatis.service.impl;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareCommentReply;
import com.jingdianjichi.circle.infra.mybatis.dao.ShareCommentReplyDao;
import com.jingdianjichi.circle.infra.mybatis.service.ShareCommentReplyService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 评论及回复信息(ShareCommentReply)表服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:13:43
 */
@Service
public class ShareCommentReplyServiceImpl implements ShareCommentReplyService {
    @Resource
    private ShareCommentReplyDao shareCommentReplyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShareCommentReply queryById(Long id) {
        return this.shareCommentReplyDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param shareCommentReply 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<ShareCommentReply> queryByPage(ShareCommentReply shareCommentReply
            , int start
            , int size) {
        return this.shareCommentReplyDao.queryByPage(shareCommentReply, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param shareCommentReply 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(ShareCommentReply shareCommentReply) {
        return this.shareCommentReplyDao.count(shareCommentReply);
    }

    /**
     * 新增数据
     *
     * @param shareCommentReply 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(ShareCommentReply shareCommentReply) {
        return shareCommentReplyDao.insert(shareCommentReply);
    }

    /**
     * 批量新增数据
     *
     * @param shareCommentReplyList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<ShareCommentReply> shareCommentReplyList) {
        return shareCommentReplyDao.insertBatch(shareCommentReplyList);
    }


    /**
     * 修改数据
     *
     * @param shareCommentReply 实例对象
     * @return 实例对象
     */
    @Override
    public int update(ShareCommentReply shareCommentReply) {
        return shareCommentReplyDao.update(shareCommentReply);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.shareCommentReplyDao.deleteById(id);
    }
}
