package com.jingdianjichi.practice.server.service;

import com.jingdianjichi.practice.server.entity.PracticeDetail;
import com.jingdianjichi.practice.server.req.SubmitPracticeDetailReq;
import com.jingdianjichi.practice.server.req.SubmitSubjectReq;

import java.util.List;

/**
 * 练习详情表(PracticeDetail)表服务接口
 *
 * @author jay
 * @since 2025-01-08 18:21:19
 */
public interface PracticeDetailService {

    Boolean submit(SubmitPracticeDetailReq req);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PracticeDetail queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param practiceDetail 查询条件
     * @return 查询结果集合
     */
    List<PracticeDetail> queryByPage(PracticeDetail practiceDetail
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceDetail 查询条件
     * @return 符合条件的结果数量
     */
    long count(PracticeDetail practiceDetail);

    /**
     * 新增数据
     *
     * @param practiceDetail 实例对象
     * @return 实例对象
     */
    int insert(PracticeDetail practiceDetail);

    /**
     * 批量新增数据
     *
     * @param practiceDetailList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<PracticeDetail> practiceDetailList);

    /**
     * 修改数据
     *
     * @param practiceDetail 实例对象
     * @return 实例对象
     */
    int update(PracticeDetail practiceDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 提交题目
     * @param req
     * @return
     */
    Boolean submitSubject(SubmitSubjectReq req);
}
