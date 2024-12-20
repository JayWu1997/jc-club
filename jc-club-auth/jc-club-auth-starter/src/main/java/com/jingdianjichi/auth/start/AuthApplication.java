package com.jingdianjichi.auth.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jay
 * @since 2024/12/20 下午3:55
 */
@SpringBootApplication
@ComponentScan("com.jingdianjichi")
public class AuthApplication {
    public static void main(String[] args) {
         SpringApplication.run(AuthApplication.class, args);
    }
}
