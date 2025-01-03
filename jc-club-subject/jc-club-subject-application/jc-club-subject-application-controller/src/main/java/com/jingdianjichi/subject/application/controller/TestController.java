package com.jingdianjichi.subject.application.controller;

import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfoEs;
import com.jingdianjichi.subject.infra.basic.service.SubjectEsInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jay
 * @since 2025/1/3 下午12:09
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private SubjectEsInfoService subjectEsInfoService;

    @PostMapping("/testEsQuery")
    public PageResult<SubjectInfoEs> testEsQuery(@RequestBody SubjectInfoEs subjectInfoEs) {
        return subjectEsInfoService.query(subjectInfoEs);
    }
}
