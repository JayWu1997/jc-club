package com.jingdianjichi.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.gson.Gson;
import com.jingdianjichi.auth.common.enums.IsDeletedEnum;
import com.jingdianjichi.auth.common.enums.ResultCodeEnum;
import com.jingdianjichi.auth.common.enums.UserStatusEnum;
import com.jingdianjichi.auth.common.util.ParamCheckUtil;
import com.jingdianjichi.auth.domain.converter.AuthUserBOConverter;
import com.jingdianjichi.auth.domain.entity.AuthUserBO;
import com.jingdianjichi.auth.domain.redis.RedisUtil;
import com.jingdianjichi.auth.domain.service.AuthUserDomainService;
import com.jingdianjichi.auth.infra.base.entity.*;
import com.jingdianjichi.auth.infra.base.service.AuthRoleService;
import com.jingdianjichi.auth.infra.base.service.AuthUserRoleService;
import com.jingdianjichi.auth.infra.base.service.AuthUserService;
import com.jingdianjichi.auth.infra.base.service.impl.AuthPermissionServiceImpl;
import com.jingdianjichi.auth.infra.base.service.impl.AuthRolePermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Resource
    private RedisUtil redisUtil;

    private static final Gson GSON = new Gson();

    private static final String AUTH_PERMISSION_PREFIX = "auth.permission";

    private static final String AUTH_ROLE_PREFIX = "auth.role";

    /**
     * 密码加密盐
     */
    private static final String SALT = "jingdianjichi";
    @Autowired
    private AuthRolePermissionServiceImpl authRolePermissionService;
    @Autowired
    private AuthPermissionServiceImpl authPermissionService;

    /**
     * 注册
     *
     * @param authUserBO 用户信息
     * @return token
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaTokenInfo register(AuthUserBO authUserBO) {
        // 插入用户信息
        AuthUser user = AuthUserBOConverter.INSTANCE.convertBo2Entity(authUserBO);
        user.setStatus(UserStatusEnum.ENABLE.getCode());
        user.setPassword(SaSecureUtil.md5BySalt(user.getPassword(), SALT));
        user.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        authUserService.insert(user);
        ParamCheckUtil.checkNotNull(user.getId(), ResultCodeEnum.PARAM_ERROR, "角色添加失败！");

        // 获取默认角色
        AuthRole defaultRole = new AuthRole();
        defaultRole.setRoleKey(DEFAULT_ROLE_KEY);
        defaultRole = Optional.ofNullable(authRoleService.queryAll(defaultRole))
                .map(authRoles -> authRoles.get(0))
                .orElse(null);
        ParamCheckUtil.checkNotNull(defaultRole, ResultCodeEnum.PARAM_ERROR, "角色添加失败！role key" + DEFAULT_ROLE_KEY + "所代表的角色不存在!");

        // 建立角色关联
        AuthUserRole userRole = new AuthUserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(defaultRole.getId());
        userRole.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        authUserRoleService.insert(userRole);
        ParamCheckUtil.checkNotNull(userRole.getId(), ResultCodeEnum.PARAM_ERROR, "角色添加失败！");

        // 把当前角色刷到 redis中
        String roleKey = redisUtil.buildKey(AUTH_ROLE_PREFIX, user.getUserName());
        ArrayList<AuthRole> roleList = CollectionUtil.newArrayList(defaultRole);
        redisUtil.set(roleKey, GSON.toJson(roleList));

        // 把当前用户所拥有的权限刷到 redis中
        AuthRolePermission rolePermission = new AuthRolePermission();
        rolePermission.setRoleId(defaultRole.getId());
        List<AuthRolePermission> rolePermissionList = authRolePermissionService.queryAll(rolePermission);
        ParamCheckUtil.checkCollNotEmpty(rolePermissionList, ResultCodeEnum.PARAM_ERROR, "角色添加失败！");

        List<Long> permissionIdList = rolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        List<AuthPermission> permissionList = authPermissionService.queryBatchByIds(permissionIdList);
        ParamCheckUtil.checkCollNotEmpty(permissionList, ResultCodeEnum.PARAM_ERROR, "角色添加失败！");
        String permissionKey = redisUtil.buildKey(AUTH_PERMISSION_PREFIX, user.getUserName());
        redisUtil.set(permissionKey, GSON.toJson(permissionList));

        StpUtil.login(user.getUserName());
        return StpUtil.getTokenInfo();
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
