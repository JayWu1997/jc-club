package com.jingdianjichi.practice.api.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 练习表(PracticeInfo)实体类DTO
 *
 * @author jay
 * @since 2025-01-08 18:21:19
 */
@Data
public class PracticeInfoDTO implements Serializable {
    private static final long serialVersionUID = 236647882093092504L;
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
     * 套题id
     */
    private Long setId;
    /**
     * 是否完成 1完成 0未完成
     */
    private Integer completeStatus;
    /**
     * 用时
     */
    private String timeUse;
    /**
     * 交卷时间
     */
    private Date submitTime;
    /**
     * 正确率
     */
    private Double correctRate;
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

