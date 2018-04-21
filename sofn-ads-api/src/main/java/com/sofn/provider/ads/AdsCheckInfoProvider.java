
package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsCheckInfo;

import java.util.List;
import java.util.Map;

/**
 * 检测信息模型对象
 * @author moon.l
 *
 */
public interface AdsCheckInfoProvider extends BaseProvider<AdsCheckInfo> {

	public PageInfo<AdsCheckInfo> getPageInfo(Map<String, Object> map);

	public List<AdsCheckInfo> getInfo(Map<String, Object> map);

	public String getNextId(Map<String, Object> map);

	public String getBeforeId(Map<String, Object> map);

	public void updateResult(Map<String, Object> map);

	public PageInfo<AdsCheckInfo> getPageInfoParam(Map<String, Object> map);

	public PageInfo<AdsCheckInfo> getPageInfoWithParam(Map<String, Object> map);

	public PageInfo<AdsCheckInfo> getInfoByOrgName(Map<String, Object> map);

	public PageInfo<AdsCheckInfo> getInfoByOrgNameAll(Map<String, Object> map);


	public List<Map<String,Object>> getYear();

	public List<Map<String,Object>> getSampleName(String monitorClass);

	public List<Map<String,Object>> getTaskName(Map<String, Object> map);

	public List<Map<String,Object>> getResult();

	public List<Map<String,Object>> getSampleDeparment(Map<String, Object> map);

	public List<Map<String,Object>> getCheckOrgan(Map<String, Object> map);

	public List<Map<String,Object>> getCheckLink();

	public List<Map<String,Object>> getTestedDeparment(Map<String, Object> map);

	public PageInfo<AdsCheckInfo> getInfoProject(Map<String, Object> map);

	public AdsCheckInfo queryBySampleCode(Map<String, Object> map);

	public AdsCheckInfo queryBySampleCodeAndOrganId(Map<String, Object> map);

	public void deleteInFlag(Map<String, Object> map);

	public List<Map<String,Object>> getSampleBySampleCode(String sampleCode);

	public AdsCheckInfo queryByMyId(Map<String, Object> map);

	public AdsCheckInfo queryBySampleId(Map<String, Object> map);

	public List<Map<String,Object>> getProducingArea();

	public PageInfo<AdsCheckInfo> getPageInfoWithOrgTaskId(Map<String, Object> map);

	public void resetResult(Map<String, Object> map);

	public List<AdsCheckInfo> getCheckList(Map<String, Object> map);

	/**
	 * 根据查询条件获取AdsCheckInfo集合
	 * @param map
	 * @return
     */
	public List<AdsCheckInfo> getCheckListWithParam(Map<String, Object> map);
	public List<AdsCheckInfo> getCheckListByCheck( String[] idsArr);

	public void updateReport(Map<String, Object> map);

	public List<AdsCheckInfo> getCheckListAll(Map<String, Object> map);

	public List<AdsCheckInfo> getOrgList(Map<String, Object> map);

	public List<AdsCheckInfo> getOrgListAll(Map<String, Object> map);

	public List<AdsCheckInfo> getProjectList(Map<String, Object> map);

	public List<AdsCheckInfo> getSampleList(Map<String, Object> map);

	public int getFinish(Map<String, Object> map);

	public List<Map<String,Object>> getCheckInfo(String checkInfoId);

	public void addAdsCheckInfo(AdsCheckInfo adsCheckInfo);
}
