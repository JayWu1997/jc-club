package com.jingdianjichi.circle.api.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息表(ShareMessage)实体类DTO
 *
 * @author jay
 * @since 2025-01-21 18:21:09
 */
@Data
public class ShareMessageDTO implements Serializable {
    private static final long serialVersionUID = 478749059329391904L;
    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 来自人
     */
    private String fromId;
    /**
     * 送达人
     */
    private String toId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否被阅读 1是 2否
     */
    private Integer isRead;
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

