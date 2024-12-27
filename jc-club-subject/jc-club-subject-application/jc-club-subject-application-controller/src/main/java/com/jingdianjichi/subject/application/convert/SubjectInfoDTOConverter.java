package com.jingdianjichi.subject.application.convert;

import com.jingdianjichi.subject.api.req.SubjectAnswerDTO;
import com.jingdianjichi.subject.api.req.SubjectInfoDTO;
import com.jingdianjichi.subject.domain.entity.SubjectAnswerBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
/*
  定义了一个用于转换题目信息数据的转换器接口。
  提供了从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 */
public interface SubjectInfoDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    /**
     * 将题目信息DTO转换为BO。
     * 
     * @param subjectInfoDTO 题目信息数据传输对象，表示待转换的数据。
     * @return 转换后得到的题目信息业务对象（BO）。
     */
    SubjectInfoBO convertDto2Bo(SubjectInfoDTO subjectInfoDTO);

    /**
     * 将题目信息BO转换为DTO。
     * 
     * @param subjectInfoBO 题目信息业务对象，表示待转换的数据。
     * @return 转换后得到的题目信息数据传输对象（DTO）。
     */
    SubjectInfoDTO convertBo2Dto(SubjectInfoBO subjectInfoBO);

    /**
     * 将题目信息BO列表转换为DTO列表。
     * 
     * @param subjectInfoBO 题目信息业务对象列表，表示待转换的数据。
     * @return 转换后得到的题目信息数据传输对象（DTO）列表。
     */
    List<SubjectInfoDTO> convertBo2Dto(List<SubjectInfoBO> subjectInfoBO);

    /**
     * 将题目答案BO列表转换为DTO列表。
     * @param value 题目答案业务对象列表，表示待转换的数据。
     * @return 转换后得到的题目答案数据传输对象（DTO）列表。
     */
    List<SubjectAnswerDTO> convertAnswerBo2Dto(List<SubjectAnswerBO> value);
}

