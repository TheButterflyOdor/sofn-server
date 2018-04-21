package com.sofn.model.qry;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 销售信息
 * Created by Administrator on 2016/11/7.
 */
public class SaleInfoData implements Serializable {

    private static final long serialVersionUID = 4919442110465095178L;
    private String id;
    private String superId;
    /**
     * 产品批次码
     */
    private String productBatchCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品类别
     */
    private String productType;
    /**
     * 销售数量
     */
    private BigDecimal salesNum;
    /**
     * 销售数量单位
     */
    private String salesUnit;
    /**
     * 质检情况
     */
    private String qualityInspection;
    /**
     * 主体身份码
     */
    private String subjectIdentityCode;
    /**
     * 主体名称
     */
    private String subjectName;
    /**
     * 主体地址
     */
    private String subjectAddress;
    /**
     * 法人名称
     */
    private String legalName;
    /**
     * 联系名称
     */
    private String contactName;
    /**
     * 联系方式
     */
    private String contactInformation;

    //echarts 树图所需要属性
    private String name;//节点名称
    private String label;//节点展示名称
    private int category;//种类1流通2入市0当前查询节点3最终生产主体节点

    private List<SaleInfoData> children = new ArrayList<SaleInfoData>();//子节点

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuperId() {
        return superId;
    }

    public void setSuperId(String superId) {
        this.superId = superId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProductBatchCode() {
        return productBatchCode;
    }

    public void setProductBatchCode(String productBatchCode) {
        this.productBatchCode = productBatchCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(BigDecimal salesNum) {
        this.salesNum = salesNum;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getQualityInspection() {
        return qualityInspection;
    }

    public void setQualityInspection(String qualityInspection) {
        this.qualityInspection = qualityInspection;
    }

    public String getSubjectIdentityCode() {
        return subjectIdentityCode;
    }

    public void setSubjectIdentityCode(String subjectIdentityCode) {
        this.subjectIdentityCode = subjectIdentityCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectAddress() {
        return subjectAddress;
    }

    public void setSubjectAddress(String subjectAddress) {
        this.subjectAddress = subjectAddress;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<SaleInfoData> getChildren() {
        return children;
    }

    public void setChildren(List<SaleInfoData> children) {
        this.children = children;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleInfoData that = (SaleInfoData) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public SaleInfoData clone() {//深度克隆
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            return (SaleInfoData) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
