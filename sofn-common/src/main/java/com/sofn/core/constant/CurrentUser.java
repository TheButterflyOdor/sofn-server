package com.sofn.core.constant;


import com.sofn.core.base.BaseModel;

/**
 * Created by: dong4j.
 * Date: 2016-09-27.
 * Time: 09:43.
 * Description:
 */
@SuppressWarnings("serial")
public class CurrentUser extends BaseModel {
    private String account;

    private String password;

    private String userName;

    private String deptId;

    private String roleId;

    private String postId;

    private String phone;

    private String email;

    private String status;

    private Organization organization;

    private String organizationId;

    private String orgName;

    private String orgType;

    private String orgLevel;

    private String regionName;

    private String typeId;

    private String levelId;

    private String regionId;

    private String orgId;
    private String userType;

    @Override
    public String toString() {
        return "CurrentUser{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", postId='" + postId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgType='" + orgType + '\'' +
                ", orgLevel='" + orgLevel + '\'' +
                ", regionName='" + regionName + '\'' +
                ", typeId='" + typeId + '\'' +
                ", levelId='" + levelId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", orgId='" + orgId + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel == null ? null : orgLevel.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId == null ? null : levelId.trim();
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getAccount() {
        return account;
    }

    public CurrentUser setAccount(String account) {
        this.account = account == null ? null : account.trim();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CurrentUser setPassword(String password) {
        this.password = password == null ? null : password.trim();
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public CurrentUser setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
        return this;
    }

    public String getDeptId() {
        return deptId;
    }

    public CurrentUser setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public CurrentUser setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
        return this;
    }

    public String getPostId() {
        return postId;
    }

    public CurrentUser setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CurrentUser setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CurrentUser setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CurrentUser setStatus(String status) {
        this.status = status == null ? null : status.trim();
        return this;
    }

    public Organization getOrganization (){
        Organization organization = new Organization();
        organization.setLevelId(this.getLevelId());
        organization.setOrgId(this.getOrgId());
        organization.setOrgLevel(this.getOrgLevel());
        organization.setOrgName(this.getOrgName());
        organization.setOrgType(this.getOrgType());
        organization.setRegionId(this.getRegionId());
        organization.setId(this.getOrganizationId());
        organization.setTypeId(this.getTypeId());
        organization.setRegionName(this.getRegionName());
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

}
