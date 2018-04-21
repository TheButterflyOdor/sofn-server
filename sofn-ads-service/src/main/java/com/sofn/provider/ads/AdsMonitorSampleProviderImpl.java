package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.*;
import com.sofn.dao.generator.AdsMonitorSampleMapper;
import com.sofn.dao.generator.AdsOrganTaskMapper;
import com.sofn.model.generator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * 监测抽样单 providerImpl 实现
 * Created by moon.l
 */
@DubboService(interfaceClass = AdsMonitorSampleProvider.class)
public class AdsMonitorSampleProviderImpl extends BaseProviderImpl<AdsMonitorSample> implements AdsMonitorSampleProvider {

    @Autowired
    private AdsMonitorSampleExpandMapper adsMonitorSampleExpandMapper;

    @Autowired
    private AdsCheckInfoExpandMapper adsCheckInfoExpandMapper;

    @Autowired
    private AdsMonitorSampleMapper adsMonitorSampleMapper;

    @Autowired
    private AdsOrganTaskMapper adsOrganTaskMapper;

    @Autowired
    private AdsMonitorTaskExpandMapper adsMonitorTaskExpandMapper;

    @Autowired
    private AdsInfoProjectExpandMapper adsInfoProjectExpandMapper;

    public PageInfo<AdsMonitorSample> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorSampleExpandMapper.getPageInfo(map);
        long count = adsMonitorSampleExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    public PageInfo<AdsModelCheckObject> pageInfocodeTypeByTaskId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorSampleExpandMapper.pageInfocodeTypeByTaskId(map);
        long count = adsMonitorSampleExpandMapper.pageInfocodeTypeByTaskIdCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    public PageInfo<AdsCheckModelMapping> getMappingModel(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorSampleExpandMapper.getMappingModel(map);
        long count = adsMonitorSampleExpandMapper.getMappingModelCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    public PageInfo<AdsMonitorSample> getSamplePageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorSampleExpandMapper.getSamplePageInfo(map);
        long count = adsMonitorSampleExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<AdsMonitorSample> getAuditSamplePageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorSampleExpandMapper.getAuditSamplePageInfo(map);
        long count = adsMonitorSampleExpandMapper.getAuditSamplePageInfoCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<AdsMonitorSample> getRecheckSamplePageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorSampleExpandMapper.getRecheckSamplePageInfo(map);
        long count = adsMonitorSampleExpandMapper.getRecheckSamplePageInfoCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<AdsMonitorSample> getEntrustSamplePageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsMonitorSampleExpandMapper.getMySamplePageInfo(map);
        long count = adsMonitorSampleExpandMapper.getEntrustSamplePageInfoCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int updateAdsMonitorSampleById(AdsMonitorSample adsMonitorSample) {
        return adsMonitorSampleExpandMapper.updateAdsMonitorSampleById(adsMonitorSample);
    }

    @Override
    @Transactional
    public void updateAdsMonitorSample(AdsMonitorSample adsMonitorSample) {
         adsMonitorSampleExpandMapper.updateAdsMonitorSample(adsMonitorSample);
    }
    @Transactional
    public void addAdsMonitorSample(AdsMonitorSample adsMonitorSample) {
        if (null != adsMonitorSample) {
            Date date = new Date();
            adsMonitorSample.setCreateTime(date);
            adsMonitorSample.setUpdateTime(date);
            adsMonitorSampleExpandMapper.insertAdsMonitor(adsMonitorSample);
        }
    }

    @Override
    public Map<String, Object> findAdsMonitorSampleById(AdsMonitorSample adsMonitorSample) {
        return adsMonitorSampleExpandMapper.findAdsMonitorSampleById(adsMonitorSample);

    }

    @Override
    public AdsMonitorSample findAdsMonitorSample(AdsMonitorSample adsMonitorSample) {
        return adsMonitorSampleExpandMapper.findAdsMonitorSample(adsMonitorSample);
    }

    @Override
    public int selectCount(Map<String, Object> map) {
        return adsMonitorSampleExpandMapper.selectCount(map);
    }

    @Override
    public List<AdsMonitorSample> findDepartment(String organTaskId) {
        return adsMonitorSampleExpandMapper.findDepartment(organTaskId);
    }

    public boolean getAdsMonitorSampleList(List<AdsMonitorSample> list) {
        try {
            for (AdsMonitorSample adsMonitorSample : list) {
                //设置监督抽查抽样单没有数据的字段
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                adsMonitorSample.setSampleDate(ts);
                adsMonitorSample.setProductionDeparment(" ");
                adsMonitorSample.setProductionAddress(" ");
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                adsMonitorSample.setSampleId(uuid);
                adsMonitorSample.setSampleBarCode(uuid);
                adsMonitorSample.setTestedPerson(" ");
                adsMonitorSample.setSampleReport("2");
                adsMonitorSampleExpandMapper.insertAdsMonitor(adsMonitorSample);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<AdsMonitorSample> getAdsMonitorSamplePageInfoByCode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AdsMonitorSample> adsMonitorSamplePageInfoByCode = adsMonitorSampleExpandMapper.getAdsMonitorSamplePageInfoByCode(map);
        long countByCode = adsMonitorSampleExpandMapper.getCountByCode(map);
        pageInfo.setList(adsMonitorSamplePageInfoByCode);
        pageInfo.setTotal(countByCode);
        return pageInfo;
    }

    @Override
    public PageInfo<AdsMonitorSample> findAllAdsMonitorSample(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AdsMonitorSample> allAdsMonitorSample = adsMonitorSampleExpandMapper.findALLAdsMonitorSample(map);
        long adsMonitorSampleCount = adsMonitorSampleExpandMapper.findAdsMonitorSampleCount(map);
        pageInfo.setList(allAdsMonitorSample);
        pageInfo.setTotal(adsMonitorSampleCount);
        return pageInfo;
    }

    public List<AdsMonitorSample> queryByOrgTaskId(Map<String, Object> map) {
        List<AdsMonitorSample> list = adsMonitorSampleExpandMapper.queryByOrgTaskId(map);
        return list;
    }

    @Override
    public List<AdsMonitorSample> queryListByOrgTaskId(Map<String, Object> map) {
        List<AdsMonitorSample> list = adsMonitorSampleExpandMapper.queryListByOrgTaskId(map);
        return list;
    }

    @Override
    public Map<String, Object> getTestingOrganizationInfo(String organizationName) {
        return adsMonitorSampleExpandMapper.getTestingOrganizationInfo(organizationName);
    }

    @Override
    public Map<String, Object> getInfoBySampleId(String sampleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取检测信息
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleId", sampleId);
        AdsCheckInfo adsCheckInfo = adsCheckInfoExpandMapper.queryBySampleId(queryParams);
        map.put("adsCheckInfo", adsCheckInfo);
        //获取抽样单
        AdsMonitorSample adsMonitorSample = adsMonitorSampleMapper.selectByPrimaryKey(sampleId);
        //获取样品名称,样品编码
        map.put("sampleName", adsMonitorSample.getSampleName());
        map.put("sampleCode", adsMonitorSample.getSampleCode());
        //获取机构任务
        String organTaskId = adsMonitorSample.getOrganTaskId();
        AdsOrganTask adsOrganTask = adsOrganTaskMapper.selectByPrimaryKey(organTaskId);
        //获取抽样单位，检测单位
        map.put("sampleOrgan", adsOrganTask.getSampleOrgan());
        map.put("detectionOrgan", adsOrganTask.getDetectionOrgan());
        //获取任务
        String adsMonitorTaskId = adsOrganTask.getMonitorTaskId();
        AdsMonitorTask adsMonitorTask = adsMonitorTaskExpandMapper.findAdsMonitorTask(organTaskId);
        //获取任务名称
        map.put("taskName", adsMonitorTask.getTaskName());
        //获取检测列表
        Map<String, Object> queryParams2 = new HashMap<>();
        queryParams2.put("checkInfoId", adsCheckInfo.getId());
        List<AdsInfoProject> list = adsInfoProjectExpandMapper.getPageInfoWithCheckInfoId(queryParams2);
        map.put("projectList", list);
        return map;
    }

}

