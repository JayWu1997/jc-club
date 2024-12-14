package com.jingdianjichi.subject.domain.service.impl;

import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.enums.ResultCodeEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.domain.convert.SubjectInfoBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.domain.handler.subject.SubjectTypeHandler;
import com.jingdianjichi.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jingdianjichi.subject.domain.service.SubjectInfoDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import com.jingdianjichi.subject.infra.basic.service.impl.SubjectLabelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目信息领域服务实现类
 *
 * @author jay
 * @since 2024/6/18
 */
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectMappingService subjectMappingService;
    @Autowired
    private SubjectLabelServiceImpl subjectLabelService;

    /**
     * 新增题目信息
     *
     * @param subjectInfoBO 题目信息
     */
    @Override
    public void insert(SubjectInfoBO subjectInfoBO) {
        // 保存题目信息
        SubjectInfo subjectInfo = SubjectInfoBOConverter.INSTANCE.convertBO2Entity(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        if (subjectInfo.getId() == null) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "题目信息保存失败");
        }
        subjectInfoBO.setId(subjectInfo.getId());

        // 保存题目类型映射
        List<SubjectMapping> subjectMappingList = new LinkedList<>();
        subjectInfoBO.getCategoryIds().forEach(categoryId -> subjectInfoBO.getLabelIds().forEach(labelId -> {
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setSubjectId(subjectInfoBO.getId());
            subjectMapping.setCategoryId(categoryId);
            subjectMapping.setLabelId(labelId);
            subjectMapping.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
            subjectMappingList.add(subjectMapping);
        }));
        subjectMappingService.insertBatch(subjectMappingList);

        // 调用题目类型处理器保存题目答案
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory.getSubjectTypeHandler(subjectInfoBO.getSubjectType());
        subjectTypeHandler.insert(subjectInfoBO);
    }

    /**
     * 更新题目信息
     *
     * @param subjectInfoBO 待更新的题目信息
     * @return 是否修改成功
     */
    @Override
    public Boolean update(SubjectInfoBO subjectInfoBO) {
        return subjectInfoService.update(SubjectInfoBOConverter.INSTANCE.convertBO2Entity(subjectInfoBO)) > 0;
    }

    /**
     * 删除题目信息
     *
     * @param subjectsInfoBO 待删除的题目信息
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(SubjectInfoBO subjectsInfoBO) {
        SubjectInfo subjectInfo = new SubjectInfo();
        subjectInfo.setId(subjectsInfoBO.getId());
        subjectInfo.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        return subjectInfoService.update(subjectInfo) > 0;
    }

    /**
     * 分页查询题目信息
     *
     * @param subjectInfoBO 查询条件
     * @return
     */
    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {

        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoBOConverter.INSTANCE.convertBO2Entity(subjectInfoBO);
        int total = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
        // 查询得到的数量为0，就不用继续查询题目列表信息了
        if (total == 0) {
            return new PageResult<>(subjectInfoBO.getPageNo(),
                    subjectInfoBO.getPageSize(),
                    total,
                    Collections.emptyList());
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryByCondition(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoBOConverter.INSTANCE.convertEntity2BO(subjectInfoList);

        return new PageResult<>(subjectInfoBO.getPageNo(),
                subjectInfoBO.getPageSize(),
                total,
                subjectInfoBOList);
    }

    /**
     * 获取题目信息
     *
     * @param subjectInfoBO 包含查询条件的主题信息对象，不能为空
     * @return 返回一个填充了详细信息的题目信息对象如果找不到相关信息，则返回null
     */
    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        // 查询题目信息
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        if (subjectInfo == null) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "题目信息不存在");
        }
        // 查询题目答案
        subjectInfoBO = SubjectInfoBOConverter.INSTANCE.convertEntity2BO(subjectInfo);
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory.getSubjectTypeHandler(subjectInfo.getSubjectType());
        SubjectOptionBO subjectOptionBO = subjectTypeHandler.querySubjectOptions(subjectInfoBO.getId());
        if (subjectOptionBO != null) {
            subjectInfoBO.setSubjectAnswer(subjectOptionBO.getSubjectAnswer());
            subjectInfoBO.setOptionList(subjectOptionBO.getOptionList());
        }
        // 查询标签名称
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfoBO.getId());
        List<Long> labelIds = subjectMappingService.queryDistinctLabelIdsByCondition(subjectMapping);
        List<String> labelNameList = subjectLabelService.queryBatchByIds(labelIds).stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        subjectInfoBO.setLabelNames(labelNameList);
        return subjectInfoBO;
    }
}
