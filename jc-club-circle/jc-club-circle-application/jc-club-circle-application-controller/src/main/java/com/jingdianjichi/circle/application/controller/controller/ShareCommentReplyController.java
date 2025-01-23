package com.jingdianjichi.circle.application.controller.controller;

import com.jingdianjichi.circle.api.req.ShareCommentReplyDTO;
import com.jingdianjichi.circle.api.resp.Result;
import com.jingdianjichi.circle.application.controller.convert.ShareCommentReplyDTOConverter;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.utils.ParamCheckUtil;
import com.jingdianjichi.circle.domain.entity.ShareCommentReplyBO;
import com.jingdianjichi.circle.domain.service.ShareCommentReplyDomainService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评论及回复信息 前端控制器
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@RestController
@RequestMapping("/circle/share/comment")
public class ShareCommentReplyController {

    @Resource
    private ShareCommentReplyDomainService commentDomainService;

    @PostMapping(value = "/save")
    public Result<Boolean> save(@RequestBody ShareCommentReplyDTO dto) {
        ParamCheckUtil.checkNotNull(dto.getReplyType(), BusinessErrorEnum.PARAM_ERROR, "评论类型不能为空");
        ParamCheckUtil.checkStrNotEmpty(dto.getContent(), BusinessErrorEnum.PARAM_ERROR, "评论内容不能为空");
        ParamCheckUtil.checkNotNull(dto.getMomentId(), BusinessErrorEnum.PARAM_ERROR, "动态id不能为空");
        ParamCheckUtil.checkNotNull(dto.getTargetId(), BusinessErrorEnum.PARAM_ERROR, "目标id不能为空");
        ShareCommentReplyBO bo = ShareCommentReplyDTOConverter.INSTANCE.convertDto2Bo(dto);
        return Result.success(commentDomainService.save(bo));
    }

    @PostMapping(value = "/list")
    public Result<List<ShareCommentReplyDTO>> list(@RequestBody ShareCommentReplyDTO dto) {
        ParamCheckUtil.checkNotNull(dto.getId(), BusinessErrorEnum.PARAM_ERROR, "动态id不能为空");
        List<ShareCommentReplyBO> bolist = commentDomainService.getCommentReplyListByMomentId(dto.getId());
        return Result.success(ShareCommentReplyDTOConverter.INSTANCE.convertBo2Dto(bolist));
    }
}
