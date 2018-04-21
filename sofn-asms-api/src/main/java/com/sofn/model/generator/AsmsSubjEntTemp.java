package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AsmsSubjEntTemp extends BaseModel implements Serializable {
    private String entityName;

    private String entityScale;

    private String entityScaleId;

    private String entityType;

    private String entityTypeId;

    private String entityIndustry;

    private String entityIndustryId;

    private String credType;

    private String credTime;

    private String orgCode;//企业证件号

    private String areaId;

    private String contactName;

    private String contactPhone;

    private String contactEmail;

    private String createSuperviseId;

    private String enable;

    private String address;

    private String identityCode;

    private String businessLicenceimg;//企业证件照

    private String organizationCertificateimg;//组织机构在照

    private String positiveIdcardeimg;//身份证正面照

    private String negativeIdcardimg;//身份证反面照

    private String handIdcardimg;//手持身份证照

    private String attachmentAddress;//附件地址

    private String attachmentName;//附件名称

    private String organizationCode;//组织机构代码

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

    public String getBusinessLicenceimg() {
        return businessLicenceimg;
    }

    public void setBusinessLicenceimg(String businessLicenceimg) {
        this.businessLicenceimg = businessLicenceimg == null ? null : businessLicenceimg.trim();
    }

    public String getOrganizationCertificateimg() {
        return organizationCertificateimg;
    }

    public void setOrganizationCertificateimg(String organizationCertificateimg) {
        this.organizationCertificateimg = organizationCertificateimg == null ? null : organizationCertificateimg.trim();
    }

    public String getPositiveIdcardeimg() {
        return positiveIdcardeimg;
    }

    public void setPositiveIdcardeimg(String positiveIdcardeimg) {
        this.positiveIdcardeimg = positiveIdcardeimg == null ? null : positiveIdcardeimg.trim();
    }

    public String getNegativeIdcardimg() {
        return negativeIdcardimg;
    }

    public void setNegativeIdcardimg(String negativeIdcardimg) {
        this.negativeIdcardimg = negativeIdcardimg == null ? null : negativeIdcardimg.trim();
    }

    public String getHandIdcardimg() {
        return handIdcardimg;
    }

    public void setHandIdcardimg(String handIdcardimg) {
        this.handIdcardimg = handIdcardimg == null ? null : handIdcardimg.trim();
    }

    public String getAttachmentAddress() {
        return attachmentAddress;
    }

    public void setAttachmentAddress(String attachmentAddress) {
        this.attachmentAddress = attachmentAddress == null ? null : attachmentAddress.trim();
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName == null ? null : attachmentName.trim();
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    @Override
    public String toString() {
        return "AsmsSubjEntTemp{" +
                "entityName='" + entityName + '\'' +
                ", entityScale='" + entityScale + '\'' +
                ", entityScaleId='" + entityScaleId + '\'' +
                ", entityType='" + entityType + '\'' +
                ", entityTypeId='" + entityTypeId + '\'' +
                ", entityIndustry='" + entityIndustry + '\'' +
                ", entityIndustryId='" + entityIndustryId + '\'' +
                ", credType='" + credType + '\'' +
                ", credTime='" + credTime + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", areaId='" + areaId + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", createSuperviseId='" + createSuperviseId + '\'' +
                ", enable='" + enable + '\'' +
                ", address='" + address + '\'' +
                ", identityCode='" + identityCode + '\'' +
                ", businessLicenceimg='" + businessLicenceimg + '\'' +
                ", organizationCertificateimg='" + organizationCertificateimg + '\'' +
                ", positiveIdcardeimg='" + positiveIdcardeimg + '\'' +
                ", negativeIdcardimg='" + negativeIdcardimg + '\'' +
                ", handIdcardimg='" + handIdcardimg + '\'' +
                ", attachmentAddress='" + attachmentAddress + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsmsSubjEntTemp that = (AsmsSubjEntTemp) o;

        if (entityName != null ? !entityName.equals(that.entityName) : that.entityName != null) return false;
        if (entityScale != null ? !entityScale.equals(that.entityScale) : that.entityScale != null) return false;
        if (entityScaleId != null ? !entityScaleId.equals(that.entityScaleId) : that.entityScaleId != null)
            return false;
        if (entityType != null ? !entityType.equals(that.entityType) : that.entityType != null) return false;
        if (entityTypeId != null ? !entityTypeId.equals(that.entityTypeId) : that.entityTypeId != null) return false;
        if (entityIndustry != null ? !entityIndustry.equals(that.entityIndustry) : that.entityIndustry != null)
            return false;
        if (entityIndustryId != null ? !entityIndustryId.equals(that.entityIndustryId) : that.entityIndustryId != null)
            return false;
        if (credType != null ? !credType.equals(that.credType) : that.credType != null) return false;
        if (credTime != null ? !credTime.equals(that.credTime) : that.credTime != null) return false;
        if (orgCode != null ? !orgCode.equals(that.orgCode) : that.orgCode != null) return false;
        if (areaId != null ? !areaId.equals(that.areaId) : that.areaId != null) return false;
        if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) return false;
        if (contactPhone != null ? !contactPhone.equals(that.contactPhone) : that.contactPhone != null) return false;
        if (contactEmail != null ? !contactEmail.equals(that.contactEmail) : that.contactEmail != null) return false;
        if (createSuperviseId != null ? !createSuperviseId.equals(that.createSuperviseId) : that.createSuperviseId != null)
            return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (identityCode != null ? !identityCode.equals(that.identityCode) : that.identityCode != null) return false;
        if (businessLicenceimg != null ? !businessLicenceimg.equals(that.businessLicenceimg) : that.businessLicenceimg != null)
            return false;
        if (organizationCertificateimg != null ? !organizationCertificateimg.equals(that.organizationCertificateimg) : that.organizationCertificateimg != null)
            return false;
        if (positiveIdcardeimg != null ? !positiveIdcardeimg.equals(that.positiveIdcardeimg) : that.positiveIdcardeimg != null)
            return false;
        if (negativeIdcardimg != null ? !negativeIdcardimg.equals(that.negativeIdcardimg) : that.negativeIdcardimg != null)
            return false;
        if (handIdcardimg != null ? !handIdcardimg.equals(that.handIdcardimg) : that.handIdcardimg != null)
            return false;
        if (attachmentAddress != null ? !attachmentAddress.equals(that.attachmentAddress) : that.attachmentAddress != null)
            return false;
        if (attachmentName != null ? !attachmentName.equals(that.attachmentName) : that.attachmentName != null)
            return false;
        return organizationCode != null ? organizationCode.equals(that.organizationCode) : that.organizationCode == null;

    }

    @Override
    public int hashCode() {
        int result = entityName != null ? entityName.hashCode() : 0;
        result = 31 * result + (entityScale != null ? entityScale.hashCode() : 0);
        result = 31 * result + (entityScaleId != null ? entityScaleId.hashCode() : 0);
        result = 31 * result + (entityType != null ? entityType.hashCode() : 0);
        result = 31 * result + (entityTypeId != null ? entityTypeId.hashCode() : 0);
        result = 31 * result + (entityIndustry != null ? entityIndustry.hashCode() : 0);
        result = 31 * result + (entityIndustryId != null ? entityIndustryId.hashCode() : 0);
        result = 31 * result + (credType != null ? credType.hashCode() : 0);
        result = 31 * result + (credTime != null ? credTime.hashCode() : 0);
        result = 31 * result + (orgCode != null ? orgCode.hashCode() : 0);
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
        result = 31 * result + (contactPhone != null ? contactPhone.hashCode() : 0);
        result = 31 * result + (contactEmail != null ? contactEmail.hashCode() : 0);
        result = 31 * result + (createSuperviseId != null ? createSuperviseId.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (identityCode != null ? identityCode.hashCode() : 0);
        result = 31 * result + (businessLicenceimg != null ? businessLicenceimg.hashCode() : 0);
        result = 31 * result + (organizationCertificateimg != null ? organizationCertificateimg.hashCode() : 0);
        result = 31 * result + (positiveIdcardeimg != null ? positiveIdcardeimg.hashCode() : 0);
        result = 31 * result + (negativeIdcardimg != null ? negativeIdcardimg.hashCode() : 0);
        result = 31 * result + (handIdcardimg != null ? handIdcardimg.hashCode() : 0);
        result = 31 * result + (attachmentAddress != null ? attachmentAddress.hashCode() : 0);
        result = 31 * result + (attachmentName != null ? attachmentName.hashCode() : 0);
        result = 31 * result + (organizationCode != null ? organizationCode.hashCode() : 0);
        return result;
    }
}