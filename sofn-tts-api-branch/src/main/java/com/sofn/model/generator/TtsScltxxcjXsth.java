package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售退回模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjXsth extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String xsjlid; //销售ID
    private String productName; //产品名称
    private String productId; //产品ID
    private String productIndustry; //产品行业
    private String productSort; //产品分类
    private BigDecimal saleAmount; //销售数量
    private Date saleTime; //销售时间
    private String saleUser; //销售人
    private String productXspc; //产品销售批次
    private String productXspcSl; //产品销售数量
    private String buyComUserId; //客户ID
    private String customerEntityIdcode; //客户身份码
    private String customerUserIdcode; //客户用户码
    private String entityIdcode; //主体身份码
    private String userIdcode; //主体用户码
    private String xsthyy; //退回原因
    //产品来源追溯（实际存入主体身份码）
    private String sourceEntity;

    public String getSourceEntity() { return sourceEntity; }

    public void setSourceEntity(String sourceEntity) { this.sourceEntity = sourceEntity; }
	
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
	 //销售ID get
	public String getXsjlid(){
        return xsjlid;
    }
	//销售ID set
    public void setXsjlid(String xsjlid){
        this.xsjlid = xsjlid;
    }
	 //产品名称 get
	public String getProductName(){
        return productName;
    }
	//产品名称 set
    public void setProductName(String productName){
        this.productName = productName;
    }
	 //产品ID get
	public String getProductId(){
        return productId;
    }
	//产品ID set
    public void setProductId(String productId){
        this.productId = productId;
    }
	 //产品行业 get
	public String getProductIndustry(){
        return productIndustry;
    }
	//产品行业 set
    public void setProductIndustry(String productIndustry){
        this.productIndustry = productIndustry;
    }
	 //产品分类 get
	public String getProductSort(){
        return productSort;
    }
	//产品分类 set
    public void setProductSort(String productSort){
        this.productSort = productSort;
    }
	 //销售数量 get
	public BigDecimal getSaleAmount(){
        return saleAmount;
    }
	//销售数量 set
    public void setSaleAmount(BigDecimal saleAmount){
        this.saleAmount = saleAmount;
    }
	 //销售时间 get
	public Date getSaleTime(){
        return saleTime;
    }
	//销售时间 set
    public void setSaleTime(Date saleTime){
        this.saleTime = saleTime;
    }
	 //销售人 get
	public String getSaleUser(){
        return saleUser;
    }
	//销售人 set
    public void setSaleUser(String saleUser){
        this.saleUser = saleUser;
    }
	 //产品销售批次 get
	public String getProductXspc(){
        return productXspc;
    }
	//产品销售批次 set
    public void setProductXspc(String productXspc){
        this.productXspc = productXspc;
    }
	 //产品销售数量 get
	public String getProductXspcSl(){
        return productXspcSl;
    }
	//产品销售数量 set
    public void setProductXspcSl(String productXspcSl){
        this.productXspcSl = productXspcSl;
    }
	 //客户ID get
	public String getBuyComUserId(){
        return buyComUserId;
    }
	//客户ID set
    public void setBuyComUserId(String buyComUserId){
        this.buyComUserId = buyComUserId;
    }
	 //客户身份码 get
	public String getCustomerEntityIdcode(){
        return customerEntityIdcode;
    }
	//客户身份码 set
    public void setCustomerEntityIdcode(String customerEntityIdcode){
        this.customerEntityIdcode = customerEntityIdcode;
    }
    //客户用户码 get
	public String getCustomerUserIdcode(){
        return customerUserIdcode;
    }
	//客户用户码 set
    public void setCustomerUserIdcode(String customerUserIdcode){
        this.customerUserIdcode = customerUserIdcode;
    }
	 //主体身份码 get
	public String getEntityIdcode(){
        return entityIdcode;
    }
	//主体身份码 set
    public void setEntityIdcode(String entityIdcode){
        this.entityIdcode = entityIdcode;
    }
	 //主体用户码 get
	public String getUserIdcode(){
        return userIdcode;
    }
	//主体用户码 set
    public void setUserIdcode(String userIdcode){
        this.userIdcode = userIdcode;
    }
	 //退回原因 get
	public String getXsthyy(){
        return xsthyy;
    }
	//退回原因 set
    public void setXsthyy(String xsthyy){
        this.xsthyy = xsthyy;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", xsjlid=").append(xsjlid);
        sb.append(", productName=").append(productName);
        sb.append(", productId=").append(productId);
        sb.append(", productIndustry=").append(productIndustry);
        sb.append(", productSort=").append(productSort);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", saleUser=").append(saleUser);
        sb.append(", productXspc=").append(productXspc);
        sb.append(", productXspcSl=").append(productXspcSl);
        sb.append(", buyComUserId=").append(buyComUserId);
        sb.append(", customerEntityIdcode=").append(customerEntityIdcode);
        sb.append(", customerUserIdcode=").append(customerUserIdcode);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", xsthyy=").append(xsthyy);
        sb.append(", sourceEntity=").append(sourceEntity);
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
        TtsScltxxcjXsth other = (TtsScltxxcjXsth) that;
          return 
        		(this.getXsjlid() == null ? other.getXsjlid() == null : this.getXsjlid().equals(other.getXsjlid())) &&
        		(this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
        		(this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
        		(this.getProductIndustry() == null ? other.getProductIndustry() == null : this.getProductIndustry().equals(other.getProductIndustry())) &&
        		(this.getProductSort() == null ? other.getProductSort() == null : this.getProductSort().equals(other.getProductSort())) &&
        		(this.getSaleAmount() == null ? other.getSaleAmount() == null : this.getSaleAmount().equals(other.getSaleAmount())) &&
        		(this.getSaleTime() == null ? other.getSaleTime() == null : this.getSaleTime().equals(other.getSaleTime())) &&
        		(this.getSaleUser() == null ? other.getSaleUser() == null : this.getSaleUser().equals(other.getSaleUser())) &&
        		(this.getProductXspc() == null ? other.getProductXspc() == null : this.getProductXspc().equals(other.getProductXspc())) &&
        		(this.getProductXspcSl() == null ? other.getProductXspcSl() == null : this.getProductXspcSl().equals(other.getProductXspcSl())) &&
        		(this.getBuyComUserId() == null ? other.getBuyComUserId() == null : this.getBuyComUserId().equals(other.getBuyComUserId())) &&
        		(this.getCustomerEntityIdcode() == null ? other.getCustomerEntityIdcode() == null : this.getCustomerEntityIdcode().equals(other.getCustomerEntityIdcode())) &&
        		(this.getCustomerUserIdcode() == null ? other.getCustomerUserIdcode() == null : this.getCustomerUserIdcode().equals(other.getCustomerUserIdcode())) &&
        		(this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
        		(this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
        		(this.getXsthyy() == null ? other.getXsthyy() == null : this.getXsthyy().equals(other.getXsthyy())) &&
                (this.getSourceEntity() == null ? other.getSourceEntity() == null : this.getSourceEntity().equals(other.getSourceEntity())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getXsjlid() == null) ? 0 : getXsjlid().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductIndustry() == null) ? 0 : getProductIndustry().hashCode());
        result = prime * result + ((getProductSort() == null) ? 0 : getProductSort().hashCode());
        result = prime * result + ((getSaleAmount() == null) ? 0 : getSaleAmount().hashCode());
        result = prime * result + ((getSaleTime() == null) ? 0 : getSaleTime().hashCode());
        result = prime * result + ((getSaleUser() == null) ? 0 : getSaleUser().hashCode());
        result = prime * result + ((getProductXspc() == null) ? 0 : getProductXspc().hashCode());
        result = prime * result + ((getProductXspcSl() == null) ? 0 : getProductXspcSl().hashCode());
        result = prime * result + ((getBuyComUserId() == null) ? 0 : getBuyComUserId().hashCode());
        result = prime * result + ((getCustomerEntityIdcode() == null) ? 0 : getCustomerEntityIdcode().hashCode());
        result = prime * result + ((getCustomerUserIdcode() == null) ? 0 : getCustomerUserIdcode().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getXsthyy() == null) ? 0 : getXsthyy().hashCode());
        result = prime * result + ((getSourceEntity() == null) ? 0 : getSourceEntity().hashCode());
        return result;
    }
	
}