package com.sofn.service.ales;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.*;
import com.sofn.provider.ads.AdsCheckInfoProvider;
import com.sofn.provider.ads.AdsInfoProjectProvider;
import com.sofn.provider.ads.AdsMonitorTaskProvider;
import com.sofn.provider.ales.AlesEntrustDetectionProvider;
import com.sofn.provider.ales.AlesTaskSampleProvider;
import com.sofn.provider.ales.AlesWtTaskMonitorProvider;
import com.sofn.provider.ales.AlesWtTaskSampleProvider;
import com.sofn.provider.asms.AsmsCheckTaskReportProvider;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import com.sofn.provider.sys.AdsModelCheckStandardProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.web.ales.AlesEnum;
import jodd.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@Service
public class AlesEntrustDetectionService extends BaseService<AlesEntrustDetectionProvider, AlesEntrustDetection> {

    private SysUserProvider sysUserProvider;

    private AsmsSubjDetectionProvider asmsSubjDetectionProvider;

    private AlesTaskSampleProvider alesTaskSampleProvider;

    private AlesWtTaskMonitorProvider aesWtTaskMonitorProvider;

    private AdsMonitorTaskProvider adsMonitorTaskProvider;//检测系统

    private AlesWtTaskSampleProvider alesWtTaskSampleProvider;//委托监测任务抽样单provider

    private AsmsCheckTaskReportProvider asmsCheckTaskReportProvider;//监管系统报告

    private AdsCheckInfoProvider adsCheckInfoProvider;//检测系统-检测信息

    private AdsInfoProjectProvider adsInfoProjectProvider;//检测系统

    @DubboReference
    public void setProvider(AlesTaskSampleProvider alesTaskSampleProvider) {
        this.alesTaskSampleProvider = alesTaskSampleProvider;
    }

    @DubboReference
    public void setProvider(AlesWtTaskMonitorProvider aesWtTaskMonitorProvider) {
        this.aesWtTaskMonitorProvider = aesWtTaskMonitorProvider;
    }

    @DubboReference
    public void setProvider(AlesEntrustDetectionProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(AdsInfoProjectProvider adsInfoProjectProvider) {
        this.adsInfoProjectProvider = adsInfoProjectProvider;
    }

    @DubboReference
    public void setProvider(AdsCheckInfoProvider adsCheckInfoProvider) {
        this.adsCheckInfoProvider = adsCheckInfoProvider;
    }

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setProvider(AsmsCheckTaskReportProvider asmsCheckTaskReportProvider) {
        this.asmsCheckTaskReportProvider = asmsCheckTaskReportProvider;
    }

    @DubboReference
    public void setProvider(AsmsSubjDetectionProvider asmsSubjDetectionProvider) {
        this.asmsSubjDetectionProvider = asmsSubjDetectionProvider;
    }

    @DubboReference
    public void setProvider(AlesWtTaskSampleProvider alesWtTaskSampleProvider) {
        this.alesWtTaskSampleProvider = alesWtTaskSampleProvider;
    }

    private AdsModelCheckStandardProvider adsModelCheckStandardProvider;

    @DubboReference
    public void setAdsModelCheckStandardProvider(AdsModelCheckStandardProvider provider) {
        this.adsModelCheckStandardProvider = provider;
    }

    @DubboReference
    public void setProvider(AdsMonitorTaskProvider adsMonitorTaskProvider) {
        this.adsMonitorTaskProvider = adsMonitorTaskProvider;
    }

    /**
     * 添加委托检测任务
     */
    public AlesEntrustDetection addTask(AlesEntrustDetection alesEntrustDetection, String token) {
        alesEntrustDetection.setCreateTime(new Date());
        alesEntrustDetection.setState(AlesEnum.UN_PUBLISHED.getCode());//待发布
        String id = super.add(alesEntrustDetection).getId();//主表信息
        alesEntrustDetection.setId(id);
        return alesEntrustDetection;
    }

    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

    /**
     * 修改检测任务
     */
    public void updateTask(AlesEntrustDetection alesEntrustDetection) {
        AlesEntrustDetection detection = provider.queryById(alesEntrustDetection.getId());
        alesEntrustDetection.setCreateTime(detection.getCreateTime());
        alesEntrustDetection.setCreateBy(detection.getCreateBy());
        alesEntrustDetection.setEnable(detection.getEnable());
        alesEntrustDetection.setState(detection.getState());
        alesEntrustDetection.setDelFlag(detection.getDelFlag());
        this.update(alesEntrustDetection);
    }

    public List<Map<String, Object>> getObjById(String id) {
        return provider.getObjById(id);
    }

    /**
     * 获取委托检测任务列表
     */
    public PageInfo<List<Map<String, Object>>> list(AlesEntrustDetection alesEntrustDetection, String beginTime, String endTime, int pageNum, int pageSize, String token) {
        Map<String, Object> params = new HashMap<>();
        String createOrgId = sysUserProvider.findSysUserOrganization(token).getOrgId();
        logger.info("createOrgId=:"+createOrgId);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("taskBeginTime", StringUtil.isNotBlank(beginTime) ? beginTime : null);
        params.put("taskEndTime", StringUtil.isNotBlank(endTime) ? endTime : null);
        params.put("taskName", StringUtil.isNotBlank(alesEntrustDetection.getTaskName()) ? "%" + alesEntrustDetection.getTaskName() + "%" : null);
        params.put("taskyear", StringUtil.isNotBlank(alesEntrustDetection.getTaskyear()) ? alesEntrustDetection.getTaskyear() : null);
        params.put("state", StringUtil.isNotBlank(alesEntrustDetection.getState()) ? alesEntrustDetection.getState() : null);
        params.put("createOrgId", StringUtil.isNotBlank(createOrgId) ? createOrgId : null);
        //provider.
        return provider.list(params);
    }

    /**
     * 同步委托检测任务到检测系统
     */
    public String updataState(String id) {
        return null;
    }

    /**
     * 获取检测标准列表
     */
    public PageInfo getPageInfo(AdsModelCheckStandard bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return adsModelCheckStandardProvider.getPageInfo(queryParams);
    }

    /**
     * 报告列表
     */
    public PageInfo<List<Map<String, Object>>> getReportsList(AsmsCheckTaskReport asmsCheckTaskReport, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("taskId", StringUtil.isNotBlank(asmsCheckTaskReport.getMonitorTaskId()) ? asmsCheckTaskReport.getMonitorTaskId() : "-1");
        params.put("monitorTask", StringUtil.isNotBlank(asmsCheckTaskReport.getMonitorTask()) ? "%" + asmsCheckTaskReport.getMonitorTask() + "%" : null);
        params.put("years", StringUtil.isNotBlank(asmsCheckTaskReport.getYears()) ? asmsCheckTaskReport.getYears() : null);//年份
        return asmsCheckTaskReportProvider.list(params);
    }

    /**
     * 根据报告获取监测信息列表
     */
    public PageInfo<AdsCheckInfo> getJclistByReport(AsmsCheckTaskReport asmsCheckTaskReport, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("organTaskId", asmsCheckTaskReport.getOrganTaskId());//机构任务id（对应监测对象id）
        return adsCheckInfoProvider.getPageInfoWithOrgTaskId(params);
    }

    /**
     * 根据监测信息id获取检测详情列表
     */
    public List<AdsInfoProject> getReportByJcInfo(AdsInfoProject adsInfoProject) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkInfoId", adsInfoProject.getCheckInfoId());//检测信息id
        return adsInfoProjectProvider.getPageInfoWithCheckInfoId(params);//检测详情列表
    }

