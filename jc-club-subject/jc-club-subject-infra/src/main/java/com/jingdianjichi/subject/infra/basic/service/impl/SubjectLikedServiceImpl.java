package com.jingdianjichi.subject.infra.basic.service.impl;

import com.jingdianjichi.subject.infra.basic.dao.SubjectLikedDao;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLiked;
import com.jingdianjichi.subject.infra.basic.service.SubjectLikedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目点赞表(SubjectLiked)表服务实现类
 *
 * @author jay
 * @since 2025-01-07 14:34:04
 */
@Service("subjectLikedService")
public class SubjectLikedServiceImpl implements SubjectLikedService {
    @Resource
    private SubjectLikedDao subjectLikedDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectLiked queryById(Long id) {
        return this.subjectLikedDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param subjectLiked 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<SubjectLiked> queryByPage(SubjectLiked subjectLiked
            , int start
            , int size) {
        return this.subjectLikedDao.queryByPage(subjectLiked, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param subjectLiked 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(SubjectLiked subjectLiked) {
        return this.subjectLikedDao.count(subjectLiked);
    }

    /**
     * 新增数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectLiked subjectLiked) {
        return subjectLikedDao.insert(subjectLiked);
    }

    /**
     * 批量新增数据
     *
     * @param subjectLikedList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<SubjectLiked> subjectLikedList) {
        return subjectLikedDao.insertBatch(subjectLikedList);
    }


    /**
     * 修改数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectLiked subjectLiked) {
        return subjectLikedDao.update(subjectLiked);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.subjectLikedDao.deleteById(id);
    }
}
