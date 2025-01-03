package com.jingdianjichi.subject.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jingdianjichi.subject.application.interceptor.UserContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * webmvc 配置类，用于配置拦截器和消息转换器
 * @author jay
 * @since 2024/12/16 下午5:27
 */
@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    @Resource
    private UserContextInterceptor userContextInterceptor;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    /**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userContextInterceptor)
                .addPathPatterns("/**");
    }

    /**
     * 创建并配置一个MappingJackson2CborHttpMessageConverter实例，配置Jackson序列化属性
     *
     * @return MappingJackson2CborHttpMessageConverter实例
     */
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        // 创建一个ObjectMapper实例，用于JSON与对象的相互转换
        ObjectMapper objectMapper = new ObjectMapper();

        // 配置ObjectMapper，当序列化的对象为空时，不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 设置ObjectMapper的序列化包含属性，仅序列化非空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 创建并返回一个自定义ObjectMapper的MappingJackson2CborHttpMessageConverter实例
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
