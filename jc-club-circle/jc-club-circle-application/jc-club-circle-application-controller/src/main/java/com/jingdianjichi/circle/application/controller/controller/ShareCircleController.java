package com.jingdianjichi.circle.application.controller.controller;

import com.jingdianjichi.circle.api.req.ShareCircleDTO;
import com.jingdianjichi.circle.api.resp.Result;
import com.jingdianjichi.circle.application.controller.convert.ShareCircleDTOConverter;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.utils.ParamCheckUtil;
import com.jingdianjichi.circle.domain.service.ShareCircleDomainService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 圈子信息 前端控制器
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@RestController
@RequestMapping("/circle/share/circle")
public class ShareCircleController {

    @Resource
    private ShareCircleDomainService shareCircleDomainService;

    @PostMapping(value = "/save")
    public Result<Boolean> save(@RequestBody ShareCircleDTO dto) {
        ParamCheckUtil.checkStrNotEmpty(dto.getCircleName(), BusinessErrorEnum.PARAM_ERROR, "圈子名称不能为空");
        ParamCheckUtil.checkStrNotEmpty(dto.getCircleName(), BusinessErrorEnum.PARAM_ERROR, "父id不能为空");
        return Result.success(shareCircleDomainService.save(ShareCircleDTOConverter.INSTANCE.convertDto2Bo(dto)));
    }
}
