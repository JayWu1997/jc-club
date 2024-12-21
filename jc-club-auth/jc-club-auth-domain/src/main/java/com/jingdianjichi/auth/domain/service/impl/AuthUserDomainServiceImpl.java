package com.jingdianjichi.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.jingdianjichi.auth.common.enums.IsDeletedEnum;
import com.jingdianjichi.auth.common.enums.ResultCodeEnum;
import com.jingdianjichi.auth.common.enums.UserEnableEnum;
import com.jingdianjichi.auth.common.util.ParamCheckUtil;
import com.jingdianjichi.auth.domain.converter.AuthUserBOConverter;
import com.jingdianjichi.auth.domain.entity.AuthUserBO;
import com.jingdianjichi.auth.domain.service.AuthUserDomainService;
import com.jingdianjichi.auth.infra.base.entity.AuthRole;
import com.jingdianjichi.auth.infra.base.entity.AuthUser;
import com.jingdianjichi.auth.infra.base.entity.AuthUserRole;
import com.jingdianjichi.auth.infra.base.service.AuthRoleService;
import com.jingdianjichi.auth.infra.base.service.AuthUserRoleService;
import com.jingdianjichi.auth.infra.base.service.AuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

import static com.jingdianjichi.auth.domain.constant.AuthConstant.DEFAULT_ROLE_KEY;

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

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthRoleService authRoleService;

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
    @Transactional(rollbackFor = Exception.class)
    public String register(AuthUserBO authUserBO) {
        // 插入用户信息
        AuthUser user = AuthUserBOConverter.INSTANCE.convertBo2Entity(authUserBO);
        user.setStatus(UserEnableEnum.ENABLE.getCode());
        user.setPassword(SaSecureUtil.md5BySalt(user.getPassword(), SALT));
        user.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        authUserService.insert(user);
        ParamCheckUtil.checkNotNull(user.getId(), ResultCodeEnum.PARAM_ERROR, "角色添加失败！");

        // TODO 考虑是否改为从 redis取默认角色
        // 获取默认角色
        AuthRole role = new AuthRole();
        role.setRoleKey(DEFAULT_ROLE_KEY);
        role = Optional.ofNullable(authRoleService.queryAll(role))
                .map(authRoles -> authRoles.get(0))
                .orElse(null);
        ParamCheckUtil.checkNotNull(role, ResultCodeEnum.PARAM_ERROR, "角色添加失败！role key" + DEFAULT_ROLE_KEY + "所代表的角色不存在!");

        // 建立角色关联
        AuthUserRole userRole = new AuthUserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        userRole.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        authUserRoleService.insert(userRole);
        ParamCheckUtil.checkNotNull(userRole.getId(), ResultCodeEnum.PARAM_ERROR, "角色添加失败！");

        //TODO 把当前角色和权限刷到 redis中并生成 token
        String token = "";
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
