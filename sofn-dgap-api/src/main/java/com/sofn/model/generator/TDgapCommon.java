package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.math.BigDecimal;

/**
 * Created by weiqiang on 2016/11/3.
 */
@SuppressWarnings("serial")
public class TDgapCommon extends BaseModel {
    private String alertId;
    private String alertType;
    private String description;
    private String targetId;
    private String user;
    private BigDecimal threshold;

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }
}
