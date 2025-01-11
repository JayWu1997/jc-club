package com.jingdianjichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.api.req.SubjectMappingDTO;
import com.jingdianjichi.subject.api.resp.Result;
import com.jingdianjichi.subject.application.convert.SubjectMappingDTOConverter;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.entity.SubjectMappingBO;
import com.jingdianjichi.subject.domain.service.SubjectMappingDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jay
 * @since 2025/1/11 下午1:39
 */
@Slf4j
@RestController
@RequestMapping("/subject/mapping")
public class SubjectMappingController {

    @Resource
    private SubjectMappingDomainService subjectMappingDomainService;

    /**
     * 根据题目 ID 列表查询题目映射
     * @param dto subjectIdList
     * @return
     */
    @PostMapping("/queryBatchBySubjectIds")
    public Result<List<SubjectMappingDTO>> queryBatchBySubjectIds(@RequestBody SubjectMappingDTO dto) {
        try{
            if (log.isInfoEnabled()) {
                log.info("SubjectMappingController.queryBatchBySubjectIds.dto:{}", JSON.toJSON(dto));
            }

            ParamCheckUtil.checkCollNotEmpty(dto.getSubjectIdList(), BusinessErrorEnum.PARAM_ERROR, "题目 ID 列表不能为空!");
            List<SubjectMappingBO> boList = subjectMappingDomainService.queryBatchBySubjectIds(SubjectMappingDTOConverter.INSTANCE.convertDto2Bo(dto));
            return Result.success(SubjectMappingDTOConverter.INSTANCE.convertBo2Dto(boList));
        } catch (BusinessException e) {
            log.error("SubjectMappingController.queryBatchBySubjectIds.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectMappingController.queryBatchBySubjectIds.error:{}", e.getMessage(), e);
            return Result.fail("根据题目 ID 列表查询题目映射失败！");
        }
    }
}
