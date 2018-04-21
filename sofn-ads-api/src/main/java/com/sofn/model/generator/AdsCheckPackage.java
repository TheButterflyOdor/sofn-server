package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

/**
 * 监测模型映射表
 * Created by Administrator on 2017/5/9 0009.
 */
public class AdsCheckPackage extends BaseModel{
    private String id;
    private String ids;//批处理id
    private int rn;  //序号
    private Date createDate;//创建时间
    private String packageName;//检测包名称
    private String industry;//行业
    private String createBy;
    private String checkItems;
    private Date updateDate;
    private String updateBy;
    private String checkIds;
    private String checkId;
    private String packageId;
    private String tempTime;
    private String organId;

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCheckIds() {
        return checkIds;
    }

    public void setCheckIds(String checkIds) {
        this.checkIds = checkIds;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getTempTime() {
        return tempTime;
    }

    public void setTempTime(String tempTime) {
        this.tempTime = tempTime;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(String checkItems) {
        this.checkItems = checkItems;
    }
}
