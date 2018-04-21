package com.sofn.model.generator;

import com.sofn.core.base.BaseModel;

import java.util.Date;

/**
 * 检测对象包表映射
 * Created by Administrator on 2017/11/1 0001.
 */
public class AdsCheckObjectPackage extends BaseModel {
    private String id;
    private String ids;//批处理id
    private int rn;  //序号
    private String objectPackageName;//检测对象包名称
    private String industry;//行业
    private String checkObjects;//检测对象
    private Date createDate;//创建时间
    private String createBy;
    private Date updateDate;
    private String updateBy;
    private String productIds;
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

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
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
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getObjectPackageName() {
        return objectPackageName;
    }

    public void setObjectPackageName(String objectPackageName) {
        this.objectPackageName = objectPackageName;
    }

    public String getCheckObjects() {
        return checkObjects;
    }

    public void setCheckObjects(String checkObjects) {
        this.checkObjects = checkObjects;
    }

}
