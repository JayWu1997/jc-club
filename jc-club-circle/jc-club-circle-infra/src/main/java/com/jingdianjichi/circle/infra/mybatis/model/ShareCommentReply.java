package com.jingdianjichi.circle.infra.mybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论及回复信息
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@Getter
@Setter
@ToString
@TableName("share_comment_reply")
public class ShareCommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 原始动态ID
     */
    @TableField("moment_id")
    private Integer momentId;

    /**
     * 回复类型 1评论 2回复
     */
    @TableField("reply_type")
    private Integer replyType;

    /**
     * 评论目标id
     */
    @TableField("to_id")
    private Long toId;

    /**
     * 评论人
     */
    @TableField("to_user")
    private String toUser;

    /**
     * 评论人是否作者 1=是 0=否
     */
    @TableField("to_user_author")
    private Integer toUserAuthor;

    /**
     * 回复目标id
     */
    @TableField("reply_id")
    private Long replyId;

    /**
     * 回复人
     */
    @TableField("reply_user")
    private String replyUser;

    /**
     * 回复人是否作者 1=是 0=否
     */
    @TableField("replay_author")
    private Integer replayAuthor;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 图片内容
     */
    @TableField("pic_urls")
    private String picUrls;

    @TableField("parent_id")
    private Long parentId;

    @TableField("leaf_node")
    private String leafNode;

    @TableField("children")
    private String children;

    @TableField("root_node")
    private String rootNode;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否被删除 0为删除 1已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
}
