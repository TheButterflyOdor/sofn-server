package com.sofn.provider.sso;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.sso.SysMenu;

import java.util.List;


/**
 * Created by qjh  on 2016/10/13.
 */
public interface SSOMenuProvider extends BaseProvider<SysMenu> {
    List<SysMenu> getSysMenuByUserId(String userId,String type);
    List<SysMenu> getAllMenus();
}
