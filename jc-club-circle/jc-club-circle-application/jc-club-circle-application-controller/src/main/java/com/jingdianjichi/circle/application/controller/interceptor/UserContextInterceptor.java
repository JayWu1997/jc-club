package com.jingdianjichi.circle.application.controller.interceptor;


import com.jingdianjichi.circle.common.context.UserContext;
import com.jingdianjichi.circle.common.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author jay
 * @since 2024/12/26 下午2:55
 */
@Slf4j
public class UserContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginId = request.getHeader("loginId");
        if(log.isInfoEnabled()){
            log.info("用户{}访问接口:{}", loginId, request.getRequestURI());
        }
        UserContext userContext = new UserContext();
        userContext.setUserName(loginId);
        UserContextHolder.setUserContext(userContext);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.removeUserContext();
    }
}
