package com.sofn.web.ales;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AlesDailyEnforceLaw;
import com.sofn.model.generator.AlesWtObjectCriterion;
import com.sofn.model.generator.AlesWtTaskSample;
import com.sofn.service.ales.AlesWtTaskSampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author dongwenfeng
 */
@RestController
@Api(value = "样品抽样单", description = "样品抽样单")
@RequestMapping(value = "/alesWtTaskSample", method = RequestMethod.POST)
public class AlesWtTaskSampleController extends BaseController {

    @Autowired
    private AlesWtTaskSampleService alesWtTaskSampleService;

    @ApiOperation(value = "新增委托检测样品")
    @SystemControllerLog(description = "新增委托检测样品", operationType = "新增")
    @Authorization
    @RequestMapping(value = "/addAlesWtTaskSample", method = RequestMethod.POST)
    public Object addAlesWtTaskSample(@RequestBody AlesWtTaskSample alesWtTaskSample, @RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        alesWtTaskSampleService.addAlesWtTaskSample(alesWtTaskSample);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "根据任务id获取样品列表")
    @SystemControllerLog(description = "根据任务id获取样品列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getAlesWtTaskSamplelist", method = RequestMethod.POST)
    public Object getAlesWtTaskSamplelist(AlesWtTaskSample alesWtTaskSample, String wtTaskId, String sampleName, String queryCon, String sampleCode, int start, int length,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo<AlesDailyEnforceLaw> data = alesWtTaskSampleService.getAlesWtTaskSamplelist(alesWtTaskSample, wtTaskId, sampleName, queryCon, sampleCode, ((start + 1) / length) + 1, length);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "获取单个委托检测样品信息")
    @SystemControllerLog(description = "获取单个委托检测样品信息", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getAlesWtTaskSampleById", method = RequestMethod.POST)
    public Object findAlesWtTaskSampleById(@RequestBody AlesWtTaskSample alesWtTaskSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        AlesWtTaskSample data = alesWtTaskSampleService.queryById(alesWtTaskSample.getId());
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "修改样品信息")
    @SystemControllerLog(description = "修改样品信息", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/updateAlesWtTaskSample", method = RequestMethod.POST)
    public Object updateAlesWtTaskSample(@RequestBody AlesWtTaskSample alesWtTaskSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        alesWtTaskSampleService.update(alesWtTaskSample);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "删除样品信息")
    @SystemControllerLog(description = "删除样品信息", operationType = "删除")
    @Authorization
    @RequestMapping(value = "/deleteAlesWtTaskSample", method = RequestMethod.POST)
    public Object deleteAlesWtTaskSample(String jsonString,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            alesWtTaskSampleService.delete(id.toString());
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "判断样品编码是否存在")
    @SystemControllerLog(description = "判断样品编码是否存在", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findAdsMonitorSample", method = RequestMethod.POST)
    public Object findAdsMonitorSample(String sampleCode,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        AlesWtTaskSample alesTaskSample = alesWtTaskSampleService.findAdsMonitorSample(sampleCode);
        if (alesTaskSample != null) {
            return setSuccessModelMap(new ModelMap(), "false");
        } else {
            return setSuccessModelMap(new ModelMap(), "true");
        }

    }
//    @ApiOperation(value = "根据样品编码获得检测项")
//    @SystemControllerLog(description = "根据样品编码获得检测项", operationType = "查询")
//    @Authorization
//    @RequestMapping(value = "/queryCheckByCode", method = RequestMethod.POST)
//    public Object queryCheckByCode(String sampleCode) {
//        AlesWtObjectCriterion cri = alesWtTaskSampleService.queryCheckByCode(sampleCode);
//        if (cri != null) {
//            return setSuccessModelMap(new ModelMap(), "false");
//        } else {
//            return setSuccessModelMap(new ModelMap(), "true");
//        }
//
//    }
}
