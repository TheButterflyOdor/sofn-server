package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsCheckBearUnit extends BaseModel {
    private String superviseCheckTaskId;

    private String superviseBearUnitId;

    private String leadUnitName;

    public String getSuperviseCheckTaskId() {
        return superviseCheckTaskId;
    }

    public void setSuperviseCheckTaskId(String superviseCheckTaskId) {
        this.superviseCheckTaskId = superviseCheckTaskId == null ? null : superviseCheckTaskId.trim();
    }

    public String getSuperviseBearUnitId() {
        return superviseBearUnitId;
    }

    public void setSuperviseBearUnitId(String superviseBearUnitId) {
        this.superviseBearUnitId = superviseBearUnitId == null ? null : superviseBearUnitId.trim();
    }

    public String getLeadUnitName() {
        return leadUnitName;
    }

    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName == null ? null : leadUnitName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", superviseCheckTaskId=").append(superviseCheckTaskId);
        sb.append(", superviseBearUnitId=").append(superviseBearUnitId);
        sb.append(", leadUnitName=").append(leadUnitName);
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
        AsmsCheckBearUnit other = (AsmsCheckBearUnit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSuperviseCheckTaskId() == null ? other.getSuperviseCheckTaskId() == null : this.getSuperviseCheckTaskId().equals(other.getSuperviseCheckTaskId()))
            && (this.getSuperviseBearUnitId() == null ? other.getSuperviseBearUnitId() == null : this.getSuperviseBearUnitId().equals(other.getSuperviseBearUnitId()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getLeadUnitName() == null ? other.getLeadUnitName() == null : this.getLeadUnitName().equals(other.getLeadUnitName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSuperviseCheckTaskId() == null) ? 0 : getSuperviseCheckTaskId().hashCode());
        result = prime * result + ((getSuperviseBearUnitId() == null) ? 0 : getSuperviseBearUnitId().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getLeadUnitName() == null) ? 0 : getLeadUnitName().hashCode());
        return result;
    }
}