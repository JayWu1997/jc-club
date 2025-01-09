package com.jingdianjichi.practice.server.service.impl;

import com.jingdianjichi.practice.server.dao.PracticeInfoDao;
import com.jingdianjichi.practice.server.entity.PracticeInfo;
import com.jingdianjichi.practice.server.service.PracticeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 练习表(PracticeInfo)表服务实现类
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Service
public class PracticeInfoServiceImpl implements PracticeInfoService {
    @Resource
    private PracticeInfoDao practiceInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PracticeInfo queryById(Long id) {
        return this.practiceInfoDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param practiceInfo 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<PracticeInfo> queryByPage(PracticeInfo practiceInfo
            , int start
            , int size) {
        return this.practiceInfoDao.queryByPage(practiceInfo, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceInfo 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(PracticeInfo practiceInfo) {
        return this.practiceInfoDao.count(practiceInfo);
    }

    /**
     * 新增数据
     *
     * @param practiceInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(PracticeInfo practiceInfo) {
        return practiceInfoDao.insert(practiceInfo);
    }

    /**
     * 批量新增数据
     *
     * @param practiceInfoList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<PracticeInfo> practiceInfoList) {
        return practiceInfoDao.insertBatch(practiceInfoList);
    }


    /**
     * 修改数据
     *
     * @param practiceInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int update(PracticeInfo practiceInfo) {
        return practiceInfoDao.update(practiceInfo);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.practiceInfoDao.deleteById(id);
    }
}
