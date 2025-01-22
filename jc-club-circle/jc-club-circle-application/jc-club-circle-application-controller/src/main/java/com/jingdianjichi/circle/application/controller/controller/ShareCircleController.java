package com.jingdianjichi.circle.application.controller.controller;

import com.jingdianjichi.circle.api.req.ShareCircleDTO;
import com.jingdianjichi.circle.api.resp.Result;
import com.jingdianjichi.circle.application.controller.convert.ShareCircleDTOConverter;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.utils.ParamCheckUtil;
import com.jingdianjichi.circle.domain.entity.ShareCircleBO;
import com.jingdianjichi.circle.domain.service.ShareCircleDomainService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        ParamCheckUtil.checkNotNull(dto.getParentId(), BusinessErrorEnum.PARAM_ERROR, "父id不能为空");
        ParamCheckUtil.checkStrNotEmpty(dto.getIcon(), BusinessErrorEnum.PARAM_ERROR, "圈子图标不能为空");
        ShareCircleBO bo = ShareCircleDTOConverter.INSTANCE.convertDto2Bo(dto);
        return Result.success(shareCircleDomainService.save(bo));
    }

    @PostMapping(value = "/update")
    public Result<Boolean> update(@RequestBody ShareCircleDTO dto) {
        ParamCheckUtil.checkNotNull(dto.getId(), BusinessErrorEnum.PARAM_ERROR, "id不能为空");
        ShareCircleBO bo = ShareCircleDTOConverter.INSTANCE.convertDto2Bo(dto);
        return Result.success(shareCircleDomainService.update(bo));
    }

    @PostMapping(value = "/remove")
    public Result<Boolean> remove(@RequestBody ShareCircleDTO dto) {
        ParamCheckUtil.checkNotNull(dto.getId(), BusinessErrorEnum.PARAM_ERROR, "id不能为空");
        return Result.success(shareCircleDomainService.remove(dto.getId()));
    }

    @GetMapping(value = "/list")
    public Result<List<ShareCircleDTO>> list() {
        List<ShareCircleDTO> dtoList =  ShareCircleDTOConverter.INSTANCE.convertBo2Dto(shareCircleDomainService.list());
        return Result.success(dtoList);
    }
}
