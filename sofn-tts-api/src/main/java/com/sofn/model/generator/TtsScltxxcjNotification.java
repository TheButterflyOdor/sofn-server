package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投诉建议信息管理模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjNotification extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String beEntityIdcode; //来源主体身份码
    private String beUserIdcode; //来源主体用户码
    private String entityIdcode; //主体身份码
    private String userIdcode; //主体用户码
    private String title; //标题
    private String content; //内容
	
	
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
	 //来源主体身份码 get
	public String getBeEntityIdcode(){
        return beEntityIdcode;
    }
	//来源主体身份码 set
    public void setBeEntityIdcode(String beEntityIdcode){
        this.beEntityIdcode = beEntityIdcode;
    }
	 //来源主体用户码 get
	public String getBeUserIdcode(){
        return beUserIdcode;
    }
	//来源主体用户码 set
    public void setBeUserIdcode(String beUserIdcode){
        this.beUserIdcode = beUserIdcode;
    }
	 //主体身份码 get
	public String getEntityIdcode(){
        return entityIdcode;
    }
	//主体身份码 set
    public void setEntityIdcode(String entityIdcode){
        this.entityIdcode = entityIdcode;
    }
	 //主体用户码 get
	public String getUserIdcode(){
        return userIdcode;
    }
	//主体用户码 set
    public void setUserIdcode(String userIdcode){
        this.userIdcode = userIdcode;
    }
	 //标题 get
	public String getTitle(){
        return title;
    }
	//标题 set
    public void setTitle(String title){
        this.title = title;
    }
	 //内容 get
	public String getContent(){
        return content;
    }
	//内容 set
    public void setContent(String content){
        this.content = content;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", beEntityIdcode=").append(beEntityIdcode);
        sb.append(", beUserIdcode=").append(beUserIdcode);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
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
        TtsScltxxcjNotification other = (TtsScltxxcjNotification) that;
          return 
        		(this.getBeEntityIdcode() == null ? other.getBeEntityIdcode() == null : this.getBeEntityIdcode().equals(other.getBeEntityIdcode())) &&
        		(this.getBeUserIdcode() == null ? other.getBeUserIdcode() == null : this.getBeUserIdcode().equals(other.getBeUserIdcode())) &&
        		(this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
        		(this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
        		(this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle())) &&
        		(this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBeEntityIdcode() == null) ? 0 : getBeEntityIdcode().hashCode());
        result = prime * result + ((getBeUserIdcode() == null) ? 0 : getBeUserIdcode().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
	
}