package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;
import java.util.Date;

@SuppressWarnings("serial")
public class AlesProduceAdminPunish extends BaseModel {
    private String punishCode;

    private String legalPerson;

    private String caseName;

    private String productName;

    private String enterpriseName;

    private String manufactureDate;

    private String punishQualitative;

    private String punishResult;

    private String punishFiles;

    private String enable;

    private String enterpriseId;

    private String enterpriseCode;

    private String enforceLawId;

    private String enforceLawPeople;

    private Date enforceLawTime;

    private String punishFilesName;

    private String createOrgId;

    private String enterpriseIdName;

    private String dailyTaskCode;

    public String getPunishCode() {
        return punishCode;
    }

    public void setPunishCode(String punishCode) {
        this.punishCode = punishCode == null ? null : punishCode.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate == null ? null : manufactureDate.trim();
    }

    public String getPunishQualitative() {
        return punishQualitative;
    }

    public void setPunishQualitative(String punishQualitative) {
        this.punishQualitative = punishQualitative == null ? null : punishQualitative.trim();
    }

    public String getPunishResult() {
        return punishResult;
    }

    public void setPunishResult(String punishResult) {
        this.punishResult = punishResult == null ? null : punishResult.trim();
    }

    public String getPunishFiles() {
        return punishFiles;
    }

    public void setPunishFiles(String punishFiles) {
        this.punishFiles = punishFiles == null ? null : punishFiles.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    public String getEnforceLawId() {
        return enforceLawId;
    }

    public void setEnforceLawId(String enforceLawId) {
        this.enforceLawId = enforceLawId == null ? null : enforceLawId.trim();
    }

    public String getEnforceLawPeople() {
        return enforceLawPeople;
    }

    public void setEnforceLawPeople(String enforceLawPeople) {
        this.enforceLawPeople = enforceLawPeople == null ? null : enforceLawPeople.trim();
    }

    public Date getEnforceLawTime() {
        return enforceLawTime;
    }

    public void setEnforceLawTime(Date enforceLawTime) {
        this.enforceLawTime = enforceLawTime;
    }

    public String getPunishFilesName() {
        return punishFilesName;
    }

    public void setPunishFilesName(String punishFilesName) {
        this.punishFilesName = punishFilesName == null ? null : punishFilesName.trim();
    }

    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId == null ? null : createOrgId.trim();
    }

    public String getEnterpriseIdName() {
        return enterpriseIdName;
    }

    public void setEnterpriseIdName(String enterpriseIdName) {
        this.enterpriseIdName = enterpriseIdName == null ? null : enterpriseIdName.trim();
    }

    public String getDailyTaskCode() {
        return dailyTaskCode;
    }

    public void setDailyTaskCode(String dailyTaskCode) {
        this.dailyTaskCode = dailyTaskCode == null ? null : dailyTaskCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", punishCode=").append(punishCode);
        sb.append(", legalPerson=").append(legalPerson);
        sb.append(", caseName=").append(caseName);
        sb.append(", productName=").append(productName);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", manufactureDate=").append(manufactureDate);
        sb.append(", punishQualitative=").append(punishQualitative);
        sb.append(", punishResult=").append(punishResult);
        sb.append(", punishFiles=").append(punishFiles);
        sb.append(", enable=").append(enable);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", enterpriseCode=").append(enterpriseCode);
        sb.append(", enforceLawId=").append(enforceLawId);
        sb.append(", enforceLawPeople=").append(enforceLawPeople);
        sb.append(", enforceLawTime=").append(enforceLawTime);
        sb.append(", punishFilesName=").append(punishFilesName);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", enterpriseIdName=").append(enterpriseIdName);
        sb.append(", dailyTaskCode=").append(dailyTaskCode);
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
        AlesProduceAdminPunish other = (AlesProduceAdminPunish) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPunishCode() == null ? other.getPunishCode() == null : this.getPunishCode().equals(other.getPunishCode()))
                && (this.getLegalPerson() == null ? other.getLegalPerson() == null : this.getLegalPerson().equals(other.getLegalPerson()))
                && (this.getCaseName() == null ? other.getCaseName() == null : this.getCaseName().equals(other.getCaseName()))
                && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
                && (this.getEnterpriseName() == null ? other.getEnterpriseName() == null : this.getEnterpriseName().equals(other.getEnterpriseName()))
                && (this.getManufactureDate() == null ? other.getManufactureDate() == null : this.getManufactureDate().equals(other.getManufactureDate()))
                && (this.getPunishQualitative() == null ? other.getPunishQualitative() == null : this.getPunishQualitative().equals(other.getPunishQualitative()))
                && (this.getPunishResult() == null ? other.getPunishResult() == null : this.getPunishResult().equals(other.getPunishResult()))
                && (this.getPunishFiles() == null ? other.getPunishFiles() == null : this.getPunishFiles().equals(other.getPunishFiles()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
                && (this.getEnterpriseId() == null ? other.getEnterpriseId() == null : this.getEnterpriseId().equals(other.getEnterpriseId()))
                && (this.getEnterpriseCode() == null ? other.getEnterpriseCode() == null : this.getEnterpriseCode().equals(other.getEnterpriseCode()))
                && (this.getEnforceLawId() == null ? other.getEnforceLawId() == null : this.getEnforceLawId().equals(other.getEnforceLawId()))
                && (this.getEnforceLawPeople() == null ? other.getEnforceLawPeople() == null : this.getEnforceLawPeople().equals(other.getEnforceLawPeople()))
                && (this.getEnforceLawTime() == null ? other.getEnforceLawTime() == null : this.getEnforceLawTime().equals(other.getEnforceLawTime()))
                && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
                && (this.getPunishFilesName() == null ? other.getPunishFilesName() == null : this.getPunishFilesName().equals(other.getPunishFilesName()))
                && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
                && (this.getEnterpriseIdName() == null ? other.getEnterpriseIdName() == null : this.getEnterpriseIdName().equals(other.getEnterpriseIdName()))
                && (this.getDailyTaskCode() == null ? other.getDailyTaskCode() == null : this.getDailyTaskCode().equals(other.getDailyTaskCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPunishCode() == null) ? 0 : getPunishCode().hashCode());
        result = prime * result + ((getLegalPerson() == null) ? 0 : getLegalPerson().hashCode());
        result = prime * result + ((getCaseName() == null) ? 0 : getCaseName().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getEnterpriseName() == null) ? 0 : getEnterpriseName().hashCode());
        result = prime * result + ((getManufactureDate() == null) ? 0 : getManufactureDate().hashCode());
        result = prime * result + ((getPunishQualitative() == null) ? 0 : getPunishQualitative().hashCode());
        result = prime * result + ((getPunishResult() == null) ? 0 : getPunishResult().hashCode());
        result = prime * result + ((getPunishFiles() == null) ? 0 : getPunishFiles().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getEnterpriseId() == null) ? 0 : getEnterpriseId().hashCode());
        result = prime * result + ((getEnterpriseCode() == null) ? 0 : getEnterpriseCode().hashCode());
        result = prime * result + ((getEnforceLawId() == null) ? 0 : getEnforceLawId().hashCode());
        result = prime * result + ((getEnforceLawPeople() == null) ? 0 : getEnforceLawPeople().hashCode());
        result = prime * result + ((getEnforceLawTime() == null) ? 0 : getEnforceLawTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getPunishFilesName() == null) ? 0 : getPunishFilesName().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getEnterpriseIdName() == null) ? 0 : getEnterpriseIdName().hashCode());
        result = prime * result + ((getDailyTaskCode() == null) ? 0 : getDailyTaskCode().hashCode());
        return result;
    }
}