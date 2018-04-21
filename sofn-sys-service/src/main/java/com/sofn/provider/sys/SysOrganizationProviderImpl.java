package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysOrganizationMapper;
import com.sofn.dao.sys.SysOrganizationExpandMapper;
import com.sofn.model.generator.SysOrganization;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * Created by qjh on 2016/10/20
 */
@DubboService(interfaceClass = SysOrganizationProvider.class)
public class SysOrganizationProviderImpl extends BaseProviderImpl<SysOrganization> implements SysOrganizationProvider {

    @Autowired
    private SysOrganizationMapper sysOrgMapper;

    @Autowired
    private SysOrganizationExpandMapper sysOrgExpandMapper;


    @Override
    public int getRecordsTotal(String regionName) {
        return (int)sysOrgExpandMapper.getRecordsTotal(regionName);
    }

    @Override
    public List<SysOrganization> selectAll() {
        return sysOrgExpandMapper.selectAll();
    }

    @Override
    public List<SysOrganization> getSysOrganizationLists(Page page, String orgName) {
        return null;
    }

    @Override
    public String saveSysOrganization(SysOrganization sysOrg) {
        String tempId = UUID.randomUUID().toString();
        sysOrg.setId(tempId);
        sysOrgMapper.insert(sysOrg);
        return sysOrg.getId();
    }

    @Override
    public SysOrganization selectByName(String orgName) {
        return sysOrgExpandMapper.selectByName(orgName,null);
    }


    /**
     * 新增部门
     * @param sysOrg
     * @return
     */
    @Override
    public int addSysOrganization(SysOrganization sysOrg) {
        return sysOrgMapper.insert(sysOrg);
    }



    /**
     * 查询部门详细
     * @param id
     * @return
     */
    @Override
    public SysOrganization findDailyEnforceLawById(String id) {
        return sysOrgMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改部门
     * @param sysOrg
     * @return
     */
    @Override
    public int updateSysOrganization(SysOrganization sysOrg) {
        return sysOrgMapper.updateByPrimaryKey(sysOrg);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public int deleteSysOrganization(String id) {
        return sysOrgMapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过orgId查询部门
     * @param
     */
    @Override
    public SysOrganization findSysOrganizationByOrgId(String orgId){
        return sysOrgExpandMapper.findSysOrganizationByOrgId(orgId);
    }
}
