package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysRoleMenuMapper;
import com.sofn.dao.sys.SysRoleMenuExpandMapper;
import com.sofn.model.generator.SysRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 4:35
 */
@DubboService(interfaceClass = SysRoleMenuProvider.class)
public class SysRoleMenuProviderImpl extends BaseProviderImpl<SysRoleMenu> implements SysRoleMenuProvider {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysRoleMenuExpandMapper sysRoleMenuExpandMapper;
    @Override
    public List<SysRoleMenu> queryByParam(Map<String, Object> param) {
        return sysRoleMenuExpandMapper.queryByParam(param);
    }
}
