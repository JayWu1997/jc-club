package com.jingdianjichi.subject.application.convert;

import com.jingdianjichi.subject.api.req.SubjectLikedDTO;
import com.jingdianjichi.subject.domain.entity.SubjectLikedBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 题目点赞表(SubjectLiked)提供了SubjectLiked从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-06 15:41:28
 */
@Mapper
public interface SubjectLikedDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SubjectLikedDTOConverter INSTANCE = Mappers.getMapper(SubjectLikedDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param subjectLikedDTO DTO
     * @return BO
     */
    SubjectLikedBO convertDto2Bo(SubjectLikedDTO subjectLikedDTO);

    /**
     * BO转换为DTO。
     *
     * @param subjectLikedBO BO
     * @return 转换后的DTO。
     */
    SubjectLikedDTO convertBo2Dto(SubjectLikedBO subjectLikedBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param subjectLikedBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<SubjectLikedDTO> convertBo2Dto(List<SubjectLikedBO> subjectLikedBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param subjectLikedDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<SubjectLikedBO> convertDto2Bo(List<SubjectLikedDTO> subjectLikedDTOList);

}


