package com.sofn.model.tts;

import com.sofn.model.generator.TtsScltxxcjRegiter;

import java.io.Serializable;

public class TtsScltxxcjRegiterDto extends TtsScltxxcjRegiter implements Serializable {
    private String userId;//用户ID
    private String userAccount;//用户ID
    private String userPassword;//用户ID


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
