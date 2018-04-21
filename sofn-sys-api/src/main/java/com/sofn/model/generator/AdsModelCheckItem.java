package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 模型_检测项目模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsModelCheckItem extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

	public String standardId; //检测标准id
    public String standardName; //标准名称
    public float standardValue; //标准上限值
	public String name; //项目名称
	public int rn;  //序号
	public String item_ids; //批处理id

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
	 //检测标准id get
	public String getStandardId(){
        return standardId;
    }
	//检测标准id set
    public void setStandardId(String standardId){
        this.standardId = standardId;
    }
	 //项目名称 get
	public String getName(){
        return name;
    }
	//项目名称 set
    public void setName(String name){
        this.name = name;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getItem_ids() {
        return item_ids;
    }

    public void setItem_ids(String item_ids) {
        this.item_ids = item_ids;
    }

    public float getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(float standardValue) {
        this.standardValue = standardValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", standardId=").append(standardId);
        sb.append(", name=").append(name);
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
        AdsModelCheckItem other = (AdsModelCheckItem) that;
          return 
        		(this.getStandardId() == null ? other.getStandardId() == null : this.getStandardId().equals(other.getStandardId())) &&
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStandardId() == null) ? 0 : getStandardId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }
	
}