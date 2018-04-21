package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.AdsMonitorTaskExpandMapper;
import com.sofn.dao.qry.MonitorTaskExpandMapper;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.model.generator.AdsOrganTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:18
 */
@DubboService(interfaceClass = AdsMonitorTaskProvider.class)
public class AdsMonitorTaskProviderImpl extends BaseProviderImpl implements AdsMonitorTaskProvider {

    @Autowired
    private AdsMonitorTaskExpandMapper adsMonitorTaskExpandMapper;

    @Override
    public PageInfo<AdsMonitorTask> getRoutineMonitorList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<AdsMonitorTask> list = adsMonitorTaskExpandMapper.getRoutineMonitorList(params);
        long count = adsMonitorTaskExpandMapper.getRoutineMonitorCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<Map<String, Object>> getIndustry() {
        List<Map<String, Object>> list = adsMonitorTaskExpandMapper.getIndustry();
        return list;
    }

    public List<Map<String, Object>> getReleaseUnit() {
        List<Map<String, Object>> list = adsMonitorTaskExpandMapper.getReleaseUnit();
        return list;
    }

    public List<Map<String, Object>> getSampleLink() {
        List<Map<String, Object>> list = adsMonitorTaskExpandMapper.getSampleLink();
        return list;
    }

    public List<Map<String, Object>> getDetectionOrgan() {
        List<Map<String, Object>> list = adsMonitorTaskExpandMapper.getDetectionOrgan();
        return list;
    }

    public List<Map<String, Object>> getSampleOrgan() {
        List<Map<String, Object>> list = adsMonitorTaskExpandMapper.getSampleOrgan();
        return list;
    }

    @Override
    public List<AdsMonitorTask> findAdsMonitorTaskByid(Map<String, Object> map) {
        List<AdsMonitorTask> adsMonitorTaskById = adsMonitorTaskExpandMapper.findAdsMonitorTaskById(map);
        return adsMonitorTaskById;
    }

    @Override
    public PageInfo<AdsOrganTask> getPageInfoByMonitorTask(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsMonitorTaskExpandMapper.getPageInfoByMonitorTask(map);
        long count = adsMonitorTaskExpandMapper.getCountByMonitorTask(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getSpecialMonitorList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsMonitorTaskExpandMapper.getSpecialMonitorList(params);
        long count = adsMonitorTaskExpandMapper.getSpecialMonitorCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getCheckTaskList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorTaskExpandMapper.getCheckTaskList(params);
        long count = adsMonitorTaskExpandMapper.getCheckTaskCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getReCheckTaskList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorTaskExpandMapper.getReCheckTaskList(params);
        long count = adsMonitorTaskExpandMapper.getReCheckTaskCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
}
