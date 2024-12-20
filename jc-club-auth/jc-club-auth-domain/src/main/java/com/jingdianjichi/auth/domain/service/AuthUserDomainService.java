package com.jingdianjichi.auth.domain.service;

import com.jingdianjichi.auth.domain.entity.AuthUserBO;

/**
 * 用户领域服务
 * @author jay
 * @since 2024/12/21 上午4:13
 */
public interface AuthUserDomainService {

    /**
     * 注册
     * @param authUserBO 用户信息
     * @return token
     */
    String register(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     * @param authUserBO 用户信息
     * @return 成功标志
     */
    Boolean update(AuthUserBO authUserBO);

    /**
     * 删除用户信息
     * @param id 用户id
     * @return 结果
     */
    Boolean delete(Long id);
}
