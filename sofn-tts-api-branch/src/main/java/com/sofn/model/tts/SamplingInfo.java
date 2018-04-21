package com.sofn.model.tts;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽样单信息
 * Created by Administrator on 2016/10/25 0025.
 */
public class SamplingInfo implements Serializable {

    private static final long serialVersionUID = 1596910976123634756L;
    private String productName;//样品名称
    private String productId;//样品编码
    private Date proTime;//生产日期
    private String enterpriseName;//受检单位名称
    private String address;//受检单位通讯地址
    private String legalName;//受检单位法人代表
    private String contactName;//受检单位联系人
    private String contactPhone;//受检单位联系电话
    private String faxNumber;//受检单位传真
    private String entityTypeId;//主体类别ID
    private String entityTypeName;//主体类别名称
    private String entityScaleId;//组织形式ID
    private String entityScaleName;//组织形式名称
    private String entityIdCode;//主体号
    private String productPc;//批次码
    private String proEnterpriseName;//生产单位名称
    private String proAddress;//生产单位地址
    private String proZipcode;//生产单位邮编
    private String proContactName;//生产单位联系人
    private String proContactPhone;//生产单位联系电话
    private String proFaxNumber;//生产单位传真

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getProTime() {
        return proTime;
    }

    public void setProTime(Date proTime) {
        this.proTime = proTime;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
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

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEntityTypeId() {
        return entityTypeId;
    }

    public void setEntityTypeId(String entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    public String getEntityTypeName() {
        return entityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName;
    }

    public String getEntityScaleId() {
        return entityScaleId;
    }

    public void setEntityScaleId(String entityScaleId) {
        this.entityScaleId = entityScaleId;
    }

    public String getEntityScaleName() {
        return entityScaleName;
    }

    public void setEntityScaleName(String entityScaleName) {
        this.entityScaleName = entityScaleName;
    }

    public String getEntityIdCode() {
        return entityIdCode;
    }

    public void setEntityIdCode(String entityIdCode) {
        this.entityIdCode = entityIdCode;
    }

    public String getProductPc() {
        return productPc;
    }

    public void setProductPc(String productPc) {
        this.productPc = productPc;
    }

    public String getProEnterpriseName() {
        return proEnterpriseName;
    }

    public void setProEnterpriseName(String proEnterpriseName) {
        this.proEnterpriseName = proEnterpriseName;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProZipcode() {
        return proZipcode;
    }

    public void setProZipcode(String proZipcode) {
        this.proZipcode = proZipcode;
    }

    public String getProContactName() {
        return proContactName;
    }

    public void setProContactName(String proContactName) {
        this.proContactName = proContactName;
    }

    public String getProContactPhone() {
        return proContactPhone;
    }

    public void setProContactPhone(String proContactPhone) {
        this.proContactPhone = proContactPhone;
    }

    public String getProFaxNumber() {
        return proFaxNumber;
    }

    public void setProFaxNumber(String proFaxNumber) {
        this.proFaxNumber = proFaxNumber;
    }
}
