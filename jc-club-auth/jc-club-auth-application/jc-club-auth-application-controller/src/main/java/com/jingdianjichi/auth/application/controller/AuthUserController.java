package com.jingdianjichi.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.jingdianjichi.auth.application.converter.AuthUserDTOConverter;
import com.jingdianjichi.auth.application.dto.AuthUserDTO;
import com.jingdianjichi.auth.common.entity.Result;
import com.jingdianjichi.auth.common.enums.ResultCodeEnum;
import com.jingdianjichi.auth.common.exception.BusinessException;
import com.jingdianjichi.auth.common.util.ParamCheckUtil;
import com.jingdianjichi.auth.domain.entity.AuthUserBO;
import com.jingdianjichi.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/")
@Slf4j
public class AuthUserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    /**
     * 用户注册
     *
     * @param authUserDTO 用户注册信息
     * @return token
     */
    @PostMapping("register")
    public Result<SaTokenInfo> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthUserController.register.authUserDTO:{}", JSON.toJSON(authUserDTO));
            }
            ParamCheckUtil.checkStrNotEmpty(authUserDTO.getUserName(), ResultCodeEnum.PARAM_ERROR, "用户名不能为空!");
            ParamCheckUtil.checkStrNotEmpty(authUserDTO.getPassword(), ResultCodeEnum.PARAM_ERROR, "密码不能为空!");
            ParamCheckUtil.checkStrNotEmpty(authUserDTO.getEmail(), ResultCodeEnum.PARAM_ERROR, "邮箱不能为空!");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDto2Bo(authUserDTO);
            SaTokenInfo tokenInfo = authUserDomainService.register(authUserBO);
            return Result.success(tokenInfo);
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.register.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
           if (log.isErrorEnabled()) {
               log.error("AuthUserController.register.error:{}", e.getMessage(), e);
           }
           return Result.fail("用户注册失败！");
        }
    }

    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthUserController.doLogin.validCode:{}", JSON.toJSON(validCode));
            }
            
            ParamCheckUtil.checkStrNotEmpty(validCode, ResultCodeEnum.PARAM_ERROR, "验证码不能为空!");
            SaTokenInfo tokenInfo = authUserDomainService.doLogin(validCode);
            return Result.success(tokenInfo);
        }  catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.doLogin.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.doLogin.error:{}", e.getMessage(), e);
            }
            return Result.fail("操作失败！");
        }
    }

    @RequestMapping("/isLogin")
    public Boolean isLogin() {
        return StpUtil.isLogin();
    }

    /**
     * 获取用户信息
     * @param authUserDTO 查询条件
     * @return 用户信息
     */
    @PostMapping("/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthUserController.getUserInfo.authUserDTO:{}", JSON.toJSON(authUserDTO));
            }

            ParamCheckUtil.checkNotNull(authUserDTO.getUserName(), ResultCodeEnum.PARAM_ERROR, "用户名不能为空!");
            AuthUserBO userBO = authUserDomainService.getUserInfo(AuthUserDTOConverter.INSTANCE.convertDto2Bo(authUserDTO));
            return Result.success(AuthUserDTOConverter.INSTANCE.convertBo2Dto(userBO));
        }  catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.getUserInfo.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.getUserInfo.error:{}", e.getMessage(), e);
            }
            return Result.fail("操作失败！");
        }
    }

    /**
     * 用户信息更新
     * @param authUserDTO 用户信息
     * @return 成功标志
     */
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthUserController.update.authUserDTO:{}", JSON.toJSON(authUserDTO));
            }
            ParamCheckUtil.checkNotNull(authUserDTO.getId(), ResultCodeEnum.PARAM_ERROR, "用户 id 不能为空!");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDto2Bo(authUserDTO);
            return Result.success(authUserDomainService.update(authUserBO));
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.update.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.update.error:{}", e.getMessage(), e);
            }
            return Result.fail("信息更新失败！");
        }
    }


    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("AuthUserController.delete.authUserDTO:{}", JSON.toJSON(authUserDTO));
            }
            ParamCheckUtil.checkNotNull(authUserDTO.getId(), ResultCodeEnum.PARAM_ERROR, "用户 id 不能为空!");

            return Result.success(authUserDomainService.delete(authUserDTO.getId()));
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.delete.error:{}", e.getMessage(), e);
            }
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AuthUserController.delete.error:{}", e.getMessage(), e);
            }
            return Result.fail("删除用户失败！");
        }
    }

    
}
