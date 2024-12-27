package com.jingdianjichi.auth.application.converter;

import com.jingdianjichi.auth.api.req.AuthRolePermissionDTO;
import com.jingdianjichi.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)提供了AuthRolePermission从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:09:32
 */
@Mapper
public interface AuthRolePermissionDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param authRolePermissionDTO DTO
     * @return BO
     */
    AuthRolePermissionBO convertDto2Bo(AuthRolePermissionDTO authRolePermissionDTO);

    /**
     * BO转换为DTO。
     *
     * @param authRolePermissionBO BO
     * @return 转换后的DTO。
     */
    AuthRolePermissionDTO convertBo2Dto(AuthRolePermissionBO authRolePermissionBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param authRolePermissionBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<AuthRolePermissionDTO> convertBo2Dto(List<AuthRolePermissionBO> authRolePermissionBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param authRolePermissionDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<AuthRolePermissionBO> convertDto2Bo(List<AuthRolePermissionDTO> authRolePermissionDTOList);

}


