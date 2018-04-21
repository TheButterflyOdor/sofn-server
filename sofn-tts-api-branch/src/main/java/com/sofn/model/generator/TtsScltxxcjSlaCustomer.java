package com.sofn.model.generator;

/**
 * 屠宰销售客户模型对象
 * @author moon.l
 *
 */
 @SuppressWarnings("serial")
public class TtsScltxxcjSlaCustomer extends TtsScltxxcjSlaSale {
	
	
	/*
	* 对应字段
	*/	
	//public String id;//主键

    private String name;//客户企业名称
    private String userName;//客户企业经办人名称
    private String phone;//联系电话
    private String address;//联系地址
    //new 20161102...
    private String xsName;//销售企业名称
	
	
	/*
	* 主键
	
	public String getPrimaryKey() {
        return id;
    }

    public void setPrimaryKey(String key) {
        this.id = key;
    }
	
	public String getId(){
        return id;
    }
	//id set
    public void setId(String id){
        this.id = id;
    }
    */
    
    
    
    /*
    *其余字段
    */
	 //客户名称 get
	public String getName(){
        return name;
    }
	//客户名称 set
    public void setName(String name){
        this.name = name;
    }
	 //用户名字 get
	public String getUserName(){
        return userName;
    }
	//用户名字 set
    public void setUserName(String userName){
        this.userName = userName;
    }
	 //电话 get
	public String getPhone(){
        return phone;
    }
	//电话 set
    public void setPhone(String phone){
        this.phone = phone;
    }
	 //地址 get
	public String getAddress(){
        return address;
    }
	//地址 set
    public void setAddress(String address){
        this.address = address;
    }
	public String getIpAddress(){
        return getIpAddress();
    }
	//IP地址 set
    public void setIpAddress(String ipAddress){
        this.setIpAddress(ipAddress);;
    }
    public String getXsName() {return xsName;}

