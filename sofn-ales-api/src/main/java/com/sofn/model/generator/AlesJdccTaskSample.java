package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 监督抽查抽样单
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@SuppressWarnings("serial")
public class AlesJdccTaskSample extends BaseModel {
    private String organTaskId;//机构任务KEY(与监测信息关联字段)

    private String productTraceabilityCode;//产品追溯码

    private BigDecimal sampleBarCode;//抽样条码

    private String sampleName;//样品名称

    private String sampleCode;//样品编号

    private String tradeMark;//商标

    private String packing;//包装

    private String grade;//等级

    private String identify;//标识

    private String specificationGx;//型号规格

    private String standard;//执行标准

    private Date produceDate;//生产日期

    private String produceCertificate;//产品认证情况

    private String certificateCode;//证书编号

    private Integer sampleBase;//抽样基数

    private String samplePlace;//抽样场所

    private String testedDeparment;//受检单位名称

    private String testedAddress;//受检单位地址

    private String testedLegalrep;//受检单位法人代表

    private String testedLinkman;//受检单位联系人

    private String testedLinkmanphone;//受检单位联系人电话

    private String testedLinkmanfax;//受检单位联系人电话传真

    private String testedPerson;//受检人

    private String testedPersonphone;//受检人电话

    private String testedPersonfax;//受检人传真

    private String productionsTatus;//生产单位状态

    private String productionDeparment;//生产单位名称

    private String productionAddress;//生产单位地址

    private String productionZipcode;//生产单位邮编

    private String productionLinkman;//生产单位联系人

    private String productionLinkmanphone;//生产单位联系人电话

    private String productionLinkmanfax;//生产单位联系人传真

    private String sampleId;//抽样单位KEY

    private String sampleDeparment;//抽样单位名称

    private String sampleLinkman;//抽样单位联系人

    private String sampleAddress;//抽样单位地址

    private String sampleZipcode;//抽样单位邮编

    private String samplePhone;//抽样单位电话

    private String sampleFax;//抽样单位传真

    private String sampleEmail;//抽样单位Email

    private String proof;//检测任务依据

    private String samplePerson;//抽样人

    private Date sampleDate;//抽样日期

    private String sampleReport;//上报状态

    private String commentGx;//备注

    private String reservedField1;

    private String reservedField2;

    private String producingArea;//产地

    private String producingAreaName;//产地名称

    private String isSync;//是否同步之检测系统：0 未同步 1 已同步

    private String result;//-1：暂未检测 0：合格 1：不合格

    private Integer sampleAmount;//抽样数量

    private String attachments;//附件文件urls

    private String attachmentNames;//附件文件名称

    private String sampleSource;//样品来源

    private String note;//备注

    private String sampleUnitType;//抽样单位

    private String sampleBaseUnitType;//抽样基数单位

    public String getOrganTaskId() {
        return organTaskId;
    }

    public void setOrganTaskId(String organTaskId) {
        this.organTaskId = organTaskId == null ? null : organTaskId.trim();
    }

    public String getProductTraceabilityCode() {
        return productTraceabilityCode;
    }

    public void setProductTraceabilityCode(String productTraceabilityCode) {
        this.productTraceabilityCode = productTraceabilityCode == null ? null : productTraceabilityCode.trim();
    }

    public BigDecimal getSampleBarCode() {
        return sampleBarCode;
    }

    public void setSampleBarCode(BigDecimal sampleBarCode) {
        this.sampleBarCode = sampleBarCode;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName == null ? null : sampleName.trim();
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode == null ? null : sampleCode.trim();
    }

