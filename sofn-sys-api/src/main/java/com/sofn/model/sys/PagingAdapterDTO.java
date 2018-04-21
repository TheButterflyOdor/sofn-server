package com.sofn.model.sys;

import java.io.Serializable;

/**
 * 分页适配数据传输对象
 * <p>
 * 解决前端分页参数和后端分页查询参数不匹配的问题
 */
public class PagingAdapterDTO implements Serializable {
    private static final long serialVersionUID = 7578620901697721979L;
    private static final Integer DEFAULT_PAGE_NUM = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private Integer pageSize;
    private Integer pageNum;
    private Integer start;
    private Integer length;

    public Integer getPageSize() {
        return (pageSize == null) ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        if (this.start != null && this.length != null) {
            return (this.start / this.length) + 1;
        }
        return (pageNum == null) ? DEFAULT_PAGE_NUM : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
        this.pageSize = length;
    }
}
