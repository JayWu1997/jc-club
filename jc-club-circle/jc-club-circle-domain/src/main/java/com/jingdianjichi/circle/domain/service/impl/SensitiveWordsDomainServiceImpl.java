package com.jingdianjichi.circle.domain.service.impl;

import com.jingdianjichi.circle.domain.convert.SensitiveWordsBOConverter;
import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.SensitiveWordsBO;
import com.jingdianjichi.circle.domain.service.SensitiveWordsDomainService;
import com.jingdianjichi.circle.infra.mybatis.entity.SensitiveWords;
import com.jingdianjichi.circle.infra.mybatis.service.SensitiveWordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 敏感词表(SensitiveWords)领域服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:17:07
 */
@Service
public class SensitiveWordsDomainServiceImpl implements SensitiveWordsDomainService {

    @Resource
    private SensitiveWordsService sensitiveWordsService;

    /**
     * 新增
     *
     * @param sensitiveWordsBO 新增的对象
     * @return 结果
     */
    public Boolean add(SensitiveWordsBO sensitiveWordsBO) {
        return 1 == sensitiveWordsService.insert(SensitiveWordsBOConverter.INSTANCE.convertBo2Entity(sensitiveWordsBO));
    }

    /**
     * 更新
     *
     * @param sensitiveWordsBO 更新信息
     * @return 结果
     */
    public Boolean update(SensitiveWordsBO sensitiveWordsBO) {
        return 1 == sensitiveWordsService.update(SensitiveWordsBOConverter.INSTANCE.convertBo2Entity(sensitiveWordsBO));
    }

    /**
     * 删除
     *
     * @param sensitiveWordsBO 待删除的对象
     * @return 结果
     */
    public Boolean deleteById(SensitiveWordsBO sensitiveWordsBO) {
        return 1 == sensitiveWordsService.deleteById(sensitiveWordsBO.getId());
    }

    /**
     * 分页查询
     *
     * @param sensitiveWordsBO bo
     * @return 分页结果
     */
    public PageResult<SensitiveWordsBO> queryByPage(SensitiveWordsBO sensitiveWordsBO) {
        sensitiveWordsBO.setIsDeleted(0);

        int start = (sensitiveWordsBO.getPageNo() - 1) * sensitiveWordsBO.getPageSize();
        int pageSize = sensitiveWordsBO.getPageSize();
        int total = (int) sensitiveWordsService.count(SensitiveWordsBOConverter.INSTANCE.convertBo2Entity(sensitiveWordsBO));
        List<SensitiveWords> entityList = sensitiveWordsService.queryByPage(SensitiveWordsBOConverter.INSTANCE.convertBo2Entity(sensitiveWordsBO), start, pageSize);
        List<SensitiveWordsBO> boList = SensitiveWordsBOConverter.INSTANCE.convertEntity2Bo(entityList);
        return new PageResult<>(sensitiveWordsBO.getPageNo(), pageSize, total, boList);
    }

    /**
     * 根据 id 查询
     *
     * @param sensitiveWordsBO bo
     * @return 结果
     */
    public SensitiveWordsBO queryById(SensitiveWordsBO sensitiveWordsBO) {
        SensitiveWords entity = sensitiveWordsService.queryById(sensitiveWordsBO.getId());
        return SensitiveWordsBOConverter.INSTANCE.convertEntity2Bo(entity);
    }
}

