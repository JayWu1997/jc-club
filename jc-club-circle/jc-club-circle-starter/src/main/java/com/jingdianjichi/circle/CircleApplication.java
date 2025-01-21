package com.jingdianjichi.circle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jay
 * @since 2025/1/21 下午5:30
 */
@SpringBootApplication
@ComponentScan("com.jingdianjichi")
public class CircleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CircleApplication.class, args);
    }
}
