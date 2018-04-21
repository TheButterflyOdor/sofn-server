package com.sofn.service.sso;


import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.sso.SysMenu;
import com.sofn.model.sso.SysMenuComparator;
import com.sofn.provider.sso.SSOMenuProvider;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author qmy
 * @version 2016年10月14日 下午 3:05
 */

@Service
public class SSOMenuService extends BaseService<SSOMenuProvider,SysMenu> {


    @DubboReference
    public void setSSOMenuProvider(SSOMenuProvider provider){
        this.provider = provider;
    }

    /**
     * 根据用戶ID查看角色权限菜单信息
     * @param userId 用戶ID
     * @return
     */
    public List<SysMenu> getMenusByUserId(String userId,String type){
        List<SysMenu> baseMenus = provider.getSysMenuByUserId(userId,type);
        List<SysMenu> allMenus = provider.getAllMenus();
        Map<String,SysMenu> baseMenusMap = new HashMap<String,SysMenu>();
        Map<String,SysMenu> allMenusMap = new HashMap<String,SysMenu>();
        List<SysMenu> menus = new ArrayList<>();
        for(SysMenu s : baseMenus){
            if(s != null && s.getId() != null)
                baseMenusMap.put(s.getId(),s);
        }
        for(SysMenu s : allMenus){
            allMenusMap.put(s.getId(),s);
        }
        for(SysMenu sysMenu : baseMenus) {
            if(sysMenu != null && sysMenu.getId() != null)
                this.menuHand(baseMenusMap, allMenusMap, sysMenu, menus);
        }
        Collections.sort(menus, new SysMenuComparator());
        return menus ;
    }

    /**递归方法
     * 菜单完整性处理，通过子菜单寻找父菜单
     * @param baseMenusMap
     * @param allMenusMap
     * @param sysMenu
     * @param menus
     */
    public void menuHand(Map<String,SysMenu> baseMenusMap,Map<String,SysMenu> allMenusMap,SysMenu sysMenu,List<SysMenu> menus){
        menus.add(sysMenu) ;
        if(!"-1".equals(sysMenu.getParentId()) && baseMenusMap.get(sysMenu.getParentId()) == null && !this.pMenuIsExist(menus,sysMenu.getParentId())){
            SysMenu pMenu = allMenusMap.get(sysMenu.getParentId()) ;
            if(pMenu != null)
                this.menuHand(baseMenusMap,allMenusMap,pMenu,menus);
        }
    }

    /**
     * 判断父菜单是否存在
     * @param menus
     * @param pId
     * @return
     */
    public boolean pMenuIsExist(List<SysMenu> menus,String pId){
        boolean isExist = false ;
        for(SysMenu s : menus){
            if(pId.equals(s.getId())){
                isExist = true ;
                break;
            }
        }
        return isExist ;
    }
}
