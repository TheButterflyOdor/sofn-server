package com.sofn.model.asms;

import com.sofn.model.generator.AsmsInspectionTask;

/**
 * 任务扩展bean
 * Created by Administrator on 2016-8-31.
 */
public class SuperviseTaskInfo extends AsmsInspectionTask {

    /*下面属性为SuperviseTaskUser所有属性，并继承SuperviseInspectionTask使用bean方便使用临时属性*/

    private String taskId; //任务id

    private String userName;//巡查人员名称

    private String userId;//巡查人员id

    private String userIds;//巡查人员集合

    private String taskResult;//巡查考核结果

    private Long inspectionRealCount;//实际巡查次数

    private String attachmentAddress;//附件地址

    private String attachmentName;//附件名称

    public String getTaskId() {
        return taskId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public Long getInspectionRealCount() {
        return inspectionRealCount;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    public void setInspectionRealCount(Long inspectionRealCount) {
        this.inspectionRealCount = inspectionRealCount;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    @Override
    public String getAttachmentAddress() {
        return attachmentAddress;
    }

    @Override
    public void setAttachmentAddress(String attachmentAddress) {
        this.attachmentAddress = attachmentAddress;
    }

    @Override
    public String getAttachmentName() {
        return attachmentName;
    }

    @Override
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SuperviseTaskInfo that = (SuperviseTaskInfo) o;

        if (!taskId.equals(that.taskId)) return false;
        if (!userName.equals(that.userName)) return false;
        if (!userId.equals(that.userId)) return false;
        if (!userIds.equals(that.userIds)) return false;
        if (!taskResult.equals(that.taskResult)) return false;
        if (!inspectionRealCount.equals(that.inspectionRealCount)) return false;
        if (!attachmentAddress.equals(that.attachmentAddress)) return false;
        return attachmentName.equals(that.attachmentName);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + taskId.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + userIds.hashCode();
        result = 31 * result + taskResult.hashCode();
        result = 31 * result + inspectionRealCount.hashCode();
        result = 31 * result + attachmentAddress.hashCode();
        result = 31 * result + attachmentName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SuperviseTaskInfo{" +
                "taskId='" + taskId + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", userIds='" + userIds + '\'' +
                ", taskResult='" + taskResult + '\'' +
                ", inspectionRealCount=" + inspectionRealCount +
                ", attachmentAddress='" + attachmentAddress + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                '}';
    }
}
