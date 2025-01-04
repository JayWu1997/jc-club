package com.jingdianjichi.subject.infra.basic.service;

import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfoEs;

import java.util.List;

/**
 * @author jay
 * @since 2024/12/31 下午1:11
 */
public interface SubjectEsInfoService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    boolean update(SubjectInfoEs subjectInfoEs);

    public PageResult<SubjectInfoEs> query(SubjectInfoEs subjectInfoEs);

    List<SubjectInfo> getContributeList();
}
