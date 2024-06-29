package com.jingdianjichi.subject.application.convert;

import com.jingdianjichi.subject.application.dto.SubjectAnswerDTO;
import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
/*
  定义了一个用于转换答案数据的转换器接口。
  提供了从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 */
public interface SubjectAnswerDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    /**
     * 将答案DTO转换为BO。
     * 
     * @param subjectAnswerDTO 答案数据传输对象，表示待转换的数据。
     * @return 转换后得到的答案业务对象（BO）。
     */
    SubjectAnswerBO convertDto2Bo(SubjectAnswerDTO subjectAnswerDTO);

    /**
     * 将答案BO转换为DTO。
     * 
     * @param subjectAnswerBO 答案业务对象，表示待转换的数据。
     * @return 转换后得到的答案数据传输对象（DTO）。
     */
    SubjectAnswerDTO convertBo2Dto(SubjectAnswerBO subjectAnswerBO);

    /**
     * 将答案BO列表转换为DTO列表。
     * 
     * @param subjectAnswerBO 答案业务对象列表，表示待转换的数据。
     * @return 转换后得到的答案数据传输对象（DTO）列表。
     */
    List<SubjectAnswerDTO> convertBo2Dto(List<SubjectAnswerBO> subjectAnswerBO);
}

