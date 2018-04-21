package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品品种管理模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjProductType extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String name; //品种名称
    private String status; //品种状态
    private String typeCode; //品种标识
    private String userIdcode;//主体用户码
    private String entityIdcode;//主体身份码
	
	
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
	 //品种名称 get
	public String getName(){
        return name;
    }
	//品种名称 set
    public void setName(String name){
        this.name = name;
    }
	 //品种状态 get
	public String getStatus(){
        return status;
    }
	//品种状态 set
    public void setStatus(String status){
        this.status = status;
    }
	 //品种标识 get
	public String getTypeCode(){
        return typeCode;
    }
	//品种状态 set
    public void setTypeCode(String typeCode){
        this.typeCode = typeCode;
    }

    public String getUserIdcode() {
        return userIdcode;
    }

    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }

    public String getEntityIdcode() {
        return entityIdcode;
    }

    public void setEntityIdcode(String entityIdcode) {
        this.entityIdcode = entityIdcode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", entityIdcode=").append(entityIdcode);
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
        TtsScltxxcjProductType other = (TtsScltxxcjProductType) that;
          return 
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        		(this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) &&
        	    (this.getTypeCode() == null ? other.getTypeCode() == null : this.getTypeCode().equals(other.getTypeCode())) &&
        	    (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
        	    (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTypeCode() == null) ? 0 : getTypeCode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        return result;
    }
	
}