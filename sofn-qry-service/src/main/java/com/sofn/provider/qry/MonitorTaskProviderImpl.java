package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.MonitorTaskExpandMapper;
import com.sofn.model.generator.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:18
 */
@DubboService(interfaceClass = MonitorTaskProvider.class)
public class MonitorTaskProviderImpl extends BaseProviderImpl implements MonitorTaskProvider {

    @Autowired
    private MonitorTaskExpandMapper monitorTaskExpandMapper;

    @Override
    public PageInfo<List<Map<String, Object>>> getRoutineMonitorList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = monitorTaskExpandMapper.getRoutineMonitorList(params);
        long count = monitorTaskExpandMapper.getRoutineMonitorCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getSpecialMonitorList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = monitorTaskExpandMapper.getSpecialMonitorList(params);
        long count = monitorTaskExpandMapper.getSpecialMonitorCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getCheckTaskList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = monitorTaskExpandMapper.getCheckTaskList(params);
        long count = monitorTaskExpandMapper.getCheckTaskCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getReCheckTaskList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = monitorTaskExpandMapper.getReCheckTaskList(params);
        long count = monitorTaskExpandMapper.getReCheckTaskCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public AsmsCheckTask getCheckTaskById(String id) {
        return monitorTaskExpandMapper.getCheckTaskById(id);
    }

    @Override
    public List<AsmsCheckBearUnit> getUnitByTaskId(String taskId) {
        AsmsCheckBearUnit u = new AsmsCheckBearUnit();
        u.setSuperviseCheckTaskId(taskId);
        return monitorTaskExpandMapper.getUnitByTaskId(u);
    }

    @Override
    public List<AsmsJcStandard> getJcListByTaskId(Map<String, Object> params) {
        return monitorTaskExpandMapper.getJcListByTaskId(params);
    }

    @Override
    public List<AsmsPdStandard> getPdListByTaskId(Map<String, Object> params) {
        return monitorTaskExpandMapper.getPdListByTaskId(params);
    }

    @Override
    public List<Map<String, Object>> getObjById(String id) {
        return monitorTaskExpandMapper.getObjById(id);
    }

    @Override
    public List<Map<String, Object>> getReObjById(String id) {
        return monitorTaskExpandMapper.getReObjById(id);
    }

    @Override
    public AsmsRecheckTask getReCheckTaskById(String id) {
        return monitorTaskExpandMapper.getReCheckTaskById(id);
    }

    @Override
    public AsmsRoutineMonitor getRoutineMonitorById(String id) {
        return monitorTaskExpandMapper.getRoutineMonitorById(id);
    }

    @Override
    public List<AsmsRoutineLeadUnit> getRmUnitByTaskId(String taskId) {
        AsmsRoutineLeadUnit u = new AsmsRoutineLeadUnit();
        u.setRoutineMonitorId(taskId);
        return monitorTaskExpandMapper.getRmUnitByTaskId(u);
    }

    @Override
    public AsmsSpecialMonitor getSpecialMonitorById(String id) {
        return monitorTaskExpandMapper.getSpecialMonitorById(id);
    }

    @Override
    public List<AsmsSpecialLeadUnit> getSmUnitByTaskId(String taskId) {
        AsmsSpecialLeadUnit u = new AsmsSpecialLeadUnit();
        u.setSpecialMonitorId(taskId);
        return monitorTaskExpandMapper.getSmUnitByTaskId(u);
    }

    @Override
    public List<AsmsCheckTask> getTaskByCreateOrgIdAndParentTaskId(String parentTaskId, String CreateOrgId) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentTaskId",parentTaskId);
        params.put("CreateOrgId",CreateOrgId);
        return monitorTaskExpandMapper.getTaskByCreateOrgIdAndParentTaskId(params);
    }

    @Override
    public PageInfo<List<Map<String, Object>>> listCheckTaskReport(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = monitorTaskExpandMapper.listCheckTaskReport(params);
        long count = monitorTaskExpandMapper.countCheckTaskReport(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    public PageInfo<AdsInfoProject> getPageInfoWithCheckInfoId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AdsInfoProject> list = monitorTaskExpandMapper.getPageInfoWithCheckInfoId(map);
        long count = monitorTaskExpandMapper.getCountWithCheckInfoId(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<Map<String,Object>> getCheckInfo(String checkInfoId) {

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id",checkInfoId);
        AdsCheckInfo adsCheckInfo = monitorTaskExpandMapper.queryByMyId(queryParams);

        Map<String,Object> map1 = new HashMap<>();

        map1.put("adsCheckInfo",adsCheckInfo);

        list.add(map1);

        //通过机构任务ID查询任务名称
        String TaskName = monitorTaskExpandMapper.queryTaskName(adsCheckInfo.getOrganTaskId());

        Map<String,Object> map2 = new HashMap<>();

        map2.put("TaskName",TaskName);

        list.add(map2);

        return list;
    }

    public PageInfo<AdsCheckInfo> getPageInfoWithOrgTaskId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = monitorTaskExpandMapper.getPageInfoWithOrgTaskId(map);
        long count = monitorTaskExpandMapper.getCountWithOrgTaskId(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public AdsMonitorSample findAdsMonitorSample(AdsMonitorSample adsMonitorSample) {
        return monitorTaskExpandMapper.findAdsMonitorSample(adsMonitorSample.getSampleCode());
    }

    @Override
    public Map<String, Object> findAdsMonitorSampleById(AdsMonitorSample adsMonitorSample) {
        return monitorTaskExpandMapper.findAdsMonitorSampleById(adsMonitorSample);
    }

    @Override
    public PageInfo<AdsMonitorSample> getAdsMonitorSamplePageInfoByCode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AdsMonitorSample> adsMonitorSamplePageInfoByCode = monitorTaskExpandMapper.getAdsMonitorSamplePageInfoByCode(map);
        long countByCode = monitorTaskExpandMapper.getCountByCode(map);
        pageInfo.setList(adsMonitorSamplePageInfoByCode);
        pageInfo.setTotal(countByCode);
        return pageInfo;
    }

    @Override
    public PageInfo<AdsMonitorSample> findAllAdsMonitorSample(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AdsMonitorSample> allAdsMonitorSample = monitorTaskExpandMapper.findALLAdsMonitorSample(map);
        long adsMonitorSampleCount = monitorTaskExpandMapper.findAdsMonitorSampleCount(map);
        pageInfo.setList(allAdsMonitorSample);
        pageInfo.setTotal(adsMonitorSampleCount);
        return pageInfo;
    }

    @Override
    public List<AsmsRoutineMonitor> findRmOrgList(Map<String,Object> params) {
        return monitorTaskExpandMapper.findRmOrgList(params);
    }

    @Override
    public List<AsmsRoutineLeadUnit> getRmLeadUnitList(Map<String,Object> params) {
        return monitorTaskExpandMapper.findRmLeadUnitList(params);
    }

    @Override
    public List<AsmsSpecialMonitor> findSpecialRmOrgList(Map<String,Object> params) {
        return monitorTaskExpandMapper.findSpecialRmOrgList(params);
    }

    @Override
    public List<AsmsSpecialLeadUnit> findSpecialRmLeadUnitList(Map<String,Object> params) {
        return monitorTaskExpandMapper.findSpecialRmLeadUnitList(params);
    }

    @Override
    public PageInfo<Map<String,Object>> getPageInfoAdsFileBySupId(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = monitorTaskExpandMapper.getAdsFileBySupId(map);
        long count = monitorTaskExpandMapper.getCountBySupId(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

}
