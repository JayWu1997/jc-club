package com.jingdianjichi.circle.infra.mybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@Getter
@Setter
@ToString
@TableName("share_message")
public class ShareMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 来自人
     */
    @TableField("from_id")
    private String fromId;

    /**
     * 送达人
     */
    @TableField("to_id")
    private String toId;

    /**
     * 消息内容
     */
    @TableField("content")
    private String content;

    /**
     * 是否被阅读 1是 2否
     */
    @TableField("is_read")
    private Integer isRead;

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
