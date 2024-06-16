package com.jingdianjichi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.domain.convert.SubjectCategoryBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import com.jingdianjichi.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    /**
     * 添加一个主题分类信息
     *
     * @param subjectCategoryBO 主题分类业务对象，包含将要添加的主题分类信息
     * @return 返回添加后的主题分类业务对象
     */
    public SubjectCategoryBO add(SubjectCategoryBO subjectCategoryBO) {
        // 如果日志级别允许信息输出，则记录添加的主题分类信息
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.bo:{}", JSON.toJSON(subjectCategoryBO));
        }
        // 将业务对象转换为实体对象
        SubjectCategory subjectCategory = SubjectCategoryBOConverter.INSTANCE.convertBo2Entity(subjectCategoryBO);
        // 查询当前分类名对应的分类是否存在
        if (subjectCategoryService.queryByCategoryName(subjectCategory.getCategoryName()) != null) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "分类名已存在");
        }
        // 调用服务插入实体对象到数据库
        subjectCategory.setIsDeleted(0);
        subjectCategory.setCreatedTime(new Date());
        subjectCategoryService.insert(subjectCategory);
        // 将插入后的实体对象转换回业务对象并返回
        SubjectCategoryBO result = SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(subjectCategory);
        // 如果日志级别允许信息输出，则记录执行结果
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.result:{}", JSON.toJSON(result));
        }
        return result;
    }

    /**
     * 查询所有岗位。
     *
     * @return 返回所有岗位。
     */
    @Override
    public List<SubjectCategoryBO> queryPrimaryCategory() {
        List<SubjectCategory> entityList = subjectCategoryService.queryPrimaryCategory();
        return SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(entityList);
    }

    /**
     * 更新题目分类信息
     * @param subjectCategoryBO 待更新的主题类别信息，通过 categoryName 检索
     * @return 成功返回 true， 否则返回 false
     */
    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        int count = subjectCategoryService.update(SubjectCategoryBOConverter.INSTANCE.convertBo2Entity(subjectCategoryBO));
        return count > 0;
    }

    /**
     * 删除题目分类
     * @param subjectCategoryBO 待删除的主题类别信息，通过 id 检索
     * @return 成功返回 true， 否则返回 false
     */
    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setId(subjectCategoryBO.getId());
        subjectCategory.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        return subjectCategoryService.update(subjectCategory) == 1;
    }

}
