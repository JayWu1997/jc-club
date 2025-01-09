package com.jingdianjichi.subject.api.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    
    private static final String SUCCESS_MSG = "success";
    private static final String SUCCESS_CODE = "200";

    private static final String FAIL_MSG = "fail";
    private static final String FAIL_CODE = "500";

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
        Result result = new Result(SUCCESS_CODE, "");
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(SUCCESS_CODE, SUCCESS_MSG, data);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>(SUCCESS_CODE, message, data);
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
        Result result = new Result(FAIL_CODE, FAIL_MSG);
        result.setSuccess(false);
        return result;
    }

    public static Result fail(String message) {
        Result result = fail();
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail(String message, T data) {
        Result<T> result = new Result<>(FAIL_CODE, message, data);
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
