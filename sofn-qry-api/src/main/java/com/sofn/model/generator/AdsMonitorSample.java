package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 监测抽样单模型对象
 * @author moon.l
 *
 */
@SuppressWarnings("serial")
public class AdsMonitorSample extends BaseModel {


	/*
	* 对应字段
	*/
    //private String id;//主键

    private String productTraceabilityCode; //产品追溯码
    private String organTaskId; //机构任务KEY
    private String sampleBarCode; //抽样条码
    private String sampleName; //样品名称
    private String sampleCode; //样品编号
    private String tradeMark; //商标
    private String packing; //包装
    private String grade; //等级
    private String identify; //标识
    private String specification; //型号规格
    private String standard; //执行标准
    private Timestamp produceDate; //生产日期
    private String produceCertificate; //产品认证情况
    private String certificateCode; //证书编号
    private BigDecimal sampleBase; //抽样基数
    private String samplePlace; //抽样场所
    private String testedDeparment; //受检单位名称
    private String testedAddress; //受检单位地址
    private String testedLegalrep; //受检单位法人代表
    private String testedLinkman; //受检单位联系人
    private String testedLinkmanphone; //受检单位联系人电话
    private String testedLinkmanfax; //受检单位联系人电话传真
    private String testedPerson; //受检人
    private String testedPersonphone; //受检人电话
    private String testedPersonfax; //受检人传真
    private String productionsTatus; //生产单位状态
    private String productionDeparment; //生产单位名称
    private String productionAddress; //生产单位地址
    private String productionZipcode; //生产单位邮编
    private String productionLinkman; //生产单位联系人
    private String productionLinkmanphone; //生产单位联系人电话
    private String productionLinkmanfax; //生产单位联系人传真
    private String sampleId; //抽样单位KEY
    private String sampleDeparment; //抽样单位名称
    private String sampleLinkman; //抽样单位联系人
    private String sampleAddress; //抽样单位地址
    private String sampleZipcode; //抽样单位邮编
    private String samplePhone; //抽样单位电话
    private String sampleFax; //抽样单位传真
    private String sampleEmail; //抽样单位Email
    private String proof; //检测任务依据
    private String samplePerson; //抽样人
    private Timestamp sampleDate; //抽样日期
    private String sampleReport; //上报状态
    private String comment; //备注
    private String delFlag; //删除状态
    private String cityCode;
    private String sampleOrganId;
    private String sampleOrgan;
    private String checkLink;
    private AdsOrganTask adsOrganTask;
    private String productName; //产品名称
    private String productCode; //产品编码
    private String monitorTaskId;//产地
    private String producingArea;//产地
    private String producingAreaName;//产地名称
    private String taskName;//任务名称

    private String checkReport;//检测上报状态
    public String innerText; //...
    private Integer sampleNumber; // 抽样数量
    private String sampleNumberUnit; // 抽样数量单位
    private String sampleBaseUnit; // 抽样基数单位
    private String fileName; // 文件名
    private String fileAddress; // 文件地址
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
    //机构任务KEY get

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setMonitorTaskId(String monitorTaskId) {
        this.monitorTaskId = monitorTaskId;
    }

    public String getMonitorTaskId() {
        return monitorTaskId;
    }

    public String getSampleOrganId() {
        return sampleOrganId;
    }

