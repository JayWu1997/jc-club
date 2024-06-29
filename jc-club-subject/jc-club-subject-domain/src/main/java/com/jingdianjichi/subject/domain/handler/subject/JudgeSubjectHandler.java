package com.jingdianjichi.subject.domain.handler.subject;

import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.convert.SubjectAnswerBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectJudge;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectJudgeService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 判断题处理器
 * @author jay
 * @since  2024/06/20
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
        ParamCheckUtil.checkCollNotEmpty(subjectInfoBO.getOptionList(), ResultCodeEnum.PARAM_ERROR, "判断题的答案不能为空");

        // 保存题目选项
        List<SubjectJudge> subjectJudgeList = SubjectAnswerBOConverter.INSTANCE.convertBOList2JudgeEntityList(subjectInfoBO.getOptionList());
        subjectJudgeList.forEach(subjectJudge -> {
            subjectJudge.setSubjectId(subjectInfoBO.getId());
            subjectJudge.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        });
        subjectJudgeService.insertBatch(subjectJudgeList);
    }
}
