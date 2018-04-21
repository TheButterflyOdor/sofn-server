package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysDept;

import java.util.List;
import java.util.Map;


/**
 * Created by qjh  on 2016/10/13.
 */
public interface SysDeptProvider extends BaseProvider<SysDept> {


    int addSysDept(SysDept sysDept);

    PageInfo<SysDept> getSysDeptList(Map<String, Object> map);

    SysDept findDailyEnforceLawById(String id);

    int updateSysDept(SysDept sysDept);

    int deleteSysDept(String id);

    long getRecordsTotal(String date,String deptName);

    List<SysDept> getSysDeptLists(Page pager,String status,String deptName);

    List<SysDept> getDeptListByRegion(String regionId);
}
