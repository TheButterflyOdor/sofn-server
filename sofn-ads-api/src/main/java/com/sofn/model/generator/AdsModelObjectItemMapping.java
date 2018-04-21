package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 模型_检测对象与检测项目关联映射模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsModelObjectItemMapping extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	private String objectId; //对象id
	private String itemId; //项目id
    private int rn;  //序号
    private String ids;  //批处理id
    private String name;  //项目名称
	
	
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
	 //对象id get
	public String getObjectId(){
        return objectId;
    }
	//对象id set
    public void setObjectId(String objectId){
        this.objectId = objectId;
    }
	 //项目id get
	public String getItemId(){
        return itemId;
    }
	//项目id set
    public void setItemId(String itemId){
        this.itemId = itemId;
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
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", objectId=").append(objectId);
        sb.append(", itemId=").append(itemId);
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
        AdsModelObjectItemMapping other = (AdsModelObjectItemMapping) that;
          return 
        		(this.getObjectId() == null ? other.getObjectId() == null : this.getObjectId().equals(other.getObjectId())) &&
        		(this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getObjectId() == null) ? 0 : getObjectId().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        return result;
    }
	
}