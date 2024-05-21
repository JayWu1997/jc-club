package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS("200", "成功"),
    FAIL("500","失败"),
    PARAM_ERROR("5001", "参数错误");

    public final String code;

    public final String message;

    ResultCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public static ResultCodeEnum getByCode(String code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if(resultCodeEnum.code.equals(code)) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
