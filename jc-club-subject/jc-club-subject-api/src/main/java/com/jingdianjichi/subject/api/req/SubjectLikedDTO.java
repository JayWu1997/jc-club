package com.jingdianjichi.subject.api.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目点赞表(SubjectLiked)实体类DTO
 *
 * @author jay
 * @since 2025-01-06 15:41:28
 */
@Data
public class SubjectLikedDTO implements Serializable {
    private static final long serialVersionUID = -93657617720806598L;
    /**
     * 题目名称
     */
    private String subjectName;
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
    private Long id;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 点赞人id
     */
    private String likeUserId;
    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private Date updateTime;

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

