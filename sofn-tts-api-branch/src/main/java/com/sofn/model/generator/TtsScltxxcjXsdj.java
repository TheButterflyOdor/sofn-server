package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;
import org.apache.poi.hssf.record.pivottable.StreamIDRecord;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售登记模型对象
 * @author moon.l
 *
 */
@SuppressWarnings("serial")
public class TtsScltxxcjXsdj extends BaseModel {
	
	
	/*
	* 对应字段
	*/
    //public String id;//主键

    private String productName; //产品名称
    private String productId; //产品名称编号
    private String productIndustry; //产品行业
    private String productSort; //分类
    private String productScglId; //产品批次
    private BigDecimal saleAmount; //销售数量
    private String entityIdcode; //主体身份码
    private String userIdcode; //主体用户码
    private String customerEntityIdcode; //客户主体身份码
    private String customerUserIdcode; //客户主体用户码
    private String isCirculatesEnd; //是否入市
    private String confirmState; //确认状态
    private String traceCode; //产品追溯码
    private String traceProof; //入市追溯凭证
    private Date saleTime; //销售时间
    private Date confirmTime; //确认时间
    private String productModel; //规格型号
    private String buyComUserId; //客户ID
    private String saleUser; //销售人
    private String ipAddress; //Ip地址
    private String fromid; //来源标识
    private String stockid; //库存标识
    //new
    private String productXspc;//销售批次
    private String productXspcSl;//销售批次数量
    private String customerId;//客户ID
    //new
    private String zjcheck;//质检情况
    //new 20161104
    private String fromzsm;//来源追溯码
    private String tozsm;//去向追溯码

    //短码 20170504新增
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



    /**
     * 计量单位Id
     */
    private String unitId;

    private String joinFlag;

    private String harvestUnit;

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
    public String getHarvestUnit() {
        return harvestUnit;
    }

    public void setHarvestUnit(String harvestUnit) {
        this.harvestUnit = harvestUnit;
    }


    //临时表id
    public String tempId;

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    //质检情况
    public void setZjcheck(String zjcheck) {
        this.zjcheck = zjcheck;
    }
    //质检情况
    public String getZjcheck() {
        return zjcheck;
    }

    public String getFromzsm() {
        return fromzsm;
    }

    public String getTozsm() {
        return tozsm;
    }

    public void setFromzsm(String fromzsm) {
        this.fromzsm = fromzsm;
    }

