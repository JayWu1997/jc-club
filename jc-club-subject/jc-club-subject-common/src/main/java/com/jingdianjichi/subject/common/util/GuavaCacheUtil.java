package com.jingdianjichi.subject.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * GuavaCacheUtil
 *
 * @author jay
 * @since 2024/12/27 下午1:26
 */
public class GuavaCacheUtil {

    private static final Cache<String, String> cache =
            CacheBuilder.newBuilder()
                    .maximumSize(1000)
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build();

    /**
     * 从缓存获取一个指定类型的 List，如果缓存中不存在该 key，返回 null
     *
     * @param key   缓存 key
     * @param clazz 返回的结果类型
     * @param <T>   泛型
     * @return list 缓存命中
     *         null 缓存中不存在该 key，返回 null
     */
    public static <T> List<T> getListFromCache(String key, Class<T> clazz) {
        List<T> resultList = null;
        String strValue = cache.getIfPresent(key);
        if (ObjectUtil.isNotNull(strValue)) {
            resultList = JSON.parseArray(strValue, clazz);
        }
        return resultList;
    }

    /**
     * 从缓存获取一个指定类型的对象，如果缓存中不存在该 key，返回 null
     *
     * @param key   缓存 key
     * @param clazz 返回的结果类型
     * @param <T>   泛型
     * @return result 缓存命中
     *         null   缓存中不存在该 key，返回 null
     */
    public static <T> T getFromCache(String key, Class<T> clazz) {
        T result = null;
        String strValue = cache.getIfPresent(key);
        if (ObjectUtil.isNotNull(strValue)) {
            result = JSON.parseObject(strValue, clazz);
        }
        return result;
    }

    /**
     * 添加缓存
     * @param key key
     * @param value value
     */
    public static void put(String key, Object value) {
        cache.put(key, JSON.toJSONString(value));
    }
}
