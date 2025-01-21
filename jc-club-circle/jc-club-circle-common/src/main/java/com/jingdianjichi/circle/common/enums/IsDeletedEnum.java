package com.jingdianjichi.circle.common.enums;

import lombok.Getter;

/**
 * 是否删除枚举类
 */
@Getter
public enum IsDeletedEnum {

    DELETED(1, "已删除"),
    NOT_DELETED(0,"未删除");

    public final Integer code;

    public final String message;

    IsDeletedEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static IsDeletedEnum getByCode(Integer code) {
        for (IsDeletedEnum resultCodeEnum : IsDeletedEnum.values()) {
            if(resultCodeEnum.code.equals(code)) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
