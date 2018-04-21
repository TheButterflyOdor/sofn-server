package com.sofn.service.sso;

import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysOrganization;
import com.sofn.provider.sys.SysOrganizationProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qjh on 2016/10/20.
 */
@Service
public class SysOrganizationService extends BaseService<SysOrganizationProvider,SysOrganization> {

    @DubboReference
    public void setSysOrganizationProvider(SysOrganizationProvider provider){
        this.provider = provider;
    }

    public List<SysOrganization> getSysOrganizationLists(Page pager, String orgName){
        return provider.getSysOrganizationLists(pager, orgName);
    }

    public List<SysOrganization> selectAll(){
        return provider.selectAll();
    }

    public int getRecordsTotal(String orgName){
        return provider.getRecordsTotal(orgName);
    }
    //添加
    public int addSysOrganization(SysOrganization sysOrg){
        return provider.addSysOrganization(sysOrg);
    }
    //根据ID查找机构
    public SysOrganization findSysOrganizationByst(String id){
        return provider.findDailyEnforceLawById(id);
    }
    //修改机构信息
    public  int updateSysOrganization(SysOrganization sysOrg){
        return provider.updateSysOrganization(sysOrg);
    }
    //删除机构
    public int deleteSysOrganization(String id){
        return provider.deleteSysOrganization(id);
    }

    //根据机构名字返回机构唯一信息
    public SysOrganization selectByName(String orgName) {return provider.selectByName(orgName);}

    //根据机构名字验证机构是否唯一
    public boolean VerifyByName(String orgName) {
        boolean temp = true;
        SysOrganization sysOrganization = provider.selectByName(orgName);
        if (sysOrganization!=null)
            temp = false;
        return temp;
    }
}
