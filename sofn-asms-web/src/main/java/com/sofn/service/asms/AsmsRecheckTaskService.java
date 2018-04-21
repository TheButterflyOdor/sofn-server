package com.sofn.service.asms;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AlesTaskSample;
import com.sofn.model.generator.AsmsMonitorObject;
import com.sofn.model.generator.AsmsRecheckObject;
import com.sofn.model.generator.AsmsRecheckTask;
import com.sofn.provider.ads.AdsMonitorTaskProvider;
import com.sofn.provider.ales.AlesTaskSampleProvider;
import com.sofn.provider.asms.AsmsMonitorObjectProvider;
import com.sofn.provider.asms.AsmsRecheckObjectProvider;
import com.sofn.provider.asms.AsmsRecheckTaskProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.web.asms.AsmsEnum;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author LiBing
 */
@Service
public class AsmsRecheckTaskService extends BaseService<AsmsRecheckTaskProvider, AsmsRecheckTask> {
    @DubboReference
    public void setProvider(AsmsRecheckTaskProvider provider) {
        this.provider = provider;
    }

    private AlesTaskSampleProvider taskSampleProvider;
    private AsmsMonitorObjectProvider asmsMonitorObjectProvider;
    private AsmsRecheckObjectProvider asmsRecheckObjectProvider;
    private AdsMonitorTaskProvider adsMonitorTaskProvider;
    private SysUserProvider sysUserProvider;

    @DubboReference
    public void setAlesTaskSampleProvider(AlesTaskSampleProvider provider) {
        taskSampleProvider = provider;
    }

    @DubboReference
    public void setAsmsMonitorObjectProvider(AsmsMonitorObjectProvider provider) {
        asmsMonitorObjectProvider = provider;
    }

    @DubboReference
    public void setAdsMonitorTaskProvider(AdsMonitorTaskProvider provider) {
        adsMonitorTaskProvider = provider;
    }

    @DubboReference
    public void setAsmsRecheckObjectProvider(AsmsRecheckObjectProvider provider) {
        asmsRecheckObjectProvider = provider;
    }

    @DubboReference
    public void setSysUserProvider(SysUserProvider provider) {
        sysUserProvider = provider;
    }

    public AsmsRecheckTask addTask(AsmsRecheckTask asmsRecheckTask, String token) {
        Organization organization = this.getOrganizationByToken(token);
        asmsRecheckTask.setCreateOrgId(organization.getOrgId());
        asmsRecheckTask.setCreateOrgName(organization.getOrgName());
        asmsRecheckTask.setCreateOrgRegionId(organization.getRegionId());
        //机构级别—任务级别,系统管理是1234，而数据字典是0123，所以要处理一下
        asmsRecheckTask.setLevelEnum(String.valueOf(Integer.valueOf(organization.getOrgLevel())-1));//任务级别
        asmsRecheckTask.setState(AsmsEnum.UN_PUBLISHED.getCode());//待发布
        CurrentUser user = WebUtil.getCurrentUser(token);
        asmsRecheckTask.setId(StringUtils.getUUIDString());
        asmsRecheckTask.setCreateBy(user.getId());
        asmsRecheckTask.setCreateTime(new Date());
        asmsRecheckTask.setUpdateBy(user.getId());
        asmsRecheckTask.setUpdateTime(new Date());
        asmsRecheckTask.setDelFlag("N");
        return provider.addAsmsRecheckTask(asmsRecheckTask);//主表信息
    }

    public void updateTask(AsmsRecheckTask asmsRecheckTask, String token){
        AsmsRecheckTask recheckTask = provider.queryById(asmsRecheckTask.getId());
        recheckTask.setRecheckTaskName(asmsRecheckTask.getRecheckTaskName());
        recheckTask.setRecheckTaskYear(asmsRecheckTask.getRecheckTaskYear());
        recheckTask.setRecheckTaskBegin(asmsRecheckTask.getRecheckTaskBegin());
        recheckTask.setRecheckTaskEnd(asmsRecheckTask.getRecheckTaskEnd());
        recheckTask.setRecheckUnitId(asmsRecheckTask.getRecheckUnitId());
        recheckTask.setRecheckUnitName(asmsRecheckTask.getRecheckUnitName());
        recheckTask.setBatch(asmsRecheckTask.getBatch());
        CurrentUser user = WebUtil.getCurrentUser(token);
        recheckTask.setUpdateBy(user.getId());
        recheckTask.setUpdateTime(new Date());
        provider.updateAsmsRecheckTask(recheckTask);//主表信息
    }

