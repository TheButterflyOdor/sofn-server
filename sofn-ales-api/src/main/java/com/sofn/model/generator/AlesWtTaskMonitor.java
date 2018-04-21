package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;
import java.util.Date;
/**
 * 委托监测对象
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@SuppressWarnings("serial")
public class AlesWtTaskMonitor extends BaseModel {
    private String superviseCheckTaskId;//任务id

    private String productType;//产品类型

    private String productName;//产品名称

    private Date taskBeginTime;//开始时间

    private Date taskEndTime;//结束时间

    private String sampleUnitId;

    private String detectionUnitId;

    private String detectionStandard;

    private String judgeStandard;

    private String enable;

    private String monitorNum;//抽样数量

    private String isSample;//是否已提交抽样单信息 0 未提交 1 已提交

    private String detectionItem;//检测项目

    private String detectionItemId;//检测项目数据字典ID

    private String productTypeId;//产品类型id

    private String productNameId;//产品名称id

    private String areaId;//受检区域

    private String industry;//所属行业

    private String status; //状态（2018年2月10日10:13:54）

    public String getSuperviseCheckTaskId() {
        return superviseCheckTaskId;
    }

    public void setSuperviseCheckTaskId(String superviseCheckTaskId) {
        this.superviseCheckTaskId = superviseCheckTaskId == null ? null : superviseCheckTaskId.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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

    public String getSampleUnitId() {
        return sampleUnitId;
    }

    public void setSampleUnitId(String sampleUnitId) {
        this.sampleUnitId = sampleUnitId == null ? null : sampleUnitId.trim();
    }

    public String getDetectionUnitId() {
        return detectionUnitId;
    }

    public void setDetectionUnitId(String detectionUnitId) {
        this.detectionUnitId = detectionUnitId == null ? null : detectionUnitId.trim();
    }

    public String getDetectionStandard() {
        return detectionStandard;
    }

    public void setDetectionStandard(String detectionStandard) {
        this.detectionStandard = detectionStandard == null ? null : detectionStandard.trim();
    }

    public String getJudgeStandard() {
        return judgeStandard;
    }

    public void setJudgeStandard(String judgeStandard) {
        this.judgeStandard = judgeStandard == null ? null : judgeStandard.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getMonitorNum() {
        return monitorNum;
    }

    public void setMonitorNum(String monitorNum) {
        this.monitorNum = monitorNum == null ? null : monitorNum.trim();
    }

    public String getIsSample() {
        return isSample;
    }

    public void setIsSample(String isSample) {
        this.isSample = isSample == null ? null : isSample.trim();
    }

    public String getDetectionItem() {
        return detectionItem;
    }

    public void setDetectionItem(String detectionItem) {
        this.detectionItem = detectionItem == null ? null : detectionItem.trim();
    }

    public String getDetectionItemId() {
        return detectionItemId;
    }

    public void setDetectionItemId(String detectionItemId) {
        this.detectionItemId = detectionItemId == null ? null : detectionItemId.trim();
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId == null ? null : productTypeId.trim();
    }

    public String getProductNameId() {
        return productNameId;
    }

    public void setProductNameId(String productNameId) {
        this.productNameId = productNameId == null ? null : productNameId.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", superviseCheckTaskId=").append(superviseCheckTaskId);
        sb.append(", productType=").append(productType);
        sb.append(", productName=").append(productName);
        sb.append(", taskBeginTime=").append(taskBeginTime);
        sb.append(", taskEndTime=").append(taskEndTime);
        sb.append(", sampleUnitId=").append(sampleUnitId);
        sb.append(", detectionUnitId=").append(detectionUnitId);
        sb.append(", detectionStandard=").append(detectionStandard);
        sb.append(", judgeStandard=").append(judgeStandard);
        sb.append(", enable=").append(enable);
        sb.append(", monitorNum=").append(monitorNum);
        sb.append(", isSample=").append(isSample);
        sb.append(", detectionItem=").append(detectionItem);
        sb.append(", detectionItemId=").append(detectionItemId);
        sb.append(", productTypeId=").append(productTypeId);
        sb.append(", productNameId=").append(productNameId);
        sb.append(", areaId=").append(areaId);
        sb.append(", industry=").append(industry);
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
        AlesWtTaskMonitor other = (AlesWtTaskMonitor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSuperviseCheckTaskId() == null ? other.getSuperviseCheckTaskId() == null : this.getSuperviseCheckTaskId().equals(other.getSuperviseCheckTaskId()))
            && (this.getProductType() == null ? other.getProductType() == null : this.getProductType().equals(other.getProductType()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getTaskBeginTime() == null ? other.getTaskBeginTime() == null : this.getTaskBeginTime().equals(other.getTaskBeginTime()))
            && (this.getTaskEndTime() == null ? other.getTaskEndTime() == null : this.getTaskEndTime().equals(other.getTaskEndTime()))
            && (this.getSampleUnitId() == null ? other.getSampleUnitId() == null : this.getSampleUnitId().equals(other.getSampleUnitId()))
            && (this.getDetectionUnitId() == null ? other.getDetectionUnitId() == null : this.getDetectionUnitId().equals(other.getDetectionUnitId()))
            && (this.getDetectionStandard() == null ? other.getDetectionStandard() == null : this.getDetectionStandard().equals(other.getDetectionStandard()))
            && (this.getJudgeStandard() == null ? other.getJudgeStandard() == null : this.getJudgeStandard().equals(other.getJudgeStandard()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getMonitorNum() == null ? other.getMonitorNum() == null : this.getMonitorNum().equals(other.getMonitorNum()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getIsSample() == null ? other.getIsSample() == null : this.getIsSample().equals(other.getIsSample()))
            && (this.getDetectionItem() == null ? other.getDetectionItem() == null : this.getDetectionItem().equals(other.getDetectionItem()))
            && (this.getDetectionItemId() == null ? other.getDetectionItemId() == null : this.getDetectionItemId().equals(other.getDetectionItemId()))
            && (this.getProductTypeId() == null ? other.getProductTypeId() == null : this.getProductTypeId().equals(other.getProductTypeId()))
            && (this.getProductNameId() == null ? other.getProductNameId() == null : this.getProductNameId().equals(other.getProductNameId()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getIndustry() == null ? other.getIndustry() == null : this.getIndustry().equals(other.getIndustry()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSuperviseCheckTaskId() == null) ? 0 : getSuperviseCheckTaskId().hashCode());
        result = prime * result + ((getProductType() == null) ? 0 : getProductType().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getTaskBeginTime() == null) ? 0 : getTaskBeginTime().hashCode());
        result = prime * result + ((getTaskEndTime() == null) ? 0 : getTaskEndTime().hashCode());
        result = prime * result + ((getSampleUnitId() == null) ? 0 : getSampleUnitId().hashCode());
        result = prime * result + ((getDetectionUnitId() == null) ? 0 : getDetectionUnitId().hashCode());
        result = prime * result + ((getDetectionStandard() == null) ? 0 : getDetectionStandard().hashCode());
        result = prime * result + ((getJudgeStandard() == null) ? 0 : getJudgeStandard().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getMonitorNum() == null) ? 0 : getMonitorNum().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getIsSample() == null) ? 0 : getIsSample().hashCode());
        result = prime * result + ((getDetectionItem() == null) ? 0 : getDetectionItem().hashCode());
        result = prime * result + ((getDetectionItemId() == null) ? 0 : getDetectionItemId().hashCode());
        result = prime * result + ((getProductTypeId() == null) ? 0 : getProductTypeId().hashCode());
        result = prime * result + ((getProductNameId() == null) ? 0 : getProductNameId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getIndustry() == null) ? 0 : getIndustry().hashCode());
        return result;
    }
}