package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售记录临时数据表模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsTempXsjl extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String productId; //产品ID
    private String productName; //产品名称
    private String productIndustry; //产品所属行业
    private String productSort; //产品分类
    private String productPc; //产品生产管理批次
    private BigDecimal saleAmount; //销售数量
    private String buyComUserId; //购买客户ID
    private BigDecimal productAmount; //收获数量
    private BigDecimal storeCount; //库存数量
    private BigDecimal freezeCount; //冻结数量
    //new
    private String zjcheck;//质检情况

    private String mediCheck;//质检方式
    private String mediResult;//质检情况
    //new 2016.11.04
    private String fromzsm;//来源追溯码


    /**
     * 计量单位Id
     */
    private String unitId;

    private String joinFlag;

    private String harvestUnit;

    //短码 20170504
    private String shortCode;
    //动物检疫合格证号
    private String proofNumber;
    //来源追溯码短码 20170506新增
    private String fromShortCode;
    //去向追溯码短码
    private String toShortCode;
    //入市追溯码短码
    private String traceProofShort;
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

    public String getToShortCode() {
        return toShortCode;
    }

    public void setToShortCode(String toShortCode) {
        this.toShortCode = toShortCode;
    }

    public String getTraceProofShort() {
        return traceProofShort;
    }

    public void setTraceProofShort(String traceProofShort) {
        this.traceProofShort = traceProofShort;
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


    public String getUnitId() {
        return unitId;

    }

    public String getJoinFlag() {
        return joinFlag;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public void setJoinFlag(String joinFlag) {
        this.joinFlag = joinFlag;
    }

    //质检情况
    public void setZjcheck(String zjcheck) {
        this.zjcheck = zjcheck;
    }
    //质检情况
    public String getZjcheck() {
        return zjcheck;
    }
    public String getMediCheck() {
        return mediCheck;
    }

    public void setMediCheck(String mediCheck) {
        this.mediCheck = mediCheck;
    }

    public String getMediResult() {
        return mediResult;
    }

    public void setMediResult(String mediResult) {
        this.mediResult = mediResult;
    }


    public String getFromzsm() {
        return fromzsm;
    }

    public void setFromzsm(String fromzsm) {
        this.fromzsm = fromzsm;
    }

    public String getHarvestUnit() {
        return harvestUnit;
    }

    public void setHarvestUnit(String harvestUnit) {
        this.harvestUnit = harvestUnit;
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
	 //产品所属行业 get
	public String getProductIndustry(){
        return productIndustry;
    }
	//产品所属行业 set
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
	 //产品生产管理批次 get
	public String getProductPc(){
        return productPc;
    }
	//产品生产管理批次 set
    public void setProductPc(String productPc){
        this.productPc = productPc;
    }
	 //销售数量 get
	public BigDecimal getSaleAmount(){
        return saleAmount;
    }
	//销售数量 set
    public void setSaleAmount(BigDecimal saleAmount){
        this.saleAmount = saleAmount;
    }
	 //购买客户ID get
	public String getBuyComUserId(){
        return buyComUserId;
    }
	//购买客户ID set
    public void setBuyComUserId(String buyComUserId){
        this.buyComUserId = buyComUserId;
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
	 //冻结数量 get
	public BigDecimal getFreezeCount(){
        return freezeCount;
    }
	//冻结数量 set
    public void setFreezeCount(BigDecimal freezeCount){
        this.freezeCount = freezeCount;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productIndustry=").append(productIndustry);
        sb.append(", productSort=").append(productSort);
        sb.append(", productPc=").append(productPc);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", buyComUserId=").append(buyComUserId);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", storeCount=").append(storeCount);
        sb.append(", freezeCount=").append(freezeCount);
        sb.append(", zjcheck=").append(zjcheck);
        sb.append(", fromzsm=").append(fromzsm);
        sb.append(", unitId=").append(unitId);
        sb.append(", harvestUnit=").append(harvestUnit);
        sb.append(", mediCheck=").append(mediCheck);
        sb.append(", mediResult=").append(mediResult);
        sb.append(", shortCode=").append(shortCode);
        sb.append(", proofNumber=").append(proofNumber);
        sb.append(", fromShortCode=").append(fromShortCode);
        sb.append(", toShortCode=").append(toShortCode);
        sb.append(", traceProofShort=").append(traceProofShort);
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
        TtsTempXsjl other = (TtsTempXsjl) that;
          return 
        		(this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
        		(this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
        		(this.getProductIndustry() == null ? other.getProductIndustry() == null : this.getProductIndustry().equals(other.getProductIndustry())) &&
        		(this.getProductSort() == null ? other.getProductSort() == null : this.getProductSort().equals(other.getProductSort())) &&
        		(this.getProductPc() == null ? other.getProductPc() == null : this.getProductPc().equals(other.getProductPc())) &&
        		(this.getSaleAmount() == null ? other.getSaleAmount() == null : this.getSaleAmount().equals(other.getSaleAmount())) &&
        		(this.getBuyComUserId() == null ? other.getBuyComUserId() == null : this.getBuyComUserId().equals(other.getBuyComUserId())) &&
        		(this.getProductAmount() == null ? other.getProductAmount() == null : this.getProductAmount().equals(other.getProductAmount())) &&
        		(this.getStoreCount() == null ? other.getStoreCount() == null : this.getStoreCount().equals(other.getStoreCount())) &&
        		(this.getFreezeCount() == null ? other.getFreezeCount() == null : this.getFreezeCount().equals(other.getFreezeCount())) &&
        		(this.getZjcheck() == null ? other.getZjcheck() == null : this.getZjcheck().equals(other.getZjcheck())) &&
                (this.getFromzsm() == null ? other.getFromzsm() == null : this.getFromzsm().equals(other.getFromzsm())) &&
                (this.getUnitId() == null ? other.getUnitId() == null : this.getUnitId().equals(other.getUnitId())) &&
                (this.getHarvestUnit() == null ? other.getHarvestUnit() == null : this.getHarvestUnit().equals(other.getHarvestUnit())) &&
                (this.getMediResult() == null ? other.getMediResult() == null : this.getMediResult().equals(other.getMediResult())) &&
                (this.getMediCheck() == null ? other.getMediCheck() == null : this.getMediCheck().equals(other.getMediCheck())) &&
                (this.getShortCode() == null ? other.getShortCode() == null : this.getShortCode().equals(other.getShortCode())) &&
                (this.getProofNumber() == null ? other.getProofNumber() == null : this.getProofNumber().equals(other.getProofNumber())) &&
                (this.getFromShortCode() == null ? other.getFromShortCode() == null : this.getFromShortCode().equals(other.getFromShortCode())) &&
                (this.getToShortCode() == null ? other.getToShortCode() == null : this.getToShortCode().equals(other.getToShortCode())) &&
                (this.getTraceProofShort() == null ? other.getTraceProofShort() == null : this.getTraceProofShort().equals(other.getTraceProofShort())) &&
                (this.getSourceEntity() == null ? other.getSourceEntity() == null : this.getSourceEntity().equals(other.getSourceEntity())) &&
                (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductIndustry() == null) ? 0 : getProductIndustry().hashCode());
        result = prime * result + ((getProductSort() == null) ? 0 : getProductSort().hashCode());
        result = prime * result + ((getProductPc() == null) ? 0 : getProductPc().hashCode());
        result = prime * result + ((getSaleAmount() == null) ? 0 : getSaleAmount().hashCode());
        result = prime * result + ((getBuyComUserId() == null) ? 0 : getBuyComUserId().hashCode());
        result = prime * result + ((getProductAmount() == null) ? 0 : getProductAmount().hashCode());
        result = prime * result + ((getStoreCount() == null) ? 0 : getStoreCount().hashCode());
        result = prime * result + ((getFreezeCount() == null) ? 0 : getFreezeCount().hashCode());
        result = prime * result + ((getZjcheck() == null) ? 0 : getZjcheck().hashCode());
        result = prime * result + ((getFromzsm() == null) ? 0 : getFromzsm().hashCode());
        result = prime * result + ((getUnitId() == null) ? 0 : getUnitId().hashCode());
        result = prime * result + ((getMediResult() == null) ? 0 : getMediResult().hashCode());
        result = prime * result + ((getMediCheck() == null) ? 0 : getMediCheck().hashCode());
        result = prime * result + ((getProofNumber() == null) ? 0 : getProofNumber().hashCode());
        result = prime * result + ((getShortCode() == null) ? 0 : getShortCode().hashCode());
        result = prime * result + ((getHarvestUnit() == null) ? 0 : getHarvestUnit().hashCode());
        result = prime * result + ((getFromShortCode() == null) ? 0 : getFromShortCode().hashCode());
        result = prime * result + ((getToShortCode() == null) ? 0 : getToShortCode().hashCode());
        result = prime * result + ((getTraceProofShort() == null) ? 0 : getTraceProofShort().hashCode());
        result = prime * result + ((getSourceEntity() == null) ? 0 : getSourceEntity().hashCode());
        return result;
    }
	
}