package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsCheckTaskReport extends BaseModel {
    private String fileAddress;

    private String monitorTaskId;

    private String monitorTask;

    private String uploadDepartment;

    private String reservedField1;

    private String reservedField2;

    private String fileName;

    private String monitorType;

    private String years;

    private String source;

    private String folder;

    private String field;

    private String organTaskId;

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress == null ? null : fileAddress.trim();
    }

    public String getMonitorTaskId() {
        return monitorTaskId;
    }

    public void setMonitorTaskId(String monitorTaskId) {
        this.monitorTaskId = monitorTaskId == null ? null : monitorTaskId.trim();
    }

    public String getMonitorTask() {
        return monitorTask;
    }

    public void setMonitorTask(String monitorTask) {
        this.monitorTask = monitorTask == null ? null : monitorTask.trim();
    }

    public String getUploadDepartment() {
        return uploadDepartment;
    }

    public void setUploadDepartment(String uploadDepartment) {
        this.uploadDepartment = uploadDepartment == null ? null : uploadDepartment.trim();
    }

    public String getReservedField1() {
        return reservedField1;
    }

    public void setReservedField1(String reservedField1) {
        this.reservedField1 = reservedField1 == null ? null : reservedField1.trim();
    }

    public String getReservedField2() {
        return reservedField2;
    }

    public void setReservedField2(String reservedField2) {
        this.reservedField2 = reservedField2 == null ? null : reservedField2.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType == null ? null : monitorType.trim();
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder == null ? null : folder.trim();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    public String getOrganTaskId() {
        return organTaskId;
    }

    public void setOrganTaskId(String organTaskId) {
        this.organTaskId = organTaskId == null ? null : organTaskId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileAddress=").append(fileAddress);
        sb.append(", monitorTaskId=").append(monitorTaskId);
        sb.append(", monitorTask=").append(monitorTask);
        sb.append(", uploadDepartment=").append(uploadDepartment);
        sb.append(", reservedField1=").append(reservedField1);
        sb.append(", reservedField2=").append(reservedField2);
        sb.append(", fileName=").append(fileName);
        sb.append(", monitorType=").append(monitorType);
        sb.append(", years=").append(years);
        sb.append(", source=").append(source);
        sb.append(", folder=").append(folder);
        sb.append(", field=").append(field);
        sb.append(", organTaskId=").append(organTaskId);
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
        AsmsCheckTaskReport other = (AsmsCheckTaskReport) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFileAddress() == null ? other.getFileAddress() == null : this.getFileAddress().equals(other.getFileAddress()))
            && (this.getMonitorTaskId() == null ? other.getMonitorTaskId() == null : this.getMonitorTaskId().equals(other.getMonitorTaskId()))
            && (this.getMonitorTask() == null ? other.getMonitorTask() == null : this.getMonitorTask().equals(other.getMonitorTask()))
            && (this.getUploadDepartment() == null ? other.getUploadDepartment() == null : this.getUploadDepartment().equals(other.getUploadDepartment()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getReservedField1() == null ? other.getReservedField1() == null : this.getReservedField1().equals(other.getReservedField1()))
            && (this.getReservedField2() == null ? other.getReservedField2() == null : this.getReservedField2().equals(other.getReservedField2()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getMonitorType() == null ? other.getMonitorType() == null : this.getMonitorType().equals(other.getMonitorType()))
            && (this.getYears() == null ? other.getYears() == null : this.getYears().equals(other.getYears()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getFolder() == null ? other.getFolder() == null : this.getFolder().equals(other.getFolder()))
            && (this.getField() == null ? other.getField() == null : this.getField().equals(other.getField()))
            && (this.getOrganTaskId() == null ? other.getOrganTaskId() == null : this.getOrganTaskId().equals(other.getOrganTaskId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFileAddress() == null) ? 0 : getFileAddress().hashCode());
        result = prime * result + ((getMonitorTaskId() == null) ? 0 : getMonitorTaskId().hashCode());
        result = prime * result + ((getMonitorTask() == null) ? 0 : getMonitorTask().hashCode());
        result = prime * result + ((getUploadDepartment() == null) ? 0 : getUploadDepartment().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getReservedField1() == null) ? 0 : getReservedField1().hashCode());
        result = prime * result + ((getReservedField2() == null) ? 0 : getReservedField2().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getMonitorType() == null) ? 0 : getMonitorType().hashCode());
        result = prime * result + ((getYears() == null) ? 0 : getYears().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getFolder() == null) ? 0 : getFolder().hashCode());
        result = prime * result + ((getField() == null) ? 0 : getField().hashCode());
        result = prime * result + ((getOrganTaskId() == null) ? 0 : getOrganTaskId().hashCode());
        return result;
    }
}