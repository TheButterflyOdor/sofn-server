package com.sofn.mq.asms;

/**
 * describe:常量表
 *
 * @author xuquan
 * @date 2018/02/06
 */
public interface ApusicMQConstants {


    /**
     * ApusicMQ 加密算法
     * 目前只支持{"AES", "Blowfish", "DES", "DESede"}
     */

    public static final String Cipher_Protocols = "MQ_APUSIC_Cipher_Protocols";
    /** AES */
    public static final String ENCRYPTION_AES = "AES";
    /** Blowfish */
    public static final String ENCRYPTION_BLOWFISH = "Blowfish";
    /** DES */
    public static final String ENCRYPTION_DES = "DES";
    /** DESede */
    public static final String ENCRYPTION_DESEDE = "DESede";


    /**
     * ApusicMQ 压缩算法
     * 目前只支持{"gzip"}
     */
    /** 属性 key */
    public static final String Compress_Algorithm_Key = "MQ_APUSIC_Compress_Algorithm";
    /** 属性 value */
    public static final String Compress_Algorithm_Value = "gzip";

}
