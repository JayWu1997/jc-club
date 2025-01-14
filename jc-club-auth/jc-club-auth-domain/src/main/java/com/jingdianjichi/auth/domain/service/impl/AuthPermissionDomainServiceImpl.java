package com.jingdianjichi.auth.domain.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jingdianjichi.auth.common.enums.BusinessErrorEnum;
import com.jingdianjichi.auth.common.enums.IsDeletedEnum;
import com.jingdianjichi.auth.common.enums.PermissionShowEnum;
import com.jingdianjichi.auth.common.enums.PermissionStatusEnum;
import com.jingdianjichi.auth.common.util.ParamCheckUtil;
import com.jingdianjichi.auth.domain.converter.AuthPermissionBOConverter;
import com.jingdianjichi.auth.domain.entity.AuthPermissionBO;
import com.jingdianjichi.auth.domain.redis.RedisUtil;
import com.jingdianjichi.auth.domain.service.AuthPermissionDomainService;
import com.jingdianjichi.auth.infra.base.entity.AuthPermission;
import com.jingdianjichi.auth.infra.base.service.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限领域服务实现类
 *
 * @author jay
 * @since 2024/12/21 下午9:01
 */
@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService permissionService;
    
    @Resource
    private RedisUtil redisUtil;

    /**
     * 用户权限 redis key 前缀
     */
    private static final String REDIS_KEY_AUTH_PERMISSION_PREFIX = "auth.permission";

    private static final Gson GSON = new Gson();

    /**
     * 添加权限
     *
     * @param authPermissionBO 权限信息
     */
    @Override
    public void add(AuthPermissionBO authPermissionBO) {
        // 1.插入数据库
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBo2Entity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        ParamCheckUtil.checkNotFalse(permissionService.insert(authPermission) > 0, BusinessErrorEnum.FAIL, "添加权限失败");
        //TODO 2.向redis推送
    }

    /**
     * 更新权限信息
     *
     * @param authPermissionBO 权限信息
     */
    @Override
    public void update(AuthPermissionBO authPermissionBO) {
        // 1.更新权限
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBo2Entity(authPermissionBO);
        ParamCheckUtil.checkNotFalse(permissionService.update(authPermission) > 0, BusinessErrorEnum.FAIL, "更新权限失败");
        //TODO 2.更新redis
    }

    /**
     * 删除权限信息
     *
     * @param id 权限id
     */
    @Override
    public void delete(Long id) {
        // 1.更新权限
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(id);
        authPermission.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        ParamCheckUtil.checkNotFalse(permissionService.update(authPermission) > 0, BusinessErrorEnum.FAIL, "删除权限失败");

        //TODO 2.更新redis
    }

    /**
     * 启用或禁用权限
     *
     * @param authPermissionBO 权限信息
     */
    @Override
    public void enableOrDisable(AuthPermissionBO authPermissionBO) {
        // 1.更新权限
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setStatus(authPermissionBO.getStatus());
        String errMsg = PermissionStatusEnum.ENABLE.getCode().equals(authPermissionBO.getStatus()) ? "启用权限失败" : "禁用权限失败";
        ParamCheckUtil.checkNotFalse(permissionService.update(authPermission) > 0, BusinessErrorEnum.FAIL, errMsg);
        //TODO 2.更新redis
    }

    /**
     * 显示或隐藏权限
     *
     * @param authPermissionBO 权限信息
     */
    @Override
    public void presentOrAbsent(AuthPermissionBO authPermissionBO) {
        // 1.更新权限
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setShow(authPermissionBO.getShow());
        String errMsg = PermissionShowEnum.PRESENT.getCode().equals(authPermissionBO.getShow()) ? "显示权限失败" : "隐藏权限失败";
        ParamCheckUtil.checkNotFalse(permissionService.update(authPermission) > 0, BusinessErrorEnum.FAIL, errMsg);
        //TODO 2.更新redis
    }

    /**
     * 获取指定用户的权限列表
     *
     * @param userName 用户名
     * @return 权限列表
     */
    @Override
    public List<String> getPermission(String userName) {
        String permissionKey = redisUtil.buildKey(REDIS_KEY_AUTH_PERMISSION_PREFIX, userName);
        String permissionListJsonStr = redisUtil.get(permissionKey);
        if(StrUtil.isEmpty(permissionListJsonStr)) {
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = GSON.fromJson(permissionListJsonStr, new TypeToken<List<AuthPermission>>() {
        }.getType());
        return permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
    }
}
