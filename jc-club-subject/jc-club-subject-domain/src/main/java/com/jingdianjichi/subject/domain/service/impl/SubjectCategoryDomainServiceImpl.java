package com.jingdianjichi.subject.domain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.common.util.GuavaCacheUtil;
import com.jingdianjichi.subject.domain.convert.SubjectCategoryBOConverter;
import com.jingdianjichi.subject.domain.convert.SubjectLabelBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import com.jingdianjichi.subject.infra.basic.service.SubjectCategoryService;
import com.jingdianjichi.subject.infra.basic.service.SubjectLabelService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import com.jingdianjichi.subject.infra.basic.service.impl.SubjectLabelServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    public static final String CACHE_KEY_PREFIX_SUBCATEGORY_AND_LABEL_LIST = "SubcategoryAndLabelList";
    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Resource
    private SubjectMappingService mappingService;

    @Resource
    private SubjectLabelService labelService;

    @Resource
    private ThreadPoolExecutor labelThreadPool;
    @Resource
    private SubjectLabelServiceImpl subjectLabelService;

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
        SubjectCategory existsQueryReq = new SubjectCategory();
        existsQueryReq.setCategoryName(subjectCategoryBO.getCategoryName());
        existsQueryReq.setParentId(subjectCategoryBO.getParentId());
        if (subjectCategoryService.exists(existsQueryReq)) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "当前岗位下该分类名已存在");
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
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        // 查询岗位信息
        SubjectCategory subjectCategory = SubjectCategoryBOConverter.INSTANCE.convertBo2Entity(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (CollectionUtils.isEmpty(categoryList)) {
            return Collections.emptyList();
        }

        // 查询每个岗位的题目数量
        List<SubjectCategoryBO> categoryBOList = categoryList.stream().map(category -> {
            SubjectCategoryBO categoryBO = SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(category);
            categoryBO.setCount(mappingService.countByCategoryIdDistinctSubjectId(category.getId()));
            return categoryBO;
        }).collect(Collectors.toList());
        return categoryBOList;
    }

    /**
     * 更新题目分类信息
     *
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
     *
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

    /**
     * 查询所有子类和子类的标签
     *
     * @param categoryBO 父类 id
     * @return 所有子类和子类的标签
     */
    @SneakyThrows
    @Override
    public List<SubjectCategoryBO> querySubcategoryAndLabelList(SubjectCategoryBO categoryBO) {
        String cacheKey = CACHE_KEY_PREFIX_SUBCATEGORY_AND_LABEL_LIST + categoryBO.getId();
        List<SubjectCategoryBO> boList = GuavaCacheUtil.getListFromCache(cacheKey, SubjectCategoryBO.class);
        if (ObjectUtil.isNull(boList)) {
            boList = querySubcategoryAndLabelListFromDB(categoryBO.getId());
            GuavaCacheUtil.put(cacheKey, boList);
        }
        return boList;
    }

    /**
     * 查询大类下的分类
     *
     * @param bo
     * @return
     */
    @Override
    public List<SubjectCategoryBO> queryPrimaryCategory(SubjectCategoryBO bo) {
        SubjectCategory queryCondition = SubjectCategoryBOConverter.INSTANCE.convertBo2Entity(bo);
        queryCondition.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(queryCondition);
        return SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(categoryList);
    }

    /**
     * 根据标签批量查询分类
     *
     * @param bo
     * @return
     */
    @Override
    public List<SubjectCategoryBO> queryByLabelIds(SubjectCategoryBO bo) {
        List<SubjectCategoryBO> boList = new ArrayList<>();
        List<SubjectCategory> entityList = subjectCategoryService.queryByLabelIds(bo.getLabelIdList());
        if (CollectionUtil.isNotEmpty(entityList)) {
            boList = SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(entityList);
        }
        return boList;
    }

    /**
     * 根据传入的次级分类名 categoryId 查询此次级分类所属的岗位
     *
     * @param bo
     * @return
     */
    @Override
    public SubjectCategoryBO queryPrimaryCategoryByCategoryId(SubjectCategoryBO bo) {
        SubjectCategory category = subjectCategoryService.queryById(bo.getId());
        while(category.getParentId() != 0) {
            category = subjectCategoryService.queryById(category.getParentId());
        }
        return SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(category);
    }

    /**
     * 根据主键批量查询 Category
     * @param bo
     * @return
     */
    @Override
    public List<SubjectCategoryBO> queryByIdList(SubjectCategoryBO bo) {
        List<SubjectCategory> entityList = subjectCategoryService.queryByIdList(bo.getIdList());
        return SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(entityList);
    }

    private List<SubjectCategoryBO> querySubcategoryAndLabelListFromDB(Long categoryId) {
        // 查询子分类
        SubjectCategory queryCondition = new SubjectCategory();
        queryCondition.setParentId(categoryId);
        queryCondition.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(queryCondition);

        // 查询结果为空直接返回空集合
        if (CollectionUtils.isEmpty(categoryList)) {
            return Collections.emptyList();
        }

        // 创建任务列表，用于异步查询与子类关联的标签
        List<CompletableFuture<SubjectCategoryBO>> completableFutureList = categoryList.stream().map(category ->
                CompletableFuture.supplyAsync(() -> querySubjectCategoryBO(category), labelThreadPool)).collect(Collectors.toList());
        return completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private SubjectCategoryBO querySubjectCategoryBO(SubjectCategory category) {
        List<SubjectLabel> labelList = subjectLabelService.queryDistinctLabelListByCategoryId(category.getId());
        SubjectCategoryBO boTemp = SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(category);
        boTemp.setLabelDTOList(SubjectLabelBOConverter.INSTANCE.convertEntity2BO(labelList));
        return boTemp;
    }

}
