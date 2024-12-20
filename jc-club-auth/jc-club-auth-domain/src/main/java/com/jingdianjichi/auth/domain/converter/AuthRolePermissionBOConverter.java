package com.jingdianjichi.auth.domain.converter;

import com.jingdianjichi.auth.domain.entity.AuthRolePermissionBO;
import com.jingdianjichi.auth.infra.base.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)提供了AuthRolePermission从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:15:30
 */
@Mapper
public interface AuthRolePermissionBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param authRolePermissionBO BO
     * @return 转换得到的Entity
     */
    AuthRolePermission convertBo2Entity(AuthRolePermissionBO authRolePermissionBO);

    /**
     * Entity转换为BO。
     *
     * @param authRolePermission Entity
     * @return 转换后的BO。
     */
    AuthRolePermissionBO convertEntity2Bo(AuthRolePermission authRolePermission);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param authRolePermissionList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<AuthRolePermissionBO> convertEntity2Bo(List<AuthRolePermission> authRolePermissionList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param authRolePermissionBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<AuthRolePermission> convertBo2Entity(List<AuthRolePermissionBO> authRolePermissionBOList);
}


