package com.jingdianjichi.subject.infra.basic.es;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 从配置文件中获取ES集群配置信息
 * @author jay
 * @since 2024/12/29 上午9:11
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "es")
public class EsClusterConfigProperties {

    private List<EsClusterInfo> cluster = new ArrayList<EsClusterInfo>();

    /**
     * ES 集群类
     * @author jay
     * @since 2024/12/28 下午1:54
     */
    @Data
    static class EsClusterInfo implements Serializable {

        /**
         * es集群名称
         */
        private String name;

        /**
         * es节点地址
         */
        private String[] addr;
    }
}
