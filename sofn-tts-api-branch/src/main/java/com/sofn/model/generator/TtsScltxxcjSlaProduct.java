package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 屠宰后产品模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjSlaProduct extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String productName; //屠宰前产品名称
    private String productUnit; //单位
    private String ip; //IP地址
    private String createby; //创建人
    private Date createtime; //创建时间
    private String updateby; //修改人
    private Date updatetime; //修改时间
    private String productSlaughterName; //屠宰后产品名称
	
	
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
	 //屠宰前产品名称 get
	public String getProductName(){
        return productName;
    }
	//屠宰前产品名称 set
    public void setProductName(String productName){
        this.productName = productName;
    }
	 //单位 get
	public String getProductUnit(){
        return productUnit;
    }
	//单位 set
    public void setProductUnit(String productUnit){
        this.productUnit = productUnit;
    }
	 //IP地址 get
	public String getIp(){
        return ip;
    }
	//IP地址 set
    public void setIp(String ip){
        this.ip = ip;
    }
	 //创建人 get
	public String getCreateby(){
        return createby;
    }
	//创建人 set
    public void setCreateby(String createby){
        this.createby = createby;
    }
	 //创建时间 get
	public Date getCreatetime(){
        return createtime;
    }
	//创建时间 set
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }
	 //修改人 get
	public String getUpdateby(){
        return updateby;
    }
	//修改人 set
    public void setUpdateby(String updateby){
        this.updateby = updateby;
    }
	 //修改时间 get
	public Date getUpdatetime(){
        return updatetime;
    }
	//修改时间 set
    public void setUpdatetime(Date updatetime){
        this.updatetime = updatetime;
    }
	 //屠宰后产品名称 get
	public String getProductSlaughterName(){
        return productSlaughterName;
    }
	//屠宰后产品名称 set
    public void setProductSlaughterName(String productSlaughterName){
        this.productSlaughterName = productSlaughterName;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productName=").append(productName);
        sb.append(", productUnit=").append(productUnit);
        sb.append(", ip=").append(ip);
        sb.append(", createby=").append(createby);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateby=").append(updateby);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", productSlaughterName=").append(productSlaughterName);
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
        TtsScltxxcjSlaProduct other = (TtsScltxxcjSlaProduct) that;
          return 
        		(this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
        		(this.getProductUnit() == null ? other.getProductUnit() == null : this.getProductUnit().equals(other.getProductUnit())) &&
        		(this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp())) &&
        		(this.getCreateby() == null ? other.getCreateby() == null : this.getCreateby().equals(other.getCreateby())) &&
        		(this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime())) &&
        		(this.getUpdateby() == null ? other.getUpdateby() == null : this.getUpdateby().equals(other.getUpdateby())) &&
        		(this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime())) &&
        		(this.getProductSlaughterName() == null ? other.getProductSlaughterName() == null : this.getProductSlaughterName().equals(other.getProductSlaughterName())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductUnit() == null) ? 0 : getProductUnit().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCreateby() == null) ? 0 : getCreateby().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdateby() == null) ? 0 : getUpdateby().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getProductSlaughterName() == null) ? 0 : getProductSlaughterName().hashCode());
        return result;
    }
	
}