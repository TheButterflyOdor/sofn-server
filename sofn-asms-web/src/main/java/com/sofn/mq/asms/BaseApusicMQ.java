package com.sofn.mq.asms;

import com.apusic.jms.client.ConnectionFactoryImpl;
import com.sofn.core.util.PropertiesUtil;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.UnknownHostException;
import java.util.Hashtable;

/**
 * describe:ApusicMQ 基类(基于Apusic中间件V8.0)
 *
 * @author xuquan
 * @date 2018/02/06
 */
public class BaseApusicMQ {

    public static final String JNDI_INITIAL_CONTEXT_FACTORY = PropertiesUtil.getString("apusic.jndi_initial_context_factory");
    public static final String HOST = PropertiesUtil.getString("apusic.host");
    public static final int PORT = PropertiesUtil.getInt("apusic.port");
    public static final String PROVIDER_URL = "iiop://" + HOST + ":" + PORT;

    public static final String QUEUE = PropertiesUtil.getString("apusic.queue");
    public static final String JMS_CONNECTION_FACTORY = PropertiesUtil.getString("apusic.jms_connection_factory");
    public static final String CONNECTION_FACTORY_NAME = PropertiesUtil.getString("apusic.connection_factory_name");

    //接受线程是否运行，默认为 false
    public static boolean THREAD_RUNNABLE = false;

    private static Context context = null;
    private ConnectionFactoryImpl connectionFactory = null;
    private QueueConnection queueConnection = null;
    private QueueSession queueSession = null;
    private Queue queue = null;
    private QueueSender queueSender = null;
    private QueueReceiver queueReceiver = null;

    public BaseApusicMQ() {
        try {
            init();
        }  catch (JMSException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 queueSender
     *
     * @return
     */
    public QueueSender getQueueSender() {
        return queueSender;
    }


    /**
     * 获取 queueReceiver
     *
     * @return
     */
    public QueueReceiver getQueueReceiver() {
        return queueReceiver;
    }

    /**
     * 对信息进行 压缩、加密处理
     *
     * @param message
     */
    public void handlerMessage(Message message) {
        handlerMessage(message, false);
    }

    public void handlerMessage(Message message, boolean compressMessage) {
        handlerMessage(message, compressMessage, false);
    }

    public void handlerMessage(Message message, boolean compressMessage, boolean encryptMessage) {
        handlerMessage(message, compressMessage, encryptMessage, null);
    }

    /**
     * 对信息进行 压缩、加密处理
     *
     * @param message
     * @param compressMessage
     * @param encryptMessage
     * @param encryptAlgorithm
     */
    public void handlerMessage(Message message, boolean compressMessage, boolean encryptMessage, String encryptAlgorithm) {
        if (compressMessage) {
            try {
                message.setStringProperty(ApusicMQConstants.Compress_Algorithm_Key, ApusicMQConstants.Compress_Algorithm_Value);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        if (encryptMessage) {
            try {
                message.setStringProperty(ApusicMQConstants.Cipher_Protocols, getEncryptAlgorithm(encryptAlgorithm));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取 约定的加密算法，默认为"AES"
     *
     * @param encryptAlgorithm
     * @return
     */
    private String getEncryptAlgorithm(String encryptAlgorithm) {
        if (encryptAlgorithm == null || encryptAlgorithm.equals(""))
            return ApusicMQConstants.ENCRYPTION_AES;

        if (!encryptAlgorithm.equals(ApusicMQConstants.ENCRYPTION_AES) && !encryptAlgorithm.equals(ApusicMQConstants.ENCRYPTION_BLOWFISH) && !encryptAlgorithm.equals(ApusicMQConstants.ENCRYPTION_DES) && !encryptAlgorithm.equals(ApusicMQConstants.ENCRYPTION_DESEDE))
            return ApusicMQConstants.ENCRYPTION_AES;

        return encryptAlgorithm;

    }


    /**
     * init resource
     *
     * @throws NamingException
     * @throws JMSException
     */
    private void init() throws JMSException, UnknownHostException {
        /*if (context == null)
            context = getInitialContext();
        connectionFactory = (ConnectionFactoryImpl) context.lookup(JMS_CONNECTION_FACTORY);*/
        connectionFactory = new ConnectionFactoryImpl(HOST, PORT);
        connectionFactory.setName(CONNECTION_FACTORY_NAME);

        // if client-authentication has been applied,you should provide a couple of principal and credential
        queueConnection = connectionFactory.createQueueConnection();

        // the first parameter indicates non-transaction session
        queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        queue = queueSession.createQueue(QUEUE);

        queueSender = queueSession.createSender(queue);
        queueReceiver = queueSession.createReceiver(queue);

        queueConnection.start();


    }


    /**
     * 获取 JNDI initial context
     *
     * @return
     * @throws NamingException
     */
    private Context getInitialContext() throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, PROVIDER_URL);
        return new InitialContext(env);
    }


    /**
     * 发送信息
     *
     * @param message
     */
    public void sendMessage(Message message) {
        sendMessage(message, false);
    }

    /**
     * 发送信息
     *
     * @param message
     * @param compressMessage
     */
    public void sendMessage(Message message, boolean compressMessage) {
        sendMessage(message, compressMessage, false);
    }


    /**
     * 发送信息
     *
     * @param message
     * @param compressMessage
     * @param encryptMessage
     */
    public void sendMessage(Message message, boolean compressMessage, boolean encryptMessage) {
        sendMessage(message, compressMessage, encryptMessage, null);
    }

    /**
     * 发送信息 对其进行压缩、加密处理
     *
     * @param message
     * @param compressMessage  默认值 false
     * @param encryptMessage   默认值 false
     * @param encryptAlgorithm 默认值 AES (ApusicMQConstants.ENCRYPTION_AES)
     */
    public void sendMessage(Message message, boolean compressMessage, boolean encryptMessage, String encryptAlgorithm) {
        handlerMessage(message, compressMessage, encryptMessage, encryptAlgorithm);

        try {
            getQueueSender().send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    /**
     * 接受信息
     */
    public Message receiveMessage() {
        return receiveMessage(10000);
    }

    /**
     * 接受信息
     *
     * @param l
     */
    public Message receiveMessage(long l) {
        try {
            Message msg = getQueueReceiver().receive(l);
            return msg;
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * release resources
     */
    public void close() {
        if (queueSender != null) {
            try {
                queueSender.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        if (queueSession != null) {
            try {
                queueSession.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        if (queueConnection != null) {
            try {
                queueConnection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        if (context != null) {
            try {
                context.close();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

}
