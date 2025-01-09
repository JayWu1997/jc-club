package com.jingdianjichi.practice.server.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.practice.api.common.PageResult;
import com.jingdianjichi.practice.api.req.PracticeSetDTO;
import com.jingdianjichi.practice.api.resp.Result;
import com.jingdianjichi.practice.api.vo.SpecialPracticeVO;
import com.jingdianjichi.practice.server.common.enums.BusinessErrorEnum;
import com.jingdianjichi.practice.server.common.exception.BusinessException;
import com.jingdianjichi.practice.server.common.util.ParamCheckUtil;
import com.jingdianjichi.practice.server.convert.PracticeSetDTOConverter;
import com.jingdianjichi.practice.server.entity.PracticeSet;
import com.jingdianjichi.practice.server.service.PracticeSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 套题信息表(PracticeSet)表控制层
 *
 * @author jay
 * @since 2025-01-08 18:21:20
 */
@Slf4j
@RestController
@RequestMapping("/practiceSet")
public class PracticeSetController {

    @Resource
    private PracticeSetService practiceSetService;

    @RequestMapping("/getSpecialPracticeContent")
    public Result<List<SpecialPracticeVO>> getSpecialPracticeContent() {
        try {
            List<SpecialPracticeVO> voList = practiceSetService.getSpecialPracticeContent();
            return Result.success(voList);
        } catch (BusinessException e) {
            log.error("PracticeSetController.getSpecialPracticeContent.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetController.getSpecialPracticeContent.error:{}", e.getMessage(), e);
            return Result.fail("查询失败！");
        }
    }

    /**
     * 新增
     *
     * @param practiceSetDTO dto
     * @return 分页结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody PracticeSetDTO practiceSetDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetController.add.dto:{}", JSON.toJSON(practiceSetDTO));
            }
            PracticeSet practiceSet = PracticeSetDTOConverter.INSTANCE.convertDto2Entity(practiceSetDTO);
            return Result.success(practiceSetService.insert(practiceSet) == 1);
        } catch (BusinessException e) {
            log.error("PracticeSetController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加失败！");
        }
    }

    /**
     * 获取分页
     *
     * @param practiceSetDTO dto
     * @return 分页数据
     */
    @PostMapping("/queryByPage")
    public Result<PageResult<PracticeSetDTO>> queryByPage(@RequestBody PracticeSetDTO practiceSetDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetController.queryByPage.practiceSetDTO:{}", JSON.toJSON(practiceSetDTO));
            }

            Integer pageNo = practiceSetDTO.getPageNo();
            ParamCheckUtil.checkNotNull(pageNo, BusinessErrorEnum.PARAM_ERROR, "页码不能为空!");
            Integer pageSize = practiceSetDTO.getPageSize();
            ParamCheckUtil.checkNotNull(pageSize, BusinessErrorEnum.PARAM_ERROR, "分页大小不能为空!");

            PracticeSet queryCondition = PracticeSetDTOConverter.INSTANCE.convertDto2Entity(practiceSetDTO);
            long count = practiceSetService.count(queryCondition);
            List<PracticeSet> practiceSetList = new ArrayList<>();
            if (count > 0) {
                practiceSetList = practiceSetService.queryByPage(queryCondition, (pageNo - 1) * pageSize, pageSize);
            }
            return Result.success(new PageResult<>(pageNo, pageSize, (int) count, PracticeSetDTOConverter.INSTANCE.convertEntity2Dto(practiceSetList)));
        } catch (BusinessException e) {
            log.error("PracticeSetController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetController.queryByPage.error:{}", e.getMessage(), e);
            return Result.fail("获取分页失败！");
        }
    }

    /**
     * 根据 id 查询
     *
     * @param practiceSetDTO dto
     * @return 结果
     */
    @PostMapping("/queryById")
    public Result<PracticeSetDTO> queryById(@RequestBody PracticeSetDTO practiceSetDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetController.queryById.practiceSetDTO:{}", JSON.toJSON(practiceSetDTO));
            }

            // 参数校验，确保题目id、类别id和标签id不为空
            Long id = practiceSetDTO.getId();
            ParamCheckUtil.checkNotNull(id, BusinessErrorEnum.PARAM_ERROR, "id 不能为空!");

            PracticeSet resultBO = practiceSetService.queryById(id);
            return Result.success(PracticeSetDTOConverter.INSTANCE.convertEntity2Dto(resultBO));
        } catch (BusinessException e) {
            log.error("PracticeSetController.queryById.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetController.queryById.error:{}", e.getMessage(), e);
            return Result.fail("查询失败！");
        }
    }


    /**
     * 更新信息
     *
     * @param practiceSetDTO dto
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody PracticeSetDTO practiceSetDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetController.update.dto:{}", JSON.toJSON(practiceSetDTO));
            }

            if (practiceSetDTO.getId() == null || practiceSetDTO.getId() < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            return Result.success(practiceSetService.update(PracticeSetDTOConverter.INSTANCE.convertDto2Entity(practiceSetDTO)) == 1);
        } catch (BusinessException e) {
            log.error("PracticeSetController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新失败！");
        }
    }

    /**
     * 删除
     *
     * @param practiceSetDTO dto
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody PracticeSetDTO practiceSetDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PracticeSetController.delete.dto:{}", JSON.toJSON(practiceSetDTO));
            }

            Long id = practiceSetDTO.getId();
            if (id == null || id < 0) {
                throw new BusinessException(BusinessErrorEnum.PARAM_ERROR, "ID 不合法!");
            }

            return Result.success(practiceSetService.deleteById(id) == 1);
        } catch (BusinessException e) {
            log.error("PracticeSetController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("PracticeSetController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除失败！");
        }
    }
}

