package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 抽样信息
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@SuppressWarnings("serial")
public class AlesSample extends BaseModel {
    private String prodcutCode;//产品编码

    private String sampleCode;//样品编码

    private String checkTaskId;//抽查任务ID

    private String enterpriseId;//被抽查单位ID

    private String sampleAddress;//抽样地点

    private String sampleInformation;//抽样信息

    private String sampleUnitId;//抽样单位ID

    private String enable;//是否删除

    private String delFlag;

    public String getProdcutCode() {
        return prodcutCode;
    }

    public void setProdcutCode(String prodcutCode) {
        this.prodcutCode = prodcutCode == null ? null : prodcutCode.trim();
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode == null ? null : sampleCode.trim();
    }

    public String getCheckTaskId() {
        return checkTaskId;
    }

    public void setCheckTaskId(String checkTaskId) {
        this.checkTaskId = checkTaskId == null ? null : checkTaskId.trim();
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getSampleAddress() {
        return sampleAddress;
    }

    public void setSampleAddress(String sampleAddress) {
        this.sampleAddress = sampleAddress == null ? null : sampleAddress.trim();
    }

    public String getSampleInformation() {
        return sampleInformation;
    }

    public void setSampleInformation(String sampleInformation) {
        this.sampleInformation = sampleInformation == null ? null : sampleInformation.trim();
    }

    public String getSampleUnitId() {
        return sampleUnitId;
    }

    public void setSampleUnitId(String sampleUnitId) {
        this.sampleUnitId = sampleUnitId == null ? null : sampleUnitId.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", prodcutCode=").append(prodcutCode);
        sb.append(", sampleCode=").append(sampleCode);
        sb.append(", checkTaskId=").append(checkTaskId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", sampleAddress=").append(sampleAddress);
        sb.append(", sampleInformation=").append(sampleInformation);
        sb.append(", sampleUnitId=").append(sampleUnitId);
        sb.append(", enable=").append(enable);
        sb.append(", delFlag=").append(delFlag);
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
        AlesSample other = (AlesSample) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProdcutCode() == null ? other.getProdcutCode() == null : this.getProdcutCode().equals(other.getProdcutCode()))
            && (this.getSampleCode() == null ? other.getSampleCode() == null : this.getSampleCode().equals(other.getSampleCode()))
            && (this.getCheckTaskId() == null ? other.getCheckTaskId() == null : this.getCheckTaskId().equals(other.getCheckTaskId()))
            && (this.getEnterpriseId() == null ? other.getEnterpriseId() == null : this.getEnterpriseId().equals(other.getEnterpriseId()))
            && (this.getSampleAddress() == null ? other.getSampleAddress() == null : this.getSampleAddress().equals(other.getSampleAddress()))
            && (this.getSampleInformation() == null ? other.getSampleInformation() == null : this.getSampleInformation().equals(other.getSampleInformation()))
            && (this.getSampleUnitId() == null ? other.getSampleUnitId() == null : this.getSampleUnitId().equals(other.getSampleUnitId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProdcutCode() == null) ? 0 : getProdcutCode().hashCode());
        result = prime * result + ((getSampleCode() == null) ? 0 : getSampleCode().hashCode());
        result = prime * result + ((getCheckTaskId() == null) ? 0 : getCheckTaskId().hashCode());
        result = prime * result + ((getEnterpriseId() == null) ? 0 : getEnterpriseId().hashCode());
        result = prime * result + ((getSampleAddress() == null) ? 0 : getSampleAddress().hashCode());
        result = prime * result + ((getSampleInformation() == null) ? 0 : getSampleInformation().hashCode());
        result = prime * result + ((getSampleUnitId() == null) ? 0 : getSampleUnitId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }
}