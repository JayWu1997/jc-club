package com.jingdianjichi.circle.domain.service;

import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.ShareCircleBO;

/**
 * 圈子信息(ShareCircle)领域服务
 *
 * @author jay
 * @since 2025-01-21 18:17:08
 */
public interface ShareCircleDomainService {

    /**
     * 新增
     *
     * @param shareCircleBO 新增的对象
     * @return 结果
     */
    Boolean add(ShareCircleBO shareCircleBO);

    /**
     * 更新
     *
     * @param shareCircleBO 更新信息
     * @return 结果
     */
    Boolean update(ShareCircleBO shareCircleBO);

    /**
     * 删除
     *
     * @param shareCircleBO 待删除的对象
     * @return 结果
     */
    Boolean deleteById(ShareCircleBO shareCircleBO);

    /**
     * 分页查询
     *
     * @param shareCircleBO bo
     * @return 分页结果
     */
    PageResult<ShareCircleBO> queryByPage(ShareCircleBO shareCircleBO);

    /**
     * 根据 id 查询
     *
     * @param shareCircleBO bo
     * @return 结果
     */
    ShareCircleBO queryById(ShareCircleBO shareCircleBO);
}

