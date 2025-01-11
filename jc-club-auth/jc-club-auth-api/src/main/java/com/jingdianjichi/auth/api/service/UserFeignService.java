package com.jingdianjichi.auth.api.service;

import com.jingdianjichi.auth.api.req.AuthUserDTO;
import com.jingdianjichi.auth.api.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author jay
 * @since 2024/12/26 下午4:53
 */
@Service
@FeignClient(value = "jc-club-auth-dev", contextId = "userFeignService")
public interface UserFeignService {

    /**
     * 获取用户信息
     * @param authUserDTO 查询条件
     * @return 用户信息
     */
    @PostMapping("/user/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);

    /**
     * 批量查询用户信息
     * @param authUserDTO
     * @return
     */
    @PostMapping("/user/batchQueryByUserNames")
    public Result<List<AuthUserDTO>> batchQueryByUserNames(@RequestBody AuthUserDTO authUserDTO);
}
