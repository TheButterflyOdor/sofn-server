package com.sofn.model.generator;

/**
 * Created by Administrator on 2016/11/7.
 */
public class TtsScltxxcjCgglAndCustomer extends TtsScltxxcjCggl{

    public String name;//客户企业名称
    public String userName;//客户企业经办人名称
    public String phone;//联系电话
    public String address;//联系地址
    //new 20161102...
    public String xsName;//销售企业名称.
    public String num;//序号

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

    public String getNum() {
        return num;
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

    public void setNum(String num) {
        this.num = num;
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
        sb.append(", cgDw=").append(cgDw);
        sb.append(", confirmState=").append(confirmState);
        sb.append(", traceCode=").append(traceCode);
        sb.append(", zjresult=").append(zjresult);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", productModel=").append(productModel);
        sb.append(", buyComUserId=").append(buyComUserId);
        sb.append(", saleUser=").append(saleUser);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", fromzsm=").append(fromzsm);
        sb.append(", tozsm=").append(tozsm);
        sb.append(", productCgpc=").append(productCgpc);
        sb.append(", productCgpcSl=").append(productCgpcSl);
        sb.append(", xsdjid=").append(xsdjid);
        sb.append(", zjcheck=").append(zjcheck);
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
        TtsScltxxcjCgglAndCustomer other = (TtsScltxxcjCgglAndCustomer) that;
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
                        (this.getCgDw() == null ? other.getCgDw() == null : this.getCgDw().equals(other.getCgDw())) &&
                        (this.getConfirmState() == null ? other.getConfirmState() == null : this.getConfirmState().equals(other.getConfirmState())) &&
                        (this.getTraceCode() == null ? other.getTraceCode() == null : this.getTraceCode().equals(other.getTraceCode())) &&
                        (this.getZjresult() == null ? other.getZjresult() == null : this.getZjresult().equals(other.getZjresult())) &&
                        (this.getSaleTime() == null ? other.getSaleTime() == null : this.getSaleTime().equals(other.getSaleTime())) &&
                        (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime())) &&
                        (this.getProductModel() == null ? other.getProductModel() == null : this.getProductModel().equals(other.getProductModel())) &&
                        (this.getBuyComUserId() == null ? other.getBuyComUserId() == null : this.getBuyComUserId().equals(other.getBuyComUserId())) &&
                        (this.getSaleUser() == null ? other.getSaleUser() == null : this.getSaleUser().equals(other.getSaleUser())) &&
                        (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress())) &&
                        (this.getFromzsm() == null ? other.getFromzsm() == null : this.getFromzsm().equals(other.getFromzsm())) &&
                        (this.getTozsm() == null ? other.getTozsm() == null : this.getTozsm().equals(other.getTozsm())) &&
                        (this.getProductCgpc() == null ? other.getProductCgpc() == null : this.getProductCgpc().equals(other.getProductCgpc())) &&
                        (this.getProductCgpcSl() == null ? other.getProductCgpcSl() == null : this.getProductCgpcSl().equals(other.getProductCgpcSl())) &&
                        (this.getXsdjid() == null ? other.getXsdjid() == null : this.getXsdjid().equals(other.getXsdjid())) &&
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
        result = prime * result + ((getCgAmount() == null) ? 0 : getCgAmount().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getSaleEntityIdcode() == null) ? 0 : getSaleEntityIdcode().hashCode());
        result = prime * result + ((getSaleUserIdcode() == null) ? 0 : getSaleUserIdcode().hashCode());
        result = prime * result + ((getCgDw() == null) ? 0 : getCgDw().hashCode());
        result = prime * result + ((getConfirmState() == null) ? 0 : getConfirmState().hashCode());
        result = prime * result + ((getTraceCode() == null) ? 0 : getTraceCode().hashCode());
        result = prime * result + ((getZjresult() == null) ? 0 : getZjresult().hashCode());
        result = prime * result + ((getSaleTime() == null) ? 0 : getSaleTime().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getProductModel() == null) ? 0 : getProductModel().hashCode());
        result = prime * result + ((getBuyComUserId() == null) ? 0 : getBuyComUserId().hashCode());
        result = prime * result + ((getSaleUser() == null) ? 0 : getSaleUser().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getFromzsm() == null) ? 0 : getFromzsm().hashCode());
        result = prime * result + ((getTozsm() == null) ? 0 : getTozsm().hashCode());
        result = prime * result + ((getProductCgpc() == null) ? 0 : getProductCgpc().hashCode());
        result = prime * result + ((getProductCgpcSl() == null) ? 0 : getProductCgpcSl().hashCode());
        result = prime * result + ((getXsdjid() == null) ? 0 : getXsdjid().hashCode());
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