    public void setSampleOrganId(String sampleOrganId) {
        this.sampleOrganId = sampleOrganId;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setSampleOrgan(String sampleOrgan) {
        this.sampleOrgan = sampleOrgan;
    }

    public String getSampleOrgan() {
        return sampleOrgan;
    }

    public void setCheckLink(String checkLink) {
        this.checkLink = checkLink;
    }

    public String getCheckLink() {
        return checkLink;
    }

    public AdsOrganTask getAdsOrganTask() {
        return adsOrganTask;
    }

    public void setAdsOrganTask(AdsOrganTask adsOrganTask) {
        this.adsOrganTask = adsOrganTask;
    }


    public String getProductTraceabilityCode() {
        return productTraceabilityCode;
    }

    public void setProductTraceabilityCode(String productTraceabilityCode) {
        this.productTraceabilityCode = productTraceabilityCode;
    }

    @Override
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getOrganTaskId(){
        return organTaskId;
    }
    //机构任务KEY set
    public void setOrganTaskId(String organTaskId){
        this.organTaskId = organTaskId;
    }
    //抽样条码 get
    public String getSampleBarCode(){
        return sampleBarCode;
    }
    //抽样条码 set
    public void setSampleBarCode(String sampleBarCode){
        this.sampleBarCode = sampleBarCode;
    }
    //样品名称 get
    public String getSampleName(){
        return sampleName;
    }
    //样品名称 set
    public void setSampleName(String sampleName){
        this.sampleName = sampleName;
    }
    //样品编号 get
    public String getSampleCode(){
        return sampleCode;
    }
    //样品编号 set
    public void setSampleCode(String sampleCode){
        this.sampleCode = sampleCode;
    }
    //商标 get
    public String getTradeMark(){
        return tradeMark;
    }
    //商标 set
    public void setTradeMark(String tradeMark){
        this.tradeMark = tradeMark;
    }
    //包装 get
    public String getPacking(){
        return packing;
    }
    //包装 set
    public void setPacking(String packing){
        this.packing = packing;
    }
    //等级 get
    public String getGrade(){
        return grade;
    }
    //等级 set
    public void setGrade(String grade){
        this.grade = grade;
    }
    //标识 get
    public String getIdentify(){
        return identify;
    }
    //标识 set
    public void setIdentify(String identify){
        this.identify = identify;
    }
    //型号规格 get
    public String getSpecification(){
        return specification;
    }
    //型号规格 set
    public void setSpecification(String specification){
        this.specification = specification;
    }
    //执行标准 get
    public String getStandard(){
        return standard;
    }
    //执行标准 set
    public void setStandard(String standard){
        this.standard = standard;
    }
    //生产日期 get
    public Timestamp getProduceDate(){
        return produceDate;
    }
    //生产日期 set
    public void setProduceDate(Timestamp produceDate){
        this.produceDate = produceDate;
    }
    //产品认证情况 get
    public String getProduceCertificate(){
        return produceCertificate;
    }
    //产品认证情况 set
    public void setProduceCertificate(String produceCertificate){
        this.produceCertificate = produceCertificate;
    }
    //证书编号 get
    public String getCertificateCode(){
        return certificateCode;
    }
    //证书编号 set
    public void setCertificateCode(String certificateCode){
        this.certificateCode = certificateCode;
    }
    //抽样基数 get
    public BigDecimal getSampleBase(){
        return sampleBase;
    }
    //抽样基数 set
    public void setSampleBase(BigDecimal sampleBase){
        this.sampleBase = sampleBase;
    }
    //抽样场所 get
    public String getSamplePlace(){
        return samplePlace;
    }
    //抽样场所 set
    public void setSamplePlace(String samplePlace){
        this.samplePlace = samplePlace;
    }
    //受检单位名称 get
    public String getTestedDeparment(){
        return testedDeparment;
    }
    //受检单位名称 set
    public void setTestedDeparment(String testedDeparment){
        this.testedDeparment = testedDeparment;
    }
    //受检单位地址 get
    public String getTestedAddress(){
        return testedAddress;
    }
    //受检单位地址 set
    public void setTestedAddress(String testedAddress){
        this.testedAddress = testedAddress;
    }
    //受检单位法人代表 get
    public String getTestedLegalrep(){
        return testedLegalrep;
    }
    //受检单位法人代表 set
    public void setTestedLegalrep(String testedLegalrep){
        this.testedLegalrep = testedLegalrep;
    }
    //受检单位联系人 get
    public String getTestedLinkman(){
        return testedLinkman;
    }
    //受检单位联系人 set
    public void setTestedLinkman(String testedLinkman){
        this.testedLinkman = testedLinkman;
    }
    //受检单位联系人电话 get
    public String getTestedLinkmanphone(){
        return testedLinkmanphone;
    }
    //受检单位联系人电话 set
    public void setTestedLinkmanphone(String testedLinkmanphone){
        this.testedLinkmanphone = testedLinkmanphone;
    }
    //受检单位联系人电话传真 get
    public String getTestedLinkmanfax(){
        return testedLinkmanfax;
    }
    //受检单位联系人电话传真 set
    public void setTestedLinkmanfax(String testedLinkmanfax){
        this.testedLinkmanfax = testedLinkmanfax;
    }
    //受检人 get
    public String getTestedPerson(){
        return testedPerson;
    }
    //受检人 set
    public void setTestedPerson(String testedPerson){
        this.testedPerson = testedPerson;
    }
    //受检人电话 get
    public String getTestedPersonphone(){
        return testedPersonphone;
    }
    //受检人电话 set
    public void setTestedPersonphone(String testedPersonphone){
        this.testedPersonphone = testedPersonphone;
    }
    //受检人传真 get
    public String getTestedPersonfax(){
        return testedPersonfax;
    }
    //受检人传真 set
    public void setTestedPersonfax(String testedPersonfax){
        this.testedPersonfax = testedPersonfax;
    }
    //生产单位状态 get
    public String getProductionsTatus(){
        return productionsTatus;
    }
    //生产单位状态 set
    public void setProductionsTatus(String productionsTatus){
        this.productionsTatus = productionsTatus;
    }
    //生产单位名称 get
    public String getProductionDeparment(){
        return productionDeparment;
    }
    //生产单位名称 set
    public void setProductionDeparment(String productionDeparment){
        this.productionDeparment = productionDeparment;
    }
    //生产单位地址 get
    public String getProductionAddress(){
        return productionAddress;
    }
    //生产单位地址 set
    public void setProductionAddress(String productionAddress){
        this.productionAddress = productionAddress;
    }
    //生产单位邮编 get
    public String getProductionZipcode(){
        return productionZipcode;
    }
    //生产单位邮编 set
    public void setProductionZipcode(String productionZipcode){
        this.productionZipcode = productionZipcode;
    }
    //生产单位联系人 get
    public String getProductionLinkman(){
        return productionLinkman;
    }
    //生产单位联系人 set
    public void setProductionLinkman(String productionLinkman){
        this.productionLinkman = productionLinkman;
    }
    //生产单位联系人电话 get
    public String getProductionLinkmanphone(){
        return productionLinkmanphone;
    }
    //生产单位联系人电话 set
    public void setProductionLinkmanphone(String productionLinkmanphone){
        this.productionLinkmanphone = productionLinkmanphone;
    }
    //生产单位联系人传真 get
    public String getProductionLinkmanfax(){
        return productionLinkmanfax;
    }
    //生产单位联系人传真 set
    public void setProductionLinkmanfax(String productionLinkmanfax){
        this.productionLinkmanfax = productionLinkmanfax;
    }
    //抽样单位KEY get
    public String getSampleId(){
        return sampleId;
    }
    //抽样单位KEY set
    public void setSampleId(String sampleId){
        this.sampleId = sampleId;
    }
    //抽样单位名称 get
    public String getSampleDeparment(){
        return sampleDeparment;
    }
    //抽样单位名称 set
    public void setSampleDeparment(String sampleDeparment){
        this.sampleDeparment = sampleDeparment;
    }
    //抽样单位联系人 get
    public String getSampleLinkman(){
        return sampleLinkman;
    }
    //抽样单位联系人 set
    public void setSampleLinkman(String sampleLinkman){
        this.sampleLinkman = sampleLinkman;
    }
    //抽样单位地址 get
    public String getSampleAddress(){
        return sampleAddress;
    }
    //抽样单位地址 set
    public void setSampleAddress(String sampleAddress){
        this.sampleAddress = sampleAddress;
    }
    //抽样单位邮编 get
    public String getSampleZipcode(){
        return sampleZipcode;
    }
    //抽样单位邮编 set
    public void setSampleZipcode(String sampleZipcode){
        this.sampleZipcode = sampleZipcode;
    }
    //抽样单位电话 get
    public String getSamplePhone(){
        return samplePhone;
    }
    //抽样单位电话 set
    public void setSamplePhone(String samplePhone){
        this.samplePhone = samplePhone;
    }
    //抽样单位传真 get
    public String getSampleFax(){
        return sampleFax;
    }
    //抽样单位传真 set
    public void setSampleFax(String sampleFax){
        this.sampleFax = sampleFax;
    }
    //抽样单位Email get
    public String getSampleEmail(){
        return sampleEmail;
    }
    //抽样单位Email set
    public void setSampleEmail(String sampleEmail){
        this.sampleEmail = sampleEmail;
    }
    //检测任务依据 get
    public String getProof(){
        return proof;
    }
    //检测任务依据 set
    public void setProof(String proof){
        this.proof = proof;
    }
    //抽样人 get
    public String getSamplePerson(){
        return samplePerson;
    }
    //抽样人 set
    public void setSamplePerson(String samplePerson){
        this.samplePerson = samplePerson;
    }
    //抽样日期 get
    public Timestamp getSampleDate(){
        return sampleDate;
    }
    //抽样日期 set
    public void setSampleDate(Timestamp sampleDate){
        this.sampleDate = sampleDate;
    }
    //上报状态 get
    public String getSampleReport(){
        return sampleReport;
    }
    //上报状态 set
    public void setSampleReport(String sampleReport){
        this.sampleReport = sampleReport;
    }
    //备注 get
    public String getComment(){
        return comment;
    }
    //备注 set
    public void setComment(String comment){
        this.comment = comment;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }

    public String getProducingArea(){
        return producingArea;
    }

    public void setProducingArea(String producingArea){
        this.producingArea = producingArea;
    }

    public String getProducingAreaName(){
        return producingAreaName;
    }

    public void setProducingAreaName(String producingAreaName){
        this.producingAreaName = producingAreaName;
    }

    public String getCheckReport(){
        return checkReport;
    }

    public void setCheckReport(String checkReport){
        this.checkReport = checkReport;
    }

    public String getInnerText() {
        return innerText;
    }

    public Integer getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(Integer sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getSampleNumberUnit() {
        return sampleNumberUnit;
    }

    public void setSampleNumberUnit(String sampleNumberUnit) {
        this.sampleNumberUnit = sampleNumberUnit;
    }

    public String getSampleBaseUnit() {
        return sampleBaseUnit;
    }

    public void setSampleBaseUnit(String sampleBaseUnit) {
        this.sampleBaseUnit = sampleBaseUnit;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organTaskId=").append(organTaskId);
        sb.append(", sampleBarCode=").append(sampleBarCode);
        sb.append(", sampleName=").append(sampleName);
        sb.append(", sampleCode=").append(sampleCode);
        sb.append(", tradeMark=").append(tradeMark);
        sb.append(", packing=").append(packing);
        sb.append(", grade=").append(grade);
        sb.append(", identify=").append(identify);
        sb.append(", specification=").append(specification);
        sb.append(", standard=").append(standard);
        sb.append(", produceDate=").append(produceDate);
        sb.append(", produceCertificate=").append(produceCertificate);
        sb.append(", certificateCode=").append(certificateCode);
        sb.append(", sampleBase=").append(sampleBase);
        sb.append(", samplePlace=").append(samplePlace);
        sb.append(", testedDeparment=").append(testedDeparment);
        sb.append(", testedAddress=").append(testedAddress);
        sb.append(", testedLegalrep=").append(testedLegalrep);
        sb.append(", testedLinkman=").append(testedLinkman);
        sb.append(", testedLinkmanphone=").append(testedLinkmanphone);
        sb.append(", testedLinkmanfax=").append(testedLinkmanfax);
        sb.append(", testedPerson=").append(testedPerson);
        sb.append(", testedPersonphone=").append(testedPersonphone);
        sb.append(", testedPersonfax=").append(testedPersonfax);
        sb.append(", productionsTatus=").append(productionsTatus);
        sb.append(", productionDeparment=").append(productionDeparment);
        sb.append(", productionAddress=").append(productionAddress);
        sb.append(", productionZipcode=").append(productionZipcode);
        sb.append(", productionLinkman=").append(productionLinkman);
        sb.append(", productionLinkmanphone=").append(productionLinkmanphone);
        sb.append(", productionLinkmanfax=").append(productionLinkmanfax);
        sb.append(", sampleId=").append(sampleId);
        sb.append(", sampleDeparment=").append(sampleDeparment);
        sb.append(", sampleLinkman=").append(sampleLinkman);
        sb.append(", sampleAddress=").append(sampleAddress);
        sb.append(", sampleZipcode=").append(sampleZipcode);
        sb.append(", samplePhone=").append(samplePhone);
        sb.append(", sampleFax=").append(sampleFax);
        sb.append(", sampleEmail=").append(sampleEmail);
        sb.append(", proof=").append(proof);
        sb.append(", samplePerson=").append(samplePerson);
        sb.append(", sampleDate=").append(sampleDate);
        sb.append(", sampleReport=").append(sampleReport);
        sb.append(", comment=").append(comment);
        sb.append(", sampleNumber=").append(sampleNumber);
        sb.append(", sampleNumberUnit=").append(sampleNumberUnit);
        sb.append(", sampleBaseUnit=").append(sampleBaseUnit);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileAddress=").append(fileAddress);
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
        AdsMonitorSample other = (AdsMonitorSample) that;
        return
                (this.getOrganTaskId() == null ? other.getOrganTaskId() == null : this.getOrganTaskId().equals(other.getOrganTaskId())) &&
                        (this.getSampleBarCode() == null ? other.getSampleBarCode() == null : this.getSampleBarCode().equals(other.getSampleBarCode())) &&
                        (this.getSampleName() == null ? other.getSampleName() == null : this.getSampleName().equals(other.getSampleName())) &&
                        (this.getSampleCode() == null ? other.getSampleCode() == null : this.getSampleCode().equals(other.getSampleCode())) &&
                        (this.getTradeMark() == null ? other.getTradeMark() == null : this.getTradeMark().equals(other.getTradeMark())) &&
                        (this.getPacking() == null ? other.getPacking() == null : this.getPacking().equals(other.getPacking())) &&
                        (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade())) &&
                        (this.getIdentify() == null ? other.getIdentify() == null : this.getIdentify().equals(other.getIdentify())) &&
                        (this.getSpecification() == null ? other.getSpecification() == null : this.getSpecification().equals(other.getSpecification())) &&
                        (this.getStandard() == null ? other.getStandard() == null : this.getStandard().equals(other.getStandard())) &&
                        (this.getProduceDate() == null ? other.getProduceDate() == null : this.getProduceDate().equals(other.getProduceDate())) &&
                        (this.getProduceCertificate() == null ? other.getProduceCertificate() == null : this.getProduceCertificate().equals(other.getProduceCertificate())) &&
                        (this.getCertificateCode() == null ? other.getCertificateCode() == null : this.getCertificateCode().equals(other.getCertificateCode())) &&
                        (this.getSampleBase() == null ? other.getSampleBase() == null : this.getSampleBase().equals(other.getSampleBase())) &&
                        (this.getSamplePlace() == null ? other.getSamplePlace() == null : this.getSamplePlace().equals(other.getSamplePlace())) &&
                        (this.getTestedDeparment() == null ? other.getTestedDeparment() == null : this.getTestedDeparment().equals(other.getTestedDeparment())) &&
                        (this.getTestedAddress() == null ? other.getTestedAddress() == null : this.getTestedAddress().equals(other.getTestedAddress())) &&
                        (this.getTestedLegalrep() == null ? other.getTestedLegalrep() == null : this.getTestedLegalrep().equals(other.getTestedLegalrep())) &&
                        (this.getTestedLinkman() == null ? other.getTestedLinkman() == null : this.getTestedLinkman().equals(other.getTestedLinkman())) &&
                        (this.getTestedLinkmanphone() == null ? other.getTestedLinkmanphone() == null : this.getTestedLinkmanphone().equals(other.getTestedLinkmanphone())) &&
                        (this.getTestedLinkmanfax() == null ? other.getTestedLinkmanfax() == null : this.getTestedLinkmanfax().equals(other.getTestedLinkmanfax())) &&
                        (this.getTestedPerson() == null ? other.getTestedPerson() == null : this.getTestedPerson().equals(other.getTestedPerson())) &&
                        (this.getTestedPersonphone() == null ? other.getTestedPersonphone() == null : this.getTestedPersonphone().equals(other.getTestedPersonphone())) &&
                        (this.getTestedPersonfax() == null ? other.getTestedPersonfax() == null : this.getTestedPersonfax().equals(other.getTestedPersonfax())) &&
                        (this.getProductionsTatus() == null ? other.getProductionsTatus() == null : this.getProductionsTatus().equals(other.getProductionsTatus())) &&
                        (this.getProductionDeparment() == null ? other.getProductionDeparment() == null : this.getProductionDeparment().equals(other.getProductionDeparment())) &&
                        (this.getProductionAddress() == null ? other.getProductionAddress() == null : this.getProductionAddress().equals(other.getProductionAddress())) &&
                        (this.getProductionZipcode() == null ? other.getProductionZipcode() == null : this.getProductionZipcode().equals(other.getProductionZipcode())) &&
                        (this.getProductionLinkman() == null ? other.getProductionLinkman() == null : this.getProductionLinkman().equals(other.getProductionLinkman())) &&
                        (this.getProductionLinkmanphone() == null ? other.getProductionLinkmanphone() == null : this.getProductionLinkmanphone().equals(other.getProductionLinkmanphone())) &&
                        (this.getProductionLinkmanfax() == null ? other.getProductionLinkmanfax() == null : this.getProductionLinkmanfax().equals(other.getProductionLinkmanfax())) &&
                        (this.getSampleId() == null ? other.getSampleId() == null : this.getSampleId().equals(other.getSampleId())) &&
                        (this.getSampleDeparment() == null ? other.getSampleDeparment() == null : this.getSampleDeparment().equals(other.getSampleDeparment())) &&
                        (this.getSampleLinkman() == null ? other.getSampleLinkman() == null : this.getSampleLinkman().equals(other.getSampleLinkman())) &&
                        (this.getSampleAddress() == null ? other.getSampleAddress() == null : this.getSampleAddress().equals(other.getSampleAddress())) &&
                        (this.getSampleZipcode() == null ? other.getSampleZipcode() == null : this.getSampleZipcode().equals(other.getSampleZipcode())) &&
                        (this.getSamplePhone() == null ? other.getSamplePhone() == null : this.getSamplePhone().equals(other.getSamplePhone())) &&
                        (this.getSampleFax() == null ? other.getSampleFax() == null : this.getSampleFax().equals(other.getSampleFax())) &&
                        (this.getSampleEmail() == null ? other.getSampleEmail() == null : this.getSampleEmail().equals(other.getSampleEmail())) &&
                        (this.getProof() == null ? other.getProof() == null : this.getProof().equals(other.getProof())) &&
                        (this.getSamplePerson() == null ? other.getSamplePerson() == null : this.getSamplePerson().equals(other.getSamplePerson())) &&
                        (this.getSampleDate() == null ? other.getSampleDate() == null : this.getSampleDate().equals(other.getSampleDate())) &&
                        (this.getSampleReport() == null ? other.getSampleReport() == null : this.getSampleReport().equals(other.getSampleReport())) &&
                        (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment())) &&
                        (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrganTaskId() == null) ? 0 : getOrganTaskId().hashCode());
        result = prime * result + ((getSampleBarCode() == null) ? 0 : getSampleBarCode().hashCode());
        result = prime * result + ((getSampleName() == null) ? 0 : getSampleName().hashCode());
        result = prime * result + ((getSampleCode() == null) ? 0 : getSampleCode().hashCode());
        result = prime * result + ((getTradeMark() == null) ? 0 : getTradeMark().hashCode());
        result = prime * result + ((getPacking() == null) ? 0 : getPacking().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getIdentify() == null) ? 0 : getIdentify().hashCode());
        result = prime * result + ((getSpecification() == null) ? 0 : getSpecification().hashCode());
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
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        return result;
    }

}