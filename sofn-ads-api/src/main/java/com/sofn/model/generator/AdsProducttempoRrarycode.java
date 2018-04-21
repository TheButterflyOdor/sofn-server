package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品临时码模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class AdsProducttempoRrarycode extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	public String mainbodyCategories; //主体类别
	public String organizationForms; //组织形式
	public String mainbodyNumber; //主体号
	public String projectCategoryCode; //农产品分类码
	public String serviceType; //业务类型
	public String mainbodyAddress; //主体地址
	public String column1; //备注1
	public String column2; //备注2
	public String column3; //备注3
	
	
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
	 //主体类别 get
	public String getMainbodyCategories(){
        return mainbodyCategories;
    }
	//主体类别 set
    public void setMainbodyCategories(String mainbodyCategories){
        this.mainbodyCategories = mainbodyCategories;
    }
	 //组织形式 get
	public String getOrganizationForms(){
        return organizationForms;
    }
	//组织形式 set
    public void setOrganizationForms(String organizationForms){
        this.organizationForms = organizationForms;
    }
	 //主体号 get
	public String getMainbodyNumber(){
        return mainbodyNumber;
    }
	//主体号 set
    public void setMainbodyNumber(String mainbodyNumber){
        this.mainbodyNumber = mainbodyNumber;
    }
	 //农产品分类码 get
	public String getProjectCategoryCode(){
        return projectCategoryCode;
    }
	//农产品分类码 set
    public void setProjectCategoryCode(String projectCategoryCode){
        this.projectCategoryCode = projectCategoryCode;
    }
	 //业务类型 get
	public String getServiceType(){
        return serviceType;
    }
	//业务类型 set
    public void setServiceType(String serviceType){
        this.serviceType = serviceType;
    }
	 //主体地址 get
	public String getMainbodyAddress(){
        return mainbodyAddress;
    }
	//主体地址 set
    public void setMainbodyAddress(String mainbodyAddress){
        this.mainbodyAddress = mainbodyAddress;
    }
	 //备注1 get
	public String getColumn1(){
        return column1;
    }
	//备注1 set
    public void setColumn1(String column1){
        this.column1 = column1;
    }
	 //备注2 get
	public String getColumn2(){
        return column2;
    }
	//备注2 set
    public void setColumn2(String column2){
        this.column2 = column2;
    }
	 //备注3 get
	public String getColumn3(){
        return column3;
    }
	//备注3 set
    public void setColumn3(String column3){
        this.column3 = column3;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mainbodyCategories=").append(mainbodyCategories);
        sb.append(", organizationForms=").append(organizationForms);
        sb.append(", mainbodyNumber=").append(mainbodyNumber);
        sb.append(", projectCategoryCode=").append(projectCategoryCode);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", mainbodyAddress=").append(mainbodyAddress);
        sb.append(", column1=").append(column1);
        sb.append(", column2=").append(column2);
        sb.append(", column3=").append(column3);
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
        AdsProducttempoRrarycode other = (AdsProducttempoRrarycode) that;
          return 
        		(this.getMainbodyCategories() == null ? other.getMainbodyCategories() == null : this.getMainbodyCategories().equals(other.getMainbodyCategories())) &&
        		(this.getOrganizationForms() == null ? other.getOrganizationForms() == null : this.getOrganizationForms().equals(other.getOrganizationForms())) &&
        		(this.getMainbodyNumber() == null ? other.getMainbodyNumber() == null : this.getMainbodyNumber().equals(other.getMainbodyNumber())) &&
        		(this.getProjectCategoryCode() == null ? other.getProjectCategoryCode() == null : this.getProjectCategoryCode().equals(other.getProjectCategoryCode())) &&
        		(this.getServiceType() == null ? other.getServiceType() == null : this.getServiceType().equals(other.getServiceType())) &&
        		(this.getMainbodyAddress() == null ? other.getMainbodyAddress() == null : this.getMainbodyAddress().equals(other.getMainbodyAddress())) &&
        		(this.getColumn1() == null ? other.getColumn1() == null : this.getColumn1().equals(other.getColumn1())) &&
        		(this.getColumn2() == null ? other.getColumn2() == null : this.getColumn2().equals(other.getColumn2())) &&
        		(this.getColumn3() == null ? other.getColumn3() == null : this.getColumn3().equals(other.getColumn3())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMainbodyCategories() == null) ? 0 : getMainbodyCategories().hashCode());
        result = prime * result + ((getOrganizationForms() == null) ? 0 : getOrganizationForms().hashCode());
        result = prime * result + ((getMainbodyNumber() == null) ? 0 : getMainbodyNumber().hashCode());
        result = prime * result + ((getProjectCategoryCode() == null) ? 0 : getProjectCategoryCode().hashCode());
        result = prime * result + ((getServiceType() == null) ? 0 : getServiceType().hashCode());
        result = prime * result + ((getMainbodyAddress() == null) ? 0 : getMainbodyAddress().hashCode());
        result = prime * result + ((getColumn1() == null) ? 0 : getColumn1().hashCode());
        result = prime * result + ((getColumn2() == null) ? 0 : getColumn2().hashCode());
        result = prime * result + ((getColumn3() == null) ? 0 : getColumn3().hashCode());
        return result;
    }
	
}