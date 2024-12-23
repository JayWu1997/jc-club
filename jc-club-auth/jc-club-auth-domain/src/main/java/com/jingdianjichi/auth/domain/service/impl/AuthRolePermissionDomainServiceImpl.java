package com.jingdianjichi.auth.domain.service.impl;

import com.jingdianjichi.auth.common.enums.IsDeletedEnum;
import com.jingdianjichi.auth.common.enums.ResultCodeEnum;
import com.jingdianjichi.auth.common.util.ParamCheckUtil;
import com.jingdianjichi.auth.domain.entity.AuthRolePermissionBO;
import com.jingdianjichi.auth.domain.service.AuthRolePermissionDomainService;
import com.jingdianjichi.auth.infra.base.entity.AuthRolePermission;
import com.jingdianjichi.auth.infra.base.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色权限关联领域服务实现类
 * @author jay
 * @since 2024/12/23 下午11:40
 */
@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    /**
     * 新增角色权限关联
     *
     * @param authRolePermissionBO 角色权限关联信息
     */
    @Override
    public void add(AuthRolePermissionBO authRolePermissionBO) {
        // 1.插入关联表
        List<AuthRolePermission> mappingList = authRolePermissionBO.getPermissionIdList().stream().map(permissionId -> {
            AuthRolePermission mapping = new AuthRolePermission();
            mapping.setPermissionId(permissionId);
            mapping.setRoleId(authRolePermissionBO.getRoleId());
            mapping.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
            return mapping;
        }).collect(Collectors.toList());
        ParamCheckUtil.checkNotFalse(authRolePermissionService.insertBatch(mappingList) > 0, ResultCodeEnum.FAIL, "角色和权限关联失败");
        //TODO 2.更新redis
    }

    /**
     * 删除角色权限关联
     *
     * @param id 主键
     */
    @Override
    public void delete(Long id) {
        // 1.删除关联
        ParamCheckUtil.checkNotFalse(authRolePermissionService.deleteById(id), ResultCodeEnum.FAIL, "删除角色权限关联失败");
        //TODO 2.更新redis
    }
}
