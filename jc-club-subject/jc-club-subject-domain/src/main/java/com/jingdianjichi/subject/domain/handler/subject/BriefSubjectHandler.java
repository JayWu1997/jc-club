package com.jingdianjichi.subject.domain.handler.subject;

import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.domain.convert.SubjectAnswerBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectBrief;
import com.jingdianjichi.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 简答题处理器
 *
 * @author jay
 * @since 2024/06/20
 */
@Component
public class BriefSubjectHandler implements SubjectTypeHandler {

    @Resource
    private SubjectBriefService subjectBriefService;

    /**
     * 题目类型识别
     *
     * @return 返回对应的题目类型
     */
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    /**
     * 添加题目
     *
     * @param subjectInfoBO 题目信息
     */
    @Override
    public void insert(SubjectInfoBO subjectInfoBO) {
        // 保存题目选项
        SubjectBrief subjectBrief = SubjectAnswerBOConverter.INSTANCE.convertSubjectInfoBO2BriefEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    /**
     * 查询题目信息
     *
     * @param subjectId@return 返回查询到的题目信息对象；如果未找到相关信息，则返回null
     */
    @Override
    public SubjectOptionBO querySubjectOptions(Long subjectId) {
        SubjectBrief subjectBrief = subjectBriefService.queryBySubjectId(subjectId);
        SubjectOptionBO subjectOptionBO = null;
        if (subjectBrief != null) {
            subjectOptionBO = new SubjectOptionBO();
            subjectOptionBO.setSubjectAnswer(subjectBrief.getSubjectAnswer());
        }
        return subjectOptionBO;
    }
}
