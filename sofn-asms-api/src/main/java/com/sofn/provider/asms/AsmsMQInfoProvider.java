package com.sofn.provider.asms;

import com.sofn.model.asms.MQInfo;

import java.util.List;

/**
 * describe:
 *
 * @author xuquan
 * @date 2018/02/28
 */
public interface AsmsMQInfoProvider {


    /**
     * 插入信息
     *
     * @param info
     */
    public void insertMessage(MQInfo info);


    /**
     * 获取未接收的消息队列记录
     *
     * @return
     */
    public List<MQInfo> getListForNotReceived();


    /**
     * 根据消费值查询
     *
     * @param consumer
     * @return
     */
    public List<MQInfo> getForConsumer(String consumer);


}
