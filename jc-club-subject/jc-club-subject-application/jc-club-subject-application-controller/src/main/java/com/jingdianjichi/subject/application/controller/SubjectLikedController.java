package com.jingdianjichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.api.req.SubjectLikedDTO;
import com.jingdianjichi.subject.api.resp.Result;
import com.jingdianjichi.subject.application.convert.SubjectLikedDTOConverter;
import com.jingdianjichi.subject.common.context.UserContext;
import com.jingdianjichi.subject.common.context.UserContextHolder;
import com.jingdianjichi.subject.common.entity.PageResult;
import com.jingdianjichi.subject.common.enums.BusinessErrorEnum;
import com.jingdianjichi.subject.common.exception.BusinessException;
import com.jingdianjichi.subject.common.util.ParamCheckUtil;
import com.jingdianjichi.subject.domain.entity.SubjectLikedBO;
import com.jingdianjichi.subject.domain.service.SubjectLikedDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 题目点赞表(SubjectLiked)表控制层
 *
 * @author jay
 * @since 2025-01-06 15:41:00
 */
@Slf4j
@RestController
@RequestMapping("/subjectLiked")
public class SubjectLikedController {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    /**
     * 新增
     *
     * @param subjectLikedDTO dto
     * @return 分页结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLikedDTO subjectLikedDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLikedController.add.dto:{}", JSON.toJSON(subjectLikedDTO));
            }
            ParamCheckUtil.checkNotNull(subjectLikedDTO.getSubjectId(), BusinessErrorEnum.PARAM_ERROR, "题目ID不能为空!");
            String userName = UserContextHolder.getUserContext().getUserName();
            subjectLikedDTO.setLikeUserId(userName);
            ParamCheckUtil.checkNotNull(subjectLikedDTO.getLikeUserId(), BusinessErrorEnum.PARAM_ERROR, "获取用户信息失败!");
            ParamCheckUtil.checkNotNull(subjectLikedDTO.getStatus(), BusinessErrorEnum.PARAM_ERROR, "点赞状态不能为空!");
            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConverter.INSTANCE.convertDto2Bo(subjectLikedDTO);
            return Result.success(subjectLikedDomainService.add(subjectLikedBO));
        } catch (BusinessException e) {
            log.error("SubjectLikedController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLikedController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加失败！");
        }
    }

    /**
     * 获取分页
     *
     * @param subjectLikedDTO dto
     * @return 分页数据
     */
    @PostMapping("/getSubjectLikedPage")
    public Result<PageResult<SubjectLikedDTO>> getSubjectLikedPage(@RequestBody SubjectLikedDTO subjectLikedDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLikedController.queryByPage.subjectLikedDTO:{}", JSON.toJSON(subjectLikedDTO));
            }

            ParamCheckUtil.checkNotNull(subjectLikedDTO.getPageNo(), BusinessErrorEnum.PARAM_ERROR, "页码不能为空!");
            ParamCheckUtil.checkNotNull(subjectLikedDTO.getPageSize(), BusinessErrorEnum.PARAM_ERROR, "分页大小不能为空!");
            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConverter.INSTANCE.convertDto2Bo(subjectLikedDTO);
            String userId = Optional.ofNullable(UserContextHolder.getUserContext())
                    .map(UserContext::getUserName)
                    .orElse(null);
            ParamCheckUtil.checkNotNull(userId, BusinessErrorEnum.PARAM_ERROR, "获取用户信息失败!");

            subjectLikedBO.setLikeUserId(userId);
            PageResult<SubjectLikedBO> boPageResult = subjectLikedDomainService.getSubjectLikedPage(subjectLikedBO);
            return Result.success(new PageResult<>(boPageResult.getPageNo(), boPageResult.getPageSize(), boPageResult.getTotal(), SubjectLikedDTOConverter.INSTANCE.convertBo2Dto(boPageResult.getResult())));
        } catch (BusinessException e) {
            log.error("SubjectLikedController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLikedController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail("获取分页失败！");
        }
    }

    /**
     * 根据 id 查询
     *
     * @param subjectLikedDTO dto
     * @return 结果
     */
    @PostMapping("/queryById")
    public Result<SubjectLikedDTO> queryById(@RequestBody SubjectLikedDTO subjectLikedDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLikedController.queryById.subjectLikedDTO:{}", JSON.toJSON(subjectLikedDTO));
            }

            // 参数校验，确保题目id、类别id和标签id不为空
            ParamCheckUtil.checkNotNull(subjectLikedDTO.getId(), BusinessErrorEnum.PARAM_ERROR, "id 不能为空!");

            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConverter.INSTANCE.convertDto2Bo(subjectLikedDTO);
            SubjectLikedBO resultBO = subjectLikedDomainService.queryById(subjectLikedBO);
            return Result.success(SubjectLikedDTOConverter.INSTANCE.convertBo2Dto(resultBO));
        } catch (BusinessException e) {
            log.error("SubjectLikedController.queryById.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLikedController.queryById.error:{}", e.getMessage(), e);
            return Result.fail("查询失败！");
        }
    }


    /**
     * 更新信息
     *
     * @param subjectLikedDTO dto
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLikedDTO subjectLikedDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLikedController.update.dto:{}", JSON.toJSON(subjectLikedDTO));
            }

            if (subjectLikedDTO.getId() == null || subjectLikedDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            Boolean insertResult = subjectLikedDomainService.update(SubjectLikedDTOConverter.INSTANCE.convertDto2Bo(subjectLikedDTO));
            Result<Boolean> result = Result.success(insertResult);
            return result;
        } catch (BusinessException e) {
            log.error("SubjectLikedController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLikedController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    /**
     * 删除
     *
     * @param subjectLikedDTO dto
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLikedDTO subjectLikedDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLikedController.delete.dto:{}", JSON.toJSON(subjectLikedDTO));
            }

            if (subjectLikedDTO.getId() == null || subjectLikedDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            Boolean insertResult = subjectLikedDomainService.deleteById(SubjectLikedDTOConverter.INSTANCE.convertDto2Bo(subjectLikedDTO));
            return Result.success(insertResult);
        } catch (BusinessException e) {
            log.error("SubjectLikedController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("SubjectLikedController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除失败！");
        }
    }
}

