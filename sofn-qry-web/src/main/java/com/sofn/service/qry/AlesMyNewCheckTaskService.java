package com.sofn.service.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AlesTaskSample;
import com.sofn.model.generator.AsmsCheckBearUnit;
import com.sofn.model.generator.AsmsCheckTask;
import com.sofn.model.qry.QueryParameter;
import com.sofn.provider.qry.AlesTaskSampleProvider;
import com.sofn.provider.qry.AsmsCheckTaskProvider;
import com.sofn.provider.sys.SysUserProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@Service
public class AlesMyNewCheckTaskService extends BaseService<AlesTaskSampleProvider, AlesTaskSample> {

    private AsmsCheckTaskProvider asmsCheckTaskProvider;//监管系统-监督检查

    private SysUserProvider sysUserProvider;//系统管理

    @DubboReference
    public void setProvider(AlesTaskSampleProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(AsmsCheckTaskProvider asmsCheckTaskProvider) {
        this.asmsCheckTaskProvider = asmsCheckTaskProvider;
    }

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    /**
     * 根据id获取监督抽查任务信息
     * */
    public AsmsCheckTask queryByid(String id){
        return  asmsCheckTaskProvider.queryById(id);
    }

    /**
     * 检测信息
     * */
    public PageInfo<List<Map<String, Object>>> getMyTaskObjById(AsmsCheckTask r, String dateBegin, String dateEnd, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        //query
        params.put("taskId", r.getId());
//        params.put("dateBegin", StringUtil.isNotBlank(dateBegin) ? dateBegin : null);
//        params.put("dateEnd", StringUtil.isNotBlank(dateEnd) ? dateEnd : null);
//        params.put("taskName", StringUtil.isNotBlank(r.getTaskName()) ? "%"+r.getTaskName()+"%" : null);
//        params.put("state", StringUtil.isNotBlank(r.getState()) ? r.getState() : null);
        PageInfo<List<Map<String, Object>>> i = asmsCheckTaskProvider.getMyTaskObjById(params);
        return i;
    }

    /**
     * 承担任务列表
     * */
    public PageInfo<List<Map<String, Object>>> getHistoryCheckTaskListByUser(AsmsCheckTask r,String regionId,String dateBegin, String dateEnd, int pageNum, int pageSize,String token) {

        //用户所属机构验证
        Organization org = sysUserProvider.findSysUserOrganization(token);
        String orgId = org.getOrgId();
        //机构类型验证
        String orgType = org.getOrgType();
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        //query
        //区域id
        if(null!=regionId){
            params.put("regionId",regionId.trim()+"%");
        }
        //任务状态
        params.put("isSample",StringUtil.isNotBlank(r.getIsSample())?r.getIsSample():null);
        //任务类型
        params.put("taskType",StringUtil.isNotBlank(r.getTaskType())?r.getTaskType():null);
        params.put("taskYear",StringUtil.isNotBlank(r.getTaskYear())?r.getTaskYear():null);
        params.put("dateBegin", StringUtil.isNotBlank(dateBegin) ? dateBegin : null);
        params.put("dateEnd", StringUtil.isNotBlank(dateEnd) ? dateEnd : null);
        params.put("taskName", StringUtil.isNotBlank(r.getTaskName()) ? "%"+r.getTaskName()+"%" : null);
        params.put("state", '1');
        params.put("orgId", orgId);
        params.put("orgType", orgType);
       // params.put("isSample", "1");
        PageInfo<List<Map<String, Object>>> i = asmsCheckTaskProvider.getHistoryCheckTask(params);
        return i;
    }

    /**
     * 获取承担任务列表
     */
    public List<AsmsCheckTask> getCheckTaskList(AsmsCheckTask asmsCheckTask,String tsBeginTime,String tsEndTime,String token){
        //用户机构
        Organization organization = sysUserProvider.findSysUserOrganization(token);
        String orgId = organization.getOrgId();
        Map<String, Object> params=new HashMap<>();
        params.put("dateBegin",StringUtil.isNotBlank(tsBeginTime)?tsBeginTime:null);
        params.put("dateEnd",StringUtil.isNotBlank(tsEndTime)?tsEndTime:null);
        params.put("taskName", StringUtil.isNotBlank(asmsCheckTask.getTaskName()) ? "%"+asmsCheckTask.getTaskName()+"%" : null);
        params.put("state","1");
        params.put("orgId",orgId);
        //调用provider
        return asmsCheckTaskProvider.getCheckTaskList(params);
    }

    /**
     * 根据任务id获取检测单位列表
     * */
    public List<AsmsCheckBearUnit> getUnitByTaskId(String taskId){
        return asmsCheckTaskProvider.getUnitByTaskId(taskId);
    }


    /**
     *监测信息下抽样单列表
     * */
    public PageInfo<AlesTaskSample> taskSampleListByJcInfo(AlesTaskSample r, QueryParameter p) {
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        params.put("pageSize", p.getLength());
        //query
        params.put("organTaskId", StringUtil.isNotBlank(r.getOrganTaskId()) ? r.getOrganTaskId() : null);//关联监测信息id
        PageInfo<AlesTaskSample> i = provider.list(params);
        return i;
    }

    /**
     * 根据id获取抽样单信息
     * */
    public AlesTaskSample getSampleById(String id){
        return provider.getSampleById(id);
    }

    /**
     * findAdsMonitorSample
     * */
    public AlesTaskSample findAdsMonitorSample(String sampleCode){
        return provider.getLocalSampleBySampleCode(sampleCode);
    }

    /**
     * 根据用户token获取organization
     * */
    public Organization getOrganizationByToken(String token){
        return sysUserProvider.findSysUserOrganization(token);
    }
}
