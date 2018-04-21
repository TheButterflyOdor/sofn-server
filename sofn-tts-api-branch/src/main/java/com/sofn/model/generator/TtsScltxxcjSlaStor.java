package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 屠宰后产品库存表模型对象
 * @author moon.l
 *
 */
@SuppressWarnings("serial")
public class TtsScltxxcjSlaStor extends BaseModel {


	/*
	* 对应字段
	*/
    //public String id;//主键

    private BigDecimal saleAmount; //销售数量
    private String userIdcode; //主体用户码
    private BigDecimal freezeCount; //冻结数量
    private String entityIdcode; //主体身份码
    private String billStratrus; //单据状态：1表示已合成，0表示可合成
    private Date harvestTime; //合成时间
    private String productName; //产品名称
    private String productUnit; //产品单位
    private String productType; //产品类型
    private String slaughterBatchNo; //屠宰批次号
    private BigDecimal initAmount; //初始数量
    private BigDecimal currentAmount; //当前数量
    private String status; //状态(1未卖出2未卖完3已卖完4已合成)
    private String insideTraceCode; //屠宰内部追溯码
    private String ip; //ip地址
    private String fromTraceCode;//来源追溯码
    private String toTraceCode;//去向追溯码


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
    //销售数量 get
    public BigDecimal getSaleAmount(){
        return saleAmount;
    }
    //销售数量 set
    public void setSaleAmount(BigDecimal saleAmount){
        this.saleAmount = saleAmount;
    }
    //主体用户码 get
    public String getUserIdcode(){
        return userIdcode;
    }
    //主体用户码 set
    public void setUserIdcode(String userIdcode){
        this.userIdcode = userIdcode;
    }
    //冻结数量 get
    public BigDecimal getFreezeCount(){
        return freezeCount;
    }
    //冻结数量 set
    public void setFreezeCount(BigDecimal freezeCount){
        this.freezeCount = freezeCount;
    }
    //主体身份码 get
    public String getEntityIdcode(){
        return entityIdcode;
    }
    //主体身份码 set
    public void setEntityIdcode(String entityIdcode){
        this.entityIdcode = entityIdcode;
    }
    //单据状态：1表示已合成，0表示可合成 get
    public String getBillStratrus(){
        return billStratrus;
    }
    //单据状态：1表示已合成，0表示可合成 set
    public void setBillStratrus(String billStratrus){
        this.billStratrus = billStratrus;
    }
    //合成时间 get
    public Date getHarvestTime(){
        return harvestTime;
    }
    //合成时间 set
    public void setHarvestTime(Date harvestTime){
        this.harvestTime = harvestTime;
    }
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
    //状态(1未卖出2未卖完3已卖完4已合成) get
    public String getStatus(){
        return status;
    }
    //状态(1未卖出2未卖完3已卖完4已合成) set
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

    public String getFromTraceCode() {return fromTraceCode;}
    public void setFromTraceCode(String fromTraceCode) {this.fromTraceCode = fromTraceCode;}

    public String getToTraceCode() {return toTraceCode;}
    public void setToTraceCode(String toTraceCode) {this.toTraceCode = toTraceCode;}


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", freezeCount=").append(freezeCount);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", billStratrus=").append(billStratrus);
        sb.append(", harvestTime=").append(harvestTime);
        sb.append(", productName=").append(productName);
        sb.append(", productUnit=").append(productUnit);
        sb.append(", productType=").append(productType);
        sb.append(", slaughterBatchNo=").append(slaughterBatchNo);
        sb.append(", initAmount=").append(initAmount);
        sb.append(", currentAmount=").append(currentAmount);
        sb.append(", status=").append(status);
        sb.append(", insideTraceCode=").append(insideTraceCode);
        sb.append(", ip=").append(ip);
        sb.append(", fromTraceCode=").append(fromTraceCode);
        sb.append(", toTraceCode=").append(toTraceCode);
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
        TtsScltxxcjSlaStor other = (TtsScltxxcjSlaStor) that;
        return
                (this.getSaleAmount() == null ? other.getSaleAmount() == null : this.getSaleAmount().equals(other.getSaleAmount())) &&
                        (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
                        (this.getFreezeCount() == null ? other.getFreezeCount() == null : this.getFreezeCount().equals(other.getFreezeCount())) &&
                        (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
                        (this.getBillStratrus() == null ? other.getBillStratrus() == null : this.getBillStratrus().equals(other.getBillStratrus())) &&
                        (this.getHarvestTime() == null ? other.getHarvestTime() == null : this.getHarvestTime().equals(other.getHarvestTime())) &&
                        (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
                        (this.getProductUnit() == null ? other.getProductUnit() == null : this.getProductUnit().equals(other.getProductUnit())) &&
                        (this.getProductType() == null ? other.getProductType() == null : this.getProductType().equals(other.getProductType())) &&
                        (this.getSlaughterBatchNo() == null ? other.getSlaughterBatchNo() == null : this.getSlaughterBatchNo().equals(other.getSlaughterBatchNo())) &&
                        (this.getInitAmount() == null ? other.getInitAmount() == null : this.getInitAmount().equals(other.getInitAmount())) &&
                        (this.getCurrentAmount() == null ? other.getCurrentAmount() == null : this.getCurrentAmount().equals(other.getCurrentAmount())) &&
                        (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
                        (this.getInsideTraceCode() == null ? other.getInsideTraceCode() == null : this.getInsideTraceCode().equals(other.getInsideTraceCode())) &&
                        (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp())) &&
                        (this.getFromTraceCode() == null ? other.getFromTraceCode() == null : this.getFromTraceCode().equals(other.getFromTraceCode())) &&
                        (this.getToTraceCode() == null ? other.getToTraceCode() == null : this.getToTraceCode().equals(other.getToTraceCode())) &&
                        (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSaleAmount() == null) ? 0 : getSaleAmount().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getFreezeCount() == null) ? 0 : getFreezeCount().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getBillStratrus() == null) ? 0 : getBillStratrus().hashCode());
        result = prime * result + ((getHarvestTime() == null) ? 0 : getHarvestTime().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductUnit() == null) ? 0 : getProductUnit().hashCode());
        result = prime * result + ((getProductType() == null) ? 0 : getProductType().hashCode());
        result = prime * result + ((getSlaughterBatchNo() == null) ? 0 : getSlaughterBatchNo().hashCode());
        result = prime * result + ((getInitAmount() == null) ? 0 : getInitAmount().hashCode());
        result = prime * result + ((getCurrentAmount() == null) ? 0 : getCurrentAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getInsideTraceCode() == null) ? 0 : getInsideTraceCode().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getFromTraceCode() == null) ? 0 : getFromTraceCode().hashCode());
        result = prime * result + ((getToTraceCode() == null) ? 0 : getToTraceCode().hashCode());
        return result;
    }

}