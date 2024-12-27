package com.jingdianjichi.subject.domain.handler.subject;

import com.google.common.collect.Lists;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectJudge;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectJudgeService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 判断题处理器
 *
 * @author jay
 * @since 2024/06/20
 */
@Component
public class JudgeSubjectHandler implements SubjectTypeHandler {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Resource
    private SubjectMappingService subjectMappingService;

    /**
     * 题目类型识别
     *
     * @return 返回对应的题目类型
     */
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    /**
     * 添加题目
     *
     * @param subjectInfoBO 题目信息
     */
    @Override
    public void insert(SubjectInfoBO subjectInfoBO) {
        ParamCheckUtil.checkCollNotEmpty(subjectInfoBO.getOptionList(), BusinessErrorEnum.PARAM_ERROR, "判断题的答案不能为空");

        // 保存题目选项
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        subjectJudgeService.insert(subjectJudge);
    }

    /**
     * 查询题目信息
     *
     * @param subjectId@return 返回查询到的题目信息对象；如果未找到相关信息，则返回null
     */
    @Override
    public SubjectOptionBO querySubjectOptions(Long subjectId) {
        SubjectJudge subjectJudge = subjectJudgeService.queryBySubjectId(subjectId);
        SubjectOptionBO subjectOptionBO = null;
        if (subjectJudge != null) {
            subjectOptionBO = new SubjectOptionBO();
            SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();
            subjectAnswerBO.setIsCorrect(subjectJudge.getIsCorrect());
            subjectOptionBO.setOptionList(Lists.newArrayList(subjectAnswerBO));
        }
        return subjectOptionBO;
    }
}
