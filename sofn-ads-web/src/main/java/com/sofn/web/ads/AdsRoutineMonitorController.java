package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsRoutineMonitor;
import com.sofn.model.generator.AsmsSpecialMonitor;
import com.sofn.service.ads.AdsRoutineMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 监管下发任务接口
 *
 * @author yangran
 * @version 2016年11月18日
 */
@RestController
@Api(value = "监管下发任务接口", description = "监管下发任务接口")
@RequestMapping(value = "/adsRoutineMonitor", method = RequestMethod.POST)
public class AdsRoutineMonitorController extends BaseController {
    private static Logger logger = Logger.getLogger("AsmsRoutineMonitorController");
    @Autowired
    private AdsRoutineMonitorService service;

    @ApiOperation(value = "查询例行监测任务列表")
    @RequestMapping(value = "/list")
    @SystemControllerLog(description = "查询例行监测任务列表",operationType = "查询")
    @Authorization
    public Object list(HttpServletRequest request,AsmsRoutineMonitor r,QueryParameter p, String dateBegin, String dateEnd, int start, int length) {
        PageInfo<List<Map<String, Object>>> data = service.list(r,p, dateBegin, dateEnd, ((start + 1) / length) + 1, length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",data);
        return map;
    }

    @ApiOperation(value = "查询专项监测任务列表")
    @SystemControllerLog(description = "查询专项监测任务列表",operationType="查询")
    @RequestMapping(value = "/getSpecialList")
    @Authorization
    public Object getSpecialList(HttpServletRequest request, AsmsSpecialMonitor r, QueryParameter p) {
        PageInfo<List<Map<String, Object>>> data = null;
        if (StringUtil.isNotBlank(p.getToken())){
            data = service.getSpecialList(r, p);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",data);
        return map;
    }

}
