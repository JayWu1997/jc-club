package com.jingdianjichi.circle.domain.convert;

import com.jingdianjichi.circle.domain.entity.ShareCommentReplyBO;
import com.jingdianjichi.circle.infra.mybatis.model.ShareCommentReply;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

/**
 * 评论及回复信息(ShareCommentReply)提供了ShareCommentReply从Entity到BO以及从BO到Entity的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:17:08
 */
@Mapper
public interface ShareCommentReplyBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    ShareCommentReplyBOConverter INSTANCE = Mappers.getMapper(ShareCommentReplyBOConverter.class);

    /**
     * BO转换为Entity
     *
     * @param shareCommentReplyBO BO
     * @return 转换得到的Entity
     */
    ShareCommentReply convertBo2Entity(ShareCommentReplyBO shareCommentReplyBO);

    default String map(List<ShareCommentReplyBO> value) {
        return "";
    }

    /**
     * Entity转换为BO。
     *
     * @param shareCommentReply Entity
     * @return 转换后的BO。
     */
    ShareCommentReplyBO convertEntity2Bo(ShareCommentReply shareCommentReply);

    default List<ShareCommentReplyBO> map(String value) {
        return Collections.emptyList();
    }
    /**
     * 将Entity列表转换为BO列表。
     *
     * @param shareCommentReplyList 待转换的Entity。
     * @return 转换后得到的BO列表。
     */
    List<ShareCommentReplyBO> convertEntity2Bo(List<ShareCommentReply> shareCommentReplyList);

    /**
     * 将BO列表转换为Entity列表。
     *
     * @param shareCommentReplyBOList 待转换的BO。
     * @return 转换后得到的Entity列表。
     */
    List<ShareCommentReply> convertBo2Entity(List<ShareCommentReplyBO> shareCommentReplyBOList);
}


