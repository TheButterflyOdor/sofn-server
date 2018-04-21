package com.sofn.service.asms;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.*;
import com.sofn.provider.ads.AdsMonitorSampleProvider;
import com.sofn.provider.ads.AdsMonitorTaskProvider;
import com.sofn.provider.asms.AsmsBaseInspectionProvider;
import com.sofn.provider.asms.AsmsCheckTaskProvider;
import com.sofn.provider.asms.AsmsMonitorObjectProvider;
import com.sofn.provider.sys.SysTestItemProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.util.Page;
import com.sofn.web.asms.AsmsEnum;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author LiBing
 */
@Service
public class AsmsCheckTaskService extends BaseService<AsmsCheckTaskProvider, AsmsCheckTask> {

    private AdsMonitorSampleProvider adsMonitorSampleProvider;//检测系统-抽样单

    private AsmsBaseInspectionProvider asmsBaseInspectionProvider;//基地巡查

    private AdsMonitorTaskProvider adsMonitorTaskProvider;//检测系统

    private SysUserProvider sysUserProvider;//系统管理

    private AsmsMonitorObjectProvider monitorObjectProvider;//监测对象

    @DubboReference
    public void setProvider(AsmsCheckTaskProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(AdsMonitorTaskProvider adsMonitorTaskProvider) {
        this.adsMonitorTaskProvider = adsMonitorTaskProvider;
    }

    @DubboReference
    public void setProvider(AsmsMonitorObjectProvider monitorObjectProvider) {
        this.monitorObjectProvider = monitorObjectProvider;
    }

    @DubboReference
    public void setProvider(AsmsBaseInspectionProvider asmsBaseInspectionProvider) {
        this.asmsBaseInspectionProvider = asmsBaseInspectionProvider;
    }

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setProvider(AdsMonitorSampleProvider adsMonitorSampleProvider) {
        this.adsMonitorSampleProvider = adsMonitorSampleProvider;
    }

    private SysTestItemProvider sysTestItemProvider;
    @DubboReference
    public void setSysTestItemProviderImpl(SysTestItemProvider sysTestItemProvider){
        this.sysTestItemProvider = sysTestItemProvider;
    }


    public List<Map<String, Object>> getCheckTaskByUser(AsmsCheckTask checkTask) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskName", StringUtil.isNotBlank(checkTask.getTaskName()) ? "%" + checkTask.getTaskName() + "%" : null);
        params.put("state", StringUtil.isNotBlank(checkTask.getState()) ? checkTask.getState() : null);
        return provider.getCheckTaskByUser(params);
    }

    /**
     * 新增部级任务
     */
    public void addBjTask(AsmsCheckTask checkTask, String orgs, String token) {
        String id = super.add(this.insertBjTask(checkTask, token)).getId();//任务
        this.insertOrgs(orgs, id);//检测机构
    }

    /**
     * 修改部级任务
     */
    public void updateBjTask(AsmsCheckTask checkTask, String orgs, String token) {
        super.update(this.updateBjTask(checkTask));//任务
        this.updateOrgs(orgs, checkTask.getId());//检测机构
    }

    /**
     * 新增非部级任务
     */
    public String addTask(AsmsCheckTask checkTask, String token) {
        Organization organization = this.getOrganizationByToken(token);
        checkTask.setCreateOrgRegionId(organization.getRegionId());//任务所属创建机构行政区划
        //机构级别—任务级别,系统管理是1234，而数据字典是0123，所以要处理一下
        checkTask.setLevelEnum(String.valueOf(Integer.valueOf(organization.getOrgLevel())-1));//任务级别
        checkTask.setState(AsmsEnum.UN_PUBLISHED.getCode());//待发布
        checkTask.setTaskLevel(AsmsEnum.NON_MINISTERIAL.getCode());//非部级1 部级0
        checkTask.setCreateOrgId(organization.getOrgId());//创建机构id
        checkTask.setCreateOrgName(organization.getOrgName());//创建机构名称
        checkTask.setIsSample(AsmsEnum.NON_SEPARATE.getCode());//未提交抽样单
        CurrentUser user = WebUtil.getCurrentUser(token);
        checkTask.setId(StringUtils.getUUIDString());
        checkTask.setCreateBy(user.getId());
        checkTask.setCreateTime(new Date());
        checkTask.setUpdateBy(user.getId());
        checkTask.setDelFlag("N");
        checkTask.setUpdateTime(new Date());
        String taskId = provider.addAsmsCheckTask(checkTask).getId();//主表信息
        asmsBaseInspectionProvider.updateElState(taskId, checkTask.getBaseInspectionId());
        return taskId;
    }

