package com.jingdianjichi.subject.application.controller.convert;

import com.jingdianjichi.subject.application.controller.dto.SubjectCategoryDTO;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
/*
  定义了一个用于转换题目分类数据的转换器接口。
  提供了从DTO到BO以及从BO到DTO的转换方法，支持单个对象和对象列表的转换。
 */
public interface SubjectCategoryDTOConverter {

    // 单例模式，提供了一个该接口的实例，用于全局访问转换器
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    /**
     * 将题目分类DTO转换为BO。
     * 
     * @param subjectCategoryDTO 题目分类数据传输对象，表示待转换的数据。
     * @return 转换后得到的题目分类业务对象（BO）。
     */
    SubjectCategoryBO convertDto2Bo(SubjectCategoryDTO subjectCategoryDTO);

    /**
     * 将题目分类BO转换为DTO。
     * 
     * @param subjectCategoryBO 题目分类业务对象，表示待转换的数据。
     * @return 转换后得到的题目分类数据传输对象（DTO）。
     */
    SubjectCategoryDTO convertBo2Dto(SubjectCategoryBO subjectCategoryBO);

    /**
     * 将题目分类BO列表转换为DTO列表。
     * 
     * @param subjectCategoryBO 题目分类业务对象列表，表示待转换的数据。
     * @return 转换后得到的题目分类数据传输对象（DTO）列表。
     */
    List<SubjectCategoryDTO> convertBo2Dto(List<SubjectCategoryBO> subjectCategoryBO);
}

