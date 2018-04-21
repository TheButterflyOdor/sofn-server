package com.sofn.service.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.*;
import com.sofn.model.qry.QueryParameter;
import com.sofn.provider.qry.AdsMonitorTaskProvider;
import com.sofn.provider.qry.MonitorTaskProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 10:58
 */
@Service
public class AdsMonitorTaskService extends BaseService{

    private AdsMonitorTaskProvider provider;

    public static final String routine="1";//例行
    public static final String special="2";//专项
    public static final String supervise="3";//监督

    @DubboReference
    public void setProvider(AdsMonitorTaskProvider provider){
        this.provider = provider;
    }

    public PageInfo<AdsMonitorTask> getRoutineMonitorList(int pageNum, int pageSize, AdsMonitorTask taskObj,String result, String ReleaseUnit, String qiantou, String SampleLink, String DetectionOrgan, String SampleOrgan, CurrentUser u) {

        String orgType = u.getOrgType();
        String orgLevel = u.getOrgLevel();
        String orgId = u.getOrgId();
        String regionId = u.getRegionId();

        if("2".equals(orgLevel)){
            regionId = regionId.substring(0,2);
        }else if("3".equals(orgLevel)){
            regionId = regionId.substring(0,4);
        }

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("taskName", StringUtil.isNotBlank(taskObj.getTaskName()) ? "%"+taskObj.getTaskName()+"%" : "");
        queryParams.put("year", taskObj.getYear());
        queryParams.put("cstartTime", taskObj.getCstartTime());
        queryParams.put("cendTime", taskObj.getCendTime());
        queryParams.put("startTime", taskObj.getStartTime());
        queryParams.put("endTime", taskObj.getEndTime());
        queryParams.put("monitorClass", taskObj.getMonitorClass());
        queryParams.put("industry", taskObj.getIndustry());
        queryParams.put("result", result);
        queryParams.put("ReleaseUnit", ReleaseUnit);
        queryParams.put("qiantou", qiantou);
        queryParams.put("SampleLink", SampleLink);
        queryParams.put("DetectionOrgan", DetectionOrgan);
        queryParams.put("SampleOrgan", SampleOrgan);
        queryParams.put("orgType", orgType);
        queryParams.put("orgLevel", orgLevel);
        queryParams.put("orgId", orgId);
        queryParams.put("regionId", regionId);
        PageInfo<AdsMonitorTask> pageInfo = provider.getRoutineMonitorList(queryParams);
        return pageInfo;
    }

    public List<Map<String,Object>> getIndustry() {
        return provider.getIndustry();
    }

    public List<Map<String,Object>> getReleaseUnit() {
        return provider.getReleaseUnit();
    }

    public List<Map<String,Object>> getSampleLink() {
        return provider.getSampleLink();
    }

    public List<Map<String,Object>> getDetectionOrgan() {
        return provider.getDetectionOrgan();
    }

    public List<Map<String,Object>> getSampleOrgan() {
        return provider.getSampleOrgan();
    }

    public List<AdsMonitorTask> findAdsMonitorTaskByid(String monitorTaskId,String sampleOrganId,String monitorClass) {

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId",monitorTaskId);
        queryParams.put("sampleOrganId",null);
        queryParams.put("detectionOrganId",null);
        if(monitorClass.equals(routine)){
            queryParams.put("monitorClass","例行监测");
        }else if(monitorClass.equals(special)){
            queryParams.put("monitorClass","专项监测");
        }else if(monitorClass.equals(supervise)){
            queryParams.put("monitorClass","监督抽查");
        }
        return provider.findAdsMonitorTaskByid(queryParams);
    }

    public PageInfo getPageInfoByMonitorTask(AdsOrganTask bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId",bean.getMonitorTaskId());
        queryParams.put("sampleOrgan",bean.getSampleOrgan());
        queryParams.put("detectionOrgan",bean.getDetectionOrgan());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoByMonitorTask(queryParams);
    }

    public PageInfo<List<Map<String, Object>>> getSpecialMonitorList(AsmsSpecialMonitor r, QueryParameter p) {
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        params.put("pageSize", p.getLength());
        //query
        params.put("dateBegin", StringUtil.isNotBlank(p.getDateBegin()) ? p.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(p.getDateEnd()) ? p.getDateEnd() : null);
        params.put("smName", StringUtil.isNotBlank(r.getSmName()) ? "%"+r.getSmName()+"%" : null);
        params.put("smState", StringUtil.isNotBlank(r.getSmState()) ? r.getSmState() : null);
        params.put("smYear", StringUtil.isNotBlank(r.getSmYear()) ? r.getSmYear() : null);
        PageInfo<List<Map<String, Object>>> i = provider.getSpecialMonitorList(params);
        return i;
    }

    public PageInfo<List<Map<String, Object>>> getCheckTaskList(AsmsCheckTask r, QueryParameter p) {
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        params.put("pageSize", p.getLength());
        //query
        params.put("dateBegin", StringUtil.isNotBlank(p.getDateBegin()) ? p.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(p.getDateEnd()) ? p.getDateEnd() : null);
        params.put("taskName", StringUtil.isNotBlank(r.getTaskName()) ? "%"+r.getTaskName()+"%" : null);
        params.put("state", StringUtil.isNotBlank(r.getState()) ? r.getState() : null);
        PageInfo<List<Map<String, Object>>> i = provider.getCheckTaskList(params);
        return i;
    }

    public PageInfo<List<Map<String, Object>>> getReCheckTaskList(AsmsRecheckTask r, String dateBegin, String dateEnd, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        //query
        params.put("dateBegin", dateBegin);
        params.put("dateEnd",dateEnd);
        params.put("recheckTaskName", StringUtil.isNotBlank(r.getRecheckTaskName()) ? "%"+r.getRecheckTaskName()+"%" : null);
        params.put("recheckTaskYear", StringUtil.isNotBlank(r.getRecheckTaskYear()) ? "%"+r.getRecheckTaskYear()+"%" : null);
        params.put("state", StringUtil.isNotBlank(r.getState()) ? r.getState() : null);
        PageInfo<List<Map<String, Object>>> i = provider.getReCheckTaskList(params);
        return i;
    }
}
