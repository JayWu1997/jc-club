package com.jingdianjichi.practice.server.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.practice.api.common.PageResult;
import com.jingdianjichi.practice.api.req.PracticeDetailDTO;
import com.jingdianjichi.practice.api.resp.Result;
import com.jingdianjichi.practice.server.common.exception.BusinessErrorEnum;
import com.jingdianjichi.practice.server.common.exception.BusinessException;
import com.jingdianjichi.practice.server.common.util.ParamCheckUtil;
import com.jingdianjichi.practice.server.convert.PracticeDetailDTOConverter;
import com.jingdianjichi.practice.server.entity.PracticeDetail;
import com.jingdianjichi.practice.server.req.GetScoreDetailReq;
import com.jingdianjichi.practice.server.req.GetSubjectDetailReq;
import com.jingdianjichi.practice.server.req.SubmitPracticeDetailReq;
import com.jingdianjichi.practice.server.req.SubmitSubjectReq;
import com.jingdianjichi.practice.server.service.PracticeDetailService;
import com.jingdianjichi.practice.server.vo.ScoreDetailVO;
import com.jingdianjichi.practice.server.vo.SubjectDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 练习详情表(PracticeDetail)表控制层
 *
 * @author jay
 * @since 2025-01-08 18:21:18
 */
@Slf4j
@RestController
@RequestMapping("/practice/detail")
public class PracticeDetailController {

    @Resource
    private PracticeDetailService practiceDetailService;

    /**
     * 获取答题详情
     * @param req
     * @return
     */
    @PostMapping("/getScoreDetail")
    public Result<List<ScoreDetailVO>> getScoreDetail(@RequestBody GetScoreDetailReq req) {
        try{
            if (log.isInfoEnabled()) {
                log.info("PracticeDetailController.getScoreDetail.req:{}", JSON.toJSON(req));
            }

            ParamCheckUtil.checkNotNull(req.getPracticeId(), BusinessErrorEnum.PARAM_ERROR, "practiceId 不能为空!");

            List<ScoreDetailVO> scoreDetailVOList = practiceDetailService.getScoreDetail(req);
            if (log.isInfoEnabled()) {
                log.info("PracticeDetailController.getScoreDetail.result:{}", JSON.toJSON(scoreDetailVOList));
            }
            return Result.success(scoreDetailVOList);
        } catch (BusinessException e) {
            log.error("PracticeDetailController.getScoreDetail.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeDetailController.getScoreDetail.error:{}", e.getMessage(), e);
            return Result.fail("获取答题详情失败！");
        }
    }

    /**
     * 提交
     * @param req
     * @return
     */
    @PostMapping("/submit")
    public Result<Boolean> submit(@RequestBody SubmitPracticeDetailReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetController.submit.req:{}", JSON.toJSON(req));
            }

            ParamCheckUtil.checkNotNull(req.getPracticeId(), BusinessErrorEnum.PARAM_ERROR, "practiceId 不能为空!");
            ParamCheckUtil.checkNotNull(req.getSetId(), BusinessErrorEnum.PARAM_ERROR, "setId 不能为空!");
            ParamCheckUtil.checkNotNull(req.getTimeUse(), BusinessErrorEnum.PARAM_ERROR, "用时不能为空!");
            ParamCheckUtil.checkNotNull(req.getSubmitTime(), BusinessErrorEnum.PARAM_ERROR, "提交时间不能为空!");

