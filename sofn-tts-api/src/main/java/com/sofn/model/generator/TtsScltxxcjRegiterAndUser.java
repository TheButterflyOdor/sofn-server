package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 子账号及其主体的信息
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjRegiterAndUser extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String entityScaleName; //主体组织形式字典名称
    private String entityTypeName; //主体类型字典名称
    private String entityPropertyName; //主体属性字典名称
    private String entityIndustryName; //主体行业字典名称
    private String unitName; //企业年产量计量单位
    private BigDecimal latitude; //纬度
    private String legalName; //法人姓名
    private String legalIdnumber; //法人身份证号码
    private String legalPhone; //法人电话
    private String legalImages; //法人相关照片
    private String faxNumber; //传真
    private String contactName; //联系人姓名
    private String contactPhone; //联系人电话
    private String contactEmail; //联系人邮箱
    private Date registerTime; //注册时间
    private String approveStatus; //审批状态
    private String approveOpinion; //审批意见
    private String approveUserIdcode; //审批人主体用户码
    private String approveName; //审批人姓名
    private Date approveTime; //审批时间
    private String userIdcode; //主体用户码
    private String entityIdcode; //主体身份码
    private String account; //账号
    private String realName; //真实姓名
    private String idcode; //个人身份证号
    private String entityScale; //主体组织形式
    private String entityType; //主体类型
    private String entityProperty; //主体属性
    private String entityIndustry; //主体所属行业
    private String enterpriseName; //企业名称
    private String cardType; //企业证件类型
    private String creditCode; //企业证件号码
    private String businessOperation; //企业营业期限
    private Date businessOperationStart; //企业营业期限起日
    private Date businessOperationEnd; //企业营业期限止日
    private String enterpriseIndustry; //企业所属行业
    private String annualOutput; //企业年产量
    private String unit; //企业年产量计量单位
    private String address; //企业地址
    private String documentImages; //证件照片
    private String area; //所属区域
    private BigDecimal longitude; //经度

    private String childId;//验证码

    private String longinAccount; //登录账号
    private String childUserIdcode; //user表中主体用户码
    private String isMain; //是否是主账号
    private String status; //账号审核状态
    private String name; //姓名

    private String phone; //联系方式
    private String email; //邮箱
    private String password; //密码
    private String idCard; //身份证号

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TtsScltxxcjRegiterAndUser)) return false;

        TtsScltxxcjRegiterAndUser that = (TtsScltxxcjRegiterAndUser) o;

        if (getEntityScaleName() != null ? !getEntityScaleName().equals(that.getEntityScaleName()) : that.getEntityScaleName() != null)
            return false;
        if (getEntityTypeName() != null ? !getEntityTypeName().equals(that.getEntityTypeName()) : that.getEntityTypeName() != null)
            return false;
        if (getEntityPropertyName() != null ? !getEntityPropertyName().equals(that.getEntityPropertyName()) : that.getEntityPropertyName() != null)
            return false;
        if (getEntityIndustryName() != null ? !getEntityIndustryName().equals(that.getEntityIndustryName()) : that.getEntityIndustryName() != null)
            return false;
        if (getUnitName() != null ? !getUnitName().equals(that.getUnitName()) : that.getUnitName() != null)
            return false;
        if (getLatitude() != null ? !getLatitude().equals(that.getLatitude()) : that.getLatitude() != null)
            return false;
        if (getLegalName() != null ? !getLegalName().equals(that.getLegalName()) : that.getLegalName() != null)
            return false;
        if (getLegalIdnumber() != null ? !getLegalIdnumber().equals(that.getLegalIdnumber()) : that.getLegalIdnumber() != null)
            return false;
        if (getLegalPhone() != null ? !getLegalPhone().equals(that.getLegalPhone()) : that.getLegalPhone() != null)
            return false;
        if (getLegalImages() != null ? !getLegalImages().equals(that.getLegalImages()) : that.getLegalImages() != null)
            return false;
        if (getFaxNumber() != null ? !getFaxNumber().equals(that.getFaxNumber()) : that.getFaxNumber() != null)
            return false;
        if (getContactName() != null ? !getContactName().equals(that.getContactName()) : that.getContactName() != null)
            return false;
        if (getContactPhone() != null ? !getContactPhone().equals(that.getContactPhone()) : that.getContactPhone() != null)
            return false;
        if (getContactEmail() != null ? !getContactEmail().equals(that.getContactEmail()) : that.getContactEmail() != null)
            return false;
        if (getRegisterTime() != null ? !getRegisterTime().equals(that.getRegisterTime()) : that.getRegisterTime() != null)
            return false;
        if (getApproveStatus() != null ? !getApproveStatus().equals(that.getApproveStatus()) : that.getApproveStatus() != null)
            return false;
        if (getApproveOpinion() != null ? !getApproveOpinion().equals(that.getApproveOpinion()) : that.getApproveOpinion() != null)
            return false;
        if (getApproveUserIdcode() != null ? !getApproveUserIdcode().equals(that.getApproveUserIdcode()) : that.getApproveUserIdcode() != null)
            return false;
        if (getApproveName() != null ? !getApproveName().equals(that.getApproveName()) : that.getApproveName() != null)
            return false;
        if (getApproveTime() != null ? !getApproveTime().equals(that.getApproveTime()) : that.getApproveTime() != null)
            return false;
        if (getUserIdcode() != null ? !getUserIdcode().equals(that.getUserIdcode()) : that.getUserIdcode() != null)
            return false;
        if (getEntityIdcode() != null ? !getEntityIdcode().equals(that.getEntityIdcode()) : that.getEntityIdcode() != null)
            return false;
        if (getAccount() != null ? !getAccount().equals(that.getAccount()) : that.getAccount() != null) return false;
        if (getRealName() != null ? !getRealName().equals(that.getRealName()) : that.getRealName() != null)
            return false;
        if (getIdcode() != null ? !getIdcode().equals(that.getIdcode()) : that.getIdcode() != null) return false;
        if (getEntityScale() != null ? !getEntityScale().equals(that.getEntityScale()) : that.getEntityScale() != null)
            return false;
        if (getEntityType() != null ? !getEntityType().equals(that.getEntityType()) : that.getEntityType() != null)
            return false;
        if (getEntityProperty() != null ? !getEntityProperty().equals(that.getEntityProperty()) : that.getEntityProperty() != null)
            return false;
        if (getEntityIndustry() != null ? !getEntityIndustry().equals(that.getEntityIndustry()) : that.getEntityIndustry() != null)
            return false;
        if (getEnterpriseName() != null ? !getEnterpriseName().equals(that.getEnterpriseName()) : that.getEnterpriseName() != null)
            return false;
        if (getCardType() != null ? !getCardType().equals(that.getCardType()) : that.getCardType() != null)
            return false;
        if (getCreditCode() != null ? !getCreditCode().equals(that.getCreditCode()) : that.getCreditCode() != null)
            return false;
        if (getBusinessOperation() != null ? !getBusinessOperation().equals(that.getBusinessOperation()) : that.getBusinessOperation() != null)
            return false;
        if (getBusinessOperationStart() != null ? !getBusinessOperationStart().equals(that.getBusinessOperationStart()) : that.getBusinessOperationStart() != null)
            return false;
        if (getBusinessOperationEnd() != null ? !getBusinessOperationEnd().equals(that.getBusinessOperationEnd()) : that.getBusinessOperationEnd() != null)
            return false;
        if (getEnterpriseIndustry() != null ? !getEnterpriseIndustry().equals(that.getEnterpriseIndustry()) : that.getEnterpriseIndustry() != null)
            return false;
        if (getAnnualOutput() != null ? !getAnnualOutput().equals(that.getAnnualOutput()) : that.getAnnualOutput() != null)
            return false;
        if (getUnit() != null ? !getUnit().equals(that.getUnit()) : that.getUnit() != null) return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        if (getDocumentImages() != null ? !getDocumentImages().equals(that.getDocumentImages()) : that.getDocumentImages() != null)
            return false;
        if (getArea() != null ? !getArea().equals(that.getArea()) : that.getArea() != null) return false;
        if (getLongitude() != null ? !getLongitude().equals(that.getLongitude()) : that.getLongitude() != null)
            return false;
        if (getChildId() != null ? !getChildId().equals(that.getChildId()) : that.getChildId() != null) return false;
        if (getLonginAccount() != null ? !getLonginAccount().equals(that.getLonginAccount()) : that.getLonginAccount() != null)
            return false;
        if (getChildUserIdcode() != null ? !getChildUserIdcode().equals(that.getChildUserIdcode()) : that.getChildUserIdcode() != null)
            return false;
        if (getIsMain() != null ? !getIsMain().equals(that.getIsMain()) : that.getIsMain() != null) return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        return getIdCard() != null ? getIdCard().equals(that.getIdCard()) : that.getIdCard() == null;

    }

    @Override
    public int hashCode() {
        int result = getEntityScaleName() != null ? getEntityScaleName().hashCode() : 0;
        result = 31 * result + (getEntityTypeName() != null ? getEntityTypeName().hashCode() : 0);
        result = 31 * result + (getEntityPropertyName() != null ? getEntityPropertyName().hashCode() : 0);
        result = 31 * result + (getEntityIndustryName() != null ? getEntityIndustryName().hashCode() : 0);
        result = 31 * result + (getUnitName() != null ? getUnitName().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getLegalName() != null ? getLegalName().hashCode() : 0);
        result = 31 * result + (getLegalIdnumber() != null ? getLegalIdnumber().hashCode() : 0);
        result = 31 * result + (getLegalPhone() != null ? getLegalPhone().hashCode() : 0);
        result = 31 * result + (getLegalImages() != null ? getLegalImages().hashCode() : 0);
        result = 31 * result + (getFaxNumber() != null ? getFaxNumber().hashCode() : 0);
        result = 31 * result + (getContactName() != null ? getContactName().hashCode() : 0);
        result = 31 * result + (getContactPhone() != null ? getContactPhone().hashCode() : 0);
        result = 31 * result + (getContactEmail() != null ? getContactEmail().hashCode() : 0);
        result = 31 * result + (getRegisterTime() != null ? getRegisterTime().hashCode() : 0);
        result = 31 * result + (getApproveStatus() != null ? getApproveStatus().hashCode() : 0);
        result = 31 * result + (getApproveOpinion() != null ? getApproveOpinion().hashCode() : 0);
        result = 31 * result + (getApproveUserIdcode() != null ? getApproveUserIdcode().hashCode() : 0);
        result = 31 * result + (getApproveName() != null ? getApproveName().hashCode() : 0);
        result = 31 * result + (getApproveTime() != null ? getApproveTime().hashCode() : 0);
        result = 31 * result + (getUserIdcode() != null ? getUserIdcode().hashCode() : 0);
        result = 31 * result + (getEntityIdcode() != null ? getEntityIdcode().hashCode() : 0);
        result = 31 * result + (getAccount() != null ? getAccount().hashCode() : 0);
        result = 31 * result + (getRealName() != null ? getRealName().hashCode() : 0);
        result = 31 * result + (getIdcode() != null ? getIdcode().hashCode() : 0);
        result = 31 * result + (getEntityScale() != null ? getEntityScale().hashCode() : 0);
        result = 31 * result + (getEntityType() != null ? getEntityType().hashCode() : 0);
        result = 31 * result + (getEntityProperty() != null ? getEntityProperty().hashCode() : 0);
        result = 31 * result + (getEntityIndustry() != null ? getEntityIndustry().hashCode() : 0);
        result = 31 * result + (getEnterpriseName() != null ? getEnterpriseName().hashCode() : 0);
        result = 31 * result + (getCardType() != null ? getCardType().hashCode() : 0);
        result = 31 * result + (getCreditCode() != null ? getCreditCode().hashCode() : 0);
        result = 31 * result + (getBusinessOperation() != null ? getBusinessOperation().hashCode() : 0);
        result = 31 * result + (getBusinessOperationStart() != null ? getBusinessOperationStart().hashCode() : 0);
        result = 31 * result + (getBusinessOperationEnd() != null ? getBusinessOperationEnd().hashCode() : 0);
        result = 31 * result + (getEnterpriseIndustry() != null ? getEnterpriseIndustry().hashCode() : 0);
        result = 31 * result + (getAnnualOutput() != null ? getAnnualOutput().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getDocumentImages() != null ? getDocumentImages().hashCode() : 0);
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getChildId() != null ? getChildId().hashCode() : 0);
        result = 31 * result + (getLonginAccount() != null ? getLonginAccount().hashCode() : 0);
        result = 31 * result + (getChildUserIdcode() != null ? getChildUserIdcode().hashCode() : 0);
        result = 31 * result + (getIsMain() != null ? getIsMain().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getIdCard() != null ? getIdCard().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TtsScltxxcjRegiterAndUser{" +
                "entityScaleName='" + entityScaleName + '\'' +
                ", entityTypeName='" + entityTypeName + '\'' +
                ", entityPropertyName='" + entityPropertyName + '\'' +
                ", entityIndustryName='" + entityIndustryName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", latitude=" + latitude +
                ", legalName='" + legalName + '\'' +
                ", legalIdnumber='" + legalIdnumber + '\'' +
                ", legalPhone='" + legalPhone + '\'' +
                ", legalImages='" + legalImages + '\'' +
                ", faxNumber='" + faxNumber + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", registerTime=" + registerTime +
                ", approveStatus='" + approveStatus + '\'' +
                ", approveOpinion='" + approveOpinion + '\'' +
                ", approveUserIdcode='" + approveUserIdcode + '\'' +
                ", approveName='" + approveName + '\'' +
                ", approveTime=" + approveTime +
                ", userIdcode='" + userIdcode + '\'' +
                ", entityIdcode='" + entityIdcode + '\'' +
                ", account='" + account + '\'' +
                ", realName='" + realName + '\'' +
                ", idcode='" + idcode + '\'' +
                ", entityScale='" + entityScale + '\'' +
                ", entityType='" + entityType + '\'' +
                ", entityProperty='" + entityProperty + '\'' +
                ", entityIndustry='" + entityIndustry + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", creditCode='" + creditCode + '\'' +
                ", businessOperation='" + businessOperation + '\'' +
                ", businessOperationStart=" + businessOperationStart +
                ", businessOperationEnd=" + businessOperationEnd +
                ", enterpriseIndustry='" + enterpriseIndustry + '\'' +
                ", annualOutput='" + annualOutput + '\'' +
                ", unit='" + unit + '\'' +
                ", address='" + address + '\'' +
                ", documentImages='" + documentImages + '\'' +
                ", area='" + area + '\'' +
                ", longitude=" + longitude +
                ", childId='" + childId + '\'' +
                ", longinAccount='" + longinAccount + '\'' +
                ", childUserIdcode='" + childUserIdcode + '\'' +
                ", isMain='" + isMain + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
    public String getEntityScaleName() {
        return entityScaleName;
    }

    public void setEntityScaleName(String entityScaleName) {
        this.entityScaleName = entityScaleName;
    }

    public String getEntityTypeName() {
        return entityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName;
    }

    public String getEntityPropertyName() {
        return entityPropertyName;
    }

    public void setEntityPropertyName(String entityPropertyName) {
        this.entityPropertyName = entityPropertyName;
    }

    public String getEntityIndustryName() {
        return entityIndustryName;
    }

    public void setEntityIndustryName(String entityIndustryName) {
        this.entityIndustryName = entityIndustryName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalIdnumber() {
        return legalIdnumber;
    }

    public void setLegalIdnumber(String legalIdnumber) {
        this.legalIdnumber = legalIdnumber;
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone;
    }

    public String getLegalImages() {
        return legalImages;
    }

    public void setLegalImages(String legalImages) {
        this.legalImages = legalImages;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveOpinion() {
        return approveOpinion;
    }

    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    public String getApproveUserIdcode() {
        return approveUserIdcode;
    }

    public void setApproveUserIdcode(String approveUserIdcode) {
        this.approveUserIdcode = approveUserIdcode;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getUserIdcode() {
        return userIdcode;
    }

    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }

    public String getEntityIdcode() {
        return entityIdcode;
    }

    public void setEntityIdcode(String entityIdcode) {
        this.entityIdcode = entityIdcode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode;
    }

    public String getEntityScale() {
        return entityScale;
    }

    public void setEntityScale(String entityScale) {
        this.entityScale = entityScale;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityProperty() {
        return entityProperty;
    }

    public void setEntityProperty(String entityProperty) {
        this.entityProperty = entityProperty;
    }

    public String getEntityIndustry() {
        return entityIndustry;
    }

    public void setEntityIndustry(String entityIndustry) {
        this.entityIndustry = entityIndustry;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getBusinessOperation() {
        return businessOperation;
    }

    public void setBusinessOperation(String businessOperation) {
        this.businessOperation = businessOperation;
    }

    public Date getBusinessOperationStart() {
        return businessOperationStart;
    }

    public void setBusinessOperationStart(Date businessOperationStart) {
        this.businessOperationStart = businessOperationStart;
    }

    public Date getBusinessOperationEnd() {
        return businessOperationEnd;
    }

    public void setBusinessOperationEnd(Date businessOperationEnd) {
        this.businessOperationEnd = businessOperationEnd;
    }

    public String getEnterpriseIndustry() {
        return enterpriseIndustry;
    }

    public void setEnterpriseIndustry(String enterpriseIndustry) {
        this.enterpriseIndustry = enterpriseIndustry;
    }

    public String getAnnualOutput() {
        return annualOutput;
    }

    public void setAnnualOutput(String annualOutput) {
        this.annualOutput = annualOutput;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocumentImages() {
        return documentImages;
    }

    public void setDocumentImages(String documentImages) {
        this.documentImages = documentImages;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }


    public String getLonginAccount() {
        return longinAccount;
    }

    public void setLonginAccount(String longinAccount) {
        this.longinAccount = longinAccount;
    }

    public String getChildUserIdcode() {
        return childUserIdcode;
    }

    public void setChildUserIdcode(String childUserIdcode) {
        this.childUserIdcode = childUserIdcode;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

}