package com.jingdianjichi.auth.common.enums;

import lombok.Getter;

/**
 * 权限启用禁用枚举类
 */
@Getter
public enum PermissionStatusEnum {

    DISABLE(1, "禁用"),
    ENABLE(0,"启用");

    private final Integer code;

    private final String message;

    PermissionStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static PermissionStatusEnum getByCode(Integer code) {
        for (PermissionStatusEnum permissionStatusEnum : PermissionStatusEnum.values()) {
            if(permissionStatusEnum.code.equals(code)) {
                return permissionStatusEnum;
            }
        }
        return null;
    }
}
