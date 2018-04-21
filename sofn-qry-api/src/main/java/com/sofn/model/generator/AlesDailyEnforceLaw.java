package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;
import java.util.Date;

@SuppressWarnings("serial")
public class AlesDailyEnforceLaw extends BaseModel {
    private String taskName;

    private Date taskBeginTime;

    private Date taskEndTime;

    private String enterpriseId;

    private String areaId;

    private String enterpriseAddress;

    private String taskPersonCount;

    private String taskPersonId;

    private String enforceLawResult;

    private String enable;

    private String enterpriseName;

    private String createOrgId;

    private String enforceLawResultFlag;

    private String taskPersonName;

    private String enterpriseIdcode;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Date getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(Date taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress == null ? null : enterpriseAddress.trim();
    }

    public String getTaskPersonCount() {
        return taskPersonCount;
    }

    public void setTaskPersonCount(String taskPersonCount) {
        this.taskPersonCount = taskPersonCount == null ? null : taskPersonCount.trim();
    }

    public String getTaskPersonId() {
        return taskPersonId;
    }

    public void setTaskPersonId(String taskPersonId) {
        this.taskPersonId = taskPersonId == null ? null : taskPersonId.trim();
    }

    public String getEnforceLawResult() {
        return enforceLawResult;
    }

    public void setEnforceLawResult(String enforceLawResult) {
        this.enforceLawResult = enforceLawResult == null ? null : enforceLawResult.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId == null ? null : createOrgId.trim();
    }

    public String getEnforceLawResultFlag() {
        return enforceLawResultFlag;
    }

    public void setEnforceLawResultFlag(String enforceLawResultFlag) {
        this.enforceLawResultFlag = enforceLawResultFlag == null ? null : enforceLawResultFlag.trim();
    }

    public String getTaskPersonName() {
        return taskPersonName;
    }

    public void setTaskPersonName(String taskPersonName) {
        this.taskPersonName = taskPersonName == null ? null : taskPersonName.trim();
    }

    public String getEnterpriseIdcode() {
        return enterpriseIdcode;
    }

    public void setEnterpriseIdcode(String enterpriseIdcode) {
        this.enterpriseIdcode = enterpriseIdcode == null ? null : enterpriseIdcode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskName=").append(taskName);
        sb.append(", taskBeginTime=").append(taskBeginTime);
        sb.append(", taskEndTime=").append(taskEndTime);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", areaId=").append(areaId);
        sb.append(", enterpriseAddress=").append(enterpriseAddress);
        sb.append(", taskPersonCount=").append(taskPersonCount);
        sb.append(", taskPersonId=").append(taskPersonId);
        sb.append(", enforceLawResult=").append(enforceLawResult);
        sb.append(", enable=").append(enable);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", enforceLawResultFlag=").append(enforceLawResultFlag);
        sb.append(", taskPersonName=").append(taskPersonName);
        sb.append(", enterpriseIdcode=").append(enterpriseIdcode);
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
        AlesDailyEnforceLaw other = (AlesDailyEnforceLaw) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
                && (this.getTaskBeginTime() == null ? other.getTaskBeginTime() == null : this.getTaskBeginTime().equals(other.getTaskBeginTime()))
                && (this.getTaskEndTime() == null ? other.getTaskEndTime() == null : this.getTaskEndTime().equals(other.getTaskEndTime()))
                && (this.getEnterpriseId() == null ? other.getEnterpriseId() == null : this.getEnterpriseId().equals(other.getEnterpriseId()))
                && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
                && (this.getEnterpriseAddress() == null ? other.getEnterpriseAddress() == null : this.getEnterpriseAddress().equals(other.getEnterpriseAddress()))
                && (this.getTaskPersonCount() == null ? other.getTaskPersonCount() == null : this.getTaskPersonCount().equals(other.getTaskPersonCount()))
                && (this.getTaskPersonId() == null ? other.getTaskPersonId() == null : this.getTaskPersonId().equals(other.getTaskPersonId()))
                && (this.getEnforceLawResult() == null ? other.getEnforceLawResult() == null : this.getEnforceLawResult().equals(other.getEnforceLawResult()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
                && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
                && (this.getEnterpriseName() == null ? other.getEnterpriseName() == null : this.getEnterpriseName().equals(other.getEnterpriseName()))
                && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
                && (this.getEnforceLawResultFlag() == null ? other.getEnforceLawResultFlag() == null : this.getEnforceLawResultFlag().equals(other.getEnforceLawResultFlag()))
                && (this.getTaskPersonName() == null ? other.getTaskPersonName() == null : this.getTaskPersonName().equals(other.getTaskPersonName()))
                && (this.getEnterpriseIdcode() == null ? other.getEnterpriseIdcode() == null : this.getEnterpriseIdcode().equals(other.getEnterpriseIdcode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getTaskBeginTime() == null) ? 0 : getTaskBeginTime().hashCode());
        result = prime * result + ((getTaskEndTime() == null) ? 0 : getTaskEndTime().hashCode());
        result = prime * result + ((getEnterpriseId() == null) ? 0 : getEnterpriseId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getEnterpriseAddress() == null) ? 0 : getEnterpriseAddress().hashCode());
        result = prime * result + ((getTaskPersonCount() == null) ? 0 : getTaskPersonCount().hashCode());
        result = prime * result + ((getTaskPersonId() == null) ? 0 : getTaskPersonId().hashCode());
        result = prime * result + ((getEnforceLawResult() == null) ? 0 : getEnforceLawResult().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getEnterpriseName() == null) ? 0 : getEnterpriseName().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getEnforceLawResultFlag() == null) ? 0 : getEnforceLawResultFlag().hashCode());
        result = prime * result + ((getTaskPersonName() == null) ? 0 : getTaskPersonName().hashCode());
        result = prime * result + ((getEnterpriseIdcode() == null) ? 0 : getEnterpriseIdcode().hashCode());
        return result;
    }
}