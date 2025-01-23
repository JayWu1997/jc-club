package com.jingdianjichi.circle.application.controller.controller;

import com.jingdianjichi.circle.api.req.SensitiveWordsDTO;
import com.jingdianjichi.circle.api.resp.Result;
import com.jingdianjichi.circle.application.controller.convert.SensitiveWordsDTOConverter;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.utils.ParamCheckUtil;
import com.jingdianjichi.circle.domain.entity.SensitiveWordsBO;
import com.jingdianjichi.circle.domain.service.SensitiveWordsDomainService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 敏感词表 前端控制器
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@RestController
@RequestMapping("/circle/share/word")
public class SensitiveWordsController {

    @Resource
    private SensitiveWordsDomainService sensitiveWordsDomainService;

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody SensitiveWordsDTO dto) {
        ParamCheckUtil.checkStrNotEmpty(dto.getWords(), BusinessErrorEnum.PARAM_ERROR, "敏感词不能为空");
        ParamCheckUtil.checkNotNull(dto.getType(), BusinessErrorEnum.PARAM_ERROR, "敏感词类型不能为空");
        ParamCheckUtil.checkIntegralNumberRangeIncludeBorder(dto.getType(), 1, 2, BusinessErrorEnum.PARAM_ERROR, "敏感词类型错误");
        SensitiveWordsBO bo = SensitiveWordsDTOConverter.INSTANCE.convertDto2Bo(dto);
        Boolean success = sensitiveWordsDomainService.save(bo);
        return Result.success(success);
    }
}
