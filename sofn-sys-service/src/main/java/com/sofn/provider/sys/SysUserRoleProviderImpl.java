package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysUserRoleMapper;
import com.sofn.dao.sys.SysUserRoleExpandMapper;
import com.sofn.model.generator.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 4:35
 */
@DubboService(interfaceClass = SysUserRoleProvider.class)
public class SysUserRoleProviderImpl extends BaseProviderImpl<SysUserRole> implements SysUserRoleProvider {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserRoleExpandMapper sysUserRoleExpandMapper;
    @Override
    public List<SysUserRole> queryByParam(Map<String, Object> param) {
        return sysUserRoleExpandMapper.queryByParam(param);
    }

    @Override
    public List<String> getRoleIdsByUser(String userId){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("userId",userId);
        param.put("delFlag","N");
        List<SysUserRole> mOldAry=sysUserRoleExpandMapper.queryByParam(param);
        if(mOldAry==null||mOldAry.size()<=0)
            return null;
        List<String> ids=new ArrayList<String>();
        for (SysUserRole ur:mOldAry){
            ids.add(ur.getRoleId());
        }
        return ids;
    }

    @Override
    public List<Map<String, Object>> getRoleByUserId(String userId) {
        return sysUserRoleExpandMapper.getRoleByUserId(userId);
    }
}
