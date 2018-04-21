package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 产品管理模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjProduct extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String industry; //所属行业
    private String name; //名称
    private String alias;//别名
    private String type; //类型
    private String typeName;// 类型名称
    private String model; //规格型号
    private String picture; //图片
    private String status; //状态
    private String selfCheck; //自检
    private String authentication; //认证
    private String ip; //当前设备IP地址
    private String userIdcode; //主体用户码
    private String entityIdcode; //主体身份码
    private String productCode; //产品代码
    private String slaughterStatus; //屠宰标识
    
    /*
    *其余字段
    */
	 //所属行业 get
	public String getIndustry(){
        return industry;
    }
	//所属行业 set
    public void setIndustry(String industry){
        this.industry = industry;
    }
	 //名称 get
	public String getName(){
        return name;
    }
	//名称 set
    public void setName(String name){
        this.name = name;
    }
	 //类型 get
	public String getType(){
        return type;
    }
	//类型 set
    public void setType(String type){
        this.type = type;
    }
	 //规格型号 get
	public String getModel(){
        return model;
    }
	//规格型号 set
    public void setModel(String model){
        this.model = model;
    }
	 //图片 get
	public String getPicture(){
        return picture;
    }
	//图片 set
    public void setPicture(String picture){
        this.picture = picture;
    }
	 //状态 get
	public String getStatus(){
        return status;
    }
	//状态 set
    public void setStatus(String status){
        this.status = status;
    }
	 //自检 get
	public String getSelfCheck(){
        return selfCheck;
    }
	//自检 set
    public void setSelfCheck(String selfCheck){
        this.selfCheck = selfCheck;
    }
	 //认证 get
	public String getAuthentication(){
        return authentication;
    }
	//认证 set
    public void setAuthentication(String authentication){
        this.authentication = authentication;
    }
	 //当前设备IP地址 get
	public String getIp(){
        return ip;
    }
	//当前设备IP地址 set
    public void setIp(String ip){
        this.ip = ip;
    }
    //主体用户码
    public String getUserIdcode() {
        return userIdcode;
    }
    //主体用户码
    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }
    //主体身份码
    public String getEntityIdcode() {
        return entityIdcode;
    }
    //主体身份码
    public void setEntityIdcode(String entityIdcode) {
        this.entityIdcode = entityIdcode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSlaughterStatus() {
        return slaughterStatus;
    }
    //屠宰标识
    public void setSlaughterStatus(String slaughterStatus) {
        this.slaughterStatus = slaughterStatus;
    }

    //别名
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", industry=").append(industry);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", model=").append(model);
        sb.append(", picture=").append(picture);
        sb.append(", status=").append(status);
        sb.append(", selfCheck=").append(selfCheck);
        sb.append(", authentication=").append(authentication);
        sb.append(", ip=").append(ip);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", productCode=").append(productCode);
        sb.append(", typeName=").append(typeName);
        sb.append(", slaughterStatus=").append(slaughterStatus);
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
        TtsScltxxcjProduct other = (TtsScltxxcjProduct) that;
          return 
        		(this.getIndustry() == null ? other.getIndustry() == null : this.getIndustry().equals(other.getIndustry())) &&
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        		(this.getType() == null ? other.getType() == null : this.getType().equals(other.getType())) &&
        		(this.getModel() == null ? other.getModel() == null : this.getModel().equals(other.getModel())) &&
        		(this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture())) &&
        		(this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
        		(this.getSelfCheck() == null ? other.getSelfCheck() == null : this.getSelfCheck().equals(other.getSelfCheck())) &&
        		(this.getAuthentication() == null ? other.getAuthentication() == null : this.getAuthentication().equals(other.getAuthentication())) &&
        		(this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) &&
         (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
                 (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
                 (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode())) &&
                 (this.getTypeName() == null ? other.getTypeName() == null : this.getTypeName().equals(other.getTypeName()))&&
                 (this.getSlaughterStatus() == null ? other.getSlaughterStatus() == null : this.getSlaughterStatus().equals(other.getSlaughterStatus())) &&
                        (this.getAlias() == null ? other.getAlias() == null : this.getAlias().equals(other.getAlias()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIndustry() == null) ? 0 : getIndustry().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getModel() == null) ? 0 : getModel().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getSelfCheck() == null) ? 0 : getSelfCheck().hashCode());
        result = prime * result + ((getAuthentication() == null) ? 0 : getAuthentication().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getTypeName() == null) ? 0 : getTypeName().hashCode());
        result = prime * result + ((getSlaughterStatus() == null) ? 0 : getSlaughterStatus().hashCode());
        result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
        return result;
    }
	
}