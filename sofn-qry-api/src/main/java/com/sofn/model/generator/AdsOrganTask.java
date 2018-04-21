package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;

/**
 * 机构任务模型对象
 * @author yangran
 *
 */
 @SuppressWarnings("serial")
public class AdsOrganTask extends BaseModel {

	/*
	* 对应字段
	*/	
	//public String id;//主键
    private String sampleReportTime; //抽样上报时间
    private String checkReportTime; //检测上报时间
	private BigDecimal sampleFinishStatus; //抽样完成状态
	private BigDecimal checkFinishStatus; //检测完成状态
	private BigDecimal sampleReportStatus; //抽样上报状态
	private BigDecimal checkReportStatus; //检测上报状态
	private String monitorTaskId; //监测任务KEY
	private String sampleOrgan; //抽样机构
	private String sampleOrganId; //抽样机构KEY
	private String detectionOrgan; //检测机构
	private String detectionOrganId; //检测机构KEY
	private String regId; //抽样所属行政区划
    private String regName;  //行政区划名称
	private BigDecimal numbers; //抽样数量
	private String deparment; //报告上传单位
    private String deparmentId; //报告上传单位id
	private String uploadTime; //报告上传时间
	private BigDecimal finishNum; //完成数
	private String reportStatus; //上报状态
	private String reportTime; //上报时间
	private String tasksStatus; //任务状态
	private BigDecimal sampleFinishNum; //抽样完成数
	private BigDecimal checkFinishNum; //检测完成数
    private int rn;  //序号
    private String ids;  //批处理id
	private String cityCode; //省市区编码
	private String cityName; //省市区域名称

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
	 //抽样完成状态 get

    //抽样上报状态 get
    public String getSampleReportTime(){
        return sampleReportTime;
    }
    //抽样上报状态 set
    public void setSampleReportTime(String sampleReportTime){
        this.sampleReportTime = sampleReportTime;
    }
    //检测上报状态 get
    public String getCheckReportTime(){
        return checkReportTime;
    }
    //检测上报状态 set
    public void setCheckReportTime(String checkReportTime){
        this.checkReportTime = checkReportTime;
    }

