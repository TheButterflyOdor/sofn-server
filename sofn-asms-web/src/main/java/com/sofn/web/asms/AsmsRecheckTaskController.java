package com.sofn.web.asms;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AlesTaskSample;
import com.sofn.model.generator.AsmsRecheckObject;
import com.sofn.model.generator.AsmsRecheckTask;
import com.sofn.service.asms.AsmsRecheckTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 复检任务
 *
 * @author LiBing
 */
@RestController
@Api(value = "复检任务", description = "复检任务")
@RequestMapping(value = "/reCheckTask", method = RequestMethod.POST)
public class AsmsRecheckTaskController extends BaseController {
    @Autowired
    private AsmsRecheckTaskService service;

    @ApiOperation(value = "新增任务")
    @SystemControllerLog(description = "新增任务", operationType = "新增")
    @RequestMapping(value = "/add")
    @Authorization
    public Object add(@RequestHeader(required = true) String token, AsmsRecheckTask r) {
        Assert.isNotBlank(token, "token");
        Map<String, Object> map = new HashMap<>();
        AsmsRecheckTask task = service.addTask(r, token);
        map.put("data", task);
        map.put("msg", "true");
        return setSuccessModelMap(new ModelMap(), map);
    }

    @ApiOperation(value = "修改任务")
    @SystemControllerLog(description = "修改任务", operationType = "修改")
    @RequestMapping(value = "/update")
    @Authorization
    public Object update(@RequestHeader(required = true) String token, AsmsRecheckTask r) {
        Map<String, Object> map = new HashMap<>();
        service.updateTask(r,token);
        map.put("data", r);
        map.put("msg", "true");
        return setSuccessModelMap(new ModelMap(), map);
    }

    @ApiOperation(value = "根据id查询任务")
    @SystemControllerLog(description = "根据id查询任务", operationType = "查询")
    @RequestMapping(value = "/getTaskById")
    @Authorization
    public Object getTaskById(@RequestHeader(required = true) String token, @RequestBody AsmsRecheckTask r) {
        AsmsRecheckTask data = service.queryById(r.getId());
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "根据id查询关联对象")
    @SystemControllerLog(description = "根据id查询关联对象", operationType = "查询")
    @RequestMapping(value = "/getObjById")
    @Authorization
    public Object getObjById(@RequestHeader(required = true) String token, @RequestBody AsmsRecheckTask r) {
        List<AsmsRecheckObject> data = service.getObjById(r.getId());
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "发布任务")
    @SystemControllerLog(description = "发布任务", operationType = "修改")
    @RequestMapping(value = "/rel")
    @Authorization
    public Object rel(@RequestHeader(required = true) String token, String jsonString) {
        String b = service.sendToAds(jsonString);
        Map<String, Object> map = new HashMap<>();
        map.put("info", b);
        return setSuccessModelMap(new ModelMap(), map);
    }

    @ApiOperation(value = "废止任务")
    @SystemControllerLog(description = "废止任务", operationType = "修改")
    @RequestMapping(value = "/abo")
    @Authorization
    public Object abo(@RequestHeader(required = true) String token, String jsonString) {
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            AsmsRecheckTask o = service.queryById(id.toString());
            o.setState(AsmsEnum.ABOLISH.getCode());
            service.update(o);

            //同步更新 例行监测 监测任务 发布状态 (5:废止状态)
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
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
    @RequestMapping(value = "/del")
    @Authorization
    public Object del(@RequestHeader(required = true) String token, String jsonString) {
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            service.delete(id.toString());
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "任务列表")
    @SystemControllerLog(description = "任务列表", operationType = "查询")
    @RequestMapping(value = "/list")
    @Authorization
    public Object list(@RequestHeader(required = true) String token, AsmsRecheckTask r,QueryParameter par) {
        return setSuccessModelMap(new ModelMap(), service.list(token,r, par));
    }

    @ApiOperation(value = "抽样单列表")
    @SystemControllerLog(description = "抽样单列表", operationType = "查询")
    @RequestMapping(value = "/taskSampleList")
    @Authorization
    public Object taskSampleList(@RequestHeader(required = true) String token, AlesTaskSample r, String taskId, String batch, String taskType, int start, int length) {
        PageInfo<List<Map<String, Object>>> data = service.taskSampleList(r, taskId, batch, taskType, start, length);
        return setSuccessModelMap(new ModelMap(), data);
    }

    /**
     * 根据ID获取样品名称
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据ID获取样品名称")
    @SystemControllerLog(description = "根据ID获取样品名称", operationType = "查询")
    @RequestMapping(value = "/findAlesTaskSample", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> findAlesTaskSample(@RequestHeader(required = true) String token, String sampleCode) {
        AlesTaskSample taskSample = service.findAlesTaskSample(sampleCode);
        Map<String, Object> map = new HashMap<>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("taskSample", taskSample);
        return map;
    }

}

