package com.jingdianjichi.subject.application.config;

import com.jingdianjichi.subject.application.interceptor.UserContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * webmvc 配置类，用于配置拦截器和消息转换器
 * @author jay
 * @since 2024/12/16 下午5:27
 */
@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    /**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserContextInterceptor())
                .addPathPatterns("/**");
    }
}
