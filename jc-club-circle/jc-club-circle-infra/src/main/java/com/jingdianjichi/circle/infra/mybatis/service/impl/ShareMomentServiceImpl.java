package com.jingdianjichi.circle.infra.mybatis.service.impl;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareMoment;
import com.jingdianjichi.circle.infra.mybatis.dao.ShareMomentDao;
import com.jingdianjichi.circle.infra.mybatis.service.ShareMomentService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 动态信息(ShareMoment)表服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:13:45
 */
@Service
public class ShareMomentServiceImpl implements ShareMomentService {
    @Resource
    private ShareMomentDao shareMomentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShareMoment queryById(Long id) {
        return this.shareMomentDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param shareMoment 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<ShareMoment> queryByPage(ShareMoment shareMoment
            , int start
            , int size) {
        return this.shareMomentDao.queryByPage(shareMoment, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param shareMoment 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(ShareMoment shareMoment) {
        return this.shareMomentDao.count(shareMoment);
    }

    /**
     * 新增数据
     *
     * @param shareMoment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(ShareMoment shareMoment) {
        return shareMomentDao.insert(shareMoment);
    }

    /**
     * 批量新增数据
     *
     * @param shareMomentList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<ShareMoment> shareMomentList) {
        return shareMomentDao.insertBatch(shareMomentList);
    }


    /**
     * 修改数据
     *
     * @param shareMoment 实例对象
     * @return 实例对象
     */
    @Override
    public int update(ShareMoment shareMoment) {
        return shareMomentDao.update(shareMoment);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.shareMomentDao.deleteById(id);
    }
}
