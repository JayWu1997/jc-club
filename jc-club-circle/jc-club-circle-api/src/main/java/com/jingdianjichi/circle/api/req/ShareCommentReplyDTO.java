package com.jingdianjichi.circle.api.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论及回复信息(ShareCommentReply)实体类DTO
 *
 * @author jay
 * @since 2025-01-21 18:21:09
 */
@Data
public class ShareCommentReplyDTO implements Serializable {
    private static final long serialVersionUID = -22406339019358682L;
    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 评论ID
     */
    private Long id;
    /**
     * 原始动态ID
     */
    private Integer momentId;
    /**
     * 回复类型 1评论 2回复
     */
    private Integer replyType;
    /**
     * 评论目标id
     */
    private Long toId;
    /**
     * 评论人
     */
    private String toUser;
    /**
     * 评论人是否作者 1=是 0=否
     */
    private Integer toUserAuthor;
    /**
     * 回复目标id
     */
    private Long replyId;
    /**
     * 回复人
     */
    private String replyUser;
    /**
     * 回复人是否作者 1=是 0=否
     */
    private Integer replayAuthor;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片内容
     */
    private String picUrls;

    private Integer parentId;

    private String leafNode;

    private String children;

    private String rootNode;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否被删除 0为删除 1已删除
     */
    private Integer isDeleted;

    /**
     * 获取页码，如果页码为空或者小于1，则返回1
     *
     * @return 当前的页码
     */
    public Integer getPageNo() {
        if (pageNo == null) {
            return null;
        }
        if (pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    /**
     * 获取每页显示的记录数，如果记录数为空或者小于1或者大于100，则返回20
     *
     * @return 每页显示的记录数
     */
    public Integer getPageSize() {
        if (pageSize == null) {
            return null;
        }
        if (pageSize < 1 || pageSize > 100) {
            return 20;
        }
        return pageSize;
    }
}

