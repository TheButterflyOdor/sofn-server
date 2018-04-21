package com.sofn.model.qry;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sofn
 * @version 2018年01月25日 下午 4:01
 */
public class TtsScltxxcjRegiterDto2 implements Serializable {

    public String id;
    public String remark;
    public String createBy;
    public Date createTime;
    public String updateBy;
    public Date updateTime;
    public String delFlag;
    public String entityScaleName; //主体组织形式字典名称
    public String entityTypeName; //主体类型字典名称
    public String entityPropertyName; //主体属性字典名称
    public String entityIndustryName; //主体行业字典名称
    public String unitName; //企业年产量计量单位
    public BigDecimal latitude; //纬度
    public String legalName; //法人姓名
    public String legalIdnumber; //法人身份证号码
    public String legalPhone; //法人电话
    public String legalImages; //法人相关照片
    public String faxNumber; //传真
    public String contactName; //联系人姓名
    public String contactPhone; //联系人电话
    public String contactEmail; //联系人邮箱
    public Date registerTime; //注册时间
    public String approveStatus; //审批状态
    public String approveOpinion; //审批意见
    public String approveUserIdcode; //审批人主体用户码
    public String approveName; //审批人姓名
    public Date approveTime; //审批时间
    public String userIdcode; //主体用户码
    public String entityIdcode; //主体身份码
    public String account; //账号
    public String realName; //真实姓名
    public String idcode; //个人身份证号
    public String entityScale; //主体组织形式
    public String entityType; //主体类型
    public String entityProperty; //主体属性
    public String entityIndustry; //主体所属行业
    public String enterpriseName; //企业名称
    public String cardType; //企业证件类型
    public String creditCode; //企业证件号码
    public String businessOperation; //企业营业期限
    public Date businessOperationStart; //企业营业期限起日
    public Date businessOperationEnd; //企业营业期限止日
    public String enterpriseIndustry; //企业所属行业
    public String annualOutput; //企业年产量
    public String unit; //企业年产量计量单位
    public String address; //企业地址
    public String documentImages; //证件照片
    public String area; //所属区域
    public BigDecimal longitude; //经度

    public String code;//验证码
    public String password; //密码

    public String rePassword;//二次密码
    public String badRecord;//不良记录
    public String businessLicenceimg; //营业执照
    public String organizationCertificateimg; //组织机构代码证照
    public String positiveIdcardeimg; //身份证正面照)
    public String negativeIdcardimg; //身份证反面照
    public String handIdcardimg; //手持身份证照
    public String isMain; //主账号和子账号标识
    public String childName;//子账号姓名
    public String childPhone;//子账号联系方式
    public String childEmail;//子账号邮箱
    public String childDelFlag;//子账号删除标识
    public String childIdcard;//子账号身份证号码
    public String organizationCode; //组织机构代码
    public String isLong;//是否是长期
    public String userId;//用户ID
    public String userAccount;//账户
    public String userPassword;//密码

    public String getChildIdcard() {
        return childIdcard;
    }

    public void setChildIdcard(String childIdcard) {
        this.childIdcard = childIdcard;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildPhone() {
        return childPhone;
    }

    public void setChildPhone(String childPhone) {
        this.childPhone = childPhone;
    }

    public String getChildEmail() {
        return childEmail;
    }

    public void setChildEmail(String childEmail) {
        this.childEmail = childEmail;
    }

    public String getChildDelFlag() {
        return childDelFlag;
    }

    public void setChildDelFlag(String childDelFlag) {
        this.childDelFlag = childDelFlag;
    }

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

    public String getBusinessLicenceimg() {
        return businessLicenceimg;
    }

    public void setBusinessLicenceimg(String businessLicenceimg) {
        this.businessLicenceimg = businessLicenceimg;
    }
    public void setBadRecord(String badRecord) {
        this.badRecord = badRecord;
    }

    public String getBadRecord() {

        return badRecord;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
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
    //企业年产量计量单位 get
    public String getUnitName(){
        return unitName;
    }
    //企业年产量计量单位 set
    public void setUnitName(String unitName){
        this.unitName = unitName;
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
    //账号 get
    public String getAccount(){
        return account;
    }
    //账号 set
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
    //企业名称 get
    public String getEnterpriseName(){
        return enterpriseName;
    }
    //企业名称 set
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
    //企业年产量计量单位 get
    public String getUnit(){
        return unit;
    }
    //企业年产量计量单位 set
    public void setUnit(String unit){
        this.unit = unit;
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
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getIsLong() {
        return isLong;
    }

    public void setIsLong(String isLong) {
        this.isLong = isLong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null?null:id.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null?null:remark.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null?null:createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag  == null?null:delFlag.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId  == null?null:userId.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null?null:userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null?null:userPassword.trim();
    }
}
