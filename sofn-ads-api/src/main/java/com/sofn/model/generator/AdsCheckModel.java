package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 检测模型模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsCheckModel extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	private String modelName; //模型名称
	private String monitorType; //监测类型
	private String industry; //行业
    private int rn;  //序号
    private String ids;  //批处理id
    private String industryId; //行业id
    private String isEnable; //是否启用 0: 未启用 1:已启用
    private String organId; //创建单位ID
	
	
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
	 //模型名称 get
	public String getModelName(){
        return modelName;
    }
	//模型名称 set
    public void setModelName(String modelName){
        this.modelName = modelName;
    }
	 //监测类型 get
	public String getMonitorType(){
        return monitorType;
    }
	//监测类型 set
    public void setMonitorType(String monitorType){
        this.monitorType = monitorType;
    }
	 //行业 get
	public String getIndustry(){
        return industry;
    }
	//行业 set
    public void setIndustry(String industry){
        this.industry = industry;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    //行业id get
    public String getIndustryId(){
        return industryId;
    }
    //行业id set
    public void setIndustryId(String industryId){
        this.industryId = industryId;
    }

    public String getIsEnable(){
        return isEnable;
    }

    public void setIsEnable(String isEnable){
        this.isEnable = isEnable;
    }

    public String getOrganId(){
        return organId;
    }

    public void setOrganId(String organId){
        this.organId = organId;
    }

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", modelName=").append(modelName);
        sb.append(", monitorType=").append(monitorType);
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
        AdsCheckModel other = (AdsCheckModel) that;
          return 
        		(this.getModelName() == null ? other.getModelName() == null : this.getModelName().equals(other.getModelName())) &&
        		(this.getMonitorType() == null ? other.getMonitorType() == null : this.getMonitorType().equals(other.getMonitorType())) &&
        		(this.getIndustry() == null ? other.getIndustry() == null : this.getIndustry().equals(other.getIndustry())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModelName() == null) ? 0 : getModelName().hashCode());
        result = prime * result + ((getMonitorType() == null) ? 0 : getMonitorType().hashCode());
        result = prime * result + ((getIndustry() == null) ? 0 : getIndustry().hashCode());
        return result;
    }
	
}