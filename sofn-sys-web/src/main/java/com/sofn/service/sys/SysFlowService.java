package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysFlow;
import com.sofn.provider.sys.SysFlowProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/14.
 */
@Service
public class SysFlowService extends BaseService<SysFlowProvider,SysFlow> {

    @DubboReference
    public void setSysFlowProvider(SysFlowProvider provider){
        this.provider = provider;
    }
 

    public List<SysFlow> getSysFlowLists(Page pager, String status,String flowName){
        return provider.getSysFlowLists(pager,status,flowName);
    }

    public long getRecordsTotal(String status,String flowName){
        return provider.getRecordsTotal(status,flowName);
    }
    //添加流程
    public int addSysFlow(SysFlow sysFlow){
        return provider.addSysFlow(sysFlow);
    }
    //根据类型查找流程
    public SysFlow findSysFlowByst(String id){
        return provider.findDailyEnforceLawById(id);
    }
    //修改流程信息
    public  int updateSysFlow(SysFlow sysFlow){
        return provider.updateSysFlow(sysFlow);
    }
    //删除流程
    public int deleteSysFlow(String id){
        return provider.deleteSysFlow(id);
    }

}
