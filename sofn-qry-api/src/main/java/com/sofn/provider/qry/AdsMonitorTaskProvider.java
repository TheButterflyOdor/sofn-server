package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.model.generator.AdsOrganTask;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:17
 */
public interface AdsMonitorTaskProvider extends BaseProvider{

    PageInfo<AdsMonitorTask> getRoutineMonitorList(Map<String, Object> params);

    public List<Map<String, Object>> getIndustry();

    public List<Map<String, Object>> getReleaseUnit();

    public List<Map<String, Object>> getSampleLink();

    public List<Map<String, Object>> getDetectionOrgan();

    public List<Map<String, Object>> getSampleOrgan();

    public List<AdsMonitorTask> findAdsMonitorTaskByid(Map<String, Object> map);

    public PageInfo<AdsOrganTask> getPageInfoByMonitorTask(Map<String, Object> map);

    PageInfo<List<Map<String, Object>>> getSpecialMonitorList(Map<String, Object> params);

    PageInfo<List<Map<String, Object>>> getCheckTaskList(Map<String, Object> params);

    PageInfo<List<Map<String, Object>>> getReCheckTaskList(Map<String, Object> params);
}