    /**
     * 根据检测信息id获取检测信息基本信息
     */
    public List<Map<String, Object>> getJbInfoByJcId(AdsInfoProject adsInfoProject) {
        return adsCheckInfoProvider.getCheckInfo(adsInfoProject.getCheckInfoId());
    }

    /**
     * 发布任务并同步信息至检测系统
     */
    public Map<String, Object> relTaskAndSendDataToAdsSys(CurrentUser currentUser,String jsonString) {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        Map<String, Object> integritySuccess = dataIntegrity(jsonArray);
        if (!(boolean) integritySuccess.get("success")) {
            return integritySuccess;
        }
        Map<String, Object> synSuccessFull = syncSamplesToAdsSystem(currentUser,jsonArray);
        return synSuccessFull;
    }

    /**
     * 数据完整验证
     */
    private Map<String, Object> dataIntegrity(JSONArray taskIdJsonArray) {
        Map<String, Object> integritySuccess = new HashMap<>();
        for (Object taskId : taskIdJsonArray) {
            if (StringUtil.isNotBlank(String.valueOf(taskId))) {
                AlesEntrustDetection task = provider.queryById(String.valueOf(taskId));
                List<AlesWtTaskMonitor> alesWtTaskMonitors = getAlesWtTaskMonitors(String.valueOf(taskId));
                if (alesWtTaskMonitors == null || alesWtTaskMonitors.isEmpty()) {
                    List<Object> errorDate = new ArrayList<>();
                    errorDate.add(task);
                    integritySuccess = buildCallbackInformation(AlesEnum.ERRPR_TYPE_DATA_NON_COMPLETE.getCode(), AlesEnum.ERRPR_TYPE_DATA_NON_COMPLETE.getName(), AlesEnum.NO_SON_OBJECT.getCode(), AlesEnum.NO_SON_OBJECT.getName(), errorDate);
                    return integritySuccess;
                } else {
                    for (AlesWtTaskMonitor monitor : alesWtTaskMonitors) {
                        List<AlesTaskSample> samples = getAlesTaskSamples(monitor.getId());
                        if (samples == null || samples.isEmpty()) {
                            List<Object> errorDate = new ArrayList<>();
                            errorDate.add(task);
                            errorDate.add(monitor);
                            integritySuccess = buildCallbackInformation(AlesEnum.ERRPR_TYPE_DATA_NON_COMPLETE.getCode(), AlesEnum.ERRPR_TYPE_DATA_NON_COMPLETE.getName(), AlesEnum.NO_SAMPLE.getCode(), AlesEnum.NO_SAMPLE.getName(), errorDate);
                            return integritySuccess;
                        }
                    }
                }
            } else {
                integritySuccess = buildCallbackInformation(AlesEnum.ERRPR_TYPE_DATA_NON_COMPLETE.getCode(), AlesEnum.ERRPR_TYPE_DATA_NON_COMPLETE.getName(), AlesEnum.NON_TASK_ID.getCode(), AlesEnum.NON_TASK_ID.getName(), null);
                return integritySuccess;
            }
        }
        integritySuccess.put("success", true);
        return integritySuccess;
    }

