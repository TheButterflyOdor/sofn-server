package com.sofn.web.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.DownloadExcelUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AsmsBaseInspection;
import com.sofn.model.generator.AsmsInspectionAssess;
import com.sofn.model.generator.AsmsInspectionTask;
import com.sofn.model.qry.EnforceLawPersonnelDto;
import com.sofn.model.qry.QueryParameter;
import com.sofn.model.qry.SuperviseTaskInfo;
import com.sofn.service.qry.SupervisionInspectionService;
import com.sofn.util.Page;
import com.sofn.util.Translate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author sofn
 * @version 2017年03月23日 上午 10:54
 */
@RestController
@Api(value = "监督抽查查询",description = "监督抽查查询")
@RequestMapping(value = "/supervisionInspection",method = RequestMethod.POST)
public class SupervisionInspectionController extends BaseController{

    @Autowired
    private SupervisionInspectionService supervisionInspectionService;

    @ApiOperation(value = "根据token获取用户行业角色信息")
    @SystemControllerLog(description = "根据token获取用户行业角色信息",operationType = "查询")
    @RequestMapping(value = "/getUserIndustryRoleByToken")
    @Authorization
    public Object getUserIndustryRoleByToken(@RequestHeader String token){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null||user.getId().isEmpty()){
            return setModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        Set set = supervisionInspectionService.getUserIndustryRoleByToken(user.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("industryRole",set);
        return setModelMap(modelMap,HttpCode.OK);
    }

    @ApiOperation(value = "获取基地巡查列表")
    @SystemControllerLog(description = "获取基地巡查列表",operationType = "查询")
    @RequestMapping(value = "/getBaseInspectionAllList",method = RequestMethod.POST)
    @Authorization
    public Object getBaseInspectionAllList(@RequestHeader String token, AsmsBaseInspection asmsBaseInspection , String dateBegin, String dateEnd,
                                           Page page, String entityIndustry, String entityScale,String entityType, String enterpriseName,
                                           String area, String createDateBegin,String createDateEnd){
        PageInfo pageInfo = supervisionInspectionService.getBaseInspectionAllList(asmsBaseInspection,dateBegin,dateEnd,
                page,entityIndustry,entityScale,entityType,enterpriseName,area, createDateBegin, createDateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
          modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "基地巡查数据导出")
    @SystemControllerLog(description = "基地巡查数据导出", operationType = "导出")
    @RequestMapping(value = "/exportBIList")
    @Authorization
    public void exportBIList(String token, AsmsBaseInspection asmsBaseInspection , String dateBegin, String dateEnd, Page page,
                             String entityIndustry, String entityScale,String entityType,
                             String enterpriseName, String area, String createDateBegin,String createDateEnd){
        page.setLength(1000000000);
        page.setStart(0);
        PageInfo pageInfo = supervisionInspectionService.getBaseInspectionAllList(asmsBaseInspection,dateBegin,dateEnd,
                page,entityIndustry,entityScale,entityType,enterpriseName,area, createDateBegin, createDateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("主体名称");
        dateTitle.add("所属行业");
        dateTitle.add("区域");
        dateTitle.add("联系人");
        dateTitle.add("任务类型");
        dateTitle.add("巡查结果");
        dateTitle.add("巡查时间");
        dateTitle.add("巡查员");
        dateTitle.add("创建时间");
        datas.add(dateTitle);
        for (Object o :pageInfo.getList()){
            HashMap m= (HashMap) o;
             List<String> data = new LinkedList<>();
            data.add(m.get("ENTERPRISE_NAME").toString());
            data.add(m.get("ENTITY_INDUSTRY_NAME").toString());
            data.add(Translate.getArae(m.get("AREA").toString()));
            if(m.get("CONTACT_NAME") != null){
            data.add(m.get("CONTACT_NAME").toString());
            }else {
                data.add(" ");
            }
            data.add(m.get("INSPECTION_TYPE_NAME")!=null?m.get("INSPECTION_TYPE_NAME").toString():"");
            data.add(Translate.getInspectionResult(m.get("INSPECTION_RESULT").toString()));
            data.add(m.get("INSPECTION_TIME").toString());
            data.add(m.get("INSPECTION_USER_NAME").toString());
            data.add(m.get("CREATE_TIME").toString());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("基地巡查",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "获取考核任务列表")
    @SystemControllerLog(description = "获取考核任务列表",operationType="查询")
    @RequestMapping(value = "/getInspectionTaskList")
    @Authorization
    public Object getInspectionTaskList(@RequestHeader String token, AsmsInspectionTask task, QueryParameter p) {
        PageInfo returnDate = supervisionInspectionService.getInspectionTaskList(task,p);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data",returnDate);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "巡查人员考核列表")
    @SystemControllerLog(description = "巡查人员考核列表",operationType="查询")
    @RequestMapping(value = "/getInspectionManAssessList")
    @Authorization
    public Object getInspectionManAssessList(@RequestHeader String token, SuperviseTaskInfo taskInfo, QueryParameter p) {
        //固定项  以巡查人员关联表为主表，巡查报告表为附表，根据巡查人员、任务期限检索出实际巡查次数
        //列表展示限制 巡查人员id、任务类型、考核类型、考核类型、任务期限
        //省、市、县、任务类型、任务期限、任务状态、查找内容(查询)
        PageInfo<List<Map<String, Object>>> returnDate = supervisionInspectionService.getInspectionManAssessList(taskInfo,p);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data",returnDate);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取单个基地巡查详情")
    @SystemControllerLog(description = "获取单个基地巡查详情",operationType = "查询")
    @RequestMapping(value = "/findBaseInspectionById",method = RequestMethod.POST)
    @Authorization
    public Object findBaseInspectionById(@RequestHeader String token, @RequestBody AsmsBaseInspection asmsBaseInspection){
        asmsBaseInspection = supervisionInspectionService.findBaseInspectionById(asmsBaseInspection.getId());
        return setSuccessModelMap(new ModelMap(),asmsBaseInspection);
    }
    @ApiOperation(value = "获取执法人员列表")
    @SystemControllerLog(description = "获取执法人员列表",operationType = "查询")
    @RequestMapping(value = "/getEnforceLawPersonList")
    @Authorization
    public Object getEnforceLawPersonList(@RequestHeader String token, EnforceLawPersonnelDto enforceLawPersonnelDto, Page page){
        PageInfo pageInfo = supervisionInspectionService.getEnforceLawPersonList(enforceLawPersonnelDto,page);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "执法人员数据导出")
    @SystemControllerLog(description = "执法人员数据导出", operationType = "导出")
    @RequestMapping(value = "/exportELPList")
    @Authorization
    public void exportELPList(String token, EnforceLawPersonnelDto enforceLawPersonnelDto, Page page){
        page.setLength(1000000000);
        page.setStart(0);
        PageInfo pageInfo = supervisionInspectionService.getEnforceLawPersonList(enforceLawPersonnelDto,page);
        page.setRecordsTotal(pageInfo.getTotal());
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("姓名");
        dateTitle.add("职务");
        dateTitle.add("电话");
        dateTitle.add("所属单位");
        dateTitle.add("行政区域");
        datas.add(dateTitle);
        for (Object o :pageInfo.getList()){
            EnforceLawPersonnelDto m= (EnforceLawPersonnelDto) o;
            List<String> data = new LinkedList<>();
            data.add(m.getUserName());
            data.add(m.getUserPost());
            data.add(m.getUserPhone());
            data.add(m.getName());
            data.add(m.getArea());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("执法人员",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "获取执法人员详情")
    @SystemControllerLog(description = "获取执法人员详情",operationType = "查询")
    @RequestMapping(value = "/findEnforceLawPersonById")
    @Authorization
    public Object findEnforceLawPersonById(@RequestHeader String token, @RequestBody EnforceLawPersonnelDto enforceLawPersonnelDto){
        enforceLawPersonnelDto = supervisionInspectionService.findEnforceLawPersonById(enforceLawPersonnelDto);
        return setSuccessModelMap(new ModelMap(),enforceLawPersonnelDto);
    }

    @ApiOperation(value = "根据查询参数获取考核任务")
    @SystemControllerLog(description = "根据查询参数获取考核任务",operationType = "查询")
    @RequestMapping(value = "/getKhTaskList")
    @Authorization
    public Object getKhTaskList(@RequestHeader String token,Page page, String taskDateType, String taskDateYear,
                                String taskDateMonths, String taskDateQuarter,String taskType){
        PageInfo pageInfo = supervisionInspectionService.getKhTaskList(token, page, taskDateType, taskDateYear, taskDateMonths, taskDateQuarter, taskType);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据id查询任务")
    @SystemControllerLog(description = "根据id查询任务", operationType = "查询")
    @RequestMapping(value = "/findKhTaskById")
    @Authorization
    public Object findKhTaskById(@RequestHeader String token, String id) {
        AsmsInspectionTask inspectionTask = supervisionInspectionService.findKhTaskById(id);
        List<AsmsInspectionAssess> inspectionAssess = supervisionInspectionService.getKhPersonByTaskId(id);
        Map<String, Object> map = new HashMap<>();
        map.put("inspectionTask", inspectionTask);
        map.put("inspectionAssess", inspectionAssess);
        return setSuccessModelMap(new ModelMap(), map);
    }

    @ApiOperation(value = "巡查人员考核列表")
    @SystemControllerLog(description = "巡查人员考核列表", operationType = "查询")
    @RequestMapping(value = "/getAssessList")
    @Authorization
    public Object getAssessList(@RequestHeader String token,Page page, SuperviseTaskInfo taskInfo, String dateBegin, String dateEnd,
                                String taskDateType, String taskDateYear, String taskDateQuarter, String taskDateMonths) {
        return setSuccessModelMap(new ModelMap(), supervisionInspectionService.getAssessList(token, page, taskInfo, dateBegin, dateEnd,
                taskDateType, taskDateYear, taskDateQuarter, taskDateMonths));
    }

    @ApiOperation(value = "考核任务导出")
    @SystemControllerLog(description = "考核任务导出", operationType = "导出")
    @RequestMapping(value = "/exportKhTaskList")
    @Authorization
    public void exportKhTaskList(String token,Page page, String taskDateType, String taskDateYear,
                                 String taskDateMonths, String taskDateQuarter,String taskType) throws ParseException {
        page.setLength(1000000000);
        page.setStart(0);
        PageInfo pageInfo = supervisionInspectionService.getKhTaskList(token, page, taskDateType, taskDateYear, taskDateMonths, taskDateQuarter, taskType);
        page.setRecordsTotal(pageInfo.getTotal());
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("巡查区域");
        dateTitle.add("考核类型");
        dateTitle.add("考核时间");
        dateTitle.add("任务类型");
        dateTitle.add("巡查次数");
        dateTitle.add("创建日期");
        dateTitle.add("任务状态");
        datas.add(dateTitle);
        String taskTime = "";
        for (Object o :pageInfo.getList()){
            HashMap m= (HashMap) o;
            List<String> data = new LinkedList<>();
            data.add(Translate.getArae(m.get("INSPECTIONAREAID")==null?"":m.get("INSPECTIONAREAID").toString()));
            taskDateType = m.get("TASKDATETYPE")==null?"":m.get("TASKDATETYPE").toString();
            taskDateYear = m.get("TASKDATEYEAR")==null?"":m.get("TASKDATEYEAR").toString();
            taskDateQuarter = m.get("TASKDATEQUARTER")==null?"":m.get("TASKDATEQUARTER").toString();
            taskDateMonths = m.get("TASKDATEMONTHS")==null?"":m.get("TASKDATEMONTHS").toString();
            data.add(taskDateType);
            if("年度".equals(taskDateType.trim())){
                taskTime = taskDateYear+"年";
            }else if("季度".equals(taskDateType.trim())){
                taskTime = taskDateYear+taskDateQuarter+"季度";
            }else if("月度".equals(taskDateType.trim())){
                taskTime = taskDateYear+"年"+taskDateMonths+"月";
            }
            data.add(taskTime);
            data.add(m.get("TASKTYPENAME")==null?"":m.get("TASKTYPENAME").toString());
            data.add(m.get("INSPECTIONCOUNT")==null?"":m.get("INSPECTIONCOUNT").toString());
            data.add(m.get("CREATETIME")==null?"":m.get("CREATETIME").toString());
            data.add(supervisionInspectionService.getKhTaskStatus(taskDateType,taskDateYear,taskDateMonths,taskDateQuarter));
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("考核任务",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "巡查人员考核导出")
    @SystemControllerLog(description = "巡查人员考核导出", operationType = "导出")
    @RequestMapping(value = "/exportAssessList")
    @Authorization
    public void exportAssessList(String token,Page page, SuperviseTaskInfo taskInfo, String dateBegin, String dateEnd,
                                 String taskDateType, String taskDateYear, String taskDateQuarter, String taskDateMonths) throws ParseException {
        page.setLength(1000000000);
        page.setStart(0);
        PageInfo pageInfo = supervisionInspectionService.getAssessList(token, page, taskInfo, dateBegin, dateEnd,
                taskDateType, taskDateYear, taskDateQuarter, taskDateMonths);
        page.setRecordsTotal(pageInfo.getTotal());
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("巡查人员");
        dateTitle.add("考核类型");
        dateTitle.add("考核时间");
        dateTitle.add("设定次数");
        dateTitle.add("所属区域");
        dateTitle.add("实际次数");
        dateTitle.add("任务状态");
        dateTitle.add("考核结果");
        datas.add(dateTitle);
        String taskTime = "";
        for (Object o :pageInfo.getList()){
            HashMap m= (HashMap) o;
            List<String> data = new LinkedList<>();
            data.add(m.get("USERNAME")==null?"":m.get("USERNAME").toString());
            taskDateType = m.get("TASKDATETYPE")==null?"":m.get("TASKDATETYPE").toString();
            taskDateYear = m.get("TASKDATEYEAR")==null?"":m.get("TASKDATEYEAR").toString();
            taskDateQuarter = m.get("TASKDATEQUARTER")==null?"":m.get("TASKDATEQUARTER").toString();
            taskDateMonths = m.get("TASKDATEMONTHS")==null?"":m.get("TASKDATEMONTHS").toString();
            data.add(taskDateType);
            if("年度".equals(taskDateType.trim())){
                taskTime = taskDateYear+"年";
            }else if("季度".equals(taskDateType.trim())){
                taskTime = taskDateYear+taskDateQuarter+"季度";
            }else if("月度".equals(taskDateType.trim())){
                taskTime = taskDateYear+"年"+taskDateMonths+"月";
            }
            data.add(taskTime);
            data.add(m.get("INSPECTIONCOUNT")==null?"":m.get("INSPECTIONCOUNT").toString());
            data.add(Translate.getArae(m.get("INSPECTIONAREAID")==null?"":m.get("INSPECTIONAREAID").toString()));
            data.add(m.get("INSPECTIONREALCOUNT")==null?"":m.get("INSPECTIONREALCOUNT").toString());
            data.add(m.get("TASKSTATUS")==null?"":m.get("TASKSTATUS").toString());
            data.add(m.get("TASKRESULT")==null?"未审核":m.get("TASKRESULT").toString());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("巡查人员考核",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
