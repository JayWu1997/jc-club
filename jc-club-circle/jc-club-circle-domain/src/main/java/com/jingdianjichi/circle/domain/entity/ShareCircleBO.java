package com.jingdianjichi.circle.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 圈子信息(ShareCircle)实体类BO
 *
 * @author jay
 * @since 2025-01-21 18:17:07
 */
@Data
public class ShareCircleBO implements Serializable {
    private static final long serialVersionUID = -78724934289998890L;

    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 圈子ID
     */
    private Long id;
    /**
     * 父级ID,-1为大类
     */
    private Long parentId;
    /**
     * 圈子名称
     */
    private String circleName;
    /**
     * 圈子图片
     */
    private String icon;
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

