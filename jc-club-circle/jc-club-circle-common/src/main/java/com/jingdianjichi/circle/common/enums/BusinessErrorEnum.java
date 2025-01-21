package com.jingdianjichi.circle.common.enums;

import lombok.Getter;

@Getter
public enum BusinessErrorEnum {

    SUCCESS("200", "成功"),
    FAIL("500","失败"),
    PARAM_ERROR("5001", "参数错误");

    public final String code;

    public final String message;

    BusinessErrorEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public static BusinessErrorEnum getByCode(String code) {
        for (BusinessErrorEnum businessErrorEnum : BusinessErrorEnum.values()) {
            if(businessErrorEnum.code.equals(code)) {
                return businessErrorEnum;
            }
        }
        return null;
    }
}
