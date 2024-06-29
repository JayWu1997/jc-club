package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectBrief;
import com.jingdianjichi.subject.infra.basic.entity.SubjectJudge;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMultiple;
import com.jingdianjichi.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
/*
  定义了一个用于转换答案数据的转换器接口。
 */
public interface SubjectAnswerBOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SubjectAnswerBOConverter INSTANCE = Mappers.getMapper(SubjectAnswerBOConverter.class);

    /**
     * 将SubjectAnswerBO转换为SubjectRadio
     * @param subjectAnswerBO BO对象
     * @return 转换后的SubjectRadio对象
     */
    SubjectRadio convertBO2Entity(SubjectAnswerBO subjectAnswerBO);

    /**
     * 将SubjectAnswerBO列表转换为SubjectRadio列表
     * @param subjectAnswerBOList BO对象列表
     * @return 转换后的SubjectRadio对象列表
     */
    List<SubjectRadio> convertBOList2RadioEntityList(List<SubjectAnswerBO> subjectAnswerBOList);

    /**
     * 将SubjectAnswerBO列表转换为SubjectMultiple列表
     * @param subjectAnswerBOList BO对象列表
     * @return 转换后的SubjectMultiple对象列表
     */
    List<SubjectMultiple> convertBOList2MultipleEntityList(List<SubjectAnswerBO> subjectAnswerBOList);

    /**
     * 将SubjectAnswerBO列表转换为SubjectJudge列表
     * @param subjectAnswerBOList BO对象列表
     * @return 转换后的SubjectJudge对象列表
     */
    List<SubjectJudge> convertBOList2JudgeEntityList(List<SubjectAnswerBO> subjectAnswerBOList);

    /**
     * 将SubjectAnswerBO列表转换为SubjectBrief列表
     * @param subjectAnswerBOList BO对象列表
     * @return 转换后的SubjectBrief对象列表
     */
    List<SubjectBrief> convertBOList2BriefEntityList(List<SubjectAnswerBO> subjectAnswerBOList);
}

