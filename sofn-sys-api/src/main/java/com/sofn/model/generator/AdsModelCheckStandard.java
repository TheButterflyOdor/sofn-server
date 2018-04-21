package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;

/**
 * 模型_检测标准模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsModelCheckStandard extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	public String name; //标准名称
	public BigDecimal value; //检测标准上限值
    public int rn;  //序号
    public String ids;  //批处理id
	
	
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
	 //检测标准上限值 get
	public BigDecimal getValue(){
        return value;
    }
	//检测标准上限值 set
    public void setValue(BigDecimal value){
        this.value = value;
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
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
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
        AdsModelCheckStandard other = (AdsModelCheckStandard) that;
          return 
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        		(this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        return result;
    }
	
}