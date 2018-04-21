package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

/**
 * 客户信息管理模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjCustomer extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String userIdCode; //主体用户码
    private String customerEntityIdCode; //客户主体身份码
    private String customerUserIdCode; //客户主体用户码
    private String ipAddress; //IP地址
    private String delflag; //删除标识
    private String createby; //创建人
    private String updateby; //修改人
    private Date updatetime; //修改时间
    private String name; //企业名称
    private String type; //客户类型
    private String organizationType; //组织形式
    private String legalRepresentative; //法人代表
    private String userName; //用户名字
    private String idCardNo; //身份证号码
    private String phone; //电话
    private String email; //邮箱
    private String address; //地址
    private String status; //状态
    private Date createtime; //创建时间
    private String entityIdCode; //主体身份码

    
    /*
    *其余字段
    */
	 //主体用户码 get
	public String getUserIdCode(){
        return userIdCode;
    }
	//主体用户码 set
    public void setUserIdCode(String userIdCode){
        this.userIdCode = userIdCode;
    }
	 //客户主体身份码 get
	public String getCustomerEntityIdCode(){
        return customerEntityIdCode;
    }
	//客户主体身份码 set
    public void setCustomerEntityIdCode(String customerEntityIdCode){
        this.customerEntityIdCode = customerEntityIdCode;
    }
	 //客户主体用户码 get
	public String getCustomerUserIdCode(){
        return customerUserIdCode;
    }
	//客户主体用户码 set
    public void setCustomerUserIdCode(String customerUserIdCode){
        this.customerUserIdCode = customerUserIdCode;
    }
	 //IP地址 get
	public String getIpAddress(){
        return ipAddress;
    }
	//IP地址 set
    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }
	 //删除标识 get
	public String getDelflag(){
        return delflag;
    }
	//删除标识 set
    public void setDelflag(String delflag){
        this.delflag = delflag;
    }
	 //创建人 get
	public String getCreateby(){
        return createby;
    }
	//创建人 set
    public void setCreateby(String createby){
        this.createby = createby;
    }
	 //修改人 get
	public String getUpdateby(){
        return updateby;
    }
	//修改人 set
    public void setUpdateby(String updateby){
        this.updateby = updateby;
    }
	 //修改时间 get
	public Date getUpdatetime(){
        return updatetime;
    }
	//修改时间 set
    public void setUpdatetime(Date updatetime){
        this.updatetime = updatetime;
    }
	 //客户名称 get
	public String getName(){
        return name;
    }
	//客户名称 set
    public void setName(String name){
        this.name = name;
    }
	 //客户类型 get
	public String getType(){
        return type;
    }
	//客户类型 set
    public void setType(String type){
        this.type = type;
    }
	 //组织形式 get
	public String getOrganizationType(){
        return organizationType;
    }
	//组织形式 set
    public void setOrganizationType(String organizationType){
        this.organizationType = organizationType;
    }
	 //法人代表 get
	public String getLegalRepresentative(){
        return legalRepresentative;
    }
	//法人代表 set
    public void setLegalRepresentative(String legalRepresentative){
        this.legalRepresentative = legalRepresentative;
    }
	 //用户名字 get
	public String getUserName(){
        return userName;
    }
	//用户名字 set
    public void setUserName(String userName){
        this.userName = userName;
    }
	 //身份证号码 get
	public String getIdCardNo(){
        return idCardNo;
    }
	//身份证号码 set
    public void setIdCardNo(String idCardNo){
        this.idCardNo = idCardNo;
    }
	 //电话 get
	public String getPhone(){
        return phone;
    }
	//电话 set
    public void setPhone(String phone){
        this.phone = phone;
    }
	 //邮箱 get
	public String getEmail(){
        return email;
    }
	//邮箱 set
    public void setEmail(String email){
        this.email = email;
    }
	 //地址 get
	public String getAddress(){
        return address;
    }
	//地址 set
    public void setAddress(String address){
        this.address = address;
    }
	 //状态 get
	public String getStatus(){
        return status;
    }
	//状态 set
    public void setStatus(String status){
        this.status = status;
    }
	 //创建时间 get
	public Date getCreatetime(){
        return createtime;
    }
	//创建时间 set
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }
	 //主体身份码 get
	public String getEntityIdCode(){
        return entityIdCode;
    }
	//主体身份码 set
    public void setEntityIdCode(String entityIdCode){
        this.entityIdCode = entityIdCode;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userIdCode=").append(userIdCode);
        sb.append(", customerEntityIdCode=").append(customerEntityIdCode);
        sb.append(", customerUserIdCode=").append(customerUserIdCode);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", delflag=").append(delflag);
        sb.append(", createby=").append(createby);
        sb.append(", updateby=").append(updateby);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", organizationType=").append(organizationType);
        sb.append(", legalRepresentative=").append(legalRepresentative);
        sb.append(", userName=").append(userName);
        sb.append(", idCardNo=").append(idCardNo);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", address=").append(address);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append(", entityIdCode=").append(entityIdCode);
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
        TtsScltxxcjCustomer other = (TtsScltxxcjCustomer) that;
          return 
        		(this.getUserIdCode() == null ? other.getUserIdCode() == null : this.getUserIdCode().equals(other.getUserIdCode())) &&
        		(this.getCustomerEntityIdCode() == null ? other.getCustomerEntityIdCode() == null : this.getCustomerEntityIdCode().equals(other.getCustomerEntityIdCode())) &&
        		(this.getCustomerUserIdCode() == null ? other.getCustomerUserIdCode() == null : this.getCustomerUserIdCode().equals(other.getCustomerUserIdCode())) &&
        		(this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress())) &&
        		(this.getDelflag() == null ? other.getDelflag() == null : this.getDelflag().equals(other.getDelflag())) &&
        		(this.getCreateby() == null ? other.getCreateby() == null : this.getCreateby().equals(other.getCreateby())) &&
        		(this.getUpdateby() == null ? other.getUpdateby() == null : this.getUpdateby().equals(other.getUpdateby())) &&
        		(this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime())) &&
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        		(this.getType() == null ? other.getType() == null : this.getType().equals(other.getType())) &&
        		(this.getOrganizationType() == null ? other.getOrganizationType() == null : this.getOrganizationType().equals(other.getOrganizationType())) &&
        		(this.getLegalRepresentative() == null ? other.getLegalRepresentative() == null : this.getLegalRepresentative().equals(other.getLegalRepresentative())) &&
        		(this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName())) &&
        		(this.getIdCardNo() == null ? other.getIdCardNo() == null : this.getIdCardNo().equals(other.getIdCardNo())) &&
        		(this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone())) &&
        		(this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail())) &&
        		(this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress())) &&
        		(this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
        		(this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime())) &&
        		(this.getEntityIdCode() == null ? other.getEntityIdCode() == null : this.getEntityIdCode().equals(other.getEntityIdCode())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserIdCode() == null) ? 0 : getUserIdCode().hashCode());
        result = prime * result + ((getCustomerEntityIdCode() == null) ? 0 : getCustomerEntityIdCode().hashCode());
        result = prime * result + ((getCustomerUserIdCode() == null) ? 0 : getCustomerUserIdCode().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getDelflag() == null) ? 0 : getDelflag().hashCode());
        result = prime * result + ((getCreateby() == null) ? 0 : getCreateby().hashCode());
        result = prime * result + ((getUpdateby() == null) ? 0 : getUpdateby().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getOrganizationType() == null) ? 0 : getOrganizationType().hashCode());
        result = prime * result + ((getLegalRepresentative() == null) ? 0 : getLegalRepresentative().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getIdCardNo() == null) ? 0 : getIdCardNo().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getEntityIdCode() == null) ? 0 : getEntityIdCode().hashCode());
        return result;
    }
	
}