package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectLikedBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLiked;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 题目点赞表(SubjectLiked)提供了SubjectLiked从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-06 15:39:33
 */
@Mapper
public interface SubjectLikedBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SubjectLikedBOConverter INSTANCE = Mappers.getMapper(SubjectLikedBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param subjectLikedBO BO
     * @return 转换得到的Entity
     */
    SubjectLiked convertBo2Entity(SubjectLikedBO subjectLikedBO);

    /**
     * Entity转换为BO。
     *
     * @param subjectLiked Entity
     * @return 转换后的BO。
     */
    SubjectLikedBO convertEntity2Bo(SubjectLiked subjectLiked);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param subjectLikedList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<SubjectLikedBO> convertEntity2Bo(List<SubjectLiked> subjectLikedList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param subjectLikedBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<SubjectLiked> convertBo2Entity(List<SubjectLikedBO> subjectLikedBOList);
}


