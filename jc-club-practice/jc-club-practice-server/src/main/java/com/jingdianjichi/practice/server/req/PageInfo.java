package com.jingdianjichi.practice.server.req;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/11 下午5:23
 */
@Data
public class PageInfo {

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页条数
     */
    private Integer pageSize;
}
