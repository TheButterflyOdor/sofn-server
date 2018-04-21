package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysFlowMapper;
import com.sofn.dao.sys.SysFlowExpandMapper;
import com.sofn.model.generator.SysFlow;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/11/3.
 */
@DubboService(interfaceClass = SysFlowProvider.class)
public class SysFlowProviderImpl extends BaseProviderImpl<SysFlow> implements SysFlowProvider{

    @Autowired
    private SysFlowMapper sysFlowMapper;

    @Autowired
    private SysFlowExpandMapper sysFlowExpandMapper;


    @Override
    public long getRecordsTotal(String status,String flowName) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("status", status);
        queryParams.put("flowName", flowName);
        return sysFlowExpandMapper.getAllCount(queryParams);
    }

    @Override
    public List<SysFlow> getSysFlowLists(Page pager, String status, String flowName) {
        return sysFlowExpandMapper.getSysFlowLists(pager, status,flowName);
    }

    
    /**
     * 新增流程
     * @param sysFlow
     * @return
     */
    @Override
    public int addSysFlow(SysFlow sysFlow) {
        return sysFlowMapper.insert(sysFlow);
    }


    /**
     * 查询流程详细
     * @param id
     * @return
     */
    @Override
    public SysFlow findDailyEnforceLawById(String id) {
        return sysFlowMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改流程
     * @param sysFlow
     * @return
     */
    @Override
    public int updateSysFlow(SysFlow sysFlow) {
        return sysFlowMapper.updateByPrimaryKey(sysFlow);
    }

    /**
     * 删除流程
     * @param id
     * @return
     */
    @Override
    public int deleteSysFlow(String id) {
        return sysFlowMapper.deleteByPrimaryKey(id);
    }

}
