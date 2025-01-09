package com.jingdianjichi.practice.server.convert;

import com.jingdianjichi.practice.api.req.PracticeDetailDTO;
import com.jingdianjichi.practice.server.entity.PracticeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 练习详情表(PracticeDetail)提供了PracticeDetail从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-08 18:21:18
 */
@Mapper
public interface PracticeDetailDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    PracticeDetailDTOConverter INSTANCE = Mappers.getMapper(PracticeDetailDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param practiceDetailDTO DTO
     * @return BO
     */
    PracticeDetail convertDto2Entity(PracticeDetailDTO practiceDetailDTO);

    /**
     * BO转换为DTO。
     *
     * @param practiceDetailBO BO
     * @return 转换后的DTO。
     */
    PracticeDetailDTO convertEntity2Dto(PracticeDetail practiceDetailBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param practiceDetailBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<PracticeDetailDTO> convertEntity2Dto(List<PracticeDetail> practiceDetailBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param practiceDetailDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<PracticeDetail> convertDto2Entity(List<PracticeDetailDTO> practiceDetailDTOList);

}