    /**
     * 修改非部级任务
     */
    public void updateTask(AsmsCheckTask checkTask,String token) {
        AsmsCheckTask selectTask = provider.queryById(checkTask.getId());
        selectTask.setTaskName(checkTask.getTaskName());//任务名称
        selectTask.setTaskYear(checkTask.getTaskYear());//年限
        selectTask.setTaskIndustry(checkTask.getTaskIndustry());//行业
        selectTask.setTaskAreaId(checkTask.getTaskAreaId());//受检区域id
        selectTask.setTaskBeginTime(checkTask.getTaskBeginTime());//开始时间
        selectTask.setTaskEndTime(checkTask.getTaskEndTime());//结束时间
        selectTask.setTaskIsSeparate(checkTask.getTaskIsSeparate());//是否抽检分离
        selectTask.setTaskReleaseUnit(checkTask.getTaskReleaseUnit());//发布单位
        selectTask.setTaskSampleDeadline(checkTask.getTaskSampleDeadline());//抽样信息上报截止时间
        selectTask.setIsSample(AsmsEnum.UN_PUBLISHED.getCode());
        selectTask.setCyUnitId(checkTask.getCyUnitId());
        selectTask.setCyUnitName(checkTask.getCyUnitName());
        selectTask.setJcUnitId(checkTask.getJcUnitId());
        selectTask.setJcUnitName(checkTask.getJcUnitName());
        selectTask.setParentTaskId(checkTask.getParentTaskId());
        selectTask.setParentTaskName(checkTask.getParentTaskName());
        selectTask.setJdUnitId(checkTask.getJdUnitId());
        selectTask.setJdUnitName(checkTask.getJdUnitName());
        selectTask.setAttachmentAddress(checkTask.getAttachmentAddress());
        selectTask.setAttachmentName(checkTask.getAttachmentName());
        selectTask.setFileNumber(checkTask.getFileNumber());
        CurrentUser user = WebUtil.getCurrentUser(token);
        selectTask.setUpdateBy(user.getId());
        selectTask.setUpdateTime(new Date());
        provider.updateAsmsCheckTask(selectTask);//主表信息
    }

    /**
     * 删除
     */
    public boolean delTask(String taskId) {
        AsmsCheckTask checkTask = this.queryById(taskId);
        asmsBaseInspectionProvider.updateElState("0", checkTask.getBaseInspectionId());
        this.delete(taskId);
        return true;
    }

    public List<Map<String, Object>> getObjById(String id) {
        return provider.getObjById(id);
    }

    public PageInfo<List<Map<String, Object>>> list(String token, AsmsCheckTask checkTask, QueryParameter queryParameter) {
        Organization organization = this.getOrganizationByToken(token);
        String orgLevel = organization.getOrgLevel();
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.listFilter(organization.getOrgId()));//创建机构
        params.putAll(AsmsServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        if (!AsmsEnum.MINISTRY.getCode().equals(orgLevel)) {
            if (StringUtil.isNotBlank(queryParameter.getQueryXian())) {
                params.put("taskAreaId", queryParameter.getQueryXian() + "%");
            } else if (StringUtil.isNotBlank(queryParameter.getQueryShi())) {
                params.put("taskAreaId", queryParameter.getQueryShi() + "%");
            } else if (StringUtil.isNotBlank(queryParameter.getQuerySheng())) {
                params.put("taskAreaId", queryParameter.getQuerySheng() + "%");
            }
        }
        params.put("dateBegin", StringUtil.isNotBlank(queryParameter.getDateBegin()) ? queryParameter.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(queryParameter.getDateEnd()) ? queryParameter.getDateEnd() : null);
        params.put("taskName", StringUtil.isNotBlank(checkTask.getTaskName()) ? "%" + checkTask.getTaskName() + "%" : null);
        params.put("state", StringUtil.isNotBlank(checkTask.getState()) ? checkTask.getState() : null);
        return provider.list(params);
    }

