package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.List;

/**
 * 模型_检测对象模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsModelCheckObject extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String model_id; //模型id
	private String name; //对象名称
    private int rn;  //序号
    private String ids;  //批处理id
    private int numbers;//抽样数量
    private List<AdsModelCheckItem> itemList;    //检测项目列表
    private String gbCode;  //产品种类编号
	private String productCode; //产品编号
    private String productName; //产品种类名称
    private String industry;    //产品所属行业
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
	 //对象名称 get
	public String getName(){
        return name;
    }
	//对象名称 set
    public void setName(String name){
        this.name = name;
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

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public List<AdsModelCheckItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<AdsModelCheckItem> itemList) {
        this.itemList = itemList;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = gbCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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
        sb.append(", name=").append(name);
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
        AdsModelCheckObject other = (AdsModelCheckObject) that;
          return 
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }
	
}