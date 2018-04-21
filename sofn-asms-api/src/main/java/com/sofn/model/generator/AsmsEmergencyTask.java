package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

@SuppressWarnings("serial")
public class AsmsEmergencyTask extends BaseModel {
    private String taskName;

    private String taskType;

    private String areaId;

    private Date taskBegin;

    private Date taskEnd;

    private String releaseUnit;

    private String releaseUnitLevel;

    private String isBearUnit;

    private String bearUnit;

    private String files;

    private String fileCode;

    private String enable;

    private String expertName;

    private String filesName;

    private String createOrgId;

    private String taskCode;

    private String status;

    private String expertId;

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public Date getTaskBegin() {
        return taskBegin;
    }

    public void setTaskBegin(Date taskBegin) {
        this.taskBegin = taskBegin;
    }

    public Date getTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(Date taskEnd) {
        this.taskEnd = taskEnd;
    }

    public String getReleaseUnit() {
        return releaseUnit;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit == null ? null : releaseUnit.trim();
    }

    public String getReleaseUnitLevel() {
        return releaseUnitLevel;
    }

    public void setReleaseUnitLevel(String releaseUnitLevel) {
        this.releaseUnitLevel = releaseUnitLevel == null ? null : releaseUnitLevel.trim();
    }

    public String getIsBearUnit() {
        return isBearUnit;
    }

    public void setIsBearUnit(String isBearUnit) {
        this.isBearUnit = isBearUnit == null ? null : isBearUnit.trim();
    }

    public String getBearUnit() {
        return bearUnit;
    }

    public void setBearUnit(String bearUnit) {
        this.bearUnit = bearUnit == null ? null : bearUnit.trim();
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files == null ? null : files.trim();
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode == null ? null : fileCode.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName == null ? null : expertName.trim();
    }

    public String getFilesName() {
        return filesName;
    }

    public void setFilesName(String filesName) {
        this.filesName = filesName == null ? null : filesName.trim();
    }

    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId == null ? null : createOrgId.trim();
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskName=").append(taskName);
        sb.append(", taskType=").append(taskType);
        sb.append(", areaId=").append(areaId);
        sb.append(", taskBegin=").append(taskBegin);
        sb.append(", taskEnd=").append(taskEnd);
        sb.append(", releaseUnit=").append(releaseUnit);
        sb.append(", releaseUnitLevel=").append(releaseUnitLevel);
        sb.append(", isBearUnit=").append(isBearUnit);
        sb.append(", bearUnit=").append(bearUnit);
        sb.append(", files=").append(files);
        sb.append(", fileCode=").append(fileCode);
        sb.append(", enable=").append(enable);
        sb.append(", expertName=").append(expertName);
        sb.append(", filesName=").append(filesName);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", taskCode=").append(taskCode);
        sb.append(", status=").append(status);
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
        AsmsEmergencyTask other = (AsmsEmergencyTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
            && (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getTaskBegin() == null ? other.getTaskBegin() == null : this.getTaskBegin().equals(other.getTaskBegin()))
            && (this.getTaskEnd() == null ? other.getTaskEnd() == null : this.getTaskEnd().equals(other.getTaskEnd()))
            && (this.getReleaseUnit() == null ? other.getReleaseUnit() == null : this.getReleaseUnit().equals(other.getReleaseUnit()))
            && (this.getReleaseUnitLevel() == null ? other.getReleaseUnitLevel() == null : this.getReleaseUnitLevel().equals(other.getReleaseUnitLevel()))
            && (this.getIsBearUnit() == null ? other.getIsBearUnit() == null : this.getIsBearUnit().equals(other.getIsBearUnit()))
            && (this.getBearUnit() == null ? other.getBearUnit() == null : this.getBearUnit().equals(other.getBearUnit()))
            && (this.getFiles() == null ? other.getFiles() == null : this.getFiles().equals(other.getFiles()))
            && (this.getFileCode() == null ? other.getFileCode() == null : this.getFileCode().equals(other.getFileCode()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getExpertName() == null ? other.getExpertName() == null : this.getExpertName().equals(other.getExpertName()))
            && (this.getFilesName() == null ? other.getFilesName() == null : this.getFilesName().equals(other.getFilesName()))
            && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
            && (this.getTaskCode() == null ? other.getTaskCode() == null : this.getTaskCode().equals(other.getTaskCode()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getTaskBegin() == null) ? 0 : getTaskBegin().hashCode());
        result = prime * result + ((getTaskEnd() == null) ? 0 : getTaskEnd().hashCode());
        result = prime * result + ((getReleaseUnit() == null) ? 0 : getReleaseUnit().hashCode());
        result = prime * result + ((getReleaseUnitLevel() == null) ? 0 : getReleaseUnitLevel().hashCode());
        result = prime * result + ((getIsBearUnit() == null) ? 0 : getIsBearUnit().hashCode());
        result = prime * result + ((getBearUnit() == null) ? 0 : getBearUnit().hashCode());
        result = prime * result + ((getFiles() == null) ? 0 : getFiles().hashCode());
        result = prime * result + ((getFileCode() == null) ? 0 : getFileCode().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getExpertName() == null) ? 0 : getExpertName().hashCode());
        result = prime * result + ((getFilesName() == null) ? 0 : getFilesName().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getTaskCode() == null) ? 0 : getTaskCode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}