    public void setTozsm(String tozsm) {
        this.tozsm = tozsm;
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
    //产品名称 get
    public String getProductName(){
        return productName;
    }
    //产品名称 set
    public void setProductName(String productName){
        this.productName = productName;
    }
    //产品名称编号 get
    public String getProductId(){
        return productId;
    }
    //产品名称编号 set
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
    //分类 get
    public String getProductSort(){
        return productSort;
    }
    //分类 set
    public void setProductSort(String productSort){
        this.productSort = productSort;
    }
    //产品批次 get
    public String getProductScglId(){
        return productScglId;
    }
    //产品批次 set
    public void setProductScglId(String productScglId){
        this.productScglId = productScglId;
    }
    //销售数量 get
    public BigDecimal getSaleAmount(){
        return saleAmount;
    }
    //销售数量 set
    public void setSaleAmount(BigDecimal saleAmount){
        this.saleAmount = saleAmount;
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
    //客户主体身份码 get
    public String getCustomerEntityIdcode(){
        return customerEntityIdcode;
    }
    //客户主体身份码 set
    public void setCustomerEntityIdcode(String customerEntityIdcode){
        this.customerEntityIdcode = customerEntityIdcode;
    }
    //客户主体用户码 get
    public String getCustomerUserIdcode(){
        return customerUserIdcode;
    }
    //客户主体用户码 set
    public void setCustomerUserIdcode(String customerUserIdcode){
        this.customerUserIdcode = customerUserIdcode;
    }
    //是否入市 get
    public String getIsCirculatesEnd(){
        return isCirculatesEnd;
    }
    //是否入市 set
    public void setIsCirculatesEnd(String isCirculatesEnd){
        this.isCirculatesEnd = isCirculatesEnd;
    }
    //确认状态 get
    public String getConfirmState(){
        return confirmState;
    }
    //确认状态 set
    public void setConfirmState(String confirmState){
        this.confirmState = confirmState;
    }
    //产品追溯码 get
    public String getTraceCode(){
        return traceCode;
    }
    //产品追溯码 set
    public void setTraceCode(String traceCode){
        this.traceCode = traceCode;
    }
    //入市追溯凭证 get
    public String getTraceProof(){
        return traceProof;
    }
    //入市追溯凭证 set
    public void setTraceProof(String traceProof){
        this.traceProof = traceProof;
    }
    //销售时间 get
    public Date getSaleTime(){
        return saleTime;
    }
    //销售时间 set
    public void setSaleTime(Date saleTime){
        this.saleTime = saleTime;
    }
    //确认时间 get
    public Date getConfirmTime(){
        return confirmTime;
    }
    //确认时间 set
    public void setConfirmTime(Date confirmTime){
        this.confirmTime = confirmTime;
    }
    //规格型号 get
    public String getProductModel(){
        return productModel;
    }
    //规格型号 set
    public void setProductModel(String productModel){
        this.productModel = productModel;
    }
    //客户ID get
    public String getBuyComUserId(){
        return buyComUserId;
    }
    //客户ID set
    public void setBuyComUserId(String buyComUserId){
        this.buyComUserId = buyComUserId;
    }
    //销售人 get
    public String getSaleUser(){
        return saleUser;
    }
    //销售人 set
    public void setSaleUser(String saleUser){
        this.saleUser = saleUser;
    }
    //Ip地址 get
    public String getIpAddress(){
        return ipAddress;
    }
    //Ip地址 set
    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }
    //来源标识 get
    public String getFromid(){
        return fromid;
    }
    //来源标识 set
    public void setFromid(String fromid){
        this.fromid = fromid;
    }
    //库存标识 get
    public String getStockid(){
        return stockid;
    }
    //库存标识 set
    public void setStockid(String stockid){
        this.stockid = stockid;
    }

    //销售批次 set
    public void setProductXspc(String productXspc) {
        this.productXspc = productXspc;
    }
    //销售批次数量 set
    public void setProductXspcSl(String productXspcSl) {
        this.productXspcSl = productXspcSl;
    }
    //销售批次 get
    public String getProductXspc() {
        return productXspc;
    }
    //销售批次数量 get
    public String getProductXspcSl() {
        return productXspcSl;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productName=").append(productName);
        sb.append(", productId=").append(productId);
        sb.append(", productIndustry=").append(productIndustry);
        sb.append(", productSort=").append(productSort);
        sb.append(", productScglId=").append(productScglId);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", customerEntityIdcode=").append(customerEntityIdcode);
        sb.append(", customerUserIdcode=").append(customerUserIdcode);
        sb.append(", isCirculatesEnd=").append(isCirculatesEnd);
        sb.append(", confirmState=").append(confirmState);
        sb.append(", traceCode=").append(traceCode);
        sb.append(", traceProof=").append(traceProof);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", productModel=").append(productModel);
        sb.append(", buyComUserId=").append(buyComUserId);
        sb.append(", saleUser=").append(saleUser);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", fromid=").append(fromid);
        sb.append(", stockid=").append(stockid);
        sb.append(", productXspc=").append(productXspc);
        sb.append(", productXspcSl=").append(productXspcSl);
        sb.append(", customerId=").append(customerId);
        sb.append(", zjcheck=").append(zjcheck);
        sb.append(", fromzsm=").append(fromzsm);
        sb.append(", tozsm=").append(tozsm);
        sb.append(", fromzsm=").append(fromzsm);
        sb.append(", unitId=").append(unitId);
        sb.append(", harvestUnit=").append(harvestUnit);
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
        TtsScltxxcjXsdj other = (TtsScltxxcjXsdj) that;
        return
                (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
                        (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
                        (this.getProductIndustry() == null ? other.getProductIndustry() == null : this.getProductIndustry().equals(other.getProductIndustry())) &&
                        (this.getProductSort() == null ? other.getProductSort() == null : this.getProductSort().equals(other.getProductSort())) &&
                        (this.getProductScglId() == null ? other.getProductScglId() == null : this.getProductScglId().equals(other.getProductScglId())) &&
                        (this.getSaleAmount() == null ? other.getSaleAmount() == null : this.getSaleAmount().equals(other.getSaleAmount())) &&
                        (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
                        (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
                        (this.getCustomerEntityIdcode() == null ? other.getCustomerEntityIdcode() == null : this.getCustomerEntityIdcode().equals(other.getCustomerEntityIdcode())) &&
                        (this.getCustomerUserIdcode() == null ? other.getCustomerUserIdcode() == null : this.getCustomerUserIdcode().equals(other.getCustomerUserIdcode())) &&
                        (this.getIsCirculatesEnd() == null ? other.getIsCirculatesEnd() == null : this.getIsCirculatesEnd().equals(other.getIsCirculatesEnd())) &&
                        (this.getConfirmState() == null ? other.getConfirmState() == null : this.getConfirmState().equals(other.getConfirmState())) &&
                        (this.getTraceCode() == null ? other.getTraceCode() == null : this.getTraceCode().equals(other.getTraceCode())) &&
                        (this.getTraceProof() == null ? other.getTraceProof() == null : this.getTraceProof().equals(other.getTraceProof())) &&
                        (this.getSaleTime() == null ? other.getSaleTime() == null : this.getSaleTime().equals(other.getSaleTime())) &&
                        (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime())) &&
                        (this.getProductModel() == null ? other.getProductModel() == null : this.getProductModel().equals(other.getProductModel())) &&
                        (this.getBuyComUserId() == null ? other.getBuyComUserId() == null : this.getBuyComUserId().equals(other.getBuyComUserId())) &&
                        (this.getSaleUser() == null ? other.getSaleUser() == null : this.getSaleUser().equals(other.getSaleUser())) &&
                        (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress())) &&
                        (this.getFromid() == null ? other.getFromid() == null : this.getFromid().equals(other.getFromid())) &&
                        (this.getStockid() == null ? other.getStockid() == null : this.getStockid().equals(other.getStockid())) &&
                        (this.getProductXspc() == null ? other.getProductXspc() == null : this.getProductXspc().equals(other.getProductXspc())) &&
                        (this.getProductXspcSl() == null ? other.getProductXspcSl() == null : this.getProductXspcSl().equals(other.getProductXspcSl())) &&
                        (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) &&
                        (this.getZjcheck() == null ? other.getZjcheck() == null : this.getZjcheck().equals(other.getZjcheck())) &&
                        (this.getFromzsm() == null ? other.getFromzsm() == null : this.getFromzsm().equals(other.getFromzsm())) &&
                        (this.getTozsm() == null ? other.getTozsm() == null : this.getTozsm().equals(other.getTozsm())) &&
                        (this.getFromzsm() == null ? other.getFromzsm() == null : this.getFromzsm().equals(other.getFromzsm())) &&
                        (this.getUnitId() == null ? other.getUnitId() == null : this.getUnitId().equals(other.getUnitId())) &&
                        (this.getShortCode() == null ? other.getShortCode() == null : this.getShortCode().equals(other.getShortCode())) &&
                        (this.getProofNumber() == null ? other.getProofNumber() == null : this.getProofNumber().equals(other.getProofNumber())) &&
                        (this.getFromShortCode() == null ? other.getFromShortCode() == null : this.getFromShortCode().equals(other.getFromShortCode())) &&
                        (this.getToShortCode() == null ? other.getToShortCode() == null : this.getToShortCode().equals(other.getToShortCode())) &&
                        (this.getTraceProofShort() == null ? other.getTraceProofShort() == null : this.getTraceProofShort().equals(other.getTraceProofShort())) &&
                        (this.getHarvestUnit() == null ? other.getHarvestUnit() == null : this.getHarvestUnit().equals(other.getHarvestUnit())) &&
                        (this.getSourceEntity() == null ? other.getSourceEntity() == null : this.getSourceEntity().equals(other.getSourceEntity())) &&
                        (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductIndustry() == null) ? 0 : getProductIndustry().hashCode());
        result = prime * result + ((getProductSort() == null) ? 0 : getProductSort().hashCode());
        result = prime * result + ((getProductScglId() == null) ? 0 : getProductScglId().hashCode());
        result = prime * result + ((getSaleAmount() == null) ? 0 : getSaleAmount().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getCustomerEntityIdcode() == null) ? 0 : getCustomerEntityIdcode().hashCode());
        result = prime * result + ((getCustomerUserIdcode() == null) ? 0 : getCustomerUserIdcode().hashCode());
        result = prime * result + ((getIsCirculatesEnd() == null) ? 0 : getIsCirculatesEnd().hashCode());
        result = prime * result + ((getConfirmState() == null) ? 0 : getConfirmState().hashCode());
        result = prime * result + ((getTraceCode() == null) ? 0 : getTraceCode().hashCode());
        result = prime * result + ((getTraceProof() == null) ? 0 : getTraceProof().hashCode());
        result = prime * result + ((getSaleTime() == null) ? 0 : getSaleTime().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getProductModel() == null) ? 0 : getProductModel().hashCode());
        result = prime * result + ((getBuyComUserId() == null) ? 0 : getBuyComUserId().hashCode());
        result = prime * result + ((getSaleUser() == null) ? 0 : getSaleUser().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getFromid() == null) ? 0 : getFromid().hashCode());
        result = prime * result + ((getStockid() == null) ? 0 : getStockid().hashCode());
        result = prime * result + ((getProductXspc() == null) ? 0 : getProductXspc().hashCode());
        result = prime * result + ((getProductXspcSl() == null) ? 0 : getProductXspcSl().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getZjcheck() == null) ? 0 : getZjcheck().hashCode());
        result = prime * result + ((getFromzsm() == null) ? 0 : getFromzsm().hashCode());
        result = prime * result + ((getFromzsm() == null) ? 0 : getFromzsm().hashCode());
        result = prime * result + ((getUnitId() == null) ? 0 : getUnitId().hashCode());
        result = prime * result + ((getHarvestUnit() == null) ? 0 : getHarvestUnit().hashCode());
        result = prime * result + ((getProofNumber() == null) ? 0 : getProofNumber().hashCode());
        result = prime * result + ((getShortCode() == null) ? 0 : getShortCode().hashCode());
        result = prime * result + ((getTozsm() == null) ? 0 : getTozsm().hashCode());
        result = prime * result + ((getFromShortCode() == null) ? 0 : getFromShortCode().hashCode());
        result = prime * result + ((getToShortCode() == null) ? 0 : getToShortCode().hashCode());
        result = prime * result + ((getTraceProofShort() == null) ? 0 : getTraceProofShort().hashCode());
        result = prime * result + ((getSourceEntity() == null) ? 0 : getSourceEntity().hashCode());
        return result;
    }

}