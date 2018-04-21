package com.sofn.mq.asms;

import com.sofn.core.util.StringUtils;
import com.sofn.model.asms.MQInfo;
import com.sofn.service.asms.AsmsMQInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 * describe:创建 AsmsMQRunnable，用于接受信息并处理
 *
 * @author xuquan
 * @date 2018/02/26
 */
@Component
public class AsmsMQRunnable implements Runnable {

    @Autowired
    private AsmsMQ asmsMQ;

    @Autowired
    private AsmsMQInfoService service;//消息队列

    @Override
    public void run() {
        System.out.println("----------  AsmsMQRunnable run start ----------");
        MQInfo info = null;
        Object object = null;
        if (AsmsMQ.THREAD_RUNNABLE) {
            //循环接受消息队列并处理
            while (true) {
                Message msg = asmsMQ.receiveMessage();
                if (msg != null) {
                    if (msg instanceof ObjectMessage) {
                        try {
                            object = ((ObjectMessage) msg).getObject();
                            if (object instanceof MQInfo) {
                                info = (MQInfo) object;
                                info.setId(StringUtils.getUUIDString());
                                info.setReceiveFlag("N");

                                System.out.println(info);

                                service.insertMessage(info);
                            }
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.out.println("----------  AsmsMQRunnable run end ----------");
            AsmsMQ.mqThread = null;
            asmsMQ.close();
        }
    }
}
