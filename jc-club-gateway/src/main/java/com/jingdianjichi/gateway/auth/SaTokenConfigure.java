package com.jingdianjichi.gateway.auth;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [Sa-Token 权限认证] 配置类
 *
 * @author click33
 */
@Configuration
public class SaTokenConfigure {
    // 注册 Sa-Token全局过滤器 
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")    /* 拦截全部path */
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    System.out.println("-------- 前端访问path：" + SaHolder.getRequest().getRequestPath());
                    // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    SaRouter.notMatch("/auth/user/register")
                            .notMatch("/auth/user/doLogin")
                            .notMatch("/auth/user/isLogin")
                            .match("/**",  r -> StpUtil.checkLogin())
                            .match("/subject/subject/add", r -> StpUtil.checkPermission("subject:add"))
                            .match("/auth/permission/**", r -> StpUtil.checkRole("admin"))
                            .match("/auth/rolePermission/**", r -> StpUtil.checkRole("admin"));
                })
                ;
    }
}
