package com.jingdianjichi.subject.infra.basic.service.impl;

import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import com.jingdianjichi.subject.infra.basic.dao.SubjectInfoDao;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务实现类
 *
 * @author jay
 * @since 2024-06-18 18:19:01
 */
@Service("subjectInfoService")
public class SubjectInfoServiceImpl implements SubjectInfoService {
    @Resource
    private SubjectInfoDao subjectInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectInfo queryById(Long id) {
        return this.subjectInfoDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo insert(SubjectInfo subjectInfo) {
        subjectInfoDao.insert(subjectInfo);
        return subjectInfo;
    }

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectInfo subjectInfo) {
        return subjectInfoDao.update(subjectInfo);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectInfoDao.deleteById(id) > 0;
    }

    /**
     * 条件查询
     *
     * @param subjectInfo
     * @param categoryId
     * @param labelId
     * @return
     */
    @Override
    public Integer countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId) {
        return subjectInfoDao.countByCondition(subjectInfo, categoryId, labelId);
    }

    /**
     * 条件查询
     *
     * @param subjectInfo 查询条件
     * @param categoryId
     * @param labelId
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<SubjectInfo> queryByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId, Integer start, Integer pageSize) {
        return subjectInfoDao.queryPage(subjectInfo, categoryId, labelId, start, pageSize);
    }

    /**
     * 多表条件查询
     *
     * @param subjectType
     * @param labelIdList
     * @param queryCount
     * @return
     */
    @Override
    public List<SubjectInfo> queryByConditionInMultiTable(Integer subjectType, List<Long> labelIdList, Integer queryCount) {
        return subjectInfoDao.queryByConditionInMultiTable(subjectType, labelIdList, queryCount);
    }
}
