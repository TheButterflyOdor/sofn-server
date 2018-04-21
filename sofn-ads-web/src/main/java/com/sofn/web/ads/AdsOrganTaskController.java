package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.DownloadExcelUtil;
import com.sofn.core.util.FileManager;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.excel.ExportExcelUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.*;
import com.sofn.service.ads.AdsMonitorTaskService;
import com.sofn.service.ads.AdsOrganTaskService;
import com.sofn.service.ads.AdsPecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.CellType;
import jxl.biff.FormatRecord;
import jxl.write.WriteException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构任务 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "机构任务", description = "机构任务")
@RequestMapping(value = "/adsOrganTask", method = RequestMethod.POST)
public class AdsOrganTaskController extends BaseController {
    private static final String CLIENT_CONFIG_FILE3 = "任务详情.xls";
    @Autowired
    private AdsOrganTaskService adsOrganTaskService;
    @Autowired
    private AdsMonitorTaskService adsMonitorTaskService;
    @Autowired
    private AdsPecipeService adsPecipeService;

    /**
     * 根据条件获取机构任务列表
     *
     * @param adsOrganTask
     * @return
     */
    @ApiOperation(value = "获取机构任务信息列表")
    @SystemControllerLog(description = "获取机构任务信息列表",operationType="查询")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsOrganTask adsOrganTask, int start, int length) {
        try {
            PageInfo<AdsOrganTask> pageInfo = adsOrganTaskService.getPageInfo(adsOrganTask, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取监测任务的机构任务列表
     *
     * @param adsOrganTask
     * @return
     */
    @ApiOperation(value = "获取监测任务的机构任务列表")
    @SystemControllerLog(description = "获取监测任务的机构任务列表",operationType="查询")
    @RequestMapping(value = "/getPageInfoByMonitorTask", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfoByMonitorTask(AdsOrganTask adsOrganTask, @RequestHeader(required = true) String token, int start, int length) {
        try {
            PageInfo<AdsOrganTask> pageInfo = adsOrganTaskService.getPageInfoByMonitorTask(adsOrganTask, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增机构任务记录
     *
     * @param adsOrganTask
     * @return
     */
    @ApiOperation(value = "新增机构任务数据")
    @SystemControllerLog(description = "新增机构任务数据",operationType="添加")
    @RequestMapping(value = "/addAdsOrganTask", method = RequestMethod.POST)
    @Authorization
    public Object addAdsOrganTask(AdsOrganTask adsOrganTask, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser ut = WebUtil.getCurrentUser(token);
            if (ut != null) {
                adsOrganTask.setCreateBy(ut.getId());
                int flag = adsOrganTaskService.addOrganTask(adsOrganTask);
                if (flag > 0) {
                    return setSuccessModelMap(new ModelMap());
                } else if (flag == -3) {
                    //抽检机构已经被下发了任务
                    ModelMap modelMap = new ModelMap();
                    modelMap.put("httpCode", HttpCode.BAD_REQUEST.value());
                    modelMap.put("msg", "抽检机构已经被下发了任务,不能再次下发！");
                    return modelMap;
                } else if (flag == -4) {
                    //判断上报时间不符合条件
                    ModelMap modelMap = new ModelMap();
                    modelMap.put("httpCode", HttpCode.BAD_REQUEST.value());
                    modelMap.put("msg", "上报时间不符合条件，需要晚于抽样截止时间，早于任务结束时间！");
                    return modelMap;
                } else {
                    return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("AdsOrganTaskController.addAdsOrganTask:新增机构任务异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 根据ID获取单条机构任务数据信息
     *
     * @param adsOrganTask
     * @return
     */
    @ApiOperation(value = "获取单条机构任务数据信息")
    @SystemControllerLog(description = "获取单条机构任务数据信息", operationType = "查询")
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> queryById(AdsOrganTask adsOrganTask) {
        adsOrganTask = adsOrganTaskService.queryById(adsOrganTask.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsOrganTask", adsOrganTask);
        return map;
    }

    /**
     * 修改机构任务数据信息
     *
     * @param adsOrganTask
     * @return
     */
    @ApiOperation(value = "修改机构任务数据信息")
    @SystemControllerLog(description = "修改机构任务数据信息", operationType = "修改")
    @RequestMapping(value = "/updateAdsOrganTask", method = RequestMethod.POST)
    @Authorization
    public ResponseEntity<ModelMap> updateAdsOrganTask(@RequestBody AdsOrganTask adsOrganTask) {
        try {
            adsOrganTaskService.update(adsOrganTask);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsOrganTaskController.updateAdsOrganTask:修改机构任务异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除机构任务信息
     *
     * @param adsOrganTask
     * @return
     */
    @ApiOperation(value = "批量删除机构任务信息")
    @SystemControllerLog(description = "批量删除机构任务信息")
    @RequestMapping(value = "/deleteAdsOrganTask", method = RequestMethod.POST)
    @Authorization
    public ResponseEntity<ModelMap> deleteAdsOrganTask(AdsOrganTask adsOrganTask, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser ut = WebUtil.getCurrentUser(token);
            if (ut != null) {
                adsOrganTask.setUpdateBy(ut.getId());
                int flag = adsOrganTaskService.batchDelete(adsOrganTask);
                if (flag > 0) {
                    return setSuccessModelMap(new ModelMap());
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsOrganTaskController.deleteAdsOrganTask:批量删除机构任务异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查找所有机构
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "获取机构任务信息列表")
    @SystemControllerLog(description = "获取机构任务信息列表", operationType = "查询")
    @RequestMapping(value = "/getAdsMonitorTaskPageInfo", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> getAdsMonitorTaskPageInfo(AdsMonitorTask adsMonitorTask, AdsPecipe adsPecipe, int start, int length, AdsFile adsFile) {
        PageInfo<AdsMonitorTask> monitorTaskpageInfo = adsMonitorTaskService.getPageInfo(adsMonitorTask, ((start + 1) / length) + 1, length, null, null, null, adsFile);
        PageInfo<AdsPecipe> adsPecipepageInfo = adsPecipeService.getAdsPecipePageInfo(adsPecipe, ((start + 1) / length) + 1, length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("monitorTaskpageInfo", monitorTaskpageInfo);
        map.put("adsPecipepageInfo", adsPecipepageInfo);
        return map;
    }

    /**
     * 根据条件获取监测任务列表
     *
     * @param monitorTaskId,sampleOrganId
     * @return
     */
    @ApiOperation(value = "获取机构任务列表")
    @SystemControllerLog(description = "获取机构任务列表", operationType = "查询")
    @RequestMapping(value = "/findAdsMonitorTaskByid", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> findAdsMonitorTaskByid(String token, String monitorTaskId, String monitorClass) {
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        String sampleOrganId = currentUser.getOrgId();
        String sampleOrgan = currentUser.getOrgName();
        List<AdsMonitorTask> adsMonitorTaskList = adsMonitorTaskService.findAdsMonitorTaskByid(monitorTaskId, sampleOrganId, monitorClass);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("adsMonitorTask", adsMonitorTaskList);
        map.put("sampleOrgan", sampleOrgan);
        map.put("sampleOrganId", sampleOrganId);
        return map;
    }

    /**
     * 查询承担单位接收新任务列表
     *
     * @param token
     * @param start
     * @param length
     * @param monitorClass
     * @return
     */
    @ApiOperation(value = "查询承担单位接收新任务列表")
    @RequestMapping(value = "/queryExecUnitTaskPageInfo", method = RequestMethod.POST)
    @SystemControllerLog(description = "查询承担单位接收新任务列表", operationType = "查询")
    @Authorization
    public Object queryExecUnitTaskPageInfo(String token, int start, int length, String monitorClass) {
        try {
            ModelMap modelMap = new ModelMap();
            CurrentUser currentUser = WebUtil.getCurrentUser(token);
            String sampleOrganId = currentUser.getOrgId();
            String sampleOrgan = currentUser.getOrgName();
            modelMap.addAttribute("sampleOrgan", sampleOrgan);
            modelMap.addAttribute("sampleOrganId", sampleOrganId);
            PageInfo pageInfo = adsMonitorTaskService.queryExecUnitTaskPageInfo(sampleOrganId, monitorClass, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(modelMap, pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取监测任务列表
     *
     * @param start,length
     * @return
     */
    @ApiOperation(value = "获取任务退回列表")
    @SystemControllerLog(description = "获取任务退回列表",operationType = "查询")
    @RequestMapping(value = "/findAdsPecipepageInfo", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> findAdsPecipepageInfo(AdsPecipe adsPecipe, int start, int length) {
        PageInfo<AdsPecipe> adsPecipepageInfo = adsPecipeService.getAdsPecipePageInfo(adsPecipe, ((start + 1) / length) + 1, length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("adsPecipepageInfo", adsPecipepageInfo);
        return map;

    }

    @ApiOperation(value = "获取行业列表")
    @SystemControllerLog(description = "获取行业列表",operationType = "查询")
    @RequestMapping(value = "/findAdsMonitorTask", method = RequestMethod.POST)
    @Authorization
    public AdsMonitorTask findAdsMonitorTask(String id) {
        AdsMonitorTask ads = adsMonitorTaskService.findAdsMonitorTask(id);
        return ads;

    }

    /**
     * 根据多种条件获取机构任务列表
     *
     * @param adsOrganTask
     * @return
     */
    @ApiOperation(value = "根据多种条件获取机构任务列表")
    @SystemControllerLog(description = "根据多种条件获取机构任务列表",operationType = "查询")
    @RequestMapping(value = "/getPageInfoByMonitorTaskID", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfoByMonitorTaskID(AdsOrganTask adsOrganTask, int start, int length, String monitorClass, String year, String taskName, String publishStatus,String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsOrganTask.setDeparmentId(u.getOrgId());//临时存放token的orgId
                PageInfo<AdsOrganTask> pageInfo = adsOrganTaskService.getPageInfoByMonitorTaskID(adsOrganTask, ((start + 1) / length) + 1, length, monitorClass, year, taskName, publishStatus);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取抽样机构")
    @SystemControllerLog(description = "获取抽样机构",operationType = "查询")
    @RequestMapping(value = "/findSampleOrgan", method = RequestMethod.POST)
    public List<AdsOrganTask> findSampleOrgan(String monitorTaskId) {
        return adsOrganTaskService.findSampleOrgan(monitorTaskId);
    }

    @ApiOperation(value = "下载任务详情页面")
    @SystemControllerLog(description = "下载任务详情页面",operationType = "查询")
    @RequestMapping(value = "/downloadTaskDetailExcel", method = RequestMethod.POST)
    @Authorization
    public void downloadTaskDetailExcel(HttpServletResponse response, String token, String monitorTaskId) {
        List<AdsMonitorTask> adsMonitorTaskList = null;
        OutputStream outStream = null;
        FileInputStream fin = null;
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        String sampleOrganId = currentUser.getOrgId();
        adsMonitorTaskList = adsMonitorTaskService.findAdsMonitorTaskByid(monitorTaskId, sampleOrganId, "");

        try {
            String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
            String routineMonitorClientConfigFilePath = classPath + File.separator + "doc" + File.separator + CLIENT_CONFIG_FILE3;
            FileInputStream fs = new FileInputStream(routineMonitorClientConfigFilePath);
            POIFSFileSystem ps = new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息
            HSSFWorkbook wb = new HSSFWorkbook(ps);
            HSSFSheet sheet = wb.getSheetAt(0);  //获取到工作表，因为一个excel可能有多个工作表
            HSSFRow row0 = sheet.getRow(0);  //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
            FileOutputStream fout = new FileOutputStream(routineMonitorClientConfigFilePath);  //向d://test.xls中写数据
            row0.getCell(1).setCellValue(adsMonitorTaskList.get(0).getReleaseUnit());//发布单位
            row0.getCell(3).setCellValue(adsMonitorTaskList.get(0).getYear());//年度
            HSSFRow row1 = sheet.getRow(1);
            row1.getCell(1).setCellValue(adsMonitorTaskList.get(0).getStartTime() + "/" + adsMonitorTaskList.get(0).getEndTime());//任务时间
//            row1.getCell(3).setCellValue(adsMonitorTaskList.get(0).getDeadline());
            HSSFRow row2 = sheet.getRow(2);
            row2.getCell(1).setCellValue(adsMonitorTaskList.get(0).getDeadline());//抽样截至时间
//            row2.getCell(3).setCellValue(adsMonitorTaskList.get(0).getIndustry());
            HSSFRow row3 = sheet.getRow(3);
            row3.getCell(1).setCellValue(adsMonitorTaskList.get(0).getMonitorClass());//工作类型
            HSSFRow row4 = sheet.getRow(4);
            BigDecimal separation = adsMonitorTaskList.get(0).getSeparation();//抽检分离
            if (separation.equals(new BigDecimal(0))) {
                row4.getCell(1).setCellValue("否");
            } else {
                row4.getCell(1).setCellValue("是");
            }
            HSSFRow row5 = sheet.getRow(5);
            row5.getCell(1).setCellValue("下载附件");
//            row5.getCell(1).setCellValue(adsMonitorTaskList.get(0).getComment());
//            HSSFRow row6 = sheet.getRow(6);
//            row6.getCell(1).setCellValue(adsMonitorTaskList.get(0).getSampleLink());
            HSSFRow row7 = sheet.getRow(7);
            row7.getCell(0).setCellValue(adsMonitorTaskList.get(0).getAdsOrganTaskList().get(0).getNumbers().toString());//抽样数量
            row7.getCell(1).setCellValue(adsMonitorTaskList.get(0).getAdsOrganTaskList().get(0).getDetectionOrgan());//受检单位
            row7.getCell(2).setCellValue(adsMonitorTaskList.get(0).getAdsOrganTaskList().get(0).getSampleOrgan());//抽样单位
            row7.getCell(3).setCellValue(adsMonitorTaskList.get(0).getAdsOrganTaskList().get(0).getDetectionOrgan());//检测单位
            String regName = adsMonitorTaskList.get(0).getAdsOrganTaskList().get(0).getRegName();
            BigDecimal number = adsMonitorTaskList.get(0).getAdsOrganTaskList().get(0).getNumbers();
            if (StringUtils.isEmpty(regName) && null == number) {
                row7.getCell(4).setCellValue("");
            } else {
                row7.getCell(4).setCellValue(regName + "(" + number + ")");
            }
//            HSSFRow row8 = sheet.getRow(8);
//            row8.getCell(1).setCellValue(adsMonitorTaskList.get(0).getCheckProject());
//            HSSFRow row10 = sheet.getRow(10);
//            row10.getCell(0).setCellValue(adsMonitorTaskList.get(0).getAdsOrganTaskList().get(0).getSampleOrgan());
//            row10.getCell(1).setCellValue(adsMonitorTaskList.get(0).getAdsOrganTaskList().get(0).getDetectionOrgan());
//
            fout.flush();
            wb.write(fout);
            fout.close();
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + "任务详情.xls");
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            outStream = response.getOutputStream();
            fin = new FileInputStream(routineMonitorClientConfigFilePath);
            byte[] b = new byte[2048];
            int length;
            while ((length = fin.read(b)) > 0) {
                outStream.write(b, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fin != null) {
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (outStream != null) {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "监测对象列表")
    @SystemControllerLog(description = "监测对象列表",operationType = "查询")
    @RequestMapping(value = "/objList")
    @Authorization
    public Object objList(@RequestHeader(required = true) String token, String superviseCheckTaskId, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), adsMonitorTaskService.objList(superviseCheckTaskId, queryParameter));
    }

    @ApiOperation(value = "受托监测对象列表")
    @SystemControllerLog(description = "受托监测对象列表",operationType = "查询")
    @RequestMapping(value = "/pages")
    @Authorization
    public Object pages(String superviseCheckTaskId, String detectionUnitId, QueryParameter queryParameter) {
        return setSuccessModelMap(new ModelMap(), adsMonitorTaskService.pages(superviseCheckTaskId, detectionUnitId, queryParameter));
    }
}


