package com.jingdianjichi.practice.server.req;

import lombok.Data;

/**
 * @author jay
 * @since 2025/1/11 上午10:41
 */
@Data
public class GetScoreDetailReq {

    /**
     * 练习 id
     */
    private Long practiceId;
}
