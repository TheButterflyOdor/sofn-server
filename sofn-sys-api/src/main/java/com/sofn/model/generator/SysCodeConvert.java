package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

@SuppressWarnings("serial")
public class SysCodeConvert extends BaseModel {
    private String codeLong;

    private String codeShort;

    public String getCodeLong() {
        return codeLong;
    }

    public void setCodeLong(String codeLong) {
        this.codeLong = codeLong == null ? null : codeLong.trim();
    }

    public String getCodeShort() {
        return codeShort;
    }

    public void setCodeShort(String codeShort) {
        this.codeShort = codeShort == null ? null : codeShort.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", codeLong=").append(codeLong);
        sb.append(", codeShort=").append(codeShort);
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
        SysCodeConvert other = (SysCodeConvert) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCodeLong() == null ? other.getCodeLong() == null : this.getCodeLong().equals(other.getCodeLong()))
            && (this.getCodeShort() == null ? other.getCodeShort() == null : this.getCodeShort().equals(other.getCodeShort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCodeLong() == null) ? 0 : getCodeLong().hashCode());
        result = prime * result + ((getCodeShort() == null) ? 0 : getCodeShort().hashCode());
        return result;
    }
}