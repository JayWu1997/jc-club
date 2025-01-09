package com.jingdianjichi.practice.server.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.practice.api.common.PageResult;
import com.jingdianjichi.practice.api.req.PracticeInfoDTO;
import com.jingdianjichi.practice.api.resp.Result;
import com.jingdianjichi.practice.server.common.enums.BusinessErrorEnum;
import com.jingdianjichi.practice.server.common.exception.BusinessException;
import com.jingdianjichi.practice.server.common.util.ParamCheckUtil;
import com.jingdianjichi.practice.server.convert.PracticeInfoDTOConverter;
import com.jingdianjichi.practice.server.entity.PracticeInfo;
import com.jingdianjichi.practice.server.service.PracticeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 练习表(PracticeInfo)表控制层
 *
 * @author jay
 * @since 2025-01-08 18:21:19
 */
@Slf4j
@RestController
@RequestMapping("/practiceInfo")
public class PracticeInfoController {

    @Resource
    private PracticeInfoService practiceInfoService;

    /**
     * 新增
     *
     * @param practiceInfoDTO dto
     * @return 分页结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody PracticeInfoDTO practiceInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeInfoController.add.dto:{}", JSON.toJSON(practiceInfoDTO));
            }
            PracticeInfo practiceInfo = PracticeInfoDTOConverter.INSTANCE.convertDto2Entity(practiceInfoDTO);
            return Result.success(practiceInfoService.insert(practiceInfo) == 1);
        } catch (BusinessException e) {
            log.error("PracticeInfoController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeInfoController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加失败！");
        }
    }

    /**
     * 获取分页
     *
     * @param practiceInfoDTO dto
     * @return 分页数据
     */
    @PostMapping("/queryByPage")
    public Result<PageResult<PracticeInfoDTO>> queryByPage(@RequestBody PracticeInfoDTO practiceInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeInfoController.queryByPage.practiceInfoDTO:{}", JSON.toJSON(practiceInfoDTO));
            }

            Integer pageNo = practiceInfoDTO.getPageNo();
            ParamCheckUtil.checkNotNull(pageNo, BusinessErrorEnum.PARAM_ERROR, "页码不能为空!");
            Integer pageSize = practiceInfoDTO.getPageSize();
            ParamCheckUtil.checkNotNull(pageSize, BusinessErrorEnum.PARAM_ERROR, "分页大小不能为空!");

            PracticeInfo queryCondition = PracticeInfoDTOConverter.INSTANCE.convertDto2Entity(practiceInfoDTO);
            long count = practiceInfoService.count(queryCondition);
            List<PracticeInfo> practiceInfoList = new ArrayList<>();
            if (count > 0) {
                practiceInfoList = practiceInfoService.queryByPage(queryCondition, (pageNo-1)*pageSize, pageSize);
            }
            return Result.success(new PageResult<>(pageNo, pageSize, (int) count, PracticeInfoDTOConverter.INSTANCE.convertEntity2Dto(practiceInfoList)));
        } catch (BusinessException e) {
            log.error("PracticeInfoController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeInfoController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail("获取分页失败！");
        }
    }

    /**
     * 根据 id 查询
     *
     * @param practiceInfoDTO dto
     * @return 结果
     */
    @PostMapping("/queryById")
    public Result<PracticeInfoDTO> queryById(@RequestBody PracticeInfoDTO practiceInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeInfoController.queryById.practiceInfoDTO:{}", JSON.toJSON(practiceInfoDTO));
            }

            // 参数校验，确保题目id、类别id和标签id不为空
            Long id = practiceInfoDTO.getId();
            ParamCheckUtil.checkNotNull(id, BusinessErrorEnum.PARAM_ERROR, "id 不能为空!");

            PracticeInfo resultBO = practiceInfoService.queryById(id);
            return Result.success(PracticeInfoDTOConverter.INSTANCE.convertEntity2Dto(resultBO));
        } catch (BusinessException e) {
            log.error("PracticeInfoController.queryById.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeInfoController.queryById.error:{}", e.getMessage(), e);
            return Result.fail("查询失败！");
        }
    }


    /**
     * 更新信息
     *
     * @param practiceInfoDTO dto
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody PracticeInfoDTO practiceInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeInfoController.update.dto:{}", JSON.toJSON(practiceInfoDTO));
            }

            if (practiceInfoDTO.getId() == null || practiceInfoDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            return Result.success(practiceInfoService.update(PracticeInfoDTOConverter.INSTANCE.convertDto2Entity(practiceInfoDTO)) == 1);
        } catch (BusinessException e) {
            log.error("PracticeInfoController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeInfoController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    /**
     * 删除
     *
     * @param practiceInfoDTO dto
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody PracticeInfoDTO practiceInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeInfoController.delete.dto:{}", JSON.toJSON(practiceInfoDTO));
            }

            Long id = practiceInfoDTO.getId();
            if (id == null || id < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            return Result.success(practiceInfoService.deleteById(id) == 1);
        } catch (BusinessException e) {
            log.error("PracticeInfoController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeInfoController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除失败！");
        }
    }
}

