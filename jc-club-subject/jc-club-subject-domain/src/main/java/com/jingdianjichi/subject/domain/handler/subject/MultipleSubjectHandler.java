package com.jingdianjichi.subject.domain.handler.subject;

import cn.hutool.core.collection.CollectionUtil;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.convert.SubjectAnswerBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMultiple;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多选题处理器
 * @author jay
 * @since 2024/6/20 下午12:26
 */
@Component
public class MultipleSubjectHandler implements SubjectTypeHandler{
    
    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Resource
    private SubjectMappingService subjectMappingService;
    
    /**
     * 题目类型识别
     *
     * @return 返回对应的题目类型
     */
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    /**
     * 添加题目
     *
     * @param subjectInfoBO 题目信息
     */
    @Override
    public void insert(SubjectInfoBO subjectInfoBO) {
        ParamCheckUtil.checkCollNotEmpty(subjectInfoBO.getOptionList(), BusinessErrorEnum.PARAM_ERROR, "多选题的选项不能为空");

        // 保存题目选项
        List<SubjectMultiple> subjectMultipleList = SubjectAnswerBOConverter.INSTANCE.convertBOList2MultipleEntityList(subjectInfoBO.getOptionList());
        subjectMultipleList.forEach(subjectMultiple -> {
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        });
        subjectMultipleService.insertBatch(subjectMultipleList);
    }

    /**
     * 查询题目信息
     *
     * @param subjectId@return 返回查询到的题目信息对象；如果未找到相关信息，则返回null
     */
    @Override
    public SubjectOptionBO querySubjectOptions(Long subjectId) {
        List<SubjectMultiple> subjectMultipleList = subjectMultipleService.queryBySubjectId(subjectId);
        SubjectOptionBO subjectOptionBO = null;
        if (CollectionUtil.isNotEmpty(subjectMultipleList)) {
            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerBOConverter.INSTANCE.convertSubjectMultipleList2SubjectAnswerBOList(subjectMultipleList);
            subjectOptionBO = new SubjectOptionBO();
            subjectOptionBO.setOptionList(subjectAnswerBOList);
        }
        return subjectOptionBO;
    }
}
