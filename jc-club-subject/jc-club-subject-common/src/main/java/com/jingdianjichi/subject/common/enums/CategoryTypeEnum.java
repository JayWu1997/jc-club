package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

/**
 * 是否删除枚举类
 */
@Getter
public enum CategoryTypeEnum {

    PRIMARY(1, "岗位大类"),
    SECONDARY(0,"二级分类");

    public final Integer code;

    public final String message;

    CategoryTypeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static CategoryTypeEnum getByCode(Integer code) {
        for (CategoryTypeEnum resultCodeEnum : CategoryTypeEnum.values()) {
            if(resultCodeEnum.code.equals(code)) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
