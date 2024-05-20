package com.jingdianjichi.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.domain.convert.SubjectCategoryBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import com.jingdianjichi.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
            //TODO 异常处理
            throw new RuntimeException("分类名已存在");
        }
        // 调用服务插入实体对象到数据库
        subjectCategoryService.insert(subjectCategory);
        // 将插入后的实体对象转换回业务对象并返回
        SubjectCategoryBO result = SubjectCategoryBOConverter.INSTANCE.convertEntity2Bo(subjectCategory);
        // 如果日志级别允许信息输出，则记录执行结果
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.result:{}", JSON.toJSON(result));
        }
        return result;
    }

}
