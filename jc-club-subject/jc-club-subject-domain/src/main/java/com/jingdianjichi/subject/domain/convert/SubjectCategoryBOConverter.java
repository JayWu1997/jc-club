package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryBOConverter {

    SubjectCategoryBOConverter INSTANCE = Mappers.getMapper(SubjectCategoryBOConverter.class);

    SubjectCategory convertBo2Entity(SubjectCategoryBO subjectCategoryBO);

    SubjectCategoryBO convertEntity2Bo(SubjectCategory subjectCategory);

    List<SubjectCategoryBO> convertEntity2Bo(List<SubjectCategory> subjectCategory);
}
