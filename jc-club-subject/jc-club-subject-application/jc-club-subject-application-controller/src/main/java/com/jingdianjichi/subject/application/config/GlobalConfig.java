package com.jingdianjichi.subject.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author jay
 * @since 2024/12/16 下午5:27
 */
@Configuration
public class GlobalConfig extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(mappingJackson2CborHttpMessageConverter());
    }

    /**
     * 创建并配置一个MappingJackson2CborHttpMessageConverter实例
     * 当序列化的对象为空时，不抛出异常
     *
     * @return MappingJackson2CborHttpMessageConverter实例，用于CBOR格式的HTTP消息转换
     */
    private MappingJackson2HttpMessageConverter mappingJackson2CborHttpMessageConverter() {
        // 创建一个ObjectMapper实例，用于JSON与对象的相互转换
        ObjectMapper objectMapper = new ObjectMapper();

        // 配置ObjectMapper，当序列化的对象为空时，不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 创建并返回一个自定义ObjectMapper的MappingJackson2CborHttpMessageConverter实例
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
