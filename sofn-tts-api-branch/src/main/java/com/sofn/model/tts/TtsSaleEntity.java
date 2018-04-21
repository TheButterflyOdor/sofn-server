package com.sofn.model.tts;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
public class TtsSaleEntity {

    private String customerId;
    private String customerUserIdCode;
    private String entityId;
    private String productList;
    private String customerList;
    private String type;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUserIdCode() {
        return customerUserIdCode;
    }

    public void setCustomerUserIdCode(String customerUserIdCode) {
        this.customerUserIdCode = customerUserIdCode;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }
    public String getCustomerList() {
        return customerList;
    }

    public void setCustomerList(String customerList) {
        this.customerList = customerList;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
