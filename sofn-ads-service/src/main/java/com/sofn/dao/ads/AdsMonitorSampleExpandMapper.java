package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsMonitorSample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 监测抽样单 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsMonitorSampleExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取监测抽样单列表
    */
    List<Map<String,Object>> pageInfocodeTypeByTaskId(Map<String, Object> map);
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    List<Map<String,Object>> getMappingModel(Map<String, Object> map);
    List<Map<String,Object>> getSamplePageInfo(Map<String, Object> map);
    List<Map<String,Object>> getAuditSamplePageInfo(Map<String, Object> map);
    List<Map<String,Object>> getRecheckSamplePageInfo(Map<String, Object> map);
    List<Map<String,Object>> getEntrustSamplePageInfo(Map<String, Object> map);
    List<Map<String,Object>> getMySamplePageInfo(Map<String, Object> map);
    int updateAdsMonitorSampleById(Object object);
    void updateAdsMonitorSample(Object object);
    int selectCount(Map<String, Object> map);
	/**
	*  获取监测抽样单数据条数
	*/
    long getMappingModelCount(Map<String, Object> map);
    long getCount(Map<String, Object> map);
    long pageInfocodeTypeByTaskIdCount(Map<String, Object> map);
    void insertAdsMonitor(Object object);
    Map<String,Object> findAdsMonitorSampleById(AdsMonitorSample adsMonitorSample);
    AdsMonitorSample findAdsMonitorSample(AdsMonitorSample adsMonitorSample);
    AdsMonitorSample findAdsMonitorSampleWithCode(Map<String, Object> map);
    List<AdsMonitorSample> findDepartment(String organTaskId);

    List<AdsMonitorSample> getAdsMonitorSamplePageInfoByCode(Map<String, Object> map);
    long getCountByCode(Map<String, Object> map);
    long getAuditSamplePageInfoCount(Map<String, Object> map);
    long getRecheckSamplePageInfoCount(Map<String, Object> map);
    long getEntrustSamplePageInfoCount(Map<String, Object> map);
    List<AdsMonitorSample> findALLAdsMonitorSample(Map<String, Object> map);
    long findAdsMonitorSampleCount(Map<String, Object> map);

    List<AdsMonitorSample> queryByOrgTaskId(Map<String, Object> map);

    //根据样品名称，样品编码，任务批次分页查询抽样单
    List<Map<String,Object>> getPageInfoWithParams(Map<String, Object> map);

    long getPageInfoWithParamsCount(Map<String, Object> map);

    List<AdsMonitorSample> queryListByOrgTaskId(Map<String, Object> map);

    // 通过机构名称获取对应检测机构信息
    Map<String,Object> getTestingOrganizationInfo(@Param("organizationName") String organizationName);
}
