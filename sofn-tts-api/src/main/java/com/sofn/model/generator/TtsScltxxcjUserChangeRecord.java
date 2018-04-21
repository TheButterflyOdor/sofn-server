package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 注册主体信息表变更记录表模型对象
 * @author moon.l
 *
 */
@SuppressWarnings("serial")
public class TtsScltxxcjUserChangeRecord extends BaseModel {
	
	
	/*
	* 对应字段
	*/
    //public String id;//主键

    private String entityScaleName; //主体组织形式字典名称
    private String entityTypeName; //主体类型字典名称
    private String entityPropertyName; //主体属性字典名称
    private String entityIndustryName; //主体行业字典名称
    private String updateBeforeJson; //变更前内容JSON
    private String unit; //企业年产量计量单位
    private String unitName; //企业年产量计量单位NAME
    private String entityId; //主体ID
    private String account; //姓名
    private String realName; //真实姓名
    private String idcode; //个人身份证号
    private String entityScale; //主体组织形式
    private String entityType; //主体类型
    private String entityProperty; //主体属性
    private String entityIndustry; //主体所属行业
    private String enterpriseName; //变更前内容JSON
    private String cardType; //企业证件类型
    private String creditCode; //企业证件号码
    private String businessOperation; //企业营业期限
    private Date businessOperationStart; //企业营业期限起日
    private Date businessOperationEnd; //企业营业期限止日
    private String enterpriseIndustry; //企业所属行业
    private String annualOutput; //企业年产量
    private String address; //企业地址
    private String documentImages; //证件照片
    private String area; //所属区域
    private BigDecimal longitude; //经度
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
    private String approveType; //变更类型
    private String reason; //变更原因
    private String updateContent; //变更详细情况
    private String applyUserId; //申请人用户ID
    private String applyUpdateName; //申请人
    private String applyUpdateEntityIdcode; //申请人主体身份码
    private String applyUpdateUserIdcode; //申请人主体用户码
    private Date applyUpdateTime; //申请时间
    private String organizationCode;//组织机构代码
    private String isLong;//是否是长期
    public String getOrganizationCertificateimg() {
        return organizationCertificateimg;
    }

    public void setOrganizationCertificateimg(String organizationCertificateimg) {
        this.organizationCertificateimg = organizationCertificateimg;
    }

    public String getPositiveIdcardeimg() {
        return positiveIdcardeimg;
    }

    public void setPositiveIdcardeimg(String positiveIdcardeimg) {
        this.positiveIdcardeimg = positiveIdcardeimg;
    }

    public String getNegativeIdcardimg() {
        return negativeIdcardimg;
    }

    public void setNegativeIdcardimg(String negativeIdcardimg) {
        this.negativeIdcardimg = negativeIdcardimg;
    }

    public String getHandIdcardimg() {
        return handIdcardimg;
    }

