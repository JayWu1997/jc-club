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
 * 动态信息
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@Getter
@Setter
@ToString
@TableName("share_moment")
public class ShareMoment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 圈子ID
     */
    @TableField("circle_id")
    private Long circleId;

    /**
     * 动态内容
     */
    @TableField("content")
    private String content;

    /**
     * 动态图片内容
     */
    @TableField("pic_urls")
    private String picUrls;

    /**
     * 回复数
     */
    @TableField("reply_count")
    private Integer replyCount;

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
