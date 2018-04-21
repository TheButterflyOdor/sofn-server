package com.sofn.model.generator;


import com.sofn.core.base.BaseModel;
import java.util.Objects;

/**
 * 检测标准模型对象
 * Created by Administrator on 2017/11/13/013.
 */
public class SysTestStandardModel extends BaseModel {
    private String standardCode;
    private String standardName;

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysTestStandardModel{");
        sb.append("createBy='").append(getCreateBy()).append('\'');
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", delFlag='").append(getDelFlag()).append('\'');
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", remark='").append(getRemark()).append('\'');
        sb.append(", standardCode='").append(standardCode).append('\'');
        sb.append(", standardName='").append(standardName).append('\'');
        sb.append(", updateBy='").append(getUpdateBy()).append('\'');
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysTestStandardModel that = (SysTestStandardModel) o;
        return Objects.equals(getStandardCode(), that.getStandardCode()) &&
                Objects.equals(getStandardName(), that.getStandardName())&&
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
        return Objects.hash(getStandardCode(), getStandardName(), getId(), getRemark(), getDelFlag(), getCreateBy(), getCreateTime(), getUpdateBy(), getUpdateTime());
    }
}
