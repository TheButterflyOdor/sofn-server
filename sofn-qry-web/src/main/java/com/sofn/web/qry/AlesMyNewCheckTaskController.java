package com.sofn.web.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.Assert;
import com.sofn.core.util.DownloadExcelUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AlesTaskSample;
import com.sofn.model.generator.AsmsCheckBearUnit;
import com.sofn.model.generator.AsmsCheckTask;
import com.sofn.model.qry.QueryParameter;
import com.sofn.service.qry.AlesMyNewCheckTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * 任务管理-承担任务
 *
 * @author LiBing
 * @version 2016年9月29日 上午9:1:0
 */
@RestController
@Api(value = "承担任务", description = "承担任务")
@RequestMapping(value = "/alesmynewchecktask", method = RequestMethod.POST)
public class AlesMyNewCheckTaskController extends BaseController {
    private static Logger logger = Logger.getLogger("AlesMyNewCheckTaskController");
    @Autowired
    private AlesMyNewCheckTaskService service;

    @ApiOperation(value = "根据ID查询任务详情")
    @SystemControllerLog(description = "根据ID查询任务详情",operationType="查询")
//    @RequiresPermissions("ales.myncktask.getTaskById")
    @RequestMapping(value = "/getTaskById")
    public Object getTaskById(HttpServletRequest request,@RequestBody AsmsCheckTask r) {
        AsmsCheckTask o = service.queryByid(r.getId());
        List<AsmsCheckBearUnit> list = service.getUnitByTaskId(r.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",o);
        map.put("speList",list);
        return map;
    }

    @ApiOperation(value = "根据用户获取监督抽查任务")
    @SystemControllerLog(description = "根据用户获取监督抽查任务",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getHistoryCheckTaskListByUser")
    public Object getHistoryCheckTaskByUserlist( AsmsCheckTask r, String regionId,String dateBegin, String dateEnd, int start, int length,String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo<List<Map<String, Object>>> data = service.getHistoryCheckTaskListByUser(r,regionId,dateBegin, dateEnd, ((start + 1) / length) + 1, length,token);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data",data);
        return setSuccessModelMap(modelMap);
    }

    //数据导出
    @ApiOperation(value = "导出承担任务数据")
    @SystemControllerLog(description = "导出承担任务数据",operationType = "查询")
   @RequestMapping(value = "exportSubjSv")
   public void exportSubjSv(AsmsCheckTask asmsCheckTask,String regionId,String tsBeginTime,String tsEndTime,Integer start,Integer length,String token){
        start=1;
        length=1000000000;
        //调用service
        PageInfo<List<Map<String, Object>>> pageInfo = service.getHistoryCheckTaskListByUser(asmsCheckTask,regionId,tsBeginTime,tsEndTime,start,length,token);
        //测试
        List<List<String>> datas=new ArrayList<>();
        //添加表头
        List<String> dataTitle=new ArrayList<>();
        dataTitle.add("任务名称");
        dataTitle.add("监测类型");
        dataTitle.add("年度");
        dataTitle.add("任务开始时间");
        dataTitle.add("任务结束时间");
        dataTitle.add("发布单位");
        dataTitle.add("抽样单位");
        dataTitle.add("检测单位");
        dataTitle.add("附件");
        datas.add(dataTitle);
    //遍历
        for (Object o:pageInfo.getList()){
            HashMap a= (HashMap) o;
            List<String> data=new LinkedList<>();
            data.add(a.get("TASKNAME").toString());
            data.add(a.get("TASKTYPE").toString());
            data.add(a.get("TASKYEAR").toString());
            data.add(a.get("TASKBEGINTIME").toString().substring(0,10));
            data.add(a.get("TASKENDTIME").toString().substring(0,10));
            data.add(a.get("TASKRELEASEUNIT").toString());
            data.add(a.get("CYUNITNAME").toString());
            data.add(a.get("JCUNITNAME")!=null?a.get("JCUNITNAME").toString():"");
            data.add(a.get("FILECODE")!=null?a.get("FILECODE").toString():"");
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("承担任务",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "根据任务获取抽查对象")
    @SystemControllerLog(description = "根据任务获取抽查对象",operationType="查询")
//    @RequiresPermissions("ales.myncktask.getMyTaskObjById")
    @Authorization
    @RequestMapping(value = "/getMyTaskObjById")
    public Object getMyTaskObjById(@RequestHeader String token, HttpServletRequest request, AsmsCheckTask r, String dateBegin, String dateEnd, int start, int length) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtil.isNotBlank(r.getId())){
//            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
//            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            map.put("data",null);
            return map;
        }
        PageInfo<List<Map<String, Object>>> data = service.getMyTaskObjById(r, dateBegin, dateEnd, ((start + 1) / length) + 1, length);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",data);
        return map;
    }

    @ApiOperation(value = "根据抽查对象ID获取抽样单列表")
    @SystemControllerLog(description = "根据抽查对象ID获取抽样单列表",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getTaskSampleListByJcInfo")
    public Object getTaskSampleListByJcInfo( HttpServletRequest request, AlesTaskSample r, QueryParameter p,@RequestHeader String token) {

        logger.info("token指令数据===>>>"+token);
         Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo<AlesTaskSample> data = service.taskSampleListByJcInfo(r, p);
        return setSuccessModelMap(new ModelMap(),data);
    }

    @ApiOperation(value = "根据id获取抽样单信息")
    @SystemControllerLog(description = "根据id获取抽样单信息",operationType="查询")
//    @RequiresPermissions("asms.myncktask.getSampleById")
    @RequestMapping(value = "/getSampleById")
    public Object getSampleById(HttpServletRequest request,@RequestBody AlesTaskSample r) {
        AlesTaskSample o = service.getSampleById(r.getId());
        return setSuccessModelMap(new ModelMap(),o);
    }

    @ApiOperation(value = "生成临时码")
//    @RequiresPermissions("asms.myncktask.temporaryCode")
    @RequestMapping(value = "/temporaryCode")
    public Map<String, String> temporaryCode(int mainManagementBody, int organizationForms, String mainBodyNumber, String projectCategoryCode) {
        try {
            IdGenerator idGen = new IdGenerator();
            IdGenerator.MainBodyCategories mainBodyCategoriesCode = null;
            IdGenerator.OrganizationForms organizationFormsCode = null;
            if (mainManagementBody == 0) {
                mainBodyCategoriesCode = IdGenerator.MainBodyCategories.MainWorkingBody;//生产主体
            } else if (mainManagementBody == 1) {
                mainBodyCategoriesCode = IdGenerator.MainBodyCategories.MainManagementBody;//经营主体
            } else if (mainManagementBody == 2) {
                mainBodyCategoriesCode = IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement;//生产经营主体
            }
            if (organizationForms == 0) {
                organizationFormsCode = IdGenerator.OrganizationForms.Enterprise;//企业
            } else if (organizationForms == 1) {
                organizationFormsCode = IdGenerator.OrganizationForms.Individual;//个人
            }
            String productTemporaryCode = idGen.getProductTemporaryCode(IdGenerator.ServiceType.Retrospect,
                    mainBodyCategoriesCode,
                    organizationFormsCode,
                    mainBodyNumber, projectCategoryCode);//临时码
            Map<String, String> map = new HashedMap();
            map.put("productTemporaryCode", productTemporaryCode);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "判断样品编码是否存在")
    @RequestMapping(value = "/findAdsMonitorSample", method = RequestMethod.POST)
    public Object findAdsMonitorSample(String sampleCode) {
        AlesTaskSample alesTaskSample = service.findAdsMonitorSample(sampleCode);
        if (alesTaskSample != null){
            return setSuccessModelMap(new ModelMap(),"false");
        }else {
            return setSuccessModelMap(new ModelMap(),"true");
        }

    }
}
