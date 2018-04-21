package com.sofn.web.mobile;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.model.generator.AdsOrganTask;
import com.sofn.service.ads.AdsMonitorTaskService;
import com.sofn.service.ads.AdsOrganTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by: yangran
 * Date: 2016-11-28
 * 移动端监测任务数据接口
 */
@RestController
@Api(value = "移动端监测任务数据接口", description = "移动端监测任务数据接口")
@RequestMapping(value = "/adsMobileMonitoTask",method = RequestMethod.POST)
public class AdsMobileMonitorTaskController extends BaseController {

    @Autowired
    private AdsMonitorTaskService adsMonitorTaskService;
    @Autowired
    private AdsOrganTaskService   adsOrganTaskService;

    /**
     * 分页获取检测任务名称列表
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "分页获取检测任务名称列表")
    @RequestMapping(value = "/getPageInfoTaskName",method = RequestMethod.POST)
    public Object getPageInfoTaskName(AdsMonitorTask adsMonitorTask, int start, int length) {
        try{
            PageInfo<AdsMonitorTask> pageInfo =adsMonitorTaskService.getPageInfoTaskName(adsMonitorTask,((start+1)/length)+1,length);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据ID获取单条监测任务基础信息以及机构任务列表
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID获取单条监测任务基础信息以及机构任务列表")
    @RequestMapping(value = "/queryAdsMonitorTaskById",method = RequestMethod.POST)
    public Object queryAdsMonitorTaskById(@RequestParam(value = "id", required = false) String id){
        //TODO 接口查询权限验证
        try{
            // 参数检查
            Assert.isNotBlank(id, "ID");
            ModelMap modelMap =new ModelMap();
            AdsMonitorTask adsMonitorTask = adsMonitorTaskService.selectMonitorTaskAndOrganTaskByPrimaryKey(id);
            if(adsMonitorTask!=null){
                modelMap.put("code",true);
                return setSuccessModelMap(modelMap,adsMonitorTask);
            }else{
                modelMap.put("code",false);
                return setModelMap(modelMap, HttpCode.BAD_REQUEST);
            }
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据ID获取单条机构任务数据信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单条机构任务数据信息")
    @RequestMapping(value = "/queryAdsOrganTaskById", method = RequestMethod.POST)
    public Object queryAdsOrganTaskById(String id) {
        //TODO 接口查询权限验证
        try{
            // 参数检查
            Assert.isNotBlank(id, "ID");
            ModelMap modelMap =new ModelMap();
            AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(id);
            if(adsOrganTask!=null){
                modelMap.put("code",true);
                return setSuccessModelMap(modelMap,adsOrganTask);
            }else{
                modelMap.put("code",false);
                return setModelMap(modelMap, HttpCode.BAD_REQUEST);
            }
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


}
