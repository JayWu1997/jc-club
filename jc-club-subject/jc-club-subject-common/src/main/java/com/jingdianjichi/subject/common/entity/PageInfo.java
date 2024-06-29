package com.jingdianjichi.subject.common.entity;

import lombok.Data;

/**
 * 分页请求信息
 *
 * @author jay
 * @since 2024/6/25 下午9:47
 */
@Data
public class PageInfo {

    private Integer pageSize;

    private Integer pageNo;

    public PageInfo(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNo = pageNum;
    }

    /**
     * 获取页码，如果页码为空或者小于1，则返回1
     * @return 当前的页码
     */
    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    /**
     * 获取每页显示的记录数，如果记录数为空或者小于1或者大于100，则返回20
     * @return 每页显示的记录数
     */
    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1 || pageSize > 100) {
            return 20;
        }
        return pageSize;
    }

}
