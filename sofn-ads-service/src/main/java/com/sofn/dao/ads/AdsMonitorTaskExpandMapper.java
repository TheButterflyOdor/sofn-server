package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsMonitorTask;

import java.util.List;
import java.util.Map;

/**
 * 监测任务 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsMonitorTaskExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取监测任务列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);

	/**
	*  获取监测任务数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  获取行业列表
     */
    List<Map<String,Object>> getHangye();

    /**
     * 更新监测任务发布状态
     * @param map
     * @return
     */
    int updateAdsMonitorTaskStatus(Map<String, Object> map);

    /**
     *  插入数据库
     *  @author TC
     */
    int insert(AdsMonitorTask adsMonitorTask);

    /**
     *  获取年度列表
     */
    List<Map<String,Object>> getYear();
    void updatePublishStatus(Map<String, Object> map);
    /**
     *  获取级别列表
     */
    List<Map<String,Object>> getLevel();

    /**
     *  获取监测任务列表
     */
    List<Map<String,Object>> getPageInfoWithParam(Map<String, Object> map);

    List<Map<String,Object>> getPageInfoWithType(Map<String, Object> map);

    List<Map<String,Object>> getPageInfoWithParamAudit(Map<String, Object> map);

    long getCountWithParamAudit(Map<String, Object> map);
    /**
     *  获取监测任务数据条数
     */
    long getCountWithParam(Map<String, Object> map);

    long getCountWithType(Map<String, Object> map);

    List<AdsMonitorTask> findAdsMonitorTaskById(Map<String, Object> map);
    AdsMonitorTask findAdsMonitorTask(String id);
    /**
     *  获取监测任务任务名称
     */
    List<String> selectTaskName();

    /**
     *  获取任务ID,任务名称,年度列表
     */
    List<Map<String,Object>> getNameByParam(AdsMonitorTask adsMonitorTask);


    /**
     *  获取监测任务
     */
    AdsMonitorTask queryByMyId(Map<String, Object> map);

    /**
     *  修改废止状态
     */
    void updateAbolish(String taskId);

    /**
     *  分页获取监测任务名称列表
     */
    List<Map<String,Object>> getPageInfoTaskName(Map<String, Object> map);

    /**
     *  获取监测任务名称数据条数
     */
    AdsMonitorTask findAuditAdsMonitorTaskById(Map<String, Object> map);
    long getCountTaskName(Map<String, Object> map);

    List<AdsMonitorTask> getAdsTaskListByCondition(Map<String, Object> map);


    List<AdsMonitorTask> getAdsTaskListByIds(String[] idsArr);




    List<Map<String,Object>> getPageAdsMonitorTask(Map<String, Object> map);
    long getPageCount(Map<String, Object> map);

    /**
     * 查询承担单位接收新任务list
     * @param map
     * @return
     */
    List<AdsMonitorTask> queryExecUnitTaskList(Map<String, Object> map);

    /**
     * 查询承担单位接收新任务count
     * @param map
     * @return
     */
    int queryExecUnitTaskCount(Map<String, Object> map);

    /*
    *  根据SupId获取任务列表
    * */
    List<AdsMonitorTask> getTasksBySupIdAndBatch(Map<String, Object> map);

    /**
     * 根据条件查询监测任务的条数
     *
     */
    public int getAdsMonitorTaskNum(Map<String, Object> map);

    /**
     * 根据批次等条件查询任务信息
     */
    public AdsMonitorTask findAdsMonitorTaskInfo(Map<String, Object> map);

    public List<AdsMonitorTask> findExpiredTask();

    /**
     *  安卓获取监测任务列表
     */
    List<Map<String,Object>> getPageInfoForAndroid(Map<String, Object> map);

    /**
     *  安卓获取监测任务数据条数
     */
    long getCountForAndroid(Map<String, Object> map);


    /**
     * 根据监管任务的id,更新监测任务的状态 ，用于废止功能
     */
    void updateAdsMonitorTaskPublishStatus(Map<String, Object> map);

    /**
     * 查询监测系统状态信息
     */
    List<Map<String, Object>> getReportStatus(Map<String, Object> map);

    /**
     * 查询受托状态信息
     */
    List<Map<String, Object>> getReportStatusByDelegate(Map<String, Object> map);

}
