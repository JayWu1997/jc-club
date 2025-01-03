package com.jingdianjichi.subject.infra.basic.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.infra.basic.entity.EsSubjectFields;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfoEs;
import com.jingdianjichi.subject.infra.basic.es.EsIndexInfo;
import com.jingdianjichi.subject.infra.basic.es.EsRestClient;
import com.jingdianjichi.subject.infra.basic.es.EsSearchRequest;
import com.jingdianjichi.subject.infra.basic.es.EsSourceData;
import com.jingdianjichi.subject.infra.basic.service.SubjectEsInfoService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jay
 * @since 2024/12/31 下午1:13
 */
@Service
public class SubjectEsInfoServiceImpl implements SubjectEsInfoService {

    @Resource
    private EsRestClient esRestClient;

    /**
     * @param subjectInfoEs
     * @return
     */
    @Override
    public boolean insert(SubjectInfoEs subjectInfoEs) {
        EsSourceData sourceData = new EsSourceData();
        Map<String, Object> data = convert2Data(subjectInfoEs);
        sourceData.setDocId(IdUtil.getSnowflakeNextIdStr());
        sourceData.setData(data);
        return esRestClient.insertDoc(getEsIndexInfo(), sourceData);
    }

    private static Map<String, Object> convert2Data(SubjectInfoEs subjectInfoEs) {
        Map<String, Object> data = new HashMap<>();
        data.put(EsSubjectFields.SUBJECT_ID, subjectInfoEs.getSubjectId());
        data.put(EsSubjectFields.DOC_ID, subjectInfoEs.getDocId());
        data.put(EsSubjectFields.SUBJECT_NAME, subjectInfoEs.getSubjectName());
        data.put(EsSubjectFields.SUBJECT_ANSWER, subjectInfoEs.getSubjectAnswer());
        data.put(EsSubjectFields.SUBJECT_TYPE, subjectInfoEs.getSubjectType());
        data.put(EsSubjectFields.CREATED_BY, subjectInfoEs.getCreatedBy());
        data.put(EsSubjectFields.CREATED_TIME, subjectInfoEs.getCreatedTime());
        data.put(EsSubjectFields.SUBJECT_DIFFICULT, subjectInfoEs.getSubjectDifficult());
        data.put(EsSubjectFields.SUBJECT_SCORE, subjectInfoEs.getSubjectScore());
        data.put(EsSubjectFields.SETTLE_NAME, subjectInfoEs.getSettleName());
        data.put(EsSubjectFields.SUBJECT_PARSE, subjectInfoEs.getSubjectParse());
        return data;
    }

    /**
     * @param subjectInfoEs
     * @return
     */
    @Override
    public boolean update(SubjectInfoEs subjectInfoEs) {
        return false;
    }

    /**
     * @param subjectInfoEs
     * @return
     */
    @Override
    public PageResult<SubjectInfoEs> query(SubjectInfoEs subjectInfoEs) {
        PageResult<SubjectInfoEs> pageResult = null;
        EsSearchRequest request = createSearchRequest(subjectInfoEs);
        SearchResponse resp = esRestClient.searchWithTermQuery(getEsIndexInfo(), request);
        SearchHits searchHits = resp.getHits();
        List<SubjectInfoEs> dataList = new ArrayList<>();
        if (ObjectUtil.isNull(searchHits) || ObjectUtil.isNull(searchHits.getHits())) {
            pageResult = new PageResult<>(1, 20, 0, dataList);
        } else {
            for (SearchHit hit : searchHits.getHits()) {
                SubjectInfoEs temp = convert2SubjectInfoEs(hit);
                if (ObjectUtil.isNotNull(temp)) {
                    dataList.add(temp);
                }
            }
            pageResult = new PageResult<SubjectInfoEs>(subjectInfoEs.getPageNo(), subjectInfoEs.getPageSize(),
                    (int) searchHits.getTotalHits().value, dataList);
        }
        return pageResult;
    }

