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
        this.jdUnitId = jdUnitId;
    }

    public String getJdUnitName() {
        return jdUnitName;
    }

    public void setJdUnitName(String jdUnitName) {
        this.jdUnitName = jdUnitName;
    }

    public String getAttachmentAddress() {
        return attachmentAddress;
    }

    public void setAttachmentAddress(String attachmentAddress) {
        this.attachmentAddress = attachmentAddress;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsmsCheckTask that = (AsmsCheckTask) o;

        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (taskType != null ? !taskType.equals(that.taskType) : that.taskType != null) return false;
        if (taskYear != null ? !taskYear.equals(that.taskYear) : that.taskYear != null) return false;
        if (taskBeginTime != null ? !taskBeginTime.equals(that.taskBeginTime) : that.taskBeginTime != null)
            return false;
        if (taskEndTime != null ? !taskEndTime.equals(that.taskEndTime) : that.taskEndTime != null) return false;
        if (taskReleaseUnit != null ? !taskReleaseUnit.equals(that.taskReleaseUnit) : that.taskReleaseUnit != null)
            return false;
        if (taskIsSeparate != null ? !taskIsSeparate.equals(that.taskIsSeparate) : that.taskIsSeparate != null)
            return false;
        if (taskAreaId != null ? !taskAreaId.equals(that.taskAreaId) : that.taskAreaId != null) return false;
        if (taskIndustry != null ? !taskIndustry.equals(that.taskIndustry) : that.taskIndustry != null) return false;
        if (taskSampleDeadline != null ? !taskSampleDeadline.equals(that.taskSampleDeadline) : that.taskSampleDeadline != null)
            return false;
        if (files != null ? !files.equals(that.files) : that.files != null) return false;
        if (fileCode != null ? !fileCode.equals(that.fileCode) : that.fileCode != null) return false;
        if (taskLevel != null ? !taskLevel.equals(that.taskLevel) : that.taskLevel != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (createOrgRegionId != null ? !createOrgRegionId.equals(that.createOrgRegionId) : that.createOrgRegionId != null)
            return false;
        if (baseInspectionId != null ? !baseInspectionId.equals(that.baseInspectionId) : that.baseInspectionId != null)
            return false;
        if (cyUnitId != null ? !cyUnitId.equals(that.cyUnitId) : that.cyUnitId != null) return false;
        if (cyUnitName != null ? !cyUnitName.equals(that.cyUnitName) : that.cyUnitName != null) return false;
        if (jcUnitId != null ? !jcUnitId.equals(that.jcUnitId) : that.jcUnitId != null) return false;
        if (jcUnitName != null ? !jcUnitName.equals(that.jcUnitName) : that.jcUnitName != null) return false;
        if (isSample != null ? !isSample.equals(that.isSample) : that.isSample != null) return false;
        if (judgeStandard != null ? !judgeStandard.equals(that.judgeStandard) : that.judgeStandard != null)
            return false;
        if (judgeStandardId != null ? !judgeStandardId.equals(that.judgeStandardId) : that.judgeStandardId != null)
            return false;
        if (detectionStandard != null ? !detectionStandard.equals(that.detectionStandard) : that.detectionStandard != null)
            return false;
        if (detectionStandardId != null ? !detectionStandardId.equals(that.detectionStandardId) : that.detectionStandardId != null)
            return false;
        if (parentTaskId != null ? !parentTaskId.equals(that.parentTaskId) : that.parentTaskId != null) return false;
        if (parentTaskName != null ? !parentTaskName.equals(that.parentTaskName) : that.parentTaskName != null)
            return false;
        if (createOrgId != null ? !createOrgId.equals(that.createOrgId) : that.createOrgId != null) return false;
        if (createOrgName != null ? !createOrgName.equals(that.createOrgName) : that.createOrgName != null)
            return false;
        if (jcStandardLinkId != null ? !jcStandardLinkId.equals(that.jcStandardLinkId) : that.jcStandardLinkId != null)
            return false;
        if (pdStandardLinkId != null ? !pdStandardLinkId.equals(that.pdStandardLinkId) : that.pdStandardLinkId != null)
            return false;
        if (levelEnum != null ? !levelEnum.equals(that.levelEnum) : that.levelEnum != null) return false;
        if (filesname != null ? !filesname.equals(that.filesname) : that.filesname != null) return false;
        if (jdUnitId != null ? !jdUnitId.equals(that.jdUnitId) : that.jdUnitId != null) return false;
        if (jdUnitName != null ? !jdUnitName.equals(that.jdUnitName) : that.jdUnitName != null) return false;
        if (attachmentAddress != null ? !attachmentAddress.equals(that.attachmentAddress) : that.attachmentAddress != null)
            return false;
        if (attachmentName != null ? !attachmentName.equals(that.attachmentName) : that.attachmentName != null)
            return false;
        return fileNumber != null ? fileNumber.equals(that.fileNumber) : that.fileNumber == null;

    }

    @Override
    public int hashCode() {
        int result = taskName != null ? taskName.hashCode() : 0;
        result = 31 * result + (taskType != null ? taskType.hashCode() : 0);
        result = 31 * result + (taskYear != null ? taskYear.hashCode() : 0);
        result = 31 * result + (taskBeginTime != null ? taskBeginTime.hashCode() : 0);
        result = 31 * result + (taskEndTime != null ? taskEndTime.hashCode() : 0);
        result = 31 * result + (taskReleaseUnit != null ? taskReleaseUnit.hashCode() : 0);
        result = 31 * result + (taskIsSeparate != null ? taskIsSeparate.hashCode() : 0);
        result = 31 * result + (taskAreaId != null ? taskAreaId.hashCode() : 0);
        result = 31 * result + (taskIndustry != null ? taskIndustry.hashCode() : 0);
        result = 31 * result + (taskSampleDeadline != null ? taskSampleDeadline.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        result = 31 * result + (fileCode != null ? fileCode.hashCode() : 0);
        result = 31 * result + (taskLevel != null ? taskLevel.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (createOrgRegionId != null ? createOrgRegionId.hashCode() : 0);
        result = 31 * result + (baseInspectionId != null ? baseInspectionId.hashCode() : 0);
        result = 31 * result + (cyUnitId != null ? cyUnitId.hashCode() : 0);
        result = 31 * result + (cyUnitName != null ? cyUnitName.hashCode() : 0);
        result = 31 * result + (jcUnitId != null ? jcUnitId.hashCode() : 0);
        result = 31 * result + (jcUnitName != null ? jcUnitName.hashCode() : 0);
        result = 31 * result + (isSample != null ? isSample.hashCode() : 0);
        result = 31 * result + (judgeStandard != null ? judgeStandard.hashCode() : 0);
        result = 31 * result + (judgeStandardId != null ? judgeStandardId.hashCode() : 0);
        result = 31 * result + (detectionStandard != null ? detectionStandard.hashCode() : 0);
        result = 31 * result + (detectionStandardId != null ? detectionStandardId.hashCode() : 0);
        result = 31 * result + (parentTaskId != null ? parentTaskId.hashCode() : 0);
        result = 31 * result + (parentTaskName != null ? parentTaskName.hashCode() : 0);
        result = 31 * result + (createOrgId != null ? createOrgId.hashCode() : 0);
        result = 31 * result + (createOrgName != null ? createOrgName.hashCode() : 0);
        result = 31 * result + (jcStandardLinkId != null ? jcStandardLinkId.hashCode() : 0);
        result = 31 * result + (pdStandardLinkId != null ? pdStandardLinkId.hashCode() : 0);
        result = 31 * result + (levelEnum != null ? levelEnum.hashCode() : 0);
        result = 31 * result + (filesname != null ? filesname.hashCode() : 0);
        result = 31 * result + (jdUnitId != null ? jdUnitId.hashCode() : 0);
        result = 31 * result + (jdUnitName != null ? jdUnitName.hashCode() : 0);
        result = 31 * result + (attachmentAddress != null ? attachmentAddress.hashCode() : 0);
        result = 31 * result + (attachmentName != null ? attachmentName.hashCode() : 0);
        result = 31 * result + (fileNumber != null ? fileNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AsmsCheckTask{" +
                "taskName='" + taskName + '\'' +
                ", taskType='" + taskType + '\'' +
                ", taskYear='" + taskYear + '\'' +
                ", taskBeginTime=" + taskBeginTime +
                ", taskEndTime=" + taskEndTime +
                ", taskReleaseUnit='" + taskReleaseUnit + '\'' +
                ", taskIsSeparate='" + taskIsSeparate + '\'' +
                ", taskAreaId='" + taskAreaId + '\'' +
                ", taskIndustry='" + taskIndustry + '\'' +
                ", taskSampleDeadline=" + taskSampleDeadline +
                ", files='" + files + '\'' +
                ", fileCode='" + fileCode + '\'' +
                ", taskLevel='" + taskLevel + '\'' +
                ", state='" + state + '\'' +
                ", enable='" + enable + '\'' +
                ", createOrgRegionId='" + createOrgRegionId + '\'' +
                ", baseInspectionId='" + baseInspectionId + '\'' +
                ", cyUnitId='" + cyUnitId + '\'' +
                ", cyUnitName='" + cyUnitName + '\'' +
                ", jcUnitId='" + jcUnitId + '\'' +
                ", jcUnitName='" + jcUnitName + '\'' +
                ", isSample='" + isSample + '\'' +
                ", judgeStandard='" + judgeStandard + '\'' +
                ", judgeStandardId='" + judgeStandardId + '\'' +
                ", detectionStandard='" + detectionStandard + '\'' +
                ", detectionStandardId='" + detectionStandardId + '\'' +
                ", parentTaskId='" + parentTaskId + '\'' +
                ", parentTaskName='" + parentTaskName + '\'' +
                ", createOrgId='" + createOrgId + '\'' +
                ", createOrgName='" + createOrgName + '\'' +
                ", jcStandardLinkId='" + jcStandardLinkId + '\'' +
                ", pdStandardLinkId='" + pdStandardLinkId + '\'' +
                ", levelEnum='" + levelEnum + '\'' +
                ", filesname='" + filesname + '\'' +
                ", jdUnitId='" + jdUnitId + '\'' +
                ", jdUnitName='" + jdUnitName + '\'' +
                ", attachmentAddress='" + attachmentAddress + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", fileNumber='" + fileNumber + '\'' +
                '}';
    }

}