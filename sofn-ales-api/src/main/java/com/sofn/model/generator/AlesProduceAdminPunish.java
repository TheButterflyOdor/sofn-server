package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;
import java.util.Date;

/**
 * 行政处罚
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@SuppressWarnings("serial")
public class AlesProduceAdminPunish extends BaseModel {
    private String punishCode;//行政处罚决定案号

    private String legalPerson;//当事人

    private String caseName;//案件名称

    private String productName;//违反产品名称

    private String enterpriseName;//标称生产企业

    private String manufactureDate;//生产日期或批次

    private String punishQualitative;//案件定性

    private String punishResult;//行政处罚决定

    private String punishFiles;//决定书附件

    private String enable;//是否删除

    private String enterpriseId;//当事主体ID

    private String enterpriseCode;//当事主体身份码

    private String enforceLawId;//执法单位ID

    private String enforceLawPeople;//执法人员

    private Date enforceLawTime;//执法时间

    private String punishFilesName;//附件名称

    private String createOrgId;//创建机构ID

    private String enterpriseIdName;//当事主体名称

    private String dailyTaskCode;//现场巡查ID，跟现场巡查进行关联

    private String penaltySource;//行政处罚来源

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

    public String getPenaltySource() {
        return penaltySource;
    }

    public void setPenaltySource(String penaltySource) {
        this.penaltySource = penaltySource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlesProduceAdminPunish that = (AlesProduceAdminPunish) o;

        if (punishCode != null ? !punishCode.equals(that.punishCode) : that.punishCode != null) return false;
        if (legalPerson != null ? !legalPerson.equals(that.legalPerson) : that.legalPerson != null) return false;
        if (caseName != null ? !caseName.equals(that.caseName) : that.caseName != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (enterpriseName != null ? !enterpriseName.equals(that.enterpriseName) : that.enterpriseName != null)
            return false;
        if (manufactureDate != null ? !manufactureDate.equals(that.manufactureDate) : that.manufactureDate != null)
            return false;
        if (punishQualitative != null ? !punishQualitative.equals(that.punishQualitative) : that.punishQualitative != null)
            return false;
        if (punishResult != null ? !punishResult.equals(that.punishResult) : that.punishResult != null) return false;
        if (punishFiles != null ? !punishFiles.equals(that.punishFiles) : that.punishFiles != null) return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (enterpriseId != null ? !enterpriseId.equals(that.enterpriseId) : that.enterpriseId != null) return false;
        if (enterpriseCode != null ? !enterpriseCode.equals(that.enterpriseCode) : that.enterpriseCode != null)
            return false;
        if (enforceLawId != null ? !enforceLawId.equals(that.enforceLawId) : that.enforceLawId != null) return false;
        if (enforceLawPeople != null ? !enforceLawPeople.equals(that.enforceLawPeople) : that.enforceLawPeople != null)
            return false;
        if (enforceLawTime != null ? !enforceLawTime.equals(that.enforceLawTime) : that.enforceLawTime != null)
            return false;
        if (punishFilesName != null ? !punishFilesName.equals(that.punishFilesName) : that.punishFilesName != null)
            return false;
        if (createOrgId != null ? !createOrgId.equals(that.createOrgId) : that.createOrgId != null) return false;
        if (enterpriseIdName != null ? !enterpriseIdName.equals(that.enterpriseIdName) : that.enterpriseIdName != null)
            return false;
        if (dailyTaskCode != null ? !dailyTaskCode.equals(that.dailyTaskCode) : that.dailyTaskCode != null)
            return false;
        return penaltySource != null ? penaltySource.equals(that.penaltySource) : that.penaltySource == null;

    }

    @Override
    public int hashCode() {
        int result = punishCode != null ? punishCode.hashCode() : 0;
        result = 31 * result + (legalPerson != null ? legalPerson.hashCode() : 0);
        result = 31 * result + (caseName != null ? caseName.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (enterpriseName != null ? enterpriseName.hashCode() : 0);
        result = 31 * result + (manufactureDate != null ? manufactureDate.hashCode() : 0);
        result = 31 * result + (punishQualitative != null ? punishQualitative.hashCode() : 0);
        result = 31 * result + (punishResult != null ? punishResult.hashCode() : 0);
        result = 31 * result + (punishFiles != null ? punishFiles.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (enterpriseId != null ? enterpriseId.hashCode() : 0);
        result = 31 * result + (enterpriseCode != null ? enterpriseCode.hashCode() : 0);
        result = 31 * result + (enforceLawId != null ? enforceLawId.hashCode() : 0);
        result = 31 * result + (enforceLawPeople != null ? enforceLawPeople.hashCode() : 0);
        result = 31 * result + (enforceLawTime != null ? enforceLawTime.hashCode() : 0);
        result = 31 * result + (punishFilesName != null ? punishFilesName.hashCode() : 0);
        result = 31 * result + (createOrgId != null ? createOrgId.hashCode() : 0);
        result = 31 * result + (enterpriseIdName != null ? enterpriseIdName.hashCode() : 0);
        result = 31 * result + (dailyTaskCode != null ? dailyTaskCode.hashCode() : 0);
        result = 31 * result + (penaltySource != null ? penaltySource.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AlesProduceAdminPunish{" +
                "punishCode='" + punishCode + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", caseName='" + caseName + '\'' +
                ", productName='" + productName + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", manufactureDate='" + manufactureDate + '\'' +
                ", punishQualitative='" + punishQualitative + '\'' +
                ", punishResult='" + punishResult + '\'' +
                ", punishFiles='" + punishFiles + '\'' +
                ", enable='" + enable + '\'' +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enforceLawId='" + enforceLawId + '\'' +
                ", enforceLawPeople='" + enforceLawPeople + '\'' +
                ", enforceLawTime=" + enforceLawTime +
                ", punishFilesName='" + punishFilesName + '\'' +
                ", createOrgId='" + createOrgId + '\'' +
                ", enterpriseIdName='" + enterpriseIdName + '\'' +
                ", dailyTaskCode='" + dailyTaskCode + '\'' +
                ", penaltySource='" + penaltySource + '\'' +
                '}';
    }
}