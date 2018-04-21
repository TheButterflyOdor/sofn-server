package com.sofn.model.generator;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class TtsScltxxcjCgglAndUserInfo extends TtsScltxxcjCggl {
    private String enType; //主体类型
    private String enName; //企业名称
    private String enIndustry; //主体所属行业
    private String enArea; //所属区域

    public String getEnType() {
        return enType;
    }

    public void setEnType(String enType) {
        this.enType = enType;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnIndustry() {
        return enIndustry;
    }

    public void setEnIndustry(String enIndustry) {
        this.enIndustry = enIndustry;
    }

    public String getEnArea() {
        return enArea;
    }
    public void setEnArea(String enArea) {
        this.enArea = enArea;
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
        sb.append(", cgAmount=").append(getCgAmount());
        sb.append(", entityIdcode=").append(getEntityIdcode());
        sb.append(", userIdcode=").append(getSaleUserIdcode());
        sb.append(", saleEntityIdcode=").append(getSaleEntityIdcode());
        sb.append(", saleUserIdcode=").append(getSaleUserIdcode());
        sb.append(", cgDw=").append(getCgDw());
        sb.append(", confirmState=").append(getConfirmState());
        sb.append(", traceCode=").append(getTraceCode());
        sb.append(", zjresult=").append(getZjresult());
        sb.append(", saleTime=").append(getSaleTime());
        sb.append(", confirmTime=").append(getConfirmTime());
        sb.append(", productModel=").append(getProductModel());
        sb.append(", buyComUserId=").append(getBuyComUserId());
        sb.append(", saleUser=").append(getSaleUserIdcode());
        sb.append(", ipAddress=").append(getIpAddress());
        sb.append(", fromzsm=").append(getFromzsm());
        sb.append(", tozsm=").append(getTozsm());
        sb.append(", productCgpc=").append(getProductCgpc());
        sb.append(", productCgpcSl=").append(getProductCgpcSl());
        sb.append(", xsdjid=").append(getXsdjid());
        sb.append(", zjcheck=").append(getZjcheck());
        sb.append(", enType=").append(enType);
        sb.append(", enName=").append(enName);
        sb.append(", enIndustry=").append(enIndustry);
        sb.append(", enArea=").append(enArea);
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
        TtsScltxxcjCgglAndUserInfo other = (TtsScltxxcjCgglAndUserInfo) that;
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
                        (this.getEnType() == null ? other.getEnType() == null : this.getEnType().equals(other.getEnType())) &&
                        (this.getEnName() == null ? other.getEnName() == null : this.getEnName().equals(other.getEnName())) &&
                        (this.getEnIndustry() == null ? other.getEnIndustry() == null : this.getEnIndustry().equals(other.getEnIndustry())) &&
                        (this.getEnArea() == null ? other.getEnArea() == null : this.getEnArea().equals(other.getEnArea())) &&
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
        result = prime * result + ((getEnType() == null) ? 0 : getEnType().hashCode());
        result = prime * result + ((getEnName() == null) ? 0 : getEnName().hashCode());
        result = prime * result + ((getEnIndustry() == null) ? 0 : getEnIndustry().hashCode());
        result = prime * result + ((getEnArea() == null) ? 0 : getEnArea().hashCode());
        return result;
    }
}
