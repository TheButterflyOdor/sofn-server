package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsSubjSupervise extends BaseModel {
    private String svName;

    private String svCode;

    private String svType;

    private String svLevel;

    private String svLevelId;

    private String svAreaId;

    private String svAddress;

    private String svLeader;

    private String svLeaderPhone;

    private String svContact;

    private String svContactPhone;

    private String svContactQQ;

    private String svContactEmail;

    private String svPostcode;

    private String status;

    private String svTypeId;

    private String svArea;

    private String industryId;

    private String industryName;

    private String industryValue;

    public String getSvName() {
        return svName;
    }

    public void setSvName(String svName) {
        this.svName = svName == null ? null : svName.trim();
    }

    public String getSvCode() {
        return svCode;
    }

    public void setSvCode(String svCode) {
        this.svCode = svCode == null ? null : svCode.trim();
    }

    public String getSvType() {
        return svType;
    }

    public void setSvType(String svType) {
        this.svType = svType == null ? null : svType.trim();
    }

    public String getSvLevel() {
        return svLevel;
    }

    public void setSvLevel(String svLevel) {
        this.svLevel = svLevel == null ? null : svLevel.trim();
    }

    public String getSvLevelId() {
        return svLevelId;
    }

    public void setSvLevelId(String svLevelId) {
        this.svLevelId = svLevelId == null ? null : svLevelId.trim();
    }

    public String getSvAreaId() {
        return svAreaId;
    }

    public void setSvAreaId(String svAreaId) {
        this.svAreaId = svAreaId == null ? null : svAreaId.trim();
    }

    public String getSvAddress() {
        return svAddress;
    }

    public void setSvAddress(String svAddress) {
        this.svAddress = svAddress == null ? null : svAddress.trim();
    }

    public String getSvLeader() {
        return svLeader;
    }

    public void setSvLeader(String svLeader) {
        this.svLeader = svLeader == null ? null : svLeader.trim();
    }

    public String getSvLeaderPhone() {
        return svLeaderPhone;
    }

    public void setSvLeaderPhone(String svLeaderPhone) {
        this.svLeaderPhone = svLeaderPhone == null ? null : svLeaderPhone.trim();
    }

    public String getSvContact() {
        return svContact;
    }

    public void setSvContact(String svContact) {
        this.svContact = svContact == null ? null : svContact.trim();
    }

    public String getSvContactPhone() {
        return svContactPhone;
    }

    public void setSvContactPhone(String svContactPhone) {
        this.svContactPhone = svContactPhone == null ? null : svContactPhone.trim();
    }

    public String getSvContactQQ() {
        return svContactQQ;
    }

    public void setSvContactQQ(String svContactQQ) {
        this.svContactQQ = svContactQQ == null ? null : svContactQQ.trim();
    }

    public String getSvContactEmail() {
        return svContactEmail;
    }

    public void setSvContactEmail(String svContactEmail) {
        this.svContactEmail = svContactEmail == null ? null : svContactEmail.trim();
    }

    public String getSvPostcode() {
        return svPostcode;
    }

    public void setSvPostcode(String svPostcode) {
        this.svPostcode = svPostcode == null ? null : svPostcode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSvTypeId() {
        return svTypeId;
    }

    public void setSvTypeId(String svTypeId) {
        this.svTypeId = svTypeId == null ? null : svTypeId.trim();
    }

    public String getSvArea() {
        return svArea;
    }

    public void setSvArea(String svArea) {
        this.svArea = svArea == null ? null : svArea.trim();
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId == null ? null : industryId.trim();
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName == null ? null : industryName.trim();
    }

    public String getIndustryValue() {
        return industryValue;
    }

    public void setIndustryValue(String industryValue) {
        this.industryValue = industryValue == null ? null : industryValue.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", svName=").append(svName);
        sb.append(", svCode=").append(svCode);
        sb.append(", svType=").append(svType);
        sb.append(", svLevel=").append(svLevel);
        sb.append(", svLevelId=").append(svLevelId);
        sb.append(", svAreaId=").append(svAreaId);
        sb.append(", svAddress=").append(svAddress);
        sb.append(", svLeader=").append(svLeader);
        sb.append(", svLeaderPhone=").append(svLeaderPhone);
        sb.append(", svContact=").append(svContact);
        sb.append(", svContactPhone=").append(svContactPhone);
        sb.append(", svContactQQ=").append(svContactQQ);
        sb.append(", svContactEmail=").append(svContactEmail);
        sb.append(", svPostcode=").append(svPostcode);
        sb.append(", status=").append(status);
        sb.append(", svTypeId=").append(svTypeId);
        sb.append(", svArea=").append(svArea);
        sb.append(", industryId=").append(industryId);
        sb.append(", industryName=").append(industryName);
        sb.append(", industryValue=").append(industryValue);
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
        AsmsSubjSupervise other = (AsmsSubjSupervise) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSvName() == null ? other.getSvName() == null : this.getSvName().equals(other.getSvName()))
            && (this.getSvCode() == null ? other.getSvCode() == null : this.getSvCode().equals(other.getSvCode()))
            && (this.getSvType() == null ? other.getSvType() == null : this.getSvType().equals(other.getSvType()))
            && (this.getSvLevel() == null ? other.getSvLevel() == null : this.getSvLevel().equals(other.getSvLevel()))
            && (this.getSvLevelId() == null ? other.getSvLevelId() == null : this.getSvLevelId().equals(other.getSvLevelId()))
            && (this.getSvAreaId() == null ? other.getSvAreaId() == null : this.getSvAreaId().equals(other.getSvAreaId()))
            && (this.getSvAddress() == null ? other.getSvAddress() == null : this.getSvAddress().equals(other.getSvAddress()))
            && (this.getSvLeader() == null ? other.getSvLeader() == null : this.getSvLeader().equals(other.getSvLeader()))
            && (this.getSvLeaderPhone() == null ? other.getSvLeaderPhone() == null : this.getSvLeaderPhone().equals(other.getSvLeaderPhone()))
            && (this.getSvContact() == null ? other.getSvContact() == null : this.getSvContact().equals(other.getSvContact()))
            && (this.getSvContactPhone() == null ? other.getSvContactPhone() == null : this.getSvContactPhone().equals(other.getSvContactPhone()))
            && (this.getSvContactQQ() == null ? other.getSvContactQQ() == null : this.getSvContactQQ().equals(other.getSvContactQQ()))
            && (this.getSvContactEmail() == null ? other.getSvContactEmail() == null : this.getSvContactEmail().equals(other.getSvContactEmail()))
            && (this.getSvPostcode() == null ? other.getSvPostcode() == null : this.getSvPostcode().equals(other.getSvPostcode()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getSvTypeId() == null ? other.getSvTypeId() == null : this.getSvTypeId().equals(other.getSvTypeId()))
            && (this.getSvArea() == null ? other.getSvArea() == null : this.getSvArea().equals(other.getSvArea()))
            && (this.getIndustryId() == null ? other.getIndustryId() == null : this.getIndustryId().equals(other.getIndustryId()))
            && (this.getIndustryName() == null ? other.getIndustryName() == null : this.getIndustryName().equals(other.getIndustryName()))
            && (this.getIndustryValue() == null ? other.getIndustryValue() == null : this.getIndustryValue().equals(other.getIndustryValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSvName() == null) ? 0 : getSvName().hashCode());
        result = prime * result + ((getSvCode() == null) ? 0 : getSvCode().hashCode());
        result = prime * result + ((getSvType() == null) ? 0 : getSvType().hashCode());
        result = prime * result + ((getSvLevel() == null) ? 0 : getSvLevel().hashCode());
        result = prime * result + ((getSvLevelId() == null) ? 0 : getSvLevelId().hashCode());
        result = prime * result + ((getSvAreaId() == null) ? 0 : getSvAreaId().hashCode());
        result = prime * result + ((getSvAddress() == null) ? 0 : getSvAddress().hashCode());
        result = prime * result + ((getSvLeader() == null) ? 0 : getSvLeader().hashCode());
        result = prime * result + ((getSvLeaderPhone() == null) ? 0 : getSvLeaderPhone().hashCode());
        result = prime * result + ((getSvContact() == null) ? 0 : getSvContact().hashCode());
        result = prime * result + ((getSvContactPhone() == null) ? 0 : getSvContactPhone().hashCode());
        result = prime * result + ((getSvContactQQ() == null) ? 0 : getSvContactQQ().hashCode());
        result = prime * result + ((getSvContactEmail() == null) ? 0 : getSvContactEmail().hashCode());
        result = prime * result + ((getSvPostcode() == null) ? 0 : getSvPostcode().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getSvTypeId() == null) ? 0 : getSvTypeId().hashCode());
        result = prime * result + ((getSvArea() == null) ? 0 : getSvArea().hashCode());
        result = prime * result + ((getIndustryId() == null) ? 0 : getIndustryId().hashCode());
        result = prime * result + ((getIndustryName() == null) ? 0 : getIndustryName().hashCode());
        result = prime * result + ((getIndustryValue() == null) ? 0 : getIndustryValue().hashCode());
        return result;
    }
}