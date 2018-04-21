package com.sofn.model.asms;

import com.sofn.model.generator.AsmsSubjEnforceLaw;

import java.io.Serializable;

public class AsmsSubjEnforceLawDto extends AsmsSubjEnforceLaw implements Serializable {
    private String userId;//用户ID
    private String account;//用户ID
    private String password;//用户ID


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
