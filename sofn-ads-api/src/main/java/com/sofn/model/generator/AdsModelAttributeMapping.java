package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 模型_检测模型关联属性模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsModelAttributeMapping extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	private String modelId; //模型id
	private String objectId; //子表对象id（种类、牵头单位、检测对象、监测地区、抽样环节、判定标准）
	private String type; //关联类型（种类、牵头单位、检测对象、监测地区、抽样环节、判定标准）
    private int rn;  //序号
    private String ids;  //批处理id
    private String name;  //关联名称
    private String filePath; //文件路径
	
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
	 //模型id get
	public String getModelId(){
        return modelId;
    }
	//模型id set
    public void setModelId(String modelId){
        this.modelId = modelId;
    }
	 //子表对象id（种类、牵头单位、检测对象、监测地区、抽样环节、判定标准） get
	public String getObjectId(){
        return objectId;
    }
	//子表对象id（种类、牵头单位、检测对象、监测地区、抽样环节、判定标准） set
    public void setObjectId(String objectId){
        this.objectId = objectId;
    }
	 //关联类型（种类、牵头单位、检测对象、监测地区、抽样环节、判定标准） get
	public String getType(){
        return type;
    }
	//关联类型（种类、牵头单位、检测对象、监测地区、抽样环节、判定标准） set
    public void setType(String type){
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", modelId=").append(modelId);
        sb.append(", objectId=").append(objectId);
        sb.append(", type=").append(type);
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
        AdsModelAttributeMapping other = (AdsModelAttributeMapping) that;
          return 
        		(this.getModelId() == null ? other.getModelId() == null : this.getModelId().equals(other.getModelId())) &&
        		(this.getObjectId() == null ? other.getObjectId() == null : this.getObjectId().equals(other.getObjectId())) &&
        		(this.getType() == null ? other.getType() == null : this.getType().equals(other.getType())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModelId() == null) ? 0 : getModelId().hashCode());
        result = prime * result + ((getObjectId() == null) ? 0 : getObjectId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }
	
}