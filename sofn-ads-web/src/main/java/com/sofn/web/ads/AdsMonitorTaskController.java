package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.freemarker.CreateDoc;
import com.sofn.model.generator.*;
import com.sofn.service.ads.AdsCheckInfoService;
import com.sofn.service.ads.AdsInfoProjectService;
import com.sofn.service.ads.AdsMonitorTaskService;
import com.sofn.service.ads.AdsOrganTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监测任务 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "监测任务", description = "监测任务")
@RequestMapping(value = "/adsMonitorTask", method = RequestMethod.POST)
public class AdsMonitorTaskController extends BaseController {

    @Autowired
    private AdsMonitorTaskService adsMonitorTaskService;
    @Autowired
    private AdsOrganTaskService adsOrganTaskService;
    @Autowired
    private AdsCheckInfoService adsCheckInfoService;
    @Autowired
    private AdsInfoProjectService adsInfoProjectService;

    /**
     * 根据条件获取监测任务列表
     *
     * @param adsMonitorTask
     * @param start          起始位置
     * @param length         分页个数
     * @param queryStartTiem 任务开始时间查询起始
     * @param queryEndTiem   任务开始时间查询结束
     * @return
     */
    @ApiOperation(value = "获取监测任务信息列表")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取监测任务信息列表", operationType = "查询")
    public Object getPageInfo(AdsMonitorTask adsMonitorTask, int start, int length, String queryStartTiem, String queryEndTiem, String token, AdsFile adsFile) {
        try {
            String orgId = null;
            CurrentUser ut = WebUtil.getCurrentUser(token);
            if (ut != null) {
                orgId = ut.getOrgId();
            }
            PageInfo<AdsMonitorTask> pageInfo = adsMonitorTaskService.getPageInfo(adsMonitorTask, ((start + 1) / length) + 1, length, queryStartTiem, queryEndTiem, orgId, adsFile);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param adsMonitorTask
     * @param response
     */
    @ApiOperation(value = "导出监测任务excel")
    @RequestMapping(value = "/exportAdsTaskExcel", method = RequestMethod.POST)
    @SystemControllerLog(description = "导出监测任务excel", operationType = "查询")
    public void exportAdsTaskExcel(AdsMonitorTaskExtend adsMonitorTask, HttpServletResponse response) throws Exception {


        CurrentUser ut = WebUtil.getCurrentUser(adsMonitorTask.getToken());
        if (ut != null) {
            List<AdsMonitorTask> adsMonitorTaskList = adsMonitorTaskService.getAdsTaskListByIds(adsMonitorTask);
            exportAdsTask(adsMonitorTaskList, response);

        }

    }

    @ApiOperation(value = "发布监测任务数据信息")
    @RequestMapping(value = "/publishAdsMonitorTask", method = RequestMethod.POST)
    @SystemControllerLog(description = "发布监测任务数据信息", operationType = "修改")
    public Object publishAdsMonitorTask(AdsMonitorTask adsMonitorTask, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser ut = WebUtil.getCurrentUser(token);
            if (ut != null) {
                adsMonitorTask.setUpdateBy(ut.getId());
                int result = adsMonitorTaskService.publishAdsMonitorTask(adsMonitorTask);
                if (result > 0) {
                    return setSuccessModelMap(new ModelMap());
                } else if (result == -1) {
                    //发布任务前，判断监测任务是否已添加机构任务，如果没有，发布失败
                    ModelMap modelMap = new ModelMap();
                    modelMap.put("httpCode", HttpCode.BAD_REQUEST.value());
                    modelMap.put("msg", "提示，选择的监测任务存在未创建机构任务情况，不能进行发布！");
                    return modelMap;
                } else if (result == -2) {
                    //发布任务前，判断是否选择检测模型,如果没有,发布失败
                    ModelMap modelMap = new ModelMap();
                    modelMap.put("httpCode", HttpCode.BAD_REQUEST.value());
                    modelMap.put("msg", "提示，选择的监测任务未选择检测模型，不能进行发布！");
                    return modelMap;
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            logger.error("AdsMonitorTaskController.publishAdsMonitorTask:发布监测任务数据异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增监测任务记录
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "新增监测任务数据")
    @RequestMapping(value = "/addAdsMonitorTask", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增监测任务数据", operationType = "添加")
    public Object addAdsMonitorTask(AdsMonitorTask adsMonitorTask, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsMonitorTask.setCreateBy(u.getId());
                adsMonitorTask.setOrganId(u.getOrgId());
                ModelMap model = new ModelMap();
                adsMonitorTask = adsMonitorTaskService.insert(adsMonitorTask);
                model.addAttribute("adsMonitorTask", adsMonitorTask);
                if (null != adsMonitorTask && null != adsMonitorTask.getId()) {
                    return setSuccessModelMap(model);
                } else {
                    return setModelMap(model, HttpCode.INTERNAL_SERVER_ERROR);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsMonitorTaskController.addAdsMonitorTask:新增监测任务数据异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
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
    public Map<String, Object> queryById(@RequestBody String id, @RequestHeader(required = true) String token) {
        AdsMonitorTask adsMonitorTask = adsMonitorTaskService.selectByPrimaryKey(id);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsMonitorTask", adsMonitorTask);
        return map;
    }

    /**
     * 根据ID获取单条监测任务数据信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单条监测任务数据信息")
    @RequestMapping(value = "/queryAndUpdateById", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条监测任务数据信息", operationType = "查詢")
    public Map<String, Object> queryAndUpdateById(String id, int publishStatus) {
        AdsMonitorTask adsMonitorTask = adsMonitorTaskService.selectByPrimaryKey(id);
        if (new BigDecimal(publishStatus).equals(new BigDecimal(1))) {
            AdsMonitorTask ads = new AdsMonitorTask();
            ads.setPublishStatus(new BigDecimal(2));//修改状态为执行中
            ads.setId(id);
            adsMonitorTaskService.updatePublishStatus(ads);
            //同步监管状态
            if (adsMonitorTask.getMonitorClass().equals("监督抽查") || adsMonitorTask.getMonitorClass().equals("复检任务"))
                adsMonitorTaskService.updateAsmsTaskStateByIdAndType(adsMonitorTask.getId(), adsMonitorTask.getMonitorClass(), "3");
            else
                adsMonitorTaskService.updateAsmsTaskStateByIdAndType(adsMonitorTask.getSupId(), adsMonitorTask.getMonitorClass(), "3");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsMonitorTask", adsMonitorTask);
        return map;
    }

    /**
     * 修改监测任务数据信息
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "修改监测任务数据信息")
    @RequestMapping(value = "/updateAdsMonitorTask", method = RequestMethod.POST)
    @SystemControllerLog(description = "修改监测任务数据信息", operationType = "修改")
    public Object updateAdsMonitorTask(AdsMonitorTask adsMonitorTask, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsMonitorTask.setUpdateBy(u.getId());
                int result = adsMonitorTaskService.updateByPrimaryKey(adsMonitorTask);
                if (result > 0) {
                    return setSuccessModelMap(new ModelMap());
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsMonitorTaskController.updateAdsMonitorTask:修改监测任务数据异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除监测任务信息
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "删除监测任务信息")
    @RequestMapping(value = "/deleteAdsMonitorTask", method = RequestMethod.POST)
    @SystemControllerLog(description = "删除监测任务信息", operationType = "刪除")
    public Object deleteAdsMonitorTask(AdsMonitorTask adsMonitorTask) {
        try {
            adsMonitorTaskService.delete(adsMonitorTask.getId());
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsMonitorTaskController.deleteAdsMonitorTask:删除监测任务信息异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取行业列表
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "获取行业列表")
    @RequestMapping(value = "/getHangye", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取行业列表", operationType = "查詢")
    public Object getHangye(AdsMonitorTask adsMonitorTask) {
        try {
            List<Map<String, Object>> checkModel = adsMonitorTaskService.getHangye(adsMonitorTask);
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取年度列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取年度列表")
    @RequestMapping(value = "/getYear", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取年度列表", operationType = "查詢")
    public Object getYear() {
        try {
            List<Map<String, Object>> checkModel = adsMonitorTaskService.getYear();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取级别列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取级别列表")
    @RequestMapping(value = "/getLevel", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取级别列表", operationType = "查詢")
    public Object getLevel() {
        try {
            List<Map<String, Object>> checkModel = adsMonitorTaskService.getLevel();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据多种条件获取监测任务列表(查询承担单位接收任务列表)
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "根据多种条件获取监测任务列表(查询承担单位接收任务列表)")
    @RequestMapping(value = "/getPageInfoWithParam", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据多种条件获取监测任务列表(查询承担单位接收任务列表)", operationType = "查詢")
    public Object getPageInfoWithParam(AdsMonitorTask adsMonitorTask, int start, int length, String industry, String year, String status, String type, String level, @RequestHeader String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            String orgId = u.getOrgId();
            PageInfo<AdsMonitorTask> pageInfo = adsMonitorTaskService.getPageInfoWithParam(adsMonitorTask, ((start + 1) / length) + 1, length, industry, year, status, type, orgId, level);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据多种条件获取任务列表")
    @RequestMapping(value = "/getPageInfoWithType", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据多种条件获取任务列表", operationType = "查詢")
    public Object getPageInfoWithType(AdsMonitorTask adsMonitorTask, int start, int length, String status, String type, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            String orgId = u.getOrgId();
            PageInfo<AdsMonitorTask> pageInfo = adsMonitorTaskService.getPageInfoWithType(adsMonitorTask, ((start + 1) / length) + 1, length, status, type, orgId);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取该机构的监测列表
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据条件获取该机构的监测列表")
    @RequestMapping(value = "/getPageAdsMonitorTask", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件获取该机构的监测列表", operationType = "查詢")
    public Object getPageAdsMonitorTask(AdsMonitorTask adsMonitorTask, String id, int start, int length) {
        try {
            PageInfo<AdsMonitorTask> pageInfo = adsMonitorTaskService.getPageAdsMonitorTask(id, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取任务名称列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取任务名称列表")
    @RequestMapping(value = "/selectTaskName", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件获取该机构的监测列表", operationType = "查詢")
    public Object selectTaskName() {
        try {
            List<String> taskName = adsMonitorTaskService.selectTaskName();
            return setSuccessModelMap(new ModelMap(), taskName);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据模型id获取监测任务统计数据，检测对象、检测项目、判定标准数
     *
     * @param model_id
     * @return
     */
    @ApiOperation(value = "根据模型id获取监测任务统计数据，检测对象、检测项目、判定标准数")
    @RequestMapping(value = "/getTaskCountDataByModel", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据模型id获取监测任务统计数据，检测对象、检测项目、判定标准数", operationType = "查詢")
    public Object getTaskCountDataByModel(String model_id) {
        try {
            Map<String, Object> map = adsMonitorTaskService.getTaskCountDataByModel(model_id);
            return setSuccessModelMap(new ModelMap(), map);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 上传文件
     *
     * @param
     * @return
     */
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public Object saveInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String address = this.uploadFile(request);
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            map.put("address", address);
            return map;
        } catch (Exception e) {
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            logger.error("ADS:监测任务上传附件异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取任务ID,任务名称,年度列表
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "获取任务ID,任务名称,年度列表")
    @RequestMapping(value = "/getNameByParam", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取任务ID,任务名称,年度列表", operationType = "查詢")
    public Object getNameByParam(@RequestBody AdsMonitorTask adsMonitorTask, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsMonitorTask.setOrganId(u.getOrgId());
                String now_year = adsMonitorTask.getYear();
                if (StringUtils.isEmpty(now_year)) {
                    SimpleDateFormat sdf = new SimpleDateFormat(" yyyy");
                    adsMonitorTask.setYear(sdf.format(new Date()).trim());
                }
                List<Map<String, Object>> task = adsMonitorTaskService.getNameByParam(adsMonitorTask);
                return setSuccessModelMap(new ModelMap(), task);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 分页获取检测任务名称列表
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "分页获取检测任务名称列表")
    @RequestMapping(value = "/getPageInfoTaskName", method = RequestMethod.POST)
    @SystemControllerLog(description = "分页获取检测任务名称列表", operationType = "查詢")
    public Object getPageInfoTaskName(AdsMonitorTask adsMonitorTask, int start, int length, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsMonitorTask.setOrganId(u.getOrgId());
                PageInfo<AdsMonitorTask> pageInfo = adsMonitorTaskService.getPageInfoTaskName(adsMonitorTask, ((start + 1) / length) + 1, length);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 新增监测任务记录(监管机构下发)
     *
     * @param adsMonitorTask
     * @return
     */
    @ApiOperation(value = "新增监测任务数据(监管机构下发)")
    @RequestMapping(value = "/addAdsMonitorTaskSup", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增监测任务数据(监管机构下发)", operationType = "添加")
    public Object addAdsMonitorTaskSup(AdsMonitorTask adsMonitorTask, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null && adsMonitorTask != null) {
                adsMonitorTask.setCreateBy(u.getId());
                adsMonitorTask.setOrganId(u.getOrgId());
                ModelMap model = new ModelMap();
                //判断是否符合日期范围
                String end_time = adsMonitorTask.getEndTime();
                if (end_time != null) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    String nowDate = df.format(new Date());// new Date()为获取当前系统时间
                    Date end = df.parse(end_time);
                    Date now = df.parse(nowDate);
                    if (now.getTime() > end.getTime()) {
                        //任务日期过期
                        return setModelMap(model, HttpCode.NOT_FOUND_DATA);
                    }
                }

                //判断有没有待发布的
                int count = adsMonitorTaskService.getAdsMonitorTaskNum(adsMonitorTask, 0);
                if (count == 0) { //继续查询该任务在年内执行了多少次
                    int countNum = adsMonitorTaskService.getAdsMonitorTaskNum(adsMonitorTask, 1);//查询该任务被执行了几次
                    if (countNum == 0) {
                        //adsMonitorTask.setBatch("1");//初始化为第一次
                        adsMonitorTask = adsMonitorTaskService.insertSup(adsMonitorTask);
                        model.addAttribute("adsMonitorTask", adsMonitorTask);
                        if (null != adsMonitorTask && null != adsMonitorTask.getId()) {
                            return setSuccessModelMap(model);
                        } else {
                            return setModelMap(model, HttpCode.INTERNAL_SERVER_ERROR);
                        }
                    } else {
//                        if (countNum+1>4){
//                            return setModelMap(model, HttpCode.GONE);
//                        }
                        //判断任务还有没有未执行完成的
                        int cc = adsMonitorTaskService.getAdsMonitorTaskNum(adsMonitorTask, 2);
                        if (cc < countNum) {
                            return setModelMap(model, HttpCode.CONFLICT);
                        }
                        //adsMonitorTask.setBatch(countNum+"");
                        AdsMonitorTask adsMonitorTask1 = adsMonitorTaskService.findAdsMonitorTaskInfo(adsMonitorTask);
                        adsMonitorTask.setStartTime(adsMonitorTask1.getEndTime());
                        adsMonitorTask.setEndTime(adsMonitorTask1.getEndTime());
                        adsMonitorTask.setBatch(adsMonitorTask1.getBatch());
                        adsMonitorTask = adsMonitorTaskService.insertSup(adsMonitorTask);
                        model.addAttribute("adsMonitorTask", adsMonitorTask);
                        if (null != adsMonitorTask && null != adsMonitorTask.getId()) {
                            return setSuccessModelMap(model);
                        } else {
                            return setModelMap(model, HttpCode.INTERNAL_SERVER_ERROR);
                        }
                    }
                } else {
                    return setModelMap(model, HttpCode.CONFLICT);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsMonitorTaskController.addAdsMonitorTaskSup:新增监测任务数据(监管机构下发)异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询监督抽查监测任务
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询监督抽查监测任务")
    @RequestMapping(value = "/findAuditAdsMonitorTaskById", method = RequestMethod.POST)
    @SystemControllerLog(description = "查询监督抽查监测任务", operationType = "查詢")
    public Object findAuditAdsMonitorTaskById(String id, String sampleOrganId) {
        try {
            AdsMonitorTask adsMonitorTask = adsMonitorTaskService.findAuditAdsMonitorTaskById(id, sampleOrganId);
            return setSuccessModelMap(new ModelMap(), adsMonitorTask);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "导出监测任务总览word文档")
    @RequestMapping(value = "/exportTaskDoc", method = RequestMethod.POST)
    @SystemControllerLog(description = "导出监测任务总览word文档", operationType = "查詢")
    public void exportTaskDoc(String adsMonitorTaskId, HttpServletRequest request, HttpServletResponse response) {
        OutputStream os = null;
        ByteArrayOutputStream bos = null;
        InputStream inputStream = null;
        try {
            AdsMonitorTask task = adsMonitorTaskService.selectByPrimaryKey(adsMonitorTaskId);
            List<AdsOrganTask> adsOrganTaskList = adsOrganTaskService.getOrganListByTaskId(adsMonitorTaskId);
            task.setAdsOrganTaskList(adsOrganTaskList);
            bos = CreateDoc.createAdsTaskDoc(task, request.getServletContext());
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


    public void exportAdsTask(List<AdsMonitorTask> adsMonitorTaskList, HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        for (int i = 0; i < adsMonitorTaskList.size(); i++) {
            AdsMonitorTask task = adsMonitorTaskList.get(i);
            HSSFSheet sheet = wb.createSheet("记录" + String.valueOf(i) + "(" + task.getTaskName() + ")");//"记录"+i+":"+
            // 表头字体及样式
            HSSFFont headfont = wb.createFont();
            headfont.setFontName("黑体");
            headfont.setFontHeightInPoints((short) 18);// 字体大小
            HSSFCellStyle headstyle = wb.createCellStyle();
            headstyle.setFont(headfont);
            headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            headstyle.setLocked(true);
            // 副标题字体及样式
            HSSFFont aheadfont = wb.createFont();
            aheadfont.setFontName("微软雅黑");
            aheadfont.setFontHeightInPoints((short) 16);// 字体大小
            HSSFCellStyle aheadstyle = wb.createCellStyle();
            aheadstyle.setFont(aheadfont);
            aheadstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            aheadstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            aheadstyle.setLocked(true);
            //普通单元格样式
            HSSFFont font = wb.createFont();
            font.setFontName("微软雅黑");
            font.setFontHeightInPoints((short) 10);// 字体大小
            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 靠左
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            style.setLocked(true);
            //第一行
            HSSFRow row = sheet.createRow(0);// 创建第一行
            row.setHeight((short) 500);
            HSSFCell cell = row.createCell(0);// 创建第一行的第一个单元格
            cell.setCellValue(task.getMonitorClass() + " 检测任务详情");
            cell.setCellStyle(headstyle);
            CellRangeAddress range = new CellRangeAddress(0, 0, 0, 6);
            sheet.addMergedRegion(range);
            //第二行
            row = sheet.createRow(1);// 创建第2行
            row.setHeight((short) 400);
            cell = row.createCell(0);//
            cell.setCellValue("基本信息");
            cell.setCellStyle(aheadstyle);
            range = new CellRangeAddress(1, 1, 0, 6);
            sheet.addMergedRegion(range);
            //第三行
            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellValue("任务名称：   " + (task.getTaskName() != null ? task.getTaskName() : ""));
            cell.setCellStyle(style);
            range = new CellRangeAddress(2, 2, 0, 6);
            sheet.addMergedRegion(range);
            //第四行
            row = sheet.createRow(3);
            cell = row.createCell(0);
            cell.setCellValue("年度：   " + (task.getYear() != null ? task.getYear() : ""));
            cell.setCellStyle(style);
            range = new CellRangeAddress(3, 3, 0, 6);
            sheet.addMergedRegion(range);
            //第五行
            row = sheet.createRow(4);
            cell = row.createCell(0);
            cell.setCellValue("批次：   " + (task.getBatch() != null ? task.getBatch() : ""));
            cell.setCellStyle(style);
            range = new CellRangeAddress(4, 4, 0, 6);
            sheet.addMergedRegion(range);
            row = sheet.createRow(5);//第六行
            cell = row.createCell(0);
            cell.setCellValue("发布单位：   " + (task.getReleaseUnit() != null ? task.getReleaseUnit() : ""));
            cell.setCellStyle(style);
            range = new CellRangeAddress(5, 5, 0, 6);
            sheet.addMergedRegion(range);
            row = sheet.createRow(6);//第七行
            cell = row.createCell(0);
            cell.setCellValue("开始时间：   " + (task.getStartTime() != null ? task.getStartTime() : ""));
            cell.setCellStyle(style);
            range = new CellRangeAddress(6, 6, 0, 6);
            sheet.addMergedRegion(range);
            row = sheet.createRow(7);//第八行
            cell = row.createCell(0);
            cell.setCellValue("结束时间：   " + (task.getEndTime() != null ? task.getEndTime() : ""));
            cell.setCellStyle(style);
            range = new CellRangeAddress(7, 7, 0, 6);
            sheet.addMergedRegion(range);
            row = sheet.createRow(8);//第九行
            cell = row.createCell(0);
            cell.setCellValue("抽样上报截止时间：   " + (task.getDeadline() != null ? task.getDeadline() : ""));
            cell.setCellStyle(style);
            range = new CellRangeAddress(8, 8, 0, 6);
            sheet.addMergedRegion(range);
            row = sheet.createRow(9);//第十行
            cell = row.createCell(0);
            BigDecimal a = new BigDecimal(0);
            cell.setCellValue("抽样分离：   " + (a.equals(task.getSeparation()) ? "否" : "是"));
            cell.setCellStyle(style);
            range = new CellRangeAddress(9, 9, 0, 6);
            sheet.addMergedRegion(range);
            List<AdsOrganTask> adsOrganTaskList = adsOrganTaskService.getOrganListByTaskId(task.getId());
            if (adsOrganTaskList.size() > 0) {
                row = sheet.createRow(10);
                row.setHeight((short) 400);
                cell = row.createCell(0);
                cell.setCellValue("检测信息");
                cell.setCellStyle(aheadstyle);
                range = new CellRangeAddress(10, 10, 0, 6);
                sheet.addMergedRegion(range);
                int rowNum = 10;
                for (int j = 0; j < adsOrganTaskList.size(); j++) {
                    AdsOrganTask organTask = adsOrganTaskList.get(j);
                    List<AdsCheckInfo> infos = adsCheckInfoService.getInfo(organTask.getId());
                    if (infos.size() > 0) {
                        for (int k = 0; k < infos.size(); k++) {
                            AdsCheckInfo info = infos.get(k);
                            row = sheet.createRow(rowNum + 1);
                            cell = row.createCell(0);
                            cell.setCellValue("样品名称：   " + (info.getSampleName() != null ? info.getSampleName() : ""));
                            cell.setCellStyle(style);
                            range = new CellRangeAddress(rowNum + 1, rowNum + 1, 0, 6);
                            sheet.addMergedRegion(range);
                            row = sheet.createRow(rowNum + 2);
                            cell = row.createCell(0);
                            cell.setCellValue("样品编码：   " + (info.getSampleCode() != null ? info.getSampleCode() : ""));
                            cell.setCellStyle(style);
                            range = new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 6);
                            sheet.addMergedRegion(range);
                            row = sheet.createRow(rowNum + 3);
                            cell = row.createCell(0);
                            cell.setCellValue("抽样单位：   " + (info.getSampleDeparment() != null ? info.getSampleDeparment() : ""));
                            cell.setCellStyle(style);
                            range = new CellRangeAddress(rowNum + 3, rowNum + 3, 0, 6);
                            sheet.addMergedRegion(range);
                            row = sheet.createRow(rowNum + 4);
                            cell = row.createCell(0);
                            cell.setCellValue("监测单位：   " + (info.getCheckOrgan() != null ? info.getCheckOrgan() : ""));
                            cell.setCellStyle(style);
                            range = new CellRangeAddress(rowNum + 4, rowNum + 4, 0, 6);
                            sheet.addMergedRegion(range);
                            row = sheet.createRow(rowNum + 5);
                            cell = row.createCell(0);
                            cell.setCellValue("检测项");
                            cell.setCellStyle(style);
                            range = new CellRangeAddress(rowNum + 5, rowNum + 5, 0, 1);
                            sheet.addMergedRegion(range);
                            cell = row.createCell(2);
                            cell.setCellValue("检测值");
                            cell.setCellStyle(style);
                            cell = row.createCell(3);
                            cell.setCellValue("上限值");
                            cell.setCellStyle(style);
                            cell = row.createCell(4);
                            cell.setCellValue("检测结果");
                            cell.setCellStyle(style);
                            cell = row.createCell(5);
                            cell.setCellValue("LOD");
                            cell.setCellStyle(style);
                            cell = row.createCell(6);
                            cell.setCellValue("LOQ");
                            cell.setCellStyle(style);
                            List<AdsInfoProject> adsInfoProjects = adsInfoProjectService.getPageInfoWithCheckInfoId(info.getId());
                            for (int x = 0; x < adsInfoProjects.size(); x++) {
                                AdsInfoProject adsInfoProject = adsInfoProjects.get(x);
                                sheet.addMergedRegion(range);
                                row = sheet.createRow(rowNum + 6 + x);
                                cell = row.createCell(0);
                                cell.setCellValue(adsInfoProject.getCheckProject() != null ? adsInfoProject.getCheckProject() : "");//检测项
                                cell.setCellStyle(style);
                                range = new CellRangeAddress(rowNum + 6 + x, rowNum + 6 + x, 0, 1);
                                sheet.addMergedRegion(range);
                                cell = row.createCell(2);
                                cell.setCellValue(adsInfoProject.getCheckNum() != null ? adsInfoProject.getCheckNum() : "");
                                cell.setCellStyle(style);//检测值
                                cell = row.createCell(3);
                                cell.setCellValue(adsInfoProject.getJudgeNum() != null ? adsInfoProject.getJudgeNum() : "");
                                cell.setCellStyle(style);//上限值
                                cell = row.createCell(4);
                                cell.setCellValue(adsInfoProject.getResult().equals("0") ? "不合格" : "合格");
                                cell.setCellStyle(style);//检测结果
                                cell = row.createCell(5);
                                cell.setCellValue(adsInfoProject.getLOD() != null ? adsInfoProject.getLOD() : "");
                                cell.setCellStyle(style);//lod
                                cell = row.createCell(6);
                                cell.setCellValue(adsInfoProject.getLOO() != null ? adsInfoProject.getLOO() : "");
                                cell.setCellStyle(style);//lo0
                            }
                            rowNum = (rowNum + 5 + adsInfoProjects.size());
                        }
                    }
                }
            }
        }
        OutputStream outStream = response.getOutputStream();
        try {
            wb.write(outStream);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            if (outStream != null) {
                outStream.close();
            }
            e.printStackTrace();
        }


    }

}

