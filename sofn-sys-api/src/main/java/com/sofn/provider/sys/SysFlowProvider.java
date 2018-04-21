package com.sofn.provider.sys;


import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.SysFlow;
import com.sofn.core.persistence.Page;
import java.util.List;

/**
 * Created by qjh  on 2016/10/13.
 */
public interface SysFlowProvider extends BaseProvider<SysFlow> {


    int addSysFlow(SysFlow sysFlow);

    SysFlow findDailyEnforceLawById(String id);

    int updateSysFlow(SysFlow sysFlow);

    int deleteSysFlow(String id);

    long getRecordsTotal(String date, String deptName);

    List<SysFlow> getSysFlowLists(Page pager, String status, String deptName);

}
