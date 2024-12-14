package com.jingdianjichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.application.convert.SubjectInfoDTOConverter;
import com.jingdianjichi.subject.application.dto.SubjectInfoDTO;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.entity.Result;
import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.service.SubjectInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 题目信息 Controller
 */
@RestController
@RequestMapping("/subject/info")
@Slf4j
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    /**
     * 新增题目信息
     * @param subjectInfoDTO dto
     * @return 包装后的结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.add.dto:{}", JSON.toJSON(subjectInfoDTO));
            }

            ParamCheckUtil.checkStrNotEmpty(subjectInfoDTO.getSubjectName(), ResultCodeEnum.PARAM_ERROR, "名称不能为空!");
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getSubjectType(), ResultCodeEnum.PARAM_ERROR, "类型不能为空!");
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getSubjectDifficult(), ResultCodeEnum.PARAM_ERROR, "难度不能为空!");
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getSubjectScore(), ResultCodeEnum.PARAM_ERROR, "分数不能为空!");
            ParamCheckUtil.checkCollNotEmpty(subjectInfoDTO.getCategoryIds(), ResultCodeEnum.PARAM_ERROR, "分类不能为空!");
            ParamCheckUtil.checkCollNotEmpty(subjectInfoDTO.getLabelIds(), ResultCodeEnum.PARAM_ERROR, "标签不能为空!");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDto2Bo(subjectInfoDTO);
            subjectInfoDomainService.insert(subjectInfoBO);
            Result<Boolean> result = Result.success();

            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.add.successResult:{}", JSON.toJSON(result));
            }
            return result;
        } catch (BusinessException e) {
            log.error("SubjectInfoController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectInfoController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加题目失败！");
        }
    }

    /**
     * 获取题目分页
     * @param subjectInfoDTO dto
     * @return 分页数据
     */
    @PostMapping("/querySubjectPage")
    public Result<PageResult<SubjectInfoDTO>> querySubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.querySubjectPage.subjectInfoDTO:{}", JSON.toJSON(subjectInfoDTO));
            }

            ParamCheckUtil.checkNotNull(subjectInfoDTO.getCategoryId(), ResultCodeEnum.PARAM_ERROR, "类型不能为空!");
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getLabelId(), ResultCodeEnum.PARAM_ERROR, "标签不能为空!");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDto2Bo(subjectInfoDTO);
            PageResult<SubjectInfoBO> boPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.success(new PageResult<>(boPageResult.getPageNo(), boPageResult.getPageSize(), boPageResult.getTotal(), SubjectInfoDTOConverter.INSTANCE.convertBo2Dto(boPageResult.getResult())));
        } catch (BusinessException e) {
            log.error("SubjectInfoController.querySubjectPage.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectInfoController.querySubjectPage.error:{}", e.getMessage(), e);
            return Result.fail("获取题目分页失败！");
        }
    }

    /**
     * 更新题目信息
     * @param subjectInfoDTO dto
     * @return 包装后的结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.update.dto:{}", JSON.toJSON(subjectInfoDTO));
            }

            if (subjectInfoDTO.getId() == null || subjectInfoDTO.getId() < 0) {
                throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "题目 ID 不合法!");
            }

            Boolean insertResult = subjectInfoDomainService.update(SubjectInfoDTOConverter.INSTANCE.convertDto2Bo(subjectInfoDTO));
            Result<Boolean> result = Result.success(insertResult);

            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.update.successResult:{}", JSON.toJSON(result));
            }
            return result;
        } catch (BusinessException e) {
            log.error("SubjectInfoController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectInfoController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新题目信息失败！");
        }
    }

    /**
     * 删除题目
     * @param subjectInfoDTO dto
     * @return 包装后的结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.delete.dto:{}", JSON.toJSON(subjectInfoDTO));
            }

            if (subjectInfoDTO.getId() == null || subjectInfoDTO.getId() < 0) {
                throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "题目标签 ID 不合法!");
            }

            Boolean insertResult = subjectInfoDomainService.delete(SubjectInfoDTOConverter.INSTANCE.convertDto2Bo(subjectInfoDTO));
            Result<Boolean> result = Result.success(insertResult);

            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.delete.successResult:{}", JSON.toJSON(result));
            }
            return result;
        } catch (BusinessException e) {
            log.error("SubjectInfoController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectInfoController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除题目失败！");
        }
    }
}
