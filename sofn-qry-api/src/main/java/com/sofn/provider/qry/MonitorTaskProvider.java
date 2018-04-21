package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.*;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:17
 */
public interface MonitorTaskProvider extends BaseProvider{

    PageInfo<List<Map<String, Object>>> getRoutineMonitorList(Map<String, Object> params);

    PageInfo<List<Map<String, Object>>> getSpecialMonitorList(Map<String, Object> params);

    PageInfo<List<Map<String, Object>>> getCheckTaskList(Map<String, Object> params);

    PageInfo<List<Map<String, Object>>> getReCheckTaskList(Map<String, Object> params);

    AsmsCheckTask getCheckTaskById(String id);

    /**
     * 根据任务id获取检测单位列表
     * */
    List<AsmsCheckBearUnit> getUnitByTaskId(String taskId);
    /**
     * 自定义获得列表
     * */
    List<AsmsJcStandard> getJcListByTaskId(Map<String, Object> params);
    /**
     * 自定义获得列表
     * */
    List<AsmsPdStandard> getPdListByTaskId(Map<String, Object> params);

    /**
     * 根据任务key获取所属检测对象列表
     * */
    List<Map<String,Object>> getObjById(String id);

    /**
     * 根据id获取关联对象
     */
    List<Map<String,Object>> getReObjById(String id);

    AsmsRecheckTask getReCheckTaskById(String id);

    AsmsRoutineMonitor getRoutineMonitorById(String id);

    /**
     * 根据任务id获取检测单位列表
     * */
    List<AsmsRoutineLeadUnit> getRmUnitByTaskId(String taskId);

    AsmsSpecialMonitor getSpecialMonitorById(String id);

    /**
     * 根据任务id获取检测单位列表
     * */
    List<AsmsSpecialLeadUnit> getSmUnitByTaskId(String taskId);

    /**
     * 根据创建机构id和父级任务id获取任务信息
     * */
    List<AsmsCheckTask> getTaskByCreateOrgIdAndParentTaskId(String parentTaskId,String CreateOrgId);

    PageInfo<List<Map<String, Object>>> listCheckTaskReport(Map<String, Object> params);

    PageInfo<AdsInfoProject> getPageInfoWithCheckInfoId(Map<String, Object> map);

    List<Map<String,Object>> getCheckInfo(String checkInfoId);

    PageInfo<AdsCheckInfo> getPageInfoWithOrgTaskId(Map<String, Object> map);

    AdsMonitorSample findAdsMonitorSample(AdsMonitorSample adsMonitorSample);

    Map<String, Object> findAdsMonitorSampleById(AdsMonitorSample adsMonitorSample);

    //监管机构分页查询抽样单列表
    PageInfo<AdsMonitorSample> getAdsMonitorSamplePageInfoByCode(Map<String, Object> map);

    PageInfo<AdsMonitorSample> findAllAdsMonitorSample(Map<String, Object> map);

    //例行监测查询已有任务所有的发布机构信息
    public List<AsmsRoutineMonitor> findRmOrgList(Map<String,Object> params);

    //例行监测查询已有任务所有的牵头单位信息
    public List<AsmsRoutineLeadUnit> getRmLeadUnitList(Map<String,Object> params);

    //例行监测查询已有任务所有的发布机构信息
    public List<AsmsSpecialMonitor> findSpecialRmOrgList(Map<String,Object> params);

    //专项监测查询已有任务所有的牵头单位信息
    public List<AsmsSpecialLeadUnit> findSpecialRmLeadUnitList(Map<String,Object> params);

    /**
     *【监管系统--查询牵头单位上传报告分页】
     *
     * @see “参数说明：Map params：
     * supId :监管下发任务ID
     * pageSize :一页需要显示数据多少
     * pageNum :显示第几页数据
     *
     * @author TianCheng
     * */
    public PageInfo<Map<String,Object>> getPageInfoAdsFileBySupId(Map<String, Object> map);

}
