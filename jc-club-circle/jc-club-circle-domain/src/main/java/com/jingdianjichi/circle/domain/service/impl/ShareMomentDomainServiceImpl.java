package com.jingdianjichi.circle.domain.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson.JSON;
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
import java.time.LocalDateTime;

/**
 * @author jay
 * @since 2025/1/22 下午4:19
 */
@Service
public class ShareMomentDomainServiceImpl implements ShareMomentDomainService {

    @Resource
    private IShareMomentService momentService;

    @Resource
    private IShareCircleService circleService;

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
        return momentService.save(entity);
    }
}
