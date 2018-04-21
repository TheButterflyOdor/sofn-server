package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * 基地信息管理模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjBase extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	private String pictureTwo; //基地图片2
    private String name; //基地名称
    private String area; //基地面积
    private String address; //基地地址
    private String manager; //负责人
    private String phone; //电话
    private String productNames; //主要产品
    private String longitude; //经度
    private String latitude; //纬度
    private String picture; //基地图片1
    private String status; //0不限，1启用，2停用
    private String ip; //ip地址
    private String userIdcode; //主体用户码
    private String entityIdcode; //主体身份码
    private String addressCode; //地区代码
    private String addressName; //基地区域名称

    private String dictName;//基地单位名称
    
    /*
    *其余字段
    */
	 //基地图片2 get
	public String getPictureTwo(){
        return pictureTwo;
    }
	//基地图片2 set
    public void setPictureTwo(String pictureTwo){
        this.pictureTwo = pictureTwo;
    }
	 //基地名称 get
	public String getName(){
        return name;
    }
	//基地名称 set
    public void setName(String name){
        this.name = name;
    }
	 //基地面积 get
	public String getArea(){
        return area;
    }
	//基地面积 set
    public void setArea(String area){
        this.area = area;
    }
	 //基地地址 get
	public String getAddress(){
        return address;
    }
	//基地地址 set
    public void setAddress(String address){
        this.address = address;
    }
	 //负责人 get
	public String getManager(){
        return manager;
    }
	//负责人 set
    public void setManager(String manager){
        this.manager = manager;
    }
	 //电话 get
	public String getPhone(){
        return phone;
    }
	//电话 set
    public void setPhone(String phone){
        this.phone = phone;
    }
	 //主要产品 get
	public String getProductNames(){
        return productNames;
    }
	//主要产品 set
    public void setProductNames(String productNames){
        this.productNames = productNames;
    }
	 //经度 get
	public String getLongitude(){
        return longitude;
    }
	//经度 set
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
	 //纬度 get
	public String getLatitude(){
        return latitude;
    }
	//纬度 set
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
	 //基地图片1 get
	public String getPicture(){
        return picture;
    }
	//基地图片1 set
    public void setPicture(String picture){
        this.picture = picture;
    }
	 //0不限，1启用，2停用 get
	public String getStatus(){
        return status;
    }
	//0不限，1启用，2停用 set
    public void setStatus(String status){
        this.status = status;
    }
	 //ip地址 get
	public String getIp(){
        return ip;
    }
	//ip地址 set
    public void setIp(String ip){
        this.ip = ip;
    }
    //主体用户码
    public String getUserIdcode() {
        return userIdcode;
    }
    //主体用户码
    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }
    //主体身份码
    public String getEntityIdcode() {
        return entityIdcode;
    }
    //主体身份码
    public void setEntityIdcode(String entityIdcode) {
        this.entityIdcode = entityIdcode;
    }
    //地区代码
    public String getAddressCode() {
        return addressCode;
    }
    //地区代码
    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }
    //基地区域名称
    public String getAddressName() {
        return addressName;
    }
    //基地区域名称
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    //基地名称
    public String getDictName() {
        return dictName;
    }
    //基地名称
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pictureTwo=").append(pictureTwo);
        sb.append(", name=").append(name);
        sb.append(", area=").append(area);
        sb.append(", address=").append(address);
        sb.append(", manager=").append(manager);
        sb.append(", phone=").append(phone);
        sb.append(", productNames=").append(productNames);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", picture=").append(picture);
        sb.append(", status=").append(status);
        sb.append(", ip=").append(ip);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", addressCode=").append(addressCode);
        sb.append(", addressName=").append(addressName);
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
        TtsScltxxcjBase other = (TtsScltxxcjBase) that;
          return 
        		(this.getPictureTwo() == null ? other.getPictureTwo() == null : this.getPictureTwo().equals(other.getPictureTwo())) &&
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        		(this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea())) &&
        		(this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress())) &&
        		(this.getManager() == null ? other.getManager() == null : this.getManager().equals(other.getManager())) &&
        		(this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone())) &&
        		(this.getProductNames() == null ? other.getProductNames() == null : this.getProductNames().equals(other.getProductNames())) &&
        		(this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude())) &&
        		(this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude())) &&
        		(this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture())) &&
        		(this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
        		(this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) &&
        	    (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
        	    (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
        	    (this.getAddressCode() == null ? other.getAddressCode() == null : this.getAddressCode().equals(other.getAddressCode())) &&
        	    (this.getAddressName() == null ? other.getAddressName() == null : this.getAddressName().equals(other.getAddressName()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPictureTwo() == null) ? 0 : getPictureTwo().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getManager() == null) ? 0 : getManager().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getProductNames() == null) ? 0 : getProductNames().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getAddressCode() == null) ? 0 : getAddressCode().hashCode());
        result = prime * result + ((getAddressName() == null) ? 0 : getAddressName().hashCode());
        return result;
    }
	
}