package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

/**
 * 是否删除枚举类
 */
@Getter
public enum SubjectLikedStatusEnum {

    LIKED(1, "点赞"),
    UNLIKED(0,"取消点赞");

    public final Integer code;

    public final String message;

    SubjectLikedStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static SubjectLikedStatusEnum getByCode(Integer code) {
        for (SubjectLikedStatusEnum resultCodeEnum : SubjectLikedStatusEnum.values()) {
            if(resultCodeEnum.code.equals(code)) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