    public BigDecimal getSampleFinishStatus(){
        return sampleFinishStatus;
    }
	//抽样完成状态 set
    public void setSampleFinishStatus(BigDecimal sampleFinishStatus){
        this.sampleFinishStatus = sampleFinishStatus;
    }
	 //检测完成状态 get
	public BigDecimal getCheckFinishStatus(){
        return checkFinishStatus;
    }
	//检测完成状态 set
    public void setCheckFinishStatus(BigDecimal checkFinishStatus){
        this.checkFinishStatus = checkFinishStatus;
    }
	 //抽样上报状态 get
	public BigDecimal getSampleReportStatus(){
        return sampleReportStatus;
    }
	//抽样上报状态 set
    public void setSampleReportStatus(BigDecimal sampleReportStatus){
        this.sampleReportStatus = sampleReportStatus;
    }
	 //检测上报状态 get
	public BigDecimal getCheckReportStatus(){
        return checkReportStatus;
    }
	//检测上报状态 set
    public void setCheckReportStatus(BigDecimal checkReportStatus){
        this.checkReportStatus = checkReportStatus;
    }
	 //监测任务KEY get
	public String getMonitorTaskId(){
        return monitorTaskId;
    }
	//监测任务KEY set
    public void setMonitorTaskId(String monitorTaskId){
        this.monitorTaskId = monitorTaskId;
    }
	 //抽样机构 get
	public String getSampleOrgan(){
        return sampleOrgan;
    }
	//抽样机构 set
    public void setSampleOrgan(String sampleOrgan){
        this.sampleOrgan = sampleOrgan;
    }
	 //抽样机构KEY get
	public String getSampleOrganId(){
        return sampleOrganId;
    }
	//抽样机构KEY set
    public void setSampleOrganId(String sampleOrganId){
        this.sampleOrganId = sampleOrganId;
    }
	 //检测机构 get
	public String getDetectionOrgan(){
        return detectionOrgan;
    }
	//检测机构 set
    public void setDetectionOrgan(String detectionOrgan){
        this.detectionOrgan = detectionOrgan;
    }
	 //检测机构KEY get
	public String getDetectionOrganId(){
        return detectionOrganId;
    }
	//检测机构KEY set
    public void setDetectionOrganId(String detectionOrganId){
        this.detectionOrganId = detectionOrganId;
    }
	 //抽样所属行政区划 get
	public String getRegId(){
        return regId;
    }
	//抽样所属行政区划 set
    public void setRegId(String regId){
        this.regId = regId;
    }
	 //抽样数量 get
	public BigDecimal getNumbers(){
        return numbers;
    }
	//抽样数量 set
    public void setNumbers(BigDecimal numbers){
        this.numbers = numbers;
    }
	 //报告上传单位 get
	public String getDeparment(){
        return deparment;
    }
	//报告上传单位 set
    public void setDeparment(String deparment){
        this.deparment = deparment;
    }
	 //报告上传时间 get
	public String getUploadTime(){
        return uploadTime;
    }
	//报告上传时间 set
    public void setUploadTime(String uploadTime){
        this.uploadTime = uploadTime;
    }
	 //完成数 get
	public BigDecimal getFinishNum(){
        return finishNum;
    }
	//完成数 set
    public void setFinishNum(BigDecimal finishNum){
        this.finishNum = finishNum;
    }
	 //上报状态 get
	public String getReportStatus(){
        return reportStatus;
    }
	//上报状态 set
    public void setReportStatus(String reportStatus){
        this.reportStatus = reportStatus;
    }
	 //上报时间 get
	public String getReportTime(){
        return reportTime;
    }
	//上报时间 set
    public void setReportTime(String reportTime){
        this.reportTime = reportTime;
    }
	 //任务状态 get
	public String getTasksStatus(){
        return tasksStatus;
    }
	//任务状态 set
    public void setTasksStatus(String tasksStatus){
        this.tasksStatus = tasksStatus;
    }
	 //抽样完成数 get
	public BigDecimal getSampleFinishNum(){
        return sampleFinishNum;
    }
	//抽样完成数 set
    public void setSampleFinishNum(BigDecimal sampleFinishNum){
        this.sampleFinishNum = sampleFinishNum;
    }
	 //检测完成数 get
	public BigDecimal getCheckFinishNum(){
        return checkFinishNum;
    }
	//检测完成数 set
    public void setCheckFinishNum(BigDecimal checkFinishNum){
        this.checkFinishNum = checkFinishNum;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDeparmentId() {
        return deparmentId;
    }

    public void setDeparmentId(String deparmentId) {
        this.deparmentId = deparmentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sampleFinishStatus=").append(sampleFinishStatus);
        sb.append(", checkFinishStatus=").append(checkFinishStatus);
        sb.append(", sampleReportStatus=").append(sampleReportStatus);
        sb.append(", checkReportStatus=").append(checkReportStatus);
        sb.append(", monitorTaskId=").append(monitorTaskId);
        sb.append(", sampleOrgan=").append(sampleOrgan);
        sb.append(", sampleOrganId=").append(sampleOrganId);
        sb.append(", detectionOrgan=").append(detectionOrgan);
        sb.append(", detectionOrganId=").append(detectionOrganId);
        sb.append(", regId=").append(regId);
        sb.append(", numbers=").append(numbers);
        sb.append(", deparment=").append(deparment);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", finishNum=").append(finishNum);
        sb.append(", reportStatus=").append(reportStatus);
        sb.append(", reportTime=").append(reportTime);
        sb.append(", tasksStatus=").append(tasksStatus);
        sb.append(", sampleFinishNum=").append(sampleFinishNum);
        sb.append(", checkFinishNum=").append(checkFinishNum);
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
        AdsOrganTask other = (AdsOrganTask) that;
          return 
        		(this.getSampleFinishStatus() == null ? other.getSampleFinishStatus() == null : this.getSampleFinishStatus().equals(other.getSampleFinishStatus())) &&
        		(this.getCheckFinishStatus() == null ? other.getCheckFinishStatus() == null : this.getCheckFinishStatus().equals(other.getCheckFinishStatus())) &&
        		(this.getSampleReportStatus() == null ? other.getSampleReportStatus() == null : this.getSampleReportStatus().equals(other.getSampleReportStatus())) &&
        		(this.getCheckReportStatus() == null ? other.getCheckReportStatus() == null : this.getCheckReportStatus().equals(other.getCheckReportStatus())) &&
        		(this.getMonitorTaskId() == null ? other.getMonitorTaskId() == null : this.getMonitorTaskId().equals(other.getMonitorTaskId())) &&
        		(this.getSampleOrgan() == null ? other.getSampleOrgan() == null : this.getSampleOrgan().equals(other.getSampleOrgan())) &&
        		(this.getSampleOrganId() == null ? other.getSampleOrganId() == null : this.getSampleOrganId().equals(other.getSampleOrganId())) &&
        		(this.getDetectionOrgan() == null ? other.getDetectionOrgan() == null : this.getDetectionOrgan().equals(other.getDetectionOrgan())) &&
        		(this.getDetectionOrganId() == null ? other.getDetectionOrganId() == null : this.getDetectionOrganId().equals(other.getDetectionOrganId())) &&
        		(this.getRegId() == null ? other.getRegId() == null : this.getRegId().equals(other.getRegId())) &&
        		(this.getNumbers() == null ? other.getNumbers() == null : this.getNumbers().equals(other.getNumbers())) &&
        		(this.getDeparment() == null ? other.getDeparment() == null : this.getDeparment().equals(other.getDeparment())) &&
        		(this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime())) &&
        		(this.getFinishNum() == null ? other.getFinishNum() == null : this.getFinishNum().equals(other.getFinishNum())) &&
        		(this.getReportStatus() == null ? other.getReportStatus() == null : this.getReportStatus().equals(other.getReportStatus())) &&
        		(this.getReportTime() == null ? other.getReportTime() == null : this.getReportTime().equals(other.getReportTime())) &&
        		(this.getTasksStatus() == null ? other.getTasksStatus() == null : this.getTasksStatus().equals(other.getTasksStatus())) &&
        		(this.getSampleFinishNum() == null ? other.getSampleFinishNum() == null : this.getSampleFinishNum().equals(other.getSampleFinishNum())) &&
        		(this.getCheckFinishNum() == null ? other.getCheckFinishNum() == null : this.getCheckFinishNum().equals(other.getCheckFinishNum())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSampleFinishStatus() == null) ? 0 : getSampleFinishStatus().hashCode());
        result = prime * result + ((getCheckFinishStatus() == null) ? 0 : getCheckFinishStatus().hashCode());
        result = prime * result + ((getSampleReportStatus() == null) ? 0 : getSampleReportStatus().hashCode());
        result = prime * result + ((getCheckReportStatus() == null) ? 0 : getCheckReportStatus().hashCode());
        result = prime * result + ((getMonitorTaskId() == null) ? 0 : getMonitorTaskId().hashCode());
        result = prime * result + ((getSampleOrgan() == null) ? 0 : getSampleOrgan().hashCode());
        result = prime * result + ((getSampleOrganId() == null) ? 0 : getSampleOrganId().hashCode());
        result = prime * result + ((getDetectionOrgan() == null) ? 0 : getDetectionOrgan().hashCode());
        result = prime * result + ((getDetectionOrganId() == null) ? 0 : getDetectionOrganId().hashCode());
        result = prime * result + ((getRegId() == null) ? 0 : getRegId().hashCode());
        result = prime * result + ((getNumbers() == null) ? 0 : getNumbers().hashCode());
        result = prime * result + ((getDeparment() == null) ? 0 : getDeparment().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        result = prime * result + ((getFinishNum() == null) ? 0 : getFinishNum().hashCode());
        result = prime * result + ((getReportStatus() == null) ? 0 : getReportStatus().hashCode());
        result = prime * result + ((getReportTime() == null) ? 0 : getReportTime().hashCode());
        result = prime * result + ((getTasksStatus() == null) ? 0 : getTasksStatus().hashCode());
        result = prime * result + ((getSampleFinishNum() == null) ? 0 : getSampleFinishNum().hashCode());
        result = prime * result + ((getCheckFinishNum() == null) ? 0 : getCheckFinishNum().hashCode());
        return result;
    }
	
}