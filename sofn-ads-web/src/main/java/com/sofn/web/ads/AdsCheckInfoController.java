
package com.sofn.web.ads;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.model.sys.SysDocumentQueryParam;
import org.springframework.web.bind.annotation.RequestBody;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.service.ads.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hwpf.usermodel.LineSpacingDescriptor;
import com.sofn.core.util.excel.ExportExcelUtil;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import com.sofn.core.annotation.SystemControllerLog;

/**
*	检测信息 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "检测信息", description = "检测信息")
@RequestMapping(value = "/adsCheckInfo",method = RequestMethod.POST)
public class AdsCheckInfoController extends BaseController {

    @Autowired
    private AdsCheckInfoService adsCheckInfoService;

    @Autowired
    private AdsOrganTaskService adsOrganTaskService;

    @Autowired
    private AdsInfoProjectService adsInfoProjectService;

    @Autowired
    private AdsTaskProjectService adsTaskProjectService;

    @Autowired
    private AdsMonitorTaskService adsMonitorTaskService;

    @Autowired
    private AdsCheckModelService adsCheckModelService;

    @Autowired
    private AdsModelCheckObjectService adsModelCheckObjectService;

    @Autowired
    private AdsModelCheckItemService adsModelCheckItemService;

    @Autowired
    private AdsModelCheckStandardService adsModelCheckStandardService;

    @Autowired
    private AdsMonitorSampleService adsMonitorSampleService;

    @Autowired
    private AdsSysUserService adsSysUserService;

    @Autowired
    private AdsNewModelService adsNewModelService;

    /**
     * 根据条件获取检测信息列表
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "获取检测信息信息列表")
    @SystemControllerLog(description = "获取检测信息信息列表",operationType="查询")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsCheckInfo adsCheckInfo, int start, int length) {
        try {
            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getPageInfo(adsCheckInfo, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增检测信息记录
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "新增检测信息数据")
    @SystemControllerLog(description = "新增检测信息数据",operationType="添加")
    @RequestMapping(value = "/addAdsCheckInfo", method = RequestMethod.POST)
    @Authorization
    public Object addAdsCheckInfo(@RequestBody AdsCheckInfo adsCheckInfo) {
        try {
            adsCheckInfoService.add(adsCheckInfo);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 根据ID获取单条检测信息数据信息
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "获取单条检测信息数据信息")
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public Map<String, Object> queryById(@RequestBody AdsCheckInfo adsCheckInfo) {
        adsCheckInfo = adsCheckInfoService.queryById(adsCheckInfo.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsCheckInfo", adsCheckInfo);
        return map;
    }

    /**
     * 修改检测信息数据信息
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "修改检测信息数据信息")
    @RequestMapping(value = "/updateAdsCheckInfo", method = RequestMethod.POST)
    public Object updateAdsCheckInfo(@RequestBody AdsCheckInfo adsCheckInfo) {
        try {
            adsCheckInfoService.update(adsCheckInfo);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除检测信息信息
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = " 删除检测信息信息")
    @RequestMapping(value = "/deleteAdsCheckInfo", method = RequestMethod.POST)
    public Object deleteAdsCheckInfo(AdsCheckInfo adsCheckInfo) {
        try {
            adsCheckInfoService.delete(adsCheckInfo.getId());
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 首次进入页面根据机构任务ID获取产品编码和样品编码以及下一个样品的ID
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "首次进入页面根据机构任务ID获取产品编码和样品编码以及下一个样品的ID")
    @RequestMapping(value = "/getNextPrimaryWithInfo", method = RequestMethod.POST)
    public Object getNextPrimaryWithInfo(AdsCheckInfo adsCheckInfo, String organId,String checkInfoId,String token) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if(!"".equals(checkInfoId)){
                AdsCheckInfo adsCheckInfo2 = adsCheckInfoService.queryById(checkInfoId);

                String nextId = adsCheckInfoService.getNextId(adsCheckInfo2.getSampleCode(),organId);

                String beforeId = adsCheckInfoService.getBeforeId(adsCheckInfo2.getSampleCode(),organId);
                map.put("chanp", adsCheckInfo2.getProductTraceabilityCode());//返回产品编码
                map.put("yangp", adsCheckInfo2.getSampleCode());//返回样品编码
                map.put("id", adsCheckInfo2.getId());//返回ID用于查询待检测的项
                if(null == beforeId){
                    map.put("before", "");//返回上一个样品的ID
                }else{
                    map.put("before", beforeId);//返回上一个样品的ID
                }
                if (null == nextId) {
                    map.put("after", "");//返回下一个样品的ID
                } else {
                    map.put("after", nextId);//返回下一个样品的ID
                }
                map.put("pageId", adsCheckInfo2.getMonitorSampleId());//返回抽样单key用于查看样品详细信息
                map.put("sampleName", adsCheckInfo2.getSampleName());//返回样品名称
                map.put("checkReport", adsCheckInfo2.getCheckReport());//检测上传状态  1:代表可以上报  0：代表不能上报
            }else {
                List<AdsCheckInfo> adsCheckInfos = adsCheckInfoService.getInfo(organId);
                System.out.println(adsCheckInfos.size());
                if (0 != adsCheckInfos.size()) {
                    AdsCheckInfo adsCheckInfo1 = adsCheckInfos.get(0);
                    map.put("chanp", adsCheckInfo1.getProductTraceabilityCode());//返回产品编码
                    map.put("yangp", adsCheckInfo1.getSampleCode());//返回样品编码
                    map.put("id", adsCheckInfo1.getId());//返回ID用于查询待检测的项
                    map.put("before", "");//返回上一个样品的ID，由于是第一次进来是第一个不会有上一个
                    if (1 < adsCheckInfos.size()) {
                        map.put("after", adsCheckInfos.get(1).getId());//返回下一个样品的ID
                    } else {
                        map.put("after", "");//返回下一个样品的ID
                    }
                    map.put("pageId", adsCheckInfo1.getMonitorSampleId());//返回抽样单key用于查看样品详细信息
                    map.put("sampleName", adsCheckInfo1.getSampleName());//返回样品名称
                    map.put("checkReport", adsCheckInfo1.getCheckReport());//检测上传状态  1:代表已上报  0：代表未上报
                }
            }
            return setSuccessModelMap(new ModelMap(), map);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取下一个
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取下一个")
    @RequestMapping(value = "/getAfter", method = RequestMethod.POST)
    public Object getAfter(String id,String organId,String token) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryById(id);

            String nextId = adsCheckInfoService.getNextId(adsCheckInfo.getSampleCode(),organId);

            String beforeId = adsCheckInfoService.getBeforeId(adsCheckInfo.getSampleCode(),organId);

            map.put("chanp", adsCheckInfo.getProductTraceabilityCode());//返回产品编码
            map.put("yangp", adsCheckInfo.getSampleCode());//返回样品编码
            map.put("id", adsCheckInfo.getId());//返回ID用于查询待检测的项
            if(null == beforeId){
                map.put("before", "");//返回上一个样品的ID
            }else{
                map.put("before", beforeId);//返回上一个样品的ID
            }
            if (null == nextId) {
                map.put("after", "");//返回下一个样品的ID
            } else {
                map.put("after", nextId);//返回下一个样品的ID
            }
            map.put("pageId", adsCheckInfo.getMonitorSampleId());//返回抽样单key用于查看样品详细信息
            map.put("sampleName", adsCheckInfo.getSampleName());//返回样品名称
            map.put("checkReport", adsCheckInfo.getCheckReport());//检测上传状态  1:代表可以上报  0：代表不能上报

            return setSuccessModelMap(new ModelMap(), map);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取上一个
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取上一个")
    @RequestMapping(value = "/getBefore", method = RequestMethod.POST)
    public Object getBefore(String id,String organId,String token) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryById(id);

            String beforeId = adsCheckInfoService.getBeforeId(adsCheckInfo.getSampleCode(),organId);

            String nextId = adsCheckInfoService.getNextId(adsCheckInfo.getSampleCode(),organId);

            map.put("chanp", adsCheckInfo.getProductTraceabilityCode());//返回产品编码
            map.put("yangp", adsCheckInfo.getSampleCode());//返回样品编码
            map.put("id", adsCheckInfo.getId());//返回ID用于查询待检测的项
            if (null == beforeId) {
                map.put("before", "");//返回下一个样品的ID
            } else {
                map.put("before", beforeId);//返回下一个样品的ID
            }
            map.put("after", nextId);//返回下一个样品的ID
            map.put("pageId", adsCheckInfo.getMonitorSampleId());//返回抽样单key用于查看样品详细信息
            map.put("sampleName", adsCheckInfo.getSampleName());//返回样品名称
            map.put("checkReport", adsCheckInfo.getCheckReport());//检测上传状态  1:代表可以上报  0：代表不能上报

            return setSuccessModelMap(new ModelMap(), map);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取检测项目
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取检测项目")
    @RequestMapping(value = "/getTestItem", method = RequestMethod.POST)
    public Object getTestItem(String id,String sampleCode,String token) {
        try {
            List<AdsInfoProject> pageInfo = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
            String id2 = id;
            if ((null == id && null == sampleCode)||"undefined".equals(id)) {
                return setSuccessModelMap(new ModelMap(), new ArrayList<AdsInfoProject>());
            } else {
                if (0 == pageInfo.size()) {

                    AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
                    if(null != sampleCode){
                        adsCheckInfo = adsCheckInfoService.queryBySampleCode(sampleCode);
                    }else {
                        adsCheckInfo = adsCheckInfoService.queryByMyId(id);
                    }


                    //获取机构任务
                    AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(adsCheckInfo.getOrganTaskId());
                    //获取监测任务
                    AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryByMyId(adsOrganTask.getMonitorTaskId());

                    String sampleName = adsCheckInfo.getSampleName();//样品名称
                    //通过模型ID和样品名称去获取所有的检测项目
                    List<AdsCheckModelMapping> adsCheckModelMappings = adsNewModelService.queryByModelIdAndName(adsMonitorTask.getCheckModel(),sampleName);

                    for (AdsCheckModelMapping adsCheckModelMapping : adsCheckModelMappings) {
                        Timestamp timestamp = new Timestamp(new Date().getTime());
                        AdsInfoProject adsInfoProject = new AdsInfoProject();
                        adsInfoProject.setCheckProject(adsCheckModelMapping.getCheckName());
                        adsInfoProject.setCheckNum(" ");
                        adsInfoProject.setJudgeNum(String.valueOf(adsCheckModelMapping.getMaxValue()));
                        adsInfoProject.setResult("-1");//1:代表合格 0:代表不合格 -1:代表未检测
                        adsInfoProject.setCheckInfoId(id);
                        adsInfoProject.setCreateBy("qq");
                        adsInfoProject.setCreateTime(timestamp);
                        adsInfoProject.setUpdateBy("qq");
                        adsInfoProject.setUpdateTime(timestamp);
                        adsInfoProject.setUnit(adsCheckModelMapping.getUnitMeasure());
                        adsInfoProjectService.add(adsInfoProject);
                    }

                    List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                    return setSuccessModelMap(new ModelMap(),myList);
                }
            }

            List<AdsInfoProject> pageInfo2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id2);
            return setSuccessModelMap(new ModelMap(), pageInfo2);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取检测项目
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取检测项目")
    @RequestMapping(value = "/getTestItemForB", method = RequestMethod.POST)
    public Object getTestItemForB(String id,String sampleCode,String token) {
        try {
            List<AdsInfoProject> pageInfo = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
            String id2 = id;
            if ((null == id && null == sampleCode)||"undefined".equals(id)) {
                return setSuccessModelMap(new ModelMap(), new ArrayList<AdsInfoProject>());
            } else {
                if (0 == pageInfo.size()) {

                    AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
                    if(null != sampleCode){
                        adsCheckInfo = adsCheckInfoService.queryBySampleCode(sampleCode);
                    }else {
                        adsCheckInfo = adsCheckInfoService.queryByMyId(id);
                    }

                    String sampleCode2 = adsCheckInfo.getSampleCode();

                    if (0 == pageInfo.size()) {
                        //不存在检测项目时
                        //根据样品编码和ID去新增检测项目
                        adsCheckInfoService.inzertInfoProject(id,sampleCode2);

                        List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                        return setSuccessModelMap(new ModelMap(),myList);
                    }else{
                        //存在检测项目时，更新上限值
                        adsInfoProjectService.updateInfoProject(pageInfo,sampleCode2);

                        List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                        return setSuccessModelMap(new ModelMap(),myList);
                    }
                }
            }

            AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
            if(null != sampleCode){
                adsCheckInfo = adsCheckInfoService.queryBySampleCode(sampleCode);
            }else {
                adsCheckInfo = adsCheckInfoService.queryByMyId(id);
            }

            String sampleCode2 = adsCheckInfo.getSampleCode();
            //存在检测项目时，更新上限值
            adsInfoProjectService.updateInfoProject(pageInfo,sampleCode2);

            List<AdsInfoProject> pageInfo2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id2);

            return setSuccessModelMap(new ModelMap(), pageInfo2);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取检测项目")
    @RequestMapping(value = "/getTestItemForRecheck", method = RequestMethod.POST)
    public Object getTestItemForRecheck(String id,String sampleCode,String token) {
        try {
            List<AdsInfoProject> pageInfo = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
            String id2 = id;
            if ((null == id && null == sampleCode)||"undefined".equals(id)) {
                return setSuccessModelMap(new ModelMap(), new ArrayList<AdsInfoProject>());
            } else {
                if (0 == pageInfo.size()) {

                    AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
                    if(null != sampleCode){
                        adsCheckInfo = adsCheckInfoService.queryBySampleCode(sampleCode);
                    }else {
                        adsCheckInfo = adsCheckInfoService.queryByMyId(id);
                    }


                    //获取机构任务
                    AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(adsCheckInfo.getOrganTaskId());
                    //获取监测任务
                    AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryByMyId(adsOrganTask.getMonitorTaskId());

                    String sampleName = adsCheckInfo.getSampleName();//样品名称
                    //通过模型ID和样品名称去获取所有的检测项目
                    List<AdsCheckModelMapping> adsCheckModelMappings = adsNewModelService.queryByModelIdAndName(adsMonitorTask.getCheckModel(),sampleName);

                    for (AdsCheckModelMapping adsCheckModelMapping : adsCheckModelMappings) {
                        Timestamp timestamp = new Timestamp(new Date().getTime());
                        AdsInfoProject adsInfoProject = new AdsInfoProject();
                        adsInfoProject.setCheckProject(adsCheckModelMapping.getCheckName());
                        adsInfoProject.setCheckNum(" ");
                        adsInfoProject.setJudgeNum(String.valueOf(adsCheckModelMapping.getMaxValue()));
                        adsInfoProject.setResult("-1");//1:代表合格 0:代表不合格 -1:代表未检测
                        adsInfoProject.setCheckInfoId(id);
                        adsInfoProject.setCreateBy("qq");
                        adsInfoProject.setCreateTime(timestamp);
                        adsInfoProject.setUpdateBy("qq");
                        adsInfoProject.setUpdateTime(timestamp);
                        adsInfoProject.setUnit(adsCheckModelMapping.getUnitMeasure());
                        adsInfoProjectService.add(adsInfoProject);
                    }

                    List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                    if(0 == myList.size()){
                        AdsCheckInfo adsCheckInfo2 = adsCheckInfoService.queryByMyId(id);
                        String sampleCode2 = adsCheckInfo2.getSampleCode();

                        adsCheckInfoService.inzertInfoProject(id,sampleCode2);

                        List<AdsInfoProject> myList3 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                        return setSuccessModelMap(new ModelMap(),myList3);
                    }
                    return setSuccessModelMap(new ModelMap(),myList);
                }
            }

            List<AdsInfoProject> pageInfo2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id2);
            return setSuccessModelMap(new ModelMap(), pageInfo2);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取检测信息列表
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "获取牵头单位检测详细信息列表")
    @RequestMapping(value = "/getPageInfoParam", method = RequestMethod.POST)
    public Object getPageInfoParam(AdsCheckInfo adsCheckInfo, int start, int length,String startTime,String endTime,String producingArea,String cityCode,String monitorClass) {
        try {

            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getPageInfoParam(adsCheckInfo, ((start + 1) / length) + 1, length,startTime,endTime,producingArea,cityCode,monitorClass);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取年度、
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取年度")
    @RequestMapping(value = "/getYear", method = RequestMethod.POST)
    public Object getYear() {
        try {

            List<Map<String, Object>> year = adsCheckInfoService.getYear();
            return setSuccessModelMap(new ModelMap(), year);

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /* 获取任务名、
            * @param
     * @return
             */
    @ApiOperation(value = "获取任务名称")
    @RequestMapping(value = "/getTaskName", method = RequestMethod.POST)
    public Object getTaskName(String monitorClass,String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                String organId = u.getOrgId();
                List<Map<String, Object>> TaskName = adsCheckInfoService.getTaskName(monitorClass,organId);
                return setSuccessModelMap(new ModelMap(), TaskName);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /* 获取任务名、
            * @param
     * @return
             */
    @ApiOperation(value = "获取样品名称")
    @RequestMapping(value = "/getSampleName", method = RequestMethod.POST)
    public Object getSampleName(@RequestBody String monitorClass) {
        try {

            List<Map<String, Object>> SampleName = adsCheckInfoService.getSampleName(monitorClass);
            return setSuccessModelMap(new ModelMap(), SampleName);

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /* 获取结果、
            * @param
     * @return
             */
    @ApiOperation(value = "获取结果")
    @RequestMapping(value = "/getResult",method = RequestMethod.POST)
    public Object getResult() {
        try{
            List<Map<String,Object>> result =adsCheckInfoService.getResult();
            return setSuccessModelMap(new ModelMap(),result);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取抽样单位、
     * @param
     * @return
     */
    @ApiOperation(value = "获取抽样单位")
    @RequestMapping(value = "/getSampleDeparment",method = RequestMethod.POST)
    public Object getSampleDeparment(@RequestBody String monitorClass) {
        try{
            List<Map<String,Object>> sampleDeparment =adsCheckInfoService.getSampleDeparment(monitorClass);
            return setSuccessModelMap(new ModelMap(),sampleDeparment);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取检测单位
     * @param
     * @return
     */
    @ApiOperation(value = "获取检测单位")
    @RequestMapping(value = "/getCheckOrgan",method = RequestMethod.POST)
    public Object getCheckOrgan(@RequestBody String monitorClass) {
        try{
            List<Map<String,Object>> checkOrgan =adsCheckInfoService.getCheckOrgan(monitorClass);
            return setSuccessModelMap(new ModelMap(),checkOrgan);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取检测环节
     * @param
     * @return
     */
    @ApiOperation(value = "获取检测环节")
    @RequestMapping(value = "/getCheckLink",method = RequestMethod.POST)
    public Object getCheckLink() {
        try{
            List<Map<String,Object>> checkLink =adsCheckInfoService.getCheckLink();
            return setSuccessModelMap(new ModelMap(),checkLink);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取受检单位
     * @param
     * @return
     */
    @ApiOperation(value = "获取受检单位")
    @RequestMapping(value = "/getTestedDeparment",method = RequestMethod.POST)
    public Object getTestedDeparment(@RequestBody String monitorClass) {
        try{
            List<Map<String,Object>> testedDeparment =adsCheckInfoService.getTestedDeparment(monitorClass);
            return setSuccessModelMap(new ModelMap(),testedDeparment);
        }catch (Exception e){
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
     * 根据条件获取检测信息列表
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "获取牵头检测信息信息列表")
    @RequestMapping(value = "/getPageInfoWithParam", method = RequestMethod.POST)
    public Object getPageInfoWithParam(AdsCheckInfo adsCheckInfo, int start, int length,String startTime,String endTime,String producingArea,String cityCode,String monitorClass,String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsCheckInfo.setCheckOrganId(u.getOrgId());
                PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getPageInfoWithParam(adsCheckInfo, ((start + 1) / length) + 1, length,startTime,endTime,producingArea,cityCode,monitorClass);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            }else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
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
    @SystemControllerLog(description = "获取承担单位检测信息信息列表", operationType = "查询")
    @RequestMapping(value = "/getInfoByOrgName", method = RequestMethod.POST)
    @Authorization
    public Object getInfoByOrgName(@RequestHeader(required = true) String token,AdsCheckInfo adsCheckInfo, int start, int length,String startTime,String endTime,String producingArea,String cityCode,String monitorClass,String orgId) {
        try {
            /*orgId = "ef63553425e14cedb06e51009d97c8bf40428f5a31a6469381a160f365d525c1";*/
            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getInfoByOrgName(adsCheckInfo, ((start + 1) / length) + 1, length,startTime,endTime,producingArea,cityCode,monitorClass,orgId);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 根据条件获取承担单位所有检测信息列表
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "获取承担单位检测信息信息列表")
    @RequestMapping(value = "/getInfoByOrgNameAll", method = RequestMethod.POST)
    public Object getInfoByOrgNameAll(AdsCheckInfo adsCheckInfo, int start, int length,String startTime,String endTime,String producingArea,String cityCode,String monitorClass,String orgId) {
        try {
             /*orgId = "ef63553425e14cedb06e51009d97c8bf40428f5a31a6469381a160f365d525c1";*/
            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getInfoByOrgNameAll(adsCheckInfo, ((start + 1) / length) + 1, length,startTime,endTime,producingArea,cityCode,monitorClass,orgId);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取检测信息列表
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "获取检测信息信息列表")
    @RequestMapping(value = "/getPageInfoWithParams", method = RequestMethod.POST)
    public Object getPageInfoWithParams(String ids) {
        try {
            List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
            if(null != ids && !"".equals(ids)){
                String realIds = ids.substring(0,ids.length()-1);
                String[] a = realIds.split(",");
                List<AdsCheckInfo> list = new ArrayList<AdsCheckInfo>();
                for(String id : a){
                    AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryById(id);
                    list.add(adsCheckInfo);
                }
                //序号
                int rn = 1;
                for(AdsCheckInfo adsCheckInfo : list){
                    Map<String,Object> map = new HashedMap();
                    map.put("productCode",adsCheckInfo.getProductCode());
                    map.put("sampleCode",adsCheckInfo.getSampleCode());
                    map.put("sampleName",adsCheckInfo.getSampleName());
                    map.put("testedDeparment",adsCheckInfo.getTestedDeparment());
                    map.put("sampleDeparment",adsCheckInfo.getSampleDeparment());
                    map.put("result",adsCheckInfo.getResult());
                    //查询机构任务
                    String organTaskId = adsCheckInfo.getOrganTaskId();
                    AdsOrganTask task = adsOrganTaskService.queryById(organTaskId);
                    map.put("detectionOrgan",task.getDetectionOrgan());
                    map.put("rn",rn);
                    map.put("id",adsCheckInfo.getId());
                    rn++;
                    maps.add(map);
                }
            }
            return setSuccessModelMap(new ModelMap(), maps);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取检测项目
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取检测项目")
    @RequestMapping(value = "/getChild", method = RequestMethod.POST)
    public Object getChild(String id,String token) {
        try {
            List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
            if (0 == myList.size()) {

                AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryByMyId(id);

                String sampleName = adsCheckInfo.getSampleName();//样品名称

                //获取机构任务
                AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(adsCheckInfo.getOrganTaskId());
                //获取监测任务
                AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryByMyId(adsOrganTask.getMonitorTaskId());

                //通过模型ID和样品名称去获取所有的检测项目
                List<AdsCheckModelMapping> adsCheckModelMappings = adsNewModelService.queryByModelIdAndName(adsMonitorTask.getCheckModel(),sampleName);

                for (AdsCheckModelMapping adsCheckModelMapping : adsCheckModelMappings) {
                    Timestamp timestamp = new Timestamp(new Date().getTime());
                    AdsInfoProject adsInfoProject = new AdsInfoProject();
                    adsInfoProject.setCheckProject(adsCheckModelMapping.getCheckName());
                    adsInfoProject.setCheckNum(" ");
                    adsInfoProject.setJudgeNum(String.valueOf(adsCheckModelMapping.getMaxValue()));
                    adsInfoProject.setResult("-1");//1:代表合格 0:代表不合格 -1:代表未检测
                    adsInfoProject.setCheckInfoId(id);
                    adsInfoProject.setCreateBy("qq");
                    adsInfoProject.setCreateTime(timestamp);
                    adsInfoProject.setUpdateBy("qq");
                    adsInfoProject.setUpdateTime(timestamp);
                    adsInfoProject.setUnit(adsCheckModelMapping.getUnitMeasure());
                    adsInfoProjectService.add(adsInfoProject);
                }

                //通过样品名称去查基础数据里面有没有这个样品对象
//                AdsModelCheckObject adsModelCheckObject = adsModelCheckObjectService.queryByName(sampleName);
//
//                if(null != adsModelCheckObject){
//                    //根据检测对象查检测项目集合
//                    List<AdsModelCheckItem> adsModelCheckItems = adsModelCheckItemService.queryByObjId(adsModelCheckObject.getId());
//
//                    for (AdsModelCheckItem adsModelCheckItem : adsModelCheckItems) {
//
//                        //通过检测项目ID查找检测标准
//                        AdsModelCheckStandard adsModelCheckStandard = adsModelCheckStandardService.queryByMyId(adsModelCheckItem.getStandardId());
//
//                        Timestamp timestamp = new Timestamp(new Date().getTime());
//                        AdsInfoProject adsInfoProject = new AdsInfoProject();
//                        adsInfoProject.setCheckProject(adsModelCheckItem.getName());
//                        adsInfoProject.setCheckNum("0");
//                        adsInfoProject.setJudgeNum(adsModelCheckStandard.getValue().toString());
//                        adsInfoProject.setResult("-1");//1:代表合格 0:代表不合格 -1:代表未检测
//                        adsInfoProject.setCheckInfoId(id);
//                        adsInfoProject.setCreateBy("qq");
//                        adsInfoProject.setCreateTime(timestamp);
//                        adsInfoProject.setUpdateBy("qq");
//                        adsInfoProject.setUpdateTime(timestamp);
//                        adsInfoProjectService.add(adsInfoProject);
//                    }
//                }

                List<AdsInfoProject> myList2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                return setSuccessModelMap(new ModelMap(),myList2);
            }
            return setSuccessModelMap(new ModelMap(),myList);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取检测项目")
    @RequestMapping(value = "/getChildForRecheck", method = RequestMethod.POST)
    public Object getChildForRecheck(String id,String token) {
        try {
            List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
            if (0 == myList.size()) {

                AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryByMyId(id);

                String sampleName = adsCheckInfo.getSampleName();//样品名称

                //获取机构任务
                AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(adsCheckInfo.getOrganTaskId());
                //获取监测任务
                AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryByMyId(adsOrganTask.getMonitorTaskId());

                //通过模型ID和样品名称去获取所有的检测项目
                List<AdsCheckModelMapping> adsCheckModelMappings = adsNewModelService.queryByModelIdAndName(adsMonitorTask.getCheckModel(),sampleName);

                for (AdsCheckModelMapping adsCheckModelMapping : adsCheckModelMappings) {
                    Timestamp timestamp = new Timestamp(new Date().getTime());
                    AdsInfoProject adsInfoProject = new AdsInfoProject();
                    adsInfoProject.setCheckProject(adsCheckModelMapping.getCheckName());
                    adsInfoProject.setCheckNum(" ");
                    adsInfoProject.setJudgeNum(String.valueOf(adsCheckModelMapping.getMaxValue()));
                    adsInfoProject.setResult("-1");//1:代表合格 0:代表不合格 -1:代表未检测
                    adsInfoProject.setCheckInfoId(id);
                    adsInfoProject.setCreateBy("qq");
                    adsInfoProject.setCreateTime(timestamp);
                    adsInfoProject.setUpdateBy("qq");
                    adsInfoProject.setUpdateTime(timestamp);
                    adsInfoProject.setUnit(adsCheckModelMapping.getUnitMeasure());
                    adsInfoProjectService.add(adsInfoProject);
                }
                List<AdsInfoProject> myList2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                if(0 == myList2.size()){
                    AdsCheckInfo adsCheckInfo2 = adsCheckInfoService.queryByMyId(id);
                    String sampleCode = adsCheckInfo2.getSampleCode();

                    adsCheckInfoService.inzertInfoProject(id,sampleCode);

                    List<AdsInfoProject> myList3 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                    return setSuccessModelMap(new ModelMap(),myList3);
                }
                return setSuccessModelMap(new ModelMap(),myList2);
            }
            return setSuccessModelMap(new ModelMap(),myList);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取检测项目
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "通过样品编码获取其他系统检测项目")
    @RequestMapping(value = "/getChildForB", method = RequestMethod.POST)
    public Object getChildForB(String id,String token) {
        try {
            List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);

            AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryByMyId(id);
            String sampleCode = adsCheckInfo.getSampleCode();

            if (0 == myList.size()) {
                //不存在检测项目时
                //根据样品编码和ID去新增检测项目
                adsCheckInfoService.inzertInfoProject(id,sampleCode);

                List<AdsInfoProject> myList2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                return setSuccessModelMap(new ModelMap(),myList2);
            }else{
                //存在检测项目时，更新上限值
                adsInfoProjectService.updateInfoProject(myList,sampleCode);

                List<AdsInfoProject> myList2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                return setSuccessModelMap(new ModelMap(),myList2);
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "通过样品编码获取其他系统检测项目")
    @RequestMapping(value = "/getChildForC", method = RequestMethod.POST)
    public Object getChildForC(String id,String token) {
        try {
            List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);

            AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryByMyId(id);
            String sampleCode = adsCheckInfo.getSampleCode();

            if (0 == myList.size()) {
                //不存在检测项目时
                //根据样品编码和ID去新增检测项目
                adsCheckInfoService.inzertInfoProjectForC(id,sampleCode);

                List<AdsInfoProject> myList2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                return setSuccessModelMap(new ModelMap(),myList2);
            }else{
                //存在检测项目时，更新上限值
                adsInfoProjectService.updateInfoProjectForC(myList,sampleCode);

                List<AdsInfoProject> myList2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                return setSuccessModelMap(new ModelMap(),myList2);
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除检测信息
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "逻辑删除检测信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(String ids) {
        try {
            if(null != ids && !"".equals(ids)){
                String realIds = ids.substring(0,ids.length()-1);
                String[] a = realIds.split(",");
                List<AdsCheckInfo> list = new ArrayList<AdsCheckInfo>();
                for(String id : a){
                    adsCheckInfoService.deleteInFlag(id);
                }
            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsCheckInfoController.delete:逻辑删除检测信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据sampleCode获取单条抽样数据信息
     * @param sampleCode
     * @return
     */
    @ApiOperation(value = "获取单条抽样数据信息")
    @RequestMapping(value = "/getSampleBySampleCode",method = RequestMethod.POST)
    public Map<String, Object> getSampleBySampleCode(String sampleCode){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String,Object>> adsMonitorSample =adsCheckInfoService.getSampleBySampleCode(sampleCode);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsMonitorSample",adsMonitorSample);
        return map;
    }

    /**
     * 获取区域ID
     * @param
     * @return
     */
    @ApiOperation(value = "获取区域ID")
    @RequestMapping(value = "/getProducingArea",method = RequestMethod.POST)
    public Object getProducingArea() {
        try{
            List<Map<String,Object>> producingArea =adsCheckInfoService.getProducingArea();
            return setSuccessModelMap(new ModelMap(),producingArea);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据机构任务ID获取检测信息列表
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "根据机构任务ID获取检测信息信息列表")
    @RequestMapping(value = "/getPageInfoWithOrgTaskId", method = RequestMethod.POST)
    public Object getPageInfoWithOrgTaskId(AdsCheckInfo adsCheckInfo, int start, int length,String organTaskId,String token) {
        try {
            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getPageInfoWithOrgTaskId(adsCheckInfo, ((start + 1) / length) + 1, length,organTaskId);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取帮助中心列表")
    @RequestMapping(value = "/getHelpList", method = RequestMethod.POST)
    public Object getHelpList(String token) {
        try {
            SysDocumentQueryParam sysDocumentQueryParam = new SysDocumentQueryParam();
            PageInfo<SysDocument> pageInfo = adsCheckInfoService.getDocumentList(sysDocumentQueryParam);
            List<SysDocument> list = pageInfo.getList();
            List<SysDocument> list2 = new ArrayList();
            for(SysDocument sysDocument : list){
                String enableFlag = sysDocument.getEnableFlag();
                if("Y".equals(enableFlag)){
                    list2.add(sysDocument);
                }
            }
            return setSuccessModelMap(new ModelMap(), list2);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取帮助中心信息")
    @RequestMapping(value = "/getHelpListInfo", method = RequestMethod.POST)
    public Object getHelpListInfo(String id,String token) {
        try {
            SysDocument sysDocument = adsCheckInfoService.getDocument(id);
            return setSuccessModelMap(new ModelMap(), sysDocument);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据机构任务ID获取检测信息信息列表并新增检测信息对象
     *
     * @param adsCheckInfo
     * @return
     */
    @ApiOperation(value = "根据机构任务ID获取检测信息信息列表并新增检测信息对象")
    @SystemControllerLog(description = "根据机构任务ID获取检测信息信息列表并新增检测信息对象", operationType = "查询")
    @RequestMapping(value = "/getPageInfoWithOrgTaskIdAndInsert", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfoWithOrgTaskIdAndInsert(AdsCheckInfo adsCheckInfo, int start, int length,String organTaskId,String token) {
        try {
            List<AdsMonitorSample> adsMonitorSamples = adsMonitorSampleService.queryByOrgTaskId(organTaskId);
            for(AdsMonitorSample adsMonitorSample : adsMonitorSamples){
                AdsCheckInfo adsCheckInfo1 = adsCheckInfoService.queryBySampleId(adsMonitorSample.getId());
                if(null == adsCheckInfo1){
                    adsMonitorSample = adsMonitorSampleService.queryById(adsMonitorSample.getId());
                    AdsCheckInfo adsCheckInfo2 = new AdsCheckInfo();
                    adsCheckInfo2.setOrganTaskId(adsMonitorSample.getOrganTaskId());
                    adsCheckInfo2.setSampleBarCode(adsMonitorSample.getSampleBarCode());
                    adsCheckInfo2.setMonitorSampleId(adsMonitorSample.getId());
                    if(null == adsMonitorSample.getSampleOrganId()){
                        adsCheckInfo2.setCheckOrganId("1");
                    }else{
                        adsCheckInfo2.setCheckOrganId(adsMonitorSample.getSampleOrganId());
                    }
                    adsCheckInfo2.setCheckLink(adsMonitorSample.getSamplePlace());
                    long time = new Date().getTime();
                    adsCheckInfo2.setCheckTime(new Timestamp(time));
                    adsCheckInfo2.setResult("-1");
                    adsCheckInfo2.setSampleBarCode("111");
                    adsCheckInfo2.setCheckReport("0");
                    adsCheckInfo2.setOrganTaskId(adsMonitorSample.getOrganTaskId());
                    adsCheckInfo2.setTestedDeparment(adsMonitorSample.getTestedDeparment());
                    adsCheckInfo2.setSampleDeparment(adsMonitorSample.getSampleDeparment());
                    adsCheckInfo2.setCheckOrgan(adsMonitorSample.getSampleOrgan());
                    adsCheckInfo2.setSampleCode(adsMonitorSample.getSampleCode());
                    adsCheckInfo2.setSampleName(adsMonitorSample.getSampleName());
                    if(null == adsMonitorSample.getProductTraceabilityCode()){
                        adsCheckInfo2.setProductTraceabilityCode("1");
                    }else{
                        adsCheckInfo2.setProductTraceabilityCode(adsMonitorSample.getProductTraceabilityCode());
                    }
                    adsCheckInfo2.setCreateBy(adsMonitorSample.getCreateBy());
                    adsCheckInfo2.setUpdateBy(adsMonitorSample.getUpdateBy());
                    adsCheckInfo2.setDelFlag("N");
                    adsCheckInfoService.addAdsCheckInfo(adsCheckInfo2);//初始化检测信息
                }
            }
            PageInfo<AdsCheckInfo> pageInfo = adsCheckInfoService.getPageInfoWithOrgTaskId(adsCheckInfo, ((start + 1) / length) + 1, length,organTaskId);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取任务名称
     *
     * @param organTaskId
     * @return
     */
    @ApiOperation(value = "获取任务名称")
    @SystemControllerLog(description = "获取任务名称", operationType = "查询")
    @RequestMapping(value = "/getTaskNameByOrganTaskId", method = RequestMethod.POST)
    @Authorization
    public Object getTaskNameByOrganTaskId(String organTaskId,String token) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(organTaskId);
            AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryById(adsOrganTask.getMonitorTaskId());
            map.put("taskName",adsMonitorTask.getTaskName());
            map.put("publishStatus", adsMonitorTask.getPublishStatus());

            return setSuccessModelMap(new ModelMap(), map);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除（重置判定结果为-1）
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除（重置判定结果为-1）")
    @RequestMapping(value = "/resetResult", method = RequestMethod.POST)
    public Object resetResult(String ids,String token) {
        try {
            if(null != ids && !"".equals(ids)){
                String realIds = ids.substring(0,ids.length()-1);
                String[] a = realIds.split(",");
                List<AdsCheckInfo> list = new ArrayList<AdsCheckInfo>();
                for(String id : a){
                    adsCheckInfoService.resetResult(id);
                }
            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsCheckInfoController.resetResult:重置判定结果异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 导出监测任务excel
     * @param monitorClass
     * @param response
     */
    @ApiOperation(value = "导出监测任务excel")
    @RequestMapping(value = "/getCheckList",method = RequestMethod.POST)
    public void getCheckList(AdsCheckInfo adsCheckInfo,String startTime,String endTime,String producingArea,String cityCode,String monitorClass,String token,HttpServletResponse response){
        try{
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsCheckInfo.setCheckOrganId(u.getOrgId());
                List<AdsCheckInfo> adsCheckInfoList = adsCheckInfoService.getCheckListWithParam(adsCheckInfo, startTime, endTime, producingArea, cityCode, monitorClass);
                if (adsCheckInfoList != null && adsCheckInfoList.size() > 0) {
                    List<Map> list = new ArrayList<>();
                    String[] columns = {"任务名称", "产品编码", "样品编码", "样品名称", "受检单位", "抽样单位", "抽样时间", "检测单位", "检测时间", "判定结果"};
                    String[] propertyNames = {"taskName", "productTraceabilityCode", "sampleCode", "sampleName", "testedDeparment", "sampleDeparment", "sampleDate", "checkOrgan", "checkTime", "result"};
                    AdsOrganTask aot = null;
                    AdsMonitorTask amt = null;
                    String taskName = null;
                    for (AdsCheckInfo task : adsCheckInfoList) {
                        if (task.getOrganTaskId() != null && !"".equals(task.getOrganTaskId())) {
                            aot = adsOrganTaskService.queryById(task.getOrganTaskId());
                            if (aot.getMonitorTaskId() != null && !"".equals(aot.getMonitorTaskId())) {
                                amt = adsMonitorTaskService.queryByMyId(aot.getMonitorTaskId());
                                taskName = amt.getTaskName();
                            } else {
                                taskName = "";
                            }
                        } else {
                            taskName = "";
                        }
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("taskName", taskName);
                        map.put("productTraceabilityCode", task.getProductTraceabilityCode());
                        map.put("sampleCode", task.getSampleCode());
                        map.put("sampleName", task.getSampleName());
                        map.put("testedDeparment", task.getTestedDeparment());
                        map.put("sampleDeparment", task.getSampleDeparment());
                        map.put("sampleDate", task.getSampleDate());
                        map.put("checkOrgan", task.getCheckOrgan());
                        map.put("checkTime", task.getCheckTime());
                        String rst = "";
                        if ("1".equals(task.getResult())) {
                            rst = "合格";
                        } else if ("0".equals(task.getResult())) {
                            rst = "不合格";
                        } else if ("-1".equals(task.getResult())) {
                            rst = "暂未检测";
                        } else {
                            rst = "未知";
                        }
                        map.put("result", rst);
                        list.add(map);
                    }
                    //导出excel
                    ExportExcelUtil.exportView(response, list, columns, propertyNames);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 导出监测任务excel
     * @param monitorClass
     * @param response
     */
    @ApiOperation(value = "导出监测任务excel")
    @RequestMapping(value = "/getCheckListByCheck",method = RequestMethod.POST)
    public void getCheckListByCheck(String ids,String monitorClass,String token,HttpServletResponse response){
        try{
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                List<AdsCheckInfo> adsCheckInfoList = adsCheckInfoService.getCheckListByCheck(ids);
                if (adsCheckInfoList != null && adsCheckInfoList.size() > 0) {
                    List<Map> list = new ArrayList<>();
                    String[] columns = {"任务名称", "产品编码", "样品编码", "样品名称", "受检单位", "抽样单位", "抽样时间", "检测单位", "检测时间", "判定结果"};
                    String[] propertyNames = {"taskName", "productTraceabilityCode", "sampleCode", "sampleName", "testedDeparment", "sampleDeparment", "sampleDate", "checkOrgan", "checkTime", "result"};
                    AdsOrganTask aot = null;
                    AdsMonitorTask amt = null;
                    String taskName = null;
                    for (AdsCheckInfo task : adsCheckInfoList) {
                        if (task.getOrganTaskId() != null && !"".equals(task.getOrganTaskId())) {
                            aot = adsOrganTaskService.queryById(task.getOrganTaskId());
                            if (aot.getMonitorTaskId() != null && !"".equals(aot.getMonitorTaskId())) {
                                amt = adsMonitorTaskService.queryByMyId(aot.getMonitorTaskId());
                                taskName = amt.getTaskName();
                            } else {
                                taskName = "";
                            }
                        } else {
                            taskName = "";
                        }
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("taskName", taskName);
                        map.put("productTraceabilityCode", task.getProductTraceabilityCode());
                        map.put("sampleCode", task.getSampleCode());
                        map.put("sampleName", task.getSampleName());
                        map.put("testedDeparment", task.getTestedDeparment());
                        map.put("sampleDeparment", task.getSampleDeparment());
                        map.put("sampleDate", task.getSampleDate());
                        map.put("checkOrgan", task.getCheckOrgan());
                        map.put("checkTime", task.getCheckTime());
                        String rst = "";
                        if ("1".equals(task.getResult())) {
                            rst = "合格";
                        } else if ("0".equals(task.getResult())) {
                            rst = "不合格";
                        } else if ("-1".equals(task.getResult())) {
                            rst = "暂未检测";
                        } else {
                            rst = "未知";
                        }
                        map.put("result", rst);
                        list.add(map);
                    }
                    //导出excel
                    ExportExcelUtil.exportView(response, list, columns, propertyNames);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 上报抽样单和抽样信息
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "上报抽样单和抽样信息")
    @RequestMapping(value = "/upInfo", method = RequestMethod.POST)
    public Object upInfo(String ids,String organTaskId,String token) {
        try {
            if(null != ids && !"".equals(ids)){
                String realIds = ids.substring(0,ids.length()-1);
                String[] a = realIds.split(",");
                List<AdsCheckInfo> list = new ArrayList<AdsCheckInfo>();
                List<AdsInfoProject> adsInfoProjects = new ArrayList<AdsInfoProject>();
                for(String id : a){
                    AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
                    adsCheckInfo = adsCheckInfoService.queryById(id);
                    list.add(adsCheckInfo);
                    List<AdsInfoProject> oneList = new ArrayList<AdsInfoProject>();
                    oneList = adsInfoProjectService.queryListByInfoId(id);
                    adsInfoProjects.addAll(oneList);
                }
                // FIXME: 2016-12-02 调用其他项目的接口同步数据给他们

                //修改上报状态为已上报
                for(String id : a){
                    adsCheckInfoService.updateReport(id);
                }

                //修改机构任务的检测上报状态和检测完成数量和检测上报时间
                int num = adsCheckInfoService.getFinish(organTaskId);

                AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(organTaskId);

                BigDecimal bigNum = adsOrganTask.getNumbers();

                int nums = bigNum.intValue();

                int status = 1;
                if(num >= nums){
                    status = 2;
                }

                adsOrganTaskService.updateCheckInfo(organTaskId,num,status);

            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsCheckInfoController.upInfo:上报抽样单和抽样信息操作异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取当前用户账号信息 By Token")
    @RequestMapping(value = "/findSysUserByToken",method = RequestMethod.POST)
    public Object findSysUserByToken(String token){
        try{
            Assert.isNotBlank(token,"token");
            ModelMap modelMap = new ModelMap();
            CurrentUser u= WebUtil.getCurrentUser(token);
            modelMap.addAttribute("user", u);
            return setSuccessModelMap(modelMap);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param monitorClass
     * @param response
     */
    @ApiOperation(value = "导出监测任务详细excel")
    @RequestMapping(value = "/getCheckListAll",method = RequestMethod.POST)
    public void getCheckListAll(  String monitorClass,HttpServletResponse response){
        try{
            List<AdsCheckInfo> adsCheckInfoList = adsCheckInfoService.getCheckListAll(monitorClass);
            if(adsCheckInfoList!=null && adsCheckInfoList.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"任务名称","产品编码","样品编码","样品名称","受检单位","抽样单位","抽样时间","检测单位","检测时间","判定结果","抽样地区","检测项目","检测值","判定值"};
                String[] propertyNames = {"taskName","productTraceabilityCode","sampleCode","sampleName","testedDeparment","sampleDeparment","sampleDate","checkOrgan","checkTime","result","producingArea","checkProject","checkNum","judgeNum"};
                AdsOrganTask aot=null;
                AdsMonitorTask amt=null;
                for(AdsCheckInfo task : adsCheckInfoList){
                    if(task.getOrganTaskId()!=null&&!"".equals(task.getOrganTaskId())){
                        aot=adsOrganTaskService.queryById(task.getOrganTaskId());
                        if(aot.getMonitorTaskId()!=null&&!"".equals(aot.getMonitorTaskId())){
                            amt=adsMonitorTaskService.queryByMyId(aot.getMonitorTaskId());
                        }
                    }
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("taskName",amt.getTaskName());
                    map.put("productTraceabilityCode",task.getProductTraceabilityCode());
                    map.put("sampleCode",task.getSampleCode());
                    map.put("sampleName",task.getSampleName());
                    map.put("testedDeparment",task.getTestedDeparment());
                    map.put("sampleDeparment",task.getSampleDeparment());
                    map.put("sampleDate",task.getSampleDate());
                    map.put("checkOrgan",task.getCheckOrgan());
                    map.put("checkTime",task.getCheckTime());
                    map.put("result",task.getResult());
                    map.put("producingArea",task.getProducingArea());
                    map.put("checkProject",task.getCheckProject());
                    map.put("checkNum",task.getCheckNum());
                    map.put("judgeNum",task.getJudgeNum());
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorClass
     * @param response
     */
    @ApiOperation(value = "导出承担单位监测任务excel")
    @RequestMapping(value = "/getOrgList",method = RequestMethod.POST)
    public void getOrgList(  String monitorClass,String orgId,HttpServletResponse response){
        try{
            List<AdsCheckInfo> adsCheckInfoList = adsCheckInfoService.getOrgList(monitorClass,orgId);
            if(adsCheckInfoList!=null && adsCheckInfoList.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"产品编码","样品编码","样品名称","受检单位","抽样单位","检测单位","判定结果"};
                String[] propertyNames = {"productTraceabilityCode","sampleCode","sampleName","testedDeparment","sampleDeparment","checkOrgan","result"};
                for(AdsCheckInfo task : adsCheckInfoList){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("productTraceabilityCode",task.getProductTraceabilityCode());
                    map.put("sampleCode",task.getSampleCode());
                    map.put("sampleName",task.getSampleName());
                    map.put("testedDeparment",task.getTestedDeparment());
                    map.put("sampleDeparment",task.getSampleDeparment());
                    map.put("checkOrgan",task.getCheckOrgan());
                    map.put("result",task.getResult());
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorClass
     * @param response
     */
    @ApiOperation(value = "导出承担监测任务详细excel")
    @RequestMapping(value = "/getOrgListAll",method = RequestMethod.POST)
    public void getOrgListAll(  String monitorClass,String orgId,HttpServletResponse response){
        try{
            List<AdsCheckInfo> adsCheckInfoList = adsCheckInfoService.getOrgList(monitorClass,orgId);
            if(adsCheckInfoList!=null && adsCheckInfoList.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"产品编码","样品编码","样品名称","受检单位","抽样单位","检测单位","判定结果","抽样地区","检测项目","检测值","判定值"};
                String[] propertyNames = {"productTraceabilityCode","sampleCode","sampleName","testedDeparment","sampleDeparment","checkOrgan","result","producingArea","checkProject","checkNum","judgeNum"};
                for(AdsCheckInfo task : adsCheckInfoList){
                    HashMap<String,Object> map = new HashMap<>();

                    map.put("productTraceabilityCode",task.getProductTraceabilityCode());
                    map.put("sampleCode",task.getSampleCode());
                    map.put("sampleName",task.getSampleName());
                    map.put("testedDeparment",task.getTestedDeparment());
                    map.put("sampleDeparment",task.getSampleDeparment());
                    map.put("checkOrgan",task.getCheckOrgan());
                    map.put("result",task.getResult());
                    map.put("producingArea",task.getProducingArea());
                    map.put("checkProject",task.getCheckProject());
                    map.put("checkNum",task.getCheckNum());
                    map.put("judgeNum",task.getJudgeNum());
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sampleCode
     * @param response
     */
    @ApiOperation(value = "导出监测任务excel")
    @RequestMapping(value = "/getSampleList",method = RequestMethod.POST)
    public void getSampleList(String sampleCode,HttpServletResponse response){
        try{
            List<AdsCheckInfo> adsCheckInfoList = adsCheckInfoService.getSampleList(sampleCode);
            if(adsCheckInfoList!=null && adsCheckInfoList.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"样品名称","样品编码","商标","包装","等级","标识","型号规格","执行标准","生成日期或批号","产品认证情况","证书编号","抽样基数","抽样场所","受检单位情况","受检单位名称","通讯地址","法定人代表","联系人","电话","传真","受检人","电话","传真","生产单位情况","单位名称","通讯地址","邮编","联系人","电话","传真","抽样单位情况","单位名称","联系人","通讯地址","邮编","联系电话","传真","E-mail","检测任务依据","抽样时间"," 抽样人员"};
                String[] propertyNames = {"sampleName","sampleCode","tradeMark","packing","grade","identify","specification","standard","produceDate","produceCertificate","certificateCode","sampleBase","samplePlace"," ","testedDeparment","testedAddress","testedLegalrep","testedLinkman","testedLinkmanphone","testedLinkmanfax","testedPerson","testedPersonphone","testedPersonfax","productionsTatus","productionDeparment","productionAddress","productionZipcode","productionLinkman","productionLinkmanphone","productionLinkmanfax","","sampleDeparment","sampleLinkman","sampleAddress","sampleZipcode","samplePhone","sampleFax","sampleEmail","proof","sampleDate","samplePerson"};
                for(AdsCheckInfo task : adsCheckInfoList){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("sampleName",task.getSampleName());
                    map.put("SampleCode",task.getSampleCode());
                    map.put("tradeMark",task.getTradeMark());
                    map.put("packing",task.getPacking());
                    map.put("grade",task.getGrade());
                    map.put("identify",task.getIdentify());
                    map.put("specification",task.getSpecification());
                    map.put("standard",task.getStandard());
                    map.put("produceDate",task.getProduceDate());
                    map.put("produceCertificate",task.getProduceCertificate());
                    map.put("certificateCode",task.getCertificateCode());
                    map.put("sampleBase",task.getSampleBase());
                    map.put("samplePlace",task.getSamplePlace());
                    map.put("testedDeparment",task.getTestedDeparment());
                    map.put("testedAddress",task.getTestedAddress());
                    map.put("testedLegalrep",task.getTestedLegalrep());
                    map.put("testedLinkman",task.getTestedLinkman());
                    map.put("testedLinkmanphone",task.getTestedLinkmanphone());
                    map.put("testedLinkmanfax",task.getTestedLinkmanfax());
                    map.put("testedPerson",task.getTestedPerson());
                    map.put("testedPersonphone",task.getTestedPersonphone());
                    map.put("testedPersonfax",task.getTestedPersonfax());
                    map.put("productionsTatus",task.getProductionsTatus());
                    map.put("productionDeparment",task.getProductionDeparment());
                    map.put("productionAddress",task.getProductionAddress());
                    map.put("productionZipcode",task.getProductionZipcode());
                    map.put("productionLinkman",task.getProductionLinkman());
                    map.put("productionLinkmanphone",task.getProductionLinkmanphone());
                    map.put("productionLinkmanfax",task.getProductionLinkmanfax());
                    map.put("sampleDeparment",task.getSampleDeparment());
                    map.put("sampleLinkman",task.getSampleLinkman());
                    map.put("sampleAddress",task.getSampleAddress());
                    map.put("sampleZipcode",task.getSampleZipcode());
                    map.put("samplePhone",task.getSamplePhone());
                    map.put("sampleFax",task.getSampleFax());
                    map.put("sampleEmail",task.getSampleEmail());
                    map.put("proof",task.getProof());
                    map.put("sampleDate",task.getSampleDate());
                    map.put("samplePerson",task.getSamplePerson());
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sampleCode
     * @param response
     */
    @ApiOperation(value = "导出监测项目excel")
    @RequestMapping(value = "/getProjectList",method = RequestMethod.POST)
    public void getProjectList(  String sampleCode,HttpServletResponse response){
        try{
            List<AdsCheckInfo> adsCheckInfoList = adsCheckInfoService.getProjectList(sampleCode);
            if(adsCheckInfoList!=null && adsCheckInfoList.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"检测项目","检测值","判定值","判定结果"};
                String[] propertyNames = {"checkProject","checkNum","judgeNum","result"};
                for(AdsCheckInfo task : adsCheckInfoList){
                    HashMap<String,Object> map = new HashMap<>();

                    map.put("checkProject",task.getCheckProject());
                    map.put("checkNum",task.getCheckNum());
                    map.put("judgeNum",task.getJudgeNum());
                    if("1".equals(task.getResult())){
                        map.put("result","合格");
                    }else if ("0".equals(task.getResult())){
                        map.put("result","不合格");
                    }else {
                        map.put("result","暂未检测");
                    }
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取检测信息
     * @param
     * @return
     */
    @ApiOperation(value = "获取检测信息")
    @RequestMapping(value = "/getCheckInfo",method = RequestMethod.POST)
    public Object getCheckInfo(String checkInfoId) {
        try{
            AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryByMyId(checkInfoId);
            return setSuccessModelMap(new ModelMap(),adsCheckInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取任务
     * @param
     * @return
     */
    @ApiOperation(value = "获取任务")
    @RequestMapping(value = "/getMonitorTaskByCheckInfoId",method = RequestMethod.POST)
    public Object getMonitorTaskByCheckInfoId(String checkInfoId) {
        try{
            AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryByMyId(checkInfoId);
            //获取机构任务
            AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(adsCheckInfo.getOrganTaskId());
            //获取监测任务
            AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryByMyId(adsOrganTask.getMonitorTaskId());
            return setSuccessModelMap(new ModelMap(),adsMonitorTask);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 下载检测任务单
     * @param
     * @return
     */
    @ApiOperation(value = "下载检测任务单")
    @RequestMapping(value = "/writeWordFile",method = RequestMethod.POST)
    public void writeWordFile(@RequestBody AdsCheckInfo adsCheckInfo,HttpServletResponse response) {
//        String path = "d:/";
        try {
//            if (!"".equals(path)) {
                // 检查目录是否存在
//                File fileDir = new File(path);
//                if (fileDir.exists()) {
                    // 生成临时文件名称
                    String fileName = "检测任务单.doc";
//                    String content = "<html><div style=\"text-align: center\"><span style=\"font-size: 28px\"><span style=\"font-family: 黑体\">" +
//                            "制度发布通知<br /> <br /> </span></span></div></html>";
                    String content = adsCheckInfo.getInnerText();
                    byte b[] = content.getBytes();
                    ByteArrayInputStream bais = new ByteArrayInputStream(b);
                    POIFSFileSystem poifs = new POIFSFileSystem();
                    DirectoryEntry directory = poifs.getRoot();
                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
//                    FileOutputStream ostream = new FileOutputStream(path+ fileName);
//                    poifs.writeFilesystem(ostream);
                    OutputStream outStream = response.getOutputStream();
                    response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                    response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "iso8859-1"));
//                    InputStream inputStream = new FileInputStream(new File(path+ fileName));
//                    byte[] c = new byte[2048];
//                    int length;
//                    while ((length = inputStream.read(c)) > 0) {
//                        outStream.write(c, 0, length);
//                    }
                    poifs.writeFilesystem(outStream);
                    bais.close();
//                    ostream.close();
                    outStream.flush();
                    outStream.close();
//                }
//            }
//            return setSuccessModelMap(new ModelMap());
        } catch (IOException e) {
            e.printStackTrace();
//            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

}


