package com.jingdianjichi.subject.application.interceptor;

import com.jingdianjichi.subject.common.context.UserContext;
import com.jingdianjichi.subject.common.context.UserContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author jay
 * @since 2024/12/26 下午2:55
 */
@Component
public class UserContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginId = request.getHeader("loginId");
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
