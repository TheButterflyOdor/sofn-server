package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsMonitorTask;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:21
 */
@MyBatisDao
public interface AdsMonitorTaskExpandMapper extends BaseExpandMapper{

    List<AdsMonitorTask> getRoutineMonitorList(Map<String, Object> map);

    List<Map<String,Object>> getIndustry();

    List<Map<String,Object>> getReleaseUnit();

    List<Map<String,Object>> getSampleLink();

    List<Map<String,Object>> getDetectionOrgan();

    List<Map<String,Object>> getSampleOrgan();

    List<AdsMonitorTask> findAdsMonitorTaskById(Map<String, Object> map);

    List<Map<String,Object>> getPageInfoByMonitorTask(Map<String, Object> map);

    long getCountByMonitorTask(Map<String, Object> map);

    Long getRoutineMonitorCount(Map<String, Object> params);

    List<Map<String, Object>> getSpecialMonitorList(Map<String, Object> map);

    Long getSpecialMonitorCount(Map<String, Object> params);

    List<Map<String, Object>> getCheckTaskList(Map<String, Object> map);

    Long getCheckTaskCount(Map<String, Object> params);

    List<Map<String, Object>> getReCheckTaskList(Map<String, Object> map);

    Long getReCheckTaskCount(Map<String, Object> params);
}
