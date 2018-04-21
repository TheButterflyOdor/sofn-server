package com.sofn.model.sso;

import java.util.Comparator;

/**
 * Created by Liw on 2017/6/15.
 * 对菜单按照序号进行升序排列
 */
public class SysMenuComparator implements Comparator<SysMenu> {
    public int compare(SysMenu s1, SysMenu s2) {
        try{
            return (Integer.parseInt(s1.getNumbers()) - Integer.parseInt(s2.getNumbers()));
        }catch (Exception e){
            return 0 ;
        }
    }
}
