package com.sofn.web.asms;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsSpecialLeadUnit;
import com.sofn.model.generator.AsmsSpecialMonitor;
import com.sofn.service.asms.AsmsSpecialMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 专项监测
 *
 * @author LiBing
 */
@RestController
@Api(value = "专项监测", description = "专项监测")
@RequestMapping(value = "/specialMonitor", method = RequestMethod.POST)
public class AsmsSpecialMonitorController extends BaseController {
    private static Logger logger = Logger.getLogger("AsmsSpecialMonitorController");
    @Autowired
    private AsmsSpecialMonitorService service;

    @ApiOperation(value = "新增任务")
    @SystemControllerLog(description = "新增任务", operationType = "新增")
    @RequestMapping(value = "/add")
    @Authorization
    public Object add(@RequestHeader(required = true) String token,AsmsSpecialMonitor r, String orgs) {
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        service.addTask(r, orgs, token, user.getId());
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "修改任务")
    @SystemControllerLog(description = "修改任务", operationType = "修改")
    @RequestMapping(value = "/update")
    public Object update(@RequestHeader(required = true) String token,AsmsSpecialMonitor r, String orgs) {
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        service.updateTask(r, orgs,user.getId());
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "根据id查询任务")
    @SystemControllerLog(description = "根据id查询任务", operationType = "查询")
    @RequestMapping(value = "/getTaskById")
    public Object getTaskById(@RequestHeader(required = true) String token,@RequestBody AsmsSpecialMonitor r) {
        AsmsSpecialMonitor o = service.queryById(r.getId());
        List<AsmsSpecialLeadUnit> list = service.getUnitByTaskId(r.getId());
        Map<String, Object> map = new HashMap<>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", o);
        map.put("speList", list);
        return map;
    }

    @ApiOperation(value = "发布任务")
    @SystemControllerLog(description = "发布任务", operationType = "修改")
    @RequestMapping(value = "/rel")
    @Authorization
    public Object rel(@RequestHeader(required = true) String token, String jsonString) {
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            AsmsSpecialMonitor o = service.queryById(id.toString());
            o.setSmState(AsmsEnum.PUBLISHED.getCode());
            service.update(o);
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "废止任务")
    @SystemControllerLog(description = "废止任务", operationType = "修改")
    @RequestMapping(value = "/abo")
    @Authorization
    public Object abo(@RequestHeader(required = true) String token, String jsonString) {
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            AsmsSpecialMonitor o = service.queryById(id.toString());
            o.setSmState(AsmsEnum.ABOLISH.getCode());
            service.update(o);

            //同步更新 例行监测 监测任务 发布状态 (5:废止状态)
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null){
                Map<String, Object> map = new HashMap<>();
                map.put("supId", o.getId());
                map.put("publishStatus", 5);
                map.put("updateBy", u.getId());

                service.updateAdsMonitorTaskPublishStatus(map);
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
    public Object list(@RequestHeader(required = true) String token, AsmsSpecialMonitor r, QueryParameter p) {
        return setSuccessModelMap(new ModelMap(), service.list(token, r, p));
    }


    @ApiOperation(value = "机构所属任务列表")
    @SystemControllerLog(description = "机构所属任务列表", operationType = "查询")
    @RequestMapping(value = "/listByOrg")
    @Authorization
    public Object listByOrg(@RequestHeader(required = true) String token, AsmsSpecialMonitor r, QueryParameter p) {
        return setSuccessModelMap(new ModelMap(), service.listByOrg(token, r, p));
    }

    @ApiOperation(value = "文件上传")
    @SystemControllerLog(description = "文件上传")
    @RequestMapping(value = "/fileUpload")
    public Object fileUpload(HttpServletRequest request) {
        String address = "";
        try {
            address = super.uploadFile(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setSuccessModelMap(new ModelMap(), address);
    }
}
