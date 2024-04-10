package com.jingdianjichi.subject.domain.service;

import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;

/**
 * 主题类别领域服务接口
 * 该接口定义了与主题类别相关的领域业务操作。
 */
public interface SubjectCategoryDomainService {
    /**
     * 添加主题类别信息。
     *
     * @param subjectCategoryBO 主题类别对象，包含要添加的主题类别信息。
     * @return 返回添加后的主题类别对象，包含已存在于数据库中的主题类别信息。
     */
    SubjectCategoryBO add(SubjectCategoryBO subjectCategoryBO);
}
