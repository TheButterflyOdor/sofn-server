/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.asms.AsmsSpecialLeadUnitExpandMapper;
import com.sofn.dao.asms.AsmsSpecialMonitorExpandMapper;
import com.sofn.dao.generator.AsmsSpecialLeadUnitMapper;
import com.sofn.dao.generator.AsmsSpecialMonitorMapper;
import com.sofn.model.generator.AsmsRoutineMonitor;
import com.sofn.model.generator.AsmsSpecialLeadUnit;
import com.sofn.model.generator.AsmsSpecialMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AsmsSpecialMonitorProvider.class)
@CacheConfig(cacheNames = "AsmsSpecialMonitor")
public class AsmsSpecialMonitorProviderImpl extends BaseProviderImpl<AsmsSpecialMonitor> implements AsmsSpecialMonitorProvider {
    @Autowired
    private AsmsSpecialMonitorExpandMapper mapper;
    @Autowired
    private AsmsSpecialMonitorMapper asmsSpecialMonitorMapper;
    @Autowired
    private AsmsSpecialLeadUnitMapper LeadUnitMapper;//自动生成关联表mapp
    @Autowired
    private AsmsSpecialLeadUnitExpandMapper LeadUnitExpandMapper;//扩展关联表mapp

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
    public PageInfo<List<AsmsSpecialMonitor>> listByOrg(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSpecialMonitor> list = mapper.getPagesListByCreateOrgId(params);
        long count = mapper.getPageCountCreateOrgId(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int addGlInfo(AsmsSpecialLeadUnit o) {
        o.setId(StringUtils.getUUIDString());//赋值64位id
        return LeadUnitMapper.insert(o);
    }

    @Override
    @Transactional
    public boolean delGlInfoByTaskId(String taskId) {
        AsmsSpecialLeadUnit t = new AsmsSpecialLeadUnit();
        t.setSpecialMonitorId(taskId);
        return LeadUnitExpandMapper.delByTaskId(t);
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
    public List<AsmsSpecialLeadUnit> getUnitByTaskId(String taskId) {
        AsmsSpecialLeadUnit u = new AsmsSpecialLeadUnit();
        u.setSpecialMonitorId(taskId);
        return LeadUnitExpandMapper.getUnitByTaskId(u);
    }

    @Override
    public AsmsSpecialMonitor addAsmsSpecialMonitor(AsmsSpecialMonitor asmsSpecialMonitor) {
        asmsSpecialMonitorMapper.insert(asmsSpecialMonitor);
        return asmsSpecialMonitor;
    }

    @Override
    public AsmsSpecialMonitor updateAsmsSpecialMonitor(AsmsSpecialMonitor asmsSpecialMonitor) {
        asmsSpecialMonitorMapper.updateByPrimaryKey(asmsSpecialMonitor);
        return asmsSpecialMonitor;
    }
}
