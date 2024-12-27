package com.jingdianjichi.auth.application.converter;

import com.jingdianjichi.auth.api.req.AuthRoleDTO;
import com.jingdianjichi.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * (AuthRole)提供了AuthRole从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:09:32
 */
@Mapper
public interface AuthRoleDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param authRoleDTO DTO
     * @return BO
     */
    AuthRoleBO convertDto2Bo(AuthRoleDTO authRoleDTO);

    /**
     * BO转换为DTO。
     *
     * @param authRoleBO BO
     * @return 转换后的DTO。
     */
    AuthRoleDTO convertBo2Dto(AuthRoleBO authRoleBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param authRoleBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<AuthRoleDTO> convertBo2Dto(List<AuthRoleBO> authRoleBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param authRoleDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<AuthRoleBO> convertDto2Bo(List<AuthRoleDTO> authRoleDTOList);

}


