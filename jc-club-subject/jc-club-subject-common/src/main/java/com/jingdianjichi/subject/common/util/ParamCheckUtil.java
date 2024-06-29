package com.jingdianjichi.subject.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;

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
     * @param resultCodeEnum 异常信息
     */
    public static void checkNotNull(Object obj, ResultCodeEnum resultCodeEnum, String errorMsg) {
        if (ObjectUtil.isEmpty(obj)) {
            throw new BusinessException(resultCodeEnum, errorMsg);
        }
    }

    /**
     * 检查字符串是否为空，为空则抛出异常
     *
     * @param str            字符串
     * @param resultCodeEnum 异常信息
     */
    public static void checkStrNotEmpty(String str, ResultCodeEnum resultCodeEnum, String errorMsg) {
        if (StrUtil.isBlank(str)) {
            throw new BusinessException(resultCodeEnum, errorMsg);
        }
    }

    /**
     * 检查集合是否为空，为空则抛出异常
     *
     * @param collection     集合
     * @param resultCodeEnum 异常信息
     */
    public static void checkCollNotEmpty(Collection<?> collection, ResultCodeEnum resultCodeEnum, String errorMsg) {
        if (CollUtil.isEmpty(collection)) {
            throw new BusinessException(resultCodeEnum, errorMsg);
        }
    }
}