    public void setHandIdcardimg(String handIdcardimg) {
        this.handIdcardimg = handIdcardimg;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String businessLicenceimg; //营业执照
    public String organizationCertificateimg; //组织机构代码证照
    public String positiveIdcardeimg; //身份证正面照)
    public String negativeIdcardimg; //身份证反面照
    public String handIdcardimg; //手持身份证照

    public String getBusinessLicenceimg() {
        return businessLicenceimg;
    }

    public void setBusinessLicenceimg(String businessLicenceimg) {
        this.businessLicenceimg = businessLicenceimg;
    }
    /*
	* 主键

	public String getPrimaryKey() {
        return id;
    }

    public void setPrimaryKey(String key) {
        this.id = key;
    }

	public String getId(){
        return id;
    }
	//id set
    public void setId(String id){
        this.id = id;
    }
    */



    /*
    *其余字段
    */
    //主体组织形式字典名称 get
    public String getEntityScaleName(){
        return entityScaleName;
    }
    //主体组织形式字典名称 set
    public void setEntityScaleName(String entityScaleName){
        this.entityScaleName = entityScaleName;
    }
    //主体类型字典名称 get
    public String getEntityTypeName(){
        return entityTypeName;
    }
    //主体类型字典名称 set
    public void setEntityTypeName(String entityTypeName){
        this.entityTypeName = entityTypeName;
    }
    //主体属性字典名称 get
    public String getEntityPropertyName(){
        return entityPropertyName;
    }
    //主体属性字典名称 set
    public void setEntityPropertyName(String entityPropertyName){
        this.entityPropertyName = entityPropertyName;
    }
    //主体行业字典名称 get
    public String getEntityIndustryName(){
        return entityIndustryName;
    }
    //主体行业字典名称 set
    public void setEntityIndustryName(String entityIndustryName){
        this.entityIndustryName = entityIndustryName;
    }
    //变更前内容JSON get
    public String getUpdateBeforeJson(){
        return updateBeforeJson;
    }
    //变更前内容JSON set
    public void setUpdateBeforeJson(String updateBeforeJson){
        this.updateBeforeJson = updateBeforeJson;
    }
    //企业年产量计量单位 get
    public String getUnit(){
        return unit;
    }
    //企业年产量计量单位 set
    public void setUnit(String unit){
        this.unit = unit;
    }
    //企业年产量计量单位NAME get
    public String getUnitName(){
        return unitName;
    }
    //企业年产量计量单位NAME set
    public void setUnitName(String unitName){
        this.unitName = unitName;
    }
    //主体ID get
    public String getEntityId(){
        return entityId;
    }
    //主体ID set
    public void setEntityId(String entityId){
        this.entityId = entityId;
    }
    //姓名 get
    public String getAccount(){
        return account;
    }
    //姓名 set
    public void setAccount(String account){
        this.account = account;
    }
    //真实姓名 get
    public String getRealName(){
        return realName;
    }
    //真实姓名 set
    public void setRealName(String realName){
        this.realName = realName;
    }
    //个人身份证号 get
    public String getIdcode(){
        return idcode;
    }
    //个人身份证号 set
    public void setIdcode(String idcode){
        this.idcode = idcode;
    }
    //主体组织形式 get
    public String getEntityScale(){
        return entityScale;
    }
    //主体组织形式 set
    public void setEntityScale(String entityScale){
        this.entityScale = entityScale;
    }
    //主体类型 get
    public String getEntityType(){
        return entityType;
    }
    //主体类型 set
    public void setEntityType(String entityType){
        this.entityType = entityType;
    }
    //主体属性 get
    public String getEntityProperty(){
        return entityProperty;
    }
    //主体属性 set
    public void setEntityProperty(String entityProperty){
        this.entityProperty = entityProperty;
    }
    //主体所属行业 get
    public String getEntityIndustry(){
        return entityIndustry;
    }
    //主体所属行业 set
    public void setEntityIndustry(String entityIndustry){
        this.entityIndustry = entityIndustry;
    }
    //变更前内容JSON get
    public String getEnterpriseName(){
        return enterpriseName;
    }
    //变更前内容JSON set
    public void setEnterpriseName(String enterpriseName){
        this.enterpriseName = enterpriseName;
    }
    //企业证件类型 get
    public String getCardType(){
        return cardType;
    }
    //企业证件类型 set
    public void setCardType(String cardType){
        this.cardType = cardType;
    }
    //企业证件号码 get
    public String getCreditCode(){
        return creditCode;
    }
    //企业证件号码 set
    public void setCreditCode(String creditCode){
        this.creditCode = creditCode;
    }
    //企业营业期限 get
    public String getBusinessOperation(){
        return businessOperation;
    }
    //企业营业期限 set
    public void setBusinessOperation(String businessOperation){
        this.businessOperation = businessOperation;
    }
    //企业营业期限起日 get
    public Date getBusinessOperationStart(){
        return businessOperationStart;
    }
    //企业营业期限起日 set
    public void setBusinessOperationStart(Date businessOperationStart){
        this.businessOperationStart = businessOperationStart;
    }
    //企业营业期限止日 get
    public Date getBusinessOperationEnd(){
        return businessOperationEnd;
    }
    //企业营业期限止日 set
    public void setBusinessOperationEnd(Date businessOperationEnd){
        this.businessOperationEnd = businessOperationEnd;
    }
    //企业所属行业 get
    public String getEnterpriseIndustry(){
        return enterpriseIndustry;
    }
    //企业所属行业 set
    public void setEnterpriseIndustry(String enterpriseIndustry){
        this.enterpriseIndustry = enterpriseIndustry;
    }
    //企业年产量 get
    public String getAnnualOutput(){
        return annualOutput;
    }
    //企业年产量 set
    public void setAnnualOutput(String annualOutput){
        this.annualOutput = annualOutput;
    }
    //企业地址 get
    public String getAddress(){
        return address;
    }
    //企业地址 set
    public void setAddress(String address){
        this.address = address;
    }
    //证件照片 get
    public String getDocumentImages(){
        return documentImages;
    }
    //证件照片 set
    public void setDocumentImages(String documentImages){
        this.documentImages = documentImages;
    }
    //所属区域 get
    public String getArea(){
        return area;
    }
    //所属区域 set
    public void setArea(String area){
        this.area = area;
    }
    //经度 get
    public BigDecimal getLongitude(){
        return longitude;
    }
    //经度 set
    public void setLongitude(BigDecimal longitude){
        this.longitude = longitude;
    }
    //纬度 get
    public BigDecimal getLatitude(){
        return latitude;
    }
    //纬度 set
    public void setLatitude(BigDecimal latitude){
        this.latitude = latitude;
    }
    //法人姓名 get
    public String getLegalName(){
        return legalName;
    }
    //法人姓名 set
    public void setLegalName(String legalName){
        this.legalName = legalName;
    }
    //法人身份证号码 get
    public String getLegalIdnumber(){
        return legalIdnumber;
    }
    //法人身份证号码 set
    public void setLegalIdnumber(String legalIdnumber){
        this.legalIdnumber = legalIdnumber;
    }
    //法人电话 get
    public String getLegalPhone(){
        return legalPhone;
    }
    //法人电话 set
    public void setLegalPhone(String legalPhone){
        this.legalPhone = legalPhone;
    }
    //法人相关照片 get
    public String getLegalImages(){
        return legalImages;
    }
    //法人相关照片 set
    public void setLegalImages(String legalImages){
        this.legalImages = legalImages;
    }
    //传真 get
    public String getFaxNumber(){
        return faxNumber;
    }
    //传真 set
    public void setFaxNumber(String faxNumber){
        this.faxNumber = faxNumber;
    }
    //联系人姓名 get
    public String getContactName(){
        return contactName;
    }
    //联系人姓名 set
    public void setContactName(String contactName){
        this.contactName = contactName;
    }
    //联系人电话 get
    public String getContactPhone(){
        return contactPhone;
    }
    //联系人电话 set
    public void setContactPhone(String contactPhone){
        this.contactPhone = contactPhone;
    }
    //联系人邮箱 get
    public String getContactEmail(){
        return contactEmail;
    }
    //联系人邮箱 set
    public void setContactEmail(String contactEmail){
        this.contactEmail = contactEmail;
    }
    //注册时间 get
    public Date getRegisterTime(){
        return registerTime;
    }
    //注册时间 set
    public void setRegisterTime(Date registerTime){
        this.registerTime = registerTime;
    }
    //审批状态 get
    public String getApproveStatus(){
        return approveStatus;
    }
    //审批状态 set
    public void setApproveStatus(String approveStatus){
        this.approveStatus = approveStatus;
    }
    //审批意见 get
    public String getApproveOpinion(){
        return approveOpinion;
    }
    //审批意见 set
    public void setApproveOpinion(String approveOpinion){
        this.approveOpinion = approveOpinion;
    }
    //审批人主体用户码 get
    public String getApproveUserIdcode(){
        return approveUserIdcode;
    }
    //审批人主体用户码 set
    public void setApproveUserIdcode(String approveUserIdcode){
        this.approveUserIdcode = approveUserIdcode;
    }
    //审批人姓名 get
    public String getApproveName(){
        return approveName;
    }
    //审批人姓名 set
    public void setApproveName(String approveName){
        this.approveName = approveName;
    }
    //审批时间 get
    public Date getApproveTime(){
        return approveTime;
    }
    //审批时间 set
    public void setApproveTime(Date approveTime){
        this.approveTime = approveTime;
    }
    //主体用户码 get
    public String getUserIdcode(){
        return userIdcode;
    }
    //主体用户码 set
    public void setUserIdcode(String userIdcode){
        this.userIdcode = userIdcode;
    }
    //主体身份码 get
    public String getEntityIdcode(){
        return entityIdcode;
    }
    //主体身份码 set
    public void setEntityIdcode(String entityIdcode){
        this.entityIdcode = entityIdcode;
    }
    //变更类型 get
    public String getApproveType(){
        return approveType;
    }
    //变更类型 set
    public void setApproveType(String approveType){
        this.approveType = approveType;
    }
    //变更原因 get
    public String getReason(){
        return reason;
    }
    //变更原因 set
    public void setReason(String reason){
        this.reason = reason;
    }
    //变更详细情况 get
    public String getUpdateContent(){
        return updateContent;
    }
    //变更详细情况 set
    public void setUpdateContent(String updateContent){
        this.updateContent = updateContent;
    }
    //申请人用户ID get
    public String getApplyUserId(){
        return applyUserId;
    }
    //申请人用户ID set
    public void setApplyUserId(String applyUserId){
        this.applyUserId = applyUserId;
    }
    //申请人 get
    public String getApplyUpdateName(){
        return applyUpdateName;
    }
    //申请人 set
    public void setApplyUpdateName(String applyUpdateName){
        this.applyUpdateName = applyUpdateName;
    }
    //申请人主体身份码 get
    public String getApplyUpdateEntityIdcode(){
        return applyUpdateEntityIdcode;
    }
    //申请人主体身份码 set
    public void setApplyUpdateEntityIdcode(String applyUpdateEntityIdcode){
        this.applyUpdateEntityIdcode = applyUpdateEntityIdcode;
    }
    //申请人主体用户码 get
    public String getApplyUpdateUserIdcode(){
        return applyUpdateUserIdcode;
    }
    //申请人主体用户码 set
    public void setApplyUpdateUserIdcode(String applyUpdateUserIdcode){
        this.applyUpdateUserIdcode = applyUpdateUserIdcode;
    }
    //申请时间 get
    public Date getApplyUpdateTime(){
        return applyUpdateTime;
    }
    //申请时间 set
    public void setApplyUpdateTime(Date applyUpdateTime){
        this.applyUpdateTime = applyUpdateTime;
    }

    public String getIsLong() {
        return isLong;
    }

    public void setIsLong(String isLong) {
        this.isLong = isLong;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TtsScltxxcjUserChangeRecord{");
        sb.append("entityScaleName='").append(entityScaleName).append('\'');
        sb.append(", entityTypeName='").append(entityTypeName).append('\'');
        sb.append(", entityPropertyName='").append(entityPropertyName).append('\'');
        sb.append(", entityIndustryName='").append(entityIndustryName).append('\'');
        sb.append(", updateBeforeJson='").append(updateBeforeJson).append('\'');
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", unitName='").append(unitName).append('\'');
        sb.append(", entityId='").append(entityId).append('\'');
        sb.append(", account='").append(account).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append(", idcode='").append(idcode).append('\'');
        sb.append(", entityScale='").append(entityScale).append('\'');
        sb.append(", entityType='").append(entityType).append('\'');
        sb.append(", entityProperty='").append(entityProperty).append('\'');
        sb.append(", entityIndustry='").append(entityIndustry).append('\'');
        sb.append(", enterpriseName='").append(enterpriseName).append('\'');
        sb.append(", cardType='").append(cardType).append('\'');
        sb.append(", creditCode='").append(creditCode).append('\'');
        sb.append(", businessOperation='").append(businessOperation).append('\'');
        sb.append(", businessOperationStart=").append(businessOperationStart);
        sb.append(", businessOperationEnd=").append(businessOperationEnd);
        sb.append(", enterpriseIndustry='").append(enterpriseIndustry).append('\'');
        sb.append(", annualOutput='").append(annualOutput).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", documentImages='").append(documentImages).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", legalName='").append(legalName).append('\'');
        sb.append(", legalIdnumber='").append(legalIdnumber).append('\'');
        sb.append(", legalPhone='").append(legalPhone).append('\'');
        sb.append(", legalImages='").append(legalImages).append('\'');
        sb.append(", faxNumber='").append(faxNumber).append('\'');
        sb.append(", contactName='").append(contactName).append('\'');
        sb.append(", contactPhone='").append(contactPhone).append('\'');
        sb.append(", contactEmail='").append(contactEmail).append('\'');
        sb.append(", registerTime=").append(registerTime);
        sb.append(", approveStatus='").append(approveStatus).append('\'');
        sb.append(", approveOpinion='").append(approveOpinion).append('\'');
        sb.append(", approveUserIdcode='").append(approveUserIdcode).append('\'');
        sb.append(", approveName='").append(approveName).append('\'');
        sb.append(", approveTime=").append(approveTime);
        sb.append(", userIdcode='").append(userIdcode).append('\'');
        sb.append(", entityIdcode='").append(entityIdcode).append('\'');
        sb.append(", approveType='").append(approveType).append('\'');
        sb.append(", reason='").append(reason).append('\'');
        sb.append(", updateContent='").append(updateContent).append('\'');
        sb.append(", applyUserId='").append(applyUserId).append('\'');
        sb.append(", applyUpdateName='").append(applyUpdateName).append('\'');
        sb.append(", applyUpdateEntityIdcode='").append(applyUpdateEntityIdcode).append('\'');
        sb.append(", applyUpdateUserIdcode='").append(applyUpdateUserIdcode).append('\'');
        sb.append(", applyUpdateTime=").append(applyUpdateTime);
        sb.append(", businessLicenceimg='").append(businessLicenceimg).append('\'');
        sb.append(", organizationCertificateimg='").append(organizationCertificateimg).append('\'');
        sb.append(", positiveIdcardeimg='").append(positiveIdcardeimg).append('\'');
        sb.append(", negativeIdcardimg='").append(negativeIdcardimg).append('\'');
        sb.append(", handIdcardimg='").append(handIdcardimg).append('\'');
        sb.append(", isLong='").append(handIdcardimg).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TtsScltxxcjUserChangeRecord)) return false;

        TtsScltxxcjUserChangeRecord that = (TtsScltxxcjUserChangeRecord) o;

        if (getEntityScaleName() != null ? !getEntityScaleName().equals(that.getEntityScaleName()) : that.getEntityScaleName() != null)
            return false;
        if (getEntityTypeName() != null ? !getEntityTypeName().equals(that.getEntityTypeName()) : that.getEntityTypeName() != null)
            return false;
        if (getEntityPropertyName() != null ? !getEntityPropertyName().equals(that.getEntityPropertyName()) : that.getEntityPropertyName() != null)
            return false;
        if (getEntityIndustryName() != null ? !getEntityIndustryName().equals(that.getEntityIndustryName()) : that.getEntityIndustryName() != null)
            return false;
        if (getUpdateBeforeJson() != null ? !getUpdateBeforeJson().equals(that.getUpdateBeforeJson()) : that.getUpdateBeforeJson() != null)
            return false;
        if (getUnit() != null ? !getUnit().equals(that.getUnit()) : that.getUnit() != null) return false;
        if (getUnitName() != null ? !getUnitName().equals(that.getUnitName()) : that.getUnitName() != null)
            return false;
        if (getEntityId() != null ? !getEntityId().equals(that.getEntityId()) : that.getEntityId() != null)
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
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        if (getDocumentImages() != null ? !getDocumentImages().equals(that.getDocumentImages()) : that.getDocumentImages() != null)
            return false;
        if (getArea() != null ? !getArea().equals(that.getArea()) : that.getArea() != null) return false;
        if (getLongitude() != null ? !getLongitude().equals(that.getLongitude()) : that.getLongitude() != null)
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
        if (getApproveType() != null ? !getApproveType().equals(that.getApproveType()) : that.getApproveType() != null)
            return false;
        if (getReason() != null ? !getReason().equals(that.getReason()) : that.getReason() != null) return false;
        if (getUpdateContent() != null ? !getUpdateContent().equals(that.getUpdateContent()) : that.getUpdateContent() != null)
            return false;
        if (getApplyUserId() != null ? !getApplyUserId().equals(that.getApplyUserId()) : that.getApplyUserId() != null)
            return false;
        if (getApplyUpdateName() != null ? !getApplyUpdateName().equals(that.getApplyUpdateName()) : that.getApplyUpdateName() != null)
            return false;
        if (getApplyUpdateEntityIdcode() != null ? !getApplyUpdateEntityIdcode().equals(that.getApplyUpdateEntityIdcode()) : that.getApplyUpdateEntityIdcode() != null)
            return false;
        if (getApplyUpdateUserIdcode() != null ? !getApplyUpdateUserIdcode().equals(that.getApplyUpdateUserIdcode()) : that.getApplyUpdateUserIdcode() != null)
            return false;
        if (getApplyUpdateTime() != null ? !getApplyUpdateTime().equals(that.getApplyUpdateTime()) : that.getApplyUpdateTime() != null)
            return false;
        if (getBusinessLicenceimg() != null ? !getBusinessLicenceimg().equals(that.getBusinessLicenceimg()) : that.getBusinessLicenceimg() != null)
            return false;
        if (getOrganizationCertificateimg() != null ? !getOrganizationCertificateimg().equals(that.getOrganizationCertificateimg()) : that.getOrganizationCertificateimg() != null)
            return false;
        if (getPositiveIdcardeimg() != null ? !getPositiveIdcardeimg().equals(that.getPositiveIdcardeimg()) : that.getPositiveIdcardeimg() != null)
            return false;
        if (getNegativeIdcardimg() != null ? !getNegativeIdcardimg().equals(that.getNegativeIdcardimg()) : that.getNegativeIdcardimg() != null)
            return false;
        return getHandIdcardimg() != null ? getHandIdcardimg().equals(that.getHandIdcardimg()) : that.getHandIdcardimg() == null;

    }

    @Override
    public int hashCode() {
        int result = getEntityScaleName() != null ? getEntityScaleName().hashCode() : 0;
        result = 31 * result + (getEntityTypeName() != null ? getEntityTypeName().hashCode() : 0);
        result = 31 * result + (getEntityPropertyName() != null ? getEntityPropertyName().hashCode() : 0);
        result = 31 * result + (getEntityIndustryName() != null ? getEntityIndustryName().hashCode() : 0);
        result = 31 * result + (getUpdateBeforeJson() != null ? getUpdateBeforeJson().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        result = 31 * result + (getUnitName() != null ? getUnitName().hashCode() : 0);
        result = 31 * result + (getEntityId() != null ? getEntityId().hashCode() : 0);
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
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getDocumentImages() != null ? getDocumentImages().hashCode() : 0);
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
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
        result = 31 * result + (getApproveType() != null ? getApproveType().hashCode() : 0);
        result = 31 * result + (getReason() != null ? getReason().hashCode() : 0);
        result = 31 * result + (getUpdateContent() != null ? getUpdateContent().hashCode() : 0);
        result = 31 * result + (getApplyUserId() != null ? getApplyUserId().hashCode() : 0);
        result = 31 * result + (getApplyUpdateName() != null ? getApplyUpdateName().hashCode() : 0);
        result = 31 * result + (getApplyUpdateEntityIdcode() != null ? getApplyUpdateEntityIdcode().hashCode() : 0);
        result = 31 * result + (getApplyUpdateUserIdcode() != null ? getApplyUpdateUserIdcode().hashCode() : 0);
        result = 31 * result + (getApplyUpdateTime() != null ? getApplyUpdateTime().hashCode() : 0);
        result = 31 * result + (getBusinessLicenceimg() != null ? getBusinessLicenceimg().hashCode() : 0);
        result = 31 * result + (getOrganizationCertificateimg() != null ? getOrganizationCertificateimg().hashCode() : 0);
        result = 31 * result + (getPositiveIdcardeimg() != null ? getPositiveIdcardeimg().hashCode() : 0);
        result = 31 * result + (getNegativeIdcardimg() != null ? getNegativeIdcardimg().hashCode() : 0);
        result = 31 * result + (getHandIdcardimg() != null ? getHandIdcardimg().hashCode() : 0);
        return result;
    }
}