package com.jingdianjichi.subject.domain.handler.subject;

import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.convert.SubjectAnswerBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectBrief;
import com.jingdianjichi.subject.infra.basic.service.SubjectBriefService;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简答题处理器
 * @author jay
 * @since  2024/06/20
 */
@Component
public class BriefSubjectHandler implements SubjectTypeHandler {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectBriefService subjectBriefService;

    @Resource
    private SubjectMappingService subjectMappingService;

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
        ParamCheckUtil.checkCollNotEmpty(subjectInfoBO.getOptionList(), ResultCodeEnum.PARAM_ERROR, "简答题的答案不能为空");

        // 保存题目选项
        List<SubjectBrief> subjectBriefList = SubjectAnswerBOConverter.INSTANCE.convertBOList2BriefEntityList(subjectInfoBO.getOptionList());
        subjectBriefList.forEach(subjectBrief -> {
            subjectBrief.setSubjectId(subjectInfoBO.getId());
            subjectBrief.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        });
        subjectBriefService.insertBatch(subjectBriefList);
    }
}
