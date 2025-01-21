package com.jingdianjichi.circle.infra.mybatis.service.impl;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareCircle;
import com.jingdianjichi.circle.infra.mybatis.dao.ShareCircleDao;
import com.jingdianjichi.circle.infra.mybatis.service.ShareCircleService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 圈子信息(ShareCircle)表服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:13:43
 */
@Service
public class ShareCircleServiceImpl implements ShareCircleService {
    @Resource
    private ShareCircleDao shareCircleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShareCircle queryById(Long id) {
        return this.shareCircleDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param shareCircle 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<ShareCircle> queryByPage(ShareCircle shareCircle
            , int start
            , int size) {
        return this.shareCircleDao.queryByPage(shareCircle, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param shareCircle 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(ShareCircle shareCircle) {
        return this.shareCircleDao.count(shareCircle);
    }

    /**
     * 新增数据
     *
     * @param shareCircle 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(ShareCircle shareCircle) {
        return shareCircleDao.insert(shareCircle);
    }

    /**
     * 批量新增数据
     *
     * @param shareCircleList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<ShareCircle> shareCircleList) {
        return shareCircleDao.insertBatch(shareCircleList);
    }


    /**
     * 修改数据
     *
     * @param shareCircle 实例对象
     * @return 实例对象
     */
    @Override
    public int update(ShareCircle shareCircle) {
        return shareCircleDao.update(shareCircle);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.shareCircleDao.deleteById(id);
    }
}
