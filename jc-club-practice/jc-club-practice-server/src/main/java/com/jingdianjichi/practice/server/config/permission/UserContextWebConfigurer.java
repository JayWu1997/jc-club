package com.jingdianjichi.practice.server.config.permission;

import com.jingdianjichi.practice.server.common.context.UserContext;
import com.jingdianjichi.practice.server.common.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * webmvc 配置类，用于配置拦截器和消息转换器
 *
 * @author jay
 * @since 2024/12/16 下午5:27
 */
@Configuration
public class UserContextWebConfigurer implements WebMvcConfigurer {

    /**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserContextInterceptor())
                .addPathPatterns("/**");
    }

    @Slf4j
    private static class UserContextInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String loginId = request.getHeader("loginId");
            if (log.isInfoEnabled()) {
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
}
