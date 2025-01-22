package com.jingdianjichi.circle.infra.mybatis.dao;

import com.jingdianjichi.circle.infra.mybatis.model.ShareMoment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;


/**
 * <p>
 * 动态信息 Mapper 接口
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
public interface ShareMomentMapper extends BaseMapper<ShareMoment> {

    Integer incrReplyCount(@Param("id") Long id);
}
