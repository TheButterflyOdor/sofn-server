package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsRecheckObject extends BaseModel {
    private String recheckTaskId;

    private String recheckSampleName;

    private String recheckSampleCode;

    private String recheckUnitId;

    private String recheckStandard;

    private String recheckJudgeStandard;

    private String enable;

    private String recheckUnitName;

    private String testedDeparment;

    public String getRecheckTaskId() {
        return recheckTaskId;
    }

    public void setRecheckTaskId(String recheckTaskId) {
        this.recheckTaskId = recheckTaskId == null ? null : recheckTaskId.trim();
    }

    public String getRecheckSampleName() {
        return recheckSampleName;
    }

    public void setRecheckSampleName(String recheckSampleName) {
        this.recheckSampleName = recheckSampleName == null ? null : recheckSampleName.trim();
    }

    public String getRecheckSampleCode() {
        return recheckSampleCode;
    }

    public void setRecheckSampleCode(String recheckSampleCode) {
        this.recheckSampleCode = recheckSampleCode == null ? null : recheckSampleCode.trim();
    }

    public String getRecheckUnitId() {
        return recheckUnitId;
    }

    public void setRecheckUnitId(String recheckUnitId) {
        this.recheckUnitId = recheckUnitId == null ? null : recheckUnitId.trim();
    }

    public String getRecheckStandard() {
        return recheckStandard;
    }

    public void setRecheckStandard(String recheckStandard) {
        this.recheckStandard = recheckStandard == null ? null : recheckStandard.trim();
    }

    public String getRecheckJudgeStandard() {
        return recheckJudgeStandard;
    }

    public void setRecheckJudgeStandard(String recheckJudgeStandard) {
        this.recheckJudgeStandard = recheckJudgeStandard == null ? null : recheckJudgeStandard.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getRecheckUnitName() {
        return recheckUnitName;
    }

    public void setRecheckUnitName(String recheckUnitName) {
        this.recheckUnitName = recheckUnitName == null ? null : recheckUnitName.trim();
    }

    public String getTestedDeparment() {
        return testedDeparment;
    }

    public void setTestedDeparment(String testedDeparment) {
        this.testedDeparment = testedDeparment == null ? null : testedDeparment.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recheckTaskId=").append(recheckTaskId);
        sb.append(", recheckSampleName=").append(recheckSampleName);
        sb.append(", recheckSampleCode=").append(recheckSampleCode);
        sb.append(", recheckUnitId=").append(recheckUnitId);
        sb.append(", recheckStandard=").append(recheckStandard);
        sb.append(", recheckJudgeStandard=").append(recheckJudgeStandard);
        sb.append(", enable=").append(enable);
        sb.append(", recheckUnitName=").append(recheckUnitName);
        sb.append(", testedDeparment=").append(testedDeparment);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AsmsRecheckObject other = (AsmsRecheckObject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRecheckTaskId() == null ? other.getRecheckTaskId() == null : this.getRecheckTaskId().equals(other.getRecheckTaskId()))
            && (this.getRecheckSampleName() == null ? other.getRecheckSampleName() == null : this.getRecheckSampleName().equals(other.getRecheckSampleName()))
            && (this.getRecheckSampleCode() == null ? other.getRecheckSampleCode() == null : this.getRecheckSampleCode().equals(other.getRecheckSampleCode()))
            && (this.getRecheckUnitId() == null ? other.getRecheckUnitId() == null : this.getRecheckUnitId().equals(other.getRecheckUnitId()))
            && (this.getRecheckStandard() == null ? other.getRecheckStandard() == null : this.getRecheckStandard().equals(other.getRecheckStandard()))
            && (this.getRecheckJudgeStandard() == null ? other.getRecheckJudgeStandard() == null : this.getRecheckJudgeStandard().equals(other.getRecheckJudgeStandard()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getRecheckUnitName() == null ? other.getRecheckUnitName() == null : this.getRecheckUnitName().equals(other.getRecheckUnitName()))
            && (this.getTestedDeparment() == null ? other.getTestedDeparment() == null : this.getTestedDeparment().equals(other.getTestedDeparment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRecheckTaskId() == null) ? 0 : getRecheckTaskId().hashCode());
        result = prime * result + ((getRecheckSampleName() == null) ? 0 : getRecheckSampleName().hashCode());
        result = prime * result + ((getRecheckSampleCode() == null) ? 0 : getRecheckSampleCode().hashCode());
        result = prime * result + ((getRecheckUnitId() == null) ? 0 : getRecheckUnitId().hashCode());
        result = prime * result + ((getRecheckStandard() == null) ? 0 : getRecheckStandard().hashCode());
        result = prime * result + ((getRecheckJudgeStandard() == null) ? 0 : getRecheckJudgeStandard().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getRecheckUnitName() == null) ? 0 : getRecheckUnitName().hashCode());
        result = prime * result + ((getTestedDeparment() == null) ? 0 : getTestedDeparment().hashCode());
        return result;
    }
}