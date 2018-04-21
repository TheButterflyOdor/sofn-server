package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售记录模型对象
 *
 * @author moon.l
 */
@SuppressWarnings("serial")
public class TtsScltxxcjXsdjjl extends BaseModel {

	
	/*
    * 对应字段
	*/
    //public String id;//主键

    public String xsdjId; //销售登记ID
    public String productName; //产品名称
    public String productId; //产品ID
    public String productIndustry; //产品所属行业
    public String productSort; //产品分类
    public String productScglId; //产品批次
    public BigDecimal saleAmount; //销售数量
    public String entityIdcode; //主体身份码
    public String userIdcode; //主体用户码
    public String customerEntityIdcode; //客户主体身份码
    public String customerUserIdcode; //客户主体用户码
    public String isCirculatesEnd; //是否入市
    public String confirmState; //确认状态
    public String traceCode; //产品追溯码
    public String traceProof; //入市追溯凭证
    public Date saleTime; //销售时间
    public Date confirmTime; //确认时间
    public String productModel; //规格型号
    public String buyComUserId; //客户ID
    public String saleUser; //销售人
    public String ipAddress; //Ip地址
    public String productXspc; //销售批次
    public String customerId;
    //new
    public String zjcheck;//质检情况
    //new 20161104
    public String fromzsm;//来源追溯码
    public String tozsm;//去向追溯码

    //质检情况
    public String getZjcheck() {
        return zjcheck;
    }

    //质检情况
    public void setZjcheck(String zjcheck) {
        this.zjcheck = zjcheck;
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

    public String getFromzsm() {
        return fromzsm;
    }

    public void setFromzsm(String fromzsm) {
        this.fromzsm = fromzsm;
    }

    public String getTozsm() {
        return tozsm;
    }

    public void setTozsm(String tozsm) {
        this.tozsm = tozsm;
    }

    /*
    *其余字段
    */
    //销售登记ID get
    public String getXsdjId() {
        return xsdjId;
    }

    //销售登记ID set
    public void setXsdjId(String xsdjId) {
        this.xsdjId = xsdjId;
    }

    //产品名称 get
    public String getProductName() {
        return productName;
    }

    //产品名称 set
    public void setProductName(String productName) {
        this.productName = productName;
    }

    //产品ID get
    public String getProductId() {
        return productId;
    }

    //产品ID set
    public void setProductId(String productId) {
        this.productId = productId;
    }

    //产品所属行业 get
    public String getProductIndustry() {
        return productIndustry;
    }

    //产品所属行业 set
    public void setProductIndustry(String productIndustry) {
        this.productIndustry = productIndustry;
    }

    //产品分类 get
    public String getProductSort() {
        return productSort;
    }

    //产品分类 set
    public void setProductSort(String productSort) {
        this.productSort = productSort;
    }

    //产品批次 get
    public String getProductScglId() {
        return productScglId;
    }

    //产品批次 set
    public void setProductScglId(String productScglId) {
        this.productScglId = productScglId;
    }

    //销售数量 get
    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    //销售数量 set
    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    //主体身份码 get
    public String getEntityIdcode() {
        return entityIdcode;
    }

    //主体身份码 set
    public void setEntityIdcode(String entityIdcode) {
        this.entityIdcode = entityIdcode;
    }

    //主体用户码 get
    public String getUserIdcode() {
        return userIdcode;
    }

    //主体用户码 set
    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }

    //客户主体身份码 get
    public String getCustomerEntityIdcode() {
        return customerEntityIdcode;
    }

    //客户主体身份码 set
    public void setCustomerEntityIdcode(String customerEntityIdcode) {
        this.customerEntityIdcode = customerEntityIdcode;
    }

    //客户主体用户码 get
    public String getCustomerUserIdcode() {
        return customerUserIdcode;
    }

    //客户主体用户码 set
    public void setCustomerUserIdcode(String customerUserIdcode) {
        this.customerUserIdcode = customerUserIdcode;
    }

    //是否入市 get
    public String getIsCirculatesEnd() {
        return isCirculatesEnd;
    }

    //是否入市 set
    public void setIsCirculatesEnd(String isCirculatesEnd) {
        this.isCirculatesEnd = isCirculatesEnd;
    }

    //确认状态 get
    public String getConfirmState() {
        return confirmState;
    }

    //确认状态 set
    public void setConfirmState(String confirmState) {
        this.confirmState = confirmState;
    }

    //产品追溯码 get
    public String getTraceCode() {
        return traceCode;
    }

    //产品追溯码 set
    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }

    //入市追溯凭证 get
    public String getTraceProof() {
        return traceProof;
    }

    //入市追溯凭证 set
    public void setTraceProof(String traceProof) {
        this.traceProof = traceProof;
    }

    //销售时间 get
    public Date getSaleTime() {
        return saleTime;
    }

    //销售时间 set
    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    //确认时间 get
    public Date getConfirmTime() {
        return confirmTime;
    }

    //确认时间 set
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    //规格型号 get
    public String getProductModel() {
        return productModel;
    }

    //规格型号 set
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    //客户ID get
    public String getBuyComUserId() {
        return buyComUserId;
    }

    //客户ID set
    public void setBuyComUserId(String buyComUserId) {
        this.buyComUserId = buyComUserId;
    }

    //销售人 get
    public String getSaleUser() {
        return saleUser;
    }

    //销售人 set
    public void setSaleUser(String saleUser) {
        this.saleUser = saleUser;
    }

    //Ip地址 get
    public String getIpAddress() {
        return ipAddress;
    }

    //Ip地址 set
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    //销售批次 get
    public String getProductXspc() {
        return productXspc;
    }

    //销售批次 set
    public void setProductXspc(String productXspc) {
        this.productXspc = productXspc;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", xsdjId=").append(xsdjId);
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
        sb.append(", productXspc=").append(productXspc);
        sb.append(", customerId=").append(productXspc);
        sb.append(", zjcheck=").append(zjcheck);
        sb.append(", fromzsm=").append(fromzsm);
        sb.append(", tozsm=").append(tozsm);
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
        TtsScltxxcjXsdjjl other = (TtsScltxxcjXsdjjl) that;
        return
                (this.getXsdjId() == null ? other.getXsdjId() == null : this.getXsdjId().equals(other.getXsdjId())) &&
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
                        (this.getProductXspc() == null ? other.getProductXspc() == null : this.getProductXspc().equals(other.getProductXspc())) &&
                        (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) &&
                        (this.getZjcheck() == null ? other.getZjcheck() == null : this.getZjcheck().equals(other.getZjcheck())) &&
                        (this.getFromzsm() == null ? other.getFromzsm() == null : this.getFromzsm().equals(other.getFromzsm())) &&
                        (this.getTozsm() == null ? other.getTozsm() == null : this.getTozsm().equals(other.getTozsm())) &&
                        (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getXsdjId() == null) ? 0 : getXsdjId().hashCode());
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
        result = prime * result + ((getProductXspc() == null) ? 0 : getProductXspc().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getZjcheck() == null) ? 0 : getZjcheck().hashCode());
        result = prime * result + ((getFromzsm() == null) ? 0 : getFromzsm().hashCode());
        result = prime * result + ((getTozsm() == null) ? 0 : getTozsm().hashCode());
        return result;
    }

}