package com.jingdianjichi.subject.domain.service;

import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.domain.entity.SubjectLikedBO;

/**
 * 题目点赞表(SubjectLiked)领域服务
 *
 * @author jay
 * @since 2025-01-06 15:39:33
 */
public interface SubjectLikedDomainService {

    /**
     * 新增
     *
     * @param subjectLikedBO 新增的对象
     * @return 结果
     */
    Boolean add(SubjectLikedBO subjectLikedBO);

    /**
     * 更新
     *
     * @param subjectLikedBO 更新信息
     * @return 结果
     */
    Boolean update(SubjectLikedBO subjectLikedBO);

    /**
     * 删除
     *
     * @param subjectLikedBO 待删除的对象
     * @return 结果
     */
    Boolean deleteById(SubjectLikedBO subjectLikedBO);

    /**
     * 分页查询
     *
     * @param subjectLikedBO bo
     * @return 分页结果
     */
    PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO);

    /**
     * 根据 id 查询
     *
     * @param subjectLikedBO bo
     * @return 结果
     */
    SubjectLikedBO queryById(SubjectLikedBO subjectLikedBO);

    /**
     * 查询当前用户是否点赞过
     * @param subjectLikedBO bo
     * @return 点赞结果
     */
    Boolean isLiked(SubjectLikedBO subjectLikedBO);

    /**
     * 查询题目点赞数量
     * @param subjectLikedBO bo
     * @return 点赞数量
     */
    Long likedNum(SubjectLikedBO subjectLikedBO);

    /**
     * 同步点赞信息
     */
    void syncLiked(int size);
}

