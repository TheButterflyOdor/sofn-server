package com.sofn.model.generator;


/**
 * 销售登记模型对象
 * @author moon.l
 *
 */
@SuppressWarnings("serial")
public class TtsScltxxcjXsdjAndCustomer extends TtsScltxxcjXsdj {


    private String name;//客户企业名称
    private String userName;//客户企业经办人名称
    private String phone;//联系电话
    private String address;//联系地址
    //new 20161102...
    private String xsName;//销售企业名称
    private String num;//序号


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public void setXsName(String xsName) {
        this.xsName = xsName;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }



    public String getXsName() {
        return xsName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productName=").append(getProductName());
        sb.append(", productId=").append(getProductId());
        sb.append(", productIndustry=").append(getProductIndustry());
        sb.append(", productSort=").append(getProductSort());
        sb.append(", productScglId=").append(getProductScglId());
        sb.append(", saleAmount=").append(getSaleAmount());
        sb.append(", entityIdcode=").append(getEntityIdcode());
        sb.append(", userIdcode=").append(getUserIdcode());
        sb.append(", customerEntityIdcode=").append(getCustomerEntityIdcode());
        sb.append(", customerUserIdcode=").append(getCustomerUserIdcode());
        sb.append(", isCirculatesEnd=").append(getIsCirculatesEnd());
        sb.append(", confirmState=").append(getConfirmState());
        sb.append(", traceCode=").append(getTraceCode());
        sb.append(", traceProof=").append(getTraceProof());
        sb.append(", saleTime=").append(getSaleTime());
        sb.append(", confirmTime=").append(getConfirmTime());
        sb.append(", productModel=").append(getProductModel());
        sb.append(", buyComUserId=").append(getBuyComUserId());
        sb.append(", saleUser=").append(getSaleUser());
        sb.append(", ipAddress=").append(getIpAddress());
        sb.append(", fromid=").append(getFromid());
        sb.append(", stockid=").append(getStockid());
        sb.append(", productXspc=").append(getProductXspc());
        sb.append(", productXspcSl=").append(getProductXspcSl());
        sb.append(", customerId=").append(getCustomerId());
        sb.append(", zjcheck=").append(getZjcheck());
        //
        sb.append(", name=").append(name);
        sb.append(", userName=").append(userName);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        //new 2016.11.02
        sb.append(", xsName=").append(xsName);
        //new 2016.11.03
        sb.append(", num=").append(num);
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
        TtsScltxxcjXsdjAndCustomer other = (TtsScltxxcjXsdjAndCustomer) that;
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
                        (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
                        (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName())) &&
                        (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone())) &&
                        (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress())) &&
                        (this.getXsName() == null ? other.getXsName() == null : this.getXsName().equals(other.getXsName())) &&
                        (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum())) &&
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
        //
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        //new 2016.11.02
        result = prime * result + ((getXsName() == null) ? 0 : getXsName().hashCode());
        //new 2016.11.03
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());

        return result;
    }

}