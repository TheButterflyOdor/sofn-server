package com.sofn.model.tts;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public class MqInfo implements Serializable{
    private String key;//routing Key
    private String account;//账号
    private String title; //标题
    private String content;//内容
    private String sendingTime;//时间

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }
}
