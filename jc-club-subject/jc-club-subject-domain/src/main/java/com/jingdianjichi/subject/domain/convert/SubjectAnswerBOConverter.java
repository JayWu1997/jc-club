package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectBrief;
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
     *
     * @param subjectAnswerBO BO对象
     * @return 转换后的SubjectRadio对象
     */
    SubjectRadio convertBO2Entity(SubjectAnswerBO subjectAnswerBO);

    /**
     * 将SubjectAnswerBO列表转换为SubjectRadio列表
     *
     * @param subjectAnswerBOList BO对象列表
     * @return 转换后的SubjectRadio对象列表
     */
    List<SubjectRadio> convertBOList2RadioEntityList(List<SubjectAnswerBO> subjectAnswerBOList);

    /**
     * 将SubjectAnswerBO列表转换为SubjectMultiple列表
     *
     * @param subjectAnswerBOList BO对象列表
     * @return 转换后的SubjectMultiple对象列表
     */
    List<SubjectMultiple> convertBOList2MultipleEntityList(List<SubjectAnswerBO> subjectAnswerBOList);

    /**
     * 将SubjectAnswerBO列表转换为SubjectBrief列表
     *
     * @param subjectInfoBO BO对象
     * @return 转换后的SubjectBrief对象列表
     */
    SubjectBrief convertSubjectInfoBO2BriefEntity(SubjectInfoBO subjectInfoBO);

    /**
     * 将题目多项选择题列表转换为题目答案业务对象列表
     * 此方法用于处理多项选择题列表，将其转换为一个包含题目答案的业务对象列表，
     * 以便在业务逻辑中更方便地处理和使用答案数据
     *
     * @param subjectMultipleList 多项选择题列表，代表一系列的题目多项选择题
     * @return 转换后的题目答案业务对象列表，包含从多项选择题中提取的或转换而来的答案信息
     */
    List<SubjectAnswerBO> convertSubjectMultipleList2SubjectAnswerBOList(List<SubjectMultiple> subjectMultipleList);

    /**
     * 将一组 SubjectRadio 对象转换为 SubjectAnswerBO 对象列表
     * 此方法用于处理单选题目的转换，将单选题的选项和相关信息封装到 SubjectAnswerBO 对象中，
     * 便于后续的操作和展示
     *
     * @param subjectRadioList 单选题列表，包含多个 SubjectRadio 对象
     * @return 转换后的 SubjectAnswerBO 列表，用于展示或进一步处理
     */
    List<SubjectAnswerBO> convertsubjectRadioList2SubjectAnswerBOList(List<SubjectRadio> subjectRadioList);
}

