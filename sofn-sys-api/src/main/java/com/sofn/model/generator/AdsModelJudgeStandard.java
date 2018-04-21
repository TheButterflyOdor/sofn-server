package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 模型_判定标准模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsModelJudgeStandard extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	public String name; //标准名称
	public String filePath; //文件路径
	public String rn;   //序号
    public String judge_ids;    //批处理id
	
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
	 //标准名称 get
	public String getName(){
        return name;
    }
	//标准名称 set
    public void setName(String name){
        this.name = name;
    }
	 //文件路径 get
	public String getFilePath(){
        return filePath;
    }
	//文件路径 set
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getJudge_ids() {
        return judge_ids;
    }

    public void setJudge_ids(String judge_ids) {
        this.judge_ids = judge_ids;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", filePath=").append(filePath);
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
        AdsModelJudgeStandard other = (AdsModelJudgeStandard) that;
          return 
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        		(this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        return result;
    }
	
}