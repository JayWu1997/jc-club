package com.jingdianjichi.practice.server.req;

import lombok.Data;

import java.util.List;

/**
 * @author jay
 * @since 2025/1/9 下午3:48
 */
@Data
public class AddPracticeReq {
    /**
     * 组装id集合, eg: ["categoryId-labelId"]
     */
    private List<String> assembleIds;
}
