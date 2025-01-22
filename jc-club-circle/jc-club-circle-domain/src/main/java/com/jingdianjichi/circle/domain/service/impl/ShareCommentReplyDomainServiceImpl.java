package com.jingdianjichi.circle.domain.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jingdianjichi.auth.api.req.AuthUserDTO;
import com.jingdianjichi.auth.api.resp.Result;
import com.jingdianjichi.auth.api.service.UserFeignService;
import com.jingdianjichi.circle.common.context.UserContextHolder;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.enums.IsDeletedEnum;
import com.jingdianjichi.circle.common.exception.BusinessException;
import com.jingdianjichi.circle.domain.convert.ShareCommentReplyBOConverter;
import com.jingdianjichi.circle.domain.entity.ShareCommentReplyBO;
import com.jingdianjichi.circle.domain.service.ShareCommentReplyDomainService;
import com.jingdianjichi.circle.infra.mybatis.model.ShareCommentReply;
import com.jingdianjichi.circle.infra.mybatis.model.ShareMoment;
import com.jingdianjichi.circle.infra.mybatis.service.IShareCommentReplyService;
import com.jingdianjichi.circle.infra.mybatis.service.IShareMomentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author jay
 * @since 2025/1/22 下午8:29
 */
@Service
public class ShareCommentReplyDomainServiceImpl implements ShareCommentReplyDomainService {

    @Resource
    private IShareCommentReplyService commentService;

    @Resource
    private IShareMomentService momentService;

    @Resource
    private UserFeignService userFeignService;

    /**
     * @param bo
     * @return
     */
    @Override
    @Transactional
    public Boolean save(ShareCommentReplyBO bo) {
        ShareMoment moment = momentService.getById(bo.getMomentId());
        if (ObjUtil.isNull(moment)) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "动态不存在!");
        }
        ShareCommentReply entity = ShareCommentReplyBOConverter.INSTANCE.convertBo2Entity(bo);
        String loginId = UserContextHolder.getUserContext().getUserName();
        entity.setCreatedBy(loginId);
        entity.setCreatedTime(LocalDateTime.now());
        entity.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());

        if (entity.getReplyType() == 1) {
            entity.setToId(bo.getTargetId());
            entity.setParentId((long)-1);
            entity.setToUser(loginId);
            entity.setToUserAuthor(Objects.nonNull(moment.getCreatedBy()) && loginId.equals(moment.getCreatedBy()) ? 1 : 0);
        } else {
            entity.setParentId(bo.getTargetId());
            entity.setReplyId(bo.getTargetId());
            entity.setReplyUser(loginId);
            entity.setReplayAuthor(Objects.nonNull(moment.getCreatedBy()) && loginId.equals(moment.getCreatedBy()) ? 1 : 0);

            ShareCommentReply replyTargetComment = commentService.getById(bo.getTargetId());
            entity.setToId(bo.getTargetId());
            entity.setToUser(replyTargetComment.getCreatedBy());
            entity.setToUserAuthor(Objects.nonNull(replyTargetComment.getCreatedBy()) && loginId.equals(replyTargetComment.getCreatedBy()) ? 1 : 0);
        }
        if (CollUtil.isNotEmpty(bo.getPicUrlList())) {
            entity.setPicUrls(JSON.toJSONString(bo.getPicUrlList()));
        }
        momentService.incrReplyCount(bo.getMomentId());
        return commentService.save(entity);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<ShareCommentReplyBO> getCommentReplyListByMomentId(Long id) {
        List<ShareCommentReply> list = commentService.list(Wrappers.<ShareCommentReply>lambdaQuery().eq(ShareCommentReply::getMomentId, id));
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }

        List<ShareCommentReplyBO> boList = ShareCommentReplyBOConverter.INSTANCE.convertEntity2Bo(list);
        List<String> userNameList = list.stream().map(ShareCommentReply::getCreatedBy).distinct().filter(Objects::nonNull).collect(Collectors.toList());
        List<String> toUserNameList = list.stream().map(ShareCommentReply::getToUser).distinct().filter(Objects::nonNull).collect(Collectors.toList());
        AuthUserDTO userQuery = new AuthUserDTO();
        userQuery.setUserNameList(userNameList);
        Result<List<AuthUserDTO>> listResult = userFeignService.batchQueryByUserNames(userQuery);
        if (ObjUtil.isNull(listResult) || CollUtil.isEmpty(listResult.getData())) {
            return boList;
        }

        List<AuthUserDTO> userInfoList = listResult.getData();
        // 建立从 userName 到 userInfo 的映射的 map
        Map<String, AuthUserDTO> nameMap = userInfoList.stream().collect(Collectors.toMap(AuthUserDTO::getUserName, userInfo -> userInfo));
        boList.forEach(bo->{
            if (StrUtil.isNotBlank(bo.getPicUrls())) {
                bo.setPicUrlList(JSON.parseArray(bo.getPicUrls(), String.class));
            }
            AuthUserDTO userInfo = nameMap.get(bo.getCreatedBy());
            if (ObjUtil.isNotNull(userInfo)) {
                bo.setAvatar(userInfo.getAvatar());
                bo.setUserName(userInfo.getNickName());
            }

            if (bo.getReplyType() == 2) {
                AuthUserDTO toUserInfo = nameMap.get(bo.getToUser());
                if (ObjUtil.isNotNull(toUserInfo)) {
                    bo.setToName(toUserInfo.getNickName());
                    bo.setToAvatar(toUserInfo.getAvatar());
                }
                bo.setFromId(bo.getCreatedBy());
                bo.setToId(bo.getToUser());
            }
        });

        // 剔除boList中的item的 create update isDeleted 信息
        boList.forEach(bo->{
            bo.setCreatedTime(null);
            bo.setUpdateBy(null);
            bo.setUpdateTime(null);
            bo.setIsDeleted(null);
        });
        return boList;
    }
}
