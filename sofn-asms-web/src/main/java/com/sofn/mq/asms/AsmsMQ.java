package com.sofn.mq.asms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * describe:AsmsMQ 中间件
 *
 * @author xuquan
 * @date 2018/02/06
 */
@Component
public class AsmsMQ extends BaseApusicMQ {

    /**
     * The Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(AsmsMQ.class);

    /**
     * 声明一个线程实现类
     */
    @Autowired
    private AsmsMQRunnable runnable;

    /**
     * mq消息队列接收线程
     */
    public static Thread mqThread = null;


    /**
     * 发送信息 对其进行压缩、加密处理
     *
     * @param message
     * @param compressMessage  默认值 false
     * @param encryptMessage   默认值 false
     * @param encryptAlgorithm 默认值 AES (ApusicMQConstants.ENCRYPTION_AES)
     */
    @Override
    public void sendMessage(Message message, boolean compressMessage, boolean encryptMessage, String encryptAlgorithm) {
        handlerMessage(message, compressMessage, encryptMessage, encryptAlgorithm);

        try {
            getQueueSender().send(message);

            //开启消息接收线程
            if(mqThread == null){
                THREAD_RUNNABLE = true;
                mqThread = new Thread(runnable);
                mqThread.setName("AsmsMQ Thread Receive");
                mqThread.setDaemon(true);
                mqThread.start();
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}
