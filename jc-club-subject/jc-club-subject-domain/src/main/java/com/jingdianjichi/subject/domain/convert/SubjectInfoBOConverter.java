package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoBOConverter {

    SubjectInfoBOConverter INSTANCE = Mappers.getMapper(SubjectInfoBOConverter.class);

    SubjectInfo convertBO2Entity(SubjectInfoBO subjectInfoBO);

    SubjectCategoryBO convertEntity2BO(SubjectInfo subjectInfo);

    List<SubjectInfoBO> convertEntity2BO(List<SubjectInfo> subjectInfos);
}
