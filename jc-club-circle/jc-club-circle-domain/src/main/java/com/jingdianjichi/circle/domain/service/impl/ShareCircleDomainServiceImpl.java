package com.jingdianjichi.circle.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

/**
 * @author jay
 * @since 2025/1/21 下午11:56
 */
@Service
public class ShareCircleDomainServiceImpl implements ShareCircleDomainService {

    @Resource
    private IShareCircleService shareCircleService;

    /**
     * @param bo
     * @return
     */
    @Override
    public Boolean save(ShareCircleBO bo) {
        LambdaQueryWrapper<ShareCircle> existQuery = new LambdaQueryWrapper<>();
        existQuery.eq(ShareCircle::getIsDeleted, IsDeletedEnum.NOT_DELETED.getCode());
        existQuery.eq(ShareCircle::getId, bo.getParentId());
        if (shareCircleService.exists(existQuery)) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "父级圈子不存在!");
        }

        ShareCircle entity = ShareCircleBOConverter.INSTANCE.convertBo2Entity(bo);
        entity.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        return shareCircleService.save(entity);
    }
}
