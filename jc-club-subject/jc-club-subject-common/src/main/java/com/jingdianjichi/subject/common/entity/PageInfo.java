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

    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 无参构造方法，默认分页为10条记录，第一页
     */
    public PageInfo() {
    }

    /**
     * 构造方法，传入每页显示的记录数和当前页码
     * @param pageSize 每页显示的记录数
     * @param pageNum 当前页码
     */
    public PageInfo(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNo = pageNum;
    }

    /**
     * 获取页码，如果页码为空或者小于1，则返回1
     * @return 当前的页码
     */
    public Integer getPageNo() {
        if (pageNo == null) {
            return null;
        }
        if (pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    /**
     * 获取每页显示的记录数，如果记录数为空或者小于1或者大于100，则返回20
     * @return 每页显示的记录数
     */
    public Integer getPageSize() {
        if (pageSize == null) {
            return null;
        }
        if (pageSize < 1 || pageSize > 100) {
            return 20;
        }
        return pageSize;
    }

}
