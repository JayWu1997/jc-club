package com.jingdianjichi.practice.server.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.practice.api.common.PageResult;
import com.jingdianjichi.practice.api.req.PracticeSetDetailDTO;
import com.jingdianjichi.practice.api.resp.Result;
import com.jingdianjichi.practice.server.common.exception.BusinessErrorEnum;
import com.jingdianjichi.practice.server.common.exception.BusinessException;
import com.jingdianjichi.practice.server.common.util.ParamCheckUtil;
import com.jingdianjichi.practice.server.convert.PracticeSetDetailDTOConverter;
import com.jingdianjichi.practice.server.entity.PracticeSetDetail;
import com.jingdianjichi.practice.server.service.PracticeSetDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 套题内容表(PracticeSetDetail)表控制层
 *
 * @author jay
 * @since 2025-01-08 18:21:21
 */
@Slf4j
@RestController
@RequestMapping("/practiceSetDetail")
public class PracticeSetDetailController {

    @Resource
    private PracticeSetDetailService practiceSetDetailService;

    /**
     * 新增
     *
     * @param practiceSetDetailDTO dto
     * @return 分页结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody PracticeSetDetailDTO practiceSetDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetDetailController.add.dto:{}", JSON.toJSON(practiceSetDetailDTO));
            }
            PracticeSetDetail practiceSetDetail = PracticeSetDetailDTOConverter.INSTANCE.convertDto2Entity(practiceSetDetailDTO);
            return Result.success(practiceSetDetailService.insert(practiceSetDetail) == 1);
        } catch (BusinessException e) {
            log.error("PracticeSetDetailController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetDetailController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加失败！");
        }
    }

    /**
     * 获取分页
     *
     * @param practiceSetDetailDTO dto
     * @return 分页数据
     */
    @PostMapping("/queryByPage")
    public Result<PageResult<PracticeSetDetailDTO>> queryByPage(@RequestBody PracticeSetDetailDTO practiceSetDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetDetailController.queryByPage.practiceSetDetailDTO:{}", JSON.toJSON(practiceSetDetailDTO));
            }


            Integer pageNo = practiceSetDetailDTO.getPageNo();
            ParamCheckUtil.checkNotNull(pageNo, BusinessErrorEnum.PARAM_ERROR, "页码不能为空!");
            Integer pageSize = practiceSetDetailDTO.getPageSize();
            ParamCheckUtil.checkNotNull(pageSize, BusinessErrorEnum.PARAM_ERROR, "分页大小不能为空!");

            PracticeSetDetail queryRequest = PracticeSetDetailDTOConverter.INSTANCE.convertDto2Entity(practiceSetDetailDTO);
            long count = practiceSetDetailService.count(queryRequest);
            List<PracticeSetDetail> practiceSetDetailList = new ArrayList<>();
            if (count > 0) {
                practiceSetDetailList = practiceSetDetailService.queryByPage(queryRequest, pageNo, pageSize);
            }
            return Result.success(new PageResult<>(pageNo, pageSize, (int) count, PracticeSetDetailDTOConverter.INSTANCE.convertEntity2Dto(practiceSetDetailList)));
        } catch (BusinessException e) {
            log.error("PracticeSetDetailController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetDetailController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail("获取分页失败！");
        }
    }

    /**
     * 根据 id 查询
     *
     * @param practiceSetDetailDTO dto
     * @return 结果
     */
    @PostMapping("/queryById")
    public Result<PracticeSetDetailDTO> queryById(@RequestBody PracticeSetDetailDTO practiceSetDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetDetailController.queryById.practiceSetDetailDTO:{}", JSON.toJSON(practiceSetDetailDTO));
            }

            // 参数校验，确保题目id、类别id和标签id不为空
            ParamCheckUtil.checkNotNull(practiceSetDetailDTO.getId(), BusinessErrorEnum.PARAM_ERROR, "id 不能为空!");

            PracticeSetDetail practiceSetDetail = PracticeSetDetailDTOConverter.INSTANCE.convertDto2Entity(practiceSetDetailDTO);
            PracticeSetDetail resultBO = practiceSetDetailService.queryById(practiceSetDetail.getId());
            return Result.success(PracticeSetDetailDTOConverter.INSTANCE.convertEntity2Dto(resultBO));
        } catch (BusinessException e) {
            log.error("PracticeSetDetailController.queryById.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetDetailController.queryById.error:{}", e.getMessage(), e);
            return Result.fail("查询失败！");
        }
    }


    /**
     * 更新信息
     *
     * @param practiceSetDetailDTO dto
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody PracticeSetDetailDTO practiceSetDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetDetailController.update.dto:{}", JSON.toJSON(practiceSetDetailDTO));
            }

            if (practiceSetDetailDTO.getId() == null || practiceSetDetailDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            return Result.success(practiceSetDetailService.update(PracticeSetDetailDTOConverter.INSTANCE.convertDto2Entity(practiceSetDetailDTO)) == 1);
        } catch (BusinessException e) {
            log.error("PracticeSetDetailController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetDetailController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    /**
     * 删除
     *
     * @param practiceSetDetailDTO dto
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody PracticeSetDetailDTO practiceSetDetailDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetDetailController.delete.dto:{}", JSON.toJSON(practiceSetDetailDTO));
            }

            Long id = practiceSetDetailDTO.getId();
            if (id == null || id < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }
            return Result.success(practiceSetDetailService.deleteById(id) == 1);
        } catch (BusinessException e) {
            log.error("PracticeSetDetailController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetDetailController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除失败！");
        }
    }
}

