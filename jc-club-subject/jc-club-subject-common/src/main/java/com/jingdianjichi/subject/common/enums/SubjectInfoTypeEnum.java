package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

/**
 * 题目类型枚举类
 */
@Getter
public enum SubjectInfoTypeEnum {

    RADIO(1, "单选"),
    MULTIPLE(2, "多选"),
    JUDGE(3, "判断"),
    BRIEF(4, "简答");

    public final Integer code;

    public final String message;

    SubjectInfoTypeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static SubjectInfoTypeEnum getByCode(Integer code) {
        for (SubjectInfoTypeEnum resultCodeEnum : SubjectInfoTypeEnum.values()) {
            if(resultCodeEnum.code.equals(code)) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
