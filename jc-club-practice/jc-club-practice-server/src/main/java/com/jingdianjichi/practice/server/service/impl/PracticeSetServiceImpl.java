package com.jingdianjichi.practice.server.service.impl;

import com.jingdianjichi.practice.api.vo.SpecialPracticeVO;
import com.jingdianjichi.practice.server.dao.PracticeSetDao;
import com.jingdianjichi.practice.server.entity.PracticeSet;
import com.jingdianjichi.practice.server.service.PracticeSetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 套题信息表(PracticeSet)表服务实现类
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Service("practiceSetService")
public class PracticeSetServiceImpl implements PracticeSetService {
    @Resource
    private PracticeSetDao practiceSetDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PracticeSet queryById(Long id) {
        return this.practiceSetDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param practiceSet 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<PracticeSet> queryByPage(PracticeSet practiceSet
            , int start
            , int size) {
        return this.practiceSetDao.queryByPage(practiceSet, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceSet 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(PracticeSet practiceSet) {
        return this.practiceSetDao.count(practiceSet);
    }

    /**
     * 新增数据
     *
     * @param practiceSet 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(PracticeSet practiceSet) {
        return practiceSetDao.insert(practiceSet);
    }

    /**
     * 批量新增数据
     *
     * @param practiceSetList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<PracticeSet> practiceSetList) {
        return practiceSetDao.insertBatch(practiceSetList);
    }


    /**
     * 修改数据
     *
     * @param practiceSet 实例对象
     * @return 实例对象
     */
    @Override
    public int update(PracticeSet practiceSet) {
        return practiceSetDao.update(practiceSet);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.practiceSetDao.deleteById(id);
    }

    /**
     * 获取专项练习标签列表
     *
     * @return
     */
    @Override
    public List<SpecialPracticeVO> getSpecialPracticeContent() {
        List<SpecialPracticeVO> voList = new ArrayList<>();
        return Collections.emptyList();
    }
}
