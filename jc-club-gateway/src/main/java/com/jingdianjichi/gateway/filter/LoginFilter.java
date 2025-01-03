package com.jingdianjichi.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import com.jingdianjichi.gateway.entity.BusinessErrorEnum;
import com.jingdianjichi.gateway.util.ParamCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author jay
 * @since 2024/12/26 下午2:27
 */
@Configuration
public class LoginFilter implements GlobalFilter {
    private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

    /**
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        try {
            String loginId = StpUtil.getLoginId().toString();
            ParamCheckUtil.checkStrNotEmpty(loginId, BusinessErrorEnum.FAIL, "用户未登录");
            mutate.header("loginId", loginId);
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("未能获取到loginId, path:{}", request.getPath(), ex);
            }
        }
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
