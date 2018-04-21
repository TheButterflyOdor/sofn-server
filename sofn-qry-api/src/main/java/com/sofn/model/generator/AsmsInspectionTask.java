package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsInspectionTask extends BaseModel {
    private String taskType;

    private String taskDateType;

    private String taskDate;

    private String inspectionAreaId;

    private Long inspectionCount;

    private String enable;

    private String taskDateYear;

    private String taskDateMonths;

    private String taskDateQuarter;

    private String taskTypeName;

    private String createOrgId;

    private String createOrgName;

    private String attachmentAddress;

    private String attachmentName;

    private String detailContent;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getTaskDateType() {
        return taskDateType;
    }

    public void setTaskDateType(String taskDateType) {
        this.taskDateType = taskDateType == null ? null : taskDateType.trim();
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate == null ? null : taskDate.trim();
    }

    public String getInspectionAreaId() {
        return inspectionAreaId;
    }

    public void setInspectionAreaId(String inspectionAreaId) {
        this.inspectionAreaId = inspectionAreaId == null ? null : inspectionAreaId.trim();
    }

    public Long getInspectionCount() {
        return inspectionCount;
    }

    public void setInspectionCount(Long inspectionCount) {
        this.inspectionCount = inspectionCount;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getTaskDateYear() {
        return taskDateYear;
    }

    public void setTaskDateYear(String taskDateYear) {
        this.taskDateYear = taskDateYear == null ? null : taskDateYear.trim();
    }

    public String getTaskDateMonths() {
        return taskDateMonths;
    }

    public void setTaskDateMonths(String taskDateMonths) {
        this.taskDateMonths = taskDateMonths == null ? null : taskDateMonths.trim();
    }

    public String getTaskDateQuarter() {
        return taskDateQuarter;
    }

    public void setTaskDateQuarter(String taskDateQuarter) {
        this.taskDateQuarter = taskDateQuarter == null ? null : taskDateQuarter.trim();
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName == null ? null : taskTypeName.trim();
    }

    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId == null ? null : createOrgId.trim();
    }

    public String getCreateOrgName() {
        return createOrgName;
    }

    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName == null ? null : createOrgName.trim();
    }

    public String getAttachmentAddress() {
        return attachmentAddress;
    }

    public void setAttachmentAddress(String attachmentAddress) {
        this.attachmentAddress = attachmentAddress == null ? null : attachmentAddress.trim();
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName == null ? null : attachmentName.trim();
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent == null ? null : detailContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskType=").append(taskType);
        sb.append(", taskDateType=").append(taskDateType);
        sb.append(", taskDate=").append(taskDate);
        sb.append(", inspectionAreaId=").append(inspectionAreaId);
        sb.append(", inspectionCount=").append(inspectionCount);
        sb.append(", enable=").append(enable);
        sb.append(", taskDateYear=").append(taskDateYear);
        sb.append(", taskDateMonths=").append(taskDateMonths);
        sb.append(", taskDateQuarter=").append(taskDateQuarter);
        sb.append(", taskTypeName=").append(taskTypeName);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", createOrgName=").append(createOrgName);
        sb.append(", attachmentAddress=").append(attachmentAddress);
        sb.append(", attachmentName=").append(attachmentName);
        sb.append(", detailContent=").append(detailContent);
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
        AsmsInspectionTask other = (AsmsInspectionTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
                && (this.getTaskDateType() == null ? other.getTaskDateType() == null : this.getTaskDateType().equals(other.getTaskDateType()))
                && (this.getTaskDate() == null ? other.getTaskDate() == null : this.getTaskDate().equals(other.getTaskDate()))
                && (this.getInspectionAreaId() == null ? other.getInspectionAreaId() == null : this.getInspectionAreaId().equals(other.getInspectionAreaId()))
                && (this.getInspectionCount() == null ? other.getInspectionCount() == null : this.getInspectionCount().equals(other.getInspectionCount()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
                && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
                && (this.getTaskDateYear() == null ? other.getTaskDateYear() == null : this.getTaskDateYear().equals(other.getTaskDateYear()))
                && (this.getTaskDateMonths() == null ? other.getTaskDateMonths() == null : this.getTaskDateMonths().equals(other.getTaskDateMonths()))
                && (this.getTaskDateQuarter() == null ? other.getTaskDateQuarter() == null : this.getTaskDateQuarter().equals(other.getTaskDateQuarter()))
                && (this.getTaskTypeName() == null ? other.getTaskTypeName() == null : this.getTaskTypeName().equals(other.getTaskTypeName()))
                && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
                && (this.getCreateOrgName() == null ? other.getCreateOrgName() == null : this.getCreateOrgName().equals(other.getCreateOrgName()))
                && (this.getAttachmentAddress() == null ? other.getAttachmentAddress() == null : this.getAttachmentAddress().equals(other.getAttachmentAddress()))
                && (this.getAttachmentName() == null ? other.getAttachmentName() == null : this.getAttachmentName().equals(other.getAttachmentName()))
                && (this.getDetailContent() == null ? other.getDetailContent() == null : this.getDetailContent().equals(other.getDetailContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getTaskDateType() == null) ? 0 : getTaskDateType().hashCode());
        result = prime * result + ((getTaskDate() == null) ? 0 : getTaskDate().hashCode());
        result = prime * result + ((getInspectionAreaId() == null) ? 0 : getInspectionAreaId().hashCode());
        result = prime * result + ((getInspectionCount() == null) ? 0 : getInspectionCount().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getTaskDateYear() == null) ? 0 : getTaskDateYear().hashCode());
        result = prime * result + ((getTaskDateMonths() == null) ? 0 : getTaskDateMonths().hashCode());
        result = prime * result + ((getTaskDateQuarter() == null) ? 0 : getTaskDateQuarter().hashCode());
        result = prime * result + ((getTaskTypeName() == null) ? 0 : getTaskTypeName().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getCreateOrgName() == null) ? 0 : getCreateOrgName().hashCode());
        result = prime * result + ((getAttachmentAddress() == null) ? 0 : getAttachmentAddress().hashCode());
        result = prime * result + ((getAttachmentName() == null) ? 0 : getAttachmentName().hashCode());
        result = prime * result + ((getDetailContent() == null) ? 0 : getDetailContent().hashCode());
        return result;
    }
}