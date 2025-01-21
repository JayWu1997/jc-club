package com.jingdianjichi.circle.infra.mybatis.service.impl;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareMessage;
import com.jingdianjichi.circle.infra.mybatis.dao.ShareMessageDao;
import com.jingdianjichi.circle.infra.mybatis.service.ShareMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 消息表(ShareMessage)表服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:13:44
 */
@Service
public class ShareMessageServiceImpl implements ShareMessageService {
    @Resource
    private ShareMessageDao shareMessageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShareMessage queryById(Integer id) {
        return this.shareMessageDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param shareMessage 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<ShareMessage> queryByPage(ShareMessage shareMessage
            , int start
            , int size) {
        return this.shareMessageDao.queryByPage(shareMessage, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param shareMessage 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(ShareMessage shareMessage) {
        return this.shareMessageDao.count(shareMessage);
    }

    /**
     * 新增数据
     *
     * @param shareMessage 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(ShareMessage shareMessage) {
        return shareMessageDao.insert(shareMessage);
    }

    /**
     * 批量新增数据
     *
     * @param shareMessageList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<ShareMessage> shareMessageList) {
        return shareMessageDao.insertBatch(shareMessageList);
    }


    /**
     * 修改数据
     *
     * @param shareMessage 实例对象
     * @return 实例对象
     */
    @Override
    public int update(ShareMessage shareMessage) {
        return shareMessageDao.update(shareMessage);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Integer id) {
        return this.shareMessageDao.deleteById(id);
    }
}
