package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Objects;

/**
 * 检测农药残留限量模型
 * Created by Administrator on 2017/11/13/013.
 */
public class SysPesticideResidueModel extends BaseModel {
    private String testItemId;
    private String testObjectId;
    private String testObjectName;
    private Double upperBound;
    private String unitMeasure;
    private String reservedField1;
    private String reservedField2;
    private String itemName;
    private String standardCode;

    public String getTestItemId() {
        return testItemId;
    }

    public void setTestItemId(String testItemId) {
        this.testItemId = testItemId;
    }

    public String getTestObjectId() {
        return testObjectId;
    }

    public void setTestObjectId(String testObjectId) {
        this.testObjectId = testObjectId;
    }

    public String getTestObjectName() {
        return testObjectName;
    }

    public void setTestObjectName(String testObjectName) {
        this.testObjectName = testObjectName;
    }

    public Double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public String getReservedField1() {
        return reservedField1;
    }

    public void setReservedField1(String reservedField1) {
        this.reservedField1 = reservedField1;
    }

    public String getReservedField2() {
        return reservedField2;
    }

    public void setReservedField2(String reservedField2) {
        this.reservedField2 = reservedField2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysPesticideResidueModel{");
        sb.append("testItemId='").append(testItemId).append('\'');
        sb.append(", itemName='").append(itemName).append('\'');
        sb.append(", standardCode='").append(standardCode).append('\'');
        sb.append(", testObjectId='").append(testObjectId).append('\'');
        sb.append(", testObjectName='").append(testObjectName).append('\'');
        sb.append(", upperBound=").append(upperBound);
        sb.append(", unitMeasure='").append(unitMeasure).append('\'');
        sb.append(", delFlag='").append(getDelFlag()).append('\'');
        sb.append(", reservedField1='").append(reservedField1).append('\'');
        sb.append(", reservedField2='").append(reservedField2).append('\'');
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", remark='").append(getRemark()).append('\'');
        sb.append(", createBy='").append(getCreateBy()).append('\'');
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", updateBy='").append(getUpdateBy()).append('\'');
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPesticideResidueModel that = (SysPesticideResidueModel) o;
        return Objects.equals(testItemId, that.testItemId) &&
                Objects.equals(testObjectId, that.testObjectId) &&
                Objects.equals(testObjectName, that.testObjectName) &&
                Objects.equals(upperBound, that.upperBound) &&
                Objects.equals(unitMeasure, that.unitMeasure) &&
                Objects.equals(reservedField1, that.reservedField1) &&
                Objects.equals(reservedField2, that.reservedField2) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRemark(), that.getRemark()) &&
                Objects.equals(getCreateBy(), that.getCreateBy()) &&
                Objects.equals(getCreateTime(), that.getCreateTime()) &&
                Objects.equals(getUpdateBy(), that.getUpdateBy()) &&
                Objects.equals(getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(getDelFlag(), that.getDelFlag()) &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(standardCode, that.standardCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, standardCode, testItemId, testObjectId, testObjectName, upperBound, unitMeasure, reservedField1, reservedField2, getId(), getRemark(), getDelFlag(), getCreateBy(), getCreateTime(), getUpdateBy(), getUpdateTime());
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }
}
