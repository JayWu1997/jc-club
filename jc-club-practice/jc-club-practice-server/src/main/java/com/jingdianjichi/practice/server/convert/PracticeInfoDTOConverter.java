package com.jingdianjichi.practice.server.convert;

import com.jingdianjichi.practice.api.req.PracticeInfoDTO;
import com.jingdianjichi.practice.server.entity.PracticeInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 练习表(PracticeInfo)提供了PracticeInfo从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-08 18:21:19
 */
@Mapper
public interface PracticeInfoDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    PracticeInfoDTOConverter INSTANCE = Mappers.getMapper(PracticeInfoDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param practiceInfoDTO DTO
     * @return BO
     */
    PracticeInfo convertDto2Entity(PracticeInfoDTO practiceInfoDTO);

    /**
     * BO转换为DTO。
     *
     * @param practiceInfoBO BO
     * @return 转换后的DTO。
     */
    PracticeInfoDTO convertEntity2Dto(PracticeInfo practiceInfoBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param practiceInfoBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<PracticeInfoDTO> convertEntity2Dto(List<PracticeInfo> practiceInfoBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param practiceInfoDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<PracticeInfo> convertDto2Entity(List<PracticeInfoDTO> practiceInfoDTOList);

}


