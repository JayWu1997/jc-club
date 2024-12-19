package com.jingdianjichi.oss.config;

import com.jingdianjichi.oss.adapter.StorageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 存储服务选择
 * @author jay
 * @since 2024/12/18 下午9:00
 */
@Configuration
@Slf4j
@RefreshScope
public class StorageServiceSelectConfig {

    @Value("${storage.service.type}")
    private String storageServiceType;

    @Resource
    private StorageAdapter minioStorageAdapter;

    @Resource
    private StorageAdapter aliyunStorageAdapter;

    @Bean
    @RefreshScope
    public StorageAdapter storageAdapter() {
        if ("minio".equals(storageServiceType)) {
            return minioStorageAdapter;
        } else if ("aliyun".equals(storageServiceType)){
            return aliyunStorageAdapter;
        } else {
            throw new IllegalArgumentException("未指定存储服务厂商或指定的厂商不存在");
        }
    }
}
