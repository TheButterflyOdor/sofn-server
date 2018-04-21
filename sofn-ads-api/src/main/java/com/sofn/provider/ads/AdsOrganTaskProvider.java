package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsOrganTask;

import java.util.List;
import java.util.Map;

/**
 * 机构任务模型对象
 * @author yangran
 *
 */
public interface AdsOrganTaskProvider extends BaseProvider<AdsOrganTask> {

	public PageInfo<AdsOrganTask> getPageInfo(Map<String, Object> map);

	public PageInfo<AdsOrganTask> getPageInfoByMonitorTask(Map<String, Object> map);

	public int add(AdsOrganTask adsOrganTask);

	public int batchDelete(AdsOrganTask adsOrganTask);

	public PageInfo<AdsOrganTask> getPageInfoByMonitorTaskID(Map<String, Object> map);
	List<AdsOrganTask> findSampleOrgan(String monitorTaskId);

	public void updateCheckInfo(Map<String, Object> map);

	public void updateById(AdsOrganTask adsOrganTask);

	/**
	 * 根据监测任务id查询机构任务列表
	 * @param taskId
	 * @return
     */
	List<AdsOrganTask> getOrganListByTaskId(String taskId);
}