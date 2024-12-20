package com.jingdianjichi.auth.application.converter;

import com.jingdianjichi.auth.application.dto.AuthUserDTO;
import com.jingdianjichi.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户信息表(AuthUser)提供了AuthUser从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2024-12-21 03:09:32
 */
@Mapper
public interface AuthUserDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param authUserDTO DTO
     * @return BO
     */
    AuthUserBO convertDto2Bo(AuthUserDTO authUserDTO);

    /**
     * BO转换为DTO。
     *
     * @param authUserBO BO
     * @return 转换后的DTO。
     */
    AuthUserDTO convertBo2Dto(AuthUserBO authUserBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param authUserBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<AuthUserDTO> convertBo2Dto(List<AuthUserBO> authUserBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param authUserDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<AuthUserBO> convertDto2Bo(List<AuthUserDTO> authUserDTOList);

}


