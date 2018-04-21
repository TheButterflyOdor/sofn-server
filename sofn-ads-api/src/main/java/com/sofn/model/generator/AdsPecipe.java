package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 回执单模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsPecipe extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//private String id;//主键
    private BigDecimal finishNum;//完成数
	private String organName; //机构名称
	private String organTaskId; //机构任务KEY
	private String organId; //机构KEY
	private BigDecimal taskNum; //任务数
	private String receiptClass; //回执单类型
	private String receiptTime; //上报时间
	private String reason; //退回原因
    private String sName; //样品名称
    private String sCode; //样品编号
    private String ids; //批处理id
	
	
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
    //完成数 get
    public BigDecimal getFinishNum(){
        return finishNum;
    }
    //完成数 set
    public void setFinishNum(BigDecimal finishNum){
        this.finishNum = finishNum;
    }
	 //机构名称 get
	public String getOrganName(){
        return organName;
    }
	//机构名称 set
    public void setOrganName(String organName){
        this.organName = organName;
    }
	 //机构任务KEY get
	public String getOrganTaskId(){
        return organTaskId;
    }
	//机构任务KEY set
    public void setOrganTaskId(String organTaskId){
        this.organTaskId = organTaskId;
    }
	 //机构KEY get
	public String getOrganId(){
        return organId;
    }
	//机构KEY set
    public void setOrganId(String organId){
        this.organId = organId;
    }
	 //任务数 get
	public BigDecimal getTaskNum(){
        return taskNum;
    }
	//任务数 set
    public void setTaskNum(BigDecimal taskNum){
        this.taskNum = taskNum;
    }
	 //回执单类型 get
	public String getReceiptClass(){
        return receiptClass;
    }
	//回执单类型 set
    public void setReceiptClass(String receiptClass){
        this.receiptClass = receiptClass;
    }
	 //上报时间 get
	public String getReceiptTime(){
        return receiptTime;
    }
	//上报时间 set
    public void setReceiptTime(String receiptTime){
        this.receiptTime = receiptTime;
    }
	 //退回原因 get
	public String getReason(){
        return reason;
    }
	//退回原因 set
    public void setReason(String reason){
        this.reason = reason;
    }


    public String getSName(){
        return sName;
    }
    public void setSName(String sName){
        this.sName = sName;
    }

    public String getSCode(){
        return sCode;
    }
    public void setSCode(String sCode){
        this.sCode = sCode;
    }

    public String getIds(){
        return ids;
    }
    public void setIds(String ids){
        this.ids = ids;
    }
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organName=").append(organName);
        sb.append(", organTaskId=").append(organTaskId);
        sb.append(", organId=").append(organId);
        sb.append(", taskNum=").append(taskNum);
        sb.append(", receiptClass=").append(receiptClass);
        sb.append(", receiptTime=").append(receiptTime);
        sb.append(", reason=").append(reason);
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
        AdsPecipe other = (AdsPecipe) that;
          return 
        		(this.getOrganName() == null ? other.getOrganName() == null : this.getOrganName().equals(other.getOrganName())) &&
        		(this.getOrganTaskId() == null ? other.getOrganTaskId() == null : this.getOrganTaskId().equals(other.getOrganTaskId())) &&
        		(this.getOrganId() == null ? other.getOrganId() == null : this.getOrganId().equals(other.getOrganId())) &&
        		(this.getTaskNum() == null ? other.getTaskNum() == null : this.getTaskNum().equals(other.getTaskNum())) &&
        		(this.getReceiptClass() == null ? other.getReceiptClass() == null : this.getReceiptClass().equals(other.getReceiptClass())) &&
        		(this.getReceiptTime() == null ? other.getReceiptTime() == null : this.getReceiptTime().equals(other.getReceiptTime())) &&
        		(this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrganName() == null) ? 0 : getOrganName().hashCode());
        result = prime * result + ((getOrganTaskId() == null) ? 0 : getOrganTaskId().hashCode());
        result = prime * result + ((getOrganId() == null) ? 0 : getOrganId().hashCode());
        result = prime * result + ((getTaskNum() == null) ? 0 : getTaskNum().hashCode());
        result = prime * result + ((getReceiptClass() == null) ? 0 : getReceiptClass().hashCode());
        result = prime * result + ((getReceiptTime() == null) ? 0 : getReceiptTime().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        return result;
    }
	
}