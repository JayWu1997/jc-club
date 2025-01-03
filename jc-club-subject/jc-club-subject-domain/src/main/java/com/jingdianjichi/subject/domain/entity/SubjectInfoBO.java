package com.jingdianjichi.subject.domain.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfo) BO对象
 *
 * @author jay
 * @since 2024-06-18 18:13:19
 */
@Data
public class SubjectInfoBO implements Serializable {
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
     * 标签名称
     */
    private List<String> labelNames;
    /**
     * 选项答案
     */
    private List<SubjectAnswerBO> optionList;
    private String createdBy;
    private Long createdTime;
    private String updateBy;
    private Long updateTime;
    /**
     * 分类 id
     */
    private Long categoryId;
    /**
     * 标签 id
     */
    private Long labelId;
    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 获取页码，如果页码为空或者小于1，则返回1
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

