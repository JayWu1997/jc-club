package com.jingdianjichi.auth.domain.converter;

import com.jingdianjichi.auth.domain.entity.AuthRoleBO;
import com.jingdianjichi.auth.infra.base.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * (AuthRole)提供了AuthRole从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:15:30
 */
@Mapper
public interface AuthRoleBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param authRoleBO BO
     * @return 转换得到的Entity
     */
    AuthRole convertBo2Entity(AuthRoleBO authRoleBO);

    /**
     * Entity转换为BO。
     *
     * @param authRole Entity
     * @return 转换后的BO。
     */
    AuthRoleBO convertEntity2Bo(AuthRole authRole);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param authRoleList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<AuthRoleBO> convertEntity2Bo(List<AuthRole> authRoleList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param authRoleBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<AuthRole> convertBo2Entity(List<AuthRoleBO> authRoleBOList);
}


