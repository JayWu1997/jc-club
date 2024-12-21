package com.jingdianjichi.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.auth.application.converter.AuthPermissionDTOConverter;
import com.jingdianjichi.auth.application.dto.AuthPermissionDTO;
import com.jingdianjichi.auth.common.entity.Result;
import com.jingdianjichi.auth.common.enums.PermissionShowEnum;
import com.jingdianjichi.auth.common.enums.PermissionStatusEnum;
import com.jingdianjichi.auth.common.enums.ResultCodeEnum;
import com.jingdianjichi.auth.common.exception.BusinessException;
import com.jingdianjichi.auth.common.util.ParamCheckUtil;
import com.jingdianjichi.auth.domain.entity.AuthPermissionBO;
import com.jingdianjichi.auth.domain.service.AuthPermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permission/")
@Slf4j
public class AuthPermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;

    /**
     * 添加权限
     * @param authPermissionDTO 权限信息
     * @return 操作成功标志
     */
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.add.authPermissionDTO:{}", JSON.toJSON(authPermissionDTO));
            }
            ParamCheckUtil.checkStrNotEmpty(authPermissionDTO.getName(), ResultCodeEnum.PARAM_ERROR, "权限名不能为空!");
            ParamCheckUtil.checkStrNotEmpty(authPermissionDTO.getPermissionKey(), ResultCodeEnum.PARAM_ERROR, "key不能为空!");

            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDto2Bo(authPermissionDTO);
            authPermissionDomainService.add(authPermissionBO);
            return Result.success(Boolean.TRUE);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.add.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
           if (log.isErrorEnabled()) {
               log.error("AuthPermissionController.add.error:{}", e.getMessage(), e);
           }
           return Result.fail("添加权限失败！");
        }
    }

    /**
     * 权限信息更新
     * @param authPermissionDTO 权限信息
     * @return 成功标志
     */
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.update.authPermissionDTO:{}", JSON.toJSON(authPermissionDTO));
            }
            ParamCheckUtil.checkNotNull(authPermissionDTO.getId(), ResultCodeEnum.PARAM_ERROR, "权限 id 不能为空!");

            authPermissionDomainService.update(AuthPermissionDTOConverter.INSTANCE.convertDto2Bo(authPermissionDTO));
            return Result.success(Boolean.TRUE);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.update.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.update.error:{}", e.getMessage(), e);
            }
            return Result.fail("权限信息更新失败！");
        }
    }

    /**
     * 删除权限
     * @param authPermissionDTO 权限信息
     * @return 操作成功标志
     */
    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.delete.authPermissionDTO:{}", JSON.toJSON(authPermissionDTO));
            }
            ParamCheckUtil.checkNotNull(authPermissionDTO.getId(), ResultCodeEnum.PARAM_ERROR, "权限 id 不能为空!");
            authPermissionDomainService.delete(authPermissionDTO.getId());
            return Result.success(Boolean.TRUE);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.delete.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.delete.error:{}", e.getMessage(), e);
            }
            return Result.fail("删除权限失败！");
        }
    }

    /**
     * 启用或禁用权限
     * @param authPermissionDTO 启用状态信息
     * @return 操作成功标志
     */
    @PostMapping("enableOrDisable")
    public Result<Boolean> enableOrDisable(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.enableOrDisable.authPermissionDTO:{}", JSON.toJSON(authPermissionDTO));
            }
            ParamCheckUtil.checkNotNull(authPermissionDTO.getId(), ResultCodeEnum.PARAM_ERROR, "权限 id 不能为空!");
            Integer statusCode = authPermissionDTO.getStatus();
            ParamCheckUtil.checkNotNull(statusCode, ResultCodeEnum.PARAM_ERROR, "权限 id 不能为空!");
            ParamCheckUtil.checkNotFalse(statusCode.equals(PermissionStatusEnum.ENABLE.getCode()) || statusCode.equals(PermissionStatusEnum.DISABLE.getCode())
                    , ResultCodeEnum.PARAM_ERROR
                    , "传入的权限启用状态码无效!");
            authPermissionDomainService.enableOrDisable(AuthPermissionDTOConverter.INSTANCE.convertDto2Bo(authPermissionDTO));
            return Result.success(Boolean.TRUE);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.enableOrDisable.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.enableOrDisable.error:{}", e.getMessage(), e);
            }
            return Result.fail("更改权限启用状态失败！");
        }
    }

    /**
     * 展示或隐藏权限
     * @param authPermissionDTO 状态的展示信息
     * @return 操作成功标志
     */
    @PostMapping("presentOrAbsent")
    public Result<Boolean> presentOrAbsent(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.presentOrAbsent.authPermissionDTO:{}", JSON.toJSON(authPermissionDTO));
            }
            ParamCheckUtil.checkNotNull(authPermissionDTO.getId(), ResultCodeEnum.PARAM_ERROR, "权限 id 不能为空!");
            Integer showCode = authPermissionDTO.getShow();
            ParamCheckUtil.checkNotNull(showCode, ResultCodeEnum.PARAM_ERROR, "权限 id 不能为空!");
            ParamCheckUtil.checkNotFalse(showCode.equals(PermissionShowEnum.PRESENT.getCode()) || showCode.equals(PermissionShowEnum.ABSENT.getCode())
                    , ResultCodeEnum.PARAM_ERROR
                    , "传入的权限展示状态码无效!");
            authPermissionDomainService.presentOrAbsent(AuthPermissionDTOConverter.INSTANCE.convertDto2Bo(authPermissionDTO));
            return Result.success(Boolean.TRUE);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.presentOrAbsent.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.presentOrAbsent.error:{}", e.getMessage(), e);
            }
            return Result.fail("更改权限展示状态失败！");
        }
    }

    /**
     * 角色与权限的关联
     * @param authPermissionDTO 参数
     * @return 操作成功标志
     */
    @PostMapping("/mappingRoleAndPermission")
    public Result<Boolean> mappingRoleAndPermission(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.mappingRoleAndPermission.authPermissionDTO:{}", JSON.toJSON(authPermissionDTO));
            }
            ParamCheckUtil.checkNotNull(authPermissionDTO.getRoleId(), ResultCodeEnum.PARAM_ERROR, "角色 id 不能为空!");
            ParamCheckUtil.checkCollNotEmpty(authPermissionDTO.getPermissionIds(), ResultCodeEnum.PARAM_ERROR, "权限 id 列表不能为空!");
            authPermissionDomainService.mappingRoleAndPermission(AuthPermissionDTOConverter.INSTANCE.convertDto2Bo(authPermissionDTO));
            return Result.success(Boolean.TRUE);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.mappingRoleAndPermission.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthPermissionController.mappingRoleAndPermission.error:{}", e.getMessage(), e);
            }
            return Result.fail("关联角色与权限失败！");
        }
    }

    
}
