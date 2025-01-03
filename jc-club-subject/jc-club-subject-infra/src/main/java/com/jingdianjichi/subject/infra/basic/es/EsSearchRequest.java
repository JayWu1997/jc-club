package com.jingdianjichi.subject.infra.basic.es;

import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 * es 搜索请求
 * @author jay
 * @since 2024/12/29 上午9:20
 */
@Data
public class EsSearchRequest {

    /**
     * 搜索条件
     */
    private BoolQueryBuilder boolQueryBuilder;

    /**
     * 搜索字段
     */
    private String[] fields;

    /**
     * 分页页码
     */
    private Integer from;

    /**
     * 分页大小
     */
    private Integer size;

    /**
     * 是否需要快照
     */
    private Boolean needScroll;

    /**
     * 快照缓存时间
     */
    private Long minutes;

    /**
     * 排序字段名
     */
    private String sortFieldName;

    /**
     * 排序方式
     */
    private SortOrder sortOrder;

    /**
     * 高亮 Builder
     */
    private HighlightBuilder highlightBuilder;
}
