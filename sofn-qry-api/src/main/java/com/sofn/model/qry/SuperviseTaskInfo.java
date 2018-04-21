package com.sofn.model.qry;

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
    public String toString() {
        return "SuperviseTaskInfo{" +
                "inspectionRealCount=" + inspectionRealCount +
                ", taskId='" + taskId + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", userIds='" + userIds + '\'' +
                ", taskResult='" + taskResult + '\'' +
                '}';
    }
}
