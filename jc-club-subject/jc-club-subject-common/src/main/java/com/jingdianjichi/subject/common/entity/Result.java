package com.jingdianjichi.subject.common.entity;

import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result {

    private Boolean success;

    private Integer code;

    private String message;

    private Object data;

    private String reqUuid;

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        return result;
    }

    public static <T> Result ok(T data) {
        Result result = ok();
        result.setData(data);
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        return result;
    }

    public static <T> Result fail(String message) {
        Result result = fail();
        result.setMessage(message);
        return result;
    }

    public static <T> Result fail(String message, T data) {
        Result result = fail();
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
