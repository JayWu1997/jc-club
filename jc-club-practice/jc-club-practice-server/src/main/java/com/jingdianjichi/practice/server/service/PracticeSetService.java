package com.jingdianjichi.practice.server.service;

import com.jingdianjichi.practice.api.req.PracticeSetDTO;
import com.jingdianjichi.practice.server.req.GetPracticeSubjectReq;
import com.jingdianjichi.practice.server.req.GetSubjectsReq;
import com.jingdianjichi.practice.server.vo.GetSubjectsVO;
import com.jingdianjichi.practice.server.vo.PracticeSetVO;
import com.jingdianjichi.practice.server.vo.PracticeSubjectVO;
import com.jingdianjichi.practice.server.vo.SpecialPracticeVO;
import com.jingdianjichi.practice.server.entity.PracticeSet;

import java.util.List;

/**
 * 套题信息表(PracticeSet)表服务接口
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
public interface PracticeSetService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PracticeSet queryById(Long id);

    /**
     * 通过条件查询所有数据
     *
     * @param practiceSet 查询条件
     * @return 查询结果集合
     */
    List<PracticeSet> queryByPage(PracticeSet practiceSet
            , int start
            , int size);

    /**
     * 查询符合条件的结果数量
     *
     * @param practiceSet 查询条件
     * @return 符合条件的结果数量
     */
    long count(PracticeSet practiceSet);

    /**
     * 新增数据
     *
     * @param dto 实例对象
     * @return 实例对象
     */
    PracticeSetVO insert(PracticeSetDTO dto);

    /**
     * 批量新增数据
     *
     * @param practiceSetList 实例对象列表
     * @return 成功数目
     */
    int insertBatch(List<PracticeSet> practiceSetList);

    /**
     * 修改数据
     *
     * @param practiceSet 实例对象
     * @return 实例对象
     */
    int update(PracticeSet practiceSet);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 获取专项练习标签列表
     *
     * @return
     */
    List<SpecialPracticeVO> getSpecialPracticeContent();

    /**
     * 根据 paracticeId 或者 setId 获取套题题目列表
     * @param req
     * @return
     */
    GetSubjectsVO getSubjects(GetSubjectsReq req);

    /**
     * 根据题目 id 获取题目信息
     * @param req
     * @return
     */
    PracticeSubjectVO getPracticeSubject(GetPracticeSubjectReq req);
}
