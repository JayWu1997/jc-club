package com.jingdianjichi.circle.infra.mybatis.service;

import com.jingdianjichi.circle.infra.mybatis.entity.SensitiveWords;

import java.util.List;

/**
 * 敏感词表(SensitiveWords)表服务接口
 *
 * @author jay
 * @since 2025-01-21 18:13:42
 */
public interface SensitiveWordsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SensitiveWords queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param sensitiveWords 查询条件
     * @return 查询结果集合
     */
    List<SensitiveWords> queryByPage(SensitiveWords sensitiveWords
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param sensitiveWords 查询条件
     * @return 符合条件的结果数量
     */
    long count(SensitiveWords sensitiveWords);

    /**
     * 新增数据
     *
     * @param sensitiveWords 实例对象
     * @return 实例对象
     */
    int insert(SensitiveWords sensitiveWords);

    /**
     * 批量新增数据
     *
     * @param sensitiveWordsList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<SensitiveWords> sensitiveWordsList);

    /**
     * 修改数据
     *
     * @param sensitiveWords 实例对象
     * @return 实例对象
     */
    int update(SensitiveWords sensitiveWords);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
