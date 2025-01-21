package com.jingdianjichi.circle.infra.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 敏感词表(SensitiveWords)实体类
 *
 * @author jay
 * @since 2025-01-21 18:13:42
 */
@Data
public class SensitiveWords implements Serializable {
    private static final long serialVersionUID = -47606046774003862L;
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
}

