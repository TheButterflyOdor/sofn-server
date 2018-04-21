package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Syssegobj extends BaseModel {
    private BigDecimal obj;

    private BigDecimal file;

    private BigDecimal block;

    private String type;

    private BigDecimal pctfree;

    private BigDecimal pctused;

    public BigDecimal getObj() {
        return obj;
    }

    public void setObj(BigDecimal obj) {
        this.obj = obj;
    }

    public BigDecimal getFile() {
        return file;
    }

    public void setFile(BigDecimal file) {
        this.file = file;
    }

    public BigDecimal getBlock() {
        return block;
    }

    public void setBlock(BigDecimal block) {
        this.block = block;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getPctfree() {
        return pctfree;
    }

    public void setPctfree(BigDecimal pctfree) {
        this.pctfree = pctfree;
    }

    public BigDecimal getPctused() {
        return pctused;
    }

    public void setPctused(BigDecimal pctused) {
        this.pctused = pctused;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", obj=").append(obj);
        sb.append(", file=").append(file);
        sb.append(", block=").append(block);
        sb.append(", type=").append(type);
        sb.append(", pctfree=").append(pctfree);
        sb.append(", pctused=").append(pctused);
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
        Syssegobj other = (Syssegobj) that;
        return (this.getObj() == null ? other.getObj() == null : this.getObj().equals(other.getObj()))
            && (this.getFile() == null ? other.getFile() == null : this.getFile().equals(other.getFile()))
            && (this.getBlock() == null ? other.getBlock() == null : this.getBlock().equals(other.getBlock()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPctfree() == null ? other.getPctfree() == null : this.getPctfree().equals(other.getPctfree()))
            && (this.getPctused() == null ? other.getPctused() == null : this.getPctused().equals(other.getPctused()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getObj() == null) ? 0 : getObj().hashCode());
        result = prime * result + ((getFile() == null) ? 0 : getFile().hashCode());
        result = prime * result + ((getBlock() == null) ? 0 : getBlock().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPctfree() == null) ? 0 : getPctfree().hashCode());
        result = prime * result + ((getPctused() == null) ? 0 : getPctused().hashCode());
        return result;
    }
}