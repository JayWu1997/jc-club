package com.jingdianjichi.circle.application.controller.convert;

import com.jingdianjichi.circle.api.req.ShareCircleDTO;
import com.jingdianjichi.circle.domain.entity.ShareCircleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 圈子信息(ShareCircle)提供了ShareCircle从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:21:09
 */
@Mapper
public interface ShareCircleDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    ShareCircleDTOConverter INSTANCE = Mappers.getMapper(ShareCircleDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param shareCircleDTO DTO
     * @return BO
     */
    ShareCircleBO convertDto2Bo(ShareCircleDTO shareCircleDTO);

    /**
     * BO转换为DTO。
     *
     * @param shareCircleBO BO
     * @return 转换后的DTO。
     */
    ShareCircleDTO convertBo2Dto(ShareCircleBO shareCircleBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param shareCircleBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<ShareCircleDTO> convertBo2Dto(List<ShareCircleBO> shareCircleBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param shareCircleDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<ShareCircleBO> convertDto2Bo(List<ShareCircleDTO> shareCircleDTOList);

}


