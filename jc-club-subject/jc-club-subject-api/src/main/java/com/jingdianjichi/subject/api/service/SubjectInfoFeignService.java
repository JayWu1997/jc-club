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

    /**
     * 根据题目信息查询题目详情
     * <p>
     * 此方法通过POST请求接收一个SubjectInfoDTO对象作为参数，该对象包含了查询所需的信息，
     * 包括题目ID、类别ID和标签ID。方法首先会检查这些参数是否为空，然后将DTO转换为BO，
     * 并调用领域服务获取题目信息。最后，将结果转换回DTO并返回。
     *
     * @param subjectInfoDTO 包含查询信息的SubjectInfoDTO对象
     * @return 返回一个Result对象，其中包含查询到的SubjectInfoDTO对象
     */
    @PostMapping("/subject/querySubjectInfo")
    public Result<SubjectInfoDTO> querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO);
}
