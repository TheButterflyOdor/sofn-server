package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

@SuppressWarnings("serial")
public class AsmsSpecialMonitor extends BaseModel {
    private String smName;

    private String smType;

    private String smModelId;

    private String smYear;

    private String smBatch;

    private Date smDateBegin;

    private Date smDateEnd;

    private String smReleaseUnit;

    private String smFile;

    private String smFileNum;

    private String smRemark;

    private String smState;

    private String enable;

    private String createOrgRegionId;

    private String createOrgId;

    private String createOrgName;

    private String levelEnum;

    private String smFileName;

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName == null ? null : smName.trim();
    }

    public String getSmType() {
        return smType;
    }

    public void setSmType(String smType) {
        this.smType = smType == null ? null : smType.trim();
    }

    public String getSmModelId() {
        return smModelId;
    }

    public void setSmModelId(String smModelId) {
        this.smModelId = smModelId == null ? null : smModelId.trim();
    }

    public String getSmYear() {
        return smYear;
    }

    public void setSmYear(String smYear) {
        this.smYear = smYear == null ? null : smYear.trim();
    }

    public String getSmBatch() {
        return smBatch;
    }

    public void setSmBatch(String smBatch) {
        this.smBatch = smBatch == null ? null : smBatch.trim();
    }

    public Date getSmDateBegin() {
        return smDateBegin;
    }

    public void setSmDateBegin(Date smDateBegin) {
        this.smDateBegin = smDateBegin;
    }

    public Date getSmDateEnd() {
        return smDateEnd;
    }

    public void setSmDateEnd(Date smDateEnd) {
        this.smDateEnd = smDateEnd;
    }

    public String getSmReleaseUnit() {
        return smReleaseUnit;
    }

    public void setSmReleaseUnit(String smReleaseUnit) {
        this.smReleaseUnit = smReleaseUnit == null ? null : smReleaseUnit.trim();
    }

    public String getSmFile() {
        return smFile;
    }

    public void setSmFile(String smFile) {
        this.smFile = smFile == null ? null : smFile.trim();
    }

    public String getSmFileNum() {
        return smFileNum;
    }

    public void setSmFileNum(String smFileNum) {
        this.smFileNum = smFileNum == null ? null : smFileNum.trim();
    }

    public String getSmRemark() {
        return smRemark;
    }

    public void setSmRemark(String smRemark) {
        this.smRemark = smRemark == null ? null : smRemark.trim();
    }

    public String getSmState() {
        return smState;
    }

    public void setSmState(String smState) {
        this.smState = smState == null ? null : smState.trim();
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

    public String getLevelEnum() {
        return levelEnum;
    }

    public void setLevelEnum(String levelEnum) {
        this.levelEnum = levelEnum == null ? null : levelEnum.trim();
    }

    public String getSmFileName() {
        return smFileName;
    }

    public void setSmFileName(String smFileName) {
        this.smFileName = smFileName == null ? null : smFileName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", smName=").append(smName);
        sb.append(", smType=").append(smType);
        sb.append(", smModelId=").append(smModelId);
        sb.append(", smYear=").append(smYear);
        sb.append(", smBatch=").append(smBatch);
        sb.append(", smDateBegin=").append(smDateBegin);
        sb.append(", smDateEnd=").append(smDateEnd);
        sb.append(", smReleaseUnit=").append(smReleaseUnit);
        sb.append(", smFile=").append(smFile);
        sb.append(", smFileNum=").append(smFileNum);
        sb.append(", smRemark=").append(smRemark);
        sb.append(", smState=").append(smState);
        sb.append(", enable=").append(enable);
        sb.append(", createOrgRegionId=").append(createOrgRegionId);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", createOrgName=").append(createOrgName);
        sb.append(", levelEnum=").append(levelEnum);
        sb.append(", smFileName=").append(smFileName);
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
        AsmsSpecialMonitor other = (AsmsSpecialMonitor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSmName() == null ? other.getSmName() == null : this.getSmName().equals(other.getSmName()))
            && (this.getSmType() == null ? other.getSmType() == null : this.getSmType().equals(other.getSmType()))
            && (this.getSmModelId() == null ? other.getSmModelId() == null : this.getSmModelId().equals(other.getSmModelId()))
            && (this.getSmYear() == null ? other.getSmYear() == null : this.getSmYear().equals(other.getSmYear()))
            && (this.getSmBatch() == null ? other.getSmBatch() == null : this.getSmBatch().equals(other.getSmBatch()))
            && (this.getSmDateBegin() == null ? other.getSmDateBegin() == null : this.getSmDateBegin().equals(other.getSmDateBegin()))
            && (this.getSmDateEnd() == null ? other.getSmDateEnd() == null : this.getSmDateEnd().equals(other.getSmDateEnd()))
            && (this.getSmReleaseUnit() == null ? other.getSmReleaseUnit() == null : this.getSmReleaseUnit().equals(other.getSmReleaseUnit()))
            && (this.getSmFile() == null ? other.getSmFile() == null : this.getSmFile().equals(other.getSmFile()))
            && (this.getSmFileNum() == null ? other.getSmFileNum() == null : this.getSmFileNum().equals(other.getSmFileNum()))
            && (this.getSmRemark() == null ? other.getSmRemark() == null : this.getSmRemark().equals(other.getSmRemark()))
            && (this.getSmState() == null ? other.getSmState() == null : this.getSmState().equals(other.getSmState()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateOrgRegionId() == null ? other.getCreateOrgRegionId() == null : this.getCreateOrgRegionId().equals(other.getCreateOrgRegionId()))
            && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
            && (this.getCreateOrgName() == null ? other.getCreateOrgName() == null : this.getCreateOrgName().equals(other.getCreateOrgName()))
            && (this.getLevelEnum() == null ? other.getLevelEnum() == null : this.getLevelEnum().equals(other.getLevelEnum()))
            && (this.getSmFileName() == null ? other.getSmFileName() == null : this.getSmFileName().equals(other.getSmFileName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSmName() == null) ? 0 : getSmName().hashCode());
        result = prime * result + ((getSmType() == null) ? 0 : getSmType().hashCode());
        result = prime * result + ((getSmModelId() == null) ? 0 : getSmModelId().hashCode());
        result = prime * result + ((getSmYear() == null) ? 0 : getSmYear().hashCode());
        result = prime * result + ((getSmBatch() == null) ? 0 : getSmBatch().hashCode());
        result = prime * result + ((getSmDateBegin() == null) ? 0 : getSmDateBegin().hashCode());
        result = prime * result + ((getSmDateEnd() == null) ? 0 : getSmDateEnd().hashCode());
        result = prime * result + ((getSmReleaseUnit() == null) ? 0 : getSmReleaseUnit().hashCode());
        result = prime * result + ((getSmFile() == null) ? 0 : getSmFile().hashCode());
        result = prime * result + ((getSmFileNum() == null) ? 0 : getSmFileNum().hashCode());
        result = prime * result + ((getSmRemark() == null) ? 0 : getSmRemark().hashCode());
        result = prime * result + ((getSmState() == null) ? 0 : getSmState().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateOrgRegionId() == null) ? 0 : getCreateOrgRegionId().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getCreateOrgName() == null) ? 0 : getCreateOrgName().hashCode());
        result = prime * result + ((getLevelEnum() == null) ? 0 : getLevelEnum().hashCode());
        result = prime * result + ((getSmFileName() == null) ? 0 : getSmFileName().hashCode());
        return result;
    }
}