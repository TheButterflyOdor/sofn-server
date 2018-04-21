package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class AsmsSubjEntBadrecord extends BaseModel {
    private String enterpriseId;

    private String badrecordContent;

    private String badrecordFile;

    private String sourceId;

    private String sourceType;

    private String badrecordFileName;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
    }

    public String getBadrecordContent() {
        return badrecordContent;
    }

    public void setBadrecordContent(String badrecordContent) {
        this.badrecordContent = badrecordContent == null ? null : badrecordContent.trim();
    }

    public String getBadrecordFile() {
        return badrecordFile;
    }

    public void setBadrecordFile(String badrecordFile) {
        this.badrecordFile = badrecordFile == null ? null : badrecordFile.trim();
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getBadrecordFileName() {
        return badrecordFileName;
    }

    public void setBadrecordFileName(String badrecordFileName) {
        this.badrecordFileName = badrecordFileName == null ? null : badrecordFileName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", badrecordContent=").append(badrecordContent);
        sb.append(", badrecordFile=").append(badrecordFile);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", badrecordFileName=").append(badrecordFileName);
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
        AsmsSubjEntBadrecord other = (AsmsSubjEntBadrecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEnterpriseId() == null ? other.getEnterpriseId() == null : this.getEnterpriseId().equals(other.getEnterpriseId()))
            && (this.getBadrecordContent() == null ? other.getBadrecordContent() == null : this.getBadrecordContent().equals(other.getBadrecordContent()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getBadrecordFile() == null ? other.getBadrecordFile() == null : this.getBadrecordFile().equals(other.getBadrecordFile()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getSourceId() == null ? other.getSourceId() == null : this.getSourceId().equals(other.getSourceId()))
            && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()))
            && (this.getBadrecordFileName() == null ? other.getBadrecordFileName() == null : this.getBadrecordFileName().equals(other.getBadrecordFileName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEnterpriseId() == null) ? 0 : getEnterpriseId().hashCode());
        result = prime * result + ((getBadrecordContent() == null) ? 0 : getBadrecordContent().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getBadrecordFile() == null) ? 0 : getBadrecordFile().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getSourceId() == null) ? 0 : getSourceId().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        result = prime * result + ((getBadrecordFileName() == null) ? 0 : getBadrecordFileName().hashCode());
        return result;
    }
}