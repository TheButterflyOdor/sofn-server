package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.model.generator.AsmsRecheckObject;
import com.sofn.service.asms.AsmsRecheckObjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 复检对象
 *
 * @author LiBing
 */
@RestController
@Api(value = "复检对象", description = "复检对象")
@RequestMapping(value = "/reCheckObject", method = RequestMethod.POST)
public class AsmsRecheckObjectController extends BaseController {

    @Autowired
    private AsmsRecheckObjectService service;


    @ApiOperation(value = "新增复检对象")
    @SystemControllerLog(description = "新增复检对象", operationType = "新增")
    @RequestMapping(value = "/addObj")
    @Authorization
    public Object addObj(@RequestHeader String token, AsmsRecheckObject r) {
        service.addObj(r);
        return setSuccessModelMap(new ModelMap(), "OK");
    }

    @ApiOperation(value = "删除复检对象")
    @SystemControllerLog(description = "删除复检对象", operationType = "删除")
    @RequestMapping(value = "/delObj")
    @Authorization
    public Object delObj(@RequestHeader String token, String ids) {
        service.delObj(ids);
        return setSuccessModelMap(new ModelMap(), "OK");
    }

    @ApiOperation(value = "复检对象列表")
    @SystemControllerLog(description = "复检对象列表", operationType = "查询")
    @RequestMapping(value = "/reCheckObjlist")
    @Authorization
    public Object reCheckObjlist(@RequestHeader String token, AsmsRecheckObject r, int start, int length) {
        PageInfo<AsmsRecheckObject> data = service.reCheckObjlist(r, start, length);
        return setSuccessModelMap(new ModelMap(), data);
    }
}
