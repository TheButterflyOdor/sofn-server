package com.sofn.web.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.model.sys.SysUserBean;
import com.sofn.service.ales.AlesToolsService;
import com.sofn.service.ales.DailyEnforceLawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "执法系统工具类", description = "执法系统工具类")
@RequestMapping(value = "/alesTools", method = RequestMethod.POST)
public class AlesToolsController extends BaseController{

    @Autowired
    private AlesToolsService service;
    @Autowired
    private DailyEnforceLawService dailyEnforceLawService;

    @ApiOperation(value = "根据用户token获取当前用户所属机构下所有用户")
    @SystemControllerLog(description = "根据用户token获取当前用户所属机构下所有用户",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getUsersListByUserToken")
    public Object getUsersListByUserToken(@RequestHeader String token, QueryParameter queryParameter,String keyword) {
//        return setSuccessModelMap(new ModelMap(), service.getUsersByToken(queryParameter, keyword));
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        com.sofn.util.Page page = new com.sofn.util.Page();
        page.setStart((long)(queryParameter.getStart()));
        page.setLength((long)queryParameter.getLength());
        PageInfo<SysUserBean> pageInfo = dailyEnforceLawService.getSysUserListByOrgId(page,user.getOrgId(),keyword);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
//        modelMap.addAttribute("page",page);
        modelMap.addAttribute("data",pageInfo);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据用户token获取用户所属机构信息")
    @SystemControllerLog(description = "根据用户token获取用户所属机构信息",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getOrgbyUserToken")
    public Object getOrgbyUserToken(@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        Organization o = service.getOrganizationByToken(token);
        return setSuccessModelMap(new ModelMap(),o);
    }


    @ApiOperation(value = "根据用户token获取机构详细信息")
    @SystemControllerLog(description = "根据用户token获取机构详细信息",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getTestingOrganization", method = RequestMethod.POST)
    public Object getTestingOrganization(String token) {
        Assert.isNotBlank(token,"token");
        ModelMap modelMap = new ModelMap();
        CurrentUser u= WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        String orgId=u.getOrganization().getOrgId();
        AsmsSubjEnforceLaw testingOrganization = service.getTestingOrganizationInfo(orgId);
        return setSuccessModelMap(new ModelMap(), testingOrganization);
    }


    @ApiOperation(value = "获取检测标准信息")
    @SystemControllerLog(description = "获取检测标准信息",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getTestStandard", method = RequestMethod.POST)
    public Object getTestStandard(@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        List<SysTestStandardModel> testStandards = service.getTestStandard();
        return setSuccessModelMap(new ModelMap(), testStandards);
    }


    @ApiOperation(value = "根据用户token获取用户所属机构下所有检测机构列表")
    @SystemControllerLog(description = "根据用户token获取用户所属机构下所有检测机构列表",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getOrgsListByUserToken")
    public Object getOrgsListByUserToken(AsmsSubjDetection subjDetection, com.sofn.util.Page page, String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = service.getOrgs(subjDetection,page,token);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        return setSuccessModelMap(modelMap,pageInfo.getList());
    }

    @ApiOperation(value = "根据用户token获取用户所属机构下所有执法机构列表")
    @SystemControllerLog(description = "根据用户token获取用户所属机构下所有执法机构列表",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getZfOrgsListByUserToken")
    public Object getZfOrgsListByUserToken(AsmsSubjEnforceLaw subjEnforceLaw, com.sofn.util.Page page, String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = service.getZfOrgs(subjEnforceLaw,page,token);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        return setSuccessModelMap(modelMap,pageInfo.getList());
    }

}
