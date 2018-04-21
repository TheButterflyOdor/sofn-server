package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户及子账户信息表模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjUser extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String account; //登录账号
    private String userIdcode; //主体用户码
    private String entityIdcode; //主体身份码
    private String isMain; //是否是主账号
    private String status; //账号审核状态
    private String name; //姓名
    private String phone; //联系方式
    private String email; //邮箱
    private String password; //密码
    private String idCard; //身份证号
	
	
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
	 //登录账号 get
	public String getAccount(){
        return account;
    }
	//登录账号 set
    public void setAccount(String account){
        this.account = account;
    }
	 //主体用户码 get
	public String getUserIdcode(){
        return userIdcode;
    }
	//主体用户码 set
    public void setUserIdcode(String userIdcode){
        this.userIdcode = userIdcode;
    }
	 //主体身份码 get
	public String getEntityIdcode(){
        return entityIdcode;
    }
	//主体身份码 set
    public void setEntityIdcode(String entityIdcode){
        this.entityIdcode = entityIdcode;
    }
	 //是否是主账号 get
	public String getIsMain(){
        return isMain;
    }
	//是否是主账号 set
    public void setIsMain(String isMain){
        this.isMain = isMain;
    }
	 //账号审核状态 get
	public String getStatus(){
        return status;
    }
	//账号审核状态 set
    public void setStatus(String status){
        this.status = status;
    }
	 //姓名 get
	public String getName(){
        return name;
    }
	//姓名 set
    public void setName(String name){
        this.name = name;
    }
	 //联系方式 get
	public String getPhone(){
        return phone;
    }
	//联系方式 set
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
	 //密码 get
	public String getPassword(){
        return password;
    }
	//密码 set
    public void setPassword(String password){
        this.password = password;
    }
	 //身份证号 get
	public String getIdCard(){
        return idCard;
    }
	//身份证号 set
    public void setIdCard(String idCard){
        this.idCard = idCard;
    }

	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", account=").append(account);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", isMain=").append(isMain);
        sb.append(", status=").append(status);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", eamil=").append(email);
        sb.append(", password=").append(password);
        sb.append(", idCard=").append(idCard);
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
        TtsScltxxcjUser other = (TtsScltxxcjUser) that;
          return 
        		(this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount())) &&
        		(this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
        		(this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
        		(this.getIsMain() == null ? other.getIsMain() == null : this.getIsMain().equals(other.getIsMain())) &&
        		(this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) &&
                (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
                (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone())) &&
                (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail())) &&
                (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword())) &&
                (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getIsMain() == null) ? 0 : getIsMain().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
        return result;
    }
	
}