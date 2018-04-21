package com.sofn.web.ales;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.service.ales.AlesEntrustDetectionService;
import com.sofn.service.ales.AlesPdStandardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 委托检测任务
 *
 * @author LiBing
 */
@RestController
@Api(value = "委托检测任务", description = "委托检测任务")
@RequestMapping(value = "/llesEntrustDetection", method = RequestMethod.POST)
public class AlesEntrustDetectionController extends BaseController {
    @Autowired
    private AlesEntrustDetectionService service;
    @Autowired
    private AlesPdStandardService pdStandardService;//判定标准

    @ApiOperation(value = "新增任务")
    @SystemControllerLog(description = "新增任务", operationType = "新增")
    @Authorization
    @RequestMapping(value = "/add")
    public Object add(AlesEntrustDetection entrustDetection, String token, String pdStandard) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        AlesEntrustDetection data = service.addTask(entrustDetection, token);
        if (StringUtil.isNotBlank(data.getId())) {
            List<AlesPdStandard> alesPdStandards = JSONObject.parseArray(pdStandard, AlesPdStandard.class);
            if (alesPdStandards != null && !alesPdStandards.isEmpty()) {
                for (AlesPdStandard alesPdStandard : alesPdStandards) {
                    alesPdStandard.setTaskId(data.getId());
                    pdStandardService.add(alesPdStandard);
                }
            }
        }
        return setSuccessModelMap(new ModelMap(), data);
    }

    /**
     *
     * @param entrustDetection
     * @param pdStandard
     * @return
     */
    @ApiOperation(value = "修改任务")
    @SystemControllerLog(description = "修改任务", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/update")
    public Object update(AlesEntrustDetection entrustDetection, String pdStandard,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        service.updateTask(entrustDetection);
        List<AlesPdStandard> alesPdStandards = JSONObject.parseArray(pdStandard, AlesPdStandard.class);
        pdStandardService.delByTaskId(entrustDetection.getId());
        for (AlesPdStandard alesPdStandard : alesPdStandards) {
            if (alesPdStandard != null) {
                alesPdStandard.setTaskId(entrustDetection.getId());
                pdStandardService.add(alesPdStandard);
            }
        }
        return setSuccessModelMap(new ModelMap(), entrustDetection);
    }

    @ApiOperation(value = "获取检测标准")
    @SystemControllerLog(description = "获取检测标准", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getPageInfo")
    public Object getPageInfo(AdsModelCheckStandard modelCheckStandard, int start, int length,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.getPageInfo(modelCheckStandard, ((start + 1) / length) + 1, length));
    }

    @ApiOperation(value = "根据id查询任务")
    @SystemControllerLog(description = "根据id查询任务", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getTaskById")
    public Object getTaskById(@RequestBody AlesEntrustDetection entrustDetection,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        AlesEntrustDetection alesEntrustDetection = service.queryById(entrustDetection.getId());
        List<AlesPdStandard> alesPdStandards = pdStandardService.getListByTaskId(entrustDetection.getId());
        Map<String, Object> map = new HashMap<>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", alesEntrustDetection);
        map.put("standards", alesPdStandards);
        return map;
    }

    @ApiOperation(value = "根据id查询关联对象")
    @SystemControllerLog(description = "根据id查询关联对象", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getObjById")
    public Object getObjById(@RequestBody AlesEntrustDetection entrustDetection,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        List<Map<String, Object>> data = service.getObjById(entrustDetection.getId());
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "发布任务")
    @SystemControllerLog(description = "发布任务", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/rel")
    public Object rel(@RequestHeader String token, String jsonString) {
        Assert.isNotBlank(token,"token");
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        if(currentUser == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
      //  CurrentUser currentUser = WebUtil.getCurrentUser(token);
        return setSuccessModelMap(new ModelMap(), service.relTaskAndSendDataToAdsSys(currentUser,jsonString));
    }

    @ApiOperation(value = "废止任务")
    @SystemControllerLog(description = "废止任务", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/abo")
    public Object abo(String jsonString, String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            AlesEntrustDetection o = service.queryById(id.toString());//根据id获取对象
            o.setState(AlesEnum.ABOLISH.getCode());//已废止状态
            service.update(o);//更新对象状态


            //同步更新 例行监测 监测任务 发布状态 (5:废止状态)
            Assert.isNotBlank(token, "token");
        //    CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null){
                Map<String, Object> param = new HashMap<>();
                param.put("id", o.getId());
                param.put("publishStatus", 5);
                param.put("updateBy", u.getId());

                service.updateAdsMonitorTaskPublishStatus(param);
            }
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "删除")
    @SystemControllerLog(description = "删除", operationType = "删除")
    @Authorization
    @RequestMapping(value = "/del")
    public Object del(String jsonString,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            service.delete(id.toString());
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "任务列表")
    @SystemControllerLog(description = "任务列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/list")
    public Object list(AlesEntrustDetection entrustDetection, String beginTime, String endTime, int start, int length, String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.list(entrustDetection, beginTime, endTime, ((start + 1) / length) + 1, length, token));
    }

    @ApiOperation(value = "报告列表")
    @SystemControllerLog(description = "报告列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/presList")
    public Object presList(AsmsCheckTaskReport checkTaskReport, int start, int length,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.getReportsList(checkTaskReport, ((start + 1) / length) + 1, length));
    }

    @ApiOperation(value = "根据报告获取监测信息列表")
    @SystemControllerLog(description = "根据报告获取监测信息列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getJclistByReport")
    public Object getJclistByReport(AsmsCheckTaskReport checkTaskReport, int start, int length,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.getJclistByReport(checkTaskReport, ((start + 1) / length) + 1, length));
    }

    @ApiOperation(value = "根据监测信息获取检测详情")
    @SystemControllerLog(description = "根据监测信息获取检测详情", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getReportByJcInfo")
    public Object getReportByJcInfo(AdsInfoProject infoProject,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        List<AdsInfoProject> list = service.getReportByJcInfo(infoProject);
        List<Map<String, Object>> jbInfo = service.getJbInfoByJcId(infoProject);
        Map<String, Object> map = new HashMap<>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", list);
        map.put("jbInfo", jbInfo);
        return map;
    }


    @ApiOperation(value = "文件上传")
    @SystemControllerLog(description = "文件上传")
    @Authorization
    @RequestMapping(value = "/fileUpload")
    public Object fileUpload(HttpServletRequest request,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        String address = "";
        try {
            address = super.uploadFile(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setSuccessModelMap(new ModelMap(), address);
    }
    @ApiOperation(value = "获取当前登录用户Id")
    @SystemControllerLog(description = "获取登录用户Id")
    @Authorization
    @RequestMapping(value = "/getCreateBy")
    public Object getCreateBy(String token){
        String uid = WebUtil.getCurrentUserId();//当前用户
        /*String userId = WebUtil.getCurrentUserId(token);*///获取admin用户
        return setSuccessModelMap(new ModelMap(),uid);
    }
}
