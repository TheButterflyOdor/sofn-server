package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;
import java.util.Date;

/**
 * 委托检测任务类
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@SuppressWarnings("serial")
public class AlesEntrustDetection extends BaseModel {
    private String taskName;//任务名称

    private Date taskBeginTime;//开始时间

    private Date taskEndTime;//截止时间

    private String detectionId;//判定标准

    private String fileUrl;//文件路径

    private String fileCode;//文件号

    private String enable;//是否删除

    private String state;//状态

    private String taskyear;//任务年份

    private String taskType;//任务类型

    private String taskReleaseUnit;//任务发布机构

    private String taskAreaId;//任务区域

    private String createOrgRegionId;

    private String cyUnitId;

    private String cyUnitName;

    private String jcUnitId;

    private String jcUnitName;

    private String judgeStandard;

    private String judgeStandardId;

    private String detectionStandard;

    private String detectionStandardId;

    private String parentTaskId;

    private String parentTaskName;

    private String createOrgId;//创建机构id

    private String createOrgName;//创建机构名

    private String stUnitId;//受托单位id

    private String stUnitName;//受托单位名称


    private String attachments;//附件url

    private String attachmentNames;//附件名

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

    public String getDetectionId() {
        return detectionId;
    }

    public void setDetectionId(String detectionId) {
        this.detectionId = detectionId == null ? null : detectionId.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getTaskyear() {
        return taskyear;
    }

    public void setTaskyear(String taskyear) {
        this.taskyear = taskyear == null ? null : taskyear.trim();
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getTaskReleaseUnit() {
        return taskReleaseUnit;
    }

    public void setTaskReleaseUnit(String taskReleaseUnit) {
        this.taskReleaseUnit = taskReleaseUnit == null ? null : taskReleaseUnit.trim();
    }

    public String getTaskAreaId() {
        return taskAreaId;
    }

    public void setTaskAreaId(String taskAreaId) {
        this.taskAreaId = taskAreaId == null ? null : taskAreaId.trim();
    }

    public String getCreateOrgRegionId() {
        return createOrgRegionId;
    }

    public void setCreateOrgRegionId(String createOrgRegionId) {
        this.createOrgRegionId = createOrgRegionId == null ? null : createOrgRegionId.trim();
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

    public String getStUnitId() {
        return stUnitId;
    }

    public void setStUnitId(String stUnitId) {
        this.stUnitId = stUnitId == null ? null : stUnitId.trim();
    }

    public String getStUnitName() {
        return stUnitName;
    }

    public void setStUnitName(String stUnitName) {
        this.stUnitName = stUnitName == null ? null : stUnitName.trim();
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(String attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    @Override
    public String toString() {
        return "AlesEntrustDetection{" +
                "taskName='" + taskName + '\'' +
                ", taskBeginTime=" + taskBeginTime +
                ", taskEndTime=" + taskEndTime +
                ", detectionId='" + detectionId + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileCode='" + fileCode + '\'' +
                ", enable='" + enable + '\'' +
                ", state='" + state + '\'' +
                ", taskyear='" + taskyear + '\'' +
                ", taskType='" + taskType + '\'' +
                ", taskReleaseUnit='" + taskReleaseUnit + '\'' +
                ", taskAreaId='" + taskAreaId + '\'' +
                ", createOrgRegionId='" + createOrgRegionId + '\'' +
                ", cyUnitId='" + cyUnitId + '\'' +
                ", cyUnitName='" + cyUnitName + '\'' +
                ", jcUnitId='" + jcUnitId + '\'' +
                ", jcUnitName='" + jcUnitName + '\'' +
                ", judgeStandard='" + judgeStandard + '\'' +
                ", judgeStandardId='" + judgeStandardId + '\'' +
                ", detectionStandard='" + detectionStandard + '\'' +
                ", detectionStandardId='" + detectionStandardId + '\'' +
                ", parentTaskId='" + parentTaskId + '\'' +
                ", parentTaskName='" + parentTaskName + '\'' +
                ", createOrgId='" + createOrgId + '\'' +
                ", createOrgName='" + createOrgName + '\'' +
                ", stUnitId='" + stUnitId + '\'' +
                ", stUnitName='" + stUnitName + '\'' +
                ", attachments='" + attachments + '\'' +
                ", attachmentNames='" + attachmentNames + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlesEntrustDetection that = (AlesEntrustDetection) o;

        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (taskBeginTime != null ? !taskBeginTime.equals(that.taskBeginTime) : that.taskBeginTime != null)
            return false;
        if (taskEndTime != null ? !taskEndTime.equals(that.taskEndTime) : that.taskEndTime != null) return false;
        if (detectionId != null ? !detectionId.equals(that.detectionId) : that.detectionId != null) return false;
        if (fileUrl != null ? !fileUrl.equals(that.fileUrl) : that.fileUrl != null) return false;
        if (fileCode != null ? !fileCode.equals(that.fileCode) : that.fileCode != null) return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (taskyear != null ? !taskyear.equals(that.taskyear) : that.taskyear != null) return false;
        if (taskType != null ? !taskType.equals(that.taskType) : that.taskType != null) return false;
        if (taskReleaseUnit != null ? !taskReleaseUnit.equals(that.taskReleaseUnit) : that.taskReleaseUnit != null)
            return false;
        if (taskAreaId != null ? !taskAreaId.equals(that.taskAreaId) : that.taskAreaId != null) return false;
        if (createOrgRegionId != null ? !createOrgRegionId.equals(that.createOrgRegionId) : that.createOrgRegionId != null)
            return false;
        if (cyUnitId != null ? !cyUnitId.equals(that.cyUnitId) : that.cyUnitId != null) return false;
        if (cyUnitName != null ? !cyUnitName.equals(that.cyUnitName) : that.cyUnitName != null) return false;
        if (jcUnitId != null ? !jcUnitId.equals(that.jcUnitId) : that.jcUnitId != null) return false;
        if (jcUnitName != null ? !jcUnitName.equals(that.jcUnitName) : that.jcUnitName != null) return false;
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
        if (stUnitId != null ? !stUnitId.equals(that.stUnitId) : that.stUnitId != null) return false;
        if (stUnitName != null ? !stUnitName.equals(that.stUnitName) : that.stUnitName != null) return false;
        if (attachments != null ? !attachments.equals(that.attachments) : that.attachments != null) return false;
        return attachmentNames != null ? attachmentNames.equals(that.attachmentNames) : that.attachmentNames == null;

    }

    @Override
    public int hashCode() {
        int result = taskName != null ? taskName.hashCode() : 0;
        result = 31 * result + (taskBeginTime != null ? taskBeginTime.hashCode() : 0);
        result = 31 * result + (taskEndTime != null ? taskEndTime.hashCode() : 0);
        result = 31 * result + (detectionId != null ? detectionId.hashCode() : 0);
        result = 31 * result + (fileUrl != null ? fileUrl.hashCode() : 0);
        result = 31 * result + (fileCode != null ? fileCode.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (taskyear != null ? taskyear.hashCode() : 0);
        result = 31 * result + (taskType != null ? taskType.hashCode() : 0);
        result = 31 * result + (taskReleaseUnit != null ? taskReleaseUnit.hashCode() : 0);
        result = 31 * result + (taskAreaId != null ? taskAreaId.hashCode() : 0);
        result = 31 * result + (createOrgRegionId != null ? createOrgRegionId.hashCode() : 0);
        result = 31 * result + (cyUnitId != null ? cyUnitId.hashCode() : 0);
        result = 31 * result + (cyUnitName != null ? cyUnitName.hashCode() : 0);
        result = 31 * result + (jcUnitId != null ? jcUnitId.hashCode() : 0);
        result = 31 * result + (jcUnitName != null ? jcUnitName.hashCode() : 0);
        result = 31 * result + (judgeStandard != null ? judgeStandard.hashCode() : 0);
        result = 31 * result + (judgeStandardId != null ? judgeStandardId.hashCode() : 0);
        result = 31 * result + (detectionStandard != null ? detectionStandard.hashCode() : 0);
        result = 31 * result + (detectionStandardId != null ? detectionStandardId.hashCode() : 0);
        result = 31 * result + (parentTaskId != null ? parentTaskId.hashCode() : 0);
        result = 31 * result + (parentTaskName != null ? parentTaskName.hashCode() : 0);
        result = 31 * result + (createOrgId != null ? createOrgId.hashCode() : 0);
        result = 31 * result + (createOrgName != null ? createOrgName.hashCode() : 0);
        result = 31 * result + (stUnitId != null ? stUnitId.hashCode() : 0);
        result = 31 * result + (stUnitName != null ? stUnitName.hashCode() : 0);
        result = 31 * result + (attachments != null ? attachments.hashCode() : 0);
        result = 31 * result + (attachmentNames != null ? attachmentNames.hashCode() : 0);
        return result;
    }
}