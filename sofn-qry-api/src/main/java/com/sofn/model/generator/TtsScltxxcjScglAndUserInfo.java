package com.sofn.model.generator;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class TtsScltxxcjScglAndUserInfo extends TtsScltxxcjScgl {

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
        TtsScltxxcjScglAndUserInfo other = (TtsScltxxcjScglAndUserInfo) that;
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
        result = prime * result + ((getEnType() == null) ? 0 : getEnType().hashCode());
        result = prime * result + ((getEnName() == null) ? 0 : getEnName().hashCode());
        result = prime * result + ((getEnIndustry() == null) ? 0 : getEnIndustry().hashCode());
        result = prime * result + ((getEnArea() == null) ? 0 : getEnArea().hashCode());
        return result;
    }
}
