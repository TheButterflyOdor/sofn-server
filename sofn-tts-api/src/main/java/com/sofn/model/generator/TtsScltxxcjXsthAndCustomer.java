package com.sofn.model.generator;

/**
 * Created by Administrator on 2017/1/11 0011.
 */
public class TtsScltxxcjXsthAndCustomer extends TtsScltxxcjXsth {
    private String name;//客户企业名称
    private String userName;//客户企业经办人名称
    private String phone;//联系电话
    private String address;//联系地址
    private String xsName;//购买企业名称.
    private String num;//序号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getXsName() {
        return xsName;
    }

    public void setXsName(String xsName) {
        this.xsName = xsName;
    }

    public String getNum() {
        return num;
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
        sb.append(", xsjlid=").append(getXsjlid());
        sb.append(", productName=").append(getProductName());
        sb.append(", productId=").append(getProductId());
        sb.append(", productIndustry=").append(getProductIndustry());
        sb.append(", productSort=").append(getProductSort());
        sb.append(", saleAmount=").append(getSaleAmount());
        sb.append(", saleTime=").append(getSaleTime());
        sb.append(", saleUser=").append(getSaleUser());
        sb.append(", productXspc=").append(getProductXspc());
        sb.append(", productXspcSl=").append(getProductXspcSl());
        sb.append(", buyComUserId=").append(getBuyComUserId());
        sb.append(", customerEntityIdcode=").append(getCustomerEntityIdcode());
        sb.append(", customerUserIdcode=").append(getCustomerUserIdcode());
        sb.append(", entityIdcode=").append(getEntityIdcode());
        sb.append(", userIdcode=").append(getUserIdcode());
        sb.append(", xsthyy=").append(getXsthyy());

        sb.append(", name=").append(name);
        sb.append(", userName=").append(userName);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", xsName=").append(xsName);
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
        TtsScltxxcjXsthAndCustomer other = (TtsScltxxcjXsthAndCustomer) that;
        return
                (this.getXsjlid() == null ? other.getXsjlid() == null : this.getXsjlid().equals(other.getXsjlid())) &&
                        (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
                        (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
                        (this.getProductIndustry() == null ? other.getProductIndustry() == null : this.getProductIndustry().equals(other.getProductIndustry())) &&
                        (this.getProductSort() == null ? other.getProductSort() == null : this.getProductSort().equals(other.getProductSort())) &&
                        (this.getSaleAmount() == null ? other.getSaleAmount() == null : this.getSaleAmount().equals(other.getSaleAmount())) &&
                        (this.getSaleTime() == null ? other.getSaleTime() == null : this.getSaleTime().equals(other.getSaleTime())) &&
                        (this.getSaleUser() == null ? other.getSaleUser() == null : this.getSaleUser().equals(other.getSaleUser())) &&
                        (this.getProductXspc() == null ? other.getProductXspc() == null : this.getProductXspc().equals(other.getProductXspc())) &&
                        (this.getProductXspcSl() == null ? other.getProductXspcSl() == null : this.getProductXspcSl().equals(other.getProductXspcSl())) &&
                        (this.getBuyComUserId() == null ? other.getBuyComUserId() == null : this.getBuyComUserId().equals(other.getBuyComUserId())) &&
                        (this.getCustomerEntityIdcode() == null ? other.getCustomerEntityIdcode() == null : this.getCustomerEntityIdcode().equals(other.getCustomerEntityIdcode())) &&
                        (this.getCustomerUserIdcode() == null ? other.getCustomerUserIdcode() == null : this.getCustomerUserIdcode().equals(other.getCustomerUserIdcode())) &&
                        (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
                        (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
                        (this.getXsthyy() == null ? other.getXsthyy() == null : this.getXsthyy().equals(other.getXsthyy())) &&
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
        result = prime * result + ((getXsjlid() == null) ? 0 : getXsjlid().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductIndustry() == null) ? 0 : getProductIndustry().hashCode());
        result = prime * result + ((getProductSort() == null) ? 0 : getProductSort().hashCode());
        result = prime * result + ((getSaleAmount() == null) ? 0 : getSaleAmount().hashCode());
        result = prime * result + ((getSaleTime() == null) ? 0 : getSaleTime().hashCode());
        result = prime * result + ((getSaleUser() == null) ? 0 : getSaleUser().hashCode());
        result = prime * result + ((getProductXspc() == null) ? 0 : getProductXspc().hashCode());
        result = prime * result + ((getProductXspcSl() == null) ? 0 : getProductXspcSl().hashCode());
        result = prime * result + ((getBuyComUserId() == null) ? 0 : getBuyComUserId().hashCode());
        result = prime * result + ((getCustomerEntityIdcode() == null) ? 0 : getCustomerEntityIdcode().hashCode());
        result = prime * result + ((getCustomerUserIdcode() == null) ? 0 : getCustomerUserIdcode().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getXsthyy() == null) ? 0 : getXsthyy().hashCode());

        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getXsName() == null) ? 0 : getXsName().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        return result;
    }
}
