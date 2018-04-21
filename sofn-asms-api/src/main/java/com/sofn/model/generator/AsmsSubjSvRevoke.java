package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

@SuppressWarnings("serial")
public class AsmsSubjSvRevoke extends BaseModel {
    private String svId;

    private String applyReason;

    private String applyUserId;

    private String applySvId;

    private Date applyTime;

    private String auditUserId;

    private String auditSvId;

    private Date auditTime;

    private String auditSuggestion;

    private String auditState;

    public String getSvId() {
        return svId;
    }

    public void setSvId(String svId) {
        this.svId = svId == null ? null : svId.trim();
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId == null ? null : applyUserId.trim();
    }

    public String getApplySvId() {
        return applySvId;
    }

    public void setApplySvId(String applySvId) {
        this.applySvId = applySvId == null ? null : applySvId.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId == null ? null : auditUserId.trim();
    }

    public String getAuditSvId() {
        return auditSvId;
    }

    public void setAuditSvId(String auditSvId) {
        this.auditSvId = auditSvId == null ? null : auditSvId.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditSuggestion() {
        return auditSuggestion;
    }

    public void setAuditSuggestion(String auditSuggestion) {
        this.auditSuggestion = auditSuggestion == null ? null : auditSuggestion.trim();
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState == null ? null : auditState.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", svId=").append(svId);
        sb.append(", applyReason=").append(applyReason);
        sb.append(", applyUserId=").append(applyUserId);
        sb.append(", applySvId=").append(applySvId);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", auditUserId=").append(auditUserId);
        sb.append(", auditSvId=").append(auditSvId);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", auditSuggestion=").append(auditSuggestion);
        sb.append(", auditState=").append(auditState);
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
        AsmsSubjSvRevoke other = (AsmsSubjSvRevoke) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSvId() == null ? other.getSvId() == null : this.getSvId().equals(other.getSvId()))
            && (this.getApplyReason() == null ? other.getApplyReason() == null : this.getApplyReason().equals(other.getApplyReason()))
            && (this.getApplyUserId() == null ? other.getApplyUserId() == null : this.getApplyUserId().equals(other.getApplyUserId()))
            && (this.getApplySvId() == null ? other.getApplySvId() == null : this.getApplySvId().equals(other.getApplySvId()))
            && (this.getApplyTime() == null ? other.getApplyTime() == null : this.getApplyTime().equals(other.getApplyTime()))
            && (this.getAuditUserId() == null ? other.getAuditUserId() == null : this.getAuditUserId().equals(other.getAuditUserId()))
            && (this.getAuditSvId() == null ? other.getAuditSvId() == null : this.getAuditSvId().equals(other.getAuditSvId()))
            && (this.getAuditTime() == null ? other.getAuditTime() == null : this.getAuditTime().equals(other.getAuditTime()))
            && (this.getAuditSuggestion() == null ? other.getAuditSuggestion() == null : this.getAuditSuggestion().equals(other.getAuditSuggestion()))
            && (this.getAuditState() == null ? other.getAuditState() == null : this.getAuditState().equals(other.getAuditState()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSvId() == null) ? 0 : getSvId().hashCode());
        result = prime * result + ((getApplyReason() == null) ? 0 : getApplyReason().hashCode());
        result = prime * result + ((getApplyUserId() == null) ? 0 : getApplyUserId().hashCode());
        result = prime * result + ((getApplySvId() == null) ? 0 : getApplySvId().hashCode());
        result = prime * result + ((getApplyTime() == null) ? 0 : getApplyTime().hashCode());
        result = prime * result + ((getAuditUserId() == null) ? 0 : getAuditUserId().hashCode());
        result = prime * result + ((getAuditSvId() == null) ? 0 : getAuditSvId().hashCode());
        result = prime * result + ((getAuditTime() == null) ? 0 : getAuditTime().hashCode());
        result = prime * result + ((getAuditSuggestion() == null) ? 0 : getAuditSuggestion().hashCode());
        result = prime * result + ((getAuditState() == null) ? 0 : getAuditState().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }
}