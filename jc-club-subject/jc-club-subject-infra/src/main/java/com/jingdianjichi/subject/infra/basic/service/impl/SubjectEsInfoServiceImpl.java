package com.jingdianjichi.subject.infra.basic.service.impl;

import cn.hutool.core.util.IdUtil;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.infra.basic.entity.EsSubjectFields;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfoEs;
import com.jingdianjichi.subject.infra.basic.es.EsIndexInfo;
import com.jingdianjichi.subject.infra.basic.es.EsRestClient;
import com.jingdianjichi.subject.infra.basic.es.EsSourceData;
import com.jingdianjichi.subject.infra.basic.service.SubjectEsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
        return null;
    }

    private EsIndexInfo getEsIndexInfo() {
        EsIndexInfo esIndexInfo = new EsIndexInfo();
        esIndexInfo.setClusterName("3baa79479703");
        esIndexInfo.setIndexName("subject_index");
        return esIndexInfo;
    }
}