    private SubjectInfoEs convert2SubjectInfoEs(SearchHit hit) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        Map<String, Object> sourceAsMap = hit.getSourceAsMap();
        if (CollUtil.isNotEmpty(sourceAsMap)) {
            subjectInfoEs.setSubjectId(MapUtil.get(sourceAsMap, EsSubjectFields.SUBJECT_ID, Long.class));
            subjectInfoEs.setDocId((MapUtil.get(sourceAsMap, EsSubjectFields.DOC_ID, Long.class)));
            subjectInfoEs.setSubjectName(MapUtil.get(sourceAsMap, EsSubjectFields.SUBJECT_NAME, String.class));
            subjectInfoEs.setSubjectAnswer(MapUtil.get(sourceAsMap, EsSubjectFields.SUBJECT_ANSWER, String.class));
            subjectInfoEs.setSubjectType((MapUtil.get(sourceAsMap, EsSubjectFields.SUBJECT_TYPE, Integer.class)));
            subjectInfoEs.setSubjectDifficult((MapUtil.get(sourceAsMap, EsSubjectFields.SUBJECT_DIFFICULT, Integer.class)));
            subjectInfoEs.setSubjectScore((MapUtil.get(sourceAsMap, EsSubjectFields.SUBJECT_SCORE, Integer.class)));
            subjectInfoEs.setSettleName(MapUtil.get(sourceAsMap, EsSubjectFields.SETTLE_NAME, String.class));
            subjectInfoEs.setSubjectParse(MapUtil.get(sourceAsMap, EsSubjectFields.SUBJECT_PARSE, String.class));
            subjectInfoEs.setCreatedBy(MapUtil.get(sourceAsMap, EsSubjectFields.CREATED_BY, String.class));
            subjectInfoEs.setCreatedTime((MapUtil.get(sourceAsMap, EsSubjectFields.CREATED_TIME, Long.class)));
            subjectInfoEs.setUpdateBy(MapUtil.get(sourceAsMap, EsSubjectFields.CREATED_BY, String.class));
            subjectInfoEs.setUpdateTime((MapUtil.get(sourceAsMap, EsSubjectFields.CREATED_TIME, Long.class)));
            subjectInfoEs.setScore(new BigDecimal(String.valueOf(hit.getScore()))
                    .multiply(new BigDecimal("100.00"))
                    .setScale(2, RoundingMode.HALF_UP));
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            // 处理题目高亮
            HighlightField subjectNameHighlight = highlightFields.get(EsSubjectFields.SUBJECT_NAME);
            if (ObjectUtil.isNotNull(subjectNameHighlight)) {
                Text[] fragments = subjectNameHighlight.getFragments();
                StringBuilder sb = new StringBuilder();
                for (Text text : fragments) {
                    sb.append(text);
                }
                subjectInfoEs.setSubjectName(sb.toString());
            }
            // 处理答案高亮
            HighlightField subjectAnswerHighlight = highlightFields.get(EsSubjectFields.SUBJECT_ANSWER);
            if (ObjectUtil.isNotNull(subjectAnswerHighlight)) {
                Text[] fragments = subjectAnswerHighlight.getFragments();
                StringBuilder sb = new StringBuilder();
                for (Text text : fragments) {
                    sb.append(text);
                }
                subjectInfoEs.setSubjectAnswer(sb.toString());
            }
        }
        return subjectInfoEs;
    }

    private EsSearchRequest createSearchRequest(SubjectInfoEs subjectInfoEs) {
        EsSearchRequest req = new EsSearchRequest();
        BoolQueryBuilder bq = new BoolQueryBuilder();
        MatchQueryBuilder subjectNameQB =
                QueryBuilders.matchQuery(EsSubjectFields.SUBJECT_NAME, subjectInfoEs.getKeyword());
        // 使通过题目名称搜索得到的结果提升权重排在前面
        subjectNameQB.boost(2);
        bq.should(subjectNameQB);
        MatchQueryBuilder subjectAnswerQB =
                QueryBuilders.matchQuery(EsSubjectFields.SUBJECT_ANSWER, subjectInfoEs.getKeyword());
        bq.should(subjectAnswerQB);
        MatchQueryBuilder subjectTypeQB =
                QueryBuilders.matchQuery(EsSubjectFields.SUBJECT_TYPE, SubjectInfoTypeEnum.BRIEF.getCode());
        bq.must(subjectTypeQB);
        // 两个 should 最少命中 1 个
        bq.minimumShouldMatch(1);

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("*")
                .requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        req.setBoolQueryBuilder(bq);
        req.setHighlightBuilder(highlightBuilder);
        req.setFields(EsSubjectFields.All_Fields);
        req.setFrom((subjectInfoEs.getPageNo() - 1) * subjectInfoEs.getPageSize());
        req.setSize(subjectInfoEs.getPageSize());
        req.setNeedScroll(false);

        return req;
    }

    private EsIndexInfo getEsIndexInfo() {
        EsIndexInfo esIndexInfo = new EsIndexInfo();
        esIndexInfo.setClusterName("3baa79479703");
        esIndexInfo.setIndexName("subject_index");
        return esIndexInfo;
    }
}
