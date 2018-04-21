package com.sofn.model.asms;

import java.io.Serializable;

/**
 * Created by dong4j on 16/9/1.
 * Description: 巡查人信息 与任务为一对多关系
 * dto:数据传输对象, 主要根据表现层定义需要的数据对象,对于多表联查数据的封装
 */
public class SuperviseCheckDto implements Serializable {
    private String taskId; //任务id
    private String userName;//巡查人员名称
    private String userId;//巡查人员id
    private String taskResult;//巡查考核结果
    private Long inspectionRealCount;//实际巡查次数
    // 巡查区域Id todo 后面连接区域表查询出具体名称
    private String addressId;
    // 设定的巡查次数
    private Long inspectionDesignCount;
    // 任务状态
    private boolean taskStatus;
    // 报告Id
    private String reportId;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Long getInspectionDesignCount() {
        return inspectionDesignCount;
    }

    public void setInspectionDesignCount(Long inspectionDesignCount) {
        this.inspectionDesignCount = inspectionDesignCount;
    }

    public Long getInspectionRealCount() {
        return inspectionRealCount;
    }

    public void setInspectionRealCount(Long inspectionRealCount) {
        this.inspectionRealCount = inspectionRealCount;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
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
        return "SuperviseCheckDto{" +
                "addressId='" + addressId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", taskResult='" + taskResult + '\'' +
                ", inspectionRealCount=" + inspectionRealCount +
                ", inspectionDesignCount=" + inspectionDesignCount +
                ", taskStatus=" + taskStatus +
                ", reportId='" + reportId + '\'' +
                '}';
    }
}
