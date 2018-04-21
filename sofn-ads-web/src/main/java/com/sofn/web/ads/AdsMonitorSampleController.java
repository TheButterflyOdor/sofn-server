package com.sofn.web.ads;


import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.Assert;
import com.sofn.core.util.*;
import com.sofn.core.util.excel.ExportExcelUtil;
import com.sofn.freemarker.CreateDoc;
import com.sofn.model.generator.*;
import com.sofn.service.ads.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * 监测抽样单 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "监测抽样单", description = "监测抽样单")
@RequestMapping(value = "/adsMonitorSample", method = RequestMethod.POST)
public class AdsMonitorSampleController extends BaseController {
    @Autowired
    private AdsOrganTaskService adsOrganTaskService;
    @Autowired
    private AdsMonitorSampleService adsMonitorSampleService;
    @Autowired
    private AdsCheckInfoService adsCheckInfoService;
    @Autowired
    public AdsMonitorTaskService adsMonitorTaskService;
    @Autowired
    public AdsSysArgiProductService adsSysArgiProductService;
    @Autowired
    public AdsProducttempoRrarycodeService adsProducttempoRrarycodeService;

    /**
     * 根据条件获取监测抽样单列表
     *
     * @param adsMonitorSample
     * @return
     */
    @ApiOperation(value = "获取监测抽样单信息列表")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsMonitorSample adsMonitorSample, int start, int length) {
        try {
            PageInfo<AdsMonitorSample> pageInfo = adsMonitorSampleService.getPageInfo(adsMonitorSample, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取监测抽样单信息列表")
    @RequestMapping(value = "/getSamplePageInfo", method = RequestMethod.POST)
    public Object getSamplePageInfo(AdsMonitorSample adsMonitorSample, int start, int length) {
        PageInfo<AdsMonitorSample> pageInfo = adsMonitorSampleService.getPageInfo(adsMonitorSample, ((start + 1) / length) + 1, length);
        return pageInfo;

    }

    @ApiOperation(value = "获取监测抽样单信息列表")
    @RequestMapping(value = "/getSamplePageInfo2", method = RequestMethod.POST)
    public Object getSamplePageInfo2(AdsMonitorSample adsMonitorSample, int start, int length) {
        try {
            PageInfo<AdsMonitorSample> pageInfo = adsMonitorSampleService.getPageInfo(adsMonitorSample, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取监督抽查抽样单信息列表")
    @SystemControllerLog(description = "获取监督抽查抽样单信息列表", operationType = "查询")
    @RequestMapping(value = "/getAuditSamplePageInfo", method = RequestMethod.POST)
    @Authorization
    public Object getAuditSamplePageInfo(@RequestHeader(required = true) String token, AdsMonitorSample adsMonitorSample, int start, int length) {
        try {
            PageInfo<AdsMonitorSample> pageInfo = adsMonitorSampleService.getAuditSamplePageInfo(adsMonitorSample, ((start + 1) / length) + 1, length);
            return setModelMap(new ModelMap(), HttpCode.OK, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取复检任务抽样单信息列表")
    @RequestMapping(value = "/getRecheckSamplePageInfo", method = RequestMethod.POST)
    public Object getRecheckSamplePageInfo(AdsMonitorSample adsMonitorSample, int start, int length) {
        try {
            PageInfo<AdsMonitorSample> pageInfo = adsMonitorSampleService.getRecheckSamplePageInfo(adsMonitorSample, ((start + 1) / length) + 1, length);
            return setModelMap(new ModelMap(), HttpCode.OK, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取委托检测抽样单信息列表")
    @RequestMapping(value = "/getEntrustSamplePageInfo", method = RequestMethod.POST)
    public Object getEntrustSamplePageInfo(AdsMonitorSample adsMonitorSample, int start, int length) {
        try {
            PageInfo<AdsMonitorSample> pageInfo = adsMonitorSampleService.getEntrustSamplePageInfo(adsMonitorSample, ((start + 1) / length) + 1, length);
            return setModelMap(new ModelMap(), HttpCode.OK, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增监测抽样单记录
     *
     * @param adsMonitorSample
     * @return
     */
    @ApiOperation(value = "新增监测抽样单数据")
    @RequestMapping(value = "/addAdsMonitorSample", method = RequestMethod.POST)
    public Object addAdsMonitorSample(@RequestBody AdsMonitorSample adsMonitorSample) {
        try {
            adsMonitorSampleService.add(adsMonitorSample);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsMonitorSampleController.addAdsMonitorSample:新增监测抽样单记录异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增监测抽样单记录
     *
     * @param adsMonitorSample
     * @return
     */
    @ApiOperation(value = "新增抽样单信息")
    @RequestMapping(value = "/insertAdsMonitorSample", method = RequestMethod.POST)
    public Object insertAdsMonitorSample(@RequestBody AdsMonitorSample adsMonitorSample, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
//            String monitorTaskId = adsMonitorSample.getMonitorTaskId();
//            String sampleName = adsMonitorSample.getSampleName();
//            RedisUtil.set("sampleCode", "0000");
//            RedisUtil.incr("sampleCode");
//            String sampleCode = adsMonitorSample.getSampleCode();
//            AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryById(monitorTaskId);//查询检测对象
//            String[] CheckObject = adsMonitorTask.getCheckObject().split("、");
//            List<String> list = Arrays.asList(CheckObject);//
//            boolean contains = list.contains(sampleName);//查询是否包含样品名称
//            if (!contains) {
//                throw new IllegalParameterException("检测对象不存在");
//            }
//            AdsMonitorSample ads = adsMonitorSampleService.findAdsMonitorSample(sampleCode);
//            if (null != ads) {
//                throw new IllegalParameterException("样品编码已经存在");
//            }
            String uuid = UUID.randomUUID().toString();
            String id = uuid.replace("-", "");
            adsMonitorSample.setId(id);
            adsMonitorSample.setCreateBy(u.getId());
            adsMonitorSample.setUpdateBy(u.getId());
            adsMonitorSample.setDelFlag("N");
            adsMonitorSampleService.addAdsMonitorSample(adsMonitorSample);//新增抽样单
            AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
            adsCheckInfo.setOrganTaskId(adsMonitorSample.getOrganTaskId());
            adsCheckInfo.setSampleBarCode(adsMonitorSample.getSampleBarCode());
            adsCheckInfo.setMonitorSampleId(id);
            adsCheckInfo.setCheckOrganId(adsMonitorSample.getSampleOrganId());
            adsCheckInfo.setCheckLink(adsMonitorSample.getSamplePlace());
            long time = new Date().getTime();
            adsCheckInfo.setCheckTime(new Timestamp(time));
            adsCheckInfo.setResult("-1");
            adsCheckInfo.setCheckReport("0");
            adsCheckInfo.setOrganTaskId(adsMonitorSample.getOrganTaskId());
            adsCheckInfo.setTestedDeparment(adsMonitorSample.getTestedDeparment());
            adsCheckInfo.setSampleDeparment(adsMonitorSample.getSampleDeparment());
            adsCheckInfo.setCheckOrgan(adsMonitorSample.getSampleOrgan());
            adsCheckInfo.setSampleCode(adsMonitorSample.getSampleCode());
            adsCheckInfo.setSampleName(adsMonitorSample.getSampleName());
            adsCheckInfo.setProductTraceabilityCode(adsMonitorSample.getProductTraceabilityCode());
            adsCheckInfo.setCreateBy(u.getId());
            adsCheckInfo.setUpdateBy(u.getId());
            adsCheckInfo.setDelFlag("N");
            adsCheckInfoService.addAdsCheckInfo(adsCheckInfo);//初始化检测信息
            return setSuccessModelMap(new ModelMap(), HttpCode.OK);
        } catch (Exception e) {
            logger.error("AdsMonitorSampleController.insertAdsMonitorSample:新增监测抽样单记录异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR, e.toString());
        }
    }


    /**
     * 根据ID获取单条监测任务数据信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单条监测任务数据信息")
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条监测任务数据信息", operationType = "查詢")
    public Map<String, Object> queryById(String id, int publishStatus) {
        AdsMonitorTask adsMonitorTask = adsMonitorTaskService.selectByPrimaryKey(id);
        if (new BigDecimal(publishStatus).equals(new BigDecimal(1))) {
            AdsMonitorTask ads = new AdsMonitorTask();
            ads.setId(id);
            ads.setPublishStatus(new BigDecimal(2));
            adsMonitorTaskService.updatePublishStatus(ads);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsMonitorTask", adsMonitorTask);
        return map;
    }

    /**
     * 修改监测抽样单数据信息
     *
     * @param adsMonitorSample
     * @return
     */
    @ApiOperation(value = "修改监测抽样单数据信息")
    @RequestMapping(value = "/updateAdsMonitorSample", method = RequestMethod.POST)
    public Object updateAdsMonitorSample(@RequestBody AdsMonitorSample adsMonitorSample, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            Date date = new Date();
            adsMonitorSample.setUpdateBy(u.getId());
            adsMonitorSample.setUpdateTime(date);
            adsMonitorSampleService.updateAdsMonitorSample(adsMonitorSample);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsMonitorSampleController.updateAdsMonitorSample:修改监测抽样单数据信息异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "上报抽样单")
    @RequestMapping(value = "/updateAdsMonitorSampleById", method = RequestMethod.POST)
    public Object updateAdsMonitorSampleById(String id, String organTaskId, String token) {
        Assert.isNotBlank(token, "token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        Date date = new Date();
        try {
            JSONArray array = JSONArray.parseArray(id);//多条上报的id
            for (int i = 0; i < array.size(); i++) {
                AdsMonitorSample adsMonitorSample = new AdsMonitorSample();
                adsMonitorSample.setId((String) array.get(i));
                adsMonitorSample.setSampleReport("2");
                adsMonitorSample.setUpdateBy(u.getId());
                adsMonitorSample.setUpdateTime(date);
                adsMonitorSampleService.updateAdsMonitorSampleById(adsMonitorSample);
                adsMonitorSample = adsMonitorSampleService.queryById((String) array.get(i));
                AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryBySampleCode(adsMonitorSample.getSampleCode());
                adsMonitorSampleService.updateInfo(adsMonitorSample,adsCheckInfo);
            }
            AdsOrganTask adsOrganTask = new AdsOrganTask();
            BigDecimal finishNum = new BigDecimal(array.size());//上报数
            AdsOrganTask adsOrgan = adsOrganTaskService.queryById(organTaskId);
            BigDecimal sampleFinishNum = adsOrgan.getSampleFinishNum();//已上报数
            BigDecimal all = finishNum.add(sampleFinishNum);
            adsOrganTask.setSampleFinishNum(all);//抽样完成数
            adsOrganTask.setSampleReportTime(DateUtil.getDate());//上报日期
            BigDecimal numbers = adsOrgan.getNumbers();//任务数
            BigDecimal finishStatus = new BigDecimal(1);
            adsOrganTask.setSampleReportStatus(finishStatus);//上报状态为1
            adsOrganTask.setId(organTaskId);
            adsOrganTask.setUpdateTime(date);
            adsOrganTask.setUpdateBy(u.getId());
            adsOrganTaskService.updateById(adsOrganTask);//修改机构任务信息
            AdsOrganTask ads = adsOrganTaskService.queryById(organTaskId);
            BigDecimal sFinishNum = ads.getSampleFinishNum();//已上报数
            if (sFinishNum.compareTo(numbers) != -1) {
                AdsOrganTask adsOTask = new AdsOrganTask();
                BigDecimal fStatus = new BigDecimal(2);
                adsOTask.setSampleReportStatus(fStatus);//上报状态置为2
                adsOTask.setId(organTaskId);
                adsOTask.setUpdateBy(u.getId());
                adsOTask.setUpdateTime(date);
                adsOrganTaskService.updateById(adsOTask);//更新机构任务上报状态
            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsMonitorSampleController.updateAdsMonitorSampleById:上报抽样单异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "上报全部抽样单")
    @RequestMapping(value = "/updateAdsMonitorSampleAll", method = RequestMethod.POST)
    public Object updateAdsMonitorSampleAll(String organTaskId) {
        try {

            AdsMonitorSample adsMonitorSample = new AdsMonitorSample();
            adsMonitorSample.setOrganTaskId(organTaskId);
            adsMonitorSample.setSampleReport("2");
            adsMonitorSampleService.updateAdsMonitorSampleById(adsMonitorSample);//全部上报
            int count = adsMonitorSampleService.selectCount(adsMonitorSample);
            AdsOrganTask adsOrganTask = new AdsOrganTask();
            adsOrganTask.setSampleFinishNum(new BigDecimal(count));//抽样单总数
            adsOrganTask.setSampleReportTime(DateUtil.getDate());//上报日期
            adsOrganTask.setSampleReportStatus(new BigDecimal(1));//上报状态为1
            adsOrganTask.setId(organTaskId);
            adsOrganTaskService.updateById(adsOrganTask);//修改机构任务
            AdsOrganTask ads = adsOrganTaskService.queryById(organTaskId);
            BigDecimal sFinishNum = ads.getSampleFinishNum();//已上报数
            BigDecimal numbers = ads.getNumbers();//任务数
            if (sFinishNum.compareTo(numbers) != -1) {
                AdsOrganTask adsOTask = new AdsOrganTask();
                adsOTask.setSampleReportStatus(new BigDecimal(2));//上报状态为2
                adsOTask.setId(organTaskId);
                adsOrganTaskService.updateById(adsOTask);
            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsMonitorSampleController.updateAdsMonitorSampleAll:上报全部抽样单", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除监测抽样单信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = " 删除监测抽样单信息")
    @RequestMapping(value = "/deleteAdsMonitorSample", method = RequestMethod.POST)
    public Object deleteAdsMonitorSample(String id, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            Date date = new Date();
            JSONArray array = JSONArray.parseArray(id);//多条删除
            for (int i = 0; i < array.size(); i++) {
                AdsMonitorSample adsMonitorSample = new AdsMonitorSample();
                adsMonitorSample.setId((String) array.get(i));
                System.out.println("id=" + (String) array.get(i));
                adsMonitorSample.setDelFlag("Y");//置为删除
                adsMonitorSample.setUpdateTime(date);
                adsMonitorSample.setUpdateBy(u.getId());
                adsMonitorSampleService.updateAdsMonitorSampleById(adsMonitorSample);
            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsMonitorSampleController.deleteAdsMonitorSample:删除监测抽样单信息", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = " 查询抽样单详情")
    @RequestMapping(value = "/findAdsMonitorSampleById", method = RequestMethod.POST)
    public Object findAdsMonitorSampleById(String sampleCode,String organTaskId) {
        try {
            Map<String, Object> map = adsMonitorSampleService.findAdsMonitorSampleById(sampleCode,organTaskId);
            return setModelMap(new ModelMap(), HttpCode.OK, map);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = " 查询抽样单")
    @RequestMapping(value = "/findAdsMonitorSample", method = RequestMethod.POST)
    public Object findAdsMonitorSample(String sampleCode) {
        try {
            AdsMonitorSample adsMonitorSample = adsMonitorSampleService.findAdsMonitorSample(sampleCode);
            if (null != adsMonitorSample) {
                logger.error("抽样编号已经存在");
                throw new IllegalParameterException("抽样编号已经存在");
            } else {
                return setModelMap(new ModelMap(), HttpCode.OK);
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @ApiOperation(value = " 查询监测对象")
    @RequestMapping(value = "/findSampleName", method = RequestMethod.POST)
    public Object findSampleName(String monitorTaskId) {
        AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryById(monitorTaskId);//查询检测对象
        String checkObject = adsMonitorTask.getCheckObject();
        return setModelMap(new ModelMap(), HttpCode.OK, checkObject);
    }

    @ApiOperation(value = " 导出监测抽样单列表")
    @RequestMapping(value = "/downloadMonitorSample", method = RequestMethod.POST)
    public void downloadMonitorSample(AdsMonitorSample adsMonitorSample, HttpServletResponse response) {
        try {
            PageInfo samplePageInfo = adsMonitorSampleService.getPageInfo(adsMonitorSample, 1, 5000);
            List<Map<String, Object>> adsMonitorSampleList = samplePageInfo.getList();
            if (adsMonitorSampleList != null && adsMonitorSampleList.size() > 0) {
                List<Map> list = new ArrayList<>();
                String[] columns = {"产品编码", "样品编码", "样品名称", "受检单位", "样品产地", "抽样日期", "状态"};
                String[] propertyNames = {"productTraceabilityCode", "sampleCode", "sampleName", "testedDeparment", "producingAreaName", "sampleDate", "sampleReport"};
                for (int i = 0; i < adsMonitorSampleList.size(); i++) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("productTraceabilityCode", adsMonitorSampleList.get(i).get("PRODUCT_TRACEABILITY_CODE"));
                    map.put("sampleCode", adsMonitorSampleList.get(i).get("SAMPLE_CODE"));
                    map.put("sampleName", adsMonitorSampleList.get(i).get("SAMPLE_NAME"));
                    map.put("testedDeparment", adsMonitorSampleList.get(i).get("TESTED_DEPARMENT"));
                    map.put("producingAreaName", adsMonitorSampleList.get(i).get("PRODUCING_AREA_NAME"));
                    map.put("sampleDate", adsMonitorSampleList.get(i).get("SAMPLE_DATE"));
                    String sample_report = (String) adsMonitorSampleList.get(i).get("SAMPLE_REPORT");
                    if (null == sample_report) {
                        map.put("sampleReport", "");
                    } else {
                        if (sample_report.equals("0")) {
                            map.put("sampleReport", "未上报");
                        } else if (sample_report.equals("2")) {
                            map.put("sampleReport", "已上报");
                        } else if (sample_report.equals("3")) {
                            map.put("sampleReport", "已退回");
                        } else {
                            map.put("sampleReport", "");
                        }
                        list.add(map);
                    }
                }
                //导出excel
                ExportExcelUtil.exportView(response, list, columns, propertyNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @ApiOperation(value = " 导出监测监督抽查抽样单列表")
    @RequestMapping(value = "/downloadMonitorAuditSample", method = RequestMethod.POST)
    public void downloadMonitorAuditSample(AdsMonitorSample adsMonitorSample, HttpServletResponse response) {
        try {
            PageInfo samplePageInfo = adsMonitorSampleService.getAuditSamplePageInfo(adsMonitorSample, 1, 5000);
            List<Map<String, Object>> adsMonitorSampleList = samplePageInfo.getList();
            if (adsMonitorSampleList != null && adsMonitorSampleList.size() > 0) {
                List<Map> list = new ArrayList<>();
                String[] columns = {"产品编码", "样品编码", "样品名称", "受检单位", "样品产地", "抽样日期", "状态"};
                String[] propertyNames = {"productTraceabilityCode", "sampleCode", "sampleName", "testedDeparment", "producingAreaName", "sampleDate", "sampleReport"};
                for (int i = 0; i < adsMonitorSampleList.size(); i++) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("productTraceabilityCode", adsMonitorSampleList.get(i).get("PRODUCT_TRACEABILITY_CODE"));
                    map.put("sampleCode", adsMonitorSampleList.get(i).get("SAMPLE_CODE"));
                    map.put("sampleName", adsMonitorSampleList.get(i).get("SAMPLE_NAME"));
                    map.put("testedDeparment", adsMonitorSampleList.get(i).get("TESTED_DEPARMENT"));
                    map.put("producingAreaName", adsMonitorSampleList.get(i).get("PRODUCING_AREA_NAME"));
                    map.put("sampleDate", adsMonitorSampleList.get(i).get("SAMPLE_DATE"));
                    String sample_report = (String) adsMonitorSampleList.get(i).get("SAMPLE_REPORT");
                    if (null == sample_report) {
                        map.put("sampleReport", "");
                    } else {
                        if (sample_report.equals("0")) {
                            map.put("sampleReport", "未上报");
                        } else if (sample_report.equals("2")) {
                            map.put("sampleReport", "已上报");
                        } else if (sample_report.equals("3")) {
                            map.put("sampleReport", "已退回");
                        } else {
                            map.put("sampleReport", "");
                        }
                        list.add(map);
                    }
                }
                //导出excel
                ExportExcelUtil.exportView(response, list, columns, propertyNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @ApiOperation(value = " 查询抽样单位")
    @RequestMapping(value = "/findDepartment", method = RequestMethod.POST)
    public List<AdsMonitorSample> findDepartment(String organTaskId) {
        return adsMonitorSampleService.findDepartment(organTaskId);
    }


    @ApiOperation(value = " 下载监测抽样单")
    @RequestMapping(value = "/downloadSampleList", method = RequestMethod.POST)
    public void downloadSampleList(AdsMonitorSample adsMonitorSample, HttpServletResponse response, String monitorClass) {
        adsMonitorSampleService.downloadSampleList(response, monitorClass);
    }

    @ApiOperation(value = "生成临时码")
    @RequestMapping(value = "/temporaryCode", method = RequestMethod.POST)
    public Map<String, String> temporaryCode(int mainManagementBody, int organizationForms, String mainBodyNumber, String projectCategoryCode) {
        Map<String, String> map = new HashedMap();
        AdsProducttempoRrarycode adsProducttempoRrarycode = new AdsProducttempoRrarycode();
        IdGenerator idGen = new IdGenerator();
        IdGenerator.MainBodyCategories mainBodyCategoriesCode = null;
        IdGenerator.OrganizationForms organizationFormsCode = null;
        List<Map<String, Object>> byNameOrAlias = null;
        if (mainManagementBody == 0) {
            adsProducttempoRrarycode.setMainbodyCategories(String.valueOf(0));
            mainBodyCategoriesCode = IdGenerator.MainBodyCategories.MainWorkingBody;//生产主体
        } else if (mainManagementBody == 1) {
            adsProducttempoRrarycode.setMainbodyCategories(String.valueOf(1));
            mainBodyCategoriesCode = IdGenerator.MainBodyCategories.MainManagementBody;//经营主体
        } else if (mainManagementBody == 2) {
            adsProducttempoRrarycode.setMainbodyCategories(String.valueOf(2));
            mainBodyCategoriesCode = IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement;//生产经营主体
        }
        if (organizationForms == 0) {
            organizationFormsCode = IdGenerator.OrganizationForms.Enterprise;//企业
            adsProducttempoRrarycode.setOrganizationForms(String.valueOf(0));
        } else if (organizationForms == 1) {
            adsProducttempoRrarycode.setOrganizationForms(String.valueOf(1));
            organizationFormsCode = IdGenerator.OrganizationForms.Individual;//个人
        }
        String productTemporaryCode = idGen.getProductTemporaryCode(IdGenerator.ServiceType.Retrospect,
                mainBodyCategoriesCode,
                organizationFormsCode,
                mainBodyNumber, projectCategoryCode);//临时码
        map.put(ApiConstants.CODE, "200");
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("productTemporaryCode", productTemporaryCode);
        //新增临时表
//        String uuid = UUID.randomUUID().toString();
//        String id = uuid.replace("-", "");
//        adsProducttempoRrarycode.setId(id);
        adsProducttempoRrarycode.setProjectCategoryCode(projectCategoryCode);
        adsProducttempoRrarycode.setServiceType("1");
        adsProducttempoRrarycode.setMainbodyNumber(mainBodyNumber);
        adsProducttempoRrarycodeService.add(adsProducttempoRrarycode);//新增临时表
        return map;
    }
//        try {
//            byNameOrAlias = adsSysArgiProductService.getByNameOrAlias(codeType, keyword);//获取农产品分类码
//            if (byNameOrAlias.size() == 0 || byNameOrAlias == null) {
//                map.put(ApiConstants.CODE, "400");
//                map.put("message", "未找到产品信息");
//            } else {
//                SysArgiProduct sysArgiProduct = (SysArgiProduct) byNameOrAlias.get(0);
//                String productCode = sysArgiProduct.getProductCode();//产品编码
//                String productTemporaryCode = idGen.getProductTemporaryCode(IdGenerator.ServiceType.Retrospect,
//                        mainBodyCategoriesCode,
//                        organizationFormsCode,
//                        mainBodyNumber, productCode);//临时码
//                map.put(ApiConstants.CODE, "200");
//                map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
//                map.put("productTemporaryCode", productTemporaryCode);
//            }
//            return map;
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put(ApiConstants.CODE, "400");
//            map.put("message", "调用外部接口异常");
//            return map;
//        }
//    }

    @ApiOperation(value = " 获取追溯码信息")
    @RequestMapping(value = "/getMsgByProductTraceabilityCode", method = RequestMethod.POST)
    public Map<Object, Object> getMsgByProductTraceabilityCode(String productTraceabilityCode, String orgId, String monitorTaskId) {
        AdsMonitorTask adsMonitorTask = adsMonitorTaskService.selectByPrimaryKey(monitorTaskId);
        adsMonitorTask.getBatch();
        adsMonitorTask.getIndustryId();
        return adsMonitorSampleService.getMsgByProductTraceabilityCode(productTraceabilityCode);
    }

    @ApiOperation(value = " 获取追溯码信息")
    @RequestMapping(value = "/getSampleCode", method = RequestMethod.POST)
    public Object getSampleCode(String orgId, String monitorTaskId) {
        try {
            String i = "";
            AdsMonitorTask adsMonitorTask = adsMonitorTaskService.selectByPrimaryKey(monitorTaskId);
            String batch = "0" + adsMonitorTask.getBatch();//批次
            int year = Calendar.getInstance().get(Calendar.YEAR);//年
            RedisUtil.set("linshi", "0001");
            String sampleCode = "LXJC" + "000" + "00" + year + batch;
            RedisUtil.set("sampleCode", sampleCode.toString());//前缀
            long linshi = RedisUtil.incr(RedisUtil.get("linshi").toString());//后四位自增
            RedisUtil.set("linshi", String.valueOf(linshi));
            if (linshi <= 9) {
                i = RedisUtil.get("sampleCode") + "000" + String.valueOf(linshi);
            } else if (linshi <= 99 && linshi > 9) {
                i = RedisUtil.get("sampleCode") + "00" + String.valueOf(linshi);
            } else if (linshi <= 999 && linshi > 99) {
                i = RedisUtil.get("sampleCode") + "0" + String.valueOf(linshi);
            }
            return setSuccessModelMap(new ModelMap(), i);
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取产品类型代码")
    @RequestMapping(value = "/PageInfocodeTypeByTaskId", method = RequestMethod.POST)
    public PageInfo pageInfocodeTypeByTaskId(String monitorTaskId, int start, int length) {
        return adsMonitorSampleService.pageInfocodeTypeByTaskId(monitorTaskId, ((start + 1) / length) + 1, length);
    }

    /**
     * 下载检测任务单
     *
     * @param
     * @return
     */
    @ApiOperation(value = "下载检测任务单")
    @RequestMapping(value = "/writeWordFile", method = RequestMethod.POST)
    public void writeWordFile(String sampleCode, String organTaskId, String monitorClass,HttpServletRequest request, HttpServletResponse response) {

        OutputStream os = null;
        ByteArrayOutputStream bos = null;
        InputStream inputStream = null;
        try {
            Map<String, Object> map = adsMonitorSampleService.findAdsMonitorSampleById(sampleCode,organTaskId);
            map.put("monitorClass",monitorClass);
            bos = CreateDoc.createAdsSampleDoc(map, request.getServletContext());
             byte[] os_byte = bos.toByteArray();
            inputStream = new ByteArrayInputStream(os_byte);
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "获取检测项目")
    @RequestMapping(value = "/getMappingModel", method = RequestMethod.POST)
    public PageInfo getMappingModel(String monitorTaskId, int start, int length) {
        return adsMonitorSampleService.getMappingModel(monitorTaskId, ((start + 1) / length) + 1, length);
    }

    @ApiOperation(value = "获取检测项目")
    @RequestMapping(value = "/getMappingModel2", method = RequestMethod.POST)
    public Object getMappingModel2(String monitorTaskId, int start, int length) {
        try {
            PageInfo pageInfo = adsMonitorSampleService.getMappingModel(monitorTaskId, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 通过机构名称获取检测机构信息
     * @param orgName
     * @return
     */
    @ApiOperation(value = "通过机构名称获取检测机构信息")
    @RequestMapping(value = "/getTestingOrganization", method = RequestMethod.POST)
    public Object getTestingOrganizationInfo(String orgName) {
        Map<String,Object> testingOrganization = adsMonitorSampleService.getTestingOrganizationInfo(orgName);
        return setSuccessModelMap(new ModelMap(), testingOrganization);
    }
}