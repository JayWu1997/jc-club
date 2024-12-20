package com.jingdianjichi.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.jingdianjichi.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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
     * @param key key
     * @param prefix 类型前缀
     * @return list
     */
    private List<String> getStringListByKeyFromRedis(String key, String prefix) {
        // 验证输入参数
        if (StringUtils.isBlank(key) || StringUtils.isBlank(prefix)) {
            return Collections.emptyList();
        }

        String authKey = redisUtil.buildKey(prefix, key);
        String token = redisUtil.get(authKey);

        if (StringUtils.isBlank(token)) {
            return Collections.emptyList();
        }

        return GSON.fromJson(token, new com.google.gson.reflect.TypeToken<List<String>>() {}.getType());
    }

}
