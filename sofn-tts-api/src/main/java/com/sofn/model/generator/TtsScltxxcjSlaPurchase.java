package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 屠宰采购管理模型对象
 * @author moon.l
 *
 */
@SuppressWarnings("serial")
public class TtsScltxxcjSlaPurchase extends BaseModel {


	/*
	* 对应字段
	*/
    //public String id;//主键

    private String productName; //产品名称
    private String productId; //产品名称编号
    private String productIndustry; //产品行业
    private String productSort; //分类
    private String productScglId; //产品批次
    private BigDecimal cgAmount; //采购数量
    private String entityIdcode; //主体身份码
    private String userIdcode; //主体用户码
    private String saleEntityIdcode; //销售主体身份码
    private String saleUserIdcode; //销售主体用户码
    private String confirmState; //确认状态
    private String traceCode; //产品追溯码
    private Date saleTime; //采购时间
    private Date confirmTime; //确认时间
    private String productModel; //规格型号
    private String buyComUserId; //客户ID
    private String saleUser; //销售人
    private String ipAddress; //Ip地址
    private String fromTraceCode; //来源追溯码
    private String toTraceCode; //去向追溯码
    private String productCgpc; //采购批次ID
    private String productCgpcSl; //采购批次数量
    private String xsdjid; //销售登记ID
    private String zjcheck; //质检情况
    private String cgDw; //采购计量单位
    private String zjresult; //质检结论


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
    //采购数量 get
    public BigDecimal getCgAmount(){
        return cgAmount;
    }
    //采购数量 set
    public void setCgAmount(BigDecimal cgAmount){
        this.cgAmount = cgAmount;
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
    //销售主体身份码 get
    public String getSaleEntityIdcode(){
        return saleEntityIdcode;
    }
    //销售主体身份码 set
    public void setSaleEntityIdcode(String saleEntityIdcode){
        this.saleEntityIdcode = saleEntityIdcode;
    }
    //销售主体用户码 get
    public String getSaleUserIdcode(){
        return saleUserIdcode;
    }
    //销售主体用户码 set
    public void setSaleUserIdcode(String saleUserIdcode){
        this.saleUserIdcode = saleUserIdcode;
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
    //采购时间 get
    public Date getSaleTime(){
        return saleTime;
    }
    //采购时间 set
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
    //来源追溯码 get
    public String getFromTraceCode(){
        return fromTraceCode;
    }
    //来源追溯码 set
    public void setFromTraceCode(String fromTraceCode){
        this.fromTraceCode = fromTraceCode;
    }
    //去向追溯码 get
    public String getToTraceCode(){
        return toTraceCode;
    }
    //去向追溯码 set
    public void setToTraceCode(String toTraceCode){
        this.toTraceCode = toTraceCode;
    }
    //采购批次ID get
    public String getProductCgpc(){
        return productCgpc;
    }
    //采购批次ID set
    public void setProductCgpc(String productCgpc){
        this.productCgpc = productCgpc;
    }
    //采购批次数量 get
    public String getProductCgpcSl(){
        return productCgpcSl;
    }
    //采购批次数量 set
    public void setProductCgpcSl(String productCgpcSl){
        this.productCgpcSl = productCgpcSl;
    }
    //销售登记ID get
    public String getXsdjid(){
        return xsdjid;
    }
    //销售登记ID set
    public void setXsdjid(String xsdjid){
        this.xsdjid = xsdjid;
    }
    //质检情况 get
    public String getZjcheck(){
        return zjcheck;
    }
    //质检情况 set
    public void setZjcheck(String zjcheck){
        this.zjcheck = zjcheck;
    }
    //采购计量单位 get
    public String getCgDw(){
        return cgDw;
    }
    //采购计量单位 set
    public void setCgDw(String cgDw){
        this.cgDw = cgDw;
    }
    //质检结论 get
    public String getZjresult(){
        return zjresult;
    }
    //质检结论 set
    public void setZjresult(String zjresult){
        this.zjresult = zjresult;
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
        sb.append(", cgAmount=").append(cgAmount);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", saleEntityIdcode=").append(saleEntityIdcode);
        sb.append(", saleUserIdcode=").append(saleUserIdcode);
        sb.append(", confirmState=").append(confirmState);
        sb.append(", traceCode=").append(traceCode);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", productModel=").append(productModel);
        sb.append(", buyComUserId=").append(buyComUserId);
        sb.append(", saleUser=").append(saleUser);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", fromzsm=").append(fromTraceCode);
        sb.append(", toTraceCode=").append(toTraceCode);
        sb.append(", productCgpc=").append(productCgpc);
        sb.append(", productCgpcSl=").append(productCgpcSl);
        sb.append(", xsdjid=").append(xsdjid);
        sb.append(", zjcheck=").append(zjcheck);
        sb.append(", cgDw=").append(cgDw);
        sb.append(", zjresult=").append(zjresult);
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
        TtsScltxxcjSlaPurchase other = (TtsScltxxcjSlaPurchase) that;
        return
                (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
                        (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
                        (this.getProductIndustry() == null ? other.getProductIndustry() == null : this.getProductIndustry().equals(other.getProductIndustry())) &&
                        (this.getProductSort() == null ? other.getProductSort() == null : this.getProductSort().equals(other.getProductSort())) &&
                        (this.getProductScglId() == null ? other.getProductScglId() == null : this.getProductScglId().equals(other.getProductScglId())) &&
                        (this.getCgAmount() == null ? other.getCgAmount() == null : this.getCgAmount().equals(other.getCgAmount())) &&
                        (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
                        (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
                        (this.getSaleEntityIdcode() == null ? other.getSaleEntityIdcode() == null : this.getSaleEntityIdcode().equals(other.getSaleEntityIdcode())) &&
                        (this.getSaleUserIdcode() == null ? other.getSaleUserIdcode() == null : this.getSaleUserIdcode().equals(other.getSaleUserIdcode())) &&
                        (this.getConfirmState() == null ? other.getConfirmState() == null : this.getConfirmState().equals(other.getConfirmState())) &&
                        (this.getTraceCode() == null ? other.getTraceCode() == null : this.getTraceCode().equals(other.getTraceCode())) &&
                        (this.getSaleTime() == null ? other.getSaleTime() == null : this.getSaleTime().equals(other.getSaleTime())) &&
                        (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime())) &&
                        (this.getProductModel() == null ? other.getProductModel() == null : this.getProductModel().equals(other.getProductModel())) &&
                        (this.getBuyComUserId() == null ? other.getBuyComUserId() == null : this.getBuyComUserId().equals(other.getBuyComUserId())) &&
                        (this.getSaleUser() == null ? other.getSaleUser() == null : this.getSaleUser().equals(other.getSaleUser())) &&
                        (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress())) &&
                        (this.getFromTraceCode() == null ? other.getFromTraceCode() == null : this.getFromTraceCode().equals(other.getFromTraceCode())) &&
                        (this.getToTraceCode() == null ? other.getToTraceCode() == null : this.getToTraceCode().equals(other.getToTraceCode())) &&
                        (this.getProductCgpc() == null ? other.getProductCgpc() == null : this.getProductCgpc().equals(other.getProductCgpc())) &&
                        (this.getProductCgpcSl() == null ? other.getProductCgpcSl() == null : this.getProductCgpcSl().equals(other.getProductCgpcSl())) &&
                        (this.getXsdjid() == null ? other.getXsdjid() == null : this.getXsdjid().equals(other.getXsdjid())) &&
                        (this.getZjcheck() == null ? other.getZjcheck() == null : this.getZjcheck().equals(other.getZjcheck())) &&
                        (this.getCgDw() == null ? other.getCgDw() == null : this.getCgDw().equals(other.getCgDw())) &&
                        (this.getZjresult() == null ? other.getZjresult() == null : this.getZjresult().equals(other.getZjresult())) &&
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
        result = prime * result + ((getCgAmount() == null) ? 0 : getCgAmount().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getSaleEntityIdcode() == null) ? 0 : getSaleEntityIdcode().hashCode());
        result = prime * result + ((getSaleUserIdcode() == null) ? 0 : getSaleUserIdcode().hashCode());
        result = prime * result + ((getConfirmState() == null) ? 0 : getConfirmState().hashCode());
        result = prime * result + ((getTraceCode() == null) ? 0 : getTraceCode().hashCode());
        result = prime * result + ((getSaleTime() == null) ? 0 : getSaleTime().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getProductModel() == null) ? 0 : getProductModel().hashCode());
        result = prime * result + ((getBuyComUserId() == null) ? 0 : getBuyComUserId().hashCode());
        result = prime * result + ((getSaleUser() == null) ? 0 : getSaleUser().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getFromTraceCode() == null) ? 0 : getFromTraceCode().hashCode());
        result = prime * result + ((getToTraceCode() == null) ? 0 : getToTraceCode().hashCode());
        result = prime * result + ((getProductCgpc() == null) ? 0 : getProductCgpc().hashCode());
        result = prime * result + ((getProductCgpcSl() == null) ? 0 : getProductCgpcSl().hashCode());
        result = prime * result + ((getXsdjid() == null) ? 0 : getXsdjid().hashCode());
        result = prime * result + ((getZjcheck() == null) ? 0 : getZjcheck().hashCode());
        result = prime * result + ((getCgDw() == null) ? 0 : getCgDw().hashCode());
        result = prime * result + ((getZjresult() == null) ? 0 : getZjresult().hashCode());
        return result;
    }

}