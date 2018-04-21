package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品批次合成模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjCppchc extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String hcid; //合成ID
    private String productId; //产品ID
    private String productName; //产品名称
    private String mediCheck; //质检情况
    private String mediResult; //质检结论
    private BigDecimal productAmount; //收获数量
    private BigDecimal storeCount; //库存数量
    private Date harvestTime; //收获时间
    private String productPc; //合成前产品批次码
    private String productPcNew; //合成后产品批次码
    private String harvestBaseid; //收获基地id
    private String harvestBasename; //收获基地名称
    private String productInnerKey; //内部追溯批次
    private String productSource; //来源标识
    private String status;//状态
    private String billStratrus;//单据状态
    //new 20161109
    private String fromzsm;//来源追溯码
    private String hczsm;//合成追溯码

    private String harvestUnit;//合成追溯码

    //短码 20170504
    private String shortCode;
    //动物检疫合格证号
    private String proofNumber;
    private String fromShortCode;
    //产品来源追溯（实际存入主体身份码）
    private String sourceEntity;

    public String getSourceEntity() { return sourceEntity; }

    public void setSourceEntity(String sourceEntity) { this.sourceEntity = sourceEntity; }

    public String getFromShortCode() {
        return fromShortCode;
    }

    public void setFromShortCode(String fromShortCode) {
        this.fromShortCode = fromShortCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getProofNumber() {
        return proofNumber;
    }

    public void setProofNumber(String proofNumber) {
        this.proofNumber = proofNumber;
    }


	
	
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
	 //合成ID get
	public String getHcid(){
        return hcid;
    }
	//合成ID set
    public void setHcid(String hcid){
        this.hcid = hcid;
    }
	 //产品ID get
	public String getProductId(){
        return productId;
    }
	//产品ID set
    public void setProductId(String productId){
        this.productId = productId;
    }
	 //产品名称 get
	public String getProductName(){
        return productName;
    }
	//产品名称 set
    public void setProductName(String productName){
        this.productName = productName;
    }
	 //质检情况 get
	public String getMediCheck(){
        return mediCheck;
    }
	//质检情况 set
    public void setMediCheck(String mediCheck){
        this.mediCheck = mediCheck;
    }
	 //质检结论 get
	public String getMediResult(){
        return mediResult;
    }
	//质检结论 set
    public void setMediResult(String mediResult){
        this.mediResult = mediResult;
    }
	 //收获数量 get
	public BigDecimal getProductAmount(){
        return productAmount;
    }
	//收获数量 set
    public void setProductAmount(BigDecimal productAmount){
        this.productAmount = productAmount;
    }
	 //库存数量 get
	public BigDecimal getStoreCount(){
        return storeCount;
    }
	//库存数量 set
    public void setStoreCount(BigDecimal storeCount){
        this.storeCount = storeCount;
    }
	 //收获时间 get
	public Date getHarvestTime(){
        return harvestTime;
    }
	//收获时间 set
    public void setHarvestTime(Date harvestTime){
        this.harvestTime = harvestTime;
    }
	 //合成前产品批次码 get
	public String getProductPc(){
        return productPc;
    }
	//合成前产品批次码 set
    public void setProductPc(String productPc){
        this.productPc = productPc;
    }
	 //合成后产品批次码 get
	public String getProductPcNew(){
        return productPcNew;
    }
	//合成后产品批次码 set
    public void setProductPcNew(String productPcNew){
        this.productPcNew = productPcNew;
    }
	 //收获基地id get
	public String getHarvestBaseid(){
        return harvestBaseid;
    }
	//收获基地id set
    public void setHarvestBaseid(String harvestBaseid){
        this.harvestBaseid = harvestBaseid;
    }
	 //收获基地名称 get
	public String getHarvestBasename(){
        return harvestBasename;
    }
	//收获基地名称 set
    public void setHarvestBasename(String harvestBasename){
        this.harvestBasename = harvestBasename;
    }
	 //内部追溯批次 get
	public String getProductInnerKey(){
        return productInnerKey;
    }
	//内部追溯批次 set
    public void setProductInnerKey(String productInnerKey){
        this.productInnerKey = productInnerKey;
    }
	 //来源标识 get
	public String getProductSource(){
        return productSource;
    }
	//来源标识 set
    public void setProductSource(String productSource){
        this.productSource = productSource;
    }

    //当前状态 get
    public String getStatus(){
        return status;
    }
    //当前状态 set
    public void setStatus(String status){
        this.status = status;
    }
    //单据状态 get
    public String getBillStratrus(){
        return billStratrus;
    }
    //单据状态 set
    public void setBillStratrus(String billStratrus){
        this.billStratrus = billStratrus;
    }

    //new 2016.11.09


    public String getFromzsm() {
        return fromzsm;
    }

    public String getHczsm() {
        return hczsm;
    }

    public void setFromzsm(String fromzsm) {
        this.fromzsm = fromzsm;
    }

    public void setHczsm(String hczsm) {
        this.hczsm = hczsm;
    }
    public String getHarvestUnit() {
        return harvestUnit;
    }

    public void setHarvestUnit(String harvestUnit) {
        this.harvestUnit = harvestUnit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hcid=").append(hcid);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", mediCheck=").append(mediCheck);
        sb.append(", mediResult=").append(mediResult);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", storeCount=").append(storeCount);
        sb.append(", harvestTime=").append(harvestTime);
        sb.append(", productPc=").append(productPc);
        sb.append(", productPcNew=").append(productPcNew);
        sb.append(", harvestBaseid=").append(harvestBaseid);
        sb.append(", harvestBasename=").append(harvestBasename);
        sb.append(", productInnerKey=").append(productInnerKey);
        sb.append(", productSource=").append(productSource);
        sb.append(", status=").append(status);
        sb.append(", billStratrus=").append(billStratrus);
        sb.append(", fromzsm=").append(fromzsm);
        sb.append(", hczsm=").append(hczsm);
        sb.append(", harvestUnit=").append(harvestUnit);
        sb.append(", shortCode=").append(shortCode);
        sb.append(", proofNumber=").append(proofNumber);
        sb.append(", fromShortCode=").append(fromShortCode);
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
        TtsScltxxcjCppchc other = (TtsScltxxcjCppchc) that;
          return 
        		(this.getHcid() == null ? other.getHcid() == null : this.getHcid().equals(other.getHcid())) &&
        		(this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
        		(this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
        		(this.getMediCheck() == null ? other.getMediCheck() == null : this.getMediCheck().equals(other.getMediCheck())) &&
        		(this.getMediResult() == null ? other.getMediResult() == null : this.getMediResult().equals(other.getMediResult())) &&
        		(this.getProductAmount() == null ? other.getProductAmount() == null : this.getProductAmount().equals(other.getProductAmount())) &&
        		(this.getStoreCount() == null ? other.getStoreCount() == null : this.getStoreCount().equals(other.getStoreCount())) &&
        		(this.getHarvestTime() == null ? other.getHarvestTime() == null : this.getHarvestTime().equals(other.getHarvestTime())) &&
        		(this.getProductPc() == null ? other.getProductPc() == null : this.getProductPc().equals(other.getProductPc())) &&
        		(this.getProductPcNew() == null ? other.getProductPcNew() == null : this.getProductPcNew().equals(other.getProductPcNew())) &&
        		(this.getHarvestBaseid() == null ? other.getHarvestBaseid() == null : this.getHarvestBaseid().equals(other.getHarvestBaseid())) &&
        		(this.getHarvestBasename() == null ? other.getHarvestBasename() == null : this.getHarvestBasename().equals(other.getHarvestBasename())) &&
        		(this.getProductInnerKey() == null ? other.getProductInnerKey() == null : this.getProductInnerKey().equals(other.getProductInnerKey())) &&
        		(this.getProductSource() == null ? other.getProductSource() == null : this.getProductSource().equals(other.getProductSource())) &&
                (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
                (this.getBillStratrus() == null ? other.getBillStratrus() == null : this.getBillStratrus().equals(other.getBillStratrus())) &&
                (this.getFromzsm() == null ? other.getFromzsm() == null : this.getFromzsm().equals(other.getFromzsm())) &&
                (this.getHczsm() == null ? other.getHczsm() == null : this.getHczsm().equals(other.getHczsm())) &&
                (this.getShortCode() == null ? other.getShortCode() == null : this.getShortCode().equals(other.getShortCode())) &&
                (this.getProofNumber() == null ? other.getProofNumber() == null : this.getProofNumber().equals(other.getProofNumber())) &&
                (this.getHarvestUnit() == null ? other.getHarvestUnit() == null : this.getHarvestUnit().equals(other.getHarvestUnit())) &&
                (this.getFromShortCode() == null ? other.getFromShortCode() == null : this.getFromShortCode().equals(other.getFromShortCode())) &&
                (this.getSourceEntity() == null ? other.getSourceEntity() == null : this.getSourceEntity().equals(other.getSourceEntity())) &&
                (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getHcid() == null) ? 0 : getHcid().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getMediCheck() == null) ? 0 : getMediCheck().hashCode());
        result = prime * result + ((getMediResult() == null) ? 0 : getMediResult().hashCode());
        result = prime * result + ((getProductAmount() == null) ? 0 : getProductAmount().hashCode());
        result = prime * result + ((getStoreCount() == null) ? 0 : getStoreCount().hashCode());
        result = prime * result + ((getHarvestTime() == null) ? 0 : getHarvestTime().hashCode());
        result = prime * result + ((getProductPc() == null) ? 0 : getProductPc().hashCode());
        result = prime * result + ((getProductPcNew() == null) ? 0 : getProductPcNew().hashCode());
        result = prime * result + ((getHarvestBaseid() == null) ? 0 : getHarvestBaseid().hashCode());
        result = prime * result + ((getHarvestBasename() == null) ? 0 : getHarvestBasename().hashCode());
        result = prime * result + ((getProductInnerKey() == null) ? 0 : getProductInnerKey().hashCode());
        result = prime * result + ((getProductSource() == null) ? 0 : getProductSource().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBillStratrus() == null) ? 0 : getBillStratrus().hashCode());
        result = prime * result + ((getFromzsm() == null) ? 0 : getFromzsm().hashCode());
        result = prime * result + ((getHczsm() == null) ? 0 : getHczsm().hashCode());
        result = prime * result + ((getProofNumber() == null) ? 0 : getProofNumber().hashCode());
        result = prime * result + ((getShortCode() == null) ? 0 : getShortCode().hashCode());
        result = prime * result + ((getHarvestUnit() == null) ? 0 : getHarvestUnit().hashCode());
        result = prime * result + ((getFromShortCode() == null) ? 0 : getFromShortCode().hashCode());
        result = prime * result + ((getSourceEntity() == null) ? 0 : getSourceEntity().hashCode());
        return result;
    }
	
}