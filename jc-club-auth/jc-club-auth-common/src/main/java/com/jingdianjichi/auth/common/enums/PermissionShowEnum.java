package com.jingdianjichi.auth.common.enums;

import lombok.Getter;

/**
 * 权限展示隐藏枚举类
 */
@Getter
public enum PermissionShowEnum {

    ABSENT(1, "隐藏"),
    PRESENT(0,"展示");

    private final Integer code;

    private final String message;

    PermissionShowEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static PermissionShowEnum getByCode(Integer code) {
        for (PermissionShowEnum permissionShowEnum : PermissionShowEnum.values()) {
            if(permissionShowEnum.code.equals(code)) {
                return permissionShowEnum;
            }
        }
        return null;
    }
}
