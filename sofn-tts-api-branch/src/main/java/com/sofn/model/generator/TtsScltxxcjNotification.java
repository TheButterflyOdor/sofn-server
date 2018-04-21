package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 消息模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjNotification extends BaseModel {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键
	
	public String fromId; //来源ID
	public String fromName; //来源名称
	public String toId; //去向ID
	public String toName; //去向名称
	public String title; //标题
	public String content; //内容
	public String isread; //是否阅读
	
	
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
	 //来源ID get
	public String getFromId(){
        return fromId;
    }
	//来源ID set
    public void setFromId(String fromId){
        this.fromId = fromId;
    }
	 //来源名称 get
	public String getFromName(){
        return fromName;
    }
	//来源名称 set
    public void setFromName(String fromName){
        this.fromName = fromName;
    }
	 //去向ID get
	public String getToId(){
        return toId;
    }
	//去向ID set
    public void setToId(String toId){
        this.toId = toId;
    }
	 //去向名称 get
	public String getToName(){
        return toName;
    }
	//去向名称 set
    public void setToName(String toName){
        this.toName = toName;
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
	 //是否阅读 get
	public String getIsread(){
        return isread;
    }
	//是否阅读 set
    public void setIsread(String isread){
        this.isread = isread;
    }
	
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fromId=").append(fromId);
        sb.append(", fromName=").append(fromName);
        sb.append(", toId=").append(toId);
        sb.append(", toName=").append(toName);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", isread=").append(isread);
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
        		(this.getFromId() == null ? other.getFromId() == null : this.getFromId().equals(other.getFromId())) &&
        		(this.getFromName() == null ? other.getFromName() == null : this.getFromName().equals(other.getFromName())) &&
        		(this.getToId() == null ? other.getToId() == null : this.getToId().equals(other.getToId())) &&
        		(this.getToName() == null ? other.getToName() == null : this.getToName().equals(other.getToName())) &&
        		(this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle())) &&
        		(this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent())) &&
        		(this.getIsread() == null ? other.getIsread() == null : this.getIsread().equals(other.getIsread())) &&
        	    (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFromId() == null) ? 0 : getFromId().hashCode());
        result = prime * result + ((getFromName() == null) ? 0 : getFromName().hashCode());
        result = prime * result + ((getToId() == null) ? 0 : getToId().hashCode());
        result = prime * result + ((getToName() == null) ? 0 : getToName().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getIsread() == null) ? 0 : getIsread().hashCode());
        return result;
    }
	
}