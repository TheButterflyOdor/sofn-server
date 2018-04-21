package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

@SuppressWarnings("serial")
public class TDgapLocalPlatFormAppli extends BaseModel {
    private String platFormName;

    private String platFormAreaId;//平台所属区域

    private String platFormLevel;//平台级别，可选值（PROVINCE,CITY,COUNTY）

    private String industry;//平台行业，可选值（AGRICUTURE,HUSBANDRY,AQUACULTURE）

    private String url;//平台网址

    private String note;//备注

    private String appliCant;//申请人

    private Date appliCationTime;//申请时间

    private String appliCationDepartment;//申请单位

    private String auditUserId;//审核人

    private Date auditTime;//审核时间

    private String auditComments;//审核意见

    private String status;//状态，可选值（NEW,APPLICATED,APPROVED,REJECTED,STARTED,STOPPED）

    public String getPlatFormName() {
        return platFormName;
    }

    public void setPlatFormName(String platFormName) {
        this.platFormName = platFormName;
    }

    public String getPlatFormAreaId() {
        return platFormAreaId;
    }

    public void setPlatFormAreaId(String platFormAreaId) {
        this.platFormAreaId = platFormAreaId;
    }

    public String getPlatFormLevel() {
        return platFormLevel;
    }

    public void setPlatFormLevel(String platFormLevel) {
        this.platFormLevel = platFormLevel;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAppliCant() {
        return appliCant;
    }

    public void setAppliCant(String appliCant) {
        this.appliCant = appliCant;
    }

    public Date getAppliCationTime() {
        return appliCationTime;
    }

    public void setAppliCationTime(Date appliCationTime) {
        this.appliCationTime = appliCationTime;
    }

    public String getAppliCationDepartment() {
        return appliCationDepartment;
    }

    public void setAppliCationDepartment(String appliCationDepartment) {
        this.appliCationDepartment = appliCationDepartment;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditComments() {
        return auditComments;
    }

    public void setAuditComments(String auditComments) {
        this.auditComments = auditComments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TDgapLocalPlatFormAppli{" +
                "platFormName='" + platFormName + '\'' +
                ", platFormAreaId='" + platFormAreaId + '\'' +
                ", platFormLevel='" + platFormLevel + '\'' +
                ", industry='" + industry + '\'' +
                ", url='" + url + '\'' +
                ", note='" + note + '\'' +
                ", appliCant='" + appliCant + '\'' +
                ", appliCationTime=" + appliCationTime +
                ", appliCationDepartment='" + appliCationDepartment + '\'' +
                ", auditUserId='" + auditUserId + '\'' +
                ", auditTime=" + auditTime +
                ", auditComments='" + auditComments + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDgapLocalPlatFormAppli that = (TDgapLocalPlatFormAppli) o;

        if (platFormName != null ? !platFormName.equals(that.platFormName) : that.platFormName != null) return false;
        if (platFormAreaId != null ? !platFormAreaId.equals(that.platFormAreaId) : that.platFormAreaId != null)
            return false;
        if (platFormLevel != null ? !platFormLevel.equals(that.platFormLevel) : that.platFormLevel != null)
            return false;
        if (industry != null ? !industry.equals(that.industry) : that.industry != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (appliCant != null ? !appliCant.equals(that.appliCant) : that.appliCant != null) return false;
        if (appliCationTime != null ? !appliCationTime.equals(that.appliCationTime) : that.appliCationTime != null)
            return false;
        if (appliCationDepartment != null ? !appliCationDepartment.equals(that.appliCationDepartment) : that.appliCationDepartment != null)
            return false;
        if (auditUserId != null ? !auditUserId.equals(that.auditUserId) : that.auditUserId != null) return false;
        if (auditTime != null ? !auditTime.equals(that.auditTime) : that.auditTime != null) return false;
        if (auditComments != null ? !auditComments.equals(that.auditComments) : that.auditComments != null)
            return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = platFormName != null ? platFormName.hashCode() : 0;
        result = 31 * result + (platFormAreaId != null ? platFormAreaId.hashCode() : 0);
        result = 31 * result + (platFormLevel != null ? platFormLevel.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (appliCant != null ? appliCant.hashCode() : 0);
        result = 31 * result + (appliCationTime != null ? appliCationTime.hashCode() : 0);
        result = 31 * result + (appliCationDepartment != null ? appliCationDepartment.hashCode() : 0);
        result = 31 * result + (auditUserId != null ? auditUserId.hashCode() : 0);
        result = 31 * result + (auditTime != null ? auditTime.hashCode() : 0);
        result = 31 * result + (auditComments != null ? auditComments.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}