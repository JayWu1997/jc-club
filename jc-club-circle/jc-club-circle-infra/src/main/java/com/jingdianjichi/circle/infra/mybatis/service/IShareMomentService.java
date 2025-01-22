package com.jingdianjichi.circle.infra.mybatis.service;

import com.jingdianjichi.circle.infra.mybatis.model.ShareMoment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 动态信息 服务类
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
public interface IShareMomentService extends IService<ShareMoment> {

    void incrReplyCount(Integer momentId);
}
