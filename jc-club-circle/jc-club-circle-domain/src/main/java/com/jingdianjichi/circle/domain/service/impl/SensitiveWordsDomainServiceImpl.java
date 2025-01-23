package com.jingdianjichi.circle.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jingdianjichi.circle.common.enums.BusinessErrorEnum;
import com.jingdianjichi.circle.common.enums.IsDeletedEnum;
import com.jingdianjichi.circle.common.exception.BusinessException;
import com.jingdianjichi.circle.domain.convert.SensitiveWordsBOConverter;
import com.jingdianjichi.circle.domain.entity.SensitiveWordsBO;
import com.jingdianjichi.circle.domain.service.SensitiveWordsDomainService;
import com.jingdianjichi.circle.infra.mybatis.model.SensitiveWords;
import com.jingdianjichi.circle.infra.mybatis.service.ISensitiveWordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jay
 * @since 2025/1/23 下午10:55
 */
@Service("sensitiveWordsDomainService")
public class SensitiveWordsDomainServiceImpl implements SensitiveWordsDomainService {

    @Resource
    private ISensitiveWordsService sensitiveWordsService;

    /**
     * @param bo
     * @return
     */
    @Override
    public Boolean save(SensitiveWordsBO bo) {
        // 查询该违禁词是否存在
        boolean exists = sensitiveWordsService.exists(Wrappers.<SensitiveWords>lambdaQuery()
                .eq(SensitiveWords::getWords, bo.getWords())
                .eq(SensitiveWords::getIsDeleted, IsDeletedEnum.NOT_DELETED.getCode()));
        if (exists) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "该违禁词已存在!");
        }
        return sensitiveWordsService.save(SensitiveWordsBOConverter.INSTANCE.convertBo2Entity(bo));
    }
}
