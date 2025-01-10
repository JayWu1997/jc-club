package com.jingdianjichi.practice.server.config.feign;

import com.jingdianjichi.practice.server.common.context.UserContextHolder;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author jay
 * @since 2024/12/27 下午12:49
 */
@Configuration
public class OpenFeignConfig {

    /**
     * 存储用户名的 header key
     */
    public static final String LOGIN_ID = "loginId";

    /**
     * 设置feign日志级别
     * NONE 不打日志，默认值
     * BASIC 只记录 method、url、响应码，执行时间
     * HEADERS 只记录请求和响应的 header
     * FULL 全部都记录
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Feign拦截器，每次调用其他服务时，将用户名放入 header中
     */
    @Component
    static class FeignInterceptor implements RequestInterceptor {

        /**
         * 拦截方法，再此方法中完成请求头的封装。
         */
        @Override
        public void apply(RequestTemplate requestTemplate) {
            //传递令牌
            String userName = UserContextHolder.getUserContext().getUserName();
            requestTemplate.header(LOGIN_ID, userName);
        }
    }
}
