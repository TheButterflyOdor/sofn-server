package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 检测信息表模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsCheckInfo extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//private String id;//主键
    private String productTraceabilityCode; //产品追溯码
	private String organTaskId; //机构任务KEY
	private String sampleBarCode; //抽样条码
	private String monitorSampleId; //抽样单KEY
	private String sampleCode; //样品编码
	private String sampleName; //样品名称
	private String checkLink; //检测环节
	private String result; //判定结果  1:合格 0:不合格  -1：暂未检测
	private String checkOrganId; //检测单位KEY
	private String checkOrgan; //检测单位
	private String checkReport; //检测上传状态  1:已上报  0：未上报
	private Timestamp checkTime; //检测时间
    //扩展
	private String testedDeparment; //受检单位
	private String sampleDeparment; //抽样单位
	private String sampleDate; //抽样时间

    private String monitorTaskId; //监测任务KEY
    private String year; //年度
    private String taskName; //任务名称
    private String productCode; //产品编码
    private String checkProject; //检测项目
    private String checkNum; //检出值
    private String judgeNum; //判定值
    private String checkInfoId; //检测信息Key



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
    private String sampleLinkman; //抽样单位联系人
    private String sampleAddress; //抽样单位地址
    private String sampleZipcode; //抽样单位邮编
    private String samplePhone; //抽样单位电话
    private String sampleFax; //抽样单位传真
    private String sampleEmail; //抽样单位Email
    private String proof; //检测任务依据
    private String samplePerson; //抽样人
    private String sampleReport; //上报状态
    private String comment; //备注
    public String innerText; //...


    public String productName; //产品名称

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String rn; //序号



    public String producingArea; //所属行政区id
    public String monitorClass; //jianc  leixing

    public String getMonitorClass() {
        return monitorClass;
    }

    public void setMonitorClass(String monitorClass) {
        this.monitorClass = monitorClass;
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea;
    }

    public String getProduceCertificate() {
        return produceCertificate;
    }

    public void setProduceCertificate(String produceCertificate) {
        this.produceCertificate = produceCertificate;
    }

    public String getSampleReport() {
        return sampleReport;
    }

    public void setSampleReport(String sampleReport) {
        this.sampleReport = sampleReport;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String delFlag; //删除状态

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Timestamp getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Timestamp produceDate) {
        this.produceDate = produceDate;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public BigDecimal getSampleBase() {
        return sampleBase;
    }

    public void setSampleBase(BigDecimal sampleBase) {
        this.sampleBase = sampleBase;
    }

    public String getSamplePlace() {
        return samplePlace;
    }

    public void setSamplePlace(String samplePlace) {
        this.samplePlace = samplePlace;
    }

    public String getTestedAddress() {
        return testedAddress;
    }

    public void setTestedAddress(String testedAddress) {
        this.testedAddress = testedAddress;
    }

    public String getTestedLegalrep() {
        return testedLegalrep;
    }

    public void setTestedLegalrep(String testedLegalrep) {
        this.testedLegalrep = testedLegalrep;
    }

    public String getTestedLinkman() {
        return testedLinkman;
    }

    public void setTestedLinkman(String testedLinkman) {
        this.testedLinkman = testedLinkman;
    }

    public String getTestedLinkmanphone() {
        return testedLinkmanphone;
    }

    public void setTestedLinkmanphone(String testedLinkmanphone) {
        this.testedLinkmanphone = testedLinkmanphone;
    }

    public String getTestedLinkmanfax() {
        return testedLinkmanfax;
    }

    public void setTestedLinkmanfax(String testedLinkmanfax) {
        this.testedLinkmanfax = testedLinkmanfax;
    }

    public String getTestedPerson() {
        return testedPerson;
    }

    public void setTestedPerson(String testedPerson) {
        this.testedPerson = testedPerson;
    }

    public String getTestedPersonphone() {
        return testedPersonphone;
    }

    public void setTestedPersonphone(String testedPersonphone) {
        this.testedPersonphone = testedPersonphone;
    }

    public String getTestedPersonfax() {
        return testedPersonfax;
    }

    public void setTestedPersonfax(String testedPersonfax) {
        this.testedPersonfax = testedPersonfax;
    }

    public String getProductionsTatus() {
        return productionsTatus;
    }

    public void setProductionsTatus(String productionsTatus) {
        this.productionsTatus = productionsTatus;
    }

    public String getProductionDeparment() {
        return productionDeparment;
    }

    public void setProductionDeparment(String productionDeparment) {
        this.productionDeparment = productionDeparment;
    }

    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress;
    }

    public String getProductionZipcode() {
        return productionZipcode;
    }

    public void setProductionZipcode(String productionZipcode) {
        this.productionZipcode = productionZipcode;
    }

    public String getProductionLinkman() {
        return productionLinkman;
    }

    public void setProductionLinkman(String productionLinkman) {
        this.productionLinkman = productionLinkman;
    }

    public String getProductionLinkmanphone() {
        return productionLinkmanphone;
    }

    public void setProductionLinkmanphone(String productionLinkmanphone) {
        this.productionLinkmanphone = productionLinkmanphone;
    }

    public String getProductionLinkmanfax() {
        return productionLinkmanfax;
    }

    public void setProductionLinkmanfax(String productionLinkmanfax) {
        this.productionLinkmanfax = productionLinkmanfax;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleLinkman() {
        return sampleLinkman;
    }

    public void setSampleLinkman(String sampleLinkman) {
        this.sampleLinkman = sampleLinkman;
    }

    public String getSampleAddress() {
        return sampleAddress;
    }

    public void setSampleAddress(String sampleAddress) {
        this.sampleAddress = sampleAddress;
    }

    public String getSampleZipcode() {
        return sampleZipcode;
    }

    public void setSampleZipcode(String sampleZipcode) {
        this.sampleZipcode = sampleZipcode;
    }

    public String getSamplePhone() {
        return samplePhone;
    }

    public void setSamplePhone(String samplePhone) {
        this.samplePhone = samplePhone;
    }

    public String getSampleFax() {
        return sampleFax;
    }

    public void setSampleFax(String sampleFax) {
        this.sampleFax = sampleFax;
    }

    public String getSampleEmail() {
        return sampleEmail;
    }

    public void setSampleEmail(String sampleEmail) {
        this.sampleEmail = sampleEmail;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getSamplePerson() {
        return samplePerson;
    }

    public void setSamplePerson(String samplePerson) {
        this.samplePerson = samplePerson;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }

    public void setInnerText(String innerText) {
        this.innerText = innerText;
    }

    public String getInnerText() {
        return innerText;
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

    public String getProductTraceabilityCode() {
        return productTraceabilityCode;
    }

    public void setProductTraceabilityCode(String productTraceabilityCode) {
        this.productTraceabilityCode = productTraceabilityCode;
    }


    //检测项目get
    public String getCheckProject(){return checkProject;}
  //检测项目 set
    public void setCheckProject(String checkProject){
        this.checkProject = checkProject;
    }
    //检出值get
    public String getCheckNum(){return checkNum;}
  //检出值set
    public void setCheckNum(String checkNum){
        this.checkNum = checkNum;
    }
    //检测信息Key
    public String getJudgeNum(){return judgeNum;}
  //检测信息Key
    public void setJudgeNum(String judgeNum){
        this.judgeNum = judgeNum;
    }
    //判定值get
    public String getCheckInfoId(){return checkInfoId;}
  //判定值set
    public void setCheckInfoId(String checkInfoId){
        this.checkInfoId = checkInfoId;
    }



    //监测任务KEY get
    public String getMonitorTaskId(){return monitorTaskId;}

    public void setMonitorTaskId(String monitorTaskId){
        this.monitorTaskId = monitorTaskId;
    }
    //监测任务KEY set

    //年度 set
    public void setYear(String year){
        this.year = year;
    }
    //年度 get
    public String getYear(){
        return year;
    }
    //任务名称 set
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }
    //任务名称
    public String getTaskName(){
        return taskName;
    }

    // 受检单位 get
    public String getTestedDeparment(){
        return testedDeparment;
    }
    //受检单位 set
    public void setTestedDeparment(String testedDeparment){
        this.testedDeparment = testedDeparment;
    }
    //抽样单位 get
    public String getSampleDeparment(){
        return sampleDeparment;
    }
    //抽样单位 set
    public void setSampleDeparment(String sampleDeparment){
        this.sampleDeparment = sampleDeparment;
    }
    //抽样时间 get
    public String getSampleDate(){
        return sampleDate;
    }
    //抽样时间 set
    public void setSampleDate(String sampleDate){
        this.sampleDate = sampleDate;
    }


    //机构任务KEY get
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
	 //抽样单KEY get
	public String getMonitorSampleId(){
        return monitorSampleId;
    }
	//抽样单KEY set
    public void setMonitorSampleId(String monitorSampleId){
        this.monitorSampleId = monitorSampleId;
    }
	 //产品编码 get
	public String getProductCode(){
        return productCode;
    }
	//产品编码 set
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
	 //样品编码 get
	public String getSampleCode(){
        return sampleCode;
    }
	//样品编码 set
    public void setSampleCode(String sampleCode){
        this.sampleCode = sampleCode;
    }
	 //样品名称 get
	public String getSampleName(){
        return sampleName;
    }
	//样品名称 set
    public void setSampleName(String sampleName){
        this.sampleName = sampleName;
    }
	 //检测环节 get
	public String getCheckLink(){
        return checkLink;
    }
	//检测环节 set
    public void setCheckLink(String checkLink){
        this.checkLink = checkLink;
    }
	 //判定结果 get
	public String getResult(){
        return result;
    }
	//判定结果 set
    public void setResult(String result){
        this.result = result;
    }
	 //检测单位KEY get
	public String getCheckOrganId(){
        return checkOrganId;
    }
	//检测单位KEY set
    public void setCheckOrganId(String checkOrganId){
        this.checkOrganId = checkOrganId;
    }
	 //检测单位 get
	public String getCheckOrgan(){
        return checkOrgan;
    }
	//检测单位 set
    public void setCheckOrgan(String checkOrgan){
        this.checkOrgan = checkOrgan;
    }
	 //检测上传状态 get
	public String getCheckReport(){
        return checkReport;
    }
	//检测上传状态 set
    public void setCheckReport(String checkReport){
        this.checkReport = checkReport;
    }
	 //检测时间 get
	public Timestamp getCheckTime(){
        return checkTime;
    }
	//检测时间 set
    public void setCheckTime(Timestamp checkTime){
        this.checkTime = checkTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organTaskId=").append(organTaskId);
        sb.append(", sampleBarCode=").append(sampleBarCode);
        sb.append(", monitorSampleId=").append(monitorSampleId);
        sb.append(", productCode=").append(productCode);
        sb.append(", sampleCode=").append(sampleCode);
        sb.append(", sampleName=").append(sampleName);
        sb.append(", checkLink=").append(checkLink);
        sb.append(", result=").append(result);
        sb.append(", checkOrganId=").append(checkOrganId);
        sb.append(", checkOrgan=").append(checkOrgan);
        sb.append(", checkReport=").append(checkReport);
        sb.append(", checkTime=").append(checkTime);
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
        AdsCheckInfo other = (AdsCheckInfo) that;
          return 
        		(this.getOrganTaskId() == null ? other.getOrganTaskId() == null : this.getOrganTaskId().equals(other.getOrganTaskId())) &&
        		(this.getSampleBarCode() == null ? other.getSampleBarCode() == null : this.getSampleBarCode().equals(other.getSampleBarCode())) &&
        		(this.getMonitorSampleId() == null ? other.getMonitorSampleId() == null : this.getMonitorSampleId().equals(other.getMonitorSampleId())) &&
        		(this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode())) &&
        		(this.getSampleCode() == null ? other.getSampleCode() == null : this.getSampleCode().equals(other.getSampleCode())) &&
        		(this.getSampleName() == null ? other.getSampleName() == null : this.getSampleName().equals(other.getSampleName())) &&
        		(this.getCheckLink() == null ? other.getCheckLink() == null : this.getCheckLink().equals(other.getCheckLink())) &&
        		(this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult())) &&
        		(this.getCheckOrganId() == null ? other.getCheckOrganId() == null : this.getCheckOrganId().equals(other.getCheckOrganId())) &&
        		(this.getCheckOrgan() == null ? other.getCheckOrgan() == null : this.getCheckOrgan().equals(other.getCheckOrgan())) &&
        		(this.getCheckReport() == null ? other.getCheckReport() == null : this.getCheckReport().equals(other.getCheckReport())) &&
        		(this.getCheckTime() == null ? other.getCheckTime() == null : this.getCheckTime().equals(other.getCheckTime())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrganTaskId() == null) ? 0 : getOrganTaskId().hashCode());
        result = prime * result + ((getSampleBarCode() == null) ? 0 : getSampleBarCode().hashCode());
        result = prime * result + ((getMonitorSampleId() == null) ? 0 : getMonitorSampleId().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getSampleCode() == null) ? 0 : getSampleCode().hashCode());
        result = prime * result + ((getSampleName() == null) ? 0 : getSampleName().hashCode());
        result = prime * result + ((getCheckLink() == null) ? 0 : getCheckLink().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getCheckOrganId() == null) ? 0 : getCheckOrganId().hashCode());
        result = prime * result + ((getCheckOrgan() == null) ? 0 : getCheckOrgan().hashCode());
        result = prime * result + ((getCheckReport() == null) ? 0 : getCheckReport().hashCode());
        result = prime * result + ((getCheckTime() == null) ? 0 : getCheckTime().hashCode());
        return result;
    }
	
}