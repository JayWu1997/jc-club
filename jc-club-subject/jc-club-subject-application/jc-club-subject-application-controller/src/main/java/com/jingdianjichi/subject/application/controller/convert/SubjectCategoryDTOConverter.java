package com.jingdianjichi.subject.application.controller.convert;

import com.jingdianjichi.subject.application.controller.dto.SubjectCategoryDTO;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDto2Bo(SubjectCategoryDTO subjectCategoryDTO);

    SubjectCategoryDTO convertBo2Dto(SubjectCategoryBO subjectCategoryBO);
}
