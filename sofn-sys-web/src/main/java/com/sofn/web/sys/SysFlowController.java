package com.sofn.web.sys;

import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysFlow;
import com.sofn.service.sys.SysFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@Api(value = "流程管理",description = "流程管理")
@RequestMapping(value = "/sysFlow")
public class SysFlowController extends BaseController {
    @Autowired
    private SysFlowService sysFlowService;

    /**
     * "新增流程
     * @param
     * @return
     */
    @ApiOperation(value = "新增流程")
    @RequestMapping(value = "/addSysFlow",method = RequestMethod.POST)
    public Map<String, Object> addSysFlow(@RequestBody SysFlow sysFlow){
        String userId = WebUtil.getCurrentUserId();
        sysFlow.setId(UUID.randomUUID().toString());
        sysFlow.setCreateBy(userId);
        sysFlow.setDelFlag("N");
        sysFlow.setCreateTime(new Date());
        sysFlowService.addSysFlow(sysFlow);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * 根据条件获取流程列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取流程列表")
    @RequestMapping(value = "/getSysFlowList",method = RequestMethod.POST)
    public Object getSysFlowList(ModelMap modelMap, @ApiParam(required = true, value = "状态") @RequestParam(value = "status", required = false) String status,
                                 @ApiParam(required = true, value = "流程名字") @RequestParam(value = "flowName", required = false) String flowName,
                                 @ApiParam(required = true, value = "页数") @RequestParam(value = "draw", required = false) long draw,
                                 @ApiParam(required = true, value = "开始数") @RequestParam(value = "start", required = false) long start,
                                 @ApiParam(required = true, value = "数量") @RequestParam(value = "length", required = false) long length){
        long recordsTotal = sysFlowService.getRecordsTotal(status,flowName);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setDraw(draw);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();

        List<SysFlow> list = sysFlowService.getSysFlowLists(pager,status,flowName);
        for (SysFlow sysFlow:list) {
            String temp = sysFlow.getStatus();
            if (("0").equals(temp))
                sysFlow.setStatus("可用");
            if (("1").equals(temp))
                sysFlow.setStatus("禁用");
            if (("2").equals(temp))
                sysFlow.setStatus("注销");
        }

        modelMap.addAttribute("page",pager);
        modelMap.addAttribute("list",list);

        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据ID获取单个流程
     * @param
     * @return
     */
    @ApiOperation(value = "获取流程")
    @RequestMapping(value = "/findDeptById",method = RequestMethod.POST)
    public Map<String, Object> findDeptById( @RequestBody SysFlow sysFlow){
        SysFlow temp = sysFlowService.findSysFlowByst(sysFlow.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sysFlow",temp);
        return map;
    }
    
    
    
    @ApiOperation(value = "修改流程")
    @RequestMapping(value = "/updateSysFlow",method = RequestMethod.POST)
    public Map<String,Object> updateSysFlow(@RequestBody SysFlow sysFlow){
        //获取当前登录用户
        String userId = WebUtil.getCurrentUserId();
        sysFlow.setUpdateBy(userId);
        sysFlow.setUpdateTime(new Date());
        sysFlow.setDelFlag("N");
        sysFlowService.updateSysFlow(sysFlow);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    @ApiOperation(value = "删除流程")
    @RequestMapping(value = "/deletesysFlow",method = RequestMethod.POST)
    public Map<String,Object> deleteDept(@ApiParam(required = true, value = "流程ID")
                                         @RequestParam(value = "id", required = false) String id){
        sysFlowService.deleteSysFlow(id);
//        SysFlow temp = sysFlowService.findSysFlowByst(id);
//        temp.setDelFlag("N");
//        sysFlowService.updateSysFlow(temp);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }


    /**
     * 批量删除
     * @return
     */
    @ApiOperation(value="批量删除流程信息")
    @RequestMapping(value="/deleteAll",method = RequestMethod.POST)
    public Object deleteAll(
            @ApiParam(required = true, value = "ids")
            @RequestParam(value = "idStr", required = false)
                    String idStr){
        String[] jsonStr=idStr.split(",");
        ModelMap modelMap = new ModelMap();
        for (int i = 0; i < jsonStr.length; i++) {
            logger.debug(jsonStr[i] + "**");
            String oStr=jsonStr[i].toString();
            if(oStr==null||oStr.equals("")){
                continue;
            }
            SysFlow sysFlow = sysFlowService.queryById(oStr);
            sysFlow.setDelFlag("Y");
            sysFlowService.update(sysFlow);
        }
        return setSuccessModelMap(modelMap);
    }

}
