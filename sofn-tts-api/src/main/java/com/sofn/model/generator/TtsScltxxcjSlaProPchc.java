package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 屠宰后产品批次合成模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjSlaProPchc extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String productName; //产品名称
    private String productUnit; //产品单位
    private String productType; //产品类型
    private String slaughterBatchNo; //屠宰批次号
    private BigDecimal initAmount; //初始数量
    private BigDecimal currentAmount; //当前数量
    private String status; //当前状态(1未卖出2未卖完3已卖完4已合成)
    private String insideTraceCode; //屠宰内部追溯码
    private String ip; //ip地址
    private String slaughterBatchNoNew; //新的追溯码
    private String billStratrus; //单据状态
    private String productId; //产品id
    private String productHcId; //产品合成id
	
	
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
	 //产品名称 get
	public String getProductName(){
        return productName;
    }
	//产品名称 set
    public void setProductName(String productName){
        this.productName = productName;
    }
	 //产品单位 get
	public String getProductUnit(){
        return productUnit;
    }
	//产品单位 set
    public void setProductUnit(String productUnit){
        this.productUnit = productUnit;
    }
	 //产品类型 get
	public String getProductType(){
        return productType;
    }
	//产品类型 set
    public void setProductType(String productType){
        this.productType = productType;
    }
	 //屠宰批次号 get
	public String getSlaughterBatchNo(){
        return slaughterBatchNo;
    }
	//屠宰批次号 set
    public void setSlaughterBatchNo(String slaughterBatchNo){
        this.slaughterBatchNo = slaughterBatchNo;
    }
	 //初始数量 get
	public BigDecimal getInitAmount(){
        return initAmount;
    }
	//初始数量 set
    public void setInitAmount(BigDecimal initAmount){
        this.initAmount = initAmount;
    }
	 //当前数量 get
	public BigDecimal getCurrentAmount(){
        return currentAmount;
    }
	//当前数量 set
    public void setCurrentAmount(BigDecimal currentAmount){
        this.currentAmount = currentAmount;
    }
	 //当前状态(1未卖出2未卖完3已卖完4已合成) get
	public String getStatus(){
        return status;
    }
	//当前状态(1未卖出2未卖完3已卖完4已合成) set
    public void setStatus(String status){
        this.status = status;
    }
	 //屠宰内部追溯码 get
	public String getInsideTraceCode(){
        return insideTraceCode;
    }
	//屠宰内部追溯码 set
    public void setInsideTraceCode(String insideTraceCode){
        this.insideTraceCode = insideTraceCode;
    }
	 //ip地址 get
	public String getIp(){
        return ip;
    }
	//ip地址 set
    public void setIp(String ip){
        this.ip = ip;
    }
	 //新的追溯码 get
	public String getSlaughterBatchNoNew(){
        return slaughterBatchNoNew;
    }
	//新的追溯码 set
    public void setSlaughterBatchNoNew(String slaughterBatchNoNew){
        this.slaughterBatchNoNew = slaughterBatchNoNew;
    }
	 //单据状态 get
	public String getBillStratrus(){
        return billStratrus;
    }
	//单据状态 set
    public void setBillStratrus(String billStratrus){
        this.billStratrus = billStratrus;
    }
	 //预留字段3 get
	public String getProductId(){
        return productId;
    }
	//预留字段3 set
    public void setProductId(String productId){
        this.productId = productId;
    }
	 //预留字段4 get
	public String getProductHcId(){
        return productHcId;
    }
	//预留字段4 set
    public void setProductHcId(String productHcId){
        this.productHcId = productHcId;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productName=").append(productName);
        sb.append(", productUnit=").append(productUnit);
        sb.append(", productType=").append(productType);
        sb.append(", slaughterBatchNo=").append(slaughterBatchNo);
        sb.append(", initAmount=").append(initAmount);
        sb.append(", currentAmount=").append(currentAmount);
        sb.append(", status=").append(status);
        sb.append(", insideTraceCode=").append(insideTraceCode);
        sb.append(", ip=").append(ip);
        sb.append(", slaughterBatchNoNew=").append(slaughterBatchNoNew);
        sb.append(", billStratrus=").append(billStratrus);
        sb.append(", productId=").append(productId);
        sb.append(", productHcId=").append(productHcId);
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
        TtsScltxxcjSlaProPchc other = (TtsScltxxcjSlaProPchc) that;
          return 
        		(this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
        		(this.getProductUnit() == null ? other.getProductUnit() == null : this.getProductUnit().equals(other.getProductUnit())) &&
        		(this.getProductType() == null ? other.getProductType() == null : this.getProductType().equals(other.getProductType())) &&
        		(this.getSlaughterBatchNo() == null ? other.getSlaughterBatchNo() == null : this.getSlaughterBatchNo().equals(other.getSlaughterBatchNo())) &&
        		(this.getInitAmount() == null ? other.getInitAmount() == null : this.getInitAmount().equals(other.getInitAmount())) &&
        		(this.getCurrentAmount() == null ? other.getCurrentAmount() == null : this.getCurrentAmount().equals(other.getCurrentAmount())) &&
        		(this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
        		(this.getInsideTraceCode() == null ? other.getInsideTraceCode() == null : this.getInsideTraceCode().equals(other.getInsideTraceCode())) &&
        		(this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp())) &&
        		(this.getSlaughterBatchNoNew() == null ? other.getSlaughterBatchNoNew() == null : this.getSlaughterBatchNoNew().equals(other.getSlaughterBatchNoNew())) &&
        		(this.getBillStratrus() == null ? other.getBillStratrus() == null : this.getBillStratrus().equals(other.getBillStratrus())) &&
        		(this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
        		(this.getProductHcId() == null ? other.getProductHcId() == null : this.getProductHcId().equals(other.getProductHcId())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductUnit() == null) ? 0 : getProductUnit().hashCode());
        result = prime * result + ((getProductType() == null) ? 0 : getProductType().hashCode());
        result = prime * result + ((getSlaughterBatchNo() == null) ? 0 : getSlaughterBatchNo().hashCode());
        result = prime * result + ((getInitAmount() == null) ? 0 : getInitAmount().hashCode());
        result = prime * result + ((getCurrentAmount() == null) ? 0 : getCurrentAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getInsideTraceCode() == null) ? 0 : getInsideTraceCode().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getSlaughterBatchNoNew() == null) ? 0 : getSlaughterBatchNoNew().hashCode());
        result = prime * result + ((getBillStratrus() == null) ? 0 : getBillStratrus().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductHcId() == null) ? 0 : getProductHcId().hashCode());
        return result;
    }
	
}