package com.jingdianjichi.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfo) DTO对象
 *
 * @author jay
 * @since 2024-06-18 18:13:19
 */
@Data
public class SubjectInfoDTO implements Serializable {
    private static final long serialVersionUID = -92870786840147434L;
    /**
     * 主键
     */
    private Long id;
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
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 分类 id 列表
     */
    private List<Long> categoryIds;
    /**
     * 标签 id 列表
     */
    private List<Long> labelIds;
    /**
     * 选项答案
     */
    private List<SubjectAnswerDTO> optionList;
}

