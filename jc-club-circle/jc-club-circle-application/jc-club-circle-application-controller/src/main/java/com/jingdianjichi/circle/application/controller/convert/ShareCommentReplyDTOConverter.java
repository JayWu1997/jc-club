package com.jingdianjichi.circle.application.controller.convert;

import com.jingdianjichi.circle.api.req.ShareCommentReplyDTO;
import com.jingdianjichi.circle.domain.entity.ShareCommentReplyBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 评论及回复信息(ShareCommentReply)提供了ShareCommentReply从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 *
 * @author jay
 * @since 2025-01-21 18:21:09
 */
@Mapper
public interface ShareCommentReplyDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    ShareCommentReplyDTOConverter INSTANCE = Mappers.getMapper(ShareCommentReplyDTOConverter.class);

    /**
     * DTO转换为BO
     *
     * @param shareCommentReplyDTO DTO
     * @return BO
     */
    ShareCommentReplyBO convertDto2Bo(ShareCommentReplyDTO shareCommentReplyDTO);

    /**
     * BO转换为DTO。
     *
     * @param shareCommentReplyBO BO
     * @return 转换后的DTO。
     */
    ShareCommentReplyDTO convertBo2Dto(ShareCommentReplyBO shareCommentReplyBO);

    /**
     * 将BO列表转换为DTO列表。
     *
     * @param shareCommentReplyBOList 待转换的BO列表。
     * @return 转换后得到的DTO列表。
     */
    List<ShareCommentReplyDTO> convertBo2Dto(List<ShareCommentReplyBO> shareCommentReplyBOList);

    /**
     * 将DTO列表转换为BO列表。
     *
     * @param shareCommentReplyDTOList 待转换的DTO列表。
     * @return 转换后得到的BO列表。
     */
    List<ShareCommentReplyBO> convertDto2Bo(List<ShareCommentReplyDTO> shareCommentReplyDTOList);

}


