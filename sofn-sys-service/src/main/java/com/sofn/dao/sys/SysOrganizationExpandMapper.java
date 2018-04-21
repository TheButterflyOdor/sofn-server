package com.sofn.dao.sys;


import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Pager;
import com.sofn.model.generator.SysOrganization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
@MyBatisDao
public interface SysOrganizationExpandMapper extends BaseExpandMapper {


    long getRecordsTotal(@Param("orgName") String orgName);

    List<SysOrganization> getSysOrgList(@Param("pager") Pager pager, @Param("orgName") String orgName);

    List<SysOrganization> selectAll();

    SysOrganization selectByName(@Param("orgName") String orgName,@Param("orgType") String orgType);
    //根据业务方存放的机构ID查询机构信息
    List<SysOrganization> getOrgByBusiness( @Param("orgId") String orgId);

    SysOrganization findSysOrganizationByOrgId(@Param("orgId") String orgId);
}
