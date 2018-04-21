package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysOrganization;

import java.util.List;

@MyBatisDao
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {
    int deleteByPrimaryKey(String id);

    int insert(SysOrganization record);

    SysOrganization selectByPrimaryKey(String id);

    List<SysOrganization> selectAll();

    int updateByPrimaryKey(SysOrganization record);
}