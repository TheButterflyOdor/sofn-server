package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysOrganization;

import java.util.List;


/**
 * Created by qjh  on 2016/10/20.
 */
public interface SysOrganizationProvider extends BaseProvider<SysOrganization> {

    /**
     * 添加
     * @param sysOrg
     * @return
     */
    int addSysOrganization(SysOrganization sysOrg);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SysOrganization findDailyEnforceLawById(String id);

    /**
     * 修改
     * @param sysOrg
     * @return
     */
    int updateSysOrganization(SysOrganization sysOrg);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteSysOrganization(String id);

    /**
     * 根据区域名查询
     * @param regionName
     * @return
     */
    int getRecordsTotal(String regionName);

    /**
     * 查询所有机构
     * @return
     */
    List<SysOrganization> selectAll();

    /**
     * 分页查询
     * @param pager
     * @param orgName
     * @return
     */
    List<SysOrganization> getSysOrganizationLists(Page pager, String orgName);

    /**
     * 返回id
     * @param sysOrg
     * @return
     */
    String saveSysOrganization(SysOrganization sysOrg);

    /**
     * 根据name查找唯一机构信息
     * @param orgName
     * @return
     */
    SysOrganization selectByName(String orgName);
    /**
     * 根据orgId查询机构
     * @param orgId
     * @return
     */
    SysOrganization findSysOrganizationByOrgId(String orgId);
}
