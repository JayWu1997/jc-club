package com.jingdianjichi.auth.application.converter;

import com.jingdianjichi.auth.api.req.AuthUserRoleDTO;
import com.jingdianjichi.auth.domain.entity.AuthUserRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户角色表(AuthUserRole)提供了AuthUserRole从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:09:32
 */
@Mapper
public interface AuthUserRoleDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthUserRoleDTOConverter INSTANCE = Mappers.getMapper(AuthUserRoleDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param authUserRoleDTO DTO
     * @return BO
     */
    AuthUserRoleBO convertDto2Bo(AuthUserRoleDTO authUserRoleDTO);

    /**
     * BO转换为DTO。
     *
     * @param authUserRoleBO BO
     * @return 转换后的DTO。
     */
    AuthUserRoleDTO convertBo2Dto(AuthUserRoleBO authUserRoleBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param authUserRoleBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<AuthUserRoleDTO> convertBo2Dto(List<AuthUserRoleBO> authUserRoleBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param authUserRoleDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<AuthUserRoleBO> convertDto2Bo(List<AuthUserRoleDTO> authUserRoleDTOList);

}


