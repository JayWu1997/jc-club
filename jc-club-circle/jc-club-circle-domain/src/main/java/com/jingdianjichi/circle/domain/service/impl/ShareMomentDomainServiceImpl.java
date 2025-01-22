package com.jingdianjichi.circle.domain.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.jingdianjichi.auth.api.req.AuthUserDTO;
import com.jingdianjichi.auth.api.resp.Result;
import com.jingdianjichi.auth.api.service.UserFeignService;
import com.jingdianjichi.circle.common.context.UserContextHolder;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.enums.IsDeletedEnum;
import com.jingdianjichi.circle.common.exception.BusinessException;
import com.jingdianjichi.circle.domain.convert.ShareMomentBOConverter;
import com.jingdianjichi.circle.domain.entity.ShareMomentBO;
import com.jingdianjichi.circle.domain.service.ShareMomentDomainService;
import com.jingdianjichi.circle.infra.mybatis.model.ShareMoment;
import com.jingdianjichi.circle.infra.mybatis.service.IShareCircleService;
import com.jingdianjichi.circle.infra.mybatis.service.IShareMomentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jay
 * @since 2025/1/22 下午4:19
 */
@Service
public class ShareMomentDomainServiceImpl implements ShareMomentDomainService {

    private static final String MOMENT_LIST_KEY = "MOMENT_LIST";

    private Cache<String, String> cache = Caffeine.newBuilder().expireAfterAccess(Duration.ofMinutes(1)).maximumSize(100).recordStats().build();

    @Resource
    private IShareMomentService momentService;

    @Resource
    private IShareCircleService circleService;

    @Resource
    private UserFeignService usersFeignService;

    /**
     * @param bo
     * @return
     */
    @Override
    public Boolean save(ShareMomentBO bo) {
        // 查询圈子是否存在
        if (ObjUtil.isNull(circleService.getById(bo.getCircleId()))) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "圈子不存在!");
        }
        ShareMoment entity = ShareMomentBOConverter.INSTANCE.convertBo2Entity(bo);
        // 设置图片路径
        if (CollUtil.isNotEmpty(bo.getPicUrlList())) {
            entity.setPicUrls(JSON.toJSONString(bo.getPicUrlList()));
        }
        entity.setCreatedBy(UserContextHolder.getUserContext().getUserName());
        entity.setCreatedTime(LocalDateTime.now());
        entity.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        entity.setReplyCount(0);
        boolean success = momentService.save(entity);
        if (success) {
            cache.put(MOMENT_LIST_KEY, "");
        }
        return success;
    }

    /**
     * @param bo
     * @return
     */
    @Override
    public List<ShareMomentBO> getMoments(ShareMomentBO bo) {
        // 从缓存获取
        String jsonStr = cache.getIfPresent(MOMENT_LIST_KEY);
        if (StrUtil.isNotBlank(jsonStr)) {
            return JSON.parseArray(jsonStr, ShareMomentBO.class);
        }

        // 从数据库获取
        Page<ShareMoment> page = new Page<>(bo.getPageNo(), bo.getPageSize());
        List<ShareMoment> entityList = momentService.list(page, Wrappers.<ShareMoment>lambdaQuery()
                .eq(ShareMoment::getIsDeleted, IsDeletedEnum.NOT_DELETED.getCode())
                .orderByDesc(ShareMoment::getCreatedTime));
        if (CollUtil.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        List<ShareMomentBO> boList = new ArrayList<>();
        entityList.forEach(shareMoment -> {
            ShareMomentBO boTemp = ShareMomentBOConverter.INSTANCE.convertEntity2Bo(shareMoment);
            // 填充用户信息
            AuthUserDTO userQuery = new AuthUserDTO();
            userQuery.setUserName(shareMoment.getCreatedBy());
            userQuery.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
            Result<AuthUserDTO> userInfoResult = usersFeignService.getUserInfo(userQuery);
            if (ObjUtil.isNotNull(userInfoResult) && ObjUtil.isNotNull(userInfoResult.getData())) {
                boTemp.setUserName(userInfoResult.getData().getNickName());
                boTemp.setUserAvatar(userInfoResult.getData().getAvatar());
            }
            // 填充图像路径
            if (StrUtil.isNotBlank(shareMoment.getPicUrls())) {
                boTemp.setPicUrlList(JSON.parseArray(shareMoment.getPicUrls(), String.class));
            }
            // 抹除 bo update create isDeleted信息
            boTemp.setCreatedBy(null);
            boTemp.setCreatedTime(null);
            boTemp.setUpdateBy(null);
            boTemp.setUpdateTime(null);
            boTemp.setIsDeleted(null);
            boList.add(boTemp);
        });
        // 放入缓存
        cache.put(MOMENT_LIST_KEY, JSON.toJSONString(boList));
        return boList;
    }
}
