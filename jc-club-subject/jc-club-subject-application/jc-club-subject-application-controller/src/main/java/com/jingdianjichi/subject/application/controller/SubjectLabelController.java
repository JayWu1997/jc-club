package com.jingdianjichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.api.req.SubjectLabelDTO;
import com.jingdianjichi.subject.api.resp.Result;
import com.jingdianjichi.subject.application.convert.SubjectLabelDTOConverter;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import com.jingdianjichi.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签 Controller
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增题目标签
     *
     * @param subjectLabelDTO labelName
     *                        categoryId
     * @return 包装后的结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSON(subjectLabelDTO));
            }

            if (!StringUtils.hasText(subjectLabelDTO.getLabelName())) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "题目标签名称不能为空!");
            }
            ParamCheckUtil.checkNotNull(subjectLabelDTO.getCategoryId(), BusinessErrorEnum.PARAM_ERROR, "题目标签分类 ID 不能为空!");

            Boolean insertResult = subjectLabelDomainService.insert(SubjectLabelDTOConverter.INSTANCE.convertDto2Bo(subjectLabelDTO));
            Result<Boolean> result = Result.success(insertResult);

            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.successResult:{}", JSON.toJSON(result));
            }
            return result;
        } catch (BusinessException e) {
            log.error("SubjectLabelController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLabelController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加题目标签失败！");
        }
    }

    /**
     * 更新题目标签信息
     *
     * @param subjectLabelDTO dto
     * @return 包装后的结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.dto:{}", JSON.toJSON(subjectLabelDTO));
            }

            if (subjectLabelDTO.getId() == null || subjectLabelDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "题目标签 ID 不合法!");
            }

            Boolean insertResult = subjectLabelDomainService.update(SubjectLabelDTOConverter.INSTANCE.convertDto2Bo(subjectLabelDTO));
            Result<Boolean> result = Result.success(insertResult);

            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.successResult:{}", JSON.toJSON(result));
            }
            return result;
        } catch (BusinessException e) {
            log.error("SubjectLabelController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLabelController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新题目标签失败！");
        }
    }

    /**
     * 删除题目标签
     *
     * @param subjectLabelDTO dto
     * @return 包装后的结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.dto:{}", JSON.toJSON(subjectLabelDTO));
            }

            if (subjectLabelDTO.getId() == null || subjectLabelDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "题目标签 ID 不合法!");
            }

            Boolean insertResult = subjectLabelDomainService.delete(SubjectLabelDTOConverter.INSTANCE.convertDto2Bo(subjectLabelDTO));
            Result<Boolean> result = Result.success(insertResult);

            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.successResult:{}", JSON.toJSON(result));
            }
            return result;
        } catch (BusinessException e) {
            log.error("SubjectLabelController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLabelController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除题目标签失败！");
        }
    }

    /**
     * 根据分类 ID 查询题目标签
     *
     * @param subjectLabelDTO categoryId 分类 Id
     * @return 包装结果
     */
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.dto:{}", JSON.toJSON(subjectLabelDTO));
            }

            // 分类 ID 不能为空
            if (subjectLabelDTO.getCategoryId() == null || subjectLabelDTO.getCategoryId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "分类 ID 不合法!");
            }

            List<SubjectLabelBO> boList = subjectLabelDomainService.queryBatchByCategoryId(SubjectLabelDTOConverter.INSTANCE.convertDto2Bo(subjectLabelDTO));
            Result<List<SubjectLabelDTO>> result = Result.success(SubjectLabelDTOConverter.INSTANCE.convertBo2Dto(boList));

            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.successResult:{}", JSON.toJSON(result));
            }

            return result;
        } catch (BusinessException e) {
            log.error("SubjectLabelController.queryLabelByCategoryId.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLabelController.queryLabelByCategoryId.error:{}", e.getMessage(), e);
            return Result.fail("根据分类 ID 查询题目标签失败！");
        }
    }

    /**
     * 根据题目 ID 批量查询题目标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/queryBatchBySubjectIds")
    public Result<List<SubjectLabelDTO>> queryBatchBySubjectIds(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelListBySubjectId.dto:{}", JSON.toJSON(subjectLabelDTO));
            }

            ParamCheckUtil.checkCollNotEmpty(subjectLabelDTO.getSubjectIdList(), BusinessErrorEnum.PARAM_ERROR, "题目 ID 列表不能为空!");

            List<SubjectLabelBO> boList = subjectLabelDomainService.queryBatchBySubjectId(SubjectLabelDTOConverter.INSTANCE.convertDto2Bo(subjectLabelDTO));
            return Result.success(SubjectLabelDTOConverter.INSTANCE.convertBo2Dto(boList));
        } catch (BusinessException e) {
            log.error("SubjectLabelController.queryLabelListBySubjectId.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLabelController.queryLabelListBySubjectId.error:{}", e.getMessage(), e);
            return Result.fail("根据题目 ID 查询题目标签列表失败！");
        }
    }
}
