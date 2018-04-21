package com.sofn.model.asms;

import com.sofn.core.base.BaseModel;

/**
 * describe:创建 MQInfo 消息队列模型
 *
 * @author xuquan
 * @date 2018/02/28
 */
public class MQInfo extends BaseModel {
    /**
     * 生产者
     */
    private String provider;
    /**
     * 消费者
     */
    private String consumer;

    /**
     * 标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息接收标记
     */
    private String receiveFlag;


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getReceiveFlag() {
        return receiveFlag;
    }

    public void setReceiveFlag(String receiveFlag) {
        this.receiveFlag = receiveFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MQInfo{" +
                "provider='" + provider + '\'' +
                ", consumer='" + consumer + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", receiveFlag='" + receiveFlag + '\'' +
                '}';
    }
}
