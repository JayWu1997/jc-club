package com.jingdianjichi.circle.infra.mybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 敏感词表
 * </p>
 *
 * @author jay
 * @since 2025-01-21
 */
@Getter
@Setter
@ToString
@TableName("sensitive_words")
public class SensitiveWords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容
     */
    @TableField("words")
    private String words;

    /**
     * 1=黑名单 2=白名单
     */
    @TableField("type")
    private Integer type;

    /**
     * 是否被删除 0为删除 1已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
}
