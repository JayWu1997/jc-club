package com.jingdianjichi.subject.application.controller.advice;

import com.jingdianjichi.subject.common.entity.Result;
import com.jingdianjichi.subject.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Result handleException(Throwable e) {
        log.error(e.getMessage(), e);
        return Result.fail("操作失败");
    }
}
