package com.jingdianjichi.circle.infra.mybatis.service.impl;

import com.jingdianjichi.circle.infra.mybatis.entity.SensitiveWords;
import com.jingdianjichi.circle.infra.mybatis.dao.SensitiveWordsDao;
import com.jingdianjichi.circle.infra.mybatis.service.SensitiveWordsService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 敏感词表(SensitiveWords)表服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:13:42
 */
@Service
public class SensitiveWordsServiceImpl implements SensitiveWordsService {
    @Resource
    private SensitiveWordsDao sensitiveWordsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SensitiveWords queryById(Long id) {
        return this.sensitiveWordsDao.queryById(id);
    }

    /**
     * 通过条件查询所有数据
     *
     * @param sensitiveWords 查询条件
     * @return 查询结果集合
     */
    @Override
    public List<SensitiveWords> queryByPage(SensitiveWords sensitiveWords
            , int start
            , int size) {
        return this.sensitiveWordsDao.queryByPage(sensitiveWords, start, size);
    }

    /**
     * 查询符合条件的结果数量
     *
     * @param sensitiveWords 查询条件
     * @return 符合条件的结果数量
     */
    @Override
    public long count(SensitiveWords sensitiveWords) {
        return this.sensitiveWordsDao.count(sensitiveWords);
    }

    /**
     * 新增数据
     *
     * @param sensitiveWords 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SensitiveWords sensitiveWords) {
        return sensitiveWordsDao.insert(sensitiveWords);
    }

    /**
     * 批量新增数据
     *
     * @param sensitiveWordsList 实例对象列表
     * @return 成功数目
     */
    @Override
    public int insertBatch(List<SensitiveWords> sensitiveWordsList) {
        return sensitiveWordsDao.insertBatch(sensitiveWordsList);
    }


    /**
     * 修改数据
     *
     * @param sensitiveWords 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SensitiveWords sensitiveWords) {
        return sensitiveWordsDao.update(sensitiveWords);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.sensitiveWordsDao.deleteById(id);
    }
}
