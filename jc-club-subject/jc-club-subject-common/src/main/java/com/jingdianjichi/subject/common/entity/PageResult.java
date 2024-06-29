package com.jingdianjichi.subject.common.entity;

import java.util.Collections;
import java.util.List;

/**
 * 分页返回实体
 *
 * @author jay
 * @since 2024/6/25 下午9:52
 */
public class PageResult<T> {

    private Integer pageSize = 20;

    private Integer pageNo = 1;

    private Integer totalPages = 0;

    private Integer total = 0;

    private Integer start = 1;

    private Integer end = 1;

    private List<T> result = Collections.emptyList();

    /**
     * 设置数据总记录数，并根据每页记录数和当前页码计算总页数、起始索引和结束索引。
     *
     * @param total 总记录数
     */
    public void setTotal(Integer total) {
        // 更新总记录数
        this.total = total;

        // 计算总页数：若总记录数能被每页记录数整除，则直接相除；否则加1以覆盖最后一页
        this.totalPages = (total + pageSize - 1) / pageSize;

        // 计算当前页的起始索引：(当前页码 - 1) * 每页记录数，确保至少为0
        this.start = Math.max(0, (pageNo - 1) * pageSize);

        // 计算当前页的结束索引：起始索引 + 每页记录数，但不超过总记录数
        // 注意：原end计算逻辑有误，这里提供一个更正的示例逻辑，实际应用中需根据具体需求调整
        this.end = Math.min(total, this.start + pageSize);
    }
}
