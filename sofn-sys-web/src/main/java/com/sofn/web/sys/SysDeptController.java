package com.sofn.web.sys;

import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysDept;
import com.sofn.model.generator.SysOrganization;
import com.sofn.service.sys.SysDeptService;
import com.sofn.service.sys.SysOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@Api(value = "部门管理",description = "部门管理")
@RequestMapping(value = "/sysDept")
public class SysDeptController extends BaseController {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysOrganizationService sysOrganizationService;

    /**
     * "新增部门
     * @param
     * @return
     */
    @ApiOperation(value = "新增部门")
    @RequestMapping(value = "/addSysDept",method = RequestMethod.POST)
    public Map<String, Object> addSysDept(@RequestBody SysDept sysDept){
        String userId = WebUtil.getCurrentUserId();
        sysDept.setId(UUID.randomUUID().toString());
        sysDept.setCreateBy(userId);
        sysDept.setDelFlag("N");
        sysDept.setCreateTime(new Date());
        sysDeptService.addSysDept(sysDept);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * 根据条件获取部门列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取部门列表")
    @RequestMapping(value = "/getSysDeptList",method = RequestMethod.POST)
    public Object getSysDeptList(ModelMap modelMap, @ApiParam(required = true, value = "状态") @RequestParam(value = "status", required = false) String status,
                                 @ApiParam(required = true, value = "部门名字") @RequestParam(value = "deptName", required = false) String deptName,
                                 @ApiParam(required = true, value = "页数") @RequestParam(value = "draw", required = false) long draw,
                                 @ApiParam(required = true, value = "开始数") @RequestParam(value = "start", required = false) long start,
                                 @ApiParam(required = true, value = "数量") @RequestParam(value = "length", required = false) long length){
        long recordsTotal = sysDeptService.getRecordsTotal(status,deptName);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setDraw(draw);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();

        List<SysDept> list = sysDeptService.getSysDeptLists(pager,status,deptName);
        for (SysDept sysDept:list) {
            String temp = sysDept.getStatus();
            if (("0").equals(temp))
                sysDept.setStatus("可用");
            if (("1").equals(temp))
                sysDept.setStatus("禁用");
            if (("2").equals(temp))
                sysDept.setStatus("注销");
        }

        modelMap.addAttribute("page",pager);
        modelMap.addAttribute("list",list);
//        modelMap.addAttribute("list",list);

        return setSuccessModelMap(modelMap);

//        PageInfo<SysDept> pageInfo = sysDeptService.getSysDeptList(status, ((start+1)/length)+1,length);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
//        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
//        map.put("data",pageInfo);
//        return map;
    }
    /**
     * 根据ID获取单个部门
     * @param
     * @return
     */
    @ApiOperation(value = "获取部门")
    @RequestMapping(value = "/findDeptById",method = RequestMethod.POST)
    public Map<String, Object> findDeptById( @ApiParam(required = true, value = "部门ID") @RequestParam(value = "id", required = false) String id){
        SysDept temp = sysDeptService.findSysDeptByst(id);
        String orgId = temp.getRegionId();
        SysOrganization sysOrganization = sysOrganizationService.findSysOrganizationByst(orgId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sysDept",temp);
        map.put("sysOrganization",sysOrganization);
        return map;
    }
    @ApiOperation(value = "修改部门")
    @RequestMapping(value = "updateSysDept",method = RequestMethod.POST)
    public Map<String,Object> updateSysDept(@RequestBody SysDept sysDept){


        //获取当前登录用户
        String userId = WebUtil.getCurrentUserId();
        sysDept.setUpdateBy(userId);
        sysDept.setUpdateTime(new Date());
        sysDept.setDelFlag("N");
        sysDeptService.updateSysDept(sysDept);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    @ApiOperation(value = "删除部门")
    @RequestMapping(value = "deleteDept",method = RequestMethod.POST)
    public Map<String,Object> deleteDept(@ApiParam(required = true, value = "部门ID")
                                         @RequestParam(value = "id", required = false) String id){
//        sysDeptService.deleteSysDept(id);
        SysDept temp = sysDeptService.findSysDeptByst(id);
        temp.setDelFlag("Y");
        sysDeptService.updateSysDept(temp);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    @ApiOperation(value = "根据机构ID获得所有部门")
    @RequestMapping(value = "getDeptListByRegion",method = RequestMethod.POST)
    public Map<String,Object> getDeptListByRegion(@ApiParam(required = true, value = "区域ID")
                                                  @RequestParam(value = "regionId", required = false) String regionId){
        List<SysDept> list = sysDeptService.getDeptListByRegion(regionId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",list);
        return map;
    }

    /**
     * 批量删除
     * @return
     */
    @ApiOperation(value="批量删除部门信息")
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
            SysDept sysDept = sysDeptService.queryById(oStr);
            sysDept.setDelFlag("Y");
            sysDeptService.update(sysDept);
        }
        return setSuccessModelMap(modelMap);
    }

}
