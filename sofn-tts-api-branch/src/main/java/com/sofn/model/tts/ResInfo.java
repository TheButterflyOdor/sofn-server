package com.sofn.model.tts;

/**
 * Created by Administrator on 2018/2/26 0026.
 */
public class ResInfo {

    private String status;//0：可申请注销；1：未确认上游；2：下游未确认
    private String message;//返回提示信息

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
