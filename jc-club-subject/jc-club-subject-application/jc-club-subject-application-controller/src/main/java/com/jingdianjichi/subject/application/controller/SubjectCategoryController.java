package com.jingdianjichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.application.convert.SubjectCategoryDTOConverter;
import com.jingdianjichi.subject.application.dto.SubjectCategoryDTO;
import com.jingdianjichi.subject.common.entity.Result;
import com.jingdianjichi.subject.common.enums.CategoryTypeEnum;
import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目类别控制器类
 * 这是一个用于管理题目类别的控制器类，提供了对主题类别相关操作的接口。
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 添加题目类别信息
     *
     * @param subjectCategoryDTO 主题类别数据传输对象，包含要添加的主题类别信息
     * @return 返回操作结果，成功返回包含添加后主题类别信息的结果，失败返回空结果
     */
    @PostMapping("/add")
    public Result<SubjectCategoryDTO> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSON(subjectCategoryDTO));
            }

            // 数据校验
            if (ObjectUtils.isEmpty(subjectCategoryDTO.getCategoryType())) {
                throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "添加题目类别时，分类类型不能为空");
            }
            if (!StringUtils.hasText(subjectCategoryDTO.getCategoryName())) {
                throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "添加题目类别时，分类名称不能为空");
            }

            // 转换DTO为BO，进行业务逻辑处理前的准备
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDto2Bo(subjectCategoryDTO);
            // 调用领域服务，执行添加操作，并返回处理后的BO
            subjectCategoryBO = subjectCategoryDomainService.add(subjectCategoryBO);
            // 将BO转换回DTO，封装到结果对象中返回
            Result<SubjectCategoryDTO> result = Result.success(SubjectCategoryDTOConverter.INSTANCE.convertBo2Dto(subjectCategoryBO));

            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.successResult:{}", JSON.toJSON(result));
            }

            return result;
        } catch (BusinessException e) {
            log.error("SubjectCategoryController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加题目分类失败！");
        }
    }


    /**
     * 查询岗位信息，即顶层题目分类( parentId 为 0 的分类 )
     * <p>
     * 通过调用主题分类领域服务查询所有岗位信息，并将查询结果转换为DTO形式返回。
     * 此接口用于前端获取岗位信息，以展示在相应的界面中。
     *
     * @return Result<List < SubjectCategoryDTO>> 返回查询结果，包含岗位信息的列表。
     */
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryPrimaryCategory.dto:{}", JSON.toJSON(subjectCategoryDTO));
            }

            if (subjectCategoryDTO.getCategoryType() == null || !subjectCategoryDTO.getCategoryType().equals(CategoryTypeEnum.PRIMARY.getCode())) {
                subjectCategoryDTO.setCategoryType(CategoryTypeEnum.PRIMARY.getCode());
            }
            // 调用领域服务查询所有岗位信息
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(SubjectCategoryDTOConverter.INSTANCE.convertDto2Bo(subjectCategoryDTO));
            // 将查询到的岗位信息BO转换为DTO，并包装在Result对象中返回
            Result<List<SubjectCategoryDTO>> result = Result.success(SubjectCategoryDTOConverter.INSTANCE.convertBo2Dto(subjectCategoryBOList));
            // 如果日志级别为INFO，则记录查询成功的结果
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryPrimaryCategory.successResult:{}", JSON.toJSON(result));
            }
            return result;
        } catch (BusinessException e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.fail("查询岗位信息失败！");
        }
    }

    /**
     * 查询大类下的分类
     *
     * @param subjectCategoryDTO 主题类别数据传输对象，包含要查询的大类信息
     * @return 返回操作结果，成功返回包含查询结果的结果，失败返回空结果
     */
    @PostMapping("/queryCategoryList")
    public Result<List<SubjectCategoryDTO>> queryCategoryList(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryList.dto:{}", JSON.toJSON(subjectCategoryDTO));
            }

            if (subjectCategoryDTO.getCategoryType() == null || !subjectCategoryDTO.getCategoryType().equals(CategoryTypeEnum.SECONDARY.getCode())) {
                subjectCategoryDTO.setCategoryType(CategoryTypeEnum.SECONDARY.getCode());
            }
            if (subjectCategoryDTO.getParentId() == null || subjectCategoryDTO.getParentId() < 0) {
                throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "查询次级分类时，父级分类不能为空");
            }
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(SubjectCategoryDTOConverter.INSTANCE.convertDto2Bo(subjectCategoryDTO));
            // 将查询到的岗位信息BO转换为DTO，并包装在Result对象中返回
            Result<List<SubjectCategoryDTO>> result = Result.success("分类信息查询成功!", SubjectCategoryDTOConverter.INSTANCE.convertBo2Dto(subjectCategoryBOList));
            // 如果日志级别为INFO，则记录查询成功的结果
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryList.successResult:{}", JSON.toJSON(result));
            }
            return result;
        } catch (BusinessException e) {
            log.error("SubjectCategoryController.queryCategoryList.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryCategoryList.error:{}", e.getMessage(), e);
            return Result.fail("查询分类信息失败！");
        }
    }

    /**
     * 更新分类信息
     *
     * @param subjectCategoryDTO dto
     * @return 操作结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (subjectCategoryDTO.getId() == null || subjectCategoryDTO.getId() < 0) {
                throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "更新题目类别时，分类名称不能为空");
            }

            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.update.dto:{}", JSON.toJSON(subjectCategoryDTO));
            }

            Boolean isUpdateSucceed = subjectCategoryDomainService.update(SubjectCategoryDTOConverter.INSTANCE.convertDto2Bo(subjectCategoryDTO));
            if (isUpdateSucceed != null && isUpdateSucceed) {
                return Result.success("题目分类信息更新成功！", Boolean.TRUE);
            } else {
                return Result.success("题目分类信息更新失败！", Boolean.FALSE);
            }
        } catch (BusinessException e) {
            log.error("SubjectCategoryController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新题目分类信息失败！");
        }
    }

    /**
     * 删除题目分类
     *
     * @param subjectCategoryDTO dto
     * @return 操作结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (subjectCategoryDTO.getId() == null) {
                throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "删除题目类别时，分类 id 不能为空");
            }

            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.delete.dto:{}", JSON.toJSON(subjectCategoryDTO));
            }

            Boolean isDeleteSucceed = subjectCategoryDomainService.delete(SubjectCategoryDTOConverter.INSTANCE.convertDto2Bo(subjectCategoryDTO));
            if (isDeleteSucceed) {
                return Result.success("删除题目分类成功！", Boolean.TRUE);
            } else {
                return Result.success("删除题目分类失败！", Boolean.FALSE);
            }
        } catch (BusinessException e) {
            log.error("SubjectCategoryController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除题目分类信息失败！");
        }
    }
}
