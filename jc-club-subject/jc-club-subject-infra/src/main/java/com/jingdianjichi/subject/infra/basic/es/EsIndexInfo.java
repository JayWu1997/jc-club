package com.jingdianjichi.subject.infra.basic.es;

import lombok.Data;

/**
 * es索引信息
 * @author jay
 * @since 2024/12/29 上午9:19
 */
@Data
public class EsIndexInfo {

    private String clusterName;

    private String indexName;
}
