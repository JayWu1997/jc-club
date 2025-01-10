package com.jingdianjichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.api.req.SubjectInfoDTO;
import com.jingdianjichi.subject.api.resp.Result;
import com.jingdianjichi.subject.application.convert.SubjectInfoDTOConverter;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.service.SubjectInfoDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfoEs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目信息 Controller
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    /**
     * 新增题目信息
     *
     * @param subjectInfoDTO dto
     * @return 包装后的结果
     */
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.add.dto:{}", JSON.toJSON(subjectInfoDTO));
            }

            ParamCheckUtil.checkStrNotEmpty(subjectInfoDTO.getSubjectName(), BusinessErrorEnum.PARAM_ERROR, "名称不能为空!");
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getSubjectType(), BusinessErrorEnum.PARAM_ERROR, "类型不能为空!");
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getSubjectDifficult(), BusinessErrorEnum.PARAM_ERROR, "难度不能为空!");
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getSubjectScore(), BusinessErrorEnum.PARAM_ERROR, "分数不能为空!");
            ParamCheckUtil.checkCollNotEmpty(subjectInfoDTO.getCategoryIds(), BusinessErrorEnum.PARAM_ERROR, "分类不能为空!");
            ParamCheckUtil.checkCollNotEmpty(subjectInfoDTO.getLabelIds(), BusinessErrorEnum.PARAM_ERROR, "标签不能为空!");

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
     *
     * @param subjectInfoDTO dto
     * @return 分页数据
     */
    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> querySubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.querySubjectPage.subjectInfoDTO:{}", JSON.toJSON(subjectInfoDTO));
            }

            ParamCheckUtil.checkNotNull(subjectInfoDTO.getCategoryId(), BusinessErrorEnum.PARAM_ERROR, "类型不能为空!");
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getLabelId(), BusinessErrorEnum.PARAM_ERROR, "标签不能为空!");

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
     * 根据题目信息查询题目详情
     * <p>
     * 此方法通过POST请求接收一个SubjectInfoDTO对象作为参数，该对象包含了查询所需的信息，
     * 包括题目ID、类别ID和标签ID。方法首先会检查这些参数是否为空，然后将DTO转换为BO，
     * 并调用领域服务获取题目信息。最后，将结果转换回DTO并返回。
     *
     * @param subjectInfoDTO 包含查询信息的SubjectInfoDTO对象
     * @return 返回一个Result对象，其中包含查询到的SubjectInfoDTO对象
     */
    @PostMapping("/querySubjectInfo")
    public Result<SubjectInfoDTO> querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            // 记录请求参数日志
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.querySubjectInfo.subjectInfoDTO:{}", JSON.toJSON(subjectInfoDTO));
            }

            // 参数校验，确保题目id、类别id和标签id不为空
            ParamCheckUtil.checkNotNull(subjectInfoDTO.getId(), BusinessErrorEnum.PARAM_ERROR, "题目 id 不能为空!");

            // 将DTO转换为BO，以便领域服务处理
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDto2Bo(subjectInfoDTO);
            // 调用领域服务获取题目信息
            SubjectInfoBO resultBO = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
            // 将结果转换回DTO并成功返回
            return Result.success(SubjectInfoDTOConverter.INSTANCE.convertBo2Dto(resultBO));
        } catch (BusinessException e) {
            // 记录业务异常日志并返回错误信息
            log.error("SubjectInfoController.querySubjectInfo.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            // 记录未知异常日志并返回通用错误信息
            log.error("SubjectInfoController.querySubjectInfo.error:{}", e.getMessage(), e);
            return Result.fail("获取题目信息失败！");
        }
    }


    /**
     * 更新题目信息
     *
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
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "题目 ID 不合法!");
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
     *
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
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "题目标签 ID 不合法!");
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

    /**
     * 从 ES 获取题目分页
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/getSubjectPageBySearch")
    public Result<PageResult<SubjectInfoEs>> getSubjectPageBySearch(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.getSubjectPageBySearch.subjectInfoDTO:{}", JSON.toJSON(subjectInfoDTO));
            }

            ParamCheckUtil.checkStrNotEmpty(subjectInfoDTO.getKeyWord(), BusinessErrorEnum.PARAM_ERROR, "关键词不能为空!");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDto2Bo(subjectInfoDTO);
            PageResult<SubjectInfoEs> esPageResult = subjectInfoDomainService.queryPageFromES(subjectInfoBO);
            return Result.success(esPageResult);
        } catch (BusinessException e) {
            log.error("SubjectInfoController.getSubjectPageBySearch.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectInfoController.getSubjectPageBySearch.error:{}", e.getMessage(), e);
            return Result.fail("从ES获取题目分页失败！");
        }
    }

    @RequestMapping("/getContributeList")
    public Result<List<SubjectInfoDTO>> getContributeList() {
        try {
            List<SubjectInfoBO> boList = subjectInfoDomainService.getContributeList();
            return Result.success(SubjectInfoDTOConverter.INSTANCE.convertBo2Dto(boList));
        } catch (BusinessException e) {
            log.error("SubjectInfoController.getContributeList.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectInfoController.getContributeList.error:{}", e.getMessage(), e);
            return Result.fail("获取贡献榜失败！");
        }
    }

    /**
     * 条件查询题目
     * 根据条件查询题目信息   subjectType
     *                     labelId
     *                     queryCount
     *
     * @return
     */
    @PostMapping("/queryByConditionInMultiTable")
    public Result<List<SubjectInfoDTO>> queryByConditionInMultiTable(@RequestBody SubjectInfoDTO dto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.queryByCondition.subjectInfoDTO:{}", JSON.toJSON(dto));
            }

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDto2Bo(dto);
            List<SubjectInfoBO> boList = subjectInfoDomainService.queryByConditionInMultiTable(subjectInfoBO);
            return Result.success(SubjectInfoDTOConverter.INSTANCE.convertBo2Dto(boList));
        } catch (BusinessException e) {
            log.error("SubjectInfoController.queryByCondition.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

}
