package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 生产管理模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjScgl extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String status; //当前状态 (1未卖出2未卖完3已卖完4已合成)
    private String billStratrus; //单据状态 (0初始1合成2合并)
    private String ip; //IP地址
    private String entityIdcode; //主体身份码
    private String productInnerKey; //内部追溯标识
    private String mediResult; //质检结论
    private String productSource; //产品来源
    private String harvestBaseid; //收获基地id
    private String harvestBasename; //收获基地名称
    private String productName; //产品名称
    private String productId; //产品编号
    private String productSort; //产品分类
    private String userIdcode; //主体用户码
    private String productIndustry; //所属行业
    private BigDecimal productAmount; //收获数量
    private BigDecimal storeCount; //库存数量
    private BigDecimal freezeCount; //冻结数量
    private String harvestUnit; //收获单位
    private Date harvestTime; //收获时间
    private String productPc; //产品批次
    private String mediCheck; //质检情况
    private String checkImagename; //质检照片名称
    private String checkImagepath; //质检照片路径
    private String joinFlag;//加入类型标记
    //new 20161104
    private String fromzsm;//来源追溯码

    //tanli 20161109
    //屠宰产品内部关联码
    private String insideTraceCode;
    //一般产品和屠宰产品区分标识
    private String unitId;
    //new 20161109
    private String tempEnId;

    private String slaughterAmount;

    //短码 20170504
    private String shortCode;
    //动物检疫合格证号
    private String proofNumber;
    //来源追溯短码
    private String fromShortCode;
    //产品来源追溯（实际存入主体身份码）
    private String sourceEntity;
    //农事活动详情追溯id
    private String traceId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSourceEntity() { return sourceEntity; }

    public void setSourceEntity(String sourceEntity) { this.sourceEntity = sourceEntity; }

    public String getSlaughterAmount() {
        return slaughterAmount;
    }

    public void setSlaughterAmount(String slaughterAmount) {
        this.slaughterAmount = slaughterAmount;
    }

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

    public String getInsideTraceCode() {
        return insideTraceCode;
    }

    public void setInsideTraceCode(String insideTraceCode) {
        this.insideTraceCode = insideTraceCode;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getTempEnId() {
        return tempEnId;
    }

    public void setTempEnId(String tempEnId) {
        this.tempEnId = tempEnId;
    }

    public String getFromzsm() {
        return fromzsm;
    }




    public void setFromzsm(String fromzsm) {
        this.fromzsm = fromzsm;
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
	 //IP地址 get
	public String getIp(){
        return ip;
    }
	//IP地址 set
    public void setIp(String ip){
        this.ip = ip;
    }
	 //主体身份码 get
	public String getEntityIdcode(){
        return entityIdcode;
    }
	//主体身份码 set
    public void setEntityIdcode(String entityIdcode){
        this.entityIdcode = entityIdcode;
    }
	 //内部追溯标识 get
	public String getProductInnerKey(){
        return productInnerKey;
    }
	//内部追溯标识 set
    public void setProductInnerKey(String productInnerKey){
        this.productInnerKey = productInnerKey;
    }
	 //质检结论 get
	public String getMediResult(){
        return mediResult;
    }
	//质检结论 set
    public void setMediResult(String mediResult){
        this.mediResult = mediResult;
    }
	 //产品来源 get
	public String getProductSource(){
        return productSource;
    }
	//产品来源 set
    public void setProductSource(String productSource){
        this.productSource = productSource;
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
	 //产品名称 get
	public String getProductName(){
        return productName;
    }
	//产品名称 set
    public void setProductName(String productName){
        this.productName = productName;
    }
	 //产品编号 get
	public String getProductId(){
        return productId;
    }
	//产品编号 set
    public void setProductId(String productId){
        this.productId = productId;
    }
	 //产品分类 get
	public String getProductSort(){
        return productSort;
    }
	//产品分类 set
    public void setProductSort(String productSort){
        this.productSort = productSort;
    }
	 //主体用户码 get
	public String getUserIdcode(){
        return userIdcode;
    }
	//主体用户码 set
    public void setUserIdcode(String userIdcode){
        this.userIdcode = userIdcode;
    }
	 //所属行业 get
	public String getProductIndustry(){
        return productIndustry;
    }
	//所属行业 set
    public void setProductIndustry(String productIndustry){
        this.productIndustry = productIndustry;
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
	 //收获单位 get
	public String getHarvestUnit(){
        return harvestUnit;
    }
	//收获单位 set
    public void setHarvestUnit(String harvestUnit){
        this.harvestUnit = harvestUnit;
    }
	 //收获时间 get
	public Date getHarvestTime(){
        return harvestTime;
    }
	//收获时间 set
    public void setHarvestTime(Date harvestTime){
        this.harvestTime = harvestTime;
    }
	 //产品批次 get
	public String getProductPc(){
        return productPc;
    }
	//产品批次 set
    public void setProductPc(String productPc){
        this.productPc = productPc;
    }
	 //质检情况 get
	public String getMediCheck(){
        return mediCheck;
    }
	//质检情况 set
    public void setMediCheck(String mediCheck){
        this.mediCheck = mediCheck;
    }
	 //质检照片名称 get
	public String getCheckImagename(){
        return checkImagename;
    }
	//质检照片名称 set
    public void setCheckImagename(String checkImagename){
        this.checkImagename = checkImagename;
    }
	 //质检照片路径 get
	public String getCheckImagepath(){
        return checkImagepath;
    }
	//质检照片路径 set
    public void setCheckImagepath(String checkImagepath){
        this.checkImagepath = checkImagepath;
    }

    //加入标记
    public void setJoinFlag(String joinFlag) {
        this.joinFlag = joinFlag;
    }

    public String getJoinFlag() {
        return joinFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", status=").append(status);
        sb.append(", billStratrus=").append(billStratrus);
        sb.append(", ip=").append(ip);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", productInnerKey=").append(productInnerKey);
        sb.append(", mediResult=").append(mediResult);
        sb.append(", productSource=").append(productSource);
        sb.append(", harvestBaseid=").append(harvestBaseid);
        sb.append(", harvestBasename=").append(harvestBasename);
        sb.append(", productName=").append(productName);
        sb.append(", productId=").append(productId);
        sb.append(", productSort=").append(productSort);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", productIndustry=").append(productIndustry);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", storeCount=").append(storeCount);
        sb.append(", freezeCount=").append(freezeCount);
        sb.append(", harvestUnit=").append(harvestUnit);
        sb.append(", harvestTime=").append(harvestTime);
        sb.append(", productPc=").append(productPc);
        sb.append(", mediCheck=").append(mediCheck);
        sb.append(", checkImagename=").append(checkImagename);
        sb.append(", checkImagepath=").append(checkImagepath);
        sb.append(", joinFlag=").append(joinFlag);
        sb.append(", fromzsm=").append(fromzsm);
        sb.append(", insideTraceCode=").append(insideTraceCode);
        sb.append(", sortFlag=").append(unitId);
        sb.append(", shortCode=").append(shortCode);
        sb.append(", proofNumber=").append(proofNumber);
        sb.append(", slaughterAmount=").append(slaughterAmount);
        sb.append(", fromShortCode=").append(fromShortCode);
        sb.append(", sourceEntity=").append(sourceEntity);
        sb.append(", traceId=").append(traceId);
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
        TtsScltxxcjScgl other = (TtsScltxxcjScgl) that;
          return 
        		(this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
        		(this.getBillStratrus() == null ? other.getBillStratrus() == null : this.getBillStratrus().equals(other.getBillStratrus())) &&
        		(this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp())) &&
        		(this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
        		(this.getProductInnerKey() == null ? other.getProductInnerKey() == null : this.getProductInnerKey().equals(other.getProductInnerKey())) &&
        		(this.getMediResult() == null ? other.getMediResult() == null : this.getMediResult().equals(other.getMediResult())) &&
        		(this.getProductSource() == null ? other.getProductSource() == null : this.getProductSource().equals(other.getProductSource())) &&
        		(this.getHarvestBaseid() == null ? other.getHarvestBaseid() == null : this.getHarvestBaseid().equals(other.getHarvestBaseid())) &&
        		(this.getHarvestBasename() == null ? other.getHarvestBasename() == null : this.getHarvestBasename().equals(other.getHarvestBasename())) &&
        		(this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
        		(this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
        		(this.getProductSort() == null ? other.getProductSort() == null : this.getProductSort().equals(other.getProductSort())) &&
        		(this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
        		(this.getProductIndustry() == null ? other.getProductIndustry() == null : this.getProductIndustry().equals(other.getProductIndustry())) &&
        		(this.getProductAmount() == null ? other.getProductAmount() == null : this.getProductAmount().equals(other.getProductAmount())) &&
        		(this.getStoreCount() == null ? other.getStoreCount() == null : this.getStoreCount().equals(other.getStoreCount())) &&
        		(this.getFreezeCount() == null ? other.getFreezeCount() == null : this.getFreezeCount().equals(other.getFreezeCount())) &&
        		(this.getHarvestUnit() == null ? other.getHarvestUnit() == null : this.getHarvestUnit().equals(other.getHarvestUnit())) &&
        		(this.getHarvestTime() == null ? other.getHarvestTime() == null : this.getHarvestTime().equals(other.getHarvestTime())) &&
        		(this.getProductPc() == null ? other.getProductPc() == null : this.getProductPc().equals(other.getProductPc())) &&
        		(this.getMediCheck() == null ? other.getMediCheck() == null : this.getMediCheck().equals(other.getMediCheck())) &&
        		(this.getCheckImagename() == null ? other.getCheckImagename() == null : this.getCheckImagename().equals(other.getCheckImagename())) &&
        		(this.getCheckImagepath() == null ? other.getCheckImagepath() == null : this.getCheckImagepath().equals(other.getCheckImagepath())) &&
                (this.getJoinFlag() == null ? other.getJoinFlag() == null : this.getJoinFlag().equals(other.getJoinFlag())) &&
                (this.getFromzsm() == null ? other.getFromzsm() == null : this.getFromzsm().equals(other.getFromzsm())) &&
                (this.getInsideTraceCode() == null ? other.getInsideTraceCode() == null : this.getInsideTraceCode().equals(other.getInsideTraceCode())) &&
                (this.getUnitId() == null ? other.getUnitId() == null : this.getUnitId().equals(other.getUnitId())) &&
                (this.getShortCode() == null ? other.getShortCode() == null : this.getShortCode().equals(other.getShortCode())) &&
                (this.getProofNumber() == null ? other.getProofNumber() == null : this.getProofNumber().equals(other.getProofNumber())) &&
                (this.getSlaughterAmount() == null ? other.getSlaughterAmount() == null : this.getSlaughterAmount().equals(other.getSlaughterAmount())) &&
                (this.getFromShortCode() == null ? other.getFromShortCode() == null : this.getFromShortCode().equals(other.getFromShortCode())) &&
                (this.getSourceEntity() == null ? other.getSourceEntity() == null : this.getSourceEntity().equals(other.getSourceEntity())) &&
                (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) &&
                (this.getTraceId() == null ? other.getTraceId() == null : this.getTraceId().equals(other.getTraceId()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBillStratrus() == null) ? 0 : getBillStratrus().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getProductInnerKey() == null) ? 0 : getProductInnerKey().hashCode());
        result = prime * result + ((getMediResult() == null) ? 0 : getMediResult().hashCode());
        result = prime * result + ((getProductSource() == null) ? 0 : getProductSource().hashCode());
        result = prime * result + ((getHarvestBaseid() == null) ? 0 : getHarvestBaseid().hashCode());
        result = prime * result + ((getHarvestBasename() == null) ? 0 : getHarvestBasename().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductSort() == null) ? 0 : getProductSort().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getProductIndustry() == null) ? 0 : getProductIndustry().hashCode());
        result = prime * result + ((getProductAmount() == null) ? 0 : getProductAmount().hashCode());
        result = prime * result + ((getStoreCount() == null) ? 0 : getStoreCount().hashCode());
        result = prime * result + ((getFreezeCount() == null) ? 0 : getFreezeCount().hashCode());
        result = prime * result + ((getHarvestUnit() == null) ? 0 : getHarvestUnit().hashCode());
        result = prime * result + ((getHarvestTime() == null) ? 0 : getHarvestTime().hashCode());
        result = prime * result + ((getProductPc() == null) ? 0 : getProductPc().hashCode());
        result = prime * result + ((getMediCheck() == null) ? 0 : getMediCheck().hashCode());
        result = prime * result + ((getCheckImagename() == null) ? 0 : getCheckImagename().hashCode());
        result = prime * result + ((getCheckImagepath() == null) ? 0 : getCheckImagepath().hashCode());
        result = prime * result + ((getFromzsm() == null) ? 0 : getFromzsm().hashCode());
        result = prime * result + ((getJoinFlag() == null) ? 0 : getJoinFlag().hashCode());
        result = prime * result + ((getInsideTraceCode() == null) ? 0 : getInsideTraceCode().hashCode());
        result = prime * result + ((getUnitId() == null) ? 0 : getUnitId().hashCode());
        result = prime * result + ((getProofNumber() == null) ? 0 : getProofNumber().hashCode());
        result = prime * result + ((getShortCode() == null) ? 0 : getShortCode().hashCode());
        result = prime * result + ((getSlaughterAmount() == null) ? 0 : getSlaughterAmount().hashCode());
        result = prime * result + ((getFromShortCode() == null) ? 0 : getFromShortCode().hashCode());
        result = prime * result + ((getSourceEntity() == null) ? 0 : getSourceEntity().hashCode());
        result = prime * result + ((getTraceId() == null) ? 0 : getTraceId().hashCode());
        return result;
    }
	
}