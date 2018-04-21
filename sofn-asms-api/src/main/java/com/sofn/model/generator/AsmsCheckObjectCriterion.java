package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class AsmsCheckObjectCriterion extends BaseModel {
    private String enable;

    private String objectId;

    private String taskId;

    private String criterionName;

    private String criterionId;

    private BigDecimal spperLimit;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getCriterionName() {
        return criterionName;
    }

    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName == null ? null : criterionName.trim();
    }

    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId == null ? null : criterionId.trim();
    }

    public BigDecimal getSpperLimit() {
        return spperLimit;
    }

    public void setSpperLimit(BigDecimal spperLimit) {
        this.spperLimit = spperLimit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", enable=").append(enable);
        sb.append(", objectId=").append(objectId);
        sb.append(", taskId=").append(taskId);
        sb.append(", criterionName=").append(criterionName);
        sb.append(", criterionId=").append(criterionId);
        sb.append(", spperLimit=").append(spperLimit);
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
        AsmsCheckObjectCriterion other = (AsmsCheckObjectCriterion) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getObjectId() == null ? other.getObjectId() == null : this.getObjectId().equals(other.getObjectId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getCriterionName() == null ? other.getCriterionName() == null : this.getCriterionName().equals(other.getCriterionName()))
            && (this.getCriterionId() == null ? other.getCriterionId() == null : this.getCriterionId().equals(other.getCriterionId()))
            && (this.getSpperLimit() == null ? other.getSpperLimit() == null : this.getSpperLimit().equals(other.getSpperLimit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getObjectId() == null) ? 0 : getObjectId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getCriterionName() == null) ? 0 : getCriterionName().hashCode());
        result = prime * result + ((getCriterionId() == null) ? 0 : getCriterionId().hashCode());
        result = prime * result + ((getSpperLimit() == null) ? 0 : getSpperLimit().hashCode());
        return result;
    }
}