package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

@SuppressWarnings("serial")
public class AsmsRoutineMonitor extends BaseModel {
    private String rmName;

    private String rmType;

    private String rmModelId;

    private String rmYear;

    private String rmBatch;

    private Date rmDateBegin;

    private Date rmDateEnd;

    private String rmReleaseUnit;

    private String rmFile;

    private String rmFileNum;

    private String rmRemark;

    private String rmState;

    private String enable;

    private String createOrgRegionId;

    private String createOrgName;

    private String createOrgId;

    private String levelEnum;

    private String rmFileName;

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName == null ? null : rmName.trim();
    }

    public String getRmType() {
        return rmType;
    }

    public void setRmType(String rmType) {
        this.rmType = rmType == null ? null : rmType.trim();
    }

    public String getRmModelId() {
        return rmModelId;
    }

    public void setRmModelId(String rmModelId) {
        this.rmModelId = rmModelId == null ? null : rmModelId.trim();
    }

    public String getRmYear() {
        return rmYear;
    }

    public void setRmYear(String rmYear) {
        this.rmYear = rmYear == null ? null : rmYear.trim();
    }

    public String getRmBatch() {
        return rmBatch;
    }

    public void setRmBatch(String rmBatch) {
        this.rmBatch = rmBatch == null ? null : rmBatch.trim();
    }

    public Date getRmDateBegin() {
        return rmDateBegin;
    }

    public void setRmDateBegin(Date rmDateBegin) {
        this.rmDateBegin = rmDateBegin;
    }

    public Date getRmDateEnd() {
        return rmDateEnd;
    }

    public void setRmDateEnd(Date rmDateEnd) {
        this.rmDateEnd = rmDateEnd;
    }

    public String getRmReleaseUnit() {
        return rmReleaseUnit;
    }

    public void setRmReleaseUnit(String rmReleaseUnit) {
        this.rmReleaseUnit = rmReleaseUnit == null ? null : rmReleaseUnit.trim();
    }

    public String getRmFile() {
        return rmFile;
    }

    public void setRmFile(String rmFile) {
        this.rmFile = rmFile == null ? null : rmFile.trim();
    }

    public String getRmFileNum() {
        return rmFileNum;
    }

    public void setRmFileNum(String rmFileNum) {
        this.rmFileNum = rmFileNum == null ? null : rmFileNum.trim();
    }

    public String getRmRemark() {
        return rmRemark;
    }

    public void setRmRemark(String rmRemark) {
        this.rmRemark = rmRemark == null ? null : rmRemark.trim();
    }

    public String getRmState() {
        return rmState;
    }

    public void setRmState(String rmState) {
        this.rmState = rmState == null ? null : rmState.trim();
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

    public String getCreateOrgName() {
        return createOrgName;
    }

    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName == null ? null : createOrgName.trim();
    }

    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId == null ? null : createOrgId.trim();
    }

    public String getLevelEnum() {
        return levelEnum;
    }

    public void setLevelEnum(String levelEnum) {
        this.levelEnum = levelEnum == null ? null : levelEnum.trim();
    }

    public String getRmFileName() {
        return rmFileName;
    }

    public void setRmFileName(String rmFileName) {
        this.rmFileName = rmFileName == null ? null : rmFileName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rmName=").append(rmName);
        sb.append(", rmType=").append(rmType);
        sb.append(", rmModelId=").append(rmModelId);
        sb.append(", rmYear=").append(rmYear);
        sb.append(", rmBatch=").append(rmBatch);
        sb.append(", rmDateBegin=").append(rmDateBegin);
        sb.append(", rmDateEnd=").append(rmDateEnd);
        sb.append(", rmReleaseUnit=").append(rmReleaseUnit);
        sb.append(", rmFile=").append(rmFile);
        sb.append(", rmFileNum=").append(rmFileNum);
        sb.append(", rmRemark=").append(rmRemark);
        sb.append(", rmState=").append(rmState);
        sb.append(", enable=").append(enable);
        sb.append(", createOrgRegionId=").append(createOrgRegionId);
        sb.append(", createOrgName=").append(createOrgName);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", levelEnum=").append(levelEnum);
        sb.append(", rmFileName=").append(rmFileName);
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
        AsmsRoutineMonitor other = (AsmsRoutineMonitor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRmName() == null ? other.getRmName() == null : this.getRmName().equals(other.getRmName()))
            && (this.getRmType() == null ? other.getRmType() == null : this.getRmType().equals(other.getRmType()))
            && (this.getRmModelId() == null ? other.getRmModelId() == null : this.getRmModelId().equals(other.getRmModelId()))
            && (this.getRmYear() == null ? other.getRmYear() == null : this.getRmYear().equals(other.getRmYear()))
            && (this.getRmBatch() == null ? other.getRmBatch() == null : this.getRmBatch().equals(other.getRmBatch()))
            && (this.getRmDateBegin() == null ? other.getRmDateBegin() == null : this.getRmDateBegin().equals(other.getRmDateBegin()))
            && (this.getRmDateEnd() == null ? other.getRmDateEnd() == null : this.getRmDateEnd().equals(other.getRmDateEnd()))
            && (this.getRmReleaseUnit() == null ? other.getRmReleaseUnit() == null : this.getRmReleaseUnit().equals(other.getRmReleaseUnit()))
            && (this.getRmFile() == null ? other.getRmFile() == null : this.getRmFile().equals(other.getRmFile()))
            && (this.getRmFileNum() == null ? other.getRmFileNum() == null : this.getRmFileNum().equals(other.getRmFileNum()))
            && (this.getRmRemark() == null ? other.getRmRemark() == null : this.getRmRemark().equals(other.getRmRemark()))
            && (this.getRmState() == null ? other.getRmState() == null : this.getRmState().equals(other.getRmState()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateOrgRegionId() == null ? other.getCreateOrgRegionId() == null : this.getCreateOrgRegionId().equals(other.getCreateOrgRegionId()))
            && (this.getCreateOrgName() == null ? other.getCreateOrgName() == null : this.getCreateOrgName().equals(other.getCreateOrgName()))
            && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
            && (this.getLevelEnum() == null ? other.getLevelEnum() == null : this.getLevelEnum().equals(other.getLevelEnum()))
            && (this.getRmFileName() == null ? other.getRmFileName() == null : this.getRmFileName().equals(other.getRmFileName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRmName() == null) ? 0 : getRmName().hashCode());
        result = prime * result + ((getRmType() == null) ? 0 : getRmType().hashCode());
        result = prime * result + ((getRmModelId() == null) ? 0 : getRmModelId().hashCode());
        result = prime * result + ((getRmYear() == null) ? 0 : getRmYear().hashCode());
        result = prime * result + ((getRmBatch() == null) ? 0 : getRmBatch().hashCode());
        result = prime * result + ((getRmDateBegin() == null) ? 0 : getRmDateBegin().hashCode());
        result = prime * result + ((getRmDateEnd() == null) ? 0 : getRmDateEnd().hashCode());
        result = prime * result + ((getRmReleaseUnit() == null) ? 0 : getRmReleaseUnit().hashCode());
        result = prime * result + ((getRmFile() == null) ? 0 : getRmFile().hashCode());
        result = prime * result + ((getRmFileNum() == null) ? 0 : getRmFileNum().hashCode());
        result = prime * result + ((getRmRemark() == null) ? 0 : getRmRemark().hashCode());
        result = prime * result + ((getRmState() == null) ? 0 : getRmState().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateOrgRegionId() == null) ? 0 : getCreateOrgRegionId().hashCode());
        result = prime * result + ((getCreateOrgName() == null) ? 0 : getCreateOrgName().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getLevelEnum() == null) ? 0 : getLevelEnum().hashCode());
        result = prime * result + ((getRmFileName() == null) ? 0 : getRmFileName().hashCode());
        return result;
    }
}