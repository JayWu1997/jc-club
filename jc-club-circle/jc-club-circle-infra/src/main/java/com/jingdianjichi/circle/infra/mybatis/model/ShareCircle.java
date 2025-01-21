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
 * 圈子信息
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@Getter
@Setter
@ToString
@TableName("share_circle")
public class ShareCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 圈子ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID,-1为大类
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 圈子名称
     */
    @TableField("circle_name")
    private String circleName;

    /**
     * 圈子图片
     */
    @TableField("icon")
    private String icon;

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
