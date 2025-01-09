package com.jingdianjichi.practice.server.convert;

import com.jingdianjichi.practice.api.req.PracticeSetDTO;
import com.jingdianjichi.practice.server.entity.PracticeSet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 套题信息表(PracticeSet)提供了PracticeSet从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Mapper
public interface PracticeSetDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    PracticeSetDTOConverter INSTANCE = Mappers.getMapper(PracticeSetDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param practiceSetDTO DTO
     * @return BO
     */
    PracticeSet convertDto2Entity(PracticeSetDTO practiceSetDTO);

    /**
     * BO转换为DTO。
     *
     * @param practiceSetBO BO
     * @return 转换后的DTO。
     */
    PracticeSetDTO convertEntity2Dto(PracticeSet practiceSetBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param practiceSetBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<PracticeSetDTO> convertEntity2Dto(List<PracticeSet> practiceSetBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param practiceSetDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<PracticeSet> convertDto2Entity(List<PracticeSetDTO> practiceSetDTOList);

}


