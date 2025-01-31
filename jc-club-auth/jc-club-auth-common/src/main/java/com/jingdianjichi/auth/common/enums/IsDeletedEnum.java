package com.jingdianjichi.auth.common.enums;

import lombok.Getter;

/**
 * 是否删除枚举类
 */
@Getter
public enum IsDeletedEnum {

    DELETED(1, "已删除"),
    NOT_DELETED(0,"未删除");

    private final Integer code;

    private final String message;

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
