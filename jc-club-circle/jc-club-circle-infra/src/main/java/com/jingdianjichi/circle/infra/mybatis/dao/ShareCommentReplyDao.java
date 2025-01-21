package com.jingdianjichi.circle.infra.mybatis.dao;

import com.jingdianjichi.circle.infra.mybatis.entity.ShareCommentReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 评论及回复信息(ShareCommentReply)表数据库访问层
 *
 * @author jay
 * @since 2025-01-21 18:13:43
 */
@Mapper
public interface ShareCommentReplyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShareCommentReply queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param shareCommentReply 查询条件
     * @return 符合条件的查询结果
     */
    List<ShareCommentReply> queryByPage(@Param("entity") ShareCommentReply shareCommentReply,
                                        @Param("start") int start,
                                        @Param("size") int size);

    /**
     * 统计总行数
     *
     * @param shareCommentReply 查询条件
     * @return 总行数
     */
    long count(ShareCommentReply shareCommentReply);

    /**
     * 新增数据
     *
     * @param shareCommentReply 实例对象
     * @return 影响行数
     */
    int insert(ShareCommentReply shareCommentReply);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShareCommentReply> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ShareCommentReply> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShareCommentReply> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ShareCommentReply> entities);

    /**
     * 修改数据
     *
     * @param shareCommentReply 实例对象
     * @return 影响行数
     */
    int update(ShareCommentReply shareCommentReply);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

