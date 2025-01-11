package com.jingdianjichi.subject.api.service;

import com.jingdianjichi.subject.api.req.SubjectMappingDTO;
import com.jingdianjichi.subject.api.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/11 下午1:48
 */
@Service
@FeignClient(value = "jc-club-subject-dev", contextId = "subjectMappingFeignService")
public interface SubjectMappingFeignService {

    /**
     * 根据题目 ID 列表查询题目映射
     * @param dto subjectIdList
     * @return
     */
    @PostMapping("/subject/mapping/queryBatchBySubjectIds")
    public Result<List<SubjectMappingDTO>> queryBatchBySubjectIds(@RequestBody SubjectMappingDTO dto);
}
