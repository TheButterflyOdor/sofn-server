package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

@SuppressWarnings("serial")
public class AsmsCheckTask extends BaseModel {
    private String taskName;

    private String taskType;

    private String taskYear;

    private Date taskBeginTime;

    private Date taskEndTime;

    private String taskReleaseUnit;

    private String taskIsSeparate;

    private String taskAreaId;

    private String taskIndustry;

    private Date taskSampleDeadline;

    private String files;

    private String fileCode;

    private String taskLevel;

    private String state;

    private String enable;

    private String createOrgRegionId;

    private String baseInspectionId;

    private String cyUnitId;

    private String cyUnitName;

    private String jcUnitId;

    private String jcUnitName;

    private String isSample;

    private String judgeStandard;

    private String judgeStandardId;

    private String detectionStandard;

    private String detectionStandardId;

    private String parentTaskId;

    private String parentTaskName;

    private String createOrgId;

    private String createOrgName;

    private String jcStandardLinkId;

    private String pdStandardLinkId;

    private String levelEnum;

    private String filesname;

    private String jdUnitId;

    private String jdUnitName;

    private String attachmentAddress;

    private String attachmentName;

    private String fileNumber;

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

    public String getTaskYear() {
        return taskYear;
    }

    public void setTaskYear(String taskYear) {
        this.taskYear = taskYear == null ? null : taskYear.trim();
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

    public String getTaskReleaseUnit() {
        return taskReleaseUnit;
    }

    public void setTaskReleaseUnit(String taskReleaseUnit) {
        this.taskReleaseUnit = taskReleaseUnit == null ? null : taskReleaseUnit.trim();
    }

    public String getTaskIsSeparate() {
        return taskIsSeparate;
    }

    public void setTaskIsSeparate(String taskIsSeparate) {
        this.taskIsSeparate = taskIsSeparate == null ? null : taskIsSeparate.trim();
    }

    public String getTaskAreaId() {
        return taskAreaId;
    }

    public void setTaskAreaId(String taskAreaId) {
        this.taskAreaId = taskAreaId == null ? null : taskAreaId.trim();
    }

    public String getTaskIndustry() {
        return taskIndustry;
    }

    public void setTaskIndustry(String taskIndustry) {
        this.taskIndustry = taskIndustry == null ? null : taskIndustry.trim();
    }

    public Date getTaskSampleDeadline() {
        return taskSampleDeadline;
    }

    public void setTaskSampleDeadline(Date taskSampleDeadline) {
        this.taskSampleDeadline = taskSampleDeadline;
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

    public String getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(String taskLevel) {
        this.taskLevel = taskLevel == null ? null : taskLevel.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getCreateOrgRegionId() {
        return createOrgRegionId;
    }

    public void setCreateOrgRegionId(String createOrgRegionId) {
        this.createOrgRegionId = createOrgRegionId == null ? null : createOrgRegionId.trim();
    }

    public String getBaseInspectionId() {
        return baseInspectionId;
    }

    public void setBaseInspectionId(String baseInspectionId) {
        this.baseInspectionId = baseInspectionId == null ? null : baseInspectionId.trim();
    }

    public String getCyUnitId() {
        return cyUnitId;
    }

    public void setCyUnitId(String cyUnitId) {
        this.cyUnitId = cyUnitId == null ? null : cyUnitId.trim();
    }

    public String getCyUnitName() {
        return cyUnitName;
    }

    public void setCyUnitName(String cyUnitName) {
        this.cyUnitName = cyUnitName == null ? null : cyUnitName.trim();
    }

    public String getJcUnitId() {
        return jcUnitId;
    }

    public void setJcUnitId(String jcUnitId) {
        this.jcUnitId = jcUnitId == null ? null : jcUnitId.trim();
    }

    public String getJcUnitName() {
        return jcUnitName;
    }

    public void setJcUnitName(String jcUnitName) {
        this.jcUnitName = jcUnitName == null ? null : jcUnitName.trim();
    }

    public String getIsSample() {
        return isSample;
    }

    public void setIsSample(String isSample) {
        this.isSample = isSample == null ? null : isSample.trim();
    }

    public String getJudgeStandard() {
        return judgeStandard;
    }

    public void setJudgeStandard(String judgeStandard) {
        this.judgeStandard = judgeStandard == null ? null : judgeStandard.trim();
    }

    public String getJudgeStandardId() {
        return judgeStandardId;
    }

    public void setJudgeStandardId(String judgeStandardId) {
        this.judgeStandardId = judgeStandardId == null ? null : judgeStandardId.trim();
    }

    public String getDetectionStandard() {
        return detectionStandard;
    }

    public void setDetectionStandard(String detectionStandard) {
        this.detectionStandard = detectionStandard == null ? null : detectionStandard.trim();
    }

    public String getDetectionStandardId() {
        return detectionStandardId;
    }

    public void setDetectionStandardId(String detectionStandardId) {
        this.detectionStandardId = detectionStandardId == null ? null : detectionStandardId.trim();
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId == null ? null : parentTaskId.trim();
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName == null ? null : parentTaskName.trim();
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

    public String getJcStandardLinkId() {
        return jcStandardLinkId;
    }

    public void setJcStandardLinkId(String jcStandardLinkId) {
        this.jcStandardLinkId = jcStandardLinkId == null ? null : jcStandardLinkId.trim();
    }

    public String getPdStandardLinkId() {
        return pdStandardLinkId;
    }

    public void setPdStandardLinkId(String pdStandardLinkId) {
        this.pdStandardLinkId = pdStandardLinkId == null ? null : pdStandardLinkId.trim();
    }

    public String getLevelEnum() {
        return levelEnum;
    }

    public void setLevelEnum(String levelEnum) {
        this.levelEnum = levelEnum == null ? null : levelEnum.trim();
    }

    public String getFilesname() {
        return filesname;
    }

    public void setFilesname(String filesname) {
        this.filesname = filesname == null ? null : filesname.trim();
    }

    public String getJdUnitId() {
        return jdUnitId;
    }

    public void setJdUnitId(String jdUnitId) {
        this.jdUnitId = jdUnitId == null ? null : jdUnitId.trim();
    }

    public String getJdUnitName() {
        return jdUnitName;
    }

    public void setJdUnitName(String jdUnitName) {
        this.jdUnitName = jdUnitName == null ? null : jdUnitName.trim();
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

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber == null ? null : fileNumber.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskName=").append(taskName);
        sb.append(", taskType=").append(taskType);
        sb.append(", taskYear=").append(taskYear);
        sb.append(", taskBeginTime=").append(taskBeginTime);
        sb.append(", taskEndTime=").append(taskEndTime);
        sb.append(", taskReleaseUnit=").append(taskReleaseUnit);
        sb.append(", taskIsSeparate=").append(taskIsSeparate);
        sb.append(", taskAreaId=").append(taskAreaId);
        sb.append(", taskIndustry=").append(taskIndustry);
        sb.append(", taskSampleDeadline=").append(taskSampleDeadline);
        sb.append(", files=").append(files);
        sb.append(", fileCode=").append(fileCode);
        sb.append(", taskLevel=").append(taskLevel);
        sb.append(", state=").append(state);
        sb.append(", enable=").append(enable);
        sb.append(", createOrgRegionId=").append(createOrgRegionId);
        sb.append(", baseInspectionId=").append(baseInspectionId);
        sb.append(", cyUnitId=").append(cyUnitId);
        sb.append(", cyUnitName=").append(cyUnitName);
        sb.append(", jcUnitId=").append(jcUnitId);
        sb.append(", jcUnitName=").append(jcUnitName);
        sb.append(", isSample=").append(isSample);
        sb.append(", judgeStandard=").append(judgeStandard);
        sb.append(", judgeStandardId=").append(judgeStandardId);
        sb.append(", detectionStandard=").append(detectionStandard);
        sb.append(", detectionStandardId=").append(detectionStandardId);
        sb.append(", parentTaskId=").append(parentTaskId);
        sb.append(", parentTaskName=").append(parentTaskName);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", createOrgName=").append(createOrgName);
        sb.append(", jcStandardLinkId=").append(jcStandardLinkId);
        sb.append(", pdStandardLinkId=").append(pdStandardLinkId);
        sb.append(", levelEnum=").append(levelEnum);
        sb.append(", filesname=").append(filesname);
        sb.append(", jdUnitId=").append(jdUnitId);
        sb.append(", jdUnitName=").append(jdUnitName);
        sb.append(", attachmentAddress=").append(attachmentAddress);
        sb.append(", attachmentName=").append(attachmentName);
        sb.append(", fileNumber=").append(fileNumber);
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
        AsmsCheckTask other = (AsmsCheckTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
            && (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getTaskYear() == null ? other.getTaskYear() == null : this.getTaskYear().equals(other.getTaskYear()))
            && (this.getTaskBeginTime() == null ? other.getTaskBeginTime() == null : this.getTaskBeginTime().equals(other.getTaskBeginTime()))
            && (this.getTaskEndTime() == null ? other.getTaskEndTime() == null : this.getTaskEndTime().equals(other.getTaskEndTime()))
            && (this.getTaskReleaseUnit() == null ? other.getTaskReleaseUnit() == null : this.getTaskReleaseUnit().equals(other.getTaskReleaseUnit()))
            && (this.getTaskIsSeparate() == null ? other.getTaskIsSeparate() == null : this.getTaskIsSeparate().equals(other.getTaskIsSeparate()))
            && (this.getTaskAreaId() == null ? other.getTaskAreaId() == null : this.getTaskAreaId().equals(other.getTaskAreaId()))
            && (this.getTaskIndustry() == null ? other.getTaskIndustry() == null : this.getTaskIndustry().equals(other.getTaskIndustry()))
            && (this.getTaskSampleDeadline() == null ? other.getTaskSampleDeadline() == null : this.getTaskSampleDeadline().equals(other.getTaskSampleDeadline()))
            && (this.getFiles() == null ? other.getFiles() == null : this.getFiles().equals(other.getFiles()))
            && (this.getFileCode() == null ? other.getFileCode() == null : this.getFileCode().equals(other.getFileCode()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getTaskLevel() == null ? other.getTaskLevel() == null : this.getTaskLevel().equals(other.getTaskLevel()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateOrgRegionId() == null ? other.getCreateOrgRegionId() == null : this.getCreateOrgRegionId().equals(other.getCreateOrgRegionId()))
            && (this.getBaseInspectionId() == null ? other.getBaseInspectionId() == null : this.getBaseInspectionId().equals(other.getBaseInspectionId()))
            && (this.getCyUnitId() == null ? other.getCyUnitId() == null : this.getCyUnitId().equals(other.getCyUnitId()))
            && (this.getCyUnitName() == null ? other.getCyUnitName() == null : this.getCyUnitName().equals(other.getCyUnitName()))
            && (this.getJcUnitId() == null ? other.getJcUnitId() == null : this.getJcUnitId().equals(other.getJcUnitId()))
            && (this.getJcUnitName() == null ? other.getJcUnitName() == null : this.getJcUnitName().equals(other.getJcUnitName()))
            && (this.getIsSample() == null ? other.getIsSample() == null : this.getIsSample().equals(other.getIsSample()))
            && (this.getJudgeStandard() == null ? other.getJudgeStandard() == null : this.getJudgeStandard().equals(other.getJudgeStandard()))
            && (this.getJudgeStandardId() == null ? other.getJudgeStandardId() == null : this.getJudgeStandardId().equals(other.getJudgeStandardId()))
            && (this.getDetectionStandard() == null ? other.getDetectionStandard() == null : this.getDetectionStandard().equals(other.getDetectionStandard()))
            && (this.getDetectionStandardId() == null ? other.getDetectionStandardId() == null : this.getDetectionStandardId().equals(other.getDetectionStandardId()))
            && (this.getParentTaskId() == null ? other.getParentTaskId() == null : this.getParentTaskId().equals(other.getParentTaskId()))
            && (this.getParentTaskName() == null ? other.getParentTaskName() == null : this.getParentTaskName().equals(other.getParentTaskName()))
            && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
            && (this.getCreateOrgName() == null ? other.getCreateOrgName() == null : this.getCreateOrgName().equals(other.getCreateOrgName()))
            && (this.getJcStandardLinkId() == null ? other.getJcStandardLinkId() == null : this.getJcStandardLinkId().equals(other.getJcStandardLinkId()))
            && (this.getPdStandardLinkId() == null ? other.getPdStandardLinkId() == null : this.getPdStandardLinkId().equals(other.getPdStandardLinkId()))
            && (this.getLevelEnum() == null ? other.getLevelEnum() == null : this.getLevelEnum().equals(other.getLevelEnum()))
            && (this.getFilesname() == null ? other.getFilesname() == null : this.getFilesname().equals(other.getFilesname()))
            && (this.getAttachmentAddress() == null ? other.getAttachmentAddress() == null : this.getAttachmentAddress().equals(other.getAttachmentAddress()))
            && (this.getAttachmentName() == null ? other.getAttachmentName() == null : this.getAttachmentName().equals(other.getAttachmentName()))
            && (this.getFileNumber() == null ? other.getFileNumber() == null : this.getFileNumber().equals(other.getFileNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getTaskYear() == null) ? 0 : getTaskYear().hashCode());
        result = prime * result + ((getTaskBeginTime() == null) ? 0 : getTaskBeginTime().hashCode());
        result = prime * result + ((getTaskEndTime() == null) ? 0 : getTaskEndTime().hashCode());
        result = prime * result + ((getTaskReleaseUnit() == null) ? 0 : getTaskReleaseUnit().hashCode());
        result = prime * result + ((getTaskIsSeparate() == null) ? 0 : getTaskIsSeparate().hashCode());
        result = prime * result + ((getTaskAreaId() == null) ? 0 : getTaskAreaId().hashCode());
        result = prime * result + ((getTaskIndustry() == null) ? 0 : getTaskIndustry().hashCode());
        result = prime * result + ((getTaskSampleDeadline() == null) ? 0 : getTaskSampleDeadline().hashCode());
        result = prime * result + ((getFiles() == null) ? 0 : getFiles().hashCode());
        result = prime * result + ((getFileCode() == null) ? 0 : getFileCode().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getTaskLevel() == null) ? 0 : getTaskLevel().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateOrgRegionId() == null) ? 0 : getCreateOrgRegionId().hashCode());
        result = prime * result + ((getBaseInspectionId() == null) ? 0 : getBaseInspectionId().hashCode());
        result = prime * result + ((getCyUnitId() == null) ? 0 : getCyUnitId().hashCode());
        result = prime * result + ((getCyUnitName() == null) ? 0 : getCyUnitName().hashCode());
        result = prime * result + ((getJcUnitId() == null) ? 0 : getJcUnitId().hashCode());
        result = prime * result + ((getJcUnitName() == null) ? 0 : getJcUnitName().hashCode());
        result = prime * result + ((getIsSample() == null) ? 0 : getIsSample().hashCode());
        result = prime * result + ((getJudgeStandard() == null) ? 0 : getJudgeStandard().hashCode());
        result = prime * result + ((getJudgeStandardId() == null) ? 0 : getJudgeStandardId().hashCode());
        result = prime * result + ((getDetectionStandard() == null) ? 0 : getDetectionStandard().hashCode());
        result = prime * result + ((getDetectionStandardId() == null) ? 0 : getDetectionStandardId().hashCode());
        result = prime * result + ((getParentTaskId() == null) ? 0 : getParentTaskId().hashCode());
        result = prime * result + ((getParentTaskName() == null) ? 0 : getParentTaskName().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getCreateOrgName() == null) ? 0 : getCreateOrgName().hashCode());
        result = prime * result + ((getJcStandardLinkId() == null) ? 0 : getJcStandardLinkId().hashCode());
        result = prime * result + ((getPdStandardLinkId() == null) ? 0 : getPdStandardLinkId().hashCode());
        result = prime * result + ((getLevelEnum() == null) ? 0 : getLevelEnum().hashCode());
        result = prime * result + ((getFilesname() == null) ? 0 : getFilesname().hashCode());
        result = prime * result + ((getAttachmentAddress() == null) ? 0 : getAttachmentAddress().hashCode());
        result = prime * result + ((getAttachmentName() == null) ? 0 : getAttachmentName().hashCode());
        result = prime * result + ((getFileNumber() == null) ? 0 : getFileNumber().hashCode());
        return result;
    }
}