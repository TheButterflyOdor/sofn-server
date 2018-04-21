package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;
import java.util.Date;

/**
 * 日常执法类
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@SuppressWarnings("serial")
public class AlesDailyEnforceLaw extends BaseModel {
    private String taskName;//任务名称

    private Date taskBeginTime;//执法开始时间

    private Date taskEndTime;//执法截止时间

    private String enterpriseId;//被执法对象（生产经营主体ID）

    private String areaId;//区域

    private String enterpriseAddress;//地址

    private String taskPersonCount;//执法人数

    private String taskPersonId;//执法人员

    private String enforceLawResult;//巡查结果

    private String enable;//是否删除

    private String enterpriseName;//生产经营主体名称

    private String createOrgId;//创建机构ID

    private String enforceLawResultFlag;//巡查结果是否合格（1-合格，2-不合格,3-整改）暂时预留

    private String taskPersonName;//执法人员姓名

    private String enterpriseIdcode;//主体身份码(废弃字段)

    private String principal;//负责人

    private String scenePictures;//现场照片urls

    private String scenePictureNames;//现场照片名称

    private String principalSignatures;//负责人签字照片urls

    private String principalSignatureNames;//负责人签字照片名称

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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress == null ? null : enterpriseAddress.trim();
    }

    public String getTaskPersonCount() {
        return taskPersonCount;
    }

    public void setTaskPersonCount(String taskPersonCount) {
        this.taskPersonCount = taskPersonCount == null ? null : taskPersonCount.trim();
    }

    public String getTaskPersonId() {
        return taskPersonId;
    }

    public void setTaskPersonId(String taskPersonId) {
        this.taskPersonId = taskPersonId == null ? null : taskPersonId.trim();
    }

    public String getEnforceLawResult() {
        return enforceLawResult;
    }

    public void setEnforceLawResult(String enforceLawResult) {
        this.enforceLawResult = enforceLawResult == null ? null : enforceLawResult.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId == null ? null : createOrgId.trim();
    }

    public String getEnforceLawResultFlag() {
        return enforceLawResultFlag;
    }

    public void setEnforceLawResultFlag(String enforceLawResultFlag) {
        this.enforceLawResultFlag = enforceLawResultFlag == null ? null : enforceLawResultFlag.trim();
    }

    public String getTaskPersonName() {
        return taskPersonName;
    }

    public void setTaskPersonName(String taskPersonName) {
        this.taskPersonName = taskPersonName == null ? null : taskPersonName.trim();
    }

    public String getEnterpriseIdcode() {
        return enterpriseIdcode;
    }

    public void setEnterpriseIdcode(String enterpriseIdcode) {
        this.enterpriseIdcode = enterpriseIdcode == null ? null : enterpriseIdcode.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getScenePictures() {
        return scenePictures;
    }

    public void setScenePictures(String scenePictures) {
        this.scenePictures = scenePictures;
    }

    public String getScenePictureNames() {
        return scenePictureNames;
    }

    public void setScenePictureNames(String scenePictureNames) {
        this.scenePictureNames = scenePictureNames;
    }

    public String getPrincipalSignatures() {
        return principalSignatures;
    }

    public void setPrincipalSignatures(String principalSignatures) {
        this.principalSignatures = principalSignatures;
    }

    public String getPrincipalSignatureNames() {
        return principalSignatureNames;
    }

    public void setPrincipalSignatureNames(String principalSignatureNames) {
        this.principalSignatureNames = principalSignatureNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlesDailyEnforceLaw that = (AlesDailyEnforceLaw) o;

        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (taskBeginTime != null ? !taskBeginTime.equals(that.taskBeginTime) : that.taskBeginTime != null)
            return false;
        if (taskEndTime != null ? !taskEndTime.equals(that.taskEndTime) : that.taskEndTime != null) return false;
        if (enterpriseId != null ? !enterpriseId.equals(that.enterpriseId) : that.enterpriseId != null) return false;
        if (areaId != null ? !areaId.equals(that.areaId) : that.areaId != null) return false;
        if (enterpriseAddress != null ? !enterpriseAddress.equals(that.enterpriseAddress) : that.enterpriseAddress != null)
            return false;
        if (taskPersonCount != null ? !taskPersonCount.equals(that.taskPersonCount) : that.taskPersonCount != null)
            return false;
        if (taskPersonId != null ? !taskPersonId.equals(that.taskPersonId) : that.taskPersonId != null) return false;
        if (enforceLawResult != null ? !enforceLawResult.equals(that.enforceLawResult) : that.enforceLawResult != null)
            return false;
        if (enable != null ? !enable.equals(that.enable) : that.enable != null) return false;
        if (enterpriseName != null ? !enterpriseName.equals(that.enterpriseName) : that.enterpriseName != null)
            return false;
        if (createOrgId != null ? !createOrgId.equals(that.createOrgId) : that.createOrgId != null) return false;
        if (enforceLawResultFlag != null ? !enforceLawResultFlag.equals(that.enforceLawResultFlag) : that.enforceLawResultFlag != null)
            return false;
        if (taskPersonName != null ? !taskPersonName.equals(that.taskPersonName) : that.taskPersonName != null)
            return false;
        if (enterpriseIdcode != null ? !enterpriseIdcode.equals(that.enterpriseIdcode) : that.enterpriseIdcode != null)
            return false;
        if (principal != null ? !principal.equals(that.principal) : that.principal != null) return false;
        if (scenePictures != null ? !scenePictures.equals(that.scenePictures) : that.scenePictures != null)
            return false;
        if (scenePictureNames != null ? !scenePictureNames.equals(that.scenePictureNames) : that.scenePictureNames != null)
            return false;
        if (principalSignatures != null ? !principalSignatures.equals(that.principalSignatures) : that.principalSignatures != null)
            return false;
        return principalSignatureNames != null ? principalSignatureNames.equals(that.principalSignatureNames) : that.principalSignatureNames == null;

    }

    @Override
    public int hashCode() {
        int result = taskName != null ? taskName.hashCode() : 0;
        result = 31 * result + (taskBeginTime != null ? taskBeginTime.hashCode() : 0);
        result = 31 * result + (taskEndTime != null ? taskEndTime.hashCode() : 0);
        result = 31 * result + (enterpriseId != null ? enterpriseId.hashCode() : 0);
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (enterpriseAddress != null ? enterpriseAddress.hashCode() : 0);
        result = 31 * result + (taskPersonCount != null ? taskPersonCount.hashCode() : 0);
        result = 31 * result + (taskPersonId != null ? taskPersonId.hashCode() : 0);
        result = 31 * result + (enforceLawResult != null ? enforceLawResult.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (enterpriseName != null ? enterpriseName.hashCode() : 0);
        result = 31 * result + (createOrgId != null ? createOrgId.hashCode() : 0);
        result = 31 * result + (enforceLawResultFlag != null ? enforceLawResultFlag.hashCode() : 0);
        result = 31 * result + (taskPersonName != null ? taskPersonName.hashCode() : 0);
        result = 31 * result + (enterpriseIdcode != null ? enterpriseIdcode.hashCode() : 0);
        result = 31 * result + (principal != null ? principal.hashCode() : 0);
        result = 31 * result + (scenePictures != null ? scenePictures.hashCode() : 0);
        result = 31 * result + (scenePictureNames != null ? scenePictureNames.hashCode() : 0);
        result = 31 * result + (principalSignatures != null ? principalSignatures.hashCode() : 0);
        result = 31 * result + (principalSignatureNames != null ? principalSignatureNames.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AlesDailyEnforceLaw{" +
                "taskName='" + taskName + '\'' +
                ", taskBeginTime=" + taskBeginTime +
                ", taskEndTime=" + taskEndTime +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", areaId='" + areaId + '\'' +
                ", enterpriseAddress='" + enterpriseAddress + '\'' +
                ", taskPersonCount='" + taskPersonCount + '\'' +
                ", taskPersonId='" + taskPersonId + '\'' +
                ", enforceLawResult='" + enforceLawResult + '\'' +
                ", enable='" + enable + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", createOrgId='" + createOrgId + '\'' +
                ", enforceLawResultFlag='" + enforceLawResultFlag + '\'' +
                ", taskPersonName='" + taskPersonName + '\'' +
                ", enterpriseIdcode='" + enterpriseIdcode + '\'' +
                ", principal='" + principal + '\'' +
                ", scenePictures='" + scenePictures + '\'' +
                ", scenePictureNames='" + scenePictureNames + '\'' +
                ", principalSignatures='" + principalSignatures + '\'' +
                ", principalSignatureNames='" + principalSignatureNames + '\'' +
                '}';
    }

}