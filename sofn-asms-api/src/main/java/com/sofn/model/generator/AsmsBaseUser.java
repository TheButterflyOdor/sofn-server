package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsBaseUser extends BaseModel {
    private String baseInspectionId;

    private String inspectionUserId;

    public String getBaseInspectionId() {
        return baseInspectionId;
    }

    public void setBaseInspectionId(String baseInspectionId) {
        this.baseInspectionId = baseInspectionId == null ? null : baseInspectionId.trim();
    }

    public String getInspectionUserId() {
        return inspectionUserId;
    }

    public void setInspectionUserId(String inspectionUserId) {
        this.inspectionUserId = inspectionUserId == null ? null : inspectionUserId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", baseInspectionId=").append(baseInspectionId);
        sb.append(", inspectionUserId=").append(inspectionUserId);
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
        AsmsBaseUser other = (AsmsBaseUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBaseInspectionId() == null ? other.getBaseInspectionId() == null : this.getBaseInspectionId().equals(other.getBaseInspectionId()))
            && (this.getInspectionUserId() == null ? other.getInspectionUserId() == null : this.getInspectionUserId().equals(other.getInspectionUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBaseInspectionId() == null) ? 0 : getBaseInspectionId().hashCode());
        result = prime * result + ((getInspectionUserId() == null) ? 0 : getInspectionUserId().hashCode());
        return result;
    }
}