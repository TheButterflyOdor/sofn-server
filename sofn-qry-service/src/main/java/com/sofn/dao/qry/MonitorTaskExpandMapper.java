package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:21
 */
@MyBatisDao
public interface MonitorTaskExpandMapper extends BaseExpandMapper{

    List<Map<String, Object>> getRoutineMonitorList(Map<String, Object> map);

    Long getRoutineMonitorCount(Map<String, Object> params);

    List<Map<String, Object>> getSpecialMonitorList(Map<String, Object> map);

    Long getSpecialMonitorCount(Map<String, Object> params);

    List<Map<String, Object>> getCheckTaskList(Map<String, Object> map);

    Long getCheckTaskCount(Map<String, Object> params);

    List<Map<String, Object>> getReCheckTaskList(Map<String, Object> map);

    Long getReCheckTaskCount(Map<String, Object> params);

    AsmsCheckTask getCheckTaskById(@Param("id")String id);
    /**
     * 根据任务id获取检测单位列表
     * */
    List<AsmsCheckBearUnit> getUnitByTaskId(AsmsCheckBearUnit t);

    List<AsmsJcStandard> getJcListByTaskId(Map<String, Object> params);

    List<AsmsPdStandard> getPdListByTaskId(Map<String, Object> params);

    List<Map<String, Object>> getObjById(String id);

    List<Map<String, Object>> getReObjById(String id);

    AsmsRecheckTask getReCheckTaskById(@Param("id")String id);

    AsmsRoutineMonitor getRoutineMonitorById(@Param("id")String id);

    /**
     * 根据任务id获取检测单位列表
     * */
    List<AsmsRoutineLeadUnit> getRmUnitByTaskId(AsmsRoutineLeadUnit t);

    AsmsSpecialMonitor getSpecialMonitorById(@Param("id")String id);

    /**
     * 根据任务id获取检测单位列表
     * */
    List<AsmsSpecialLeadUnit> getSmUnitByTaskId(AsmsSpecialLeadUnit t);

    //根据父级任务id和创建机构id获取任务列表
    List<AsmsCheckTask>  getTaskByCreateOrgIdAndParentTaskId(Map<String, Object> params);

    List<Map<String, Object>> listCheckTaskReport(Map<String, Object> map);

    Long countCheckTaskReport(Map<String, Object> params);

    /**
     *  根据检测信息ID获取检测信息_检测项目列表
     */
    List<AdsInfoProject> getPageInfoWithCheckInfoId(Map<String, Object> map);

    /**
     *根据检测信息ID获取检测信息_检测项目总数
     */
    Long getCountWithCheckInfoId(Map<String, Object> map);

    /**
     * 通过ID查询
     */
    AdsCheckInfo queryByMyId(Map<String, Object> map);

    /**
     * 通过orgTaskId查询任务名称
     */
    String queryTaskName(String orgTaskId);
    /**
     *  根据机构任务ID获取检测信息列表
     */
    List<Map<String,Object>> getPageInfoWithOrgTaskId(Map<String, Object> map);

    /**
     *  根据机构任务ID获取检测信息数据条数
     */
    long getCountWithOrgTaskId(Map<String, Object> map);

    AdsMonitorSample findAdsMonitorSample(String sampleCode);

    Map<String,Object> findAdsMonitorSampleById(AdsMonitorSample adsMonitorSample);

    List<AdsMonitorSample> getAdsMonitorSamplePageInfoByCode(Map<String, Object> map);

    long getCountByCode(Map<String, Object> map);

    List<AdsMonitorSample> findALLAdsMonitorSample(Map<String, Object> map);

    long findAdsMonitorSampleCount(Map<String, Object> map);

    //例行监测查询已有任务所有的发布机构信息
     List<AsmsRoutineMonitor> findRmOrgList(Map<String,Object> params);

    //例行监测查询已有任务所有的牵头单位信息
     List<AsmsRoutineLeadUnit> findRmLeadUnitList(Map<String,Object> params);

    //例行监测查询已有任务所有的发布机构信息
     List<AsmsSpecialMonitor> findSpecialRmOrgList(Map<String,Object> params);

    //专项监测查询已有任务所有的牵头单位信息
     List<AsmsSpecialLeadUnit> findSpecialRmLeadUnitList(Map<String,Object> params);

    List<Map<String,Object>>  getAdsFileBySupId(Map<String, Object> map);

    long getCountBySupId(Map<String, Object> map);

}
