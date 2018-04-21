package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 平台优化建议模型对象
 * @author moon.l
 *
 */
@SuppressWarnings("serial")
public class TtsScltxxcjProposal extends BaseModel {


	/*
	* 对应字段
	*/
    //public String id;//主键

    private String title; //标题
    private String content; //内容
    private Date time; //时间
    private String attachName; //附件名称
    private String attachPath; //附件路径
    private String entityIdcode; //主体身份码
    private String userIdcode; //主体用户码
    private String ip; //IP地址


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
    //时间 get
    public Date getTime(){
        return time;
    }
    //时间 set
    public void setTime(Date time){
        this.time = time;
    }
    //附件名称 get
    public String getAttachName(){
        return attachName;
    }
    //附件名称 set
    public void setAttachName(String attachName){
        this.attachName = attachName;
    }
    //附件路径 get
    public String getAttachPath(){
        return attachPath;
    }
    //附件路径 set
    public void setAttachPath(String attachPath){
        this.attachPath = attachPath;
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
    //IP地址 get
    public String getIp(){
        return ip;
    }
    //IP地址 set
    public void setIp(String ip){
        this.ip = ip;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", time=").append(time);
        sb.append(", attachName=").append(attachName);
        sb.append(", attachPath=").append(attachPath);
        sb.append(", entityIdcode=").append(entityIdcode);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", ip=").append(ip);
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
        TtsScltxxcjProposal other = (TtsScltxxcjProposal) that;
        return
                (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle())) &&
                        (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent())) &&
                        (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime())) &&
                        (this.getAttachName() == null ? other.getAttachName() == null : this.getAttachName().equals(other.getAttachName())) &&
                        (this.getAttachPath() == null ? other.getAttachPath() == null : this.getAttachPath().equals(other.getAttachPath())) &&
                        (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
                        (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
                        (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp())) &&
                        (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getAttachName() == null) ? 0 : getAttachName().hashCode());
        result = prime * result + ((getAttachPath() == null) ? 0 : getAttachPath().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        return result;
    }

}