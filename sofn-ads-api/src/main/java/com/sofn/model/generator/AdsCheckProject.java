package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 检测项目模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsCheckProject extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	private String checkProject; //检测项目名称
	private String checkNum; //检出值
	private String judgeNum; //判定值
	private String result; //抽检结果
	private String checkInfoId; //检测信息Key
	
	
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
	 //检测项目名称 get
	public String getCheckProject(){
        return checkProject;
    }
	//检测项目名称 set
    public void setCheckProject(String checkProject){
        this.checkProject = checkProject;
    }
	 //检出值 get
	public String getCheckNum(){
        return checkNum;
    }
	//检出值 set
    public void setCheckNum(String checkNum){
        this.checkNum = checkNum;
    }
	 //判定值 get
	public String getJudgeNum(){
        return judgeNum;
    }
	//判定值 set
    public void setJudgeNum(String judgeNum){
        this.judgeNum = judgeNum;
    }
	 //抽检结果 get
	public String getResult(){
        return result;
    }
	//抽检结果 set
    public void setResult(String result){
        this.result = result;
    }
	 //检测信息Key get
	public String getCheckInfoId(){
        return checkInfoId;
    }
	//检测信息Key set
    public void setCheckInfoId(String checkInfoId){
        this.checkInfoId = checkInfoId;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkProject=").append(checkProject);
        sb.append(", checkNum=").append(checkNum);
        sb.append(", judgeNum=").append(judgeNum);
        sb.append(", result=").append(result);
        sb.append(", checkInfoId=").append(checkInfoId);
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
        AdsCheckProject other = (AdsCheckProject) that;
          return 
        		(this.getCheckProject() == null ? other.getCheckProject() == null : this.getCheckProject().equals(other.getCheckProject())) &&
        		(this.getCheckNum() == null ? other.getCheckNum() == null : this.getCheckNum().equals(other.getCheckNum())) &&
        		(this.getJudgeNum() == null ? other.getJudgeNum() == null : this.getJudgeNum().equals(other.getJudgeNum())) &&
        		(this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult())) &&
        		(this.getCheckInfoId() == null ? other.getCheckInfoId() == null : this.getCheckInfoId().equals(other.getCheckInfoId())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCheckProject() == null) ? 0 : getCheckProject().hashCode());
        result = prime * result + ((getCheckNum() == null) ? 0 : getCheckNum().hashCode());
        result = prime * result + ((getJudgeNum() == null) ? 0 : getJudgeNum().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getCheckInfoId() == null) ? 0 : getCheckInfoId().hashCode());
        return result;
    }
	
}