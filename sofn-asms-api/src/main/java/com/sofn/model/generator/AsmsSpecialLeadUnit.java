package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsSpecialLeadUnit extends BaseModel {
    private String specialMonitorId;

    private String leadUnitId;

    private String leadUnitName;

    public String getSpecialMonitorId() {
        return specialMonitorId;
    }

    public void setSpecialMonitorId(String specialMonitorId) {
        this.specialMonitorId = specialMonitorId == null ? null : specialMonitorId.trim();
    }

    public String getLeadUnitId() {
        return leadUnitId;
    }

    public void setLeadUnitId(String leadUnitId) {
        this.leadUnitId = leadUnitId == null ? null : leadUnitId.trim();
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
        sb.append(", specialMonitorId=").append(specialMonitorId);
        sb.append(", leadUnitId=").append(leadUnitId);
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
        AsmsSpecialLeadUnit other = (AsmsSpecialLeadUnit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpecialMonitorId() == null ? other.getSpecialMonitorId() == null : this.getSpecialMonitorId().equals(other.getSpecialMonitorId()))
            && (this.getLeadUnitId() == null ? other.getLeadUnitId() == null : this.getLeadUnitId().equals(other.getLeadUnitId()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getLeadUnitName() == null ? other.getLeadUnitName() == null : this.getLeadUnitName().equals(other.getLeadUnitName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpecialMonitorId() == null) ? 0 : getSpecialMonitorId().hashCode());
        result = prime * result + ((getLeadUnitId() == null) ? 0 : getLeadUnitId().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getLeadUnitName() == null) ? 0 : getLeadUnitName().hashCode());
        return result;
    }
}