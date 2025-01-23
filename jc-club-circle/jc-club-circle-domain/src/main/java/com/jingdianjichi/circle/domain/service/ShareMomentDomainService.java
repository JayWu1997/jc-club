package com.jingdianjichi.circle.domain.service;

import com.jingdianjichi.circle.domain.entity.ShareMomentBO;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/22 下午4:19
 */
public interface ShareMomentDomainService {
    Boolean save(ShareMomentBO bo);
    List<ShareMomentBO> getMoments(ShareMomentBO shareMomentBO);
    void cleanCache();
}
