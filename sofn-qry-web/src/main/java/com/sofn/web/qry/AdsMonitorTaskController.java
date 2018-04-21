package com.sofn.web.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.qry.QueryParameter;
import com.sofn.service.qry.AdsMonitorTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 10:56
 */
@RestController
@Api(value = "监测任务查询",description = "监测任务查询")
@RequestMapping(value = "/adsMonitorTask",method = RequestMethod.POST)
public class AdsMonitorTaskController extends BaseController{

    @Autowired
    private AdsMonitorTaskService adsMonitorTaskService;

    @ApiOperation(value = "任务列表")
    @SystemControllerLog(description = "任务列表",operationType="查询")
    @RequestMapping(value = "/getRoutineMonitorList", method = RequestMethod.POST)
    @Authorization
    public Object getRoutineMonitorList(AdsMonitorTask taskObj, int start, int length,String result,String ReleaseUnit,String qiantou,String SampleLink,String DetectionOrgan,String SampleOrgan,@RequestHeader String token) {
        try{
            Assert.isNotBlank(token,"token");
            ModelMap modelMap = new ModelMap();
            CurrentUser u= WebUtil.getCurrentUser(token);
            if (u != null) {
                PageInfo<AdsMonitorTask> pageInfo = adsMonitorTaskService.getRoutineMonitorList(((start + 1) / length) + 1, length,taskObj,result,ReleaseUnit,qiantou,SampleLink,DetectionOrgan,SampleOrgan,u);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取年度列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取行业列表")
    @RequestMapping(value = "/getIndustry", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取行业列表", operationType = "查詢")
    public Object getYear() {
        try {
            List<Map<String, Object>> checkModel = adsMonitorTaskService.getIndustry();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取发布机构列表")
    @RequestMapping(value = "/getReleaseUnit", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取发布机构列表", operationType = "查詢")
    public Object getReleaseUnit() {
        try {
            List<Map<String, Object>> checkModel = adsMonitorTaskService.getReleaseUnit();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

//    @ApiOperation(value = "获取牵头单位列表") //// FIXME: 2017-04-18 牵头单位暂时不写
//    @RequestMapping(value = "/getIndustry", method = RequestMethod.POST)
//    @SystemControllerLog(description = "获取牵头单位列表", operationType = "查詢")
//    public Object getYear() {
//        try {
//            List<Map<String, Object>> checkModel = monitorTaskService.getIndustry();
//            return setSuccessModelMap(new ModelMap(), checkModel);
//        } catch (Exception e) {
//            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
//        }
//    }

    @ApiOperation(value = "获取检测环节列表")
    @RequestMapping(value = "/getSampleLink", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取检测环节列表", operationType = "查詢")
    public Object getSampleLink() {
        try {
            List<Map<String, Object>> checkModel = adsMonitorTaskService.getSampleLink();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取检测单位列表")
    @RequestMapping(value = "/getDetectionOrgan", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取检测单位列表", operationType = "查詢")
    public Object getDetectionOrgan() {
        try {
            List<Map<String, Object>> checkModel = adsMonitorTaskService.getDetectionOrgan();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取抽样单位列表")
    @RequestMapping(value = "/getSampleOrgan", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取抽样单位列表", operationType = "查詢")
    public Object getSampleOrgan() {
        try {
            List<Map<String, Object>> checkModel = adsMonitorTaskService.getSampleOrgan();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取监测任务列表
     *
     * @param monitorTaskId,sampleOrganId
     * @return
     */
    @ApiOperation(value = "获取机构任务列表")
    @RequestMapping(value = "/findAdsMonitorTaskByid", method = RequestMethod.POST)
    public Map<String, Object> findAdsMonitorTaskByid(String token, String monitorTaskId, String monitorClass) {
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        String sampleOrganId = currentUser.getOrgId();
        String sampleOrgan = currentUser.getOrgName();
        List<AdsMonitorTask> adsMonitorTaskList = adsMonitorTaskService.findAdsMonitorTaskByid(monitorTaskId, sampleOrganId, monitorClass);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("adsMonitorTask", adsMonitorTaskList);
        map.put("sampleOrgan", sampleOrgan);
        map.put("sampleOrganId", sampleOrganId);
        return map;
    }

    /**
     * 获取监测任务的机构任务列表
     *
     * @param adsOrganTask
     * @return
     */
    @ApiOperation(value = "获取监测任务的机构任务列表")
    @RequestMapping(value = "/getPageInfoByMonitorTask", method = RequestMethod.POST)
    public Object getPageInfoByMonitorTask(AdsOrganTask adsOrganTask, int start, int length) {
        try {
            PageInfo<AdsOrganTask> pageInfo = adsMonitorTaskService.getPageInfoByMonitorTask(adsOrganTask, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取专项监测任务列表")
    @SystemControllerLog(description = "获取专项监测任务列表",operationType="查询")
    @RequestMapping(value = "/getSpecialMonitorList")
    public Object getSpecialMonitorList(AsmsSpecialMonitor r, QueryParameter p) {
        PageInfo<List<Map<String, Object>>> data = adsMonitorTaskService.getSpecialMonitorList(r, p);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data",data);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取监督抽查任务列表")
    @SystemControllerLog(description = "获取监督抽查任务列表",operationType="查询")
    @RequestMapping(value = "/getCheckTaskList")
    public Object getCheckTaskList(AsmsCheckTask r, QueryParameter p) {
        PageInfo<List<Map<String, Object>>> data = adsMonitorTaskService.getCheckTaskList(r, p);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data",data);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "任务列表")
    @SystemControllerLog(description = "任务列表",operationType = "查询")
    @RequestMapping(value = "/getReCheckTaskList")
    public Object getReCheckTaskList(AsmsRecheckTask r, String dateBegin, String dateEnd, int start, int length) {
        PageInfo<List<Map<String, Object>>> data = adsMonitorTaskService.getReCheckTaskList(r, dateBegin, dateEnd, ((start + 1) / length) + 1, length);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data",data);
        return setSuccessModelMap(modelMap);
    }
}