    public PageInfo<List<AsmsCheckTask>> listByOrg(String token, AsmsCheckTask checkTask, QueryParameter queryParameter) {
        Organization org = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("createOrgId", org.getOrgId());
        params.put("dateBegin", StringUtil.isNotBlank(queryParameter.getDateBegin()) ? queryParameter.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(queryParameter.getDateEnd()) ? queryParameter.getDateEnd() : null);
        params.put("taskName", StringUtil.isNotBlank(checkTask.getTaskName()) ? "%" + checkTask.getTaskName() + "%" : null);
        params.put("state", StringUtil.isNotBlank(checkTask.getState()) ? checkTask.getState() : null);
        return provider.listByOrg(params);
    }

    /**
     * 发布任务将任务信息及检测信息同步至检测系统
     */
    public String sendDateToAdsByAdd(AsmsCheckTask task) {
        try {
            AsmsMonitorObject o = new AsmsMonitorObject();
            o.setSuperviseCheckTaskId(task.getId());
            List<AsmsMonitorObject> objList = monitorObjectProvider.getMobjListByTaskId(o);
            if (0 == objList.size()) {
                return AsmsEnum.NO_SON_OBJECT.getCode();
            }
            boolean k;
            k = adsMonitorTaskProvider.getTaskInfo(task, objList);
            if (k) {
                task.setState(AsmsEnum.PUBLISHED.getCode());
                super.update(task);
            } else {
                return String.valueOf(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    /**
     * 根据当前用户所属机构级别查询父级机构任务列表
     */
    public PageInfo<List<Map<String, Object>>> getParentTaskList(String token, AsmsCheckTask checkTask, QueryParameter queryParameter) {
        Organization org = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("dateBegin", StringUtil.isNotBlank(queryParameter.getDateBegin()) ? queryParameter.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(queryParameter.getDateEnd()) ? queryParameter.getDateEnd() : null);
        params.put("taskName", StringUtil.isNotBlank(checkTask.getTaskName()) ? "%" + checkTask.getTaskName() + "%" : null);
        params.put("superviseBearUnitId", org.getOrgId());//承担单位id
        params.put("state", AsmsEnum.PUBLISHED.getCode());//已发布任务
        params.put("taskLevel", AsmsEnum.MINISTERIAL.getCode());//部级任务
        return provider.getParentTaskList(params);
    }

    /**
     * 废止任务将废止信息同步至检测系统
     */
    public boolean sendDateToAdsByAbo(Object o) {
        return adsMonitorTaskProvider.setAbolish(o.toString());
    }

    /**
     * 根据任务id获取检测单位列表
     */
    public List<AsmsCheckBearUnit> getUnitByTaskId(String taskId) {
        return provider.getUnitByTaskId(taskId);
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

    /**
     * 根据检测对象id&父级任务id获取抽样单列表[监督抽查]
     */
    public PageInfo<AdsMonitorSample> getTaskSampleListByJcInfo(AsmsCheckTask mon, QueryParameter q, String taskObjId, String JdccCdUnit, String sonTaskId) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(q.getStart(), q.getLength()));
        List<String> taskIds = new ArrayList<>();
        AsmsCheckTask task = this.queryById(mon.getId());
        if (AsmsEnum.MINISTERIAL.getCode().equals(task.getTaskLevel())) {//部级用户
            if (sonTaskId == null) {//部级用户选择子任务
                List<AsmsCheckTask> sonTasks = provider.getTaskByCreateOrgIdAndParentTaskId(task.getId(), JdccCdUnit);
                if (sonTasks.size() != 0) {
                    for (AsmsCheckTask t : sonTasks) {
                        taskIds.add(t.getId());
                    }
                }
                params.put("supId", taskIds);
            } else {
                taskIds.add(sonTaskId);
                params.put("supId", taskIds);
            }

        } else {
            taskIds.add(mon.getId());
            params.put("supId", taskIds);
        }
        if (taskIds.size() == 0) {
            PageInfo<AdsMonitorSample> lis = new PageInfo<AdsMonitorSample>();
            List<AdsMonitorSample> l = new ArrayList<>();
            lis.setList(l);
            lis.setTotal(0);
            return lis;
        }
        params.put("organTaskId", taskObjId);//检测对象id
        return adsMonitorSampleProvider.getAdsMonitorSamplePageInfoByCode(params);
    }

    /**
     * 根据任务id与牵头单位id获取抽样单列表[例行监测、专项检测]
     */
    public PageInfo<AdsMonitorSample> getZLTaskSampleListByJcInfo(AsmsCheckTask mon, QueryParameter q) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(q.getStart(), q.getLength()));
        params.put("supId", mon.getId());//任务id
        params.put("organId", mon.getParentTaskId());//牵头单位id
        return adsMonitorSampleProvider.findAllAdsMonitorSample(params);
    }

    /**
     * 根据id获取抽样单详情
     */
    public Map<String,Object> getTaskSampleById(AdsMonitorSample mon) {
        return adsMonitorSampleProvider.findAdsMonitorSampleById(mon);
    }

    /**
     * 根据父任务id和承担单位id获取子任务
     */
    public List<AsmsCheckTask> getTasksByCreateOrgIdAndParentTaskId(AsmsCheckTask checkTask) {
        return provider.getTaskByCreateOrgIdAndParentTaskId(checkTask.getParentTaskId(), checkTask.getCreateOrgId());
    }

    /**
     * 根据父任务id获取子任务
     */
    public List<AsmsCheckTask> getTasksByParentTaskId(AsmsCheckTask checkTask) {
        return provider.getTaskByParentTaskId(checkTask.getId());
    }

    private AsmsCheckTask insertBjTask(AsmsCheckTask checkTask, String token) {
        Organization organization = this.getOrganizationByToken(token);
        checkTask.setCreateOrgRegionId(organization.getRegionId());//任务所属创建机构行政区划
        checkTask.setLevelEnum(organization.getOrgLevel());//任务级别
        checkTask.setCreateOrgId(organization.getOrgId());//创建机构id
        checkTask.setCreateOrgName(organization.getOrgName());//创建机构名称
        checkTask.setState(AsmsEnum.UN_PUBLISHED.getCode());//待发布
        checkTask.setTaskLevel(AsmsEnum.MINISTERIAL.getCode());//省级1 部级0
        return checkTask;
    }

    private AsmsCheckTask updateBjTask(AsmsCheckTask checkTask) {
        AsmsCheckTask selectTask = provider.queryById(checkTask.getId());
        selectTask.setTaskName(checkTask.getTaskName());//任务名称
        selectTask.setTaskType(checkTask.getTaskType());//任务类型
        selectTask.setTaskYear(checkTask.getTaskYear());//年限
        selectTask.setTaskBeginTime(checkTask.getTaskBeginTime());//开始时间
        selectTask.setTaskEndTime(checkTask.getTaskEndTime());//结束时间
        selectTask.setTaskReleaseUnit(checkTask.getTaskReleaseUnit());//发布单位
        selectTask.setFiles(checkTask.getFiles());//附件
        selectTask.setFilesname(checkTask.getFilesname());//附件
        selectTask.setFileCode(checkTask.getFileCode());//文件号
        selectTask.setRemark(checkTask.getRemark());//备注
        return selectTask;
    }

    private void insertOrgs(String orgs, String taskId) {
        if (!"[]".equals(orgs)) {
            List<AsmsCheckBearUnit> asmsCheckBearUnits = JSONObject.parseArray(orgs, AsmsCheckBearUnit.class);
            for (AsmsCheckBearUnit asmsCheckBearUnit : asmsCheckBearUnits) {
                if (asmsCheckBearUnit != null) {
                    asmsCheckBearUnit.setSuperviseCheckTaskId(taskId);
                    provider.addBjGlInfo(asmsCheckBearUnit);
                }
            }
        }
    }

    private void updateOrgs(String orgs, String taskId) {
        provider.delBjGlInfoByTaskId(taskId);
        this.insertOrgs(orgs, taskId);
    }

    public PageInfo<SysTestItemModel> getCheckItemList(Page page, String itemName, String standardCode){
        com.sofn.core.persistence.Page page2 = new com.sofn.core.persistence.Page();
        page2.setStart(page.getStart() / page.getLength());
        page2.setLength(page.getLength());
        return sysTestItemProvider.getTestItems(itemName, standardCode, page2);
    }


    public void updateAdsMonitorTaskPublishStatus(Map<String, Object> map){
        adsMonitorTaskProvider.updateAdsMonitorTaskPublishStatus(map);
    }
}
