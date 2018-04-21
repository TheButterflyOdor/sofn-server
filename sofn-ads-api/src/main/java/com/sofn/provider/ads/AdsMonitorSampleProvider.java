package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 监测抽样单模型对象
 * @author moon.l
 *
 */
public interface AdsMonitorSampleProvider extends BaseProvider<AdsMonitorSample> {
	PageInfo<AdsModelCheckObject> pageInfocodeTypeByTaskId(Map<String, Object> map);
	PageInfo<AdsCheckModelMapping> getMappingModel(Map<String, Object> map);
	PageInfo<AdsMonitorSample> getPageInfo(Map<String, Object> map);
	PageInfo<AdsMonitorSample> getSamplePageInfo(Map<String, Object> map);
	PageInfo<AdsMonitorSample> getAuditSamplePageInfo(Map<String, Object> map);
	PageInfo<AdsMonitorSample> getRecheckSamplePageInfo(Map<String, Object> map);
	PageInfo<AdsMonitorSample> getEntrustSamplePageInfo(Map<String, Object> map);
	int updateAdsMonitorSampleById(AdsMonitorSample adsMonitorSample);
	void updateAdsMonitorSample(AdsMonitorSample adsMonitorSample);
	void addAdsMonitorSample(AdsMonitorSample adsMonitorSample);
	Map<String, Object> findAdsMonitorSampleById(AdsMonitorSample adsMonitorSample);
	AdsMonitorSample findAdsMonitorSample(AdsMonitorSample adsMonitorSample);
	int selectCount(Map<String, Object> map);
	List<AdsMonitorSample> findDepartment(String organTaskId);

	//同步抽样单
	boolean getAdsMonitorSampleList(List<AdsMonitorSample> list);
	//监管机构分页查询抽样单列表
	PageInfo<AdsMonitorSample> getAdsMonitorSamplePageInfoByCode(Map<String, Object> map);
	//
	PageInfo<AdsMonitorSample> findAllAdsMonitorSample(Map<String, Object> map);

	List<AdsMonitorSample> queryByOrgTaskId(Map<String, Object> map);
	List<AdsMonitorSample> queryListByOrgTaskId(Map<String, Object> map);

	/**
	 * 通过机构名称获取对应检测机构信息
	 * @param organizationName 机构名称
	 * @return
	 */
	Map<String,Object> getTestingOrganizationInfo(String organizationName);

	//根据抽样单ID返回检测信息
	Map<String,Object> getInfoBySampleId(String sampleId);
}