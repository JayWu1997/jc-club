package com.jingdianjichi.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.jingdianjichi.gateway.entity.AuthPermission;
import com.jingdianjichi.gateway.entity.AuthRole;
import com.jingdianjichi.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;

    private static final Gson GSON = new Gson();

    private static final String AUTH_PERMISSION_PREFIX = "auth.permission";

    private static final String AUTH_ROLE_PREFIX = "auth.role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getStringListByKeyFromRedis(loginId.toString(), AUTH_PERMISSION_PREFIX);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getStringListByKeyFromRedis(loginId.toString(), AUTH_ROLE_PREFIX);
    }

    /**
     * 根据key从redis中获取string类型的list
     *
     * @param key    key
     * @param prefix 类型前缀
     * @return list
     */
    private List<String> getStringListByKeyFromRedis(String key, String prefix) {
        List<String> result = Collections.emptyList();
        // 验证输入参数
        if (StringUtils.isBlank(key) || StringUtils.isBlank(prefix)) {
            return result;
        }

        String authKey = redisUtil.buildKey(prefix, key);
        String jsonStr = redisUtil.get(authKey);

        if (StringUtils.isBlank(jsonStr)) {
            return result;
        }

        if (AUTH_ROLE_PREFIX.equals(prefix)) {
            List<AuthRole> authRoleList = GSON.fromJson(jsonStr, new com.google.gson.reflect.TypeToken<List<AuthRole>>() {
            }.getType());
            result = authRoleList.stream().map(AuthRole::getRoleKey).collect(Collectors.toList());
        } else if (AUTH_PERMISSION_PREFIX.equals(prefix)) {
            List<AuthPermission> authPermissionList = GSON.fromJson(jsonStr, new com.google.gson.reflect.TypeToken<List<AuthPermission>>() {
            }.getType());
            result = authPermissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        } else {
            result = GSON.fromJson(jsonStr, new com.google.gson.reflect.TypeToken<List<String>>() {
            }.getType());
        }

        return result;
    }

}
