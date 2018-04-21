package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

@SuppressWarnings("serial")
public class AsmsBaseInspection extends BaseModel {
    private String enterpriseId;

    private String inspectionType;

    private String inspectionResult;

    private String inspectionView;

    private String inspectionImages;

    private Date inspectionTime;

    private String inspectionSvName;

    private String inspectionSvId;

    private String inspectionUserName;

    private String elCheckState;

    private String headSign;

    private String headSignFile;

    private String enable;

    private String inspectionTypeName;

    private String inspectionImagesName;

    private String headSignFileName;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType == null ? null : inspectionType.trim();
    }

    public String getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.inspectionResult = inspectionResult == null ? null : inspectionResult.trim();
    }

    public String getInspectionView() {
        return inspectionView;
    }

    public void setInspectionView(String inspectionView) {
        this.inspectionView = inspectionView == null ? null : inspectionView.trim();
    }

    public String getInspectionImages() {
        return inspectionImages;
    }

    public void setInspectionImages(String inspectionImages) {
        this.inspectionImages = inspectionImages == null ? null : inspectionImages.trim();
    }

    public Date getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(Date inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public String getInspectionSvName() {
        return inspectionSvName;
    }

    public void setInspectionSvName(String inspectionSvName) {
        this.inspectionSvName = inspectionSvName == null ? null : inspectionSvName.trim();
    }

    public String getInspectionSvId() {
        return inspectionSvId;
    }

    public void setInspectionSvId(String inspectionSvId) {
        this.inspectionSvId = inspectionSvId == null ? null : inspectionSvId.trim();
    }

    public String getInspectionUserName() {
        return inspectionUserName;
    }

    public void setInspectionUserName(String inspectionUserName) {
        this.inspectionUserName = inspectionUserName == null ? null : inspectionUserName.trim();
    }

    public String getElCheckState() {
        return elCheckState;
    }

    public void setElCheckState(String elCheckState) {
        this.elCheckState = elCheckState == null ? null : elCheckState.trim();
    }

    public String getHeadSign() {
        return headSign;
    }

    public void setHeadSign(String headSign) {
        this.headSign = headSign == null ? null : headSign.trim();
    }

    public String getHeadSignFile() {
        return headSignFile;
    }

    public void setHeadSignFile(String headSignFile) {
        this.headSignFile = headSignFile == null ? null : headSignFile.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getInspectionTypeName() {
        return inspectionTypeName;
    }

    public void setInspectionTypeName(String inspectionTypeName) {
        this.inspectionTypeName = inspectionTypeName == null ? null : inspectionTypeName.trim();
    }

    public String getInspectionImagesName() {
        return inspectionImagesName;
    }

    public void setInspectionImagesName(String inspectionImagesName) {
        this.inspectionImagesName = inspectionImagesName == null ? null : inspectionImagesName.trim();
    }

    public String getHeadSignFileName() {
        return headSignFileName;
    }

    public void setHeadSignFileName(String headSignFileName) {
        this.headSignFileName = headSignFileName == null ? null : headSignFileName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", inspectionType=").append(inspectionType);
        sb.append(", inspectionResult=").append(inspectionResult);
        sb.append(", inspectionView=").append(inspectionView);
        sb.append(", inspectionImages=").append(inspectionImages);
        sb.append(", inspectionTime=").append(inspectionTime);
        sb.append(", inspectionSvName=").append(inspectionSvName);
        sb.append(", inspectionSvId=").append(inspectionSvId);
        sb.append(", inspectionUserName=").append(inspectionUserName);
        sb.append(", elCheckState=").append(elCheckState);
        sb.append(", headSign=").append(headSign);
        sb.append(", headSignFile=").append(headSignFile);
        sb.append(", enable=").append(enable);
        sb.append(", inspectionTypeName=").append(inspectionTypeName);
        sb.append(", inspectionImagesName=").append(inspectionImagesName);
        sb.append(", headSignFileName=").append(headSignFileName);
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
        AsmsBaseInspection other = (AsmsBaseInspection) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEnterpriseId() == null ? other.getEnterpriseId() == null : this.getEnterpriseId().equals(other.getEnterpriseId()))
            && (this.getInspectionType() == null ? other.getInspectionType() == null : this.getInspectionType().equals(other.getInspectionType()))
            && (this.getInspectionResult() == null ? other.getInspectionResult() == null : this.getInspectionResult().equals(other.getInspectionResult()))
            && (this.getInspectionView() == null ? other.getInspectionView() == null : this.getInspectionView().equals(other.getInspectionView()))
            && (this.getInspectionImages() == null ? other.getInspectionImages() == null : this.getInspectionImages().equals(other.getInspectionImages()))
            && (this.getInspectionTime() == null ? other.getInspectionTime() == null : this.getInspectionTime().equals(other.getInspectionTime()))
            && (this.getInspectionSvName() == null ? other.getInspectionSvName() == null : this.getInspectionSvName().equals(other.getInspectionSvName()))
            && (this.getInspectionSvId() == null ? other.getInspectionSvId() == null : this.getInspectionSvId().equals(other.getInspectionSvId()))
            && (this.getInspectionUserName() == null ? other.getInspectionUserName() == null : this.getInspectionUserName().equals(other.getInspectionUserName()))
            && (this.getElCheckState() == null ? other.getElCheckState() == null : this.getElCheckState().equals(other.getElCheckState()))
            && (this.getHeadSign() == null ? other.getHeadSign() == null : this.getHeadSign().equals(other.getHeadSign()))
            && (this.getHeadSignFile() == null ? other.getHeadSignFile() == null : this.getHeadSignFile().equals(other.getHeadSignFile()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getInspectionTypeName() == null ? other.getInspectionTypeName() == null : this.getInspectionTypeName().equals(other.getInspectionTypeName()))
            && (this.getInspectionImagesName() == null ? other.getInspectionImagesName() == null : this.getInspectionImagesName().equals(other.getInspectionImagesName()))
            && (this.getHeadSignFileName() == null ? other.getHeadSignFileName() == null : this.getHeadSignFileName().equals(other.getHeadSignFileName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEnterpriseId() == null) ? 0 : getEnterpriseId().hashCode());
        result = prime * result + ((getInspectionType() == null) ? 0 : getInspectionType().hashCode());
        result = prime * result + ((getInspectionResult() == null) ? 0 : getInspectionResult().hashCode());
        result = prime * result + ((getInspectionView() == null) ? 0 : getInspectionView().hashCode());
        result = prime * result + ((getInspectionImages() == null) ? 0 : getInspectionImages().hashCode());
        result = prime * result + ((getInspectionTime() == null) ? 0 : getInspectionTime().hashCode());
        result = prime * result + ((getInspectionSvName() == null) ? 0 : getInspectionSvName().hashCode());
        result = prime * result + ((getInspectionSvId() == null) ? 0 : getInspectionSvId().hashCode());
        result = prime * result + ((getInspectionUserName() == null) ? 0 : getInspectionUserName().hashCode());
        result = prime * result + ((getElCheckState() == null) ? 0 : getElCheckState().hashCode());
        result = prime * result + ((getHeadSign() == null) ? 0 : getHeadSign().hashCode());
        result = prime * result + ((getHeadSignFile() == null) ? 0 : getHeadSignFile().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getInspectionTypeName() == null) ? 0 : getInspectionTypeName().hashCode());
        result = prime * result + ((getInspectionImagesName() == null) ? 0 : getInspectionImagesName().hashCode());
        result = prime * result + ((getHeadSignFileName() == null) ? 0 : getHeadSignFileName().hashCode());
        return result;
    }
}