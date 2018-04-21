package com.sofn.model.tts;

import com.sofn.core.base.BaseModel;

import java.util.Date;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class TtsSlaughter extends BaseModel {

    private String id;
    private String slaughterName;//屠宰物
    private String slaughterAmount;//屠宰数量
    private String productName;//产品名称
    private String productAmount;//产品数量
    private Date slaughterTime;//屠宰时间
    private String productMode;//产品规格型号
    private String productInnerKey;//内部追溯标识
    private String batchNo;//产品批次码
    private String mediCheck;//质检情况
    private String mediResukt;//质检结论
    private String harvestBaseName;//收获基地
    private String status;//当前状态
    private String productSource;//产品来源

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getSlaughterName() {
        return slaughterName;
    }

    public void setSlaughterName(String slaughterName) {
        this.slaughterName = slaughterName;
    }

    public String getSlaughterAmount() {
        return slaughterAmount;
    }

    public void setSlaughterAmount(String slaughterAmount) {
        this.slaughterAmount = slaughterAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public Date getSlaughterTime() {
        return slaughterTime;
    }

    public void setSlaughterTime(Date slaughterTime) {
        this.slaughterTime = slaughterTime;
    }

    public String getProductMode() {
        return productMode;
    }

    public void setProductMode(String productMode) {
        this.productMode = productMode;
    }

    public String getProductInnerKey() {
        return productInnerKey;
    }

    public void setProductInnerKey(String productInnerKey) {
        this.productInnerKey = productInnerKey;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getMediCheck() {
        return mediCheck;
    }

    public void setMediCheck(String mediCheck) {
        this.mediCheck = mediCheck;
    }

    public String getMediResukt() {
        return mediResukt;
    }

    public void setMediResukt(String mediResukt) {
        this.mediResukt = mediResukt;
    }

    public String getHarvestBaseName() {
        return harvestBaseName;
    }

    public void setHarvestBaseName(String harvestBaseName) {
        this.harvestBaseName = harvestBaseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductSource() {
        return productSource;
    }

    public void setProductSource(String productSource) {
        this.productSource = productSource;
    }

    @Override
    public String toString() {
        return "TtsSlaughter{" +
                "id='" + id + '\'' +
                ", slaughterName='" + slaughterName + '\'' +
                ", slaughterAmount='" + slaughterAmount + '\'' +
                ", productName='" + productName + '\'' +
                ", productAmount='" + productAmount + '\'' +
                ", slaughterTime=" + slaughterTime +
                ", productMode='" + productMode + '\'' +
                ", productInnerKey='" + productInnerKey + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", mediCheck='" + mediCheck + '\'' +
                ", mediResukt='" + mediResukt + '\'' +
                ", harvestBaseName='" + harvestBaseName + '\'' +
                ", status='" + status + '\'' +
                ", productSource='" + productSource + '\'' +
                '}';
    }
}
