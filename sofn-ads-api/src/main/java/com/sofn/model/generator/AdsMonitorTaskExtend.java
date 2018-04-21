package com.sofn.model.generator;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class AdsMonitorTaskExtend extends AdsMonitorTask {
   private String token;
    private int flag;
    private String ids;//批量处理id集合
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
