package com.jingdianjichi.subject.infra.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import com.jingdianjichi.subject.infra.basic.dao.SubjectCategoryDao;
import com.jingdianjichi.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务实现类
 *
 * @author makejava
 * @since 2024-04-02 00:54:33
 */
@Service()
@Slf4j
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    private SubjectCategoryDao subjectCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectCategory queryById(Long id) {
        return this.subjectCategoryDao.queryById(id);
    }

    /**
     * 根据类别名称查询主题类别信息。
     *
     * @param categoryName 要查询的主题类别的名称。
     * @return 返回与给定类别名称匹配的主题类别对象。如果没有找到匹配的类别，则返回null。
     */
    @Override
    public SubjectCategory queryByCategoryName(String categoryName) {
        SubjectCategory category = new SubjectCategory();
        category.setCategoryName(categoryName);
        category.setIsDeleted(0);
        List<SubjectCategory> categoryList = subjectCategoryDao.queryByAll(category);
        return categoryList.size() == 1 ? categoryList.get(0) : null;
    }

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory insert(SubjectCategory subjectCategory) {
        if(log.isInfoEnabled()) {
            log.info("SubjectCategoryServiceImpl.insert.subjectCategory:{}", JSON.toJSON(subjectCategory));
        }
        this.subjectCategoryDao.insert(subjectCategory);
        if(log.isInfoEnabled()) {
            log.info("SubjectCategoryServiceImpl.insert.result:{}", JSON.toJSON(subjectCategory));
        }
        return subjectCategory;
    }

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory update(SubjectCategory subjectCategory) {
        this.subjectCategoryDao.update(subjectCategory);
        return this.queryById(subjectCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectCategoryDao.deleteById(id) > 0;
    }
}
