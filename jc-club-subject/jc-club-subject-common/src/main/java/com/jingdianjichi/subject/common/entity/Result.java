package com.jingdianjichi.subject.common.entity;

import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result {

    private Boolean success;

    private String code;

    private String message;

    private Object data;

    private String reqUuid;

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result success(T data) {
        Result result = success();
        result.setData(data);
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getMessage());
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
