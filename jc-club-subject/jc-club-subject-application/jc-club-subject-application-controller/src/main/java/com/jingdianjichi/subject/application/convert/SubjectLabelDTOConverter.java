package com.jingdianjichi.subject.application.convert;

import com.jingdianjichi.subject.application.dto.SubjectLabelDTO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
/*
  定义了一个用于转换题目标签数据的转换器接口。
  提供了从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 */
public interface SubjectLabelDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    /**
     * 将题目标签DTO转换为BO。
     * 
     * @param subjectLabelDTO 题目标签数据传输对象，表示待转换的数据。
     * @return 转换后得到的题目标签业务对象（BO）。
     */
    SubjectLabelBO convertDto2Bo(SubjectLabelDTO subjectLabelDTO);

    /**
     * 将题目标签BO转换为DTO。
     * 
     * @param subjectLabelBO 题目标签业务对象，表示待转换的数据。
     * @return 转换后得到的题目标签数据传输对象（DTO）。
     */
    SubjectLabelDTO convertBo2Dto(SubjectLabelBO subjectLabelBO);

    /**
     * 将题目标签BO列表转换为DTO列表。
     * 
     * @param subjectLabelBO 题目标签业务对象列表，表示待转换的数据。
     * @return 转换后得到的题目标签数据传输对象（DTO）列表。
     */
    List<SubjectLabelDTO> convertBo2Dto(List<SubjectLabelBO> subjectLabelBO);
}

