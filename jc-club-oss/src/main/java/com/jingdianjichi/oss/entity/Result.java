package com.jingdianjichi.oss.entity;


import lombok.Data;

@Data
public class Result<T> {

    private Boolean success;

    private String code;

    private String message;

    private T data;

    private String reqUuid;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        Result result = new Result(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>(ResultCodeEnum.SUCCESS.getCode(), message, data);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String code, String message, T data) {
        Result<T> result = new Result<>(code, message, data);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result fail() {
        Result result = new Result(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMessage());
        result.setSuccess(false);
        return result;
    }

    public static Result fail(String message) {
        Result result = fail();
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail(String message, T data) {
        Result<T> result = new Result<>(ResultCodeEnum.FAIL.getCode(), message, data);
        result.setSuccess(false);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String code, String message, T data) {
        Result<T> result = new Result<>(code, message, data);
        result.setSuccess(false);
        result.setData(data);
        return result;
    }
}
