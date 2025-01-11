package com.jingdianjichi.subject.api.service;

import com.jingdianjichi.subject.api.req.SubjectLabelDTO;
import com.jingdianjichi.subject.api.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/9 下午1:02
 */
@Service
@FeignClient(value = "jc-club-subject-dev", contextId = "subjectLabelFeignService")
public interface SubjectLabelFeignService {

    /**
     * 根据分类 ID 查询题目标签
     *
     * @param subjectLabelDTO categoryId 类ID
     * @return 包装结果
     */
    @PostMapping("/subject/label/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO);

    /**
     * 根据题目 ID 批量查询题目标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/subject/label/queryBatchBySubjectIds")
    public Result<List<SubjectLabelDTO>> queryBatchBySubjectIds(@RequestBody SubjectLabelDTO subjectLabelDTO);
}