    public String getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark == null ? null : tradeMark.trim();
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing == null ? null : packing.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify == null ? null : identify.trim();
    }

    public String getSpecificationGx() {
        return specificationGx;
    }

    public void setSpecificationGx(String specificationGx) {
        this.specificationGx = specificationGx == null ? null : specificationGx.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public String getProduceCertificate() {
        return produceCertificate;
    }

    public void setProduceCertificate(String produceCertificate) {
        this.produceCertificate = produceCertificate == null ? null : produceCertificate.trim();
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode == null ? null : certificateCode.trim();
    }

    public Integer getSampleBase() {
        return sampleBase;
    }

    public void setSampleBase(Integer sampleBase) {
        this.sampleBase = sampleBase;
    }

    public String getSamplePlace() {
        return samplePlace;
    }

    public void setSamplePlace(String samplePlace) {
        this.samplePlace = samplePlace == null ? null : samplePlace.trim();
    }

    public String getTestedDeparment() {
        return testedDeparment;
    }

    public void setTestedDeparment(String testedDeparment) {
        this.testedDeparment = testedDeparment == null ? null : testedDeparment.trim();
    }

    public String getTestedAddress() {
        return testedAddress;
    }

    public void setTestedAddress(String testedAddress) {
        this.testedAddress = testedAddress == null ? null : testedAddress.trim();
    }

    public String getTestedLegalrep() {
        return testedLegalrep;
    }

    public void setTestedLegalrep(String testedLegalrep) {
        this.testedLegalrep = testedLegalrep == null ? null : testedLegalrep.trim();
    }

    public String getTestedLinkman() {
        return testedLinkman;
    }

    public void setTestedLinkman(String testedLinkman) {
        this.testedLinkman = testedLinkman == null ? null : testedLinkman.trim();
    }

    public String getTestedLinkmanphone() {
        return testedLinkmanphone;
    }

    public void setTestedLinkmanphone(String testedLinkmanphone) {
        this.testedLinkmanphone = testedLinkmanphone == null ? null : testedLinkmanphone.trim();
    }

    public String getTestedLinkmanfax() {
        return testedLinkmanfax;
    }

    public void setTestedLinkmanfax(String testedLinkmanfax) {
        this.testedLinkmanfax = testedLinkmanfax == null ? null : testedLinkmanfax.trim();
    }

    public String getTestedPerson() {
        return testedPerson;
    }

    public void setTestedPerson(String testedPerson) {
        this.testedPerson = testedPerson == null ? null : testedPerson.trim();
    }

    public String getTestedPersonphone() {
        return testedPersonphone;
    }

    public void setTestedPersonphone(String testedPersonphone) {
        this.testedPersonphone = testedPersonphone == null ? null : testedPersonphone.trim();
    }

    public String getTestedPersonfax() {
        return testedPersonfax;
    }

    public void setTestedPersonfax(String testedPersonfax) {
        this.testedPersonfax = testedPersonfax == null ? null : testedPersonfax.trim();
    }

    public String getProductionsTatus() {
        return productionsTatus;
    }

    public void setProductionsTatus(String productionsTatus) {
        this.productionsTatus = productionsTatus == null ? null : productionsTatus.trim();
    }

    public String getProductionDeparment() {
        return productionDeparment;
    }

    public void setProductionDeparment(String productionDeparment) {
        this.productionDeparment = productionDeparment == null ? null : productionDeparment.trim();
    }

    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress == null ? null : productionAddress.trim();
    }

    public String getProductionZipcode() {
        return productionZipcode;
    }

    public void setProductionZipcode(String productionZipcode) {
        this.productionZipcode = productionZipcode == null ? null : productionZipcode.trim();
    }

    public String getProductionLinkman() {
        return productionLinkman;
    }

    public void setProductionLinkman(String productionLinkman) {
        this.productionLinkman = productionLinkman == null ? null : productionLinkman.trim();
    }

    public String getProductionLinkmanphone() {
        return productionLinkmanphone;
    }

    public void setProductionLinkmanphone(String productionLinkmanphone) {
        this.productionLinkmanphone = productionLinkmanphone == null ? null : productionLinkmanphone.trim();
    }

    public String getProductionLinkmanfax() {
        return productionLinkmanfax;
    }

    public void setProductionLinkmanfax(String productionLinkmanfax) {
        this.productionLinkmanfax = productionLinkmanfax == null ? null : productionLinkmanfax.trim();
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId == null ? null : sampleId.trim();
    }

    public String getSampleDeparment() {
        return sampleDeparment;
    }

    public void setSampleDeparment(String sampleDeparment) {
        this.sampleDeparment = sampleDeparment == null ? null : sampleDeparment.trim();
    }

    public String getSampleLinkman() {
        return sampleLinkman;
    }

    public void setSampleLinkman(String sampleLinkman) {
        this.sampleLinkman = sampleLinkman == null ? null : sampleLinkman.trim();
    }

    public String getSampleAddress() {
        return sampleAddress;
    }

    public void setSampleAddress(String sampleAddress) {
        this.sampleAddress = sampleAddress == null ? null : sampleAddress.trim();
    }

    public String getSampleZipcode() {
        return sampleZipcode;
    }

    public void setSampleZipcode(String sampleZipcode) {
        this.sampleZipcode = sampleZipcode == null ? null : sampleZipcode.trim();
    }

    public String getSamplePhone() {
        return samplePhone;
    }

    public void setSamplePhone(String samplePhone) {
        this.samplePhone = samplePhone == null ? null : samplePhone.trim();
    }

    public String getSampleFax() {
        return sampleFax;
    }

    public void setSampleFax(String sampleFax) {
        this.sampleFax = sampleFax == null ? null : sampleFax.trim();
    }

    public String getSampleEmail() {
        return sampleEmail;
    }

    public void setSampleEmail(String sampleEmail) {
        this.sampleEmail = sampleEmail == null ? null : sampleEmail.trim();
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof == null ? null : proof.trim();
    }

    public String getSamplePerson() {
        return samplePerson;
    }

    public void setSamplePerson(String samplePerson) {
        this.samplePerson = samplePerson == null ? null : samplePerson.trim();
    }

    public Date getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(Date sampleDate) {
        this.sampleDate = sampleDate;
    }

    public String getSampleReport() {
        return sampleReport;
    }

    public void setSampleReport(String sampleReport) {
        this.sampleReport = sampleReport == null ? null : sampleReport.trim();
    }

    public String getCommentGx() {
        return commentGx;
    }

    public void setCommentGx(String commentGx) {
        this.commentGx = commentGx == null ? null : commentGx.trim();
    }

    public String getReservedField1() {
        return reservedField1;
    }

    public void setReservedField1(String reservedField1) {
        this.reservedField1 = reservedField1 == null ? null : reservedField1.trim();
    }

    public String getReservedField2() {
        return reservedField2;
    }

    public void setReservedField2(String reservedField2) {
        this.reservedField2 = reservedField2 == null ? null : reservedField2.trim();
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea == null ? null : producingArea.trim();
    }

    public String getProducingAreaName() {
        return producingAreaName;
    }

    public void setProducingAreaName(String producingAreaName) {
        this.producingAreaName = producingAreaName == null ? null : producingAreaName.trim();
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync == null ? null : isSync.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSampleAmount() {
        return sampleAmount;
    }

    public void setSampleAmount(Integer sampleAmount) {
        this.sampleAmount = sampleAmount;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public String getSampleUnitType() {
        return sampleUnitType;
    }

    @Override
    public String toString() {
        return "AlesJdccTaskSample{" +
                "organTaskId='" + organTaskId + '\'' +
                ", productTraceabilityCode='" + productTraceabilityCode + '\'' +
                ", sampleBarCode=" + sampleBarCode +
                ", sampleName='" + sampleName + '\'' +
                ", sampleCode='" + sampleCode + '\'' +
                ", tradeMark='" + tradeMark + '\'' +
                ", packing='" + packing + '\'' +
                ", grade='" + grade + '\'' +
                ", identify='" + identify + '\'' +
                ", specificationGx='" + specificationGx + '\'' +
                ", standard='" + standard + '\'' +
                ", produceDate=" + produceDate +
                ", produceCertificate='" + produceCertificate + '\'' +
                ", certificateCode='" + certificateCode + '\'' +
                ", sampleBase=" + sampleBase +
                ", samplePlace='" + samplePlace + '\'' +
                ", testedDeparment='" + testedDeparment + '\'' +
                ", testedAddress='" + testedAddress + '\'' +
                ", testedLegalrep='" + testedLegalrep + '\'' +
                ", testedLinkman='" + testedLinkman + '\'' +
                ", testedLinkmanphone='" + testedLinkmanphone + '\'' +
                ", testedLinkmanfax='" + testedLinkmanfax + '\'' +
                ", testedPerson='" + testedPerson + '\'' +
                ", testedPersonphone='" + testedPersonphone + '\'' +
                ", testedPersonfax='" + testedPersonfax + '\'' +
                ", productionsTatus='" + productionsTatus + '\'' +
                ", productionDeparment='" + productionDeparment + '\'' +
                ", productionAddress='" + productionAddress + '\'' +
                ", productionZipcode='" + productionZipcode + '\'' +
                ", productionLinkman='" + productionLinkman + '\'' +
                ", productionLinkmanphone='" + productionLinkmanphone + '\'' +
                ", productionLinkmanfax='" + productionLinkmanfax + '\'' +
                ", sampleId='" + sampleId + '\'' +
                ", sampleDeparment='" + sampleDeparment + '\'' +
                ", sampleLinkman='" + sampleLinkman + '\'' +
                ", sampleAddress='" + sampleAddress + '\'' +
                ", sampleZipcode='" + sampleZipcode + '\'' +
                ", samplePhone='" + samplePhone + '\'' +
                ", sampleFax='" + sampleFax + '\'' +
                ", sampleEmail='" + sampleEmail + '\'' +
                ", proof='" + proof + '\'' +
                ", samplePerson='" + samplePerson + '\'' +
                ", sampleDate=" + sampleDate +
                ", sampleReport='" + sampleReport + '\'' +
                ", commentGx='" + commentGx + '\'' +
                ", reservedField1='" + reservedField1 + '\'' +
                ", reservedField2='" + reservedField2 + '\'' +
                ", producingArea='" + producingArea + '\'' +
                ", producingAreaName='" + producingAreaName + '\'' +
                ", isSync='" + isSync + '\'' +
                ", result='" + result + '\'' +
                ", sampleAmount=" + sampleAmount +
                ", attachments='" + attachments + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                ", sampleSource='" + sampleSource + '\'' +
                ", note='" + note + '\'' +
                ", sampleUnitType='" + sampleUnitType + '\'' +
                ", sampleBaseUnitType='" + sampleBaseUnitType + '\'' +
                '}';
    }

    public void setSampleUnitType(String sampleUnitType) {
        this.sampleUnitType = sampleUnitType;
    }

    public String getSampleBaseUnitType() {
        return sampleBaseUnitType;
    }

    public void setSampleBaseUnitType(String sampleBaseUnitType) {
        this.sampleBaseUnitType = sampleBaseUnitType;
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
        AlesJdccTaskSample other = (AlesJdccTaskSample) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrganTaskId() == null ? other.getOrganTaskId() == null : this.getOrganTaskId().equals(other.getOrganTaskId()))
            && (this.getProductTraceabilityCode() == null ? other.getProductTraceabilityCode() == null : this.getProductTraceabilityCode().equals(other.getProductTraceabilityCode()))
            && (this.getSampleBarCode() == null ? other.getSampleBarCode() == null : this.getSampleBarCode().equals(other.getSampleBarCode()))
            && (this.getSampleName() == null ? other.getSampleName() == null : this.getSampleName().equals(other.getSampleName()))
            && (this.getSampleCode() == null ? other.getSampleCode() == null : this.getSampleCode().equals(other.getSampleCode()))
            && (this.getTradeMark() == null ? other.getTradeMark() == null : this.getTradeMark().equals(other.getTradeMark()))
            && (this.getPacking() == null ? other.getPacking() == null : this.getPacking().equals(other.getPacking()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getIdentify() == null ? other.getIdentify() == null : this.getIdentify().equals(other.getIdentify()))
            && (this.getSpecificationGx() == null ? other.getSpecificationGx() == null : this.getSpecificationGx().equals(other.getSpecificationGx()))
            && (this.getStandard() == null ? other.getStandard() == null : this.getStandard().equals(other.getStandard()))
            && (this.getProduceDate() == null ? other.getProduceDate() == null : this.getProduceDate().equals(other.getProduceDate()))
            && (this.getProduceCertificate() == null ? other.getProduceCertificate() == null : this.getProduceCertificate().equals(other.getProduceCertificate()))
            && (this.getCertificateCode() == null ? other.getCertificateCode() == null : this.getCertificateCode().equals(other.getCertificateCode()))
            && (this.getSampleBase() == null ? other.getSampleBase() == null : this.getSampleBase().equals(other.getSampleBase()))
            && (this.getSamplePlace() == null ? other.getSamplePlace() == null : this.getSamplePlace().equals(other.getSamplePlace()))
            && (this.getTestedDeparment() == null ? other.getTestedDeparment() == null : this.getTestedDeparment().equals(other.getTestedDeparment()))
            && (this.getTestedAddress() == null ? other.getTestedAddress() == null : this.getTestedAddress().equals(other.getTestedAddress()))
            && (this.getTestedLegalrep() == null ? other.getTestedLegalrep() == null : this.getTestedLegalrep().equals(other.getTestedLegalrep()))
            && (this.getTestedLinkman() == null ? other.getTestedLinkman() == null : this.getTestedLinkman().equals(other.getTestedLinkman()))
            && (this.getTestedLinkmanphone() == null ? other.getTestedLinkmanphone() == null : this.getTestedLinkmanphone().equals(other.getTestedLinkmanphone()))
            && (this.getTestedLinkmanfax() == null ? other.getTestedLinkmanfax() == null : this.getTestedLinkmanfax().equals(other.getTestedLinkmanfax()))
            && (this.getTestedPerson() == null ? other.getTestedPerson() == null : this.getTestedPerson().equals(other.getTestedPerson()))
            && (this.getTestedPersonphone() == null ? other.getTestedPersonphone() == null : this.getTestedPersonphone().equals(other.getTestedPersonphone()))
            && (this.getTestedPersonfax() == null ? other.getTestedPersonfax() == null : this.getTestedPersonfax().equals(other.getTestedPersonfax()))
            && (this.getProductionsTatus() == null ? other.getProductionsTatus() == null : this.getProductionsTatus().equals(other.getProductionsTatus()))
            && (this.getProductionDeparment() == null ? other.getProductionDeparment() == null : this.getProductionDeparment().equals(other.getProductionDeparment()))
            && (this.getProductionAddress() == null ? other.getProductionAddress() == null : this.getProductionAddress().equals(other.getProductionAddress()))
            && (this.getProductionZipcode() == null ? other.getProductionZipcode() == null : this.getProductionZipcode().equals(other.getProductionZipcode()))
            && (this.getProductionLinkman() == null ? other.getProductionLinkman() == null : this.getProductionLinkman().equals(other.getProductionLinkman()))
            && (this.getProductionLinkmanphone() == null ? other.getProductionLinkmanphone() == null : this.getProductionLinkmanphone().equals(other.getProductionLinkmanphone()))
            && (this.getProductionLinkmanfax() == null ? other.getProductionLinkmanfax() == null : this.getProductionLinkmanfax().equals(other.getProductionLinkmanfax()))
            && (this.getSampleId() == null ? other.getSampleId() == null : this.getSampleId().equals(other.getSampleId()))
            && (this.getSampleDeparment() == null ? other.getSampleDeparment() == null : this.getSampleDeparment().equals(other.getSampleDeparment()))
            && (this.getSampleLinkman() == null ? other.getSampleLinkman() == null : this.getSampleLinkman().equals(other.getSampleLinkman()))
            && (this.getSampleAddress() == null ? other.getSampleAddress() == null : this.getSampleAddress().equals(other.getSampleAddress()))
            && (this.getSampleZipcode() == null ? other.getSampleZipcode() == null : this.getSampleZipcode().equals(other.getSampleZipcode()))
            && (this.getSamplePhone() == null ? other.getSamplePhone() == null : this.getSamplePhone().equals(other.getSamplePhone()))
            && (this.getSampleFax() == null ? other.getSampleFax() == null : this.getSampleFax().equals(other.getSampleFax()))
            && (this.getSampleEmail() == null ? other.getSampleEmail() == null : this.getSampleEmail().equals(other.getSampleEmail()))
            && (this.getProof() == null ? other.getProof() == null : this.getProof().equals(other.getProof()))
            && (this.getSamplePerson() == null ? other.getSamplePerson() == null : this.getSamplePerson().equals(other.getSamplePerson()))
            && (this.getSampleDate() == null ? other.getSampleDate() == null : this.getSampleDate().equals(other.getSampleDate()))
            && (this.getSampleReport() == null ? other.getSampleReport() == null : this.getSampleReport().equals(other.getSampleReport()))
            && (this.getCommentGx() == null ? other.getCommentGx() == null : this.getCommentGx().equals(other.getCommentGx()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getReservedField1() == null ? other.getReservedField1() == null : this.getReservedField1().equals(other.getReservedField1()))
            && (this.getReservedField2() == null ? other.getReservedField2() == null : this.getReservedField2().equals(other.getReservedField2()))
            && (this.getProducingArea() == null ? other.getProducingArea() == null : this.getProducingArea().equals(other.getProducingArea()))
            && (this.getProducingAreaName() == null ? other.getProducingAreaName() == null : this.getProducingAreaName().equals(other.getProducingAreaName()))
            && (this.getIsSync() == null ? other.getIsSync() == null : this.getIsSync().equals(other.getIsSync()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrganTaskId() == null) ? 0 : getOrganTaskId().hashCode());
        result = prime * result + ((getProductTraceabilityCode() == null) ? 0 : getProductTraceabilityCode().hashCode());
        result = prime * result + ((getSampleBarCode() == null) ? 0 : getSampleBarCode().hashCode());
        result = prime * result + ((getSampleName() == null) ? 0 : getSampleName().hashCode());
        result = prime * result + ((getSampleCode() == null) ? 0 : getSampleCode().hashCode());
        result = prime * result + ((getTradeMark() == null) ? 0 : getTradeMark().hashCode());
        result = prime * result + ((getPacking() == null) ? 0 : getPacking().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getIdentify() == null) ? 0 : getIdentify().hashCode());
        result = prime * result + ((getSpecificationGx() == null) ? 0 : getSpecificationGx().hashCode());
        result = prime * result + ((getStandard() == null) ? 0 : getStandard().hashCode());
        result = prime * result + ((getProduceDate() == null) ? 0 : getProduceDate().hashCode());
        result = prime * result + ((getProduceCertificate() == null) ? 0 : getProduceCertificate().hashCode());
        result = prime * result + ((getCertificateCode() == null) ? 0 : getCertificateCode().hashCode());
        result = prime * result + ((getSampleBase() == null) ? 0 : getSampleBase().hashCode());
        result = prime * result + ((getSamplePlace() == null) ? 0 : getSamplePlace().hashCode());
        result = prime * result + ((getTestedDeparment() == null) ? 0 : getTestedDeparment().hashCode());
        result = prime * result + ((getTestedAddress() == null) ? 0 : getTestedAddress().hashCode());
        result = prime * result + ((getTestedLegalrep() == null) ? 0 : getTestedLegalrep().hashCode());
        result = prime * result + ((getTestedLinkman() == null) ? 0 : getTestedLinkman().hashCode());
        result = prime * result + ((getTestedLinkmanphone() == null) ? 0 : getTestedLinkmanphone().hashCode());
        result = prime * result + ((getTestedLinkmanfax() == null) ? 0 : getTestedLinkmanfax().hashCode());
        result = prime * result + ((getTestedPerson() == null) ? 0 : getTestedPerson().hashCode());
        result = prime * result + ((getTestedPersonphone() == null) ? 0 : getTestedPersonphone().hashCode());
        result = prime * result + ((getTestedPersonfax() == null) ? 0 : getTestedPersonfax().hashCode());
        result = prime * result + ((getProductionsTatus() == null) ? 0 : getProductionsTatus().hashCode());
        result = prime * result + ((getProductionDeparment() == null) ? 0 : getProductionDeparment().hashCode());
        result = prime * result + ((getProductionAddress() == null) ? 0 : getProductionAddress().hashCode());
        result = prime * result + ((getProductionZipcode() == null) ? 0 : getProductionZipcode().hashCode());
        result = prime * result + ((getProductionLinkman() == null) ? 0 : getProductionLinkman().hashCode());
        result = prime * result + ((getProductionLinkmanphone() == null) ? 0 : getProductionLinkmanphone().hashCode());
        result = prime * result + ((getProductionLinkmanfax() == null) ? 0 : getProductionLinkmanfax().hashCode());
        result = prime * result + ((getSampleId() == null) ? 0 : getSampleId().hashCode());
        result = prime * result + ((getSampleDeparment() == null) ? 0 : getSampleDeparment().hashCode());
        result = prime * result + ((getSampleLinkman() == null) ? 0 : getSampleLinkman().hashCode());
        result = prime * result + ((getSampleAddress() == null) ? 0 : getSampleAddress().hashCode());
        result = prime * result + ((getSampleZipcode() == null) ? 0 : getSampleZipcode().hashCode());
        result = prime * result + ((getSamplePhone() == null) ? 0 : getSamplePhone().hashCode());
        result = prime * result + ((getSampleFax() == null) ? 0 : getSampleFax().hashCode());
        result = prime * result + ((getSampleEmail() == null) ? 0 : getSampleEmail().hashCode());
        result = prime * result + ((getProof() == null) ? 0 : getProof().hashCode());
        result = prime * result + ((getSamplePerson() == null) ? 0 : getSamplePerson().hashCode());
        result = prime * result + ((getSampleDate() == null) ? 0 : getSampleDate().hashCode());
        result = prime * result + ((getSampleReport() == null) ? 0 : getSampleReport().hashCode());
        result = prime * result + ((getCommentGx() == null) ? 0 : getCommentGx().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getReservedField1() == null) ? 0 : getReservedField1().hashCode());
        result = prime * result + ((getReservedField2() == null) ? 0 : getReservedField2().hashCode());
        result = prime * result + ((getProducingArea() == null) ? 0 : getProducingArea().hashCode());
        result = prime * result + ((getProducingAreaName() == null) ? 0 : getProducingAreaName().hashCode());
        result = prime * result + ((getIsSync() == null) ? 0 : getIsSync().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        return result;
    }
}