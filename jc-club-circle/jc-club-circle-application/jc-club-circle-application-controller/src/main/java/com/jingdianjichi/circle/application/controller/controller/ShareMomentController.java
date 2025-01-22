package com.jingdianjichi.circle.application.controller.controller;

import com.jingdianjichi.circle.api.req.ShareMomentDTO;
import com.jingdianjichi.circle.api.resp.Result;
import com.jingdianjichi.circle.application.controller.convert.ShareMomentDTOConverter;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.utils.ParamCheckUtil;
import com.jingdianjichi.circle.domain.service.ShareMomentDomainService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 动态信息 前端控制器
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@RestController
@RequestMapping("/circle/share/moment")
public class ShareMomentController {

    @Resource
    private ShareMomentDomainService momentDomainService;

    @PostMapping(value = "/save")
    public Result<Boolean> save(@RequestBody ShareMomentDTO dto) {
        ParamCheckUtil.checkStrNotEmpty(dto.getContent(), BusinessErrorEnum.PARAM_ERROR, "动态内容不能为空");
        ParamCheckUtil.checkNotNull(dto.getCircleId(), BusinessErrorEnum.PARAM_ERROR, "圈子id不能为空");
        return Result.success(momentDomainService.save(ShareMomentDTOConverter.INSTANCE.convertDto2Bo(dto)));
    }

}
