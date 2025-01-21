package com.jingdianjichi.circle.domain.service.impl;

import com.jingdianjichi.circle.domain.convert.ShareMomentBOConverter;
import com.jingdianjichi.circle.domain.entity.PageResult;
import com.jingdianjichi.circle.domain.entity.ShareMomentBO;
import com.jingdianjichi.circle.domain.service.ShareMomentDomainService;
import com.jingdianjichi.circle.infra.mybatis.entity.ShareMoment;
import com.jingdianjichi.circle.infra.mybatis.service.ShareMomentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 动态信息(ShareMoment)领域服务实现类
 *
 * @author jay
 * @since 2025-01-21 18:17:10
 */
@Service
public class ShareMomentDomainServiceImpl implements ShareMomentDomainService {

    @Resource
    private ShareMomentService shareMomentService;

    /**
     * 新增
     *
     * @param shareMomentBO 新增的对象
     * @return 结果
     */
    public Boolean add(ShareMomentBO shareMomentBO) {
        return 1 == shareMomentService.insert(ShareMomentBOConverter.INSTANCE.convertBo2Entity(shareMomentBO));
    }

    /**
     * 更新
     *
     * @param shareMomentBO 更新信息
     * @return 结果
     */
    public Boolean update(ShareMomentBO shareMomentBO) {
        return 1 == shareMomentService.update(ShareMomentBOConverter.INSTANCE.convertBo2Entity(shareMomentBO));
    }

    /**
     * 删除
     *
     * @param shareMomentBO 待删除的对象
     * @return 结果
     */
    public Boolean deleteById(ShareMomentBO shareMomentBO) {
        return 1 == shareMomentService.deleteById(shareMomentBO.getId());
    }

    /**
     * 分页查询
     *
     * @param shareMomentBO bo
     * @return 分页结果
     */
    public PageResult<ShareMomentBO> queryByPage(ShareMomentBO shareMomentBO) {
        shareMomentBO.setIsDeleted(0);

        int start = (shareMomentBO.getPageNo() - 1) * shareMomentBO.getPageSize();
        int pageSize = shareMomentBO.getPageSize();
        int total = (int) shareMomentService.count(ShareMomentBOConverter.INSTANCE.convertBo2Entity(shareMomentBO));
        List<ShareMoment> entityList = shareMomentService.queryByPage(ShareMomentBOConverter.INSTANCE.convertBo2Entity(shareMomentBO), start, pageSize);
        List<ShareMomentBO> boList = ShareMomentBOConverter.INSTANCE.convertEntity2Bo(entityList);
        return new PageResult<>(shareMomentBO.getPageNo(), pageSize, total, boList);
    }

    /**
     * 根据 id 查询
     *
     * @param shareMomentBO bo
     * @return 结果
     */
    public ShareMomentBO queryById(ShareMomentBO shareMomentBO) {
        ShareMoment entity = shareMomentService.queryById(shareMomentBO.getId());
        return ShareMomentBOConverter.INSTANCE.convertEntity2Bo(entity);
    }
}

