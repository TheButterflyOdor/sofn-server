package com.sofn.web.ales;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.*;
import com.sofn.service.ales.AlesWtObjectCriterionService;
import com.sofn.service.ales.AlesWtTaskEnterpriseService;
import com.sofn.service.ales.AlesWtTaskMonitorService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 委托检测任务-检测对象
 *
 * @author LiBing
 */
@RestController
@Api(value = "委托检测任务-检测对象", description = "委托检测任务-检测对象")
@RequestMapping(value = "/alesWtTaskMonitor", method = RequestMethod.POST)
public class AlesWtTaskMonitorController extends BaseController {
    @Autowired
    private AlesWtTaskMonitorService service;

    @Autowired
    private AlesWtTaskEnterpriseService alesWtTaskEnterpriseService;

    @Autowired
    private AlesWtObjectCriterionService alesWtObjectCriterionService;

    @ApiOperation(value = "根据监测对象id获取监测对象")
    @SystemControllerLog(description = "根据监测对象id获取监测对象", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getObjByObjId")
    public Object getObjByObjId(@RequestBody AlesWtTaskMonitor alesWtTaskMonitor, @RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        AlesWtTaskMonitor data = service.queryById(alesWtTaskMonitor.getId());
        AlesWtTaskEnterprise  alesWtTaskEnterprise = alesWtTaskEnterpriseService.geEntByObjId(alesWtTaskMonitor.getId());
        List<AlesWtObjectCriterion> alesWtObjectCriterions = alesWtObjectCriterionService.getListByObjId(alesWtTaskMonitor.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", data);
        if(alesWtTaskEnterprise!=null){
            modelMap.addAttribute("enterprises", alesWtTaskEnterprise);
        }
       else{
            modelMap.addAttribute("enterprises", "");
        }
        modelMap.addAttribute("criterions", alesWtObjectCriterions);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "分页")
    @SystemControllerLog(description = "分页", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/pages")
    public Object pages(AlesWtTaskMonitor alesWtTaskMonitor, QueryParameter queryParameter,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.pages(alesWtTaskMonitor, queryParameter));
    }

    @ApiOperation(value = "列表")
    @SystemControllerLog(description = "列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getListByTaskId")
    public Object getListByTaskId(AlesWtTaskMonitor alesWtTaskMonitor,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.getListByTaskId(alesWtTaskMonitor));
    }

    @ApiOperation(value = "新增")
    @SystemControllerLog(description = "新增", operationType = "新增")
    @Authorization
    @RequestMapping(value = "/addAlesWtTaskMonitor")
    public Object addAlesWtTaskMonitor(AlesWtTaskMonitor alesWtTaskMonitor, String enterprise, String criterions,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        AlesWtTaskMonitor monitor = service.addAlesWtTaskMonitor(alesWtTaskMonitor);
        insertCriterionAndEnterprise(enterprise, criterions, monitor.getId(), monitor.getSuperviseCheckTaskId());
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "修改")
    @SystemControllerLog(description = "修改", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/updateAlesWtTaskMonitor")
    public Object updateAlesWtTaskMonitor(AlesWtTaskMonitor alesWtTaskMonitor, String enterprise, String criterions,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        service.updateAlesWtTaskMonitor(alesWtTaskMonitor);
        alesWtTaskEnterpriseService.delByObjId(alesWtTaskMonitor.getId());
        alesWtObjectCriterionService.delByObjId(alesWtTaskMonitor.getId());
        insertCriterionAndEnterprise(enterprise, criterions, alesWtTaskMonitor.getId(), alesWtTaskMonitor.getSuperviseCheckTaskId());
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "删除")
    @SystemControllerLog(description = "删除", operationType = "删除")
    @Authorization
    @RequestMapping(value = "/deleteAlesWtTaskMonitor")
    public Object deleteAlesWtTaskMonitor(@RequestBody AlesWtTaskMonitor alesWtTaskMonitor,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        service.deleteAlesWtTaskMonitor(alesWtTaskMonitor.getId());
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "生产经营主体列表")
    @SystemControllerLog(description = "生产经营主体列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getSubjEntList")
    public Object getSubjEntList(TtsScltxxcjRegiter entity, Page page,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = service.getSubjEntList(entity, page);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "临时登记主体列表")
    @SystemControllerLog(description = "临时登记主体列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getSubjTempEntList")
    public Object getSubjTempEntList(AsmsSubjEntTemp entity, Page page,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = service.getSubjTempEntList(entity, page);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", list);
        return setSuccessModelMap(modelMap);
    }


    @ApiOperation(value = "产品列表")
    @SystemControllerLog(description = "产品列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/productList")
    public Object productList(String industry, QueryParameter queryParameter, String keyWord,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.productList(industry, queryParameter, keyWord));
    }

    @ApiOperation(value = "设置检测项上限值")
    @SystemControllerLog(description = "设置检测项上限值", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/updateSpperLimit")
    public Object updateSpperLimit(String criterions,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), alesWtObjectCriterionService.updateSpperLimits(criterions));
    }

    private void insertCriterionAndEnterprise(String enterprise, String criterions, String monitorObjectId, String superviseCheckTaskId) {


        AlesWtTaskEnterprise alesWtTaskEnterprise = JSONObject.parseObject(enterprise, AlesWtTaskEnterprise.class);
        if(alesWtTaskEnterprise != null) {
            alesWtTaskEnterprise.setCheckTaskObjectId(monitorObjectId);
            alesWtTaskEnterprise.setCheckTaskId(superviseCheckTaskId);
            alesWtTaskEnterpriseService.add(alesWtTaskEnterprise);
        }
        List<AlesWtObjectCriterion> alesWtObjectCriterions = JSONObject.parseArray(criterions, AlesWtObjectCriterion.class);
        if (alesWtObjectCriterions != null && !alesWtObjectCriterions.isEmpty()) {
            for (AlesWtObjectCriterion alesWtObjectCriterion : alesWtObjectCriterions) {
                if (alesWtObjectCriterion != null) {
                    alesWtObjectCriterion.setObjectId(monitorObjectId);
                    alesWtObjectCriterion.setTaskId(superviseCheckTaskId);
                    alesWtObjectCriterionService.add(alesWtObjectCriterion);
                }
            }
        }
    }
            }
