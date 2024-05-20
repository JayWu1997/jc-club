package com.jingdianjichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdianjichi.subject.application.controller.convert.SubjectCategoryDTOConverter;
import com.jingdianjichi.subject.application.controller.dto.SubjectCategoryDTO;
import com.jingdianjichi.subject.common.entity.Result;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 主题类别控制器类
 * 这是一个用于管理主题类别的控制器类，提供了对主题类别相关操作的接口。
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 添加主题类别信息
     *
     * @param subjectCategoryDTO 主题类别数据传输对象，包含要添加的主题类别信息
     * @return 返回操作结果，成功返回包含添加后主题类别信息的结果，失败返回空结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            // 数据校验
            Preconditions.checkArgument(!ObjectUtils.isEmpty(subjectCategoryDTO.getCategoryType()), "分类类型不能为空");
            Preconditions.checkArgument(StringUtils.hasText(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");

            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if(log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSON(subjectCategoryDTO));
            }

            // 转换DTO为BO，进行业务逻辑处理前的准备
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDto2Bo(subjectCategoryDTO);
            // 调用领域服务，执行添加操作，并返回处理后的BO
            subjectCategoryBO = subjectCategoryDomainService.add(subjectCategoryBO);
            // 将BO转换回DTO，封装到结果对象中返回
            Result result = Result.ok(SubjectCategoryDTOConverter.INSTANCE.convertBo2Dto(subjectCategoryBO));

            // 日志记录优化：确保日志级别允许时才进行DTO序列化操作
            if(log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.successResult:{}", JSON.toJSON(result));
            }

            return result;
        } catch (IllegalArgumentException e) {
            // 捕获并处理具体的业务异常，返回更详细的错误信息
            log.error("添加主题类别信息失败：", e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            // 捕获其他异常，记录日志并返回通用错误信息
            log.error("添加主题类别信息过程中发生未知异常：", e);
            return Result.fail("操作失败");
        }
    }
}
