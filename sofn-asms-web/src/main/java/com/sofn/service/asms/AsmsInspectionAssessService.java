package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.asms.SuperviseTaskInfo;
import com.sofn.model.generator.AsmsInspectionAssess;
import com.sofn.provider.asms.AsmsInspectionAssessProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.web.asms.AsmsEnum;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsInspectionAssessService extends BaseService<AsmsInspectionAssessProvider, AsmsInspectionAssess> {
    private SysUserProvider sysUserProvider;

    @DubboReference
    public void setProvider(AsmsInspectionAssessProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    public void delOldDate(AsmsInspectionAssess taskUser) {
        provider.delOldDate(taskUser);
    }

    /**
     * 获取巡查人员考核列表
     */
    public PageInfo<Map<String, Object>> getPages(String token, SuperviseTaskInfo taskInfo, QueryParameter p) {
        Organization org = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.listFilter(org.getOrgId()));
        params.putAll(AsmsServiceBoth.buidPage(p.getStart(), p.getLength()));
        params.put("dateBegin", p.getDateBegin());
        params.put("dateEnd", p.getDateEnd());
        params.put("taskType", StringUtil.isNotBlank(taskInfo.getTaskType()) ? "%" + taskInfo.getTaskType() + "%" : null);
        params.put("taskDateType", StringUtil.isNotBlank(taskInfo.getTaskDateType()) ? "%" + taskInfo.getTaskDateType() + "%" : null);
        params.put("userName", StringUtil.isNotBlank(taskInfo.getUserName()) ? "%" + taskInfo.getUserName() + "%" : null);
        params.put("taskResult", StringUtil.isNotBlank(taskInfo.getTaskResult()) ? taskInfo.getTaskResult() : null);
        PageInfo<Map<String, Object>> i = provider.getPages(params);
        i.setList(this.buidInformation(i.getList()));
        return i;
    }

    /**
     * 实际巡查次数，任务状态，巡查人员任务完成状态
     * 当前时间<任务开始时间：未开始
     * 任务开始时间<=当前时间<=任务结束时间：执行中
     * 当前时间>任务结束时间：已结束（完成）/已结束（未完成）
     * 已结束（完成）表示：当前时间>任务结束时间且实际巡查次数>=设定巡查次数
     * 已结束（未完成）表示：当前时间>任务结束时间且实际巡查次数<设定巡查次数
     */
    private List<Map<String, Object>> buidInformation(List<Map<String, Object>> list) {
        String userIdColumu = "USERID";
        String taskDateTypeColumn = "TASKDATETYPE";//考核类型
        String taskDateYearColumu = "TASKDATEYEAR";//年
        String taskDateMonthsColumn = "TASKDATEMONTHS";//月
        String taskDateQuarterColumn = "TASKDATEQUARTER";//日
        String realCountColumn = "INSPECTIONREALCOUNT";//实际巡查次数
        String countColumn = "INSPECTIONCOUNT";//任务设定巡查次数

        Map<String, Object> params = new HashMap<>();//查询参数
        if (list != null && !list.isEmpty()) {
            for (Map<String, Object> mp : list) {
                String userId = (String) mp.get(userIdColumu);
                String taskDateType = (String) mp.get(taskDateTypeColumn);
                String taskDateYear = (String) mp.get(taskDateYearColumu);
                String taskDateMonths = (String) mp.get(taskDateMonthsColumn);
                String taskDateQuarter = (String) mp.get(taskDateQuarterColumn);
                StringBuffer beginTime = new StringBuffer();
                StringBuffer endTime = new StringBuffer();
                switch (taskDateType.trim()) {
                    case "月度":
                        beginTime.append(taskDateYear + "-" + taskDateMonths + "-01 00:00:00");
                        if ("12".equals(taskDateMonths)) {
                            Integer beEndYear = Integer.valueOf(taskDateYear) + 1;
                            endTime.append(beEndYear.toString() + "-01-01 00:00:00");
                        } else {
                            Integer beEndMonths = Integer.valueOf(taskDateMonths) + 1;
                            endTime.append(taskDateYear + "-" + beEndMonths.toString() + "-01 00:00:00");
                        }
                        break;
                    case "季度":
                        switch (taskDateQuarter.trim()) {
                            case "1":
                                beginTime.append(taskDateYear + "-01-01 00:00:00");
                                endTime.append(taskDateYear + "-03-31 00:00:00");
                                break;
                            case "2":
                                beginTime.append(taskDateYear + "-04-01 00:00:00");
                                endTime.append(taskDateYear + "-06-30 00:00:00");
                                break;
                            case "3":
                                beginTime.append(taskDateYear + "-07-01 00:00:00");
                                endTime.append(taskDateYear + "-09-30 00:00:00");
                                break;
                            case "4":
                                beginTime.append(taskDateYear + "-10-01 00:00:00");
                                endTime.append(taskDateYear + "-12-30 00:00:00");
                                break;
                        }
                        break;
                    case "年度":
                        beginTime.append(taskDateYear + "-01-01 00:00:00");
                        endTime.append(taskDateYear + "-12-30 00:00:00");
                        break;

                }
                params.put("userId", userId);
                params.put("beginTime", beginTime);
                params.put("endTime", endTime);
                Long realCount = provider.getRealCount(params);//实际巡查次数
                mp.put(realCountColumn, realCount.toString());
                Long count = Long.valueOf(mp.get(countColumn).toString());//任务设定巡查次数
                mp.putAll(this.buidTaskStaicAndUserStaic(beginTime, endTime, realCount, count));
            }
        }
        return list;
    }

    /**
     * 组建任务状态及巡查人员任务完成情况
     */
    private Map<String, Object> buidTaskStaicAndUserStaic(StringBuffer beginTime, StringBuffer endTime, Long realCount, Long taskCount) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Map<String, Object> map = new HashMap<>();
        String columnName = "TASKSTATUS";
        try {
            Date taskBeginData = simpleDateFormat.parse(beginTime.toString());
            Date taskEndTimeData = simpleDateFormat.parse(endTime.toString());
            if (date.getTime() < taskBeginData.getTime()) {
                map.put(columnName, AsmsEnum.TASK_BEFORE_DATE.getName());
            } else if (date.getTime() > taskBeginData.getTime() && date.getTime() < taskEndTimeData.getTime()) {
                map.put(columnName, AsmsEnum.TASK_IN_DATE.getName());
            } else if (date.getTime() > taskEndTimeData.getTime()) {
                if (realCount < taskCount && taskCount != 0) {
                    map.put(columnName, AsmsEnum.TASK_OUT_DATE.getName() + "(" + AsmsEnum.TASK_NON_OVER.getName() + ")");
                } else {
                    map.put(columnName, AsmsEnum.TASK_OUT_DATE.getName() + "(" + AsmsEnum.TASK_OVER.getName() + ")");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据任务id获取巡查人员
     */
    public List<AsmsInspectionAssess> getPersonByTaskId(String taskId) {
        return provider.getPersonByTaskId(taskId);
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

}
