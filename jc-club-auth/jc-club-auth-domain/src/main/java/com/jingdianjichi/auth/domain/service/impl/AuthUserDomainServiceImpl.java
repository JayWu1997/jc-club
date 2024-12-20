package com.jingdianjichi.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.jingdianjichi.auth.common.enums.IsDeletedEnum;
import com.jingdianjichi.auth.common.enums.UserEnableEnum;
import com.jingdianjichi.auth.domain.converter.AuthUserBOConverter;
import com.jingdianjichi.auth.domain.entity.AuthUserBO;
import com.jingdianjichi.auth.domain.service.AuthUserDomainService;
import com.jingdianjichi.auth.infra.base.entity.AuthUser;
import com.jingdianjichi.auth.infra.base.service.AuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户领域服务实现
 *
 * @author jay
 * @since 2024/12/21 上午4:15
 */
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    /**
     * 密码加密盐
     */
    private static final String SALT = "jingdianjichi";
    /**
     * 注册
     *
     * @param authUserBO 用户信息
     * @return token
     */
    @Override
    public String register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBo2Entity(authUserBO);
        authUser.setStatus(UserEnableEnum.ENABLE.getCode());
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), SALT));
        authUser.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        authUserService.insert(authUser);

        //TODO 建立角色关联
        String token = "";
        //TODO 把当前角色和权限刷到redis中
        return token;
    }

    /**
     * 更新用户信息
     *
     * @param authUserBO 用户信息
     * @return 成功标志
     */
    @Override
    public Boolean update(AuthUserBO authUserBO) {
        Boolean result = Boolean.FALSE;
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBo2Entity(authUserBO);
        if (authUserService.update(authUser) > 0) {
            //TODO 更新redis
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return 结果
     */
    @Override
    public Boolean delete(Long id) {
        Boolean result = Boolean.FALSE;
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        authUser.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        if (authUserService.update(authUser) > 0) {
            //TODO 删除redis
            result = Boolean.TRUE;
        }
        return result;
    }
}
