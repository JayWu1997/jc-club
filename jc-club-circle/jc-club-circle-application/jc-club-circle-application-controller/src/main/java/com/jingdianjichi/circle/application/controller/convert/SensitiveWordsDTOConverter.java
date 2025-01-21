package com.jingdianjichi.circle.application.controller.convert;


import com.jingdianjichi.circle.api.req.SensitiveWordsDTO;
import com.jingdianjichi.circle.domain.entity.SensitiveWordsBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 敏感词表(SensitiveWords)提供了SensitiveWords从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:21:09
 */
@Mapper
public interface SensitiveWordsDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SensitiveWordsDTOConverter INSTANCE = Mappers.getMapper(SensitiveWordsDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param sensitiveWordsDTO DTO
     * @return BO
     */
    SensitiveWordsBO convertDto2Bo(SensitiveWordsDTO sensitiveWordsDTO);

    /**
     * BO转换为DTO。
     *
     * @param sensitiveWordsBO BO
     * @return 转换后的DTO。
     */
    SensitiveWordsDTO convertBo2Dto(SensitiveWordsBO sensitiveWordsBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param sensitiveWordsBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<SensitiveWordsDTO> convertBo2Dto(List<SensitiveWordsBO> sensitiveWordsBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param sensitiveWordsDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<SensitiveWordsBO> convertDto2Bo(List<SensitiveWordsDTO> sensitiveWordsDTOList);

}


