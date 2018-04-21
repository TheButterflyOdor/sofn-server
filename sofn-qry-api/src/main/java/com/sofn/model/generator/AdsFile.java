package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 文件模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsFile extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	private String fileName; //文件名称
	private String monitorType; //文件类别
	private String fileAddress; //文件地址
	private String monitorTaskId; //所属任务KEY
	private String monitorTask; //所属任务
    private String organTaskId; //机构任务
	private String uploadDepartment; //上传单位
    private String years; //年度
    private String source;
    private String folder;
    private String field;
    private String field_id;    //行业id
    private int rn;  //序号
	
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
	 //文件名称 get
	public String getFileName(){
        return fileName;
    }
	//文件名称 set
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
	 //文件类别 get
	public String getMonitorType(){
        return monitorType;
    }
	//文件类别 set
    public void setMonitorType(String monitorType){
        this.monitorType = monitorType;
    }
	 //文件地址 get
	public String getFileAddress(){
        return fileAddress;
    }
	//文件地址 set
    public void setFileAddress(String fileAddress){
        this.fileAddress = fileAddress;
    }
	 //所属任务KEY get
	public String getMonitorTaskId(){    return monitorTaskId;   }
	//所属任务KEY set
    public void setMonitorTaskId(String monitorTaskId){
        this.monitorTaskId = monitorTaskId;
    }

    // 机构任务
    public String getOrganTaskId(){ return organTaskId; }
    public void setOrganTaskId(String organTaskId){
        this.organTaskId = organTaskId;
    }

	//所属任务 get
	public String getMonitorTask(){
        return monitorTask;
    }
	//所属任务 set
    public void setMonitorTask(String monitorTask){
        this.monitorTask = monitorTask;
    }

	 //上传单位 get
	public String getUploadDepartment(){
        return uploadDepartment;
    }
	//上传单位 set
    public void setUploadDepartment(String uploadDepartment){
        this.uploadDepartment = uploadDepartment;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField_id() {
        return field_id;
    }

    public void setField_id(String field_id) {
        this.field_id = field_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileName=").append(fileName);
        sb.append(", monitorType=").append(monitorType);
        sb.append(", fileAddress=").append(fileAddress);
        sb.append(", monitorTaskId=").append(monitorTaskId);
        sb.append(", organTaskId=").append(organTaskId);
        sb.append(", monitorTask=").append(monitorTask);
        sb.append(", uploadDepartment=").append(uploadDepartment);
        sb.append(", years=").append(years);
        sb.append(", source=").append(source);
        sb.append(", folder=").append(folder);
        sb.append(", field=").append(field);
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
        AdsFile other = (AdsFile) that;
          return 
        		(this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName())) &&
        		(this.getMonitorType() == null ? other.getMonitorType() == null : this.getMonitorType().equals(other.getMonitorType())) &&
        		(this.getFileAddress() == null ? other.getFileAddress() == null : this.getFileAddress().equals(other.getFileAddress())) &&
        		(this.getMonitorTaskId() == null ? other.getMonitorTaskId() == null : this.getMonitorTaskId().equals(other.getMonitorTaskId())) &&
        		(this.getMonitorTask() == null ? other.getMonitorTask() == null : this.getMonitorTask().equals(other.getMonitorTask())) &&
        		(this.getUploadDepartment() == null ? other.getUploadDepartment() == null : this.getUploadDepartment().equals(other.getUploadDepartment())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getMonitorType() == null) ? 0 : getMonitorType().hashCode());
        result = prime * result + ((getFileAddress() == null) ? 0 : getFileAddress().hashCode());
        result = prime * result + ((getMonitorTaskId() == null) ? 0 : getMonitorTaskId().hashCode());
        result = prime * result + ((getMonitorTask() == null) ? 0 : getMonitorTask().hashCode());
        result = prime * result + ((getUploadDepartment() == null) ? 0 : getUploadDepartment().hashCode());
        return result;
    }
	
}