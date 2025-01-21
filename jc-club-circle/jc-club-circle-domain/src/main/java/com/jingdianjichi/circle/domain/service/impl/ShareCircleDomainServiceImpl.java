package com.jingdianjichi.circle.domain.service.impl;

import com.jingdianjichi.circle.domain.convert.ShareCircleBOConverter;
import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.ShareCircleBO;
import com.jingdianjichi.circle.domain.service.ShareCircleDomainService;
import com.jingdianjichi.circle.infra.mybatis.entity.ShareCircle;
import com.jingdianjichi.circle.infra.mybatis.service.ShareCircleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 圈子信息(ShareCircle)领域服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:17:08
 */
@Service
public class ShareCircleDomainServiceImpl implements ShareCircleDomainService {

    @Resource
    private ShareCircleService shareCircleService;

    /**
     * 新增
     *
     * @param shareCircleBO 新增的对象
     * @return 结果
     */
    public Boolean add(ShareCircleBO shareCircleBO) {
        return 1 == shareCircleService.insert(ShareCircleBOConverter.INSTANCE.convertBo2Entity(shareCircleBO));
    }

    /**
     * 更新
     *
     * @param shareCircleBO 更新信息
     * @return 结果
     */
    public Boolean update(ShareCircleBO shareCircleBO) {
        return 1 == shareCircleService.update(ShareCircleBOConverter.INSTANCE.convertBo2Entity(shareCircleBO));
    }

    /**
     * 删除
     *
     * @param shareCircleBO 待删除的对象
     * @return 结果
     */
    public Boolean deleteById(ShareCircleBO shareCircleBO) {
        return 1 == shareCircleService.deleteById(shareCircleBO.getId());
    }

    /**
     * 分页查询
     *
     * @param shareCircleBO bo
     * @return 分页结果
     */
    public PageResult<ShareCircleBO> queryByPage(ShareCircleBO shareCircleBO) {
        shareCircleBO.setIsDeleted(0);

        int start = (shareCircleBO.getPageNo() - 1) * shareCircleBO.getPageSize();
        int pageSize = shareCircleBO.getPageSize();
        int total = (int) shareCircleService.count(ShareCircleBOConverter.INSTANCE.convertBo2Entity(shareCircleBO));
        List<ShareCircle> entityList = shareCircleService.queryByPage(ShareCircleBOConverter.INSTANCE.convertBo2Entity(shareCircleBO), start, pageSize);
        List<ShareCircleBO> boList = ShareCircleBOConverter.INSTANCE.convertEntity2Bo(entityList);
        return new PageResult<>(shareCircleBO.getPageNo(), pageSize, total, boList);
    }

    /**
     * 根据 id 查询
     *
     * @param shareCircleBO bo
     * @return 结果
     */
    public ShareCircleBO queryById(ShareCircleBO shareCircleBO) {
        ShareCircle entity = shareCircleService.queryById(shareCircleBO.getId());
        return ShareCircleBOConverter.INSTANCE.convertEntity2Bo(entity);
    }
}

