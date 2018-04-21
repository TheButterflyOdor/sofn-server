package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Objects;

/**
 * 检测项目模型
 * Created by Administrator on 2017/11/13/013.
 */
public class SysTestItemModel extends BaseModel {
    private String itemName;
    private String scope;
    private String standardCode;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysTestItemModel{");
        sb.append("createBy='").append(getCreateBy()).append('\'');
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", delFlag='").append(getDelFlag()).append('\'');
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", itemName='").append(itemName).append('\'');
        sb.append(", remark='").append(getRemark()).append('\'');
        sb.append(", scope='").append(scope).append('\'');
        sb.append(", standardCode='").append(standardCode).append('\'');
        sb.append(", updateBy='").append(getUpdateBy()).append('\'');
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysTestItemModel that = (SysTestItemModel) o;
        return Objects.equals(itemName, that.itemName) &&
                Objects.equals(scope, that.scope) &&
                Objects.equals(standardCode, that.standardCode) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRemark(), that.getRemark()) &&
                Objects.equals(getCreateBy(), that.getCreateBy()) &&
                Objects.equals(getCreateTime(), that.getCreateTime()) &&
                Objects.equals(getUpdateBy(), that.getUpdateBy()) &&
                Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(getDelFlag(), that.getDelFlag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, scope, standardCode, getId(), getRemark(), getDelFlag(), getCreateBy(), getCreateTime(), getUpdateBy(), getUpdateTime());
    }
}
