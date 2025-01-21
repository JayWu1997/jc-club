package com.jingdianjichi.circle.domain.service.impl;

import com.jingdianjichi.circle.domain.convert.ShareMessageBOConverter;
import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.ShareMessageBO;
import com.jingdianjichi.circle.domain.service.ShareMessageDomainService;
import com.jingdianjichi.circle.infra.mybatis.entity.ShareMessage;
import com.jingdianjichi.circle.infra.mybatis.service.ShareMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息表(ShareMessage)领域服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:17:09
 */
@Service
public class ShareMessageDomainServiceImpl implements ShareMessageDomainService {

    @Resource
    private ShareMessageService shareMessageService;

    /**
     * 新增
     *
     * @param shareMessageBO 新增的对象
     * @return 结果
     */
    public Boolean add(ShareMessageBO shareMessageBO) {
        return 1 == shareMessageService.insert(ShareMessageBOConverter.INSTANCE.convertBo2Entity(shareMessageBO));
    }

    /**
     * 更新
     *
     * @param shareMessageBO 更新信息
     * @return 结果
     */
    public Boolean update(ShareMessageBO shareMessageBO) {
        return 1 == shareMessageService.update(ShareMessageBOConverter.INSTANCE.convertBo2Entity(shareMessageBO));
    }

    /**
     * 删除
     *
     * @param shareMessageBO 待删除的对象
     * @return 结果
     */
    public Boolean deleteById(ShareMessageBO shareMessageBO) {
        return 1 == shareMessageService.deleteById(shareMessageBO.getId());
    }

    /**
     * 分页查询
     *
     * @param shareMessageBO bo
     * @return 分页结果
     */
    public PageResult<ShareMessageBO> queryByPage(ShareMessageBO shareMessageBO) {
        shareMessageBO.setIsDeleted(0);

        int start = (shareMessageBO.getPageNo() - 1) * shareMessageBO.getPageSize();
        int pageSize = shareMessageBO.getPageSize();
        int total = (int) shareMessageService.count(ShareMessageBOConverter.INSTANCE.convertBo2Entity(shareMessageBO));
        List<ShareMessage> entityList = shareMessageService.queryByPage(ShareMessageBOConverter.INSTANCE.convertBo2Entity(shareMessageBO), start, pageSize);
        List<ShareMessageBO> boList = ShareMessageBOConverter.INSTANCE.convertEntity2Bo(entityList);
        return new PageResult<>(shareMessageBO.getPageNo(), pageSize, total, boList);
    }

    /**
     * 根据 id 查询
     *
     * @param shareMessageBO bo
     * @return 结果
     */
    public ShareMessageBO queryById(ShareMessageBO shareMessageBO) {
        ShareMessage entity = shareMessageService.queryById(shareMessageBO.getId());
        return ShareMessageBOConverter.INSTANCE.convertEntity2Bo(entity);
    }
}

