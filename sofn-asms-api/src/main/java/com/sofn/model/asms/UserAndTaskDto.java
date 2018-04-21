package com.sofn.model.asms;

import com.sofn.model.generator.AsmsInspectionTask;

import java.io.Serializable;

/**
 * Created by dong4j on 16/9/1.
 * Description:
 */
public class UserAndTaskDto implements Serializable {
    private AsmsInspectionTask task;
    // todo 还有一个 User 对象,这里用 userId代替
    private String userId;
    private String userName;
    private String taskResult;
    private Long inspectionRealCount;

    public Long getInspectionRealCount() {
        return inspectionRealCount;
    }

    public void setInspectionRealCount(Long inspectionRealCount) {
        this.inspectionRealCount = inspectionRealCount;
    }

    public AsmsInspectionTask getTask() {
        return task;
    }

    public void setTask(AsmsInspectionTask task) {
        this.task = task;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserAndTaskDto{" +
                "inspectionRealCount=" + inspectionRealCount +
                ", task=" + task +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", taskResult='" + taskResult + '\'' +
                '}';
    }
}
