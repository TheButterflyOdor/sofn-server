package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Sysfiles extends BaseModel {
    private String tsname;

    private String fname;

    private BigDecimal blocks;

    public String getTsname() {
        return tsname;
    }

    public void setTsname(String tsname) {
        this.tsname = tsname == null ? null : tsname.trim();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public BigDecimal getBlocks() {
        return blocks;
    }

    public void setBlocks(BigDecimal blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tsname=").append(tsname);
        sb.append(", fname=").append(fname);
        sb.append(", blocks=").append(blocks);
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
        Sysfiles other = (Sysfiles) that;
        return (this.getTsname() == null ? other.getTsname() == null : this.getTsname().equals(other.getTsname()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getBlocks() == null ? other.getBlocks() == null : this.getBlocks().equals(other.getBlocks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTsname() == null) ? 0 : getTsname().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getBlocks() == null) ? 0 : getBlocks().hashCode());
        return result;
    }
}