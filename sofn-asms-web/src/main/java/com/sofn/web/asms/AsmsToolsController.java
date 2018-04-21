package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.Organization;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import com.sofn.service.asms.AsmsToolsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户机构列表
 *
 * @author LiBing
 */
@RestController
@Api(value = "用户机构", description = "用户机构")
@RequestMapping(value = "/asmsTools", method = RequestMethod.POST)
public class AsmsToolsController extends BaseController {

    @Autowired
    private AsmsToolsService service;

    @ApiOperation(value = "根据用户token获取当前用户所属机构下所有用户")
    @SystemControllerLog(description = "根据用户token获取当前用户所属机构下所有用户", operationType = "查询")
    @RequestMapping(value = "/getUsersListByUserToken")
    @Authorization
    public Object getUsersListByUserToken(@RequestHeader String token,QueryParameter p, String keyword) {
        return setSuccessModelMap(new ModelMap(), service.getUsersByToken(token, p, keyword));
    }

    @ApiOperation(value = "根据用户token获取用户所属机构信息")
    @SystemControllerLog(description = "根据用户token获取用户所属机构信息", operationType = "查询")
    @RequestMapping(value = "/getOrgbyUserToken")
    @Authorization
    public Object getOrgbyUserToken(@RequestHeader String token) {
        Organization o = service.getOrganizationByToken(token);
        return setSuccessModelMap(new ModelMap(), o);
    }

    @ApiOperation(value = "根据用户token获取用户所属机构下所有检测机构列表")
    @SystemControllerLog(description = "根据用户token获取用户所属机构下所有检测机构列表", operationType = "查询")
    @RequestMapping(value = "/getOrgsListByUserToken")
    @Authorization
    public Object getOrgsListByUserToken(@RequestHeader String token,AsmsSubjDetection subjDetection, com.sofn.util.Page page) {
        PageInfo pageInfo = service.getOrgs(subjDetection, page, token);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        return setSuccessModelMap(modelMap, pageInfo.getList());
    }

    @ApiOperation(value = "根据用户token获取用户所属机构下所有执法机构列表")
    @SystemControllerLog(description = "根据用户token获取用户所属机构下所有执法机构列表", operationType = "查询")
    @RequestMapping(value = "/getZfOrgsListByUserToken")
    @Authorization
    public Object getZfOrgsListByUserToken(@RequestHeader String token,AsmsSubjEnforceLaw subjEnforceLaw, com.sofn.util.Page page) {
        PageInfo pageInfo = service.getZfOrgs(subjEnforceLaw, page, token);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        return setSuccessModelMap(modelMap, pageInfo.getList());
    }

}
