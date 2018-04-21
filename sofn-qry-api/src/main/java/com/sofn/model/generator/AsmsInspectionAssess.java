package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsInspectionAssess extends BaseModel {
    private String inspectionTaskId;

    private String userName;

    private String userId;

    private String taskResult;

    private Long inspectionRealCount;

    private String delFlag;

    public String getInspectionTaskId() {
        return inspectionTaskId;
    }

    public void setInspectionTaskId(String inspectionTaskId) {
        this.inspectionTaskId = inspectionTaskId == null ? null : inspectionTaskId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult == null ? null : taskResult.trim();
    }

    public Long getInspectionRealCount() {
        return inspectionRealCount;
    }

    public void setInspectionRealCount(Long inspectionRealCount) {
        this.inspectionRealCount = inspectionRealCount;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", inspectionTaskId=").append(inspectionTaskId);
        sb.append(", userName=").append(userName);
        sb.append(", userId=").append(userId);
        sb.append(", taskResult=").append(taskResult);
        sb.append(", inspectionRealCount=").append(inspectionRealCount);
        sb.append(", delFlag=").append(delFlag);
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
        AsmsInspectionAssess other = (AsmsInspectionAssess) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getInspectionTaskId() == null ? other.getInspectionTaskId() == null : this.getInspectionTaskId().equals(other.getInspectionTaskId()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getTaskResult() == null ? other.getTaskResult() == null : this.getTaskResult().equals(other.getTaskResult()))
                && (this.getInspectionRealCount() == null ? other.getInspectionRealCount() == null : this.getInspectionRealCount().equals(other.getInspectionRealCount()))
                && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInspectionTaskId() == null) ? 0 : getInspectionTaskId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTaskResult() == null) ? 0 : getTaskResult().hashCode());
        result = prime * result + ((getInspectionRealCount() == null) ? 0 : getInspectionRealCount().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }
}