package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.model.generator.AdsInfoProject;
import com.sofn.model.generator.AdsProducttempoRrarycode;
import com.sofn.model.generator.AsmsCheckTaskReport;
import com.sofn.service.asms.AsmsCheckTaskReportService;
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
import java.util.logging.Logger;

/**
 * 任务报告
 *
 * @author LiBing
 */
@RestController
@Api(value = "检测任务报告、抽样单[监督抽查、例行监测、专项监测]", description = "检测任务报告、抽样单[监督抽查、例行监测、专项监测]")
@RequestMapping(value = "/AsmsCheckTaskReport", method = RequestMethod.POST)
public class AsmsCheckTaskReportController extends BaseController {
    private static Logger logger = Logger.getLogger("AsmsCheckTaskReportController");
    @Autowired
    private AsmsCheckTaskReportService service;

    @ApiOperation(value = "报告列表")
    @SystemControllerLog(description = "报告列表", operationType = "查询")
    @RequestMapping(value = "/list")
    @Authorization
    public Object list(@RequestHeader String token, AsmsCheckTaskReport r, int start, int length, String CdUnitId, String taskLevel) {
        PageInfo<List<Map<String, Object>>> data = service.list(r, start, length, CdUnitId, taskLevel);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "根据报告获取监测信息列表")
    @SystemControllerLog(description = "根据报告获取监测信息列表", operationType = "查询")
    @RequestMapping(value = "/getJclistByReport")
    @Authorization
    public Object getJclistByReport(@RequestHeader String token, AsmsCheckTaskReport r, int start, int length) {
        return setSuccessModelMap(new ModelMap(), service.getJclistByReport(r, start, length));
    }

    @ApiOperation(value = "根据监测信息获取检测详情")
    @SystemControllerLog(description = "根据监测信息获取检测详情", operationType = "查询")
    @RequestMapping(value = "/getReportByJcInfo")
    @Authorization
    public Object getReportByJcInfo(@RequestHeader String token, AdsInfoProject r) {
        return setSuccessModelMap(new ModelMap(), service.getReportByJcInfo(r));
    }

    @ApiOperation(value = "产品备案列表")
    @SystemControllerLog(description = "产品备案列表", operationType = "查询")
    @RequestMapping(value = "/getPageInfo")
    @Authorization
    public Object getPageInfo(@RequestHeader String token, AdsProducttempoRrarycode r, int start, int length) {
        return setSuccessModelMap(new ModelMap(), service.getPageInfo(r, start, length));
    }
    @ApiOperation(value = "产品备案详情")
    @SystemControllerLog(description = "产品备案详情", operationType = "查询")
    @RequestMapping(value = "/findById")
    @Authorization
    public Object findById(@RequestHeader String token,String  id) {
        AdsProducttempoRrarycode adsProduct=service.findById(id);
        return setSuccessModelMap(new ModelMap(),adsProduct);
    }
}
