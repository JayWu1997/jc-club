package com.jingdianjichi.circle.domain.convert;

import com.jingdianjichi.circle.domain.entity.ShareMessageBO;
import com.jingdianjichi.circle.infra.mybatis.entity.ShareMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 消息表(ShareMessage)提供了ShareMessage从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:17:09
 */
@Mapper
public interface ShareMessageBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    ShareMessageBOConverter INSTANCE = Mappers.getMapper(ShareMessageBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param shareMessageBO BO
     * @return 转换得到的Entity
     */
    ShareMessage convertBo2Entity(ShareMessageBO shareMessageBO);

    /**
     * Entity转换为BO。
     *
     * @param shareMessage Entity
     * @return 转换后的BO。
     */
    ShareMessageBO convertEntity2Bo(ShareMessage shareMessage);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param shareMessageList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<ShareMessageBO> convertEntity2Bo(List<ShareMessage> shareMessageList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param shareMessageBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<ShareMessage> convertBo2Entity(List<ShareMessageBO> shareMessageBOList);
}


