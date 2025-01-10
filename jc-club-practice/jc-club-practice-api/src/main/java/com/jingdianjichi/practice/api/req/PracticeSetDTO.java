package com.jingdianjichi.practice.api.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 套题信息表(PracticeSet)实体类DTO
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Data
public class PracticeSetDTO implements Serializable {
    private static final long serialVersionUID = -31371053431248924L;
    /**
     * 组装id集合, eg: ["categoryId-labelId"]
     */
    private List<String> assembleIds;
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
     * 套题名称
     */
    private String setName;
    /**
     * 套题类型 1实时生成 2预设套题
     */
    private Integer setType;
    /**
     * 热度
     */
    private Integer setHeat;
    /**
     * 套题描述
     */
    private String setDesc;
    /**
     * 大类id
     */
    private Long primaryCategoryId;
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

