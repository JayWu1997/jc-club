package com.jingdianjichi.circle.application.controller.controller;

import com.jingdianjichi.circle.api.req.ShareMomentDTO;
import com.jingdianjichi.circle.api.resp.PageResult;
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
import java.util.List;

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

    @PostMapping(value = "/getMoments")
    public Result<PageResult<ShareMomentDTO>> getMoments(@RequestBody ShareMomentDTO dto) {
        ParamCheckUtil.checkNotNull(dto.getPageInfo(), BusinessErrorEnum.PARAM_ERROR, "分页信息不能为空");
        ParamCheckUtil.checkNotNull(dto.getPageInfo().getPageNo(), BusinessErrorEnum.PARAM_ERROR, "分页页码不能为空");
        ParamCheckUtil.checkNotNull(dto.getPageInfo().getPageSize(), BusinessErrorEnum.PARAM_ERROR, "分页每页数量不能为空");
        dto.setPageNo(dto.getPageInfo().getPageNo());
        dto.setPageSize(dto.getPageInfo().getPageSize());
        if (dto.getPageNo() < 1) {
            dto.setPageNo(1);
        }
        if (dto.getPageSize() < 1 || dto.getPageSize() > 100) {
            dto.setPageSize(10);
        }
        List<ShareMomentDTO> dtoList = ShareMomentDTOConverter.INSTANCE.convertBo2Dto(momentDomainService.getMoments(ShareMomentDTOConverter.INSTANCE.convertDto2Bo(dto)));
        return Result.success(new PageResult<>(dto.getPageNo(), dto.getPageSize(), dtoList.size(), dtoList));
    }

}
