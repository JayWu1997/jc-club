package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.entity.SubjectMappingBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMappingBOConverter {

    SubjectMappingBOConverter INSTANCE = Mappers.getMapper(SubjectMappingBOConverter.class);

    SubjectMapping convertBO2Entity(SubjectMappingBO subjectMappingBO);

    SubjectCategoryBO convertEntity2BO(SubjectMapping subjectMapping);

    List<SubjectMappingBO> convertEntity2BO(List<SubjectMapping> subjectMappings);
}
