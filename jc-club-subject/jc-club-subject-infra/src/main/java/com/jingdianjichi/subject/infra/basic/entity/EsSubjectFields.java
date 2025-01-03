package com.jingdianjichi.subject.infra.basic.entity;

import lombok.Data;

/**
 * @author jay
 * @since 2024/12/31 下午1:05
 */
@Data
public class EsSubjectFields {

    public static final String DOC_ID = "doc_id";
    public static final String SUBJECT_ID = "subject_id";
    public static final String SUBJECT_NAME = "subject_name";
    public static final String SUBJECT_ANSWER = "subject_answer";
    public static final String SUBJECT_DIFFICULT = "subject_difficult";
    public static final String SUBJECT_SCORE = "subject_score";
    public static final String SETTLE_NAME= "settle_name";
    public static final String SUBJECT_PARSE = "subject_parse";
    public static final String SUBJECT_TYPE = "subject_type";
    public static final String CREATED_BY = "created_by";
    public static final String CREATED_TIME = "created_time";
    public static final String[] All_Fields = {
            DOC_ID,SUBJECT_ID,SUBJECT_NAME,SUBJECT_ANSWER,SUBJECT_TYPE,CREATED_BY,CREATED_TIME
    };
}
