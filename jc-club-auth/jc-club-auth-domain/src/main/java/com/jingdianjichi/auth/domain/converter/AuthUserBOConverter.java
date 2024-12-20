package com.jingdianjichi.auth.domain.converter;

import com.jingdianjichi.auth.domain.entity.AuthUserBO;
import com.jingdianjichi.auth.infra.base.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户信息表(AuthUser)提供了AuthUser从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:15:30
 */
@Mapper
public interface AuthUserBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param authUserBO BO
     * @return 转换得到的Entity
     */
    AuthUser convertBo2Entity(AuthUserBO authUserBO);

    /**
     * Entity转换为BO。
     *
     * @param authUser Entity
     * @return 转换后的BO。
     */
    AuthUserBO convertEntity2Bo(AuthUser authUser);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param authUserList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<AuthUserBO> convertEntity2Bo(List<AuthUser> authUserList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param authUserBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<AuthUser> convertBo2Entity(List<AuthUserBO> authUserBOList);
}


