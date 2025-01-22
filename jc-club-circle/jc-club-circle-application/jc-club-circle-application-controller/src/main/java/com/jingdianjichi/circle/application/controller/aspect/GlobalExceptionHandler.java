package com.jingdianjichi.circle.application.controller.aspect;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.circle.api.resp.Result;
import com.jingdianjichi.circle.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author jay
 * @since 2025/1/22 下午2:35
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<Object> handleBusinessException(BusinessException e) {
        if (log.isErrorEnabled()) {
            log.error("业务异常", e);
        }
        Result<Object> resp = Result.fail(e.getMessage());
        // 打印出参
        if (log.isInfoEnabled()) {
            log.info("resp: {}", JSON.toJSONString(resp));
        }
        return resp;
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        if (log.isErrorEnabled()) {
            log.error("系统异常", e);
        }
        Result<Object> resp = Result.fail("系统异常");
        // 打印出参
        if (log.isInfoEnabled()) {
            log.info("resp: {}", JSON.toJSONString(resp));
        }
        return resp;
    }
}
