package com.jingdianjichi.circle.application.controller.convert;

import com.jingdianjichi.circle.api.req.ShareMomentDTO;
import com.jingdianjichi.circle.domain.entity.ShareMomentBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 动态信息(ShareMoment)提供了ShareMoment从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:21:10
 */
@Mapper
public interface ShareMomentDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    ShareMomentDTOConverter INSTANCE = Mappers.getMapper(ShareMomentDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param shareMomentDTO DTO
     * @return BO
     */
    ShareMomentBO convertDto2Bo(ShareMomentDTO shareMomentDTO);

    /**
     * BO转换为DTO。
     *
     * @param shareMomentBO BO
     * @return 转换后的DTO。
     */
    ShareMomentDTO convertBo2Dto(ShareMomentBO shareMomentBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param shareMomentBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<ShareMomentDTO> convertBo2Dto(List<ShareMomentBO> shareMomentBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param shareMomentDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<ShareMomentBO> convertDto2Bo(List<ShareMomentDTO> shareMomentDTOList);

}


