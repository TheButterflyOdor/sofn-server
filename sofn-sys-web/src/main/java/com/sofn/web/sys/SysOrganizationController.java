package com.sofn.web.sys;

import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysOrganization;
import com.sofn.service.sys.SysOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@Api(value = "机构管理",description = "机构管理")
@RequestMapping(value = "/sysOrganization")
public class SysOrganizationController extends BaseController {
    @Autowired
    private SysOrganizationService sysOrgService;

    /**
     * "新增机构
     * @param
     * @return
     */
    @ApiOperation(value = "新增机构")
    @RequestMapping(value = "/addSysOrganization",method = RequestMethod.POST)
    public Map<String, Object> addSysOrganization(@RequestBody SysOrganization sysOrg){
        sysOrg.setId(UUID.randomUUID().toString());
        sysOrgService.addSysOrganization(sysOrg);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * 根据条件获取机构列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取机构列表")
    @RequestMapping(value = "/getSysOrganizationList",method = RequestMethod.POST)
    public Object getSysOrganizationList(ModelMap modelMap, @ApiParam(required = true, value = "区域名") @RequestParam(value = "orgName", required = false) String orgName,
                                              @ApiParam(required = true, value = "页数") @RequestParam(value = "start", required = false) int start,
                                              @ApiParam(required = true, value = "数量") @RequestParam(value = "length", required = false) int length){
        int recordsTotal = sysOrgService.getRecordsTotal(orgName);
        Page pager = new Page();
//        pager.setPageNumber(start);
//        pager.setPageSize(length);

        pager.setRecordsTotal(recordsTotal);
        pager.doPage();

        List<SysOrganization> list = sysOrgService.getSysOrganizationLists(pager,orgName);
        modelMap.addAttribute("pager",pager);
        modelMap.addAttribute("list",list);

        return setSuccessModelMap(modelMap);
    }

    /**
     * 查询所有机构
     * @return
     */
    @ApiOperation(value = "查询所有机构")
    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    public Map<String, Object> selectAll(){
        List<SysOrganization> list = sysOrgService.selectAll();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",list);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }


    /**
     * 根据ID获取单个机构
     * @param
     * @return
     */
    @ApiOperation(value = "获取机构")
    @RequestMapping(value = "/findOrgById",method = RequestMethod.POST)
    public Map<String, Object> findOrgById( @ApiParam(required = true, value = "机构 id")
                                                @RequestParam(name = "id", required = false) String id){
        SysOrganization temp = new SysOrganization();
        if(("-1").equals(id)){
            List<SysOrganization> list = sysOrgService.selectAll();
            if (list.size()>0)
                temp = list.get(0);
        }else {
            temp = sysOrgService.findSysOrganizationByst(id);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("SysOrganization",temp);
        return map;
    }

    @ApiOperation(value = "修改机构")
    @RequestMapping(value = "updateSysOrganization",method = RequestMethod.POST)
    public Map<String,Object> updateSysOrganization(@RequestBody SysOrganization sysOrg){
        sysOrgService.updateSysOrganization(sysOrg);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "删除机构")
    @RequestMapping(value = "deleteOrg",method = RequestMethod.POST)
    public Map<String,Object> deleteOrg(@ApiParam(required = true, value = "机构ID")
                                         @RequestParam(value = "id", required = false) String id){
        sysOrgService.deleteSysOrganization(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "根据机构名字返回机构唯一信息")
    @RequestMapping(value = "selectByName",method = RequestMethod.POST)
    public Map<String,Object> selectByName(@RequestBody SysOrganization sysOrg){

        SysOrganization sysOrganization = sysOrgService.selectByName(sysOrg.getOrgName());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",sysOrganization);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "根据机构名字验证机构是否唯一")
    @RequestMapping(value = "VerifyByName",method = RequestMethod.POST)
    public Map<String,Object> VerifyByName(@RequestBody SysOrganization sysOrg){

        boolean temp = sysOrgService.VerifyByName(sysOrg.getOrgName());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Verification",temp);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

}
