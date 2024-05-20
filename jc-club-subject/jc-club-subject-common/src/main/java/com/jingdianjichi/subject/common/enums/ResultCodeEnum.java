package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS("200", "成功"),
    FAIL("500","失败");

    public String code;

    public String desc;

    ResultCodeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
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
