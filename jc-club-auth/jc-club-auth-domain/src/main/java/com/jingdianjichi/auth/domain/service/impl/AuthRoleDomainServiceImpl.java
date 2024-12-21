package com.jingdianjichi.auth.domain.service.impl;

import com.jingdianjichi.auth.common.enums.IsDeletedEnum;
import com.jingdianjichi.auth.domain.converter.AuthRoleBOConverter;
import com.jingdianjichi.auth.domain.entity.AuthRoleBO;
import com.jingdianjichi.auth.domain.service.AuthRoleDomainService;
import com.jingdianjichi.auth.infra.base.entity.AuthRole;
import com.jingdianjichi.auth.infra.base.service.AuthRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色领域服务实现类
 * @author jay
 * @since 2024/12/21 下午7:47
 */
@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    /**
     * 添加角色
     *
     * @param authRoleBO 角色信息
     * @return 操作成功标志
     */
    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        Boolean result = Boolean.FALSE;
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBo2Entity(authRoleBO);
        authRole.setIsDeleted(IsDeletedEnum.NOT_DELETED.getCode());
        if (authRoleService.insert(authRole) > 0) {
            //TODO 添加redis
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * 更新角色信息
     *
     * @param authRoleBO 角色信息
     * @return 操作成功标志
     */
    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        Boolean result = Boolean.FALSE;
        if (authRoleService.update(AuthRoleBOConverter.INSTANCE.convertBo2Entity(authRoleBO)) > 0) {
            //TODO 更新redis
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 操作成功标志
     */
    @Override
    public Boolean delete(Long id) {
        Boolean result = Boolean.FALSE;
        AuthRoleBO authRoleBO = new AuthRoleBO();
        authRoleBO.setId(id);
        authRoleBO.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        if (authRoleService.update(AuthRoleBOConverter.INSTANCE.convertBo2Entity(authRoleBO)) > 0) {
            //TODO 更新redis
            result = Boolean.TRUE;
        }
        return result;
    }
}
