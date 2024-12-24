package com.jingdianjichi.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.jingdianjichi.auth.domain.entity.AuthUserBO;

/**
 * 用户领域服务
 * @author jay
 * @since 2024/12/21 上午4:13
 */
public interface AuthUserDomainService {

    /**
     * 注册
     *
     * @param authUserBO 用户信息
     * @return token
     */
    SaTokenInfo register(AuthUserBO authUserBO);

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

    /**
     * 验证码登录
     * @param validCode 验证码
     * @return token 信息
     */
    SaTokenInfo doLogin(String validCode);

    /**
     * 获取用户信息
     * @param authUserBO 查询条件
     * @return 用户信息
     */
    AuthUserBO getUserInfo(AuthUserBO authUserBO);

    /**
     * 登出
     * @param authUserBO 用户信息
     * @return 操作成功标志
     */
    Boolean logout(AuthUserBO authUserBO);

    /**
     * 根据用户名更新用户信息
     * @param authUserBO 用户信息
     * @return 是否成功
     */
    Boolean updateByUserName(AuthUserBO authUserBO);
}
