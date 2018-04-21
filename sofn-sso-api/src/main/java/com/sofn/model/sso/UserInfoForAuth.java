package com.sofn.model.sso;

/**
 * Created by Liw on 2017/6/16.
 *分析决策的用户信息bean
 */
public class UserInfoForAuth {
    private String username ;
    private String name ;
    private String phone ;
    private String department ;
    private String usertype ;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
