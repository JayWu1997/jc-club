package com.jingdianjichi.subject.infra.basic.es;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * es 客户端
 *
 * @author jay
 * @since 2024/12/29 上午9:28
 */
@Slf4j
@Component
public class EsRestClient {

    private static final Map<String, RestHighLevelClient> clientMap = new HashMap<>();

    @Resource
    private EsClusterConfigProperties esClusterConfigProperties;

    private static final RequestOptions COMMON_OPTIONS = RequestOptions.DEFAULT;


    @PostConstruct
    public void initialize() {
        List<EsClusterConfigProperties.EsClusterInfo> esClusterInfos = esClusterConfigProperties.getCluster();

        esClusterInfos.forEach(esClusterInfo -> {
            try {
                RestHighLevelClient client = initEsRestClient(esClusterInfo);
                if (ObjectUtil.isNull(client)) {
                    if (log.isErrorEnabled()) {
                        log.error("初始化es客户端失败，cluster:{}", esClusterInfo);
                    }
                } else {
                    if (log.isInfoEnabled()) {
                        log.info("初始化es客户端成功，cluster:{}", esClusterInfo);
                    }
                    clientMap.put(esClusterInfo.getName(), client);
                }
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("初始化es客户端失败，cluster:{}", esClusterInfo, e);
                }
            }
        });
    }

    private static RestHighLevelClient initEsRestClient(EsClusterConfigProperties.EsClusterInfo esClusterInfo) throws IOException {
        List<HttpHost> httpHostList = new ArrayList<>(esClusterInfo.getAddr().length);
        String clusterName = esClusterInfo.getName();
        String[] clusterAddrs = esClusterInfo.getAddr();
        for (String addr : clusterAddrs) {
            int splitIndex = addr.lastIndexOf(":");
            String host = addr.substring(0, splitIndex);
            String port = addr.substring(splitIndex + 1);
            if (StrUtil.isNotBlank(host) && StrUtil.isNotBlank(port)) {
                httpHostList.add(new HttpHost(host, Integer.parseInt(port)));
            }
        }
        RestClientBuilder builder = RestClient.builder(httpHostList.toArray(new HttpHost[0]));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        client.ping(RequestOptions.DEFAULT);
        return client;
    }

    public Boolean insertDoc(EsIndexInfo indexInfo, EsSourceData sourceData) {
        IndexRequest indexRequest = new IndexRequest(indexInfo.getIndexName());
        indexRequest.id(sourceData.getDocId());
        indexRequest.source(sourceData.getData());
        try {
            RestHighLevelClient client = getClient(indexInfo.getClusterName());
            client.index(indexRequest, COMMON_OPTIONS);
            return Boolean.TRUE;
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("插入文档失败，indexInfo:{},sourceData:{}", indexInfo, sourceData, e);
            }
        }
        return Boolean.FALSE;
    }

    private RestHighLevelClient getClient(String clusterName) {
        return clientMap.get(clusterName);
    }

    public Boolean deleteAllDocs(EsIndexInfo indexInfo, EsSourceData sourceData) {
        try {
            DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(indexInfo.getIndexName());
            deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());
            BulkByScrollResponse response = getClient(indexInfo.getClusterName()).deleteByQuery(deleteByQueryRequest, COMMON_OPTIONS);
            long deletedNum = response.getDeleted();
            if (log.isInfoEnabled()) {
                log.info("删除文档成功，indexInfo:{},deletedNum:{}", indexInfo, deletedNum);
            }
            return Boolean.TRUE;
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("删除文档失败，indexInfo:{},sourceData:{}", indexInfo, sourceData, e);
            }
        }
        return Boolean.FALSE;
    }

    public Boolean deleteById(EsIndexInfo indexInfo, String docId) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(indexInfo.getIndexName());
            deleteRequest.id(docId);
            DeleteResponse response = getClient(indexInfo.getClusterName()).delete(deleteRequest, COMMON_OPTIONS);
            if (log.isInfoEnabled()) {
                log.info("删除文档成功，indexInfo:{},docId:{},response:{}", indexInfo, docId, response);
            }
            return Boolean.TRUE;
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("删除文档失败，indexInfo:{},docId:{}", indexInfo, docId, e);
            }
        }
        return Boolean.FALSE;
    }

    public boolean existsById(EsIndexInfo indexInfo, String docId) {
        try {
            GetRequest getRequest = new GetRequest(indexInfo.getIndexName());
            getRequest.id(docId);
            return getClient(indexInfo.getClusterName()).exists(getRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("判断文档是否存在失败，indexInfo:{},docId:{}", indexInfo, docId, e);
            }
        }
        return Boolean.FALSE;
    }

    public Map<String, Object> getDocById(EsIndexInfo indexInfo, String docId) {
        try {
            GetRequest getRequest = new GetRequest(indexInfo.getIndexName());
            getRequest.id(docId);
            GetResponse response = getClient(indexInfo.getClusterName()).get(getRequest, COMMON_OPTIONS);
            Map<String, Object> source = response.getSource();
            if (log.isInfoEnabled()) {
                log.info("获取文档成功，indexInfo:{},docId:{},response:{}", indexInfo, docId, response);
            }
            return source;
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("获取文档失败，indexInfo:{},docId:{}", indexInfo, docId, e);
            }
        }
        return null;
    }

    public Map<String, Object> getDocById(EsIndexInfo indexInfo, String docId,
                                          String[] fields) {
        try {
            GetRequest getRequest = new GetRequest(indexInfo.getIndexName());
            getRequest.id(docId);
            FetchSourceContext context = new FetchSourceContext(true, fields, null);
            getRequest.fetchSourceContext(context);
            GetResponse response = getClient(indexInfo.getClusterName()).get(getRequest, COMMON_OPTIONS);
            Map<String, Object> source = response.getSource();
            if (log.isInfoEnabled()) {
                log.info("获取文档成功，indexInfo:{},docId:{},response:{}", indexInfo, docId, response);
            }
            return source;
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("获取文档失败，indexInfo:{},docId:{}", indexInfo, docId, e);
            }
        }
        return null;
    }

    public SearchResponse searchWithTermQuery(EsIndexInfo indexInfo, EsSearchRequest searchRequest) {
        try {
            BoolQueryBuilder boolQueryBuilder = searchRequest.getBoolQueryBuilder();
            String[] fields = searchRequest.getFields();
            Integer from = searchRequest.getFrom();
            Integer size = searchRequest.getSize();
            Long minutes = searchRequest.getMinutes();
            Boolean needScroll = searchRequest.getNeedScroll();
            String sortFieldName = searchRequest.getSortFieldName();
            SortOrder sortOrder = searchRequest.getSortOrder();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder);
            searchSourceBuilder.fetchSource(fields, null);
            if (ObjectUtil.isNotNull(searchRequest.getHighlightBuilder())) {
                searchSourceBuilder.highlighter(searchRequest.getHighlightBuilder());
            }
            if (ObjectUtil.isNotNull(from)) {
                searchSourceBuilder.from(from);
            }
            if (ObjectUtil.isNotNull(size)) {
                searchSourceBuilder.size(size);
            }
            if (ObjectUtil.isNotNull(sortFieldName)) {
                searchSourceBuilder.sort(sortFieldName, sortOrder);
            }

            SearchRequest searchRequest1 = new SearchRequest();
            searchRequest1.searchType(SearchType.DEFAULT);
            searchRequest1.indices(indexInfo.getIndexName());
            searchRequest1.source(searchSourceBuilder);
            if (needScroll) {
                searchRequest1.scroll(TimeValue.timeValueMinutes(minutes));
            }

            return getClient(indexInfo.getClusterName()).search(searchRequest1, COMMON_OPTIONS);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("获取文档失败，indexInfo:{},searchRequest:{}", indexInfo, searchRequest, e);
            }
        }
        return null;
    }

    public Boolean updateDoc(EsIndexInfo indexInfo, EsSourceData sourceData) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(indexInfo.getIndexName(), sourceData.getDocId());
            updateRequest.index(indexInfo.getIndexName());
            updateRequest.id(sourceData.getDocId());
            updateRequest.doc(sourceData.getData());
            UpdateResponse updateResponse = getClient(indexInfo.getClusterName()).update(updateRequest, COMMON_OPTIONS);
            return Boolean.TRUE;
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("更新文档失败，indexInfo:{},sourceData:{}", indexInfo, sourceData, e);
            }
        }
        return Boolean.FALSE;
    }

    public Boolean batchUpdateDoc(EsIndexInfo indexInfo, List<EsSourceData> sourceDataList) {
        try {
            boolean flag = false;
            BulkRequest bulkRequest = new BulkRequest();
            for (EsSourceData esSourceData : sourceDataList) {
                String docId = esSourceData.getDocId();
                if (StringUtils.isNotBlank(docId)) {
                    UpdateRequest updateRequest = new UpdateRequest(indexInfo.getIndexName(), esSourceData.getDocId());
                    updateRequest.index(indexInfo.getIndexName());
                    updateRequest.id(esSourceData.getDocId());
                    updateRequest.doc(esSourceData.getData());
                    bulkRequest.add(updateRequest);
                    flag = true;
                }
            }
            if (flag) {
                BulkResponse bulk = getClient(indexInfo.getClusterName()).bulk(bulkRequest, COMMON_OPTIONS);
                if (bulk.hasFailures()) {
                    if (log.isErrorEnabled()) {
                        return Boolean.FALSE;
                    }
                }
            }
            return Boolean.TRUE;
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("更新文档失败，indexInfo:{},sourceDataList:{}", indexInfo, sourceDataList, e);
            }
        }
        return Boolean.FALSE;
    }
}