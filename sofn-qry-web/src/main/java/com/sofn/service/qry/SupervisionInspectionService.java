package com.sofn.service.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.DictType;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.qry.EnforceLawPersonnelDto;
import com.sofn.model.qry.QueryParameter;
import com.sofn.model.qry.SuperviseTaskInfo;
import com.sofn.provider.qry.SupervisionInspectionProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysRoleProvider;
import com.sofn.util.Page;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sofn
 * @version 2017年03月23日 上午 10:59
 */
@Service
public class SupervisionInspectionService extends BaseService{

    private SupervisionInspectionProvider provider;
    @DubboReference
    public void setProvider(SupervisionInspectionProvider provider){
        this.provider = provider;
    }

    private SysDictProvider sysDictProvider;
    @DubboReference
    public void setSysDictProvider(SysDictProvider sysDictProvider){
        this.sysDictProvider = sysDictProvider;
    }
    private SysRoleProvider sysRoleProvider;
    @DubboReference
    public void setSysRoleProvider(SysRoleProvider sysRoleProvider){
        this.sysRoleProvider = sysRoleProvider;
    }

    public Set getUserIndustryRoleByToken(String userId){
        List<SysRole> list = sysRoleProvider.getSysRolesByUserId(userId);
        Set set = new HashSet();
        for(SysRole sysRole:list){
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("01-JG-INDUSTRY")){
                set.add("01");
            }
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("02-JG-INDUSTRY")){
                set.add("02");
            }
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("03-JG-INDUSTRY")){
                set.add("03");
            }
        }
        return set;
    }

    public PageInfo getBaseInspectionAllList(AsmsBaseInspection asmsBaseInspection , String dateBegin, String dateEnd, Page page,
                                             String entityIndustry, String entityScale,String entityType, String enterpriseName,
                                             String area, String createDateBegin,String createDateEnd){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("dateBegin",dateBegin);
        queryParams.put("dateEnd",dateEnd);
        queryParams.put("createDateBegin",createDateBegin);
        queryParams.put("createDateEnd",createDateEnd);
        queryParams.put("inspectionUserName",asmsBaseInspection.getInspectionUserName());
        queryParams.put("pageOffset",page.getPageOffset());
        queryParams.put("pageTail",page.getPageTail());
        queryParams.put("inspectionResult",asmsBaseInspection.getInspectionResult());
        if(entityIndustry!=null&&!"".equals(entityIndustry.trim())){
            queryParams.put("entityIndustry",entityIndustry.split(","));
        }
        if(entityType!=null&&!"".equals(entityType.trim())){
            queryParams.put("entityType",getBothEntityType(entityType.split(",")));
        }
        if(entityScale!=null&&!"".equals(entityScale.trim())){
            queryParams.put("entityScale",entityScale.split(","));
        }
        if(asmsBaseInspection.getInspectionType()!=null&&!"".equals(asmsBaseInspection.getInspectionType().trim())){
            queryParams.put("inspectionType",asmsBaseInspection.getInspectionType().split(","));
        }
//        queryParams.put("entityType",entityType);
//        queryParams.put("entityScale",entityScale);
        queryParams.put("enterpriseName",enterpriseName==null?null:enterpriseName.trim());
        queryParams.put("area",area);
        return provider.getBaseInspectionAllList(queryParams);
    }

    public PageInfo<List<Map<String, Object>>> getInspectionTaskList(AsmsInspectionTask task, QueryParameter p) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        queryParams.put("pageSize", p.getLength());
        //queryParm
        queryParams.put("dateBegin", p.getDateBegin());
        queryParams.put("dateEnd", p.getDateEnd());
        queryParams.put("querySheng", StringUtil.isNotBlank(p.getQuerySheng()) ? "%" + p.getQuerySheng() + "%" : null);
        queryParams.put("queryShi", StringUtil.isNotBlank(p.getQueryShi()) ? "%" + p.getQueryShi() + "%" : null);
        queryParams.put("queryXian", StringUtil.isNotBlank(p.getQueryXian()) ? "%" + p.getQueryXian() + "%" : null);
        queryParams.put("taskType",StringUtil.isNotBlank(task.getTaskType()) ? "%" + task.getTaskType() + "%" : null);
        queryParams.put("taskDateType",StringUtil.isNotBlank(task.getTaskDateType()) ? "%" + task.getTaskDateType() + "%" : null);
        PageInfo<List<Map<String, Object>>> i = provider.getInspectionTaskList(queryParams);
        return i;
    }

    /**
     * 获取巡查人员考核列表
     * */
    public PageInfo<List<Map<String, Object>>> getInspectionManAssessList(SuperviseTaskInfo taskInfo, QueryParameter p) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        queryParams.put("pageSize", p.getLength());
        queryParams.put("dateBegin", p.getDateBegin());
        queryParams.put("dateEnd", p.getDateEnd());
        queryParams.put("taskType", StringUtil.isNotBlank(taskInfo.getTaskType()) ? "%" + taskInfo.getTaskType() + "%" : null);
        queryParams.put("taskDateType", StringUtil.isNotBlank(taskInfo.getTaskDateType()) ? "%" + taskInfo.getTaskDateType() + "%" : null);
        queryParams.put("userName", StringUtil.isNotBlank(taskInfo.getUserName()) ? "%" + taskInfo.getUserName() + "%" : null);
        queryParams.put("taskResult", StringUtil.isNotBlank(taskInfo.getTaskResult()) ? taskInfo.getTaskResult() : null);
        PageInfo<List<Map<String, Object>>> i = provider.getInspectionManAssessList(queryParams);
        return i;
    }

    public AsmsBaseInspection findBaseInspectionById(String id){
        return provider.findBaseInspectionById(id);
    }

    public PageInfo getEnforceLawPersonList(EnforceLawPersonnelDto enforceLawPersonnelDto,Page page){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("pageOffset",page.getPageOffset());
        queryParams.put("pageTail",page.getPageTail());
        queryParams.put("levelId",enforceLawPersonnelDto.getLevelId());
        queryParams.put("userPostId",enforceLawPersonnelDto.getUserPostId());
        queryParams.put("userName",enforceLawPersonnelDto.getUserName());
        queryParams.put("areaId",enforceLawPersonnelDto.getAreaId());
        return provider.getEnforceLawPersonList(queryParams);
    }

    public EnforceLawPersonnelDto findEnforceLawPersonById(EnforceLawPersonnelDto enforceLawPersonnelDto){
        return provider.findEnforceLawPersonById(enforceLawPersonnelDto.getId());
    }

    public PageInfo getKhTaskList(String token,Page page, String taskDateType, String taskDateYear,
                                  String taskDateMonths, String taskDateQuarter,String taskType){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("pageOffset",page.getPageOffset());
        queryParams.put("pageTail",page.getPageTail());
        queryParams.put("theUserOrgId", WebUtil.getCurrentUser(token).getOrgId());
        if(StringUtils.isNotBlank(taskType)){
            queryParams.put("taskType", taskType.split(","));
        }
        queryParams.put("taskDateType",taskDateType==null?"":taskDateType.trim());
        if(StringUtils.isNotBlank(taskDateType)){
            if("年度".equals(taskDateType.trim())){
                queryParams.put("taskDateYear", taskDateYear.trim());
            }else if("季度".equals(taskDateType.trim())){
                queryParams.put("taskDateYear", taskDateYear.trim());
                queryParams.put("taskDateQuarter", taskDateQuarter.trim());
            }else if("月度".equals(taskDateType.trim())){
                queryParams.put("taskDateYear", taskDateYear.trim());
                queryParams.put("taskDateMonths", taskDateMonths.trim());
            }
        }
        return provider.getKhTaskList(queryParams);
    }

    public  AsmsInspectionTask findKhTaskById(String id){
        return provider.findKhTaskById(id);
    }

    public  List<AsmsInspectionAssess>  getKhPersonByTaskId(String id){
        return provider.getKhPersonByTaskId(id);
    }

    public PageInfo<Map<String, Object>> getAssessList(String token, Page page, SuperviseTaskInfo taskInfo, String dateBegin, String dateEnd,
                                                       String taskDateType, String taskDateYear, String taskDateQuarter, String taskDateMonths) {
        String org = WebUtil.getCurrentUser(token).getOrgId();
        Map<String, Object> params = new HashMap<>();
        params.put("pageOffset",page.getPageOffset());
        params.put("pageTail",page.getPageTail());
        params.put("theUserOrgId",org);
        params.put("dateBegin", dateBegin);
        params.put("dateEnd", dateEnd);
//        params.put("taskType", taskInfo.getTaskType()==null?"":taskInfo.getTaskType());
        params.put("taskDateType", taskInfo.getTaskDateType()==null?"":taskInfo.getTaskDateType());
        params.put("userName", taskInfo.getUserName()==null?"":taskInfo.getUserName().trim());
        params.put("taskResult", taskInfo.getTaskResult()==null?"":taskInfo.getTaskResult().trim());
        params.put("taskDateType",taskDateType==null?"":taskDateType);
        if(StringUtils.isNotBlank(taskDateType)){
            if("年度".equals(taskDateType.trim())){
                params.put("taskDateYear", taskDateYear.trim());
            }else if("季度".equals(taskDateType.trim())){
                params.put("taskDateYear", taskDateYear.trim());
                params.put("taskDateQuarter", taskDateQuarter.trim());
            }else if("月度".equals(taskDateType.trim())){
                params.put("taskDateYear", taskDateYear.trim());
                params.put("taskDateMonths", taskDateMonths.trim());
            }
        }
        PageInfo<Map<String, Object>> pageInfo = provider.getAssessList(params);
        pageInfo.setList(this.buidInformation(pageInfo.getList()));
        return pageInfo;
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
                Long realCount = provider.getKhRealCount(params);//实际巡查次数
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
                map.put(columnName, "未开始");
            } else if (date.getTime() > taskBeginData.getTime() && date.getTime() < taskEndTimeData.getTime()) {
                map.put(columnName, "执行中");
            } else if (date.getTime() > taskEndTimeData.getTime()) {
                if (realCount < taskCount && taskCount != 0) {
                    map.put(columnName, "已结束( 未完成 )");
                } else {
                    map.put(columnName, "已结束( 完成 )");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }

    //考核任务任务状态判断
    public String getKhTaskStatus(String taskDateType, String taskDateYear, String taskDateMonths, String taskDateQuarter) throws ParseException {
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
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date taskBeginData = simpleDateFormat.parse(beginTime.toString());
        Date taskEndTimeData = simpleDateFormat.parse(endTime.toString());
        if (date.getTime() < taskBeginData.getTime()) {
           return "未开始";
        } else if (date.getTime() > taskBeginData.getTime() && date.getTime() < taskEndTimeData.getTime()) {
           return "进行中";
        } else if (date.getTime() > taskEndTimeData.getTime()) {
            return "已结束";
        }
    return "";
    }


    //根据临时主体id获取全部产业id,由于页面使用临时主体产业字典与主体字典id不同
    public Object[] getBothEntityType(String[] entityType){
        List<String> entityTypeName = new ArrayList<>();
        List<String> entityTypeId = new ArrayList<>(Arrays.asList(entityType));
        List<SysDictData> entityTypeTempList = sysDictProvider.getDictByType(DictType.TEMPTYPE);
        List<SysDictData> entityTypeList = sysDictProvider.getDictByType(DictType.SUBJTYPE);
        for (int i = 0;i < entityTypeTempList.size();i++){
            for (int j = 0;j < entityType.length;j++){
                if(entityType[j].equals(entityTypeTempList.get(i).getId())){
                    entityTypeName.add(entityTypeTempList.get(i).getDictName());
                }
            }
        }
        for (int i = 0;i < entityTypeName.size();i++){
            for (int j = 0;j< entityTypeList.size();j++){
                if(entityTypeName.get(i).equals(entityTypeList.get(j).getDictName())){
                    entityTypeId.add(entityTypeList.get(j).getId());
                }
            }
        }
        return entityTypeId.toArray();
    }
}
