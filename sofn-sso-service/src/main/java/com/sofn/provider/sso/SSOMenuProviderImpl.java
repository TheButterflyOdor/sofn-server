package com.sofn.provider.sso;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.sso.SSOMenuMapper;
import com.sofn.model.sso.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qjh on 2016/10/13.
 */
@DubboService(interfaceClass = SSOMenuProvider.class)
public class SSOMenuProviderImpl extends BaseProviderImpl<SysMenu> implements SSOMenuProvider {


    @Autowired
    private SSOMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> getSysMenuByUserId(String userId,String type){
        return sysMenuMapper.getSysMenuByUserId(userId,type);
    }

    @Override
    public List<SysMenu> getAllMenus() {
        return sysMenuMapper.getAllMenus();
    }
}
