package com.jingdianjichi.auth.application.converter;

import com.jingdianjichi.auth.api.req.AuthPermissionDTO;
import com.jingdianjichi.auth.domain.entity.AuthPermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * (AuthPermission)提供了AuthPermission从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:09:31
 */
@Mapper
public interface AuthPermissionDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param authPermissionDTO DTO
     * @return BO
     */
    AuthPermissionBO convertDto2Bo(AuthPermissionDTO authPermissionDTO);

    /**
     * BO转换为DTO。
     *
     * @param authPermissionBO BO
     * @return 转换后的DTO。
     */
    AuthPermissionDTO convertBo2Dto(AuthPermissionBO authPermissionBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param authPermissionBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<AuthPermissionDTO> convertBo2Dto(List<AuthPermissionBO> authPermissionBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param authPermissionDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<AuthPermissionBO> convertDto2Bo(List<AuthPermissionDTO> authPermissionDTOList);

}


