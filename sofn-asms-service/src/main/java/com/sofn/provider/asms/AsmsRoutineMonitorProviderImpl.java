/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.asms.AsmsRoutineLeadUnitExpandMapper;
import com.sofn.dao.asms.AsmsRoutineMonitorExpandMapper;
import com.sofn.dao.generator.AsmsRoutineLeadUnitMapper;
import com.sofn.dao.generator.AsmsRoutineMonitorMapper;
import com.sofn.model.generator.AsmsRoutineLeadUnit;
import com.sofn.model.generator.AsmsRoutineMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AsmsRoutineMonitorProvider.class)
@CacheConfig(cacheNames = "AsmsRoutineMonitor")
public class AsmsRoutineMonitorProviderImpl extends BaseProviderImpl<AsmsRoutineMonitor> implements AsmsRoutineMonitorProvider {
    @Autowired
    private AsmsRoutineMonitorExpandMapper mapper;
    @Autowired
    private AsmsRoutineMonitorMapper asmsRoutineMonitorMapper;
    @Autowired
    private AsmsRoutineLeadUnitMapper LeadUnitMapper;//自动生成关联表mapp
    @Autowired
    private AsmsRoutineLeadUnitExpandMapper LeadUnitExpandMapper;//扩展关联表mapp

    @Override
    public PageInfo<List<Map<String, Object>>> list(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = mapper.getPagesList(params);
        long count = mapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<AsmsRoutineMonitor>> listByCeateOrgId(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsRoutineMonitor> list = mapper.getPagesListByCeateOrgId(params);
        long count = mapper.getPageCountByCeateOrgId(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int addGlInfo(AsmsRoutineLeadUnit o) {
        o.setId(StringUtils.getUUIDString());//赋值64位id
        return LeadUnitMapper.insert(o);
    }

    @Override
    @Transactional
    public boolean delGlInfoByTaskId(String taskId) {
        AsmsRoutineLeadUnit t = new AsmsRoutineLeadUnit();
        t.setRoutineMonitorId(taskId);
        return LeadUnitExpandMapper.delByTaskId(t);
    }

    @Override
    public List<AsmsRoutineLeadUnit> getUnitByTaskId(String taskId) {
        AsmsRoutineLeadUnit u = new AsmsRoutineLeadUnit();
        u.setRoutineMonitorId(taskId);
        return LeadUnitExpandMapper.getUnitByTaskId(u);
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getTaskListByADS(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = mapper.getTaskListByADS(params);
        long count = mapper.getTaskCountByADS(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public AsmsRoutineMonitor addAsmsRoutineMonitor(AsmsRoutineMonitor asmsRoutineMonitor) {
        asmsRoutineMonitorMapper.insert(asmsRoutineMonitor);
        return asmsRoutineMonitor;
    }

    @Override
    public AsmsRoutineMonitor updateAsmsRoutineMonitor(AsmsRoutineMonitor asmsRoutineMonitor) {
        asmsRoutineMonitorMapper.updateByPrimaryKey(asmsRoutineMonitor);
        return asmsRoutineMonitor;
    }


}
