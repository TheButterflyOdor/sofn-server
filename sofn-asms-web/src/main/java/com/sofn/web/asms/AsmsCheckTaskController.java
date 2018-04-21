package com.sofn.web.asms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.sofn.model.asms.MQInfo;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.*;
import com.sofn.service.asms.*;
import com.sofn.util.Page;
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
 * 监督抽查
 *
 * @author LiBing
 */
@RestController
@Api(value = "监督抽查", description = "监督抽查")
@RequestMapping(value = "/checkTask", method = RequestMethod.POST)
public class AsmsCheckTaskController extends BaseController {
    @Autowired
    private AsmsCheckTaskService service;//监督抽查任务
    @Autowired
    private AsmsMonitorObjectService objService;//监测对象
    @Autowired
    private AsmsJcStandardService jcService;//检测标准
    @Autowired
    private AsmsPdStandardService pdService;//判断标准
    @Autowired
    private AsmsCheckTaskEnterpriseService checkTaskEnterpriseService;//受检单位
    @Autowired
    private AsmsCheckObjectCriterionService objectCriterionService;//检测项


    @ApiOperation(value = "新增任务(省级)")
    @SystemControllerLog(description = "新增省级监督抽查任务", operationType = "新增")
    @RequestMapping(value = "/add")
    @Authorization
    public Object add(AsmsCheckTask checkTask, @RequestHeader(required = true) String token, String _asmsJcStandard, String _asmsPdStandard) {
        Assert.isNotBlank(token, "token");
        String taskId = service.addTask(checkTask, token);
        List<AsmsJcStandard> asmsJcStandards = JSONObject.parseArray(_asmsJcStandard, AsmsJcStandard.class);//检测标准
        for (AsmsJcStandard asmsJcStandard : asmsJcStandards) {
            if (asmsJcStandard != null) {
                asmsJcStandard.setTaskId(taskId);
                jcService.add(asmsJcStandard);
            }
        }
        List<AsmsPdStandard> asmsPdStandards = JSONObject.parseArray(_asmsPdStandard, AsmsPdStandard.class);//判断标准
        for (AsmsPdStandard asmsPdStandard : asmsPdStandards) {
            if (asmsPdStandard != null) {
                asmsPdStandard.setTaskId(taskId);
                pdService.add(asmsPdStandard);
            }
        }
        return setSuccessModelMap(new ModelMap(), taskId);
    }

