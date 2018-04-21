package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 监测任务模型对象
 *
 * @author moon.l
 */
public interface AdsMonitorTaskProvider extends BaseProvider<AdsMonitorTask> {

    public PageInfo<AdsMonitorTask> getPageInfo(Map<String, Object> map);

    public PageInfo<Map<String, Object>> getPageAdsMonitorTask(Map<String, Object> map);

    public int publishAdsMonitorTask(AdsMonitorTask adsMonitorTask);

    public List<Map<String, Object>> getHangye();

    public List<AdsMonitorTask> findAdsMonitorTaskByid(Map<String, Object> map);

    public AdsMonitorTask findAdsMonitorTask(String id);

    public AdsMonitorTask insert(AdsMonitorTask adsMonitorTask);

    public List<Map<String, Object>> getYear();

    public List<Map<String, Object>> getLevel();


    void updatePublishStatus(Map<String, Object> map);

    public PageInfo<AdsMonitorTask> getPageInfoWithParam(Map<String, Object> map);

    public PageInfo<AdsMonitorTask> getPageInfoWithParamAudit(Map<String, Object> map);

    public PageInfo<AdsMonitorTask> getPageInfoWithType(Map<String, Object> map);

    public int updateByPrimaryKey(AdsMonitorTask adsMonitorTask);

    public List<String> selectTaskName();

    public AdsMonitorTask queryByMyId(Map<String, Object> map);

    public List<Map<String, Object>> getNameByParam(AdsMonitorTask adsMonitorTask);

    /**
     * 根据监测任务id查询监测任务信息
     *
     * @param id
     * @return
     */
    public AdsMonitorTask selectByPrimaryKey(String id);

    /**
     * 根据监测任务id查询监测任务基本信息以及机构任务列表
     *
     * @param id
     * @return
     */
    public AdsMonitorTask selectMonitorTaskAndOrganTaskByPrimaryKey(String id);

    /**
     * 根据模型id获取监测任务统计数据，检测对象、检测项目、判定标准数
     *
     * @param model_id
     * @return
     */
    public Map<String, Object> getTaskCountDataByModel(String model_id);

    /**
     * 根据条件查询监测任务数据，不分页，用作数据导出
     *
     * @param map
     * @return
     */
    public List<AdsMonitorTask> getAdsTaskListByCondition(Map<String, Object> map);


    /**
     * 根据复选id批量查询记录
     * @param idsArr
     * @return
     */
    public List<AdsMonitorTask> getAdsTaskListByIds( String[] idsArr);

    //同步监测任务和机构任务对象
    public boolean getTaskInfo(AsmsCheckTask asmsCheckTask, List<AsmsMonitorObject> list);

    //同步委托任务和抽样单
	public boolean getTaskEntrustAndSample(AlesEntrustDetection alesEntrustDetection, List<AdsMonitorSample> list);

    //设置监测任务为废止状态
    public boolean setAbolish(String taskId);

    //根据SUP_ID返回所有的抽样单
    public PageInfo<List<Map<String, Object>>> getSamplesWithSupId(Map<String, Object> params);

    //同步复检任务
    public boolean getTaskInfoForReback(AsmsRecheckTask asmsRecheckTask,List<AsmsRecheckObject> asmsRecheckObjects);

    public PageInfo<AdsMonitorTask> getPageInfoTaskName(Map<String, Object> map);

    public AdsMonitorTask findAuditAdsMonitorTaskById(Map<String, Object> map);

    /**
     * 插入监管下发的任务
     *
     * @return
     */
    public AdsMonitorTask insertSup(AdsMonitorTask adsMonitorTask);

    /**
     * 查询承担单位接收新任务列表
     *
     * @param map
     * @return
     */
    PageInfo<AdsMonitorTask> queryExecUnitTaskPageInfo(Map<String, Object> map);

    //根据条件查询监测任务的数量
    public  int getAdsMonitorTaskNum(Map<String,Object> map);

    //根据批次查询任务信息
    public  AdsMonitorTask findAdsMonitorTaskInfo(Map<String,Object> map);

    //查询过期的监测任务
    public List<AdsMonitorTask> findExpiredTask();

    /**
     *【监测任务列表】
     *
     * @see “参数说明：Map params：
     * organId : 机构id
     * monitorClass : 索要查询的参数：例行监测，专项监测，监督抽查
     * publishStatus : (0:未开始<pc 已发布> , 1：执行中 ,2：已完成<pc"已完成”和 ”已结束，未完">）
     * taskName : 任务名称
     *
     * @author TianCheng
     * */
    public PageInfo<AdsMonitorTask> getPageInfoForAndroid(Map<String, Object> map);

    /**
     * 根据监管任务的id,更新监测任务的状态 ，用于废止功能
     * @param map 包含key[supId,publishStatus,updateBy]
     */
    public void updateAdsMonitorTaskPublishStatus(Map<String, Object> map);

    /**
     * 查询监测系统状态信息
     */
    List<Map<String, Object>> getReportStatus(Map<String, Object> map);

    /**
     * 查询受托状态信息
     */
    List<Map<String, Object>> getReportStatusByDelegate(Map<String, Object> map);
}