    public void setXsName(String xsName) {this.xsName = xsName;}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", userName=").append(userName);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", ipAddress=").append(getIpAddress());
        sb.append(", xsName=").append(xsName);

        sb.append(", productXspc=").append(getProductXspc());
        sb.append(", productXspcSl=").append(getProductXspcSl());
        sb.append(", customerId=").append(getCustomerId());
        sb.append(", stockid=").append(getStockid());
        sb.append(", productName=").append(getProductName());
        sb.append(", productId=").append(getProductId());
        sb.append(", productIndustry=").append(getProductIndustry());
        sb.append(", productSort=").append(getProductSort());
        sb.append(", productScglId=").append(getProductScglId());
        sb.append(", saleAmount=").append(getSaleAmount());
        sb.append(", userIdcode=").append(getUserIdcode());
        sb.append(", customerEntityIdcode=").append(getCustomerEntityIdcode());
        sb.append(", customerUserIdcode=").append(getCustomerUserIdcode());
        sb.append(", isCirculatesEnd=").append(getIsCirculatesEnd());
        sb.append(", confirmState=").append(getConfirmState());
        sb.append(", traceCode=").append(getTraceCode());
        sb.append(", traceProof=").append(getTraceProof());
        sb.append(", saleTime=").append(getSaleTime());
        sb.append(", confirmTime=").append(getConfirmTime());
        sb.append(", productModel=").append(getProductModel());
        sb.append(", buyComUserId=").append(getBuyComUserId());
        sb.append(", saleUser=").append(getSaleUser());
        sb.append(", ipAddress=").append(getIpAddress());
        sb.append(", fromid=").append(getFromid());
        sb.append(", entityIdcode=").append(getEntityIdcode());
        sb.append(", fromTraceCode=").append(getFromTraceCode());
        sb.append(", toTraceCode=").append(getToTraceCode());
        sb.append("]");
        return sb.toString();
    }
	
	
	 @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TtsScltxxcjSlaCustomer other = (TtsScltxxcjSlaCustomer) that;
          return 
        		(this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) &&
        		(this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName())) &&
        		(this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone())) &&
        		(this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress())) &&
        		(this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress())) &&
        		(this.getXsName() == null ? other.getXsName() == null : this.getXsName().equals(other.getXsName())) &&
                (this.getProductXspc() == null ? other.getProductXspc() == null : this.getProductXspc().equals(other.getProductXspc())) &&
                (this.getProductXspcSl() == null ? other.getProductXspcSl() == null : this.getProductXspcSl().equals(other.getProductXspcSl())) &&
                (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) &&
                (this.getStockid() == null ? other.getStockid() == null : this.getStockid().equals(other.getStockid())) &&
                (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName())) &&
                (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId())) &&
                (this.getProductIndustry() == null ? other.getProductIndustry() == null : this.getProductIndustry().equals(other.getProductIndustry())) &&
                (this.getProductSort() == null ? other.getProductSort() == null : this.getProductSort().equals(other.getProductSort())) &&
                (this.getProductScglId() == null ? other.getProductScglId() == null : this.getProductScglId().equals(other.getProductScglId())) &&
                (this.getSaleAmount() == null ? other.getSaleAmount() == null : this.getSaleAmount().equals(other.getSaleAmount())) &&
                (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode())) &&
                (this.getCustomerEntityIdcode() == null ? other.getCustomerEntityIdcode() == null : this.getCustomerEntityIdcode().equals(other.getCustomerEntityIdcode())) &&
                (this.getCustomerUserIdcode() == null ? other.getCustomerUserIdcode() == null : this.getCustomerUserIdcode().equals(other.getCustomerUserIdcode())) &&
                (this.getIsCirculatesEnd() == null ? other.getIsCirculatesEnd() == null : this.getIsCirculatesEnd().equals(other.getIsCirculatesEnd())) &&
                (this.getConfirmState() == null ? other.getConfirmState() == null : this.getConfirmState().equals(other.getConfirmState())) &&
                (this.getTraceCode() == null ? other.getTraceCode() == null : this.getTraceCode().equals(other.getTraceCode())) &&
                (this.getTraceProof() == null ? other.getTraceProof() == null : this.getTraceProof().equals(other.getTraceProof())) &&
                (this.getSaleTime() == null ? other.getSaleTime() == null : this.getSaleTime().equals(other.getSaleTime())) &&
                (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime())) &&
                (this.getProductModel() == null ? other.getProductModel() == null : this.getProductModel().equals(other.getProductModel())) &&
                (this.getBuyComUserId() == null ? other.getBuyComUserId() == null : this.getBuyComUserId().equals(other.getBuyComUserId())) &&
                (this.getSaleUser() == null ? other.getSaleUser() == null : this.getSaleUser().equals(other.getSaleUser())) &&
                (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress())) &&
                (this.getFromid() == null ? other.getFromid() == null : this.getFromid().equals(other.getFromid())) &&
                (this.getEntityIdcode() == null ? other.getEntityIdcode() == null : this.getEntityIdcode().equals(other.getEntityIdcode())) &&
                (this.getFromTraceCode() == null ? other.getFromTraceCode() == null : this.getFromTraceCode().equals(other.getFromTraceCode())) &&
                (this.getToTraceCode() == null ? other.getToTraceCode() == null : this.getToTraceCode().equals(other.getToTraceCode())) &&
                (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));

       
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getXsName() == null) ? 0 : getXsName().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductXspc() == null) ? 0 : getProductXspc().hashCode());
        result = prime * result + ((getProductXspcSl() == null) ? 0 : getProductXspcSl().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getStockid() == null) ? 0 : getStockid().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductIndustry() == null) ? 0 : getProductIndustry().hashCode());
        result = prime * result + ((getProductSort() == null) ? 0 : getProductSort().hashCode());
        result = prime * result + ((getProductScglId() == null) ? 0 : getProductScglId().hashCode());
        result = prime * result + ((getSaleAmount() == null) ? 0 : getSaleAmount().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getCustomerEntityIdcode() == null) ? 0 : getCustomerEntityIdcode().hashCode());
        result = prime * result + ((getCustomerUserIdcode() == null) ? 0 : getCustomerUserIdcode().hashCode());
        result = prime * result + ((getIsCirculatesEnd() == null) ? 0 : getIsCirculatesEnd().hashCode());
        result = prime * result + ((getConfirmState() == null) ? 0 : getConfirmState().hashCode());
        result = prime * result + ((getTraceCode() == null) ? 0 : getTraceCode().hashCode());
        result = prime * result + ((getTraceProof() == null) ? 0 : getTraceProof().hashCode());
        result = prime * result + ((getSaleTime() == null) ? 0 : getSaleTime().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getProductModel() == null) ? 0 : getProductModel().hashCode());
        result = prime * result + ((getBuyComUserId() == null) ? 0 : getBuyComUserId().hashCode());
        result = prime * result + ((getSaleUser() == null) ? 0 : getSaleUser().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getFromid() == null) ? 0 : getFromid().hashCode());
        result = prime * result + ((getEntityIdcode() == null) ? 0 : getEntityIdcode().hashCode());
        result = prime * result + ((getFromTraceCode() == null) ? 0 : getFromTraceCode().hashCode());
        result = prime * result + ((getToTraceCode() == null) ? 0 : getToTraceCode().hashCode());
        return result;
    }
	
}