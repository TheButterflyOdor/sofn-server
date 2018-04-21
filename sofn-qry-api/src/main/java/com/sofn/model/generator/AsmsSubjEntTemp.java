package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsSubjEntTemp extends BaseModel {
    private String entityName;

    private String entityScale;

    private String entityScaleId;

    private String entityType;

    private String entityTypeId;

    private String entityIndustry;

    private String entityIndustryId;

    private String credType;

    private String credTime;

    private String orgCode;

    private String areaId;

    private String contactName;

    private String contactPhone;

    private String contactEmail;

    private String createSuperviseId;

    private String enable;

    private String address;

    private String identityCode;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName == null ? null : entityName.trim();
    }

    public String getEntityScale() {
        return entityScale;
    }

    public void setEntityScale(String entityScale) {
        this.entityScale = entityScale == null ? null : entityScale.trim();
    }

    public String getEntityScaleId() {
        return entityScaleId;
    }

    public void setEntityScaleId(String entityScaleId) {
        this.entityScaleId = entityScaleId == null ? null : entityScaleId.trim();
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType == null ? null : entityType.trim();
    }

    public String getEntityTypeId() {
        return entityTypeId;
    }

    public void setEntityTypeId(String entityTypeId) {
        this.entityTypeId = entityTypeId == null ? null : entityTypeId.trim();
    }

    public String getEntityIndustry() {
        return entityIndustry;
    }

    public void setEntityIndustry(String entityIndustry) {
        this.entityIndustry = entityIndustry == null ? null : entityIndustry.trim();
    }

    public String getEntityIndustryId() {
        return entityIndustryId;
    }

    public void setEntityIndustryId(String entityIndustryId) {
        this.entityIndustryId = entityIndustryId == null ? null : entityIndustryId.trim();
    }

    public String getCredType() {
        return credType;
    }

    public void setCredType(String credType) {
        this.credType = credType == null ? null : credType.trim();
    }

    public String getCredTime() {
        return credTime;
    }

    public void setCredTime(String credTime) {
        this.credTime = credTime == null ? null : credTime.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getCreateSuperviseId() {
        return createSuperviseId;
    }

    public void setCreateSuperviseId(String createSuperviseId) {
        this.createSuperviseId = createSuperviseId == null ? null : createSuperviseId.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode == null ? null : identityCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", entityName=").append(entityName);
        sb.append(", entityScale=").append(entityScale);
        sb.append(", entityScaleId=").append(entityScaleId);
        sb.append(", entityType=").append(entityType);
        sb.append(", entityTypeId=").append(entityTypeId);
        sb.append(", entityIndustry=").append(entityIndustry);
        sb.append(", entityIndustryId=").append(entityIndustryId);
        sb.append(", credType=").append(credType);
        sb.append(", credTime=").append(credTime);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", areaId=").append(areaId);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", createSuperviseId=").append(createSuperviseId);
        sb.append(", enable=").append(enable);
        sb.append(", address=").append(address);
        sb.append(", identityCode=").append(identityCode);
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
        AsmsSubjEntTemp other = (AsmsSubjEntTemp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEntityName() == null ? other.getEntityName() == null : this.getEntityName().equals(other.getEntityName()))
            && (this.getEntityScale() == null ? other.getEntityScale() == null : this.getEntityScale().equals(other.getEntityScale()))
            && (this.getEntityScaleId() == null ? other.getEntityScaleId() == null : this.getEntityScaleId().equals(other.getEntityScaleId()))
            && (this.getEntityType() == null ? other.getEntityType() == null : this.getEntityType().equals(other.getEntityType()))
            && (this.getEntityTypeId() == null ? other.getEntityTypeId() == null : this.getEntityTypeId().equals(other.getEntityTypeId()))
            && (this.getEntityIndustry() == null ? other.getEntityIndustry() == null : this.getEntityIndustry().equals(other.getEntityIndustry()))
            && (this.getEntityIndustryId() == null ? other.getEntityIndustryId() == null : this.getEntityIndustryId().equals(other.getEntityIndustryId()))
            && (this.getCredType() == null ? other.getCredType() == null : this.getCredType().equals(other.getCredType()))
            && (this.getCredTime() == null ? other.getCredTime() == null : this.getCredTime().equals(other.getCredTime()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getContactEmail() == null ? other.getContactEmail() == null : this.getContactEmail().equals(other.getContactEmail()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateSuperviseId() == null ? other.getCreateSuperviseId() == null : this.getCreateSuperviseId().equals(other.getCreateSuperviseId()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getIdentityCode() == null ? other.getIdentityCode() == null : this.getIdentityCode().equals(other.getIdentityCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEntityName() == null) ? 0 : getEntityName().hashCode());
        result = prime * result + ((getEntityScale() == null) ? 0 : getEntityScale().hashCode());
        result = prime * result + ((getEntityScaleId() == null) ? 0 : getEntityScaleId().hashCode());
        result = prime * result + ((getEntityType() == null) ? 0 : getEntityType().hashCode());
        result = prime * result + ((getEntityTypeId() == null) ? 0 : getEntityTypeId().hashCode());
        result = prime * result + ((getEntityIndustry() == null) ? 0 : getEntityIndustry().hashCode());
        result = prime * result + ((getEntityIndustryId() == null) ? 0 : getEntityIndustryId().hashCode());
        result = prime * result + ((getCredType() == null) ? 0 : getCredType().hashCode());
        result = prime * result + ((getCredTime() == null) ? 0 : getCredTime().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getContactEmail() == null) ? 0 : getContactEmail().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateSuperviseId() == null) ? 0 : getCreateSuperviseId().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getIdentityCode() == null) ? 0 : getIdentityCode().hashCode());
        return result;
    }
}