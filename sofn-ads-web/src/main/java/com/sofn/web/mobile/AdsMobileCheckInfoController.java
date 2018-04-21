package com.sofn.web.mobile;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AdsCheckInfo;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.service.ads.AdsCheckInfoService;
import com.sofn.service.ads.AdsMonitorTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016-11-29.
 */
@RestController
@Api(value = "检测信息", description = "检测信息")
@RequestMapping(value = "/adsMobileCheckInfo",method = RequestMethod.POST)
public class AdsMobileCheckInfoController extends BaseController {

    @Autowired
    private AdsCheckInfoService adsCheckInfoService;

    @Autowired
    private AdsMonitorTaskService adsMonitorTaskService;

    /**
     * 根据条件获取检测信息列表
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "获取牵头检测信息信息列表")
    @RequestMapping(value = "/getPageInfoWithParam", method = RequestMethod.POST)
    public Object getPageInfoWithParam(AdsCheckInfo adsCheckInfo, int start, int length, String startTime, String endTime, String producingArea, String cityCode, String monitorClass) {
        try {

            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getPageInfoWithParam(adsCheckInfo, ((start + 1) / length) + 1, length, startTime, endTime, producingArea, cityCode, monitorClass);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 根据条件获取承担单位检测信息列表
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "获取承担单位检测信息信息列表")
    @RequestMapping(value = "/getInfoByOrgName", method = RequestMethod.POST)
    public Object getInfoByOrgName(AdsCheckInfo adsCheckInfo, int start, int length,String startTime,String endTime,String producingArea,String cityCode,String monitorClass,String orgId) {
        try {
            /*orgId = "ef63553425e14cedb06e51009d97c8bf40428f5a31a6469381a160f365d525c1";*/
            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getInfoByOrgName(adsCheckInfo, ((start + 1) / length) + 1, length,startTime,endTime,producingArea,cityCode,monitorClass,orgId);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取监测信息列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取监测项目列表")
    @RequestMapping(value = "/getInfoProject",method = RequestMethod.POST)
    public Object getInfoProject(AdsCheckInfo adsCheckInfo, int start, int length) {
        try {
            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getInfoProject(adsCheckInfo, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取监测任务列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取监测任务列表")
    @RequestMapping(value = "/getPageInfoForAndroid",method = RequestMethod.POST)
    public Object getPageInfoForAndroid(AdsMonitorTask adsMonitorTask, int start, int length, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser ut = WebUtil.getCurrentUser(token);
            if (ut != null) {
                adsMonitorTask.setOrganId(ut.getOrgId());
                PageInfo<AdsMonitorTask> pageInfo = adsMonitorTaskService.getPageInfoForAndroid(adsMonitorTask, ((start + 1) / length) + 1, length);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}