package com.jingdianjichi.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.auth.application.converter.AuthRoleDTOConverter;
import com.jingdianjichi.auth.api.req.AuthRoleDTO;
import com.jingdianjichi.auth.api.resp.Result;
import com.jingdianjichi.auth.common.enums.BusinessErrorEnum;
import com.jingdianjichi.auth.common.exception.BusinessException;
import com.jingdianjichi.auth.common.util.ParamCheckUtil;
import com.jingdianjichi.auth.domain.entity.AuthRoleBO;
import com.jingdianjichi.auth.domain.service.AuthRoleDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色管理
 * @author jay
 * @since 2024/12/21 下午7:35
 */
@RestController
@RequestMapping("/role/")
@Slf4j
public class AuthRoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;

    /**
     * 添加角色
     * @param authRoleDTO 角色信息
     * @return 
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthRoleController.add.authRoleDTO:{}", JSON.toJSON(authRoleDTO));
            }
            ParamCheckUtil.checkStrNotEmpty(authRoleDTO.getRoleName(), BusinessErrorEnum.PARAM_ERROR, "角色不能为空!");
            ParamCheckUtil.checkStrNotEmpty(authRoleDTO.getRoleKey(), BusinessErrorEnum.PARAM_ERROR, "角色唯一标识不能为空!");

            AuthRoleBO authUserBO = AuthRoleDTOConverter.INSTANCE.convertDto2Bo(authRoleDTO);
            Boolean result = authRoleDomainService.add(authUserBO);
            return Result.success(result);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRoleController.add.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRoleController.add.error:{}", e.getMessage(), e);
            }
            return Result.fail("角色添加失败！");
        }
    }

    /**
     * 角色信息更新
     * @param authRoleDTO 角色信息
     * @return 成功标志
     */
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthUserController.update.authRoleDTO:{}", JSON.toJSON(authRoleDTO));
            }
            ParamCheckUtil.checkNotNull(authRoleDTO.getId(), BusinessErrorEnum.PARAM_ERROR, "角色 id 不能为空!");

            AuthRoleBO authUserBO = AuthRoleDTOConverter.INSTANCE.convertDto2Bo(authRoleDTO);
            return Result.success(authRoleDomainService.update(authUserBO));
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRoleController.update.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRoleController.update.error:{}", e.getMessage(), e);
            }
            return Result.fail("角色信息更新失败！");
        }
    }

    /**
     * 删除角色
     * @param authRoleDTO 角色信息
     * @return 操作结果标志
     */
    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthUserController.delete.authRoleDTO:{}", JSON.toJSON(authRoleDTO));
            }
            ParamCheckUtil.checkNotNull(authRoleDTO.getId(), BusinessErrorEnum.PARAM_ERROR, "角色 id 不能为空!");

            return Result.success(authRoleDomainService.delete(authRoleDTO.getId()));
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRoleController.delete.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthRoleController.delete.error:{}", e.getMessage(), e);
            }
            return Result.fail("删除角色失败！");
        }
    }
}
