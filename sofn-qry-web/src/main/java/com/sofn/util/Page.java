package com.sofn.util;

import com.sofn.core.constant.ApiConstants;

import java.io.Serializable;

/**
 * @author sofn
 * @version 2016年10月18日 下午 5:31
 */
public class Page implements Serializable{

    private static final long serialVersionUID = 896044207899014135L;
    public static final long DEFAULT_PAGE_SIZE = ApiConstants.DEFUALUT_PAGESIZE;

    private long pageOffset ;
    private long pageTail ;
    private long pageCount ; // 总页数

    // 安全验证 用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。
    // 要求在服务器接收到此参数后再返回
    private long draw;

    /**
     * recordsTotal : 结果总数（dataTables的分页参数）
     */
    private long recordsTotal;

    /**
     * recordsFiltered 条件过滤后记录数 必要 （dataTables的分页参数）
     */
    private long recordsFiltered;

    /**
     * start :记录开始处（dataTables的分页参数）
     */
    private long start;
    /**
     * length :每页记录数（dataTables的分页参数）
     */
    private long length;

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getPageOffset() {
        return this.start+1;
    }

    public void setPageOffset(long pageOffset) {
        this.pageOffset = pageOffset;
    }

    public long getPageTail() {
        return this.start+this.length;
    }

    public void setPageTail(long pageTail) {
        this.pageTail = pageTail;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getDraw() {
        return draw;
    }

    public void setDraw(long draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }
}
