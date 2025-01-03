package com.jingdianjichi.subject.infra.basic.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 题目信息表(SubjectInfo)实体类
 *
 * @author jay
 * @since 2024-06-18 18:13:19
 */
@Data
public class SubjectInfoEs implements Serializable {
    private static final long serialVersionUID = -92870786840147434L;
    /**
     * 主键
     */
    private Long docId;
    private Long subjectId;
    private String keyword;
    private BigDecimal score;
    private String subjectAnswer;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private Long updateTime;
    /**
     * 删除标识
     */
    private Integer isDeleted;
}

