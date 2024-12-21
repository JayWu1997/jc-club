package com.jingdianjichi.auth.common.enums;

import lombok.Getter;

/**
 * 用户启用禁用枚举类
 */
@Getter
public enum UserStatusEnum {

    DISABLE(1, "禁用"),
    ENABLE(0,"启用");

    private final Integer code;

    private final String message;

    UserStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static UserStatusEnum getByCode(Integer code) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if(userStatusEnum.code.equals(code)) {
                return userStatusEnum;
            }
        }
        return null;
    }
}
