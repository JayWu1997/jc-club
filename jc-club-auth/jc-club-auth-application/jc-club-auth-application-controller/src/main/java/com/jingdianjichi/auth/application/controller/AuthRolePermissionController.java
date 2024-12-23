package com.jingdianjichi.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.auth.application.converter.AuthRolePermissionDTOConverter;
import com.jingdianjichi.auth.application.dto.AuthRolePermissionDTO;
import com.jingdianjichi.auth.common.entity.Result;
import com.jingdianjichi.auth.common.enums.ResultCodeEnum;
import com.jingdianjichi.auth.common.exception.BusinessException;
import com.jingdianjichi.auth.common.util.ParamCheckUtil;
import com.jingdianjichi.auth.domain.entity.AuthRolePermissionBO;
import com.jingdianjichi.auth.domain.service.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限控制器
 * @author jay
 * @since 2024/12/23 下午11:37
 */
@Slf4j
@RestController
@RequestMapping("/rolePermission/")
public class AuthRolePermissionController {
    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 添加角色权限关联
     * @param authRolePermissionDTO 角色权限关联信息
     * @return 操作成功标志
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthRolePermissionController.add.authRolePermissionDTO:{}", JSON.toJSON(authRolePermissionDTO));
            }
            ParamCheckUtil.checkNotNull(authRolePermissionDTO.getRoleId(), ResultCodeEnum.PARAM_ERROR, "角色 id 不能为空!");
            ParamCheckUtil.checkCollNotEmpty(authRolePermissionDTO.getPermissionIdList(), ResultCodeEnum.PARAM_ERROR, "权限 id 集合不能为空!");

            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDto2Bo(authRolePermissionDTO);
            authRolePermissionDomainService.add(authRolePermissionBO);
            return Result.success(Boolean.TRUE);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRolePermissionController.add.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRolePermissionController.add.error:{}", e.getMessage(), e);
            }
            return Result.fail("添加角色权限关联失败！");
        }
    }

    /**
     * 删除角色权限关联
     * @param authRolePermissionDTO 角色权限关联信息
     * @return 操作成功标志
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthRolePermissionController.delete.authRolePermissionDTO:{}", JSON.toJSON(authRolePermissionDTO));
            }
            ParamCheckUtil.checkNotNull(authRolePermissionDTO.getId(), ResultCodeEnum.PARAM_ERROR, "id 不能为空!");

            authRolePermissionDomainService.delete(authRolePermissionDTO.getId());
            return Result.success(Boolean.TRUE);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRolePermissionController.delete.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRolePermissionController.delete.error:{}", e.getMessage(), e);
            }
            return Result.fail("添加角色权限关联失败！");
        }
    }
}
