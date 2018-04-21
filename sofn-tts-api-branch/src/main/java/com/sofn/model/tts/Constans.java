package com.sofn.model.tts;

/**
 * Created by Administrator on 2018/2/26 0026.
 */
public class Constans {
    //查询主体交易状态
    public static final String NORMOL_STATUS = "0";
    public static final String NORMOL_MESSAGE = "无交易存在，可申请注销";
    public static final String BASE_STATUS = "1";
    public static final String BASE_MESSAGE = "您有采购产品处于未确认状态，不能申请注销";
    public static final String DOWN_STATUS = "2";
    public static final String DOWN_MESSAGE = "您有产品处于销售流通下游未确认状态，不能申请注销";
}
