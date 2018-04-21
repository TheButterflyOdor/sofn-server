package com.sofn.web.asms;

/**
 * Created by Administrator on 2016/9/20.
 */

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.model.generator.SysResource;
import com.sofn.service.asms.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 5:05
 */
@RestController
@Api(value = "专家信息", description = "专家信息")
@RequestMapping(value = "/sysResource",method = RequestMethod.POST)
public class SysResourceController extends BaseController {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 根据条件获取专家信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取专家信息列表")
    @SystemControllerLog(description = "获取专家信息列表",operationType="查询")
    @RequestMapping(value = "/getSysResourceList", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> getSysResourceList(@RequestHeader String token, SysResource sysResource, String professionalfiled, String location, int start, int length, String queryCon) {
        PageInfo<SysResource> pageInfo = sysResourceService.getSysResourceList(sysResource,professionalfiled,location,
                ((start+1)/length)+1,length,queryCon);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

    /**
     * 根据ID获取单个专家信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取单个专家信息")
    @SystemControllerLog(description = "获取单个专家信息",operationType="查询")
    @RequestMapping(value = "/getSysResourceById", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> findSysResourceById(@RequestHeader String token, @RequestBody SysResource sysResource) {
        sysResource = sysResourceService.findSysResource(sysResource.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("sysResource",sysResource);
        return map;
    }
}
