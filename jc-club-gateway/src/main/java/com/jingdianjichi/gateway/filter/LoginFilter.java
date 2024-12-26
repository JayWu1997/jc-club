package com.jingdianjichi.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import com.jingdianjichi.gateway.entity.ResultCodeEnum;
import com.jingdianjichi.gateway.util.ParamCheckUtil;
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
    /**
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String loginId = StpUtil.getLoginId().toString();
        ParamCheckUtil.checkStrNotEmpty(loginId, ResultCodeEnum.FAIL, "用户未登录");
        mutate.header("loginId", loginId);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
