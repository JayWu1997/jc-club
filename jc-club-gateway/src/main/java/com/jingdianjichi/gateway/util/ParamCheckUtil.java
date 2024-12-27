package com.jingdianjichi.gateway.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.jingdianjichi.gateway.entity.BusinessErrorEnum;
import com.jingdianjichi.gateway.exception.BusinessException;

import java.util.Collection;

/**
 * 参数检查工具类
 *
 * @author jay
 * @date 2024/06/18
 */
public class ParamCheckUtil {

    /**
     * 检查参数是否为空，为空则抛出异常
     *
     * @param obj            参数
     * @param businessErrorEnum 异常信息
     */
    public static void checkNotNull(Object obj, BusinessErrorEnum businessErrorEnum, String errorMsg) {
        if (ObjectUtil.isNull(obj)) {
            throw new BusinessException(businessErrorEnum, errorMsg);
        }
    }

    /**
     * 检查字符串是否为空，为空则抛出异常
     *
     * @param str            字符串
     * @param businessErrorEnum 异常信息
     */
    public static void checkStrNotEmpty(String str, BusinessErrorEnum businessErrorEnum, String errorMsg) {
        if (StrUtil.isBlank(str)) {
            throw new BusinessException(businessErrorEnum, errorMsg);
        }
    }

    /**
     * 检查集合是否为空，为空则抛出异常
     *
     * @param collection     集合
     * @param businessErrorEnum 异常信息
     */
    public static void checkCollNotEmpty(Collection<?> collection, BusinessErrorEnum businessErrorEnum, String errorMsg) {
        if (CollUtil.isEmpty(collection)) {
            throw new BusinessException(businessErrorEnum, errorMsg);
        }
    }
}
