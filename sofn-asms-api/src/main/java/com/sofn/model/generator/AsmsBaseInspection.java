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

    private String userIdcode;

    private String enterpriseName;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    public String getInspectionView() {
        return inspectionView;
    }

    public void setInspectionView(String inspectionView) {
        this.inspectionView = inspectionView;
    }

    public String getInspectionImages() {
        return inspectionImages;
    }

    public void setInspectionImages(String inspectionImages) {
        this.inspectionImages = inspectionImages;
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
        this.inspectionSvName = inspectionSvName;
    }

    public String getInspectionSvId() {
        return inspectionSvId;
    }

    public void setInspectionSvId(String inspectionSvId) {
        this.inspectionSvId = inspectionSvId;
    }

    public String getInspectionUserName() {
        return inspectionUserName;
    }

    public void setInspectionUserName(String inspectionUserName) {
        this.inspectionUserName = inspectionUserName;
    }

    public String getElCheckState() {
        return elCheckState;
    }

    public void setElCheckState(String elCheckState) {
        this.elCheckState = elCheckState;
    }

    public String getHeadSign() {
        return headSign;
    }

    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    public String getHeadSignFile() {
        return headSignFile;
    }

    public void setHeadSignFile(String headSignFile) {
        this.headSignFile = headSignFile;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getInspectionTypeName() {
        return inspectionTypeName;
    }

    public void setInspectionTypeName(String inspectionTypeName) {
        this.inspectionTypeName = inspectionTypeName;
    }

    public String getInspectionImagesName() {
        return inspectionImagesName;
    }

    public void setInspectionImagesName(String inspectionImagesName) {
        this.inspectionImagesName = inspectionImagesName;
    }

    public String getHeadSignFileName() {
        return headSignFileName;
    }

    public void setHeadSignFileName(String headSignFileName) {
        this.headSignFileName = headSignFileName;
    }

    public String getUserIdcode() {
        return userIdcode;
    }

    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsmsBaseInspection that = (AsmsBaseInspection) o;

        if (enterpriseId != null ? !enterpriseId.equals(that.enterpriseId) : that.enterpriseId != null) return false;
        if (inspectionType != null ? !inspectionType.equals(that.inspectionType) : that.inspectionType != null)
            return false;
        if (inspectionResult != null ? !inspectionResult.equals(that.inspectionResult) : that.inspectionResult != null)
            return false;
        if (inspectionView != null ? !inspectionView.equals(that.inspectionView) : that.inspectionView != null)
            return false;
        if (inspectionImages != null ? !inspectionImages.equals(that.inspectionImages) : that.inspectionImages != null)
            return false;
        if (inspectionTime != null ? !inspectionTime.equals(that.inspectionTime) : that.inspectionTime != null)
            return false;
        if (inspectionSvName != null ? !inspectionSvName.equals(that.inspectionSvName) : that.inspectionSvName != null)
            return false;
        if (inspectionSvId != null ? !inspectionSvId.equals(that.inspectionSvId) : that.inspectionSvId != null)
            return false;
        if (inspectionUserName != null ? !inspectionUserName.equals(that.inspectionUserName) : that.inspectionUserName != null)
            return false;
        if (elCheckState != null ? !elCheckState.equals(that.elCheckState) : that.elCheckState != null) return false;
        if (headSign != null ? !headSign.equals(that.headSign) : that.headSign != null) return false;
        if (headSignFile != null ? !headSignFile.equals(that.headSignFile) : that.headSignFile != null) return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (inspectionTypeName != null ? !inspectionTypeName.equals(that.inspectionTypeName) : that.inspectionTypeName != null)
            return false;
        if (inspectionImagesName != null ? !inspectionImagesName.equals(that.inspectionImagesName) : that.inspectionImagesName != null)
            return false;
        if (headSignFileName != null ? !headSignFileName.equals(that.headSignFileName) : that.headSignFileName != null)
            return false;
        if (userIdcode != null ? !userIdcode.equals(that.userIdcode) : that.userIdcode != null) return false;
        return enterpriseName != null ? enterpriseName.equals(that.enterpriseName) : that.enterpriseName == null;

    }

    @Override
    public int hashCode() {
        int result = enterpriseId != null ? enterpriseId.hashCode() : 0;
        result = 31 * result + (inspectionType != null ? inspectionType.hashCode() : 0);
        result = 31 * result + (inspectionResult != null ? inspectionResult.hashCode() : 0);
        result = 31 * result + (inspectionView != null ? inspectionView.hashCode() : 0);
        result = 31 * result + (inspectionImages != null ? inspectionImages.hashCode() : 0);
        result = 31 * result + (inspectionTime != null ? inspectionTime.hashCode() : 0);
        result = 31 * result + (inspectionSvName != null ? inspectionSvName.hashCode() : 0);
        result = 31 * result + (inspectionSvId != null ? inspectionSvId.hashCode() : 0);
        result = 31 * result + (inspectionUserName != null ? inspectionUserName.hashCode() : 0);
        result = 31 * result + (elCheckState != null ? elCheckState.hashCode() : 0);
        result = 31 * result + (headSign != null ? headSign.hashCode() : 0);
        result = 31 * result + (headSignFile != null ? headSignFile.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (inspectionTypeName != null ? inspectionTypeName.hashCode() : 0);
        result = 31 * result + (inspectionImagesName != null ? inspectionImagesName.hashCode() : 0);
        result = 31 * result + (headSignFileName != null ? headSignFileName.hashCode() : 0);
        result = 31 * result + (userIdcode != null ? userIdcode.hashCode() : 0);
        result = 31 * result + (enterpriseName != null ? enterpriseName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AsmsBaseInspection{" +
                "enterpriseId='" + enterpriseId + '\'' +
                ", inspectionType='" + inspectionType + '\'' +
                ", inspectionResult='" + inspectionResult + '\'' +
                ", inspectionView='" + inspectionView + '\'' +
                ", inspectionImages='" + inspectionImages + '\'' +
                ", inspectionTime=" + inspectionTime +
                ", inspectionSvName='" + inspectionSvName + '\'' +
                ", inspectionSvId='" + inspectionSvId + '\'' +
                ", inspectionUserName='" + inspectionUserName + '\'' +
                ", elCheckState='" + elCheckState + '\'' +
                ", headSign='" + headSign + '\'' +
                ", headSignFile='" + headSignFile + '\'' +
                ", enable='" + enable + '\'' +
                ", inspectionTypeName='" + inspectionTypeName + '\'' +
                ", inspectionImagesName='" + inspectionImagesName + '\'' +
                ", headSignFileName='" + headSignFileName + '\'' +
                ", userIdcode='" + userIdcode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                '}';
    }
}