package com.jingdianjichi.practice.server.service.impl;

import com.jingdianjichi.practice.server.dao.PracticeSetDetailDao;
import com.jingdianjichi.practice.server.entity.PracticeSetDetail;
import com.jingdianjichi.practice.server.service.PracticeSetDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 套题内容表(PracticeSetDetail)表服务实现类
 *
 * @author jay
 * @since 2025-01-08 18:21:21
 */
@Service
public class PracticeSetDetailServiceImpl implements PracticeSetDetailService {
    @Resource
    private PracticeSetDetailDao practiceSetDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PracticeSetDetail queryById(Long id) {
        return this.practiceSetDetailDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param practiceSetDetail 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<PracticeSetDetail> queryByPage(PracticeSetDetail practiceSetDetail
            , int start
            , int size) {
        return this.practiceSetDetailDao.queryByPage(practiceSetDetail, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceSetDetail 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(PracticeSetDetail practiceSetDetail) {
        return this.practiceSetDetailDao.count(practiceSetDetail);
    }

    /**
     * 新增数据
     *
     * @param practiceSetDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(PracticeSetDetail practiceSetDetail) {
        return practiceSetDetailDao.insert(practiceSetDetail);
    }

    /**
     * 批量新增数据
     *
     * @param practiceSetDetailList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<PracticeSetDetail> practiceSetDetailList) {
        return practiceSetDetailDao.insertBatch(practiceSetDetailList);
    }


    /**
     * 修改数据
     *
     * @param practiceSetDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int update(PracticeSetDetail practiceSetDetail) {
        return practiceSetDetailDao.update(practiceSetDetail);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.practiceSetDetailDao.deleteById(id);
    }
}
