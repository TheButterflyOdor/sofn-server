package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 文档model
 */
public final class SysDocument extends BaseModel {
    private String title; // 标题
    private String content; // 内容
    private String type; // 文档类别
    private String applyTo; // 适用系统
    private String enableFlag; // 启用标记

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysDocument{");
        sb.append("title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", applyTo='").append(applyTo).append('\'');
        sb.append(", enableFlag='").append(enableFlag).append('\'');
        sb.append(", delFlag='").append(getDelFlag()).append('\'');
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", remark='").append(getRemark()).append('\'');
        sb.append(", createBy='").append(getCreateBy()).append('\'');
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", updateBy='").append(getUpdateBy()).append('\'');
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append('}');
        return sb.toString();
    }
}
