package com.jingdianjichi.subject.application.convert;

import com.jingdianjichi.subject.api.req.SubjectMappingDTO;
import com.jingdianjichi.subject.domain.entity.SubjectMappingBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
/*
  定义了一个用于转换题目、分类、标签映射数据的转换器接口。
  提供了从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 */
public interface SubjectMappingDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SubjectMappingDTOConverter INSTANCE = Mappers.getMapper(SubjectMappingDTOConverter.class);

    /**
     * 将题目、分类、标签映射DTO转换为BO。
     * 
     * @param subjectMappingDTO 题目、分类、标签映射数据传输对象，表示待转换的数据。
     * @return 转换后得到的题目、分类、标签映射业务对象（BO）。
     */
    SubjectMappingBO convertDto2Bo(SubjectMappingDTO subjectMappingDTO);

    /**
     * 将题目、分类、标签映射BO转换为DTO。
     * 
     * @param subjectMappingBO 题目、分类、标签映射业务对象，表示待转换的数据。
     * @return 转换后得到的题目、分类、标签映射数据传输对象（DTO）。
     */
    SubjectMappingDTO convertBo2Dto(SubjectMappingBO subjectMappingBO);

    /**
     * 将题目、分类、标签映射BO列表转换为DTO列表。
     * 
     * @param subjectMappingBO 题目、分类、标签映射业务对象列表，表示待转换的数据。
     * @return 转换后得到的题目、分类、标签映射数据传输对象（DTO）列表。
     */
    List<SubjectMappingDTO> convertBo2Dto(List<SubjectMappingBO> subjectMappingBO);
}

