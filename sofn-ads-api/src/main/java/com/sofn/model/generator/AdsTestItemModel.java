package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

/**
 * Created by Administrator on 2017/11/27 0027.
 */
public class AdsTestItemModel extends BaseModel {

    private String id;

    private String itemName;

    private String standardCode;

    private String tempTime;

    private int rn;  //序号

    public AdsTestItemModel() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getTempTime() {
        return tempTime;
    }

    public void setTempTime(String tempTime) {
        this.tempTime = tempTime;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }
}
