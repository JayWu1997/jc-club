package com.jingdianjichi.subject.api.service;

import com.jingdianjichi.subject.api.req.SubjectInfoDTO;
import com.jingdianjichi.subject.api.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/9 下午6:25
 */
@Service
@FeignClient(value = "jc-club-subject-dev", contextId = "subjectInfoFeignService")
public interface SubjectInfoFeignService {

    /**
     * 条件查询题目
     * 根据条件查询题目信息   subjectType 为空则不包含此条件
     *                     labelIds 为空则不包含此条件
     *                     queryCount 为空则不包含此条件
     *
     * @return
     */
    @PostMapping("/subject/queryByConditionInMultiTable")
    public Result<List<SubjectInfoDTO>> queryByConditionInMultiTable(@RequestBody SubjectInfoDTO dto);
}
