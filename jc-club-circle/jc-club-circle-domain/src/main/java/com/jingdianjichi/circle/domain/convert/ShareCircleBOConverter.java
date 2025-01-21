package com.jingdianjichi.circle.domain.convert;

import com.jingdianjichi.circle.domain.entity.ShareCircleBO;
import com.jingdianjichi.circle.infra.mybatis.model.ShareCircle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 圈子信息(ShareCircle)提供了ShareCircle从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:17:08
 */
@Mapper
public interface ShareCircleBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    ShareCircleBOConverter INSTANCE = Mappers.getMapper(ShareCircleBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param shareCircleBO BO
     * @return 转换得到的Entity
     */
    ShareCircle convertBo2Entity(ShareCircleBO shareCircleBO);

    /**
     * Entity转换为BO。
     *
     * @param shareCircle Entity
     * @return 转换后的BO。
     */
    ShareCircleBO convertEntity2Bo(ShareCircle shareCircle);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param shareCircleList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<ShareCircleBO> convertEntity2Bo(List<ShareCircle> shareCircleList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param shareCircleBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<ShareCircle> convertBo2Entity(List<ShareCircleBO> shareCircleBOList);
}


