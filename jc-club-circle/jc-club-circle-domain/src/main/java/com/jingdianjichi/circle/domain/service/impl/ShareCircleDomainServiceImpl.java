package com.jingdianjichi.circle.domain.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.jingdianjichi.circle.common.context.UserContextHolder;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.enums.IsDeletedEnum;
import com.jingdianjichi.circle.common.exception.BusinessException;
import com.jingdianjichi.circle.domain.convert.ShareCircleBOConverter;
import com.jingdianjichi.circle.domain.entity.ShareCircleBO;
import com.jingdianjichi.circle.domain.service.ShareCircleDomainService;
import com.jingdianjichi.circle.infra.mybatis.model.ShareCircle;
import com.jingdianjichi.circle.infra.mybatis.service.IShareCircleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jay
 * @since 2025/1/21 下午11:56
 */
@Service
public class ShareCircleDomainServiceImpl implements ShareCircleDomainService {

    private static final String SHARE_CIRCLE_LIST_KEY = "share_circle_list";

    private Cache<String, String> cache =
            Caffeine.newBuilder()
                    .expireAfterAccess(Duration.ofMinutes(1))
                    .maximumSize(100)
                    .recordStats()
                    .build();

    @Resource
    private IShareCircleService shareCircleService;

    /**
     * @param bo
     * @return
     */
    @Override
    public Boolean save(ShareCircleBO bo) {
        // 查询父级圈子是否存在
        if (bo.getParentId() != -1) {
            if (ObjUtil.isNull(shareCircleService.getById(bo.getParentId()))) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "父级圈子不存在!");
            }
        }

        ShareCircle entity = ShareCircleBOConverter.INSTANCE.convertBo2Entity(bo);
        entity.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        entity.setCreatedBy(UserContextHolder.getUserContext().getUserName());
        entity.setCreatedTime(LocalDateTime.now());
        boolean success = shareCircleService.save(entity);
        if (success) {
            cache.put(SHARE_CIRCLE_LIST_KEY, "");
        }
        return success;
    }

    /**
     * @param bo
     * @return
     */
    @Override
    public Boolean update(ShareCircleBO bo) {
        // 查询父级圈子是否存在
        Long parentId = bo.getParentId();
        if (ObjUtil.isNotNull(parentId) && parentId != -1) {
            if (ObjUtil.isNull(shareCircleService.getById(parentId))) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "父级圈子不存在!");
            }
        }
        ShareCircle entity = ShareCircleBOConverter.INSTANCE.convertBo2Entity(bo);
        entity.setUpdateBy(UserContextHolder.getUserContext().getUserName());
        entity.setUpdateTime(LocalDateTime.now());
        boolean success = shareCircleService.updateById(entity);
        if (success) {
            cache.put(SHARE_CIRCLE_LIST_KEY, "");
        }
        return success;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Long id) {
        ShareCircle entity = new ShareCircle();
        entity.setId(id);
        entity.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        entity.setUpdateBy(UserContextHolder.getUserContext().getUserName());
        entity.setUpdateTime(LocalDateTime.now());
        boolean success = shareCircleService.updateById(entity);
        if (success) {
            cache.put(SHARE_CIRCLE_LIST_KEY, "");
        }
        return success;
    }

    /**
     * @return
     */
    @Override
    public List<ShareCircleBO> list() {
        // 从缓存读取
        String listJson = cache.getIfPresent(SHARE_CIRCLE_LIST_KEY);
        if (StrUtil.isNotBlank(listJson)) {
            return JSON.parseArray(listJson, ShareCircleBO.class);
        }

        // 缓存没有则从数据库读取
        List<ShareCircle> allList = shareCircleService.list(Wrappers.<ShareCircle>lambdaQuery()
                .eq(ShareCircle::getIsDeleted, IsDeletedEnum.NOT_DELETED.getCode())
                .orderByAsc(ShareCircle::getId));
        if (CollUtil.isEmpty(allList)) {
            return Collections.emptyList();
        }
        // 抹除 update create isDeleted信息
        allList.forEach(shareCircle -> {
            shareCircle.setCreatedBy(null);
            shareCircle.setCreatedTime(null);
            shareCircle.setUpdateBy(null);
            shareCircle.setUpdateTime(null);
            shareCircle.setIsDeleted(null);
        });
        // 筛选出父级圈子
        List<ShareCircleBO> parentList = ShareCircleBOConverter.INSTANCE
                .convertEntity2Bo(allList.stream().filter(shareCircle -> shareCircle.getParentId() == -1).collect(Collectors.toList()));
        // 填充子圈子
        parentList.forEach(parent -> {
            List<ShareCircleBO> childrenList = ShareCircleBOConverter.INSTANCE.convertEntity2Bo(allList.stream()
                    .filter(shareCircle -> shareCircle.getParentId().equals(parent.getId()))
                    .collect(Collectors.toList()));
            parent.setChildren(childrenList);
        });
        // 放入缓存
        cache.put(SHARE_CIRCLE_LIST_KEY, JSON.toJSONString(parentList));
        return parentList;
    }
}
