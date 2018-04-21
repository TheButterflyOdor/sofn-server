package com.sofn.model.sso;

import java.util.List;

/**
 * Created by Liw on 2017/5/10.
 * 用户权限信息bean
 */
public class SysUserAuth {
    private List<SysMenu> leftitems ;
    private List<SysMenu> topitems ;
    private String Whichsystem ;
    private String managearea ;
    private UserInfoForAuth userinfo ;
    public List<SysMenu> getLeftitems() {
        return leftitems;
    }

    public void setLeftitems(List<SysMenu> leftitems) {
        this.leftitems = leftitems;
    }

    public List<SysMenu> getTopitems() {
        return topitems;
    }

    public void setTopitems(List<SysMenu> topitems) {
        this.topitems = topitems;
    }



    public String getManagearea() {
        return managearea;
    }

    public void setManagearea(String managearea) {
        this.managearea = managearea;
    }

    public String getWhichsystem() {
        return Whichsystem;
    }

    public void setWhichsystem(String whichsystem) {
        Whichsystem = whichsystem;
    }

    public UserInfoForAuth getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfoForAuth userinfo) {
        this.userinfo = userinfo;
    }
}
