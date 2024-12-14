package com.jingdianjichi.subject.domain.handler.subject;

import cn.hutool.core.collection.CollectionUtil;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.convert.SubjectAnswerBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectRadio;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import com.jingdianjichi.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单选题处理器
 * @author jay
 * @since  2024/06/20
 */
@Component
public class RadioSubjectHandler implements SubjectTypeHandler {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectRadioService subjectRadioService;

    @Resource
    private SubjectMappingService subjectMappingService;

    /**
     * 题目类型识别
     *
     * @return 返回对应的题目类型
     */
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    /**
     * 添加题目
     *
     * @param subjectInfoBO 题目信息
     */
    @Override
    public void insert(SubjectInfoBO subjectInfoBO) {
        ParamCheckUtil.checkCollNotEmpty(subjectInfoBO.getOptionList(), ResultCodeEnum.PARAM_ERROR, "单选题的选项不能为空");

        // 保存题目选项
        List<SubjectRadio> subjectRadioList = SubjectAnswerBOConverter.INSTANCE.convertBOList2RadioEntityList(subjectInfoBO.getOptionList());
        subjectRadioList.forEach(subjectRadio -> {
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        });
        subjectRadioService.insertBatch(subjectRadioList);
    }

    /**
     * 查询题目信息
     *
     * @param subjectId@return 返回查询到的题目信息对象；如果未找到相关信息，则返回null
     */
    @Override
    public SubjectOptionBO querySubjectOptions(Long subjectId) {
        List<SubjectRadio> subjectRadioList = subjectRadioService.queryBySubjectId(subjectId);
        SubjectOptionBO subjectOptionBO = null;
        if (CollectionUtil.isNotEmpty(subjectRadioList)) {
            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerBOConverter.INSTANCE.convertsubjectRadioList2SubjectAnswerBOList(subjectRadioList);
            subjectOptionBO = new SubjectOptionBO();
            subjectOptionBO.setOptionList(subjectAnswerBOList);
        }
        return subjectOptionBO;
    }
}
