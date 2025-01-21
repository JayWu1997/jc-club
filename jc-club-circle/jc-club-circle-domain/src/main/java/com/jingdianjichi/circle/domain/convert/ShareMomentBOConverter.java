package com.jingdianjichi.circle.domain.convert;

import com.jingdianjichi.circle.domain.entity.ShareMomentBO;
import com.jingdianjichi.circle.infra.mybatis.entity.ShareMoment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 动态信息(ShareMoment)提供了ShareMoment从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:17:09
 */
@Mapper
public interface ShareMomentBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    ShareMomentBOConverter INSTANCE = Mappers.getMapper(ShareMomentBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param shareMomentBO BO
     * @return 转换得到的Entity
     */
    ShareMoment convertBo2Entity(ShareMomentBO shareMomentBO);

    /**
     * Entity转换为BO。
     *
     * @param shareMoment Entity
     * @return 转换后的BO。
     */
    ShareMomentBO convertEntity2Bo(ShareMoment shareMoment);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param shareMomentList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<ShareMomentBO> convertEntity2Bo(List<ShareMoment> shareMomentList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param shareMomentBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<ShareMoment> convertBo2Entity(List<ShareMomentBO> shareMomentBOList);
}