    public List<AlesTaskSample> getAlesTaskSamples(String monitorId) {
        return alesTaskSampleProvider.getSampleListByjcxxId(monitorId);
    }

    /**
     * 获取检测对象列表
     */
    public List<AlesWtTaskMonitor> getAlesWtTaskMonitors(String taskId) {
        AlesWtTaskMonitor alesWtTaskMonitor = new AlesWtTaskMonitor();
        alesWtTaskMonitor.setSuperviseCheckTaskId(String.valueOf(taskId));
        return aesWtTaskMonitorProvider.getListByTaskId(alesWtTaskMonitor);
    }

    /**
     * 发布动作
     */
    private Map<String, Object> syncSamplesToAdsSystem(CurrentUser currentUser,JSONArray taskIdJsonArray) {
        Map<String, Object> syncSuccess = new HashMap<>();
        List<AdsMonitorSample> adsSamples = new ArrayList<>();//抽样单
        for (Object taskId : taskIdJsonArray) {
            AlesEntrustDetection task = provider.queryById(String.valueOf(taskId));//任务
            List<AlesWtTaskMonitor> monitors = getAlesWtTaskMonitors(String.valueOf(taskId));//检测对象
            adsSamples.clear();
            for (AlesWtTaskMonitor monitor : monitors) {
                List<AlesTaskSample> samples = getAlesTaskSamples(monitor.getId());
                for (AlesTaskSample sample : samples) {
                    AdsMonitorSample adsSample = new AdsMonitorSample();
                    try {
                        BeanUtils.copyProperties(adsSample, sample);
                        adsSample.setSpecification(sample.getSpecificationGx());
                        adsSample.setComment(sample.getCommentGx());
                        adsSample.setSampleBarCode(adsSample.getSampleCode());//抽样条码，暂时没用到，先传样品编码，后期有需要再修改
                        adsSample.setSampleId(currentUser.getOrganization().getOrgId());//抽样单位key，暂时也没用到，先传机构ID
                        adsSamples.add(adsSample);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            boolean syncBoolean = adsMonitorTaskProvider.getTaskEntrustAndSample(task, adsSamples);//发布
            if (syncBoolean) {
                task.setState(AlesEnum.PUBLISHED.getCode());//已发布
                //更新抽样单状态
                provider.updateMonitorObjectStatus(task.getId(), AlesEnum.IS_SAMPLE.getCode());//更新检测对象状态
                for(AlesWtTaskMonitor alesWtTaskMonitor:monitors){
                    provider.updateSampleStatus(alesWtTaskMonitor.getId(), AlesEnum.IS_SAMPLE.getCode());//更新本地抽样单状态
                }
                provider.update(task);
            } else {
                List<Object> errorDate = new ArrayList<>();
                errorDate.add(task);
                syncSuccess = buildCallbackInformation(AlesEnum.ERRPR_TYPE_SYNC_ERROR.getCode(), AlesEnum.ERRPR_TYPE_SYNC_ERROR.getName(), errorDate);
                return syncSuccess;
            }
        }
        syncSuccess.put("success", true);
        return syncSuccess;
    }

    /**
     * 验证失败回调
     */
    private Map<String, Object> buildCallbackInformation(String errorTypeCode, String errorTypeName, String errorCode, String errorName, List<Object> errorData) {
        Map<String, Object> callbackInformation = new HashMap<>();
        callbackInformation.put("success", false);
        callbackInformation.put("errorTypeCode", errorTypeCode);
        callbackInformation.put("errorTypeName", errorTypeName);
        callbackInformation.put("errorCode", errorCode);
        callbackInformation.put("errorName", errorName);
        callbackInformation.put("errorData", errorData);
        return callbackInformation;
    }

    /**
     * 发布失败回调
     */
    private Map<String, Object> buildCallbackInformation(String errorTypeCode, String errorTypeName, List<Object> errorData) {
        Map<String, Object> callbackInformation = new HashMap<>();
        callbackInformation.put("success", false);
        callbackInformation.put("errorTypeCode", errorTypeCode);
        callbackInformation.put("errorTypeName", errorTypeName);
        callbackInformation.put("errorData", errorData);
        return callbackInformation;
    }

    public void updateAdsMonitorTaskPublishStatus(Map<String, Object> map){
        adsMonitorTaskProvider.updateAdsMonitorTaskPublishStatus(map);
    }
}