    public List<AsmsRecheckObject> getObjById(String id) {
        return provider.getObjById(id);
    }

    public PageInfo<AsmsRecheckTask> list(String token, AsmsRecheckTask asmsRecheckTask, QueryParameter queryParameter) {
        Organization organization = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.listFilter(organization.getOrgId()));//创建机构
        params.putAll(AsmsServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("dateBegin", queryParameter.getDateBegin());
        params.put("dateEnd", queryParameter.getDateEnd());
        params.put("recheckTaskName", StringUtil.isNotBlank(asmsRecheckTask.getRecheckTaskName()) ? "%" + asmsRecheckTask.getRecheckTaskName() + "%" : null);
        params.put("recheckTaskYear", StringUtil.isNotBlank(asmsRecheckTask.getRecheckTaskYear()) ? "%" + asmsRecheckTask.getRecheckTaskYear() + "%" : null);
        params.put("state", StringUtil.isNotBlank(asmsRecheckTask.getState()) ? asmsRecheckTask.getState() : null);
        return provider.list(params);
    }

    public PageInfo<List<Map<String, Object>>> taskSampleList(AlesTaskSample alesTaskSample, String taskId, String batch, String taskType, int start, int length) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(start, length));
        params.put("taskId", taskId);
        params.put("sampleName", StringUtil.isNotBlank(alesTaskSample.getSampleName()) ? "%" + alesTaskSample.getSampleName() + "%" : null);
        params.put("sampleCode", StringUtil.isNotBlank(alesTaskSample.getSampleCode()) ? "%" + alesTaskSample.getSampleCode() + "%" : null);
        PageInfo<List<Map<String, Object>>> page = new PageInfo<>();
        if (AsmsEnum.ROUTINE_MONITOR_TASK.getCode().equals(taskType) || AsmsEnum.SPECIAL_MONITOR_TASK.getCode().equals(taskType)) {
            params.put("batch", batch);//批次
            params.put("supId", taskId); //任务id
            page = adsMonitorTaskProvider.getSamplesWithSupId(params);//例行监测&专项监测
        } else if (AsmsEnum.CHECK_TASK.getCode().equals(taskType)) {
            AsmsMonitorObject asmsMonitorObject = new AsmsMonitorObject();//监督抽查 获取对象列表
            asmsMonitorObject.setSuperviseCheckTaskId(taskId);
            List<AsmsMonitorObject> olis = asmsMonitorObjectProvider.getMobjListByTaskId(asmsMonitorObject);
            if (olis.size() == 0) {
                PageInfo pageInfo = new PageInfo();
                pageInfo.setList(new ArrayList());
                pageInfo.setTotal(0);
                return pageInfo;
            } else {
                List<String> lisId = new ArrayList<>();
                for (AsmsMonitorObject a : olis) {
                    lisId.add(a.getId());
                }
                params.put("objIds", lisId);
                page = taskSampleProvider.getSampleListByObjIds(params);//监督抽查
            }
        }
        return page;
    }

    //任务发布
    public String sendToAds(String jsonIds) {
        JSONArray jsonArray = JSONArray.parseArray(jsonIds);
        for (Object id : jsonArray) {
            AsmsRecheckTask asmsRecheckTask = super.queryById(id.toString());
            List<AsmsRecheckObject> list = asmsRecheckObjectProvider.getObjsByTaskId(id.toString());//send
            if (list.size() == 0) {
                return AsmsEnum.NO_SON_OBJECT.getCode();
            }
            boolean sendIndx = adsMonitorTaskProvider.getTaskInfoForReback(asmsRecheckTask, list);
            if (sendIndx) {
                asmsRecheckTask.setState(AsmsEnum.PUBLISHED.getCode());
                super.update(asmsRecheckTask);
                return "true";
            } else {
                return "false";
            }
        }
        return "true";
    }

    public AlesTaskSample findAlesTaskSample(String sampleCode) {
        return taskSampleProvider.getSampleBySampleCode(sampleCode);
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }


    public void updateAdsMonitorTaskPublishStatus(Map<String, Object> map){
        adsMonitorTaskProvider.updateAdsMonitorTaskPublishStatus(map);
    }
}
