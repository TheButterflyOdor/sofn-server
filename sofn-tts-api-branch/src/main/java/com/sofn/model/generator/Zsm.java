package com.sofn.model.generator;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Zsm {

    private String zsm;//追溯码
    private String enid;//主体身份码
    private String usid;//主体用户码
    private String type;//追溯类型

    public String getZsm() {
        return zsm;
    }

    public String getEnid() {
        return enid;
    }

    public String getUsid() {
        return usid;
    }

    public String getType() {
        return type;
    }

    public void setZsm(String zsm) {
        this.zsm = zsm;
    }

    public void setEnid(String enid) {
        this.enid = enid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public void setType(String type) {
        this.type = type;
    }


}
