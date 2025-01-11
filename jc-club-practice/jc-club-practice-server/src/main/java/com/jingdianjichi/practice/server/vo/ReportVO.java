package com.jingdianjichi.practice.server.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/11 下午12:47
 */
@Data
public class ReportVO {

    /**
     * 标题
     */
    private String title;

    /**
     * 正确题目数
     */
    private String correctSubject;

    /**
     * 技能图谱
     */
    private List<ReportSkillVO> skill;
}
