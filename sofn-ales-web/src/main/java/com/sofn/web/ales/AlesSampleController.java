package com.sofn.web.ales;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.Assert;
import com.sofn.core.util.PinyinUtil;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.service.ales.*;
import com.sun.javafx.scene.control.skin.SliderSkin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.tools.doclint.Entity.nu;
import static com.sun.tools.doclint.Entity.sdot;

/**
 * @author zhangdong
 */
@RestController
@Api(value = "抽样信息", description = "抽样信息")
@RequestMapping(value = "/alesSample")

public class AlesSampleController extends BaseController {
    @Autowired
    private AlesSampleService alesSampleService;
    @Autowired
    private SubjEnforceLawService subjEnforceLawService;
    @Autowired
    private AlesWtTaskMonitorService alesWtTaskMonitorService;
    @Autowired
    private AlesEntrustDetectionService alesEntrustDetectionService;
    @Autowired
    private AlesMyNewCheckTaskService alesMyNewCheckTaskService;
    @Autowired
    private AlesTestItemService alesTestItemService;

    @ApiOperation(value = "新增/修改抽样信息")
    @SystemControllerLog(description = "新增/修改抽样信息", operationType = "新增修改")
    @Authorization
    @RequestMapping(value = "/addAlesSample", method = RequestMethod.POST)
    public Object addAlesSample(@RequestBody AlesSample alesSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        if (StringUtil.isNotBlank(alesSample.getId())) {
            AlesSample old = alesSampleService.queryById(alesSample.getId());
            alesSample.setCreateBy(old.getCreateBy());
            alesSample.setCreateTime(old.getCreateTime());
            alesSample.setEnable(old.getEnable());
        }
        alesSampleService.add(alesSample);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "根据id获取抽样")
    @SystemControllerLog(description = "根据id获取抽样", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public Object getById(@RequestBody AlesSample alesSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), alesSampleService.queryById(alesSample.getId()));
    }

    @ApiOperation(value = "根据条件获取抽样信息列表")
    @SystemControllerLog(description = "根据条件获取抽样信息列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getAlesSampleList", method = RequestMethod.POST)
    public Object getAlesSampleList(AlesSample alesSample, int start, int length,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<AlesSample> pageInfo = alesSampleService.getAlesSampleList(alesSample, ((start + 1) / length) + 1, length);
        return setSuccessModelMap(new ModelMap(), pageInfo);
    }

    @ApiOperation(value = "删除抽样信息")
    @SystemControllerLog(description = "删除抽样信息", operationType = "删除")
    @Authorization
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Object del(String jsonString,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            alesSampleService.delete(id.toString());
        }
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "根据任务类型、单位、行业生成样品编码")
    @SystemControllerLog(description = "根据任务类型、单位、行业生成样品编码", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getSampleCode", method = RequestMethod.POST)
    public Object getSampleCode(@RequestHeader String token,@RequestParam String monitorTaskId,@RequestParam String taskType){
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        if(currentUser==null){
            return setModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        //定义一个样品编码
        StringBuffer sampleCode = new StringBuffer();
        //获取所属省的前2个首字母大写
        String regionHead = PinyinUtil.getPinYinHeadUperChar(currentUser.getOrganization().getRegionName());
        if(regionHead!=null&&regionHead.length()>=2){
            sampleCode.append(regionHead.substring(0,2));
        }else{
            return setModelMap(new ModelMap(),HttpCode.NOT_FOUND_DATA);
        }
        sampleCode.append(taskType);
        //获取组织机构代码，取后3位，没有则用XXX代替
        AsmsSubjEnforceLaw subjEnforceLaw = subjEnforceLawService.findSubjEnforceLawById(currentUser.getOrganization().getOrgId());
        if(subjEnforceLaw!=null&&subjEnforceLaw.getElCode()!=null&&subjEnforceLaw.getElCode().length()>=3){
            sampleCode.append(subjEnforceLaw.getElCode().substring(subjEnforceLaw.getElCode().length()-3,subjEnforceLaw.getElCode().length()));
        }else {
            sampleCode.append("XXX");
        }
        //查询最大的样品编码
        List<Map<String,Object>> list = new ArrayList<>();
        if("WTJC".equals(taskType)){
            //获取所属行业
            AlesWtTaskMonitor alesWtTaskMonitor = alesWtTaskMonitorService.queryById(monitorTaskId);
            sampleCode.append(alesWtTaskMonitor.getIndustry());
            //获取年度
            AlesEntrustDetection alesEntrustDetection = alesEntrustDetectionService.queryById(alesWtTaskMonitor.getSuperviseCheckTaskId());
            sampleCode.append(alesEntrustDetection.getTaskyear());
            //去重，通过单位、行业、年份
            //定义一个Map，接收参数，用来去重
            Map<String,Object> map = new HashMap();
//            map.put("orgId",currentUser.getOrganization().getOrgId());
//            map.put("industry",alesWtTaskMonitor.getIndustry());
//            map.put("taskYear",alesEntrustDetection.getTaskyear());
            map.put("sampleCode",sampleCode.toString());
            list = alesSampleService.getWtjcMaxSampleCode(map);
        }
        if("JDCC".equals(taskType)){
            //获取所属行业
            AsmsMonitorObject asmsMonitorObject = alesMyNewCheckTaskService.findAsmsMonitorObjectById(monitorTaskId);
            sampleCode.append(asmsMonitorObject.getIndustry());
            //获取年度
            AsmsCheckTask asmsCheckTask = alesMyNewCheckTaskService.queryByid(asmsMonitorObject.getSuperviseCheckTaskId());
            sampleCode.append(asmsCheckTask.getTaskYear());
            //去重，通过单位、行业、年份
            //定义一个Map，接收参数，用来去重
            Map<String,Object> map = new HashMap();
//            map.put("orgId",currentUser.getOrganization().getOrgId());
//            map.put("industry",asmsMonitorObject.getIndustry());
//            map.put("taskYear",asmsCheckTask.getTaskYear());
            map.put("sampleCode",sampleCode.toString());
            list = alesSampleService.getJdccMaxSampleCode(map);
        }
        //批次，监督抽查和委托检测没有批次，用00代替
        sampleCode.append("00");
        if(list==null||list.size()<1){
            sampleCode.append("0001");
        }else{
            String maxSampleCode = ((Map)list.get(0)).get("SAMPLECODE").toString();
            Integer integer = Integer.valueOf(maxSampleCode.substring(maxSampleCode.length()-4,maxSampleCode.length()));
            //尾数自增长(处理同一机构下多用户操作未保存，样品编码相同问题)
            RedisUtil.set("tempKey",integer);
            Integer i = Integer.parseInt(RedisUtil.incr(RedisUtil.get("tempKey").toString()).toString());
            i += integer;
            if(i<10){
                sampleCode.append("000").append(i);
            }else if(i<100){
                sampleCode.append("00").append(i);
            }else if(i<1000){
                sampleCode.append("0").append(i);
            }else {
                sampleCode.append(i);
            }
        }
        return setSuccessModelMap(new ModelMap(),sampleCode);
    }

    @ApiOperation(value = "根据抽样单ID获取检测详情ID")
    @SystemControllerLog(description = "根据抽样单ID获取检测详情ID", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getCheckInfoIdBySampleId", method = RequestMethod.POST)
    public Object getCheckInfoIdBySampleId(String sampleId,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        ModelMap modelMap = new ModelMap();
        if(StringUtil.isBlank(sampleId)){
            return setModelMap(modelMap,HttpCode.BAD_REQUEST);
        }
        modelMap.addAttribute("checkInfoId",alesSampleService.getCheckInfoIdBySampleId(sampleId));
        return setSuccessModelMap(modelMap);
    }
    @ApiOperation(value = "受检项目")
    @SystemControllerLog(description = "受检项目详情list", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getTestitemlist", method = RequestMethod.POST)
    public Object getTestitemlist(String standardCode, String itemName, Page page,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        page.setStart(page.getStart() / page.getLength());
        PageInfo<SysTestItemModel> pageInfo = alesTestItemService.getTestitemlist(itemName, standardCode, page);
        ModelMap modelMap = new ModelMap();
        page.setRecordsTotal(pageInfo.getTotal());
        System.out.println("=============总数："+pageInfo.getTotal());
        return setSuccessModelMap(modelMap,pageInfo);
    }
    @ApiOperation(value = "检测标准号")
    @SystemControllerLog(description = "检测标准号", operationType = "查询")
    @Authorization
    @RequestMapping(method = RequestMethod.POST, value = "/getTeststandard")
    public Object getTeststandard(String itemName, String standardCode,Page page,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<SysTestStandardModel> pageInfo = alesTestItemService.getTestStandards(itemName, standardCode,page);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, pageInfo);
    }
}