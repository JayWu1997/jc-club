package com.jingdianjichi.practice.server.req;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/13 下午6:15
 */
@Data
public class GetUnCompletePracticeReq {

    /**
     * 分页信息
     */
    private PageInfo pageInfo;
}