    @ApiOperation(value = "修改任务(省级)")
    @SystemControllerLog(description = "修改省级监督抽查任务", operationType = "修改")
    @RequestMapping(value = "/update")
    @Authorization
    public Object update(@RequestHeader(required = true) String token, AsmsCheckTask checkTask, String _asmsJcStandard, String _asmsPdStandard) {
        Assert.isNotBlank(token, "token");
        service.updateTask(checkTask, token);
        List<AsmsJcStandard> asmsJcStandards = JSONObject.parseArray(_asmsJcStandard, AsmsJcStandard.class);//检测标准
        jcService.delByTaskId(checkTask.getId());
        for (AsmsJcStandard asmsJcStandard : asmsJcStandards) {
            if (asmsJcStandard != null) {
                asmsJcStandard.setTaskId(checkTask.getId());
                jcService.add(asmsJcStandard);
            }
        }
        List<AsmsPdStandard> asmsPdStandards = JSONObject.parseArray(_asmsPdStandard, AsmsPdStandard.class);//判断标准
        pdService.delByTaskId(checkTask.getId());
        for (AsmsPdStandard asmsPdStandard : asmsPdStandards) {
            if (asmsPdStandard != null) {
                asmsPdStandard.setTaskId(checkTask.getId());
                pdService.add(asmsPdStandard);
            }
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "修改任务(部级)")
    @SystemControllerLog(description = "修改部级监督抽查任务", operationType = "修改")
    @RequestMapping(value = "/updateBjTask")
    @Authorization
    public Object updateBjTask(@RequestHeader(required = true) String token, AsmsCheckTask checkTask, String orgs) {
        Assert.isNotBlank(token, "token");
        service.updateBjTask(checkTask, orgs, token);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "新增任务(部级)")
    @SystemControllerLog(description = "新增部级监督抽查任务", operationType = "新增")
    @RequestMapping(value = "/addBjTask")
    @Authorization
    public Object addBjTask(@RequestHeader(required = true) String token, AsmsCheckTask checkTask, String orgs) {
        Assert.isNotBlank(token, "token");
        service.addBjTask(checkTask, orgs, token);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "根据id查询任务")
    @SystemControllerLog(description = "根据id查询监督抽查任务", operationType = "查询")
    @RequestMapping(value = "/getTaskById")
    @Authorization
    public Object getTaskById(@RequestHeader(required = true) String token, @RequestBody AsmsCheckTask checkTask) {
        AsmsCheckTask asmsCheckTask = service.queryById(checkTask.getId());
        List<AsmsCheckBearUnit> list = service.getUnitByTaskId(checkTask.getId());
        List<AsmsJcStandard> jcList = jcService.getListByTaskId(checkTask.getId());
        List<AsmsPdStandard> pdList = pdService.getListByTaskId(checkTask.getId());
        Map<String, Object> map = new HashMap<>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", asmsCheckTask);
        map.put("speList", list);
        map.put("jcList", jcList);
        map.put("pdList", pdList);
        return map;
    }

    @Autowired
    private AsmsMQInfoService mqInfoService;

    @ApiOperation(value = "发布任务")
    @SystemControllerLog(description = "发布监督抽查任务", operationType = "修改")
    @RequestMapping(value = "/rel")
    @Authorization
    public Object rel(@RequestHeader(required = true) String token, String jsonString) {
        String idx = "true";
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        MQInfo mqInfo = new MQInfo();
        for (Object id : jsonArray) {
            AsmsCheckTask asmsCheckTask = service.queryById(id.toString());
            if (AsmsEnum.NON_MINISTERIAL.getCode().equals(asmsCheckTask.getTaskLevel())) {
                idx = service.sendDateToAdsByAdd(asmsCheckTask);
                if (!"true".equals(idx)) {
                    break;
                }
            } else if (AsmsEnum.MINISTERIAL.getCode().equals(asmsCheckTask.getTaskLevel())) {
                asmsCheckTask.setState(AsmsEnum.PUBLISHED.getCode());
                service.update(asmsCheckTask);
            }
            if ("true".equals(idx)) {
                mqInfo.setProvider(currentUser.getOrgId());
                mqInfo.setConsumer(asmsCheckTask.getCyUnitId());
                mqInfo.setTitle("监督抽查任务");
                mqInfo.setContent("监督抽查任务");
                try {
                    mqInfoService.sendMessage(mqInfo);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return setSuccessModelMap(new ModelMap(), idx);
    }

    @ApiOperation(value = "废止任务")
    @SystemControllerLog(description = "废止监督抽查任务", operationType = "修改")
    @RequestMapping(value = "/abo")
    @Authorization
    public Object abo(@RequestHeader(required = true) String token, String jsonString) {
        Map<String, Object> map = new HashMap<>();
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<AsmsCheckTask> asmsCheckTasks = new ArrayList<>();
        AsmsCheckTask asmsCheckTask = null;
        boolean idx = false;
        for (Object id : jsonArray) {
            asmsCheckTasks.clear();
            asmsCheckTask = service.queryById(id.toString());
            //如果是部级任务,获取其子任务
            if ("0".equals(asmsCheckTask.getTaskLevel())) {
                asmsCheckTasks = service.getTasksByParentTaskId(asmsCheckTask);
            }
            //添加当前任务
            asmsCheckTasks.add(asmsCheckTask);

            for (AsmsCheckTask asmsCheckTaskTemp : asmsCheckTasks) {
                idx = service.sendDateToAdsByAbo(asmsCheckTaskTemp.getId());
                if (idx) {
                    asmsCheckTaskTemp.setState(AsmsEnum.ABOLISH.getCode());
                    service.update(asmsCheckTaskTemp);

                    //同步更新 例行监测 监测任务 发布状态 (5:废止状态)
                    Assert.isNotBlank(token, "token");
                    CurrentUser u = WebUtil.getCurrentUser(token);
                    if (u != null) {
                        Map<String, Object> param = new HashMap<>();
                        param.put("id", asmsCheckTask.getId());
                        param.put("publishStatus", 5);
                        param.put("updateBy", u.getId());

                        service.updateAdsMonitorTaskPublishStatus(param);
                    }


                } else {
                    map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
                    map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
                    return map;
                }
            }
        }
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "删除")
    @SystemControllerLog(description = "删除监督抽查任务", operationType = "删除")
    @RequestMapping(value = "/del")
    @Authorization
    public Object del(@RequestHeader(required = true) String token, String jsonString) {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        for (Object id : jsonArray) {
            service.delTask(id.toString());
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "任务列表")
    @SystemControllerLog(description = "任务列表", operationType = "查询")
    @RequestMapping(value = "/list")
    @Authorization
    public Object list(@RequestHeader(required = true) String token, AsmsCheckTask checkTask, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), service.list(token, checkTask, queryParameter));
    }

    @ApiOperation(value = "机构所属任务列表")
    @SystemControllerLog(description = "机构所属任务列表", operationType = "查询")
    @RequestMapping(value = "/listByOrg")
    @Authorization
    public Object listByOrg(@RequestHeader(required = true) String token, AsmsCheckTask checkTask, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), service.listByOrg(token, checkTask, queryParameter));
    }

    @ApiOperation(value = "获取所属父级任务列表")
    @SystemControllerLog(description = "获取所属父级任务列表", operationType = "查询")
    @RequestMapping(value = "/getParentTaskList")
    @Authorization
    public Object getParentTaskList(@RequestHeader(required = true) String token, AsmsCheckTask checkTask, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), service.getParentTaskList(token, checkTask, queryParameter));
    }

    @ApiOperation(value = "获取产品列表")
    @SystemControllerLog(description = "获取产品列表", operationType = "查询")
    @RequestMapping(value = "/getProList")
    @Authorization
    public Object getProList(@RequestHeader(required = true) String token, TtsScltxxcjProduct ttsScltxxcjProduct, QueryParameter queryParameter, String keyWord) {
        return setSuccessModelMap(new ModelMap(), objService.getProList(ttsScltxxcjProduct.getIndustry(), queryParameter, keyWord));
    }

    @ApiOperation(value = "监测对象列表")
    @SystemControllerLog(description = "监测对象列表", operationType = "查询")
    @RequestMapping(value = "/objList")
    @Authorization
    public Object objList(@RequestHeader(required = true) String token, AsmsMonitorObject monitorObject, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), objService.objList(monitorObject, queryParameter));
    }

    @ApiOperation(value = "根据监测对象id获取监测对象")
    @SystemControllerLog(description = "根据监测对象id获取监测对象", operationType = "查询")
    @RequestMapping(value = "/getObjByObjId")
    @Authorization
    public Object getObjByObjId(@RequestHeader(required = true) String token, @RequestBody AsmsMonitorObject monitorObject) {
        AsmsMonitorObject data = objService.queryById(monitorObject.getId());
        List<AsmsCheckTaskEnterprise> asmsCheckTaskEnterprises = checkTaskEnterpriseService.getListByObjId(monitorObject.getId());
        List<AsmsCheckObjectCriterion> asmsCheckObjectCriterions = objectCriterionService.getListByObjId(monitorObject.getId());
        Map<String, Object> map = new HashMap<>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", data);
        map.put("ents", asmsCheckTaskEnterprises);
        map.put("crits", asmsCheckObjectCriterions);
        return map;
    }

    @ApiOperation(value = "新增监测对象")
    @SystemControllerLog(description = "新增监测对象", operationType = "新增")
    @RequestMapping(value = "/addObj")
    @Authorization
    public Object addObj(@RequestHeader(required = true) String token, AsmsMonitorObject monitorObject, String szUnits, String crits) {
        AsmsMonitorObject monitor = objService.addObj(monitorObject);
        insertCriterionAndEnterprise(szUnits, crits, monitor.getId(), monitor.getSuperviseCheckTaskId());
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "修改监测对象")
    @SystemControllerLog(description = "修改监测对象", operationType = "修改")
    @RequestMapping(value = "/updateObj")
    @Authorization
    public Object updateObj(@RequestHeader(required = true) String token, AsmsMonitorObject monitorObject, String szUnits, String crits) {
        boolean success = objService.updateObj(monitorObject);
        if (success) {
            checkTaskEnterpriseService.delByObjId(monitorObject.getId());
            objectCriterionService.delByObjId(monitorObject.getId());
            insertCriterionAndEnterprise(szUnits, crits, monitorObject.getId(), monitorObject.getSuperviseCheckTaskId());
        }
        return setSuccessModelMap(new ModelMap(), success);
    }

    @ApiOperation(value = "删除抽查对象")
    @SystemControllerLog(description = "删除抽查对象", operationType = "删除")
    @RequestMapping(value = "/delObj")
    @Authorization
    public Object delObj(@RequestHeader(required = true) String token, @RequestBody AsmsMonitorObject monitorObject) {
        JSONArray jsonArray = JSONArray.parseArray(monitorObject.getId());
        for (Object id : jsonArray) {
            boolean success = objService.delObj(id.toString());
            if (!success) {
                return setSuccessModelMap(new ModelMap(), "false");
            }
        }
        return setSuccessModelMap(new ModelMap(), "true");
    }

    @ApiOperation(value = "根据任务id查询抽查对象列表")
    @SystemControllerLog(description = "根据任务id查询抽查对象列表", operationType = "查询")
    @RequestMapping(value = "/getObjById")
    @Authorization
    public Object getObjById(@RequestHeader(required = true) String token, @RequestBody AsmsMonitorObject monitorObject) {
        return setSuccessModelMap(new ModelMap(), service.getObjById(monitorObject.getId()));
    }

    @ApiOperation(value = "设置检测项上限值")
    @SystemControllerLog(description = "设置检测项上限值", operationType = "修改")
    @RequestMapping(value = "/updateSpperLimit")
    @Authorization
    public Object updateSpperLimit(@RequestHeader(required = true) String token, String criterions) {
        return setSuccessModelMap(new ModelMap(), objectCriterionService.updateSpperLimits(criterions));
    }

    @ApiOperation(value = "通过样品编码获取检测项目")
    @SystemControllerLog(description = "通过样品编码获取检测项目", operationType = "查询")
    @RequestMapping(value = "/getDetectionItemBySampleCode")
    @Authorization
    public Object getDetectionItemBySampleCode(@RequestHeader(required = true) String token, @RequestParam String sampleCode) {
        ModelMap modelMap = new ModelMap();
        if (StringUtil.isEmpty(sampleCode)) {
            return setModelMap(modelMap, HttpCode.BAD_REQUEST);
        }
        List<AsmsCheckObjectCriterion> list = objectCriterionService.getDetectionItemBySampleCode(sampleCode);
        return setSuccessModelMap(modelMap, list);
    }

    @ApiOperation(value = "抽样单列表[监督抽查]")
    @SystemControllerLog(description = "抽样单列表[监督抽查]", operationType = "查询")
    @RequestMapping(value = "/getTaskSampleListByJcInfo")
    @Authorization
    public Object getTaskSampleListByJcInfo(@RequestHeader(required = true) String token, AsmsCheckTask checkTask, QueryParameter queryParameter, String taskObjId, String JdccCdUnit, String sonTaskId) {
        return setSuccessModelMap(new ModelMap(), service.getTaskSampleListByJcInfo(checkTask, queryParameter, taskObjId, JdccCdUnit, sonTaskId));
    }

    @ApiOperation(value = "跟据id获取抽样单详情")
    @SystemControllerLog(description = "跟据id获取抽样单详情", operationType = "查询")
    @RequestMapping(value = "/getTaskSampleById")
    @Authorization
    public Object getTaskSampleById(@RequestHeader(required = true) String token, @RequestBody AdsMonitorSample adsMonitorSample) {
        return setSuccessModelMap(new ModelMap(), service.getTaskSampleById(adsMonitorSample));
    }

    @ApiOperation(value = "跟据父级任务id和承担单位id获取子任务列表")
    @SystemControllerLog(description = "跟据父级任务id和承担单位id获取子任务监测对象", operationType = "查询")
    @RequestMapping(value = "/getTasksByCreateOrgIdAndParentTaskId")
    @Authorization
    public Object getTasksByCreateOrgIdAndParentTaskId(@RequestHeader(required = true) String token, @RequestBody AsmsCheckTask checkTask) {
        return setSuccessModelMap(new ModelMap(), service.getTasksByCreateOrgIdAndParentTaskId(checkTask));
    }

    @ApiOperation(value = "抽样单列表[例行监测、专项检测]")
    @SystemControllerLog(description = "抽样单列表[例行监测、专项检测]", operationType = "查询")
    @RequestMapping(value = "/getZLTaskSampleListByJcInfo")
    @Authorization
    public Object getZLTaskSampleListByJcInfo(@RequestHeader(required = true) String token, AsmsCheckTask checkTask, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), service.getZLTaskSampleListByJcInfo(checkTask, queryParameter));
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

    private void insertCriterionAndEnterprise(String enterprise, String criterions, String monitorObjectId, String superviseCheckTaskId) {
        List<AsmsCheckTaskEnterprise> asmsCheckTaskEnterprises = JSONObject.parseArray(enterprise, AsmsCheckTaskEnterprise.class);
        if (asmsCheckTaskEnterprises != null && !asmsCheckTaskEnterprises.isEmpty()) {
            for (AsmsCheckTaskEnterprise asmsCheckTaskEnterprise : asmsCheckTaskEnterprises) {
                if (asmsCheckTaskEnterprise != null) {
                    asmsCheckTaskEnterprise.setCheckTaskObjectId(monitorObjectId);
                    asmsCheckTaskEnterprise.setCheckTaskId(superviseCheckTaskId);
                    checkTaskEnterpriseService.add(asmsCheckTaskEnterprise);
                }
            }
        }
        List<AsmsCheckObjectCriterion> asmsCheckObjectCriterions = JSONObject.parseArray(criterions, AsmsCheckObjectCriterion.class);
        if (asmsCheckObjectCriterions != null && !asmsCheckObjectCriterions.isEmpty()) {
            for (AsmsCheckObjectCriterion asmsCheckObjectCriterion : asmsCheckObjectCriterions) {
                if (asmsCheckObjectCriterion != null) {
                    asmsCheckObjectCriterion.setObjectId(monitorObjectId);
                    asmsCheckObjectCriterion.setTaskId(superviseCheckTaskId);
                    objectCriterionService.add(asmsCheckObjectCriterion);
                }
            }
        }
    }

    @ApiOperation(value = "获取检测项列表")
    @SystemControllerLog(description = "获取检测项列表", operationType = "查询")
    @RequestMapping(value = "/getCheckItemList")
    @Authorization
    public Object getCheckItemList(@RequestHeader(required = true) String token, Page page, String itemName, String standardCode) {
        ModelMap modelMap = new ModelMap();
        PageInfo<SysTestItemModel> pageInfo = service.getCheckItemList(page, itemName, standardCode);
        page.setRecordsTotal(pageInfo.getTotal());
        modelMap.put("list", pageInfo.getList());
        modelMap.put("page", page);
        return setSuccessModelMap(modelMap);
    }


}
