/**
 *
 */
package com.sofn.web.asms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.asms.SuperviseTaskInfo;
import com.sofn.model.generator.AsmsInspectionAssess;
import com.sofn.model.generator.AsmsInspectionTask;
import com.sofn.model.generator.SysUser;
import com.sofn.service.asms.AsmsInspectionAssessService;
import com.sofn.service.asms.AsmsInspectionTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考核任务
 *
 * @author LiBing
 */
@RestController
@Api(value = "巡查人员考核任务", description = "巡查人员考核任务")
@RequestMapping(value = "/tspinstask", method = RequestMethod.POST)
public class AsmsInspectionTaskController extends BaseController {
    @Autowired
    private AsmsInspectionTaskService taskService;
    @Autowired
    private AsmsInspectionAssessService assessService;

    @ApiOperation(value = "新增任务")
    @SystemControllerLog(description = "新增任务", operationType = "新增")
    @RequestMapping(value = "/add")
    @Authorization
    public Object add(@RequestHeader String token, @RequestBody SuperviseTaskInfo taskInfo) {
        String id = taskService.addTask(taskInfo, token).getId();//任务信息
        this.insertPersion(taskInfo.getUserIds(), id);//巡查人员
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "修改任务")
    @SystemControllerLog(description = "修改任务", operationType = "修改")
    @RequestMapping(value = "/update")
    @Authorization
    public Object update(@RequestHeader String token, @RequestBody SuperviseTaskInfo taskInfo) {
        this.updatePersion(taskInfo.getUserIds(), taskInfo.getId());//巡查人员更新
        taskService.updateTask(taskInfo);//任务信息修改
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "逻辑删除任务")
    @SystemControllerLog(description = "逻辑删除任务", operationType = "删除")
    @RequestMapping(value = "/delete")
    @Authorization
    public Object delete(@RequestHeader String token, String jsonString) {
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            taskService.delete(id.toString());
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "取消任务")
    @SystemControllerLog(description = "取消任务", operationType = "修改")
    @RequestMapping(value = "/cancel")
    @Authorization
    public Object cancel(@RequestHeader String token, String jsonString) {
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            taskService.delete(id.toString());
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "根据id查询任务")
    @SystemControllerLog(description = "根据id查询任务", operationType = "查询")
    @RequestMapping(value = "/queryById")
    @Authorization
    public Object queryById(@RequestHeader String token, @RequestBody AsmsInspectionTask task) {
        AsmsInspectionTask inspectionTask = taskService.queryById(task.getId());
        List<AsmsInspectionAssess> inspectionAssess = assessService.getPersonByTaskId(task.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("inspectionTask", inspectionTask);
        map.put("inspectionAssess", inspectionAssess);
        return setSuccessModelMap(new ModelMap(), map);
    }

    @ApiOperation(value = "巡查人员考核结果提交")
    @SystemControllerLog(description = "巡查人员考核结果提交", operationType = "修改")
    @RequestMapping(value = "/taskResult")
    @Authorization
    public Object taskResult(@RequestHeader String token, String id, String taskResult) {
        AsmsInspectionAssess nUser = assessService.queryById(id);
        nUser.setTaskResult(taskResult);
        assessService.update(nUser);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "任务列表")
    @SystemControllerLog(description = "任务列表", operationType = "查询")
    @RequestMapping(value = "/taskList")
    @Authorization
    public Object taskList(@RequestHeader String token, AsmsInspectionTask task, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), taskService.getPages(token, task, queryParameter));
    }


    @ApiOperation(value = "巡查人员考核列表")
    @SystemControllerLog(description = "巡查人员考核列表", operationType = "查询")
    @RequestMapping(value = "/list")
    @Authorization
    public Object list(@RequestHeader String token, SuperviseTaskInfo taskInfo, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), assessService.getPages(token, taskInfo, queryParameter));
    }

    private void insertPersion(String persons, String taskId) {
        if (!"[]".equals(persons)) {
            List<SysUser> users = JSONObject.parseArray(persons, SysUser.class);
            for (SysUser user : users) {
                if (user != null) {
                    AsmsInspectionAssess o = new AsmsInspectionAssess();
                    o.setInspectionTaskId(taskId);
                    o.setUserId(user.getId());
                    o.setUserName(user.getUserName());
                    assessService.add(o);
                }
            }
        }
    }

    private void updatePersion(String persons, String taskId) {
        AsmsInspectionAssess asmsInspectionAssess = new AsmsInspectionAssess();
        asmsInspectionAssess.setInspectionTaskId(taskId);
        assessService.delOldDate(asmsInspectionAssess);
        this.insertPersion(persons, taskId);
    }

}
