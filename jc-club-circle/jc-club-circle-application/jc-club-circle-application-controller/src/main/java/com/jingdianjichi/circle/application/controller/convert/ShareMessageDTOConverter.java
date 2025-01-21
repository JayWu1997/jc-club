package com.jingdianjichi.circle.application.controller.convert;

import com.jingdianjichi.circle.api.req.ShareMessageDTO;
import com.jingdianjichi.circle.domain.entity.ShareMessageBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 消息表(ShareMessage)提供了ShareMessage从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:21:09
 */
@Mapper
public interface ShareMessageDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    ShareMessageDTOConverter INSTANCE = Mappers.getMapper(ShareMessageDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param shareMessageDTO DTO
     * @return BO
     */
    ShareMessageBO convertDto2Bo(ShareMessageDTO shareMessageDTO);

    /**
     * BO转换为DTO。
     *
     * @param shareMessageBO BO
     * @return 转换后的DTO。
     */
    ShareMessageDTO convertBo2Dto(ShareMessageBO shareMessageBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param shareMessageBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<ShareMessageDTO> convertBo2Dto(List<ShareMessageBO> shareMessageBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param shareMessageDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<ShareMessageBO> convertDto2Bo(List<ShareMessageDTO> shareMessageDTOList);

}


