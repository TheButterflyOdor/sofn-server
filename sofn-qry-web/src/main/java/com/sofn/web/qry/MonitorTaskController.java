package com.sofn.web.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.DownloadExcelUtil;
import com.sofn.model.generator.*;
import com.sofn.model.qry.QueryParameter;
import com.sofn.service.qry.MonitorTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * @author sofn
 * @version 2017年03月23日 上午 10:56
 */
@RestController
@Api(value = "监测任务查询",description = "监测任务查询")
@RequestMapping(value = "/monitorTask",method = RequestMethod.POST)
public class MonitorTaskController extends BaseController {

    @Autowired
    private MonitorTaskService monitorTaskService;

    @ApiOperation(value = "任务列表")
    @SystemControllerLog(description = "任务列表", operationType = "查询")
    @RequestMapping(value = "/getRoutineMonitorList")
    @Authorization
    public Object getRoutineMonitorList(@RequestHeader String token,AsmsRoutineMonitor r, String leadUnitId,QueryParameter p, String areaId) {
        PageInfo<List<Map<String, Object>>> data = monitorTaskService.getRoutineMonitorList(token, r,leadUnitId, p, areaId);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", data);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "例行监测数据导出")
    @SystemControllerLog(description = "例行监测数据导出", operationType = "导出")
    @RequestMapping(value = "/exportRmList")
    @Authorization
    public void exportRmList(String token,AsmsRoutineMonitor r,String leadUnitId,QueryParameter p){
        p.setLength(1000000000);
        PageInfo<List<Map<String, Object>>> pageInfo = monitorTaskService.getRoutineMonitorList(token, r, leadUnitId, p, null);
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("任务名称");
        dateTitle.add("监测类型");
        dateTitle.add("年度");
        dateTitle.add("监测批次");
        dateTitle.add("文件号");
        dateTitle.add("开始时间");
        dateTitle.add("结束时间");
        dateTitle.add("任务状态");
        datas.add(dateTitle);
        for (Object o :pageInfo.getList()){
            HashMap m= (HashMap) o;
            List<String> data = new LinkedList<>();
             data.add(m.get("RMNAME").toString());
             data.add(m.get("RMTYPE").toString());
             data.add(m.get("RMYEAR").toString());
             data.add(m.get("RMBATCH").toString());//监测批次
             data.add(m.get("RMFILENUM").toString());
             data.add(m.get("RMDATEBEGIN").toString().substring(0,10));
             data.add(m.get("RMDATEEND").toString().substring(0,10));
            //状态
            if("0".equals(m.get("RMSTATE").toString())){//未发布
                data.add("未发布");
            }else if("1".equals(m.get("RMSTATE").toString())){//已发布
                data.add("已发布");
            }else if("2".equals(m.get("RMSTATE").toString())){//已废止
                data.add("已废止");
            }else{
                data.add("");
            }
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("例行监测",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "获取专项监测任务列表")
    @SystemControllerLog(description = "获取专项监测任务列表", operationType = "查询")
    @RequestMapping(value = "/getSpecialMonitorList")
    @Authorization
    public Object getSpecialMonitorList(@RequestHeader String token, AsmsSpecialMonitor r, String leadUnitId,QueryParameter p) {
        PageInfo<List<Map<String, Object>>> data = monitorTaskService.getSpecialMonitorList(token,r,leadUnitId,p);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", data);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "专项监测数据导出")
    @SystemControllerLog(description = "专项监测数据导出", operationType = "导出")
    @RequestMapping(value = "/exportSmList")
    @Authorization
    public void exportSmList(String token,AsmsSpecialMonitor r,String leadUnitId,QueryParameter p){
        p.setLength(1000000000);
        PageInfo<List<Map<String, Object>>> pageInfo = monitorTaskService.getSpecialMonitorList(token, r,leadUnitId,p);
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("任务名称");
        dateTitle.add("监测类型");
        dateTitle.add("年度");
        dateTitle.add("监测批次");
        dateTitle.add("文件号");
        dateTitle.add("开始时间");
        dateTitle.add("结束时间");
        dateTitle.add("任务状态");
        datas.add(dateTitle);
        for (Object o :pageInfo.getList()){
            HashMap m= (HashMap) o;
            List<String> data = new LinkedList<>();
            data.add(m.get("SMNAME").toString());
            data.add(m.get("SMTYPE").toString());
            data.add(m.get("SMYEAR").toString());
            data.add(m.get("SMBATCH").toString());//监测批次
            data.add(m.get("SMFILENUM").toString());
            data.add(m.get("SMDATEBEGIN").toString().substring(0,10));
            data.add(m.get("SMDATEEND").toString().substring(0,10));
            //状态
            if("0".equals(m.get("SMSTATE").toString())){//未发布
                data.add("未发布");
            }else if("1".equals(m.get("SMSTATE").toString())){//已发布
                data.add("已发布");
            }else if("2".equals(m.get("SMSTATE").toString())){//已废止
                data.add("已废止");
            }else{
                data.add("");
            }
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("专项监测",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "获取监督抽查任务列表")
    @SystemControllerLog(description = "获取监督抽查任务列表", operationType = "查询")
    @RequestMapping(value = "/getCheckTaskList")
    @Authorization
    public Object getCheckTaskList(@RequestHeader String token,AsmsCheckTask r, QueryParameter p) {
        PageInfo<List<Map<String, Object>>> data = monitorTaskService.getCheckTaskList(token, r, p);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", data);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "监督抽查数据导出")
    @SystemControllerLog(description = "监督抽查数据导出", operationType = "导出")
    @RequestMapping(value = "/exportCKList")
    @Authorization
    public void exportCKList(String token,AsmsCheckTask r,QueryParameter p){
        p.setLength(1000000000);
        PageInfo<List<Map<String, Object>>> pageInfo = monitorTaskService.getCheckTaskList(token, r, p);
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("级别");
        dateTitle.add("任务名称");
        dateTitle.add("监测类型");
        dateTitle.add("年度");
        dateTitle.add("开始时间");
        dateTitle.add("结束时间");
        dateTitle.add("任务状态");
        datas.add(dateTitle);
        for (Object o :pageInfo.getList()){
            HashMap m= (HashMap) o;
            List<String> data = new LinkedList<>();
            //级别
            switch (m.get("TASKLEVEL")==null?"":m.get("TASKLEVEL").toString().trim()){
                case "0"://部级
                   data.add("部级");
                    break;
                case "1"://省级
                    data.add("省级");
                    break;
                case "2"://市级
                    data.add("市级");
                    break;
                case "3"://县级
                    data.add("县级");
                    break;
                default:
                    data.add("");
                    break;
            }
            data.add(m.get("TASKNAME")==null?"":m.get("TASKNAME").toString());
            data.add(m.get("TASKTYPE")==null?"":m.get("TASKTYPE").toString());
            data.add(m.get("TASKYEAR")==null?"":m.get("TASKYEAR").toString());
            data.add(m.get("TASKBEGINTIME")==null?"":m.get("TASKBEGINTIME").toString().substring(0,10));
            data.add(m.get("TASKENDTIME")==null?"":m.get("TASKENDTIME").toString().substring(0,10));
            //状态
            if("0".equals(m.get("STATE")==null?"":m.get("STATE").toString())){//未发布
                data.add("未发布");
            }else if("1".equals(m.get("STATE")==null?"":m.get("STATE").toString())){//已发布
                data.add("已发布");
            }else if("2".equals(m.get("STATE")==null?"":m.get("STATE").toString())){//已废止
                data.add("已废止");
            }else{
                data.add("");
            }
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("监督抽查",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "任务列表")
    @SystemControllerLog(description = "任务列表", operationType = "查询")
    @RequestMapping(value = "/getReCheckTaskList")
    @Authorization
    public Object getReCheckTaskList(@RequestHeader String token ,AsmsRecheckTask r, String dateBegin, String dateEnd, int start, int length) {
        PageInfo<List<Map<String, Object>>> data = monitorTaskService.getReCheckTaskList(token,r, dateBegin, dateEnd, ((start + 1) / length) + 1, length);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", data);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "复检任务数据导出")
    @SystemControllerLog(description = "复检任务数据导出", operationType = "导出")
    @RequestMapping(value = "/exportReCKList")
    @Authorization
    public void exportReCKList(String token,AsmsRecheckTask r, String dateBegin, String dateEnd, int start, int length){
        length=1000000000;
        start=0;
        PageInfo<List<Map<String, Object>>> pageInfo = monitorTaskService.getReCheckTaskList(token,r, dateBegin, dateEnd, ((start + 1) / length) + 1, length);
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("任务名称");
        dateTitle.add("年度");
        dateTitle.add("复检监测单位");
        dateTitle.add("开始时间");
        dateTitle.add("结束时间");
        dateTitle.add("任务状态");
        datas.add(dateTitle);
        for (Object o :pageInfo.getList()){
            HashMap m= (HashMap) o;
            List<String> data = new LinkedList<>();
            data.add(m.get("RECHECKTASKNAME")==null?"":m.get("RECHECKTASKNAME").toString());
            data.add(m.get("RECHECKTASKYEAR")==null?"":m.get("RECHECKTASKYEAR").toString());
            data.add(m.get("RECHECKUNITNAME")==null?"":m.get("RECHECKUNITNAME").toString());
            data.add(m.get("RECHECKTASKBEGIN")==null?"":m.get("RECHECKTASKBEGIN").toString().substring(0,10));
            data.add(m.get("RECHECKTASKEND")==null?"":m.get("RECHECKTASKEND").toString().substring(0,10));
            //状态
            if("0".equals(m.get("STATE")==null?"":m.get("STATE").toString())){//未发布
                data.add("未发布");
            }else if("1".equals(m.get("STATE")==null?"":m.get("STATE").toString())){//已发布
                data.add("已发布");
            }else if("2".equals(m.get("STATE")==null?"":m.get("STATE").toString())){//已废止
                data.add("已废止");
            }else{
                data.add("");
            }
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("复检任务",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "根据id查询监督抽查任务")
    @SystemControllerLog(description = "根据id查询监督抽查任务", operationType = "查询")
    @RequestMapping(value = "/getTaskById")
    @Authorization
    public Object getTaskById(@RequestHeader String token,@RequestBody AsmsCheckTask r) {
        AsmsCheckTask o = monitorTaskService.getCheckTaskById(r.getId());
        List<AsmsCheckBearUnit> list = monitorTaskService.getUnitByTaskId(r.getId());
        List<AsmsJcStandard> jcList = monitorTaskService.getJcListByTaskId(r.getId());
        List<AsmsPdStandard> pdList = monitorTaskService.getPdListByTaskId(r.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", o);
        modelMap.addAttribute("speList", list);
        modelMap.addAttribute("jcList", jcList);
        modelMap.addAttribute("pdList", pdList);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据任务id查询抽查对象列表")
    @SystemControllerLog(description = "根据任务id查询抽查对象列表", operationType = "查询")
    @RequestMapping(value = "/getObjById")
    @Authorization
    public Object getObjById(@RequestHeader String token,@RequestBody AsmsMonitorObject r) {
        List<Map<String, Object>> o = monitorTaskService.getObjById(r.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", o);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据id查询复检任务")
    @SystemControllerLog(description = "根据id查询任务", operationType = "查询")
    @RequestMapping(value = "/getReTaskById")
    @Authorization
    public Object getReTaskById(@RequestHeader String token,@RequestBody AsmsRecheckTask r) {
        AsmsRecheckTask o = monitorTaskService.getReCheckTaskById(r.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", o);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据id查询复检关联对象")
    @SystemControllerLog(description = "根据id查询复检关联对象", operationType = "查询")
    @RequestMapping(value = "/getReObjById")
    @Authorization
    public Object getReObjById(@RequestHeader String token,@RequestBody AsmsRecheckTask r) {
        List<Map<String, Object>> o = monitorTaskService.getReObjById(r.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", o);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据id查询例行监测任务")
    @SystemControllerLog(description = "根据id查询例行监测任务", operationType = "查询")
    @RequestMapping(value = "/getRMTaskById")
    @Authorization
    public Object getRMTaskById(@RequestHeader String token,@RequestBody AsmsRoutineMonitor r) {
        AsmsRoutineMonitor o = monitorTaskService.getRoutineMonitorById(r.getId());
        List<AsmsRoutineLeadUnit> list = monitorTaskService.getRmUnitByTaskId(r.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", o);
        modelMap.put("rouList", list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据id查询专项监测任务")
    @SystemControllerLog(description = "根据id查询专项监测任务", operationType = "查询")
    @RequestMapping(value = "/getSMTaskById")
    @Authorization
    public Object getSMTaskById(@RequestHeader String token,@RequestBody AsmsSpecialMonitor r) {
        AsmsSpecialMonitor o = monitorTaskService.getSpecialMonitorById(r.getId());
        List<AsmsSpecialLeadUnit> list = monitorTaskService.getSmUnitByTaskId(r.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", o);
        modelMap.put("speList", list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "报告列表")
    @SystemControllerLog(description = "报告列表", operationType = "查询")
    @RequestMapping(value = "/listCheckTaskReport")
    @Authorization
    public Object listCheckTaskReport(@RequestHeader String token, AsmsCheckTaskReport r, int start, int length, String CdUnitId, String taskLevel) {
        PageInfo<List<Map<String, Object>>> data = monitorTaskService.listCheckTaskReport(r, ((start + 1) / length) + 1, length, CdUnitId, taskLevel);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", data);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据监测信息获取检测详情")
    @SystemControllerLog(description = "根据监测信息获取检测详情", operationType = "查询")
    @RequestMapping(value = "/getReportByJcInfo")
    @Authorization
    public Object getReportByJcInfo(@RequestHeader String token, AdsInfoProject r, int start, int length) {
        PageInfo<AdsInfoProject> data = monitorTaskService.getReportByJcInfo(r, ((start + 1) / length) + 1, length);
        List<Map<String, Object>> jbInfo = monitorTaskService.getJbInfoByJcId(r);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", data);
        modelMap.put("jbInfo", jbInfo);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据报告获取监测信息列表")
    @SystemControllerLog(description = "根据报告获取监测信息列表", operationType = "查询")
    @RequestMapping(value = "/getJclistByReport")
    @Authorization
    public Object getJclistByReport(@RequestHeader String token,AsmsCheckTaskReport r, int start, int length) {
        if (!StringUtil.isNotBlank(r.getOrganTaskId())) {
            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
        }
        PageInfo<AdsCheckInfo> data = monitorTaskService.getJclistByReport(r, ((start + 1) / length) + 1, length);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", data);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "跟据id获取抽样单详情")
    @SystemControllerLog(description = "跟据id获取抽样单详情", operationType = "查询")
    @RequestMapping(value = "/getTaskSampleById")
    @Authorization
    public Object getTaskSampleById(@RequestHeader String token,@RequestBody AdsMonitorSample r) {
        return setSuccessModelMap(new ModelMap(), monitorTaskService.getTaskSampleById(r));
    }

    @ApiOperation(value = "抽样单列表[监督抽查]")
    @SystemControllerLog(description = "抽样单列表[监督抽查]", operationType = "查询")
    @RequestMapping(value = "/getTaskSampleListByJcInfo")
    @Authorization
    public Object getTaskSampleListByJcInfo(@RequestHeader String token, AsmsCheckTask r, QueryParameter q, String taskObjId, String JdccCdUnit, String sonTaskId) {
        PageInfo<AdsMonitorSample> data = monitorTaskService.getTaskSampleListByJcInfo(r, q, taskObjId, JdccCdUnit, sonTaskId);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "抽样单列表[例行监测、专项检测]")
    @SystemControllerLog(description = "抽样单列表[例行监测、专项检测]", operationType = "查询")
    @RequestMapping(value = "/getZLTaskSampleListByJcInfo")
    @Authorization
    public Object getZLTaskSampleListByJcInfo(@RequestHeader String token, AsmsCheckTask r, QueryParameter q) {
        PageInfo<AdsMonitorSample> data = monitorTaskService.getZLTaskSampleListByJcInfo(r, q);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "跟据父级任务id和承担单位id获取子任务列表")
    @SystemControllerLog(description = "跟据父级任务id和承担单位id获取子任务监测对象", operationType = "查询")
    @RequestMapping(value = "/getTasksByCreateOrgIdAndParentTaskId")
    @Authorization
    public Object getTasksByCreateOrgIdAndParentTaskId(@RequestHeader String token,@RequestBody AsmsCheckTask r) {
        List<AsmsCheckTask> data = monitorTaskService.getTasksByCreateOrgIdAndParentTaskId(r);
        return setSuccessModelMap(new ModelMap(), data);
    }
    @ApiOperation(value ="获取现有例行监测任务下的所有创建(发布)机构")
    @SystemControllerLog(description = "获取现有例行监测任务下的所有创建(发布)机构", operationType = "查询")
    @RequestMapping(value = "/getRmOrgList")
    @Authorization
    public Object getRmOrgList(@RequestHeader String token){
        List<AsmsRoutineMonitor> data= monitorTaskService.getRmOrgList(token);
        return setSuccessModelMap(new ModelMap(),data);
    }

    @ApiOperation(value ="例行监测查询已有任务所有的牵头单位信息")
    @SystemControllerLog(description = "例行监测查询已有任务所有的牵头单位信息", operationType = "查询")
    @RequestMapping(value = "/getRmLeadUnitList")
    @Authorization
    public Object getRmLeadUnitList(@RequestHeader String token){
        List<AsmsRoutineLeadUnit> data = monitorTaskService.getRmLeadUnitList(token);
        return setSuccessModelMap(new ModelMap(),data);
    }


    @ApiOperation(value ="获取现有专项监测任务下的所有创建(发布)机构")
    @SystemControllerLog(description = "获取现有专项监测任务下的所有创建(发布)机构", operationType = "查询")
    @RequestMapping(value = "/getSpecialRmOrgList")
    @Authorization
    public Object getSpecialRmOrgList(@RequestHeader String token){
        List<AsmsSpecialMonitor> data= monitorTaskService.getSpecialRmOrgList(token);
        return setSuccessModelMap(new ModelMap(),data);
    }

    @ApiOperation(value ="专项监测查询已有任务所有的牵头单位信息")
    @SystemControllerLog(description = "专项监测查询已有任务所有的牵头单位信息", operationType = "查询")
    @RequestMapping(value = "/getSpecialRmLeadUnitList")
    @Authorization
    public Object getSpecialRmLeadUnitList(@RequestHeader String token){
        List<AsmsSpecialLeadUnit> data = monitorTaskService.getSpecialRmLeadUnitList(token);
        return setSuccessModelMap(new ModelMap(),data);
    }

    @ApiOperation(value = "获取例行检测报告列表")
    @SystemControllerLog(description = "获取例行检测报告列表", operationType = "查询")
    @RequestMapping(value = "/getPageInfoAdsFileBySupId")
    @Authorization
    public Object getPageInfoAdsFileBySupId(@RequestHeader String token,AdsFile adsFile, String taskId, int start, int length) {
        PageInfo<List<AdsFile>> data = monitorTaskService.getPageInfoAdsFileBySupId(adsFile,taskId,((start+1)/length)+1,length);
        return setSuccessModelMap(new ModelMap(), data);
    }
}
