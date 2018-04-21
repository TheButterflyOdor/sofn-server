package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.AsmsEmergencyExpert;
import com.sofn.model.generator.AsmsEmergencyTask;
import com.sofn.service.asms.AsmsEmergencyExpertService;
import com.sofn.service.asms.SuperviseEmergencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangdong on 2016/9/27 11:21.
 */
@RestController
@Api(value = "应急管理",description = "应急管理")
@RequestMapping(value = "/superviseEmergency")
public class SuperviseEmergencyController extends BaseController{
    @Autowired
    private SuperviseEmergencyService superviseEmergencyService;
    @Autowired
    private AsmsEmergencyExpertService asmsEmergencyExpertService;

    /**
     * 根据ID获取单个应急任务
     * @param asmsEmergencyTask
     * @return
     */
    @ApiOperation(value = "根据ID获取单个应急任务")
    @SystemControllerLog(description = "根据ID获取单个应急任务信息",operationType="查询")
    @RequestMapping(value = "/findAsmsEmergencyTaskById",method = RequestMethod.POST)
    @Authorization
    public Object findAsmsEmergencyTaskById(@RequestHeader String token,@RequestBody AsmsEmergencyTask asmsEmergencyTask){
        if(asmsEmergencyTask==null||asmsEmergencyTask.getId().isEmpty()){
            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND);
        }
        asmsEmergencyTask = superviseEmergencyService.findAsmsEmergencyTaskById(asmsEmergencyTask.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("asmsEmergencyTask",asmsEmergencyTask);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 修改应急任务
     * @param asmsEmergencyTask
     * @return
     */
    @ApiOperation(value = "修改应急任务")
    @SystemControllerLog(description = "修改应急任务",operationType="修改")
    @RequestMapping(value = "/updateSuperviseEmergency",method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> updateSuperviseEmergency(@RequestHeader String token,@RequestBody AsmsEmergencyTask asmsEmergencyTask){
        //修改应急任务信息
        superviseEmergencyService.update(asmsEmergencyTask);
        //获取应急任务id
        String asmsEmergencyTaskId = asmsEmergencyTask.getId();
        //修改关联关系前先删除关联关系
        asmsEmergencyExpertService.deleteemergencyId(asmsEmergencyTaskId);
        //插入应急任务与专家资源关联关系
        AsmsEmergencyExpert asmsEmergencyExpert=new AsmsEmergencyExpert();
        asmsEmergencyExpert.setId(UUID.randomUUID().toString().replace("-", ""));
        String[] aa = asmsEmergencyTask.getExpertId().split(",");
        for(int i = 0 ; i <aa.length ; i++){
            asmsEmergencyExpert.setEmergencyId(asmsEmergencyTaskId);
            asmsEmergencyExpert.setExpertId(aa[i]);
            asmsEmergencyExpertService.addAsmsEmergencyExpert(asmsEmergencyExpert);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * 新增应急任务
     * @param asmsEmergencyTask
     * @return
     */
    @ApiOperation(value = "新增应急任务")
    @SystemControllerLog(description = "新增应急任务",operationType="新增")
    @RequestMapping(value = "/addSuperviseEmergency",method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> addSuperviseEmergency(@RequestHeader String token,@RequestBody AsmsEmergencyTask asmsEmergencyTask){
        //插入应急任务信息
        String emergencyId=superviseEmergencyService.addSuperviseEmergency(asmsEmergencyTask);
        //插入应急任务与专家资源关联关系
        AsmsEmergencyExpert asmsEmergencyExpert=new AsmsEmergencyExpert();
        asmsEmergencyExpert.setId(UUID.randomUUID().toString().replace("-", ""));
        String[] aa = asmsEmergencyTask.getExpertId().split(",");
        for(int i = 0 ; i <aa.length ; i++){
            asmsEmergencyExpert.setEmergencyId(emergencyId);
            asmsEmergencyExpert.setExpertId(aa[i]);
            asmsEmergencyExpertService.addAsmsEmergencyExpert(asmsEmergencyExpert);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * 获取应急任务列表
     * @param asmsEmergencyTask
     * @return
     */
    @ApiOperation(value = "获取应急任务列表")
    @SystemControllerLog(description = "获取应急任务列表",operationType="查询")
    @RequestMapping(value = "/getSuperviseEmergencyList",method = RequestMethod.POST)
    @Authorization
    public Map<String,Object> getSuperviseEmergencyList(@RequestHeader String token, AsmsEmergencyTask asmsEmergencyTask ,
                                                    String dateBegin, String dateEnd, int start, int length,String queryCon, String taskYear,String releaseUnit, String areaId, String bearUnit,String createOrgId){

        PageInfo<AsmsEmergencyTask> pageInfo = superviseEmergencyService.getAsmsEmergencyTaskList(asmsEmergencyTask,taskYear,dateBegin,dateEnd,
                ((start+1)/length)+1,length,queryCon,releaseUnit,areaId,bearUnit,createOrgId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

    /**
     * 文件上传
     */
    @ApiOperation(value = "文件上传")
    @SystemControllerLog(description = "文件上传",operationType="上传")
//    @RequiresPermissions("asms.smtask.fileUpload")
    @RequestMapping(value = "/fileUpload")
    @Authorization
    public Object fileUpload(@RequestHeader String token, HttpServletRequest request) {
        String address = "";
        try {
            address = super.uploadFile(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setSuccessModelMap(new ModelMap(), address);
    }
}
