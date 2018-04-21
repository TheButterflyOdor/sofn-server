package com.sofn.model.sys;

import java.io.Serializable;

/**
 * 文档查询参数
 */
public final class SysDocumentQueryParam implements Serializable {
    private String title; // 标题
    private String type; // 文档类别
    private String applyTo; // 适用系统
    private String enableFlag; // 启用标记
    private String createBy; // 创建人
    private String createTime; // 创建时间
    private Integer pageSize; // 每页记录数
    private Integer pageNum; // 页号

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplyTo() {
        return applyTo;
    }

    public void setApplyTo(String applyTo) {
        this.applyTo = applyTo;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysDocumentQueryParam{");
        sb.append("title='").append(title).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", applyTo='").append(applyTo).append('\'');
        sb.append(", enableFlag='").append(enableFlag).append('\'');
        sb.append(", createBy='").append(createBy).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", pageSize=").append(pageSize);
        sb.append(", pageNum=").append(pageNum);
        sb.append('}');
        return sb.toString();
    }
}
