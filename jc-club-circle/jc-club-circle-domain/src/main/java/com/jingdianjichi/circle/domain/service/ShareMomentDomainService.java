package com.jingdianjichi.circle.domain.service;

import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.ShareMomentBO;

/**
 * 动态信息(ShareMoment)领域服务
 *
 * @author jay
 * @since 2025-01-21 18:17:09
 */
public interface ShareMomentDomainService {

    /**
     * 新增
     *
     * @param shareMomentBO 新增的对象
     * @return 结果
     */
    Boolean add(ShareMomentBO shareMomentBO);

    /**
     * 更新
     *
     * @param shareMomentBO 更新信息
     * @return 结果
     */
    Boolean update(ShareMomentBO shareMomentBO);

    /**
     * 删除
     *
     * @param shareMomentBO 待删除的对象
     * @return 结果
     */
    Boolean deleteById(ShareMomentBO shareMomentBO);

    /**
     * 分页查询
     *
     * @param shareMomentBO bo
     * @return 分页结果
     */
    PageResult<ShareMomentBO> queryByPage(ShareMomentBO shareMomentBO);

    /**
     * 根据 id 查询
     *
     * @param shareMomentBO bo
     * @return 结果
     */
    ShareMomentBO queryById(ShareMomentBO shareMomentBO);
}

