package com.jingdianjichi.circle.domain.service;

import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.SensitiveWordsBO;

/**
 * 敏感词表(SensitiveWords)领域服务
 *
 * @author jay
 * @since 2025-01-21 18:17:07
 */
public interface SensitiveWordsDomainService {

    /**
     * 新增
     *
     * @param sensitiveWordsBO 新增的对象
     * @return 结果
     */
    Boolean add(SensitiveWordsBO sensitiveWordsBO);

    /**
     * 更新
     *
     * @param sensitiveWordsBO 更新信息
     * @return 结果
     */
    Boolean update(SensitiveWordsBO sensitiveWordsBO);

    /**
     * 删除
     *
     * @param sensitiveWordsBO 待删除的对象
     * @return 结果
     */
    Boolean deleteById(SensitiveWordsBO sensitiveWordsBO);

    /**
     * 分页查询
     *
     * @param sensitiveWordsBO bo
     * @return 分页结果
     */
    PageResult<SensitiveWordsBO> queryByPage(SensitiveWordsBO sensitiveWordsBO);

    /**
     * 根据 id 查询
     *
     * @param sensitiveWordsBO bo
     * @return 结果
     */
    SensitiveWordsBO queryById(SensitiveWordsBO sensitiveWordsBO);
}

