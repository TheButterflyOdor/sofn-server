package com.sofn.service.asms;

import com.apusic.jms.message.ObjectMessageImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.MQInfo;
import com.sofn.mq.asms.AsmsMQ;
import com.sofn.provider.asms.AsmsMQInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.Date;
import java.util.List;

/**
 * describe:
 *
 * @author xuquan
 * @date 2018/02/28
 */
@Service
public class AsmsMQInfoService {

    @Autowired
    private AsmsMQ asmsMQ;


    private AsmsMQInfoProvider provider;


    @DubboReference
    public void setProvider(AsmsMQInfoProvider provider) {
        this.provider = provider;
    }

    /**
     * 发送消息队列信息
     * @param info
     */
    public void sendMessage(MQInfo info){
        try {

            info.setReceiveFlag("N");
            info.setCreateTime(new Date());
            info.setDelFlag("N");

            ObjectMessage message = new ObjectMessageImpl();
            message.setObject(info);
            asmsMQ.sendMessage(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入消息队列信息
     * @param info
     */
    public void insertMessage(MQInfo info){
        provider.insertMessage(info);
    }


    public List<MQInfo> getListForNotReceive(){
        return provider.getListForNotReceived();
    }

    public List<MQInfo> getForConsumer(String consumer){
        return provider.getForConsumer(consumer);
    }
}
