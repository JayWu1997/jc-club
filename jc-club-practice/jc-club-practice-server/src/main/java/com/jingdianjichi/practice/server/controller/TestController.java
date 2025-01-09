package com.jingdianjichi.practice.server.controller;


import com.jingdianjichi.practice.api.resp.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jay
 * @since 2025/1/3 下午12:09
 */
@RequestMapping("/practice")
@RestController
public class TestController {


    @RequestMapping("/test")
    public Result<String> test() {
        return Result.success("hello");
    }
}
