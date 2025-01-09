package com.jingdianjichi.practice.server.convert;

import com.jingdianjichi.practice.api.req.PracticeSetDetailDTO;
import com.jingdianjichi.practice.server.entity.PracticeSetDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 套题内容表(PracticeSetDetail)提供了PracticeSetDetail从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-08 18:21:21
 */
@Mapper
public interface PracticeSetDetailDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    PracticeSetDetailDTOConverter INSTANCE = Mappers.getMapper(PracticeSetDetailDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param practiceSetDetailDTO DTO
     * @return BO
     */
    PracticeSetDetail convertDto2Entity(PracticeSetDetailDTO practiceSetDetailDTO);

    /**
     * BO转换为DTO。
     *
     * @param practiceSetDetailBO BO
     * @return 转换后的DTO。
     */
    PracticeSetDetailDTO convertEntity2Dto(PracticeSetDetail practiceSetDetailBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param practiceSetDetailBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<PracticeSetDetailDTO> convertEntity2Dto(List<PracticeSetDetail> practiceSetDetailBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param practiceSetDetailDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<PracticeSetDetail> convertDto2Entity(List<PracticeSetDetailDTO> practiceSetDetailDTOList);

}


