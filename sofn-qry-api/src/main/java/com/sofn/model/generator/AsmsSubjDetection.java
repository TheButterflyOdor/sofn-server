package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsSubjDetection extends BaseModel {
    private String dtName;

    private String dtCode;

    private String dtType;

    private String dtNature;

    private String dtQualifications;

    private String dtRelyOnUnit;

    private String dtLevel;

    private String dtAreaId;

    private String dtAddress;

    private String dtLegalMan;

    private String dtLeader;

    private String dtLeaderPhone;

    private String dtContact;

    private String dtContactPhone;

    private String dtContactQQ;

    private String dtContactEmail;

    private String dtPostcode;

    private String dtTechnicalDirector;

    private String dtQualityDirector;

    private String dtParameter;

    private String dtProductRange;

    private String status;

    private String dtLevelId;

    private String dtNatureId;

    private String dtQualificationsId;

    private String dtTypeId;

    private String dtArea;

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName == null ? null : dtName.trim();
    }

    public String getDtCode() {
        return dtCode;
    }

    public void setDtCode(String dtCode) {
        this.dtCode = dtCode == null ? null : dtCode.trim();
    }

    public String getDtType() {
        return dtType;
    }

    public void setDtType(String dtType) {
        this.dtType = dtType == null ? null : dtType.trim();
    }

    public String getDtNature() {
        return dtNature;
    }

    public void setDtNature(String dtNature) {
        this.dtNature = dtNature == null ? null : dtNature.trim();
    }

    public String getDtQualifications() {
        return dtQualifications;
    }

    public void setDtQualifications(String dtQualifications) {
        this.dtQualifications = dtQualifications == null ? null : dtQualifications.trim();
    }

    public String getDtRelyOnUnit() {
        return dtRelyOnUnit;
    }

    public void setDtRelyOnUnit(String dtRelyOnUnit) {
        this.dtRelyOnUnit = dtRelyOnUnit == null ? null : dtRelyOnUnit.trim();
    }

    public String getDtLevel() {
        return dtLevel;
    }

    public void setDtLevel(String dtLevel) {
        this.dtLevel = dtLevel == null ? null : dtLevel.trim();
    }

    public String getDtAreaId() {
        return dtAreaId;
    }

    public void setDtAreaId(String dtAreaId) {
        this.dtAreaId = dtAreaId == null ? null : dtAreaId.trim();
    }

    public String getDtAddress() {
        return dtAddress;
    }

    public void setDtAddress(String dtAddress) {
        this.dtAddress = dtAddress == null ? null : dtAddress.trim();
    }

    public String getDtLegalMan() {
        return dtLegalMan;
    }

    public void setDtLegalMan(String dtLegalMan) {
        this.dtLegalMan = dtLegalMan == null ? null : dtLegalMan.trim();
    }

    public String getDtLeader() {
        return dtLeader;
    }

    public void setDtLeader(String dtLeader) {
        this.dtLeader = dtLeader == null ? null : dtLeader.trim();
    }

    public String getDtLeaderPhone() {
        return dtLeaderPhone;
    }

    public void setDtLeaderPhone(String dtLeaderPhone) {
        this.dtLeaderPhone = dtLeaderPhone == null ? null : dtLeaderPhone.trim();
    }

    public String getDtContact() {
        return dtContact;
    }

    public void setDtContact(String dtContact) {
        this.dtContact = dtContact == null ? null : dtContact.trim();
    }

    public String getDtContactPhone() {
        return dtContactPhone;
    }

    public void setDtContactPhone(String dtContactPhone) {
        this.dtContactPhone = dtContactPhone == null ? null : dtContactPhone.trim();
    }

    public String getDtContactQQ() {
        return dtContactQQ;
    }

    public void setDtContactQQ(String dtContactQQ) {
        this.dtContactQQ = dtContactQQ == null ? null : dtContactQQ.trim();
    }

    public String getDtContactEmail() {
        return dtContactEmail;
    }

    public void setDtContactEmail(String dtContactEmail) {
        this.dtContactEmail = dtContactEmail == null ? null : dtContactEmail.trim();
    }

    public String getDtPostcode() {
        return dtPostcode;
    }

    public void setDtPostcode(String dtPostcode) {
        this.dtPostcode = dtPostcode == null ? null : dtPostcode.trim();
    }

    public String getDtTechnicalDirector() {
        return dtTechnicalDirector;
    }

    public void setDtTechnicalDirector(String dtTechnicalDirector) {
        this.dtTechnicalDirector = dtTechnicalDirector == null ? null : dtTechnicalDirector.trim();
    }

    public String getDtQualityDirector() {
        return dtQualityDirector;
    }

    public void setDtQualityDirector(String dtQualityDirector) {
        this.dtQualityDirector = dtQualityDirector == null ? null : dtQualityDirector.trim();
    }

    public String getDtParameter() {
        return dtParameter;
    }

    public void setDtParameter(String dtParameter) {
        this.dtParameter = dtParameter == null ? null : dtParameter.trim();
    }

    public String getDtProductRange() {
        return dtProductRange;
    }

    public void setDtProductRange(String dtProductRange) {
        this.dtProductRange = dtProductRange == null ? null : dtProductRange.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDtLevelId() {
        return dtLevelId;
    }

    public void setDtLevelId(String dtLevelId) {
        this.dtLevelId = dtLevelId == null ? null : dtLevelId.trim();
    }

    public String getDtNatureId() {
        return dtNatureId;
    }

    public void setDtNatureId(String dtNatureId) {
        this.dtNatureId = dtNatureId == null ? null : dtNatureId.trim();
    }

    public String getDtQualificationsId() {
        return dtQualificationsId;
    }

    public void setDtQualificationsId(String dtQualificationsId) {
        this.dtQualificationsId = dtQualificationsId == null ? null : dtQualificationsId.trim();
    }

    public String getDtTypeId() {
        return dtTypeId;
    }

    public void setDtTypeId(String dtTypeId) {
        this.dtTypeId = dtTypeId == null ? null : dtTypeId.trim();
    }

    public String getDtArea() {
        return dtArea;
    }

    public void setDtArea(String dtArea) {
        this.dtArea = dtArea == null ? null : dtArea.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dtName=").append(dtName);
        sb.append(", dtCode=").append(dtCode);
        sb.append(", dtType=").append(dtType);
        sb.append(", dtNature=").append(dtNature);
        sb.append(", dtQualifications=").append(dtQualifications);
        sb.append(", dtRelyOnUnit=").append(dtRelyOnUnit);
        sb.append(", dtLevel=").append(dtLevel);
        sb.append(", dtAreaId=").append(dtAreaId);
        sb.append(", dtAddress=").append(dtAddress);
        sb.append(", dtLegalMan=").append(dtLegalMan);
        sb.append(", dtLeader=").append(dtLeader);
        sb.append(", dtLeaderPhone=").append(dtLeaderPhone);
        sb.append(", dtContact=").append(dtContact);
        sb.append(", dtContactPhone=").append(dtContactPhone);
        sb.append(", dtContactQQ=").append(dtContactQQ);
        sb.append(", dtContactEmail=").append(dtContactEmail);
        sb.append(", dtPostcode=").append(dtPostcode);
        sb.append(", dtTechnicalDirector=").append(dtTechnicalDirector);
        sb.append(", dtQualityDirector=").append(dtQualityDirector);
        sb.append(", dtParameter=").append(dtParameter);
        sb.append(", dtProductRange=").append(dtProductRange);
        sb.append(", status=").append(status);
        sb.append(", dtLevelId=").append(dtLevelId);
        sb.append(", dtNatureId=").append(dtNatureId);
        sb.append(", dtQualificationsId=").append(dtQualificationsId);
        sb.append(", dtTypeId=").append(dtTypeId);
        sb.append(", dtArea=").append(dtArea);
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
        AsmsSubjDetection other = (AsmsSubjDetection) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDtName() == null ? other.getDtName() == null : this.getDtName().equals(other.getDtName()))
            && (this.getDtCode() == null ? other.getDtCode() == null : this.getDtCode().equals(other.getDtCode()))
            && (this.getDtType() == null ? other.getDtType() == null : this.getDtType().equals(other.getDtType()))
            && (this.getDtNature() == null ? other.getDtNature() == null : this.getDtNature().equals(other.getDtNature()))
            && (this.getDtQualifications() == null ? other.getDtQualifications() == null : this.getDtQualifications().equals(other.getDtQualifications()))
            && (this.getDtRelyOnUnit() == null ? other.getDtRelyOnUnit() == null : this.getDtRelyOnUnit().equals(other.getDtRelyOnUnit()))
            && (this.getDtLevel() == null ? other.getDtLevel() == null : this.getDtLevel().equals(other.getDtLevel()))
            && (this.getDtAreaId() == null ? other.getDtAreaId() == null : this.getDtAreaId().equals(other.getDtAreaId()))
            && (this.getDtAddress() == null ? other.getDtAddress() == null : this.getDtAddress().equals(other.getDtAddress()))
            && (this.getDtLegalMan() == null ? other.getDtLegalMan() == null : this.getDtLegalMan().equals(other.getDtLegalMan()))
            && (this.getDtLeader() == null ? other.getDtLeader() == null : this.getDtLeader().equals(other.getDtLeader()))
            && (this.getDtLeaderPhone() == null ? other.getDtLeaderPhone() == null : this.getDtLeaderPhone().equals(other.getDtLeaderPhone()))
            && (this.getDtContact() == null ? other.getDtContact() == null : this.getDtContact().equals(other.getDtContact()))
            && (this.getDtContactPhone() == null ? other.getDtContactPhone() == null : this.getDtContactPhone().equals(other.getDtContactPhone()))
            && (this.getDtContactQQ() == null ? other.getDtContactQQ() == null : this.getDtContactQQ().equals(other.getDtContactQQ()))
            && (this.getDtContactEmail() == null ? other.getDtContactEmail() == null : this.getDtContactEmail().equals(other.getDtContactEmail()))
            && (this.getDtPostcode() == null ? other.getDtPostcode() == null : this.getDtPostcode().equals(other.getDtPostcode()))
            && (this.getDtTechnicalDirector() == null ? other.getDtTechnicalDirector() == null : this.getDtTechnicalDirector().equals(other.getDtTechnicalDirector()))
            && (this.getDtQualityDirector() == null ? other.getDtQualityDirector() == null : this.getDtQualityDirector().equals(other.getDtQualityDirector()))
            && (this.getDtParameter() == null ? other.getDtParameter() == null : this.getDtParameter().equals(other.getDtParameter()))
            && (this.getDtProductRange() == null ? other.getDtProductRange() == null : this.getDtProductRange().equals(other.getDtProductRange()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getDtLevelId() == null ? other.getDtLevelId() == null : this.getDtLevelId().equals(other.getDtLevelId()))
            && (this.getDtNatureId() == null ? other.getDtNatureId() == null : this.getDtNatureId().equals(other.getDtNatureId()))
            && (this.getDtQualificationsId() == null ? other.getDtQualificationsId() == null : this.getDtQualificationsId().equals(other.getDtQualificationsId()))
            && (this.getDtTypeId() == null ? other.getDtTypeId() == null : this.getDtTypeId().equals(other.getDtTypeId()))
            && (this.getDtArea() == null ? other.getDtArea() == null : this.getDtArea().equals(other.getDtArea()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDtName() == null) ? 0 : getDtName().hashCode());
        result = prime * result + ((getDtCode() == null) ? 0 : getDtCode().hashCode());
        result = prime * result + ((getDtType() == null) ? 0 : getDtType().hashCode());
        result = prime * result + ((getDtNature() == null) ? 0 : getDtNature().hashCode());
        result = prime * result + ((getDtQualifications() == null) ? 0 : getDtQualifications().hashCode());
        result = prime * result + ((getDtRelyOnUnit() == null) ? 0 : getDtRelyOnUnit().hashCode());
        result = prime * result + ((getDtLevel() == null) ? 0 : getDtLevel().hashCode());
        result = prime * result + ((getDtAreaId() == null) ? 0 : getDtAreaId().hashCode());
        result = prime * result + ((getDtAddress() == null) ? 0 : getDtAddress().hashCode());
        result = prime * result + ((getDtLegalMan() == null) ? 0 : getDtLegalMan().hashCode());
        result = prime * result + ((getDtLeader() == null) ? 0 : getDtLeader().hashCode());
        result = prime * result + ((getDtLeaderPhone() == null) ? 0 : getDtLeaderPhone().hashCode());
        result = prime * result + ((getDtContact() == null) ? 0 : getDtContact().hashCode());
        result = prime * result + ((getDtContactPhone() == null) ? 0 : getDtContactPhone().hashCode());
        result = prime * result + ((getDtContactQQ() == null) ? 0 : getDtContactQQ().hashCode());
        result = prime * result + ((getDtContactEmail() == null) ? 0 : getDtContactEmail().hashCode());
        result = prime * result + ((getDtPostcode() == null) ? 0 : getDtPostcode().hashCode());
        result = prime * result + ((getDtTechnicalDirector() == null) ? 0 : getDtTechnicalDirector().hashCode());
        result = prime * result + ((getDtQualityDirector() == null) ? 0 : getDtQualityDirector().hashCode());
        result = prime * result + ((getDtParameter() == null) ? 0 : getDtParameter().hashCode());
        result = prime * result + ((getDtProductRange() == null) ? 0 : getDtProductRange().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getDtLevelId() == null) ? 0 : getDtLevelId().hashCode());
        result = prime * result + ((getDtNatureId() == null) ? 0 : getDtNatureId().hashCode());
        result = prime * result + ((getDtQualificationsId() == null) ? 0 : getDtQualificationsId().hashCode());
        result = prime * result + ((getDtTypeId() == null) ? 0 : getDtTypeId().hashCode());
        result = prime * result + ((getDtArea() == null) ? 0 : getDtArea().hashCode());
        return result;
    }
}