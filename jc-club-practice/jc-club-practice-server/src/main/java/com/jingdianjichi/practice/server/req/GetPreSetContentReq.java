package com.jingdianjichi.practice.server.req;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/11 下午5:22
 */
@Data
public class GetPreSetContentReq {

    /**
     * 分页信息
     */
    private PageInfo pageInfo;

    /**
     * 排序方式 1:默认
     *        2: 最新
     *        3: 最热
     */
    private String orderType;

    /**
     * 套题名称模糊查询
     */
    private String setName;
}
