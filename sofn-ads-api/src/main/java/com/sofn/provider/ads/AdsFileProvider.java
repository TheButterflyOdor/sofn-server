package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsFile;

import java.util.List;
import java.util.Map;

/**
 * 文件模型对象
 * @author moon.l
 *
 */
public interface AdsFileProvider extends BaseProvider<AdsFile> {

	public PageInfo<AdsFile> getPageInfo(Map<String, Object> map);

	public PageInfo<AdsFile> getDlReportPage(Map<String, Object> map);

	public List<Map<String,Object>> getField();

	public List<AdsFile> getIdByOrganTask(Map<String, Object> map);

	public List<Map<String,Object>> getType();

	public List<Map<String,Object>> getYear();

	public List<Map<String,Object>> getTask(Map<String, Object> map);

	public PageInfo<AdsFile> getPageInfoProblemFile(Map<String, Object> map);

	public int updateForFile(Map<String, Object> map);

	public List<AdsFile> getAdsFileByCondition(Map<String, Object> map);

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