package com.jingdianjichi.subject.domain.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.jingdianjichi.auth.api.req.AuthUserDTO;
import com.jingdianjichi.auth.api.resp.Result;
import com.jingdianjichi.auth.api.service.UserFeignService;
import com.jingdianjichi.subject.common.context.UserContextHolder;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.enums.IsDeletedEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.domain.convert.SubjectInfoBOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectInfoBO;
import com.jingdianjichi.subject.domain.entity.SubjectLikedBO;
import com.jingdianjichi.subject.domain.entity.SubjectOptionBO;
import com.jingdianjichi.subject.domain.handler.subject.SubjectTypeHandler;
import com.jingdianjichi.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jingdianjichi.subject.domain.redis.RedisUtil;
import com.jingdianjichi.subject.domain.service.SubjectInfoDomainService;
import com.jingdianjichi.subject.domain.service.SubjectLikedDomainService;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfo;
import com.jingdianjichi.subject.infra.basic.entity.SubjectInfoEs;
import com.jingdianjichi.subject.infra.basic.entity.SubjectLabel;
import com.jingdianjichi.subject.infra.basic.entity.SubjectMapping;
import com.jingdianjichi.subject.infra.basic.service.SubjectEsInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectInfoService;
import com.jingdianjichi.subject.infra.basic.service.SubjectMappingService;
import com.jingdianjichi.subject.infra.basic.service.impl.SubjectLabelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目信息领域服务实现类
 *
 * @author jay
 * @since 2024/6/18
 */
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    private static final String CONTRIBUTE_RANK_KEY = "CONTRIBUTE_RANK_KEY";

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelServiceImpl subjectLabelService;
    @Resource
    private SubjectEsInfoService subjectEsInfoService;
    @Qualifier("com.jingdianjichi.auth.api.service.UserFeignService")
    @Autowired
    private UserFeignService userFeignService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;


    /**
     * 新增题目信息
     *
     * @param subjectInfoBO 题目信息
     */
    @Override
    public void insert(SubjectInfoBO subjectInfoBO) {
        // 保存题目信息
        subjectInfoBO.setCreatedBy(UserContextHolder.getUserContext().getUserName());
        subjectInfoBO.setCreatedTime(new Date());
        SubjectInfo subjectInfo = SubjectInfoBOConverter.INSTANCE.convertBO2Entity(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        if (subjectInfo.getId() == null) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "题目信息保存失败");
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

        // 插入ES
        SubjectInfoEs subjectInfoEs = convert2SubjectInfoEs(subjectInfoBO, subjectInfo);
        subjectEsInfoService.insert(subjectInfoEs);

        // redis中对应用户贡献排行榜加一
        redisUtil.addScore(CONTRIBUTE_RANK_KEY, UserContextHolder.getUserContext().getUserName(), 1);
    }

    private static SubjectInfoEs convert2SubjectInfoEs(SubjectInfoBO subjectInfoBO, SubjectInfo subjectInfo) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setSubjectId(subjectInfo.getId());
        subjectInfoEs.setSubjectName(subjectInfoBO.getSubjectName());
        subjectInfoEs.setSubjectDifficult(subjectInfoBO.getSubjectDifficult());
        subjectInfoEs.setSettleName(subjectInfoBO.getSettleName());
        subjectInfoEs.setSubjectType(subjectInfoBO.getSubjectType());
        subjectInfoEs.setSubjectScore(subjectInfoBO.getSubjectScore());
        subjectInfoEs.setSubjectParse(subjectInfoBO.getSubjectParse());
        subjectInfoEs.setCreatedBy(subjectInfoBO.getCreatedBy());
        Date createdTime = subjectInfoBO.getCreatedTime();
        subjectInfoEs.setCreatedTime(createdTime != null ? createdTime.getTime() : null);
        subjectInfoEs.setUpdateBy(subjectInfoBO.getUpdateBy());
        Date updateTime = subjectInfoBO.getUpdateTime();
        subjectInfoEs.setUpdateTime(updateTime != null ? updateTime.getTime() : null);
        subjectInfoEs.setSubjectAnswer(subjectInfoBO.getSubjectAnswer());
        return subjectInfoEs;
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
     * @param queryRequest 包含查询条件的主题信息对象，不能为空
     * @return 返回一个填充了详细信息的题目信息对象如果找不到相关信息，则返回null
     */
    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO queryRequest) {
        // 查询题目信息
        SubjectInfo subjectInfo = subjectInfoService.queryById(queryRequest.getId());
        if (subjectInfo == null) {
            throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "题目信息不存在");
        }
        // 查询题目答案
        SubjectInfoBO resultBO = SubjectInfoBOConverter.INSTANCE.convertEntity2BO(subjectInfo);
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory.getSubjectTypeHandler(subjectInfo.getSubjectType());
        SubjectOptionBO subjectOptionBO = subjectTypeHandler.querySubjectOptions(resultBO.getId());
        if (subjectOptionBO != null) {
            resultBO.setSubjectAnswer(subjectOptionBO.getSubjectAnswer());
            resultBO.setOptionList(subjectOptionBO.getOptionList());
        }
        // 查询标签名称
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(resultBO.getId());
        List<Long> labelIds = subjectMappingService.queryDistinctLabelIdsByCondition(subjectMapping);
        List<String> labelNameList = subjectLabelService.queryBatchByIds(labelIds).stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        resultBO.setLabelNames(labelNameList);
        // 查询是否被当前用户点赞过
        SubjectLikedBO subjectLikedBO = new SubjectLikedBO();
        subjectLikedBO.setSubjectId(resultBO.getId());
        resultBO.setLiked(subjectLikedDomainService.isLiked(subjectLikedBO));
        // 查询点赞数量
        resultBO.setLikedCount(subjectLikedDomainService.likedNum(subjectLikedBO).intValue());
        // 填充上一题和下一题 id
        fillCursor(queryRequest, resultBO);
        return resultBO;
    }

    /**
     * 填充上一题和下一题 id
     * @param request 查询条件
     * @param resultBO 填充查询结果
     */
    private void fillCursor(SubjectInfoBO request, SubjectInfoBO resultBO) {
        Long labelId = request.getLabelId();
        Long categoryId = request.getCategoryId();
        if (labelId != null || categoryId != null) {
            return;
        }
        SubjectMapping queryParam = new SubjectMapping();
        queryParam.setCategoryId(request.getCategoryId());
        queryParam.setLabelId(request.getLabelId());
        queryParam.setSubjectId(request.getId());
        Long lastSubjectId = subjectMappingService.queryLastSubjectId(queryParam);
        Long nextSubjectId = subjectMappingService.queryNextSubjectId(queryParam);
        resultBO.setLastSubjectId(lastSubjectId);
        resultBO.setNextSubjectId(nextSubjectId);
    }

    /**
     * 从ES中查询题目信息
     *
     * @param subjectInfoBO
     * @return
     */
    @Override
    public PageResult<SubjectInfoEs> queryPageFromES(SubjectInfoBO subjectInfoBO) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setKeyword(subjectInfoBO.getKeyWord());
        subjectInfoEs.setPageNo(subjectInfoBO.getPageNo());
        subjectInfoEs.setPageSize(subjectInfoBO.getPageSize());
        return subjectEsInfoService.query(subjectInfoEs);
    }

    /**
     * 查询贡献榜
     *
     * @return
     */
    @Override
    public List<SubjectInfoBO> getContributeList() {
        Set<ZSetOperations.TypedTuple<String>> rank = redisUtil.rank(CONTRIBUTE_RANK_KEY, 0, 5);
        if (CollUtil.isEmpty(rank)) {
            return Collections.emptyList();
        }

        List<SubjectInfoBO> boList = new ArrayList<>();
        rank.forEach(entity -> {
            AuthUserDTO authUserDTO = new AuthUserDTO();
            authUserDTO.setUserName(entity.getValue());
            Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
            if (ObjectUtil.isNotNull(result) && ObjectUtil.isNotNull(result.getData())) {
                SubjectInfoBO bo = new SubjectInfoBO();
                bo.setCreateUser(result.getData().getNickName());
                bo.setCreateUserAvatar(result.getData().getAvatar());
                bo.setCreatedBy(result.getData().getUserName());
                bo.setSubjectCount(entity.getScore().intValue());
                boList.add(bo);
            }
        });
        return boList;
    }
}
