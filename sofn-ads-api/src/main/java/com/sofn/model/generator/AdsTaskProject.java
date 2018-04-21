package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 监测任务_检测项目模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsTaskProject extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	private String monitorTaskId; //监测任务KEY
	private String checkProject; //检测项目
	private BigDecimal judgeNum; //判定标准值
	
	
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
	 //监测任务KEY get
	public String getMonitorTaskId(){
        return monitorTaskId;
    }
	//监测任务KEY set
    public void setMonitorTaskId(String monitorTaskId){
        this.monitorTaskId = monitorTaskId;
    }
	 //检测项目 get
	public String getCheckProject(){
        return checkProject;
    }
	//检测项目 set
    public void setCheckProject(String checkProject){
        this.checkProject = checkProject;
    }
	 //判定标准值 get
	public BigDecimal getJudgeNum(){
        return judgeNum;
    }
	//判定标准值 set
    public void setJudgeNum(BigDecimal judgeNum){
        this.judgeNum = judgeNum;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", monitorTaskId=").append(monitorTaskId);
        sb.append(", checkProject=").append(checkProject);
        sb.append(", judgeNum=").append(judgeNum);
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
        AdsTaskProject other = (AdsTaskProject) that;
          return 
        		(this.getMonitorTaskId() == null ? other.getMonitorTaskId() == null : this.getMonitorTaskId().equals(other.getMonitorTaskId())) &&
        		(this.getCheckProject() == null ? other.getCheckProject() == null : this.getCheckProject().equals(other.getCheckProject())) &&
        		(this.getJudgeNum() == null ? other.getJudgeNum() == null : this.getJudgeNum().equals(other.getJudgeNum())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMonitorTaskId() == null) ? 0 : getMonitorTaskId().hashCode());
        result = prime * result + ((getCheckProject() == null) ? 0 : getCheckProject().hashCode());
        result = prime * result + ((getJudgeNum() == null) ? 0 : getJudgeNum().hashCode());
        return result;
    }
	
}