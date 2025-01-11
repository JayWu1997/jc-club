package com.jingdianjichi.subject.infra.basic.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jingdianjichi.subject.infra.basic.dao.SubjectMappingDao;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务实现类
 *
 * @author jay
 * @since 2024-06-18 10:51:37
 */
@Service("subjectMappingService")
public class SubjectMappingServiceImpl implements SubjectMappingService {
    @Resource
    private SubjectMappingDao subjectMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMapping queryById(Long id) {
        return this.subjectMappingDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(SubjectMapping subjectMapping) {
        return this.subjectMappingDao.insert(subjectMapping);
    }

    /**
     * 批量新增数据
     *
     * @param subjectMappingList 实例对象列表
     * @return 成功个数
     */
    @Override
    public Integer insertBatch(List<SubjectMapping> subjectMappingList) {
        return subjectMappingDao.insertBatch(subjectMappingList);
    }

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(SubjectMapping subjectMapping) {
        return this.subjectMappingDao.update(subjectMapping);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectMappingDao.deleteById(id) > 0;
    }

    /**
     * 根据分类 ID 查询标签列表
     *
     * @param subjectMapping 包含分类 ID
     * @return 标签列表
     */
    @Override
    public List<Long> queryDistinctLabelIdsByCondition(SubjectMapping subjectMapping) {
        List<Long> idList = subjectMappingDao.queryDistinctLabelIdsByCondition(subjectMapping);
        if (CollectionUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        return idList;
    }

    /**
     * 根据分类 ID 查询题目数量
     *
     * @param categoryId 分类 ID
     * @return 符合条件的题目数量
     */
    @Override
    public Integer countByCategoryIdDistinctSubjectId(Long categoryId) {
        return subjectMappingDao.countByCategoryIdDistinctSubjectId(categoryId);
    }

    /**
     * 查询上一题的id
     *
     * @param subjectMapping
     * @return
     */
    @Override
    public Long queryLastSubjectId(SubjectMapping subjectMapping) {
        return subjectMappingDao.queryLastSubjectId(subjectMapping);
    }

    /**
     * 查询下一题的id
     *
     * @param subjectMapping
     * @return
     */
    @Override
    public Long queryNextSubjectId(SubjectMapping subjectMapping) {
        return subjectMappingDao.queryNextSubjectId(subjectMapping);
    }

    /**
     * 根据题目id批量查询
     *
     * @param subjectIdList
     * @return
     */
    @Override
    public List<SubjectMapping> queryBatchBySubjectIds(List<Long> subjectIdList) {
        List<SubjectMapping> entityList = subjectMappingDao.queryBatchBySubjectIds(subjectIdList);
        return CollUtil.isNotEmpty(entityList) ? entityList : new ArrayList<>();
    }
}
