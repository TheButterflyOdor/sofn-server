package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

/**
 * 屠宰记录模型对象
 * @author moon.l
 *
 */
@SuppressWarnings("serial")
public class TtsScltxxcjSlaRecord extends BaseModel {


	/*
	* 对应字段
	*/
    //public String id;//主键

    private Date slaughterTime; //屠宰时间
    private String name; //屠宰物名称
    private String productPc; //屠宰物批次号
    private String slaughterAmount; //屠宰数量
    private String ip; //IP地址
    private String insideTraceCode; //内部追溯码


	/*
	* 主键

	public String getPrimaryKey() {
        return id;
    }

    public void setPrimaryKey(String key) {
        this.id = key;
    }

	public String getId(){
        return id;
    }
	//id set
    public void setId(String id){
        this.id = id;
    }
    */



    /*
    *其余字段
    */
    //屠宰时间 get
    public Date getSlaughterTime(){
        return slaughterTime;
    }
    //屠宰时间 set
    public void setSlaughterTime(Date slaughterTime){
        this.slaughterTime = slaughterTime;
    }
    //屠宰物名称 get
    public String getName(){
        return name;
    }
    //屠宰物名称 set
    public void setName(String name){
        this.name = name;
    }
    //屠宰物批次号 get
    public String getProductPc(){
        return productPc;
    }
    //屠宰物批次号 set
    public void setProductPc(String productPc){
        this.productPc = productPc;
    }
    //屠宰数量 get
    public String getSlaughterAmount(){
        return slaughterAmount;
    }
    //屠宰数量 set
    public void setSlaughterAmount(String slaughterAmount){
        this.slaughterAmount = slaughterAmount;
    }
    //IP地址 get
    public String getIp(){
        return ip;
    }
    //IP地址 set
    public void setIp(String ip){
        this.ip = ip;
    }
    //
    public String getInsideTraceCode(){
        return insideTraceCode;
    }
    //所属企业 set
    public void setInsideTraceCode(String insideTraceCode){
        this.insideTraceCode = insideTraceCode;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", slaughterTime=").append(slaughterTime);
        sb.append(", name=").append(name);
        sb.append(", productPc=").append(productPc);
        sb.append(", slaughterAmount=").append(slaughterAmount);
        sb.append(", ip=").append(ip);
        sb.append(", insideTraceCode=").append(insideTraceCode);
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
        TtsScltxxcjSlaRecord other = (TtsScltxxcjSlaRecord) that;
        return
                (this.getSlaughterTime() == null ? other.getSlaughterTime() == null : this.getSlaughterTime().equals(other.getSlaughterTime())) &&
                        (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
                        (this.getProductPc() == null ? other.getProductPc() == null : this.getProductPc().equals(other.getProductPc())) &&
                        (this.getSlaughterAmount() == null ? other.getSlaughterAmount() == null : this.getSlaughterAmount().equals(other.getSlaughterAmount())) &&
                        (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp())) &&
                        (this.getInsideTraceCode() == null ? other.getInsideTraceCode() == null : this.getInsideTraceCode().equals(other.getInsideTraceCode())) &&
                        (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSlaughterTime() == null) ? 0 : getSlaughterTime().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getProductPc() == null) ? 0 : getProductPc().hashCode());
        result = prime * result + ((getSlaughterAmount() == null) ? 0 : getSlaughterAmount().hashCode());
        result = prime * result + ((getInsideTraceCode() == null) ? 0 : getInsideTraceCode().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        return result;
    }

}