package com.jingdianjichi.circle.domain.service;

import com.jingdianjichi.circle.domain.entity.ShareCircleBO;

/**
 * @author jay
 * @since 2025/1/21 下午11:56
 */
public interface ShareCircleDomainService {

    Boolean save(ShareCircleBO dto);

    Boolean update(ShareCircleBO bo);
}
