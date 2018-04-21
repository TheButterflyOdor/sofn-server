package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsCheckTask;

import java.util.List;
import java.util.Map;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AsmsCheckTaskExpandMapper extends BaseExpandMapper {

    List<AsmsCheckTask> getTaskList(Map<String, Object> params);

    List<Map<String, Object>> getPagesList(Map<String, Object> map);

    Long getPageCount(Map<String, Object> params);

    List<AsmsCheckTask> getPagesListByOrg(Map<String, Object> map);

    Long getPageCountByOrg(Map<String, Object> params);

    List<Map<String, Object>> getParentTaskPagesList(Map<String, Object> map);

    Long getParentTaskPageCount(Map<String, Object> params);

    List<Map<String, Object>> getCheckTaskByUser(Map<String, Object> params);

    //执法监督抽查新任务接口
    List<Map<String, Object>> getNewCheckTaskByUser(Map<String, Object> params);

    //监测系统任务接口
    List<Map<String, Object>> getTaskListByADS(Map<String, Object> map);

    Long getTaskListCountByADS(Map<String, Object> params);

    //修改任务是否已提交抽样单状态
    int updateIsSample(AsmsCheckTask task);

    List<Map<String, Object>> getHistoryCheckTaskList(Map<String, Object> map);

    Long getHistoryCheckTaskCount(Map<String, Object> params);

    //根据父级任务id和创建机构id获取任务列表
    List<AsmsCheckTask>  getTaskByCreateOrgIdAndParentTaskId(Map<String, Object> params);

    //根据父级任务id获取子任务列表
    List<AsmsCheckTask>  getTaskByParentTaskId(Map<String, Object> params);

    //根据追溯码查询该产品是否正在被检测
    Long findSampleCountByProductTraceabilityCode(Map<String, Object> params);
}