            return Result.success(practiceDetailService.submit(req));
        } catch (BusinessException e) {
            log.error("PracticeSetController.submit.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage(), Boolean.FALSE);
        } catch (Exception e) {
            log.error("PracticeSetController.submit.error:{}", e.getMessage(), e);
            return Result.fail("提交失败！", Boolean.FALSE);
        }
    }

    @PostMapping("/submitSubject")
    public Result<Boolean> submitSubject(@RequestBody SubmitSubjectReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetController.submit.req:{}", JSON.toJSON(req));
            }

            ParamCheckUtil.checkNotNull(req.getPracticeId(), BusinessErrorEnum.PARAM_ERROR, "practiceId 不能为空!");
            ParamCheckUtil.checkNotNull(req.getSubjectId(), BusinessErrorEnum.PARAM_ERROR, "subjectId 不能为空!");
            ParamCheckUtil.checkNotNull(req.getSubjectType(), BusinessErrorEnum.PARAM_ERROR, "subjectType 不能为空!");
            ParamCheckUtil.checkCollNotEmpty(req.getAnswerContents(), BusinessErrorEnum.PARAM_ERROR, "answerContents 不能为空!");
            ParamCheckUtil.checkStrNotEmpty(req.getTimeUse(), BusinessErrorEnum.PARAM_ERROR, "用时不能为空!");

            return Result.success(practiceDetailService.submitSubject(req));
        } catch (BusinessException e) {
            log.error("PracticeSetController.submit.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage(), Boolean.FALSE);
        } catch (Exception e) {
            log.error("PracticeSetController.submit.error:{}", e.getMessage(), e);
            return Result.fail("提交失败！", Boolean.FALSE);
        }
    }

    /**
     * 新增
     *
     * @param practiceDetailDTO dto
     * @return 分页结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody PracticeDetailDTO practiceDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeDetailController.add.dto:{}", JSON.toJSON(practiceDetailDTO));
            }
            PracticeDetail practiceDetail = PracticeDetailDTOConverter.INSTANCE.convertDto2Entity(practiceDetailDTO);
            return Result.success(practiceDetailService.insert(practiceDetail) == 1);
        } catch (BusinessException e) {
            log.error("PracticeDetailController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeDetailController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加失败！");
        }
    }

    /**
     * 获取分页
     *
     * @param practiceDetailDTO dto
     * @return 分页数据
     */
    @PostMapping("/queryByPage")
    public Result<PageResult<PracticeDetailDTO>> queryByPage(@RequestBody PracticeDetailDTO practiceDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeDetailController.queryByPage.practiceDetailDTO:{}", JSON.toJSON(practiceDetailDTO));
            }

            Integer pageNo = practiceDetailDTO.getPageNo();
            ParamCheckUtil.checkNotNull(pageNo, BusinessErrorEnum.PARAM_ERROR, "页码不能为空!");
            Integer pageSize = practiceDetailDTO.getPageSize();
            ParamCheckUtil.checkNotNull(pageSize, BusinessErrorEnum.PARAM_ERROR, "分页大小不能为空!");

            PracticeDetail queryCondition = PracticeDetailDTOConverter.INSTANCE.convertDto2Entity(practiceDetailDTO);
            long count = practiceDetailService.count(queryCondition);
            List<PracticeDetail> practiceDetailList = new ArrayList<>();
            if (count > 0) {
                practiceDetailList = practiceDetailService.queryByPage(queryCondition, (pageNo - 1) * pageSize, pageSize);
            }
            return Result.success(new PageResult<>(pageNo, pageSize, (int) count, PracticeDetailDTOConverter.INSTANCE.convertEntity2Dto(practiceDetailList)));
        } catch (BusinessException e) {
            log.error("PracticeDetailController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeDetailController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail("获取分页失败！");
        }
    }

    /**
     * 根据 id 查询
     *
     * @param practiceDetailDTO dto
     * @return 结果
     */
    @PostMapping("/queryById")
    public Result<PracticeDetailDTO> queryById(@RequestBody PracticeDetailDTO practiceDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeDetailController.queryById.practiceDetailDTO:{}", JSON.toJSON(practiceDetailDTO));
            }

            // 参数校验，确保题目id、类别id和标签id不为空
            ParamCheckUtil.checkNotNull(practiceDetailDTO.getId(), BusinessErrorEnum.PARAM_ERROR, "id 不能为空!");

            PracticeDetail result = practiceDetailService.queryById(practiceDetailDTO.getId());
            return Result.success(PracticeDetailDTOConverter.INSTANCE.convertEntity2Dto(result));
        } catch (BusinessException e) {
            log.error("PracticeDetailController.queryById.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeDetailController.queryById.error:{}", e.getMessage(), e);
            return Result.fail("查询失败！");
        }
    }


    /**
     * 更新信息
     *
     * @param practiceDetailDTO dto
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody PracticeDetailDTO practiceDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeDetailController.update.dto:{}", JSON.toJSON(practiceDetailDTO));
            }

            if (practiceDetailDTO.getId() == null || practiceDetailDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            Boolean insertResult = practiceDetailService.update(PracticeDetailDTOConverter.INSTANCE.convertDto2Entity(practiceDetailDTO)) == 1;
            return Result.success(insertResult);
        } catch (BusinessException e) {
            log.error("PracticeDetailController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeDetailController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    /**
     * 删除
     *
     * @param practiceDetailDTO dto
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody PracticeDetailDTO practiceDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeDetailController.delete.dto:{}", JSON.toJSON(practiceDetailDTO));
            }

            if (practiceDetailDTO.getId() == null || practiceDetailDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            Boolean deleteResult = practiceDetailService.deleteById(practiceDetailDTO.getId()) == 1;
            return Result.success(deleteResult);
        } catch (BusinessException e) {
            log.error("PracticeDetailController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeDetailController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除失败！");
        }
    }

    @PostMapping("/getSubjectDetail")
    public Result<SubjectDetailVO> getSubjectDetail(@RequestBody GetSubjectDetailReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeDetailController.getSubjectDetail.req:{}", JSON.toJSON(req));
            }
            ParamCheckUtil.checkNotNull(req.getPracticeId(), BusinessErrorEnum.PARAM_ERROR, "practiceId 不能为空!");
            ParamCheckUtil.checkNotNull(req.getSubjectId(), BusinessErrorEnum.PARAM_ERROR, "subjectId 不能为空!");
            return Result.success(practiceDetailService.getSubjectDetail(req));
        } catch (BusinessException e) {
            log.error("PracticeDetailController.getSubjectDetail.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeDetailController.getSubjectDetail.error:{}", e.getMessage(), e);
            return Result.fail("获取失败！");
        }
    }
}

