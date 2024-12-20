package com.jingdianjichi.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 网关启动器
 * @author jay
 * @since 2024/12/20 下午6:08
 */
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(GatewayApplication.class, args);
    }
}