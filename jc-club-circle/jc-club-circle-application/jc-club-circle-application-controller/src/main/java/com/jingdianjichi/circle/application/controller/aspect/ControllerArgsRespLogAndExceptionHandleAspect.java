package com.jingdianjichi.circle.application.controller.aspect;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.jingdianjichi.circle.api.resp.Result;
import com.jingdianjichi.circle.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * controller 入参出参打印、异常处理 切面
 */
@Slf4j
@Aspect //定义切面类
@Component
public class ControllerArgsRespLogAndExceptionHandleAspect {

    /**
     * 定义切点
     */
    @Pointcut("execution(* com.jingdianjichi.circle.application.controller.controller.*.*(..))")
    public void point(){
    }

    /**
     * 定义环绕增强
     */
    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取入参
        Object[] args = joinPoint.getArgs();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取方法所在类的类名
        String classFullName = joinPoint.getSignature().getDeclaringType().getName();
        // 打印入参
        if (ArrayUtils.isNotEmpty(args)){
            if (log.isInfoEnabled()) {
                log.info("\n{}.{}.args:{}", classFullName, methodName, JSON.toJSON(args));
            }
        }
        // controller 异常消息返回处理
        Object response;
        try {
            response = joinPoint.proceed(args);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
            response = Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
            response = Result.fail("系统异常！");
        }
        // 打印出参
        if (log.isInfoEnabled()) {
            log.info("\n{}.{}.response:{}", classFullName, methodName, JSON.toJSON(response));
        }
        //将返回值返回，方法的返回值是around的返回值，而不是原来的返回值
        return response;
    }
}

