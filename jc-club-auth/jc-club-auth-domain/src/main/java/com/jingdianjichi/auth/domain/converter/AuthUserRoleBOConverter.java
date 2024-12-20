package com.jingdianjichi.auth.domain.converter;

import com.jingdianjichi.auth.domain.entity.AuthUserRoleBO;
import com.jingdianjichi.auth.infra.base.entity.AuthUserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户角色表(AuthUserRole)提供了AuthUserRole从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:15:30
 */
@Mapper
public interface AuthUserRoleBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthUserRoleBOConverter INSTANCE = Mappers.getMapper(AuthUserRoleBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param authUserRoleBO BO
     * @return 转换得到的Entity
     */
    AuthUserRole convertBo2Entity(AuthUserRoleBO authUserRoleBO);

    /**
     * Entity转换为BO。
     *
     * @param authUserRole Entity
     * @return 转换后的BO。
     */
    AuthUserRoleBO convertEntity2Bo(AuthUserRole authUserRole);

    /**
     * 将Entity列表转换为BO列表。
     *
     * @param authUserRoleList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<AuthUserRoleBO> convertEntity2Bo(List<AuthUserRole> authUserRoleList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param authUserRoleBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<AuthUserRole> convertBo2Entity(List<AuthUserRoleBO> authUserRoleBOList);
}


