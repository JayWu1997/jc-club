package com.jingdianjichi.auth.common.enums;

import lombok.Getter;

/**
 * 是否删除枚举类
 */
@Getter
public enum UserEnableEnum {

    DISABLE(1, "禁用"),
    ENABLE(0,"启用");

    private final Integer code;

    private final String message;

    UserEnableEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static UserEnableEnum getByCode(Integer code) {
        for (UserEnableEnum userEnableEnum : UserEnableEnum.values()) {
            if(userEnableEnum.code.equals(code)) {
                return userEnableEnum;
            }
        }
        return null;
    }
}
