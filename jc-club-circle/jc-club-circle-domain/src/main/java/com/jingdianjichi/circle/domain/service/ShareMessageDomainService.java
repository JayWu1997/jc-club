package com.jingdianjichi.circle.domain.service;

import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.ShareMessageBO;

/**
 * 消息表(ShareMessage)领域服务
 *
 * @author jay
 * @since 2025-01-21 18:17:09
 */
public interface ShareMessageDomainService {

    /**
     * 新增
     *
     * @param shareMessageBO 新增的对象
     * @return 结果
     */
    Boolean add(ShareMessageBO shareMessageBO);

    /**
     * 更新
     *
     * @param shareMessageBO 更新信息
     * @return 结果
     */
    Boolean update(ShareMessageBO shareMessageBO);

    /**
     * 删除
     *
     * @param shareMessageBO 待删除的对象
     * @return 结果
     */
    Boolean deleteById(ShareMessageBO shareMessageBO);

    /**
     * 分页查询
     *
     * @param shareMessageBO bo
     * @return 分页结果
     */
    PageResult<ShareMessageBO> queryByPage(ShareMessageBO shareMessageBO);

    /**
     * 根据 id 查询
     *
     * @param shareMessageBO bo
     * @return 结果
     */
    ShareMessageBO queryById(ShareMessageBO shareMessageBO);
}

