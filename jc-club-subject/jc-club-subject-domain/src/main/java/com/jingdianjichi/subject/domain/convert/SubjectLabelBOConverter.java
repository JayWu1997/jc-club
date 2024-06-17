package com.jingdianjichi.subject.domain.convert;

import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.entity.SubjectLabelBO;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelBOConverter {

    SubjectLabelBOConverter INSTANCE = Mappers.getMapper(SubjectLabelBOConverter.class);

    SubjectLabel convertBO2Entity(SubjectLabelBO subjectLabelBO);

    SubjectCategoryBO convertEntity2BO(SubjectLabel subjectLabel);

    List<SubjectLabelBO> convertEntity2BO(List<SubjectLabel> subjectLabels);
}
