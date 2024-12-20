package com.jingdianjichi.auth.domain.converter;

import com.jingdianjichi.auth.domain.entity.AuthPermissionBO;
import com.jingdianjichi.auth.infra.base.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * (AuthPermission)提供了AuthPermission从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:15:30
 */
@Mapper
public interface AuthPermissionBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param authPermissionBO BO
     * @return 转换得到的Entity
     */
    AuthPermission convertBo2Entity(AuthPermissionBO authPermissionBO);

    /**
     * Entity转换为BO。
     *
     * @param authPermission Entity
     * @return 转换后的BO。
     */
    AuthPermissionBO convertEntity2Bo(AuthPermission authPermission);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param authPermissionList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<AuthPermissionBO> convertEntity2Bo(List<AuthPermission> authPermissionList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param authPermissionBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<AuthPermission> convertBo2Entity(List<AuthPermissionBO> authPermissionBOList);
}


