package com.jingdianjichi.practice.server.service.impl;

import com.jingdianjichi.practice.server.dao.PracticeDetailDao;
import com.jingdianjichi.practice.server.entity.PracticeDetail;
import com.jingdianjichi.practice.server.service.PracticeDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 练习详情表(PracticeDetail)表服务实现类
 *
 * @author jay
 * @since 2025-01-08 18:21:19
 */
@Service
public class PracticeDetailServiceImpl implements PracticeDetailService {
    @Resource
    private PracticeDetailDao practiceDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PracticeDetail queryById(Long id) {
        return this.practiceDetailDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param practiceDetail 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<PracticeDetail> queryByPage(PracticeDetail practiceDetail
            , int start
            , int size) {
        return this.practiceDetailDao.queryByPage(practiceDetail, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceDetail 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(PracticeDetail practiceDetail) {
        return this.practiceDetailDao.count(practiceDetail);
    }

    /**
     * 新增数据
     *
     * @param practiceDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(PracticeDetail practiceDetail) {
        return practiceDetailDao.insert(practiceDetail);
    }

    /**
     * 批量新增数据
     *
     * @param practiceDetailList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<PracticeDetail> practiceDetailList) {
        return practiceDetailDao.insertBatch(practiceDetailList);
    }


    /**
     * 修改数据
     *
     * @param practiceDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int update(PracticeDetail practiceDetail) {
        return practiceDetailDao.update(practiceDetail);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.practiceDetailDao.deleteById(id);
    }
}
