package com.jingdianjichi.circle.api.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 敏感词表(SensitiveWords)实体类DTO
 *
 * @author jay
 * @since 2025-01-21 18:21:09
 */
@Data
public class SensitiveWordsDTO implements Serializable {
    private static final long serialVersionUID = -37896098298157386L;
    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * id
     */
    private Long id;
    /**
     * 内容
     */
    private String words;
    /**
     * 1=黑名单 2=白名单
     */
    private Integer type;
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

