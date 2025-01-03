package com.jingdianjichi.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * es通用文档数据，查询后返回的数据
 * @author jay
 * @since 2024/12/29 上午9:25
 */
@Data
public class EsSourceData implements Serializable {

    private String docId;

    private Map<String, Object> data;
}
