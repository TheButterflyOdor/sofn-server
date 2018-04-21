package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.asms.SuperviseTaskInfo;
import com.sofn.model.generator.AsmsInspectionTask;
import com.sofn.provider.asms.AsmsInspectionTaskProvider;
import com.sofn.provider.sys.SysUserProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsInspectionTaskService extends BaseService<AsmsInspectionTaskProvider, AsmsInspectionTask> {
    private SysUserProvider sysUserProvider;

    @DubboReference
    public void setProvider(AsmsInspectionTaskProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    public AsmsInspectionTask findById(String id) {
        AsmsInspectionTask record = provider.findById(id);
        return record;
    }

    public AsmsInspectionTask addTask(SuperviseTaskInfo taskInfo, String token) {
        AsmsInspectionTask task = this.getInsertTask(taskInfo);
        Organization org = this.getOrganizationByToken(token);
        task.setCreateOrgName(org.getOrgName());
        task.setCreateOrgId(org.getOrgId());
        return super.add(task);
    }

    public AsmsInspectionTask updateTask(SuperviseTaskInfo taskInfo) {
        AsmsInspectionTask task = this.getUpdateTask(taskInfo, taskInfo.getId());
        task.setDelFlag("N");
        super.update(task);
        return task;
    }


    public PageInfo<List<Map<String, Object>>> getPages(String token, AsmsInspectionTask task, QueryParameter p) {
        Organization org = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.listFilter(org.getOrgId()));
        params.putAll(AsmsServiceBoth.buidPage(p.getStart(), p.getLength()));
        params.put("dateBegin", p.getDateBegin());
        params.put("dateEnd", p.getDateEnd());
        params.put("querySheng", StringUtil.isNotBlank(p.getQuerySheng()) ? "%" + p.getQuerySheng() + "%" : null);
        params.put("queryShi", StringUtil.isNotBlank(p.getQueryShi()) ? "%" + p.getQueryShi() + "%" : null);
        params.put("queryXian", StringUtil.isNotBlank(p.getQueryXian()) ? "%" + p.getQueryXian() + "%" : null);
        params.put("taskType", StringUtil.isNotBlank(task.getTaskType()) ? "%" + task.getTaskType() + "%" : null);
        params.put("taskDateType", StringUtil.isNotBlank(task.getTaskDateType()) ? "%" + task.getTaskDateType() + "%" : null);
        PageInfo<List<Map<String, Object>>> i = provider.getPages(params);
        return i;
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

    private AsmsInspectionTask getInsertTask(SuperviseTaskInfo taskInfo) {
        AsmsInspectionTask task = new AsmsInspectionTask();
        return this.getBoth(task, taskInfo);
    }

    private AsmsInspectionTask getUpdateTask(SuperviseTaskInfo taskInfo, String taskId) {
        AsmsInspectionTask task = findById(taskId);
        task.setId(taskId);
        return this.getBoth(task, taskInfo);
    }

    private AsmsInspectionTask getBoth(AsmsInspectionTask asmsInspectionTask, SuperviseTaskInfo taskInfo) {
        asmsInspectionTask.setTaskType(taskInfo.getTaskType());
        asmsInspectionTask.setTaskTypeName(taskInfo.getTaskTypeName());
        asmsInspectionTask.setTaskDateType(taskInfo.getTaskDateType());
        asmsInspectionTask.setTaskDate(taskInfo.getTaskDate());
        asmsInspectionTask.setInspectionAreaId(taskInfo.getInspectionAreaId());
        asmsInspectionTask.setInspectionCount(taskInfo.getInspectionCount());
        asmsInspectionTask.setTaskDateYear(taskInfo.getTaskDateYear());
        asmsInspectionTask.setTaskDateMonths(taskInfo.getTaskDateMonths());
        asmsInspectionTask.setTaskDateQuarter(taskInfo.getTaskDateQuarter());
        asmsInspectionTask.setAttachmentAddress(taskInfo.getAttachmentAddress());
        asmsInspectionTask.setAttachmentName(taskInfo.getAttachmentName());
        asmsInspectionTask.setDetailContent(taskInfo.getDetailContent());
        return asmsInspectionTask;
    }

}
