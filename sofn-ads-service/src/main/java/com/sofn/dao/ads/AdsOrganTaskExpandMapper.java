package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsOrganTask;

import java.util.List;
import java.util.Map;

/**
 * 机构任务 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsOrganTaskExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取机构任务列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);

	/**
	*  获取机构任务数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     * 获取监测任务的机构任务数据list
     * @param map
     * @return
     */
    List<Map<String,Object>> getPageInfoByMonitorTask(Map<String, Object> map);

    /**
     * 获取监测任务的机构任务数据count
     * @param map
     * @return
     */
    long getCountByMonitorTask(Map<String, Object> map);

    /**
     *  获取机构任务数据list(根据监测任务ID)
     *  @author TC
     */
    List<Map<String, Object>> getPageInfoByMonitorTaskID(Map<String, Object> map);

    /**
     * 获取机构任务数据list(根据监测任务ID)count
     * @param map
     * @return
     */
    long getCountByMonitorTaskID(Map<String, Object> map);

    /**
     * 根据监测任务id查询机构任务列表
     * @param taskId
     * @return
     */
    List<AdsOrganTask> getOrganListByTaskId(String taskId);

    /**
     * 查询机构是否已被派到一个地区进行抽检
     * @param map
     * @return
     */
    int getCountByAdsOrganCondition(Map<String, Object> map);

    List<AdsOrganTask> findSampleOrgan(String monitorTaskId);

    /**
     *  修改机构任务的检测上报状态和检测完成数量和检测上报时间
     */
    void updateCheckInfo(Map<String, Object> map);
    void updateById(AdsOrganTask adsOrganTask);
}
