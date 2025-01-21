package com.jingdianjichi.circle.domain.convert;


import com.jingdianjichi.circle.domain.entity.SensitiveWordsBO;
import com.jingdianjichi.circle.infra.mybatis.entity.SensitiveWords;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 敏感词表(SensitiveWords)提供了SensitiveWords从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:17:07
 */
@Mapper
public interface SensitiveWordsBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SensitiveWordsBOConverter INSTANCE = Mappers.getMapper(SensitiveWordsBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param sensitiveWordsBO BO
     * @return 转换得到的Entity
     */
    SensitiveWords convertBo2Entity(SensitiveWordsBO sensitiveWordsBO);

    /**
     * Entity转换为BO。
     *
     * @param sensitiveWords Entity
     * @return 转换后的BO。
     */
    SensitiveWordsBO convertEntity2Bo(SensitiveWords sensitiveWords);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param sensitiveWordsList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<SensitiveWordsBO> convertEntity2Bo(List<SensitiveWords> sensitiveWordsList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param sensitiveWordsBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<SensitiveWords> convertBo2Entity(List<SensitiveWordsBO> sensitiveWordsBOList);
}


