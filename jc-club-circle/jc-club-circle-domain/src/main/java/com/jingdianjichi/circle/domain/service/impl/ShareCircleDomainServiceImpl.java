package com.jingdianjichi.circle.domain.service.impl;

import cn.hutool.core.util.ObjUtil;
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
import java.time.LocalDateTime;

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
        return shareCircleService.save(entity);
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
        return shareCircleService.updateById(entity);
    }
}
