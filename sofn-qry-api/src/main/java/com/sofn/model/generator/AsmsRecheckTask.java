package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

@SuppressWarnings("serial")
public class AsmsRecheckTask extends BaseModel {
    private String recheckTaskName;

    private String recheckTaskYear;

    private Date recheckTaskBegin;

    private Date recheckTaskEnd;

    private String enable;

    private String state;

    private String initTaskType;

    private String initTaskName;

    private String initTaskId;

    private String recheckUnitId;

    private String recheckUnitName;

    private String createOrgId;

    private String createOrgName;

    private String batch;

    private String createOrgRegionId;

    private String levelEnum;

    public String getRecheckTaskName() {
        return recheckTaskName;
    }

    public void setRecheckTaskName(String recheckTaskName) {
        this.recheckTaskName = recheckTaskName == null ? null : recheckTaskName.trim();
    }

    public String getRecheckTaskYear() {
        return recheckTaskYear;
    }

    public void setRecheckTaskYear(String recheckTaskYear) {
        this.recheckTaskYear = recheckTaskYear == null ? null : recheckTaskYear.trim();
    }

    public Date getRecheckTaskBegin() {
        return recheckTaskBegin;
    }

    public void setRecheckTaskBegin(Date recheckTaskBegin) {
        this.recheckTaskBegin = recheckTaskBegin;
    }

    public Date getRecheckTaskEnd() {
        return recheckTaskEnd;
    }

    public void setRecheckTaskEnd(Date recheckTaskEnd) {
        this.recheckTaskEnd = recheckTaskEnd;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getInitTaskType() {
        return initTaskType;
    }

    public void setInitTaskType(String initTaskType) {
        this.initTaskType = initTaskType == null ? null : initTaskType.trim();
    }

    public String getInitTaskName() {
        return initTaskName;
    }

    public void setInitTaskName(String initTaskName) {
        this.initTaskName = initTaskName == null ? null : initTaskName.trim();
    }

    public String getInitTaskId() {
        return initTaskId;
    }

    public void setInitTaskId(String initTaskId) {
        this.initTaskId = initTaskId == null ? null : initTaskId.trim();
    }

    public String getRecheckUnitId() {
        return recheckUnitId;
    }

    public void setRecheckUnitId(String recheckUnitId) {
        this.recheckUnitId = recheckUnitId == null ? null : recheckUnitId.trim();
    }

    public String getRecheckUnitName() {
        return recheckUnitName;
    }

    public void setRecheckUnitName(String recheckUnitName) {
        this.recheckUnitName = recheckUnitName == null ? null : recheckUnitName.trim();
    }

    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId == null ? null : createOrgId.trim();
    }

    public String getCreateOrgName() {
        return createOrgName;
    }

    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName == null ? null : createOrgName.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getCreateOrgRegionId() {
        return createOrgRegionId;
    }

    public void setCreateOrgRegionId(String createOrgRegionId) {
        this.createOrgRegionId = createOrgRegionId == null ? null : createOrgRegionId.trim();
    }

    public String getLevelEnum() {
        return levelEnum;
    }

    public void setLevelEnum(String levelEnum) {
        this.levelEnum = levelEnum == null ? null : levelEnum.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recheckTaskName=").append(recheckTaskName);
        sb.append(", recheckTaskYear=").append(recheckTaskYear);
        sb.append(", recheckTaskBegin=").append(recheckTaskBegin);
        sb.append(", recheckTaskEnd=").append(recheckTaskEnd);
        sb.append(", enable=").append(enable);
        sb.append(", state=").append(state);
        sb.append(", initTaskType=").append(initTaskType);
        sb.append(", initTaskName=").append(initTaskName);
        sb.append(", initTaskId=").append(initTaskId);
        sb.append(", recheckUnitId=").append(recheckUnitId);
        sb.append(", recheckUnitName=").append(recheckUnitName);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", createOrgName=").append(createOrgName);
        sb.append(", batch=").append(batch);
        sb.append(", createOrgRegionId=").append(createOrgRegionId);
        sb.append(", levelEnum=").append(levelEnum);
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
        AsmsRecheckTask other = (AsmsRecheckTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRecheckTaskName() == null ? other.getRecheckTaskName() == null : this.getRecheckTaskName().equals(other.getRecheckTaskName()))
            && (this.getRecheckTaskYear() == null ? other.getRecheckTaskYear() == null : this.getRecheckTaskYear().equals(other.getRecheckTaskYear()))
            && (this.getRecheckTaskBegin() == null ? other.getRecheckTaskBegin() == null : this.getRecheckTaskBegin().equals(other.getRecheckTaskBegin()))
            && (this.getRecheckTaskEnd() == null ? other.getRecheckTaskEnd() == null : this.getRecheckTaskEnd().equals(other.getRecheckTaskEnd()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getInitTaskType() == null ? other.getInitTaskType() == null : this.getInitTaskType().equals(other.getInitTaskType()))
            && (this.getInitTaskName() == null ? other.getInitTaskName() == null : this.getInitTaskName().equals(other.getInitTaskName()))
            && (this.getInitTaskId() == null ? other.getInitTaskId() == null : this.getInitTaskId().equals(other.getInitTaskId()))
            && (this.getRecheckUnitId() == null ? other.getRecheckUnitId() == null : this.getRecheckUnitId().equals(other.getRecheckUnitId()))
            && (this.getRecheckUnitName() == null ? other.getRecheckUnitName() == null : this.getRecheckUnitName().equals(other.getRecheckUnitName()))
            && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
            && (this.getCreateOrgName() == null ? other.getCreateOrgName() == null : this.getCreateOrgName().equals(other.getCreateOrgName()))
            && (this.getBatch() == null ? other.getBatch() == null : this.getBatch().equals(other.getBatch()))
            && (this.getCreateOrgRegionId() == null ? other.getCreateOrgRegionId() == null : this.getCreateOrgRegionId().equals(other.getCreateOrgRegionId()))
            && (this.getLevelEnum() == null ? other.getLevelEnum() == null : this.getLevelEnum().equals(other.getLevelEnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRecheckTaskName() == null) ? 0 : getRecheckTaskName().hashCode());
        result = prime * result + ((getRecheckTaskYear() == null) ? 0 : getRecheckTaskYear().hashCode());
        result = prime * result + ((getRecheckTaskBegin() == null) ? 0 : getRecheckTaskBegin().hashCode());
        result = prime * result + ((getRecheckTaskEnd() == null) ? 0 : getRecheckTaskEnd().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getInitTaskType() == null) ? 0 : getInitTaskType().hashCode());
        result = prime * result + ((getInitTaskName() == null) ? 0 : getInitTaskName().hashCode());
        result = prime * result + ((getInitTaskId() == null) ? 0 : getInitTaskId().hashCode());
        result = prime * result + ((getRecheckUnitId() == null) ? 0 : getRecheckUnitId().hashCode());
        result = prime * result + ((getRecheckUnitName() == null) ? 0 : getRecheckUnitName().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getCreateOrgName() == null) ? 0 : getCreateOrgName().hashCode());
        result = prime * result + ((getBatch() == null) ? 0 : getBatch().hashCode());
        result = prime * result + ((getCreateOrgRegionId() == null) ? 0 : getCreateOrgRegionId().hashCode());
        result = prime * result + ((getLevelEnum() == null) ? 0 : getLevelEnum().hashCode());
        return result;
    }
}