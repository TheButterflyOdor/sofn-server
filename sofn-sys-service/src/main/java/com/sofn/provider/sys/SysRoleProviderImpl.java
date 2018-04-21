package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysRoleMapper;
import com.sofn.dao.generator.SysUserMapper;
import com.sofn.dao.sys.SysRoleExpandMapper;
import com.sofn.model.generator.SysRole;
import com.sofn.model.generator.SysUser;
import com.sofn.model.sys.SysRoleBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 4:35
 */
@DubboService(interfaceClass = SysRoleProvider.class)
public class SysRoleProviderImpl extends BaseProviderImpl<SysRole> implements SysRoleProvider {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleExpandMapper sysRoleExpandMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public int getRecordsTotal(String keyword,String organizationId,String userId,String status,String notRoleId) {
        return sysRoleExpandMapper.getRecordsTotal(keyword,organizationId,userId,status, notRoleId);
    }

    @Override
    public List<SysRoleBean> getSysRoleList(Page page, String keyword,String organizationId,String userId,String status,String notRoleId) {
        return sysRoleExpandMapper.getSysRoleListByPage(page, keyword,organizationId,userId,status, notRoleId);
    }

    @Override
    public List<SysRole> queryByParam(Map<String, Object> param) {
        return sysRoleExpandMapper.queryByParam(param);
    }


    @Override
    public List<SysRole> queryByUserId(String userId){
        Map<String,Object> param=new HashMap<String,Object>();
        SysUser user=sysUserMapper.selectByPrimaryKey(userId);
        if(user==null||user.getDeptId()==null)
            return null;
        param.put("deptId",user.getDeptId());
        return sysRoleExpandMapper.queryByParam(param);
    }

    @Override
    public String getUsedRoleNameByRoleIds(String[] roleId){
        return sysRoleExpandMapper.getUsedRoleNameByRoleIds(roleId);
    }

    @Override
    public SysRole getNormalRoleByAccount(String account) {
        return sysRoleExpandMapper.getNormalRoleByAccount(account);
    }

    @Override
    public List<SysRole> getASMSRoles() {
        return sysRoleExpandMapper.getASMSRoles();
    }

    @Override
    public List<SysRole> getSysRoleByRoleCodes(List<String> roleCodes) {
        return sysRoleExpandMapper.getSysRoleByRoleCodes(roleCodes);
    }
    @Override
    public List<SysRole> getOrgRoleByUserId(String status,String userId){
        return sysRoleExpandMapper.getOrgRoleByUserId(status,userId);
    }

    @Override
    public List<SysRole> getSysRolesByUserId(String userId) {
        return sysRoleExpandMapper.getSysRolesByUserId(userId);
    }

    @Override
    public List<SysRole> getIndustryormalRolesByUserId(String userId,String orgFlag){
        return sysRoleExpandMapper.getIndustryormalRolesByUserId(userId,orgFlag);
    }
}
