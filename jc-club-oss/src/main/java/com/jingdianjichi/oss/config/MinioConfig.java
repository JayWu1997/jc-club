package com.jingdianjichi.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jay
 * @since 2024/12/18 下午7:33
 */
@Configuration
public class MinioConfig {

    /**
     * minio url
     */
    @Value("${minio.url}")
    private String url;

    /**
     * minio 账户
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio 密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 创建并配置MinioClient实例
     * <p>
     * 本方法用于初始化MinioClient对象，该对象用于与Minio服务器进行交互
     * 通过使用构建者模式，本方法配置了Minio服务器的URL以及访问凭证
     *
     * @return MinioClient实例，用于执行与Minio服务器的各种操作
     */
    @Bean
    public MinioClient minioClient() {
        // 使用构建者模式开始构建MinioClient实例
        return MinioClient.builder()
                // 设置Minio服务器的访问地址
                .endpoint(url)
                // 设置访问Minio服务器的凭证，包括访问密钥和秘密密钥
                .credentials(accessKey, secretKey)
                // 完成MinioClient实例的构建并返回
                .build();
    }
}
