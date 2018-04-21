package com.sofn.web.ales;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.sofn.core.util.FastDFSUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.MQInfo;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.*;
import com.sofn.service.ales.AlesJdccTaskService;
import com.sofn.service.ales.AlesMQInfoService;
import com.sofn.service.ales.AlesMyNewCheckTaskService;
import com.sofn.service.ales.AlesWtTaskMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;


/**
 * 监督抽查
 *
 * @author LiBing
 */
@RestController
@Api(value = "承担任务", description = "承担任务")
@RequestMapping(value = "/alesmynewchecktask", method = RequestMethod.POST)
public class AlesMyNewCheckTaskController extends BaseController {
    @Autowired
    private AlesMyNewCheckTaskService service;
    @Autowired
    private AlesJdccTaskService jdccService;
    @Autowired
    private AlesWtTaskMonitorService alesWtTaskMonitorService;
    @Autowired
    private AlesMQInfoService alesMQInfoService;

    @ApiOperation(value = "根据用户获取新监督抽查任务")
    @SystemControllerLog(description = "根据用户获取新监督抽查任务", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getCheckTaskByUser", method = RequestMethod.POST)
    public Object getCheckTaskByUser(AsmsCheckTask checkTask, String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        List<Map<String, Object>> data = service.getCheckTaskByUser(checkTask, token);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "根据用户获取监督抽查任务[分页]-app")
    @SystemControllerLog(description = "根据用户获取监督抽查任务[分页]-app", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getTaskPagesByToken")
    public Object getTaskPagesByToken(AsmsCheckTask checkTask, int start, int length, String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<List<AsmsCheckTask>> data = service.getTaskPagesByToken(checkTask, ((start + 1) / length) + 1, length, token);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "根据ID查询任务详情")
    @SystemControllerLog(description = "根据ID查询任务详情", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getTaskById")
    public Object getTaskById(@RequestBody AsmsCheckTask checkTask,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        AsmsCheckTask o = service.queryByid(checkTask.getId());
        List<AsmsCheckBearUnit> list = service.getUnitByTaskId(checkTask.getId());
        Map<String, Object> map = new HashMap<>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", o);
        map.put("speList", list);
        return map;
    }

    @ApiOperation(value = "根据用户获取历史监督抽查任务")
    @SystemControllerLog(description = "根据用户获取历史监督抽查任务", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getHistoryCheckTaskListByUser")
    public Object getHistoryCheckTaskByUserlist(AsmsCheckTask checkTask, String dateBegin, String dateEnd, int start, int length, String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<List<Map<String, Object>>> data = service.getHistoryCheckTaskListByUser(checkTask, dateBegin, dateEnd, ((start + 1) / length) + 1, length, token);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "根据任务获取抽查对象")
    @SystemControllerLog(description = "根据任务获取抽查对象", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getMyTaskObjById")
    public Object getMyTaskObjById(AsmsCheckTask taskSample, String dateBegin, String dateEnd, int start, int length,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<List<Map<String, Object>>> data = service.getMyTaskObjById(taskSample, dateBegin, dateEnd, ((start + 1) / length) + 1, length);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "根据抽查对象ID获取抽样单列表")
    @SystemControllerLog(description = "根据抽查对象ID获取抽样单列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getTaskSampleListByJcInfo")
    public Object getTaskSampleListByJcInfo(AlesTaskSample taskSample, QueryParameter p,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<AlesTaskSample> data = service.taskSampleListByJcInfo(taskSample, p);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "抽样单新增")
    @SystemControllerLog(description = "抽样单新增", operationType = "新增")
    @Authorization
    @RequestMapping(value = "/addSample")
    public Object addSample(@RequestBody AlesTaskSample taskSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        service.addSample(taskSample);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "抽样单修改")
    @SystemControllerLog(description = "抽样单修改", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/updSample")
    public Object updSample(@RequestBody AlesTaskSample taskSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        service.updSample(taskSample);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "根据id获取抽样单信息")
    @SystemControllerLog(description = "根据id获取抽样单信息", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getSampleById")
    public Object getSampleById(@RequestBody AlesTaskSample taskSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        AlesTaskSample data = service.getSampleById(taskSample.getId());
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "根据抽查对象ID获取抽样单列表")
    @SystemControllerLog(description = "根据抽查对象ID获取抽样单列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getJdccTaskSampleListByJcInfo")
    public Object getJdccTaskSampleListByJcInfo(AlesTaskSample taskSample, QueryParameter p,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<AlesTaskSample> data = service.taskSampleListByJcInfo(taskSample, p);
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "监督抽查抽样单新增")
    @SystemControllerLog(description = "监督抽查抽样单新增", operationType = "新增")
    @Authorization
    @RequestMapping(value = "/addJdccSample")
    public Object addJdccSample(@RequestBody AlesJdccTaskSample taskSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        jdccService.addSample(taskSample);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "监督抽查抽样单修改")
    @SystemControllerLog(description = "监督抽查抽样单修改", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/updJdccSample")
    public Object updJdccSample(@RequestBody AlesJdccTaskSample taskSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        jdccService.updSample(taskSample);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "根据id获取监督抽查抽样单信息")
    @SystemControllerLog(description = "根据id获取监督抽查抽样单信息", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getJdccSampleById")
    public Object getJdccSampleById(@RequestBody AlesJdccTaskSample taskSample,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        AlesJdccTaskSample data = jdccService.getSampleById(taskSample.getId());
        return setSuccessModelMap(new ModelMap(), data);
    }

    @ApiOperation(value = "根据ID提交单个抽样单")
    @SystemControllerLog(description = "根据ID提交单个抽样单", operationType = "提交")
    @Authorization
    @RequestMapping(value = "/singleSyncToAds" ,method = RequestMethod.POST)
    public Object singleSyncToAds(String jsonString,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        if(currentUser == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        String infoEnum = null;
        for(Object obj: jsonArray){
            JSONArray array = JSONArray.parseArray(obj.toString());
            infoEnum = service.sinleSyncToAds(currentUser,array.get(2).toString(),array.get(1).toString(),array.get(0).toString());
        }
        ModelMap map = new ModelMap();
        map.addAttribute("key", infoEnum);
        return setSuccessModelMap(map, HttpCode.OK);
    }

    @ApiOperation(value = "根据ID删除抽样单")
    @SystemControllerLog(description = "根据ID删除抽样单", operationType = "删除")
    @Authorization
    @RequestMapping(value = "/delSampleById")
    public Object delSampleById(String jsonString,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            service.delSampleById(id.toString());
        }
        return setSuccessModelMap(new ModelMap());
    }
    @ApiOperation(value = "根据附件uri下载")
    @SystemControllerLog(description = "根据附件uri下载",operationType = "下载")
    @RequestMapping(value = "/downFile", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downFile(@RequestParam(value = "fileUrl", required = false) String fileUrl,
                                           @RequestParam(value = "fileName", required = false) String fileName)
            throws Exception {
        return FastDFSUtil.dowloadFile(fileUrl, fileName);
    }

    @ApiOperation(value = "根据监督抽查任务ID提交抽样单")
    @SystemControllerLog(description = "根据监督抽查任务ID提交抽样单", operationType = "修改")
    @Authorization
    @RequestMapping(value = "/syncToAds", method = RequestMethod.POST)
    public Object syncToAds(@RequestBody String jsonSting,@RequestHeader String token) {//@RequestBody AlesTaskSample taskSample
        Assert.isNotBlank(token,"token");
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        if(currentUser == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        String infoEnum = null;
        JSONArray array = JSONArray.parseArray(jsonSting);
        for(Object object : array){
            infoEnum  = service.sinleSyncToAds(currentUser,object.toString(),null,null);
        }

        ModelMap map = new ModelMap();
       map.addAttribute("key", infoEnum);
        return setSuccessModelMap(map, HttpCode.OK);
    }

    @ApiOperation(value = "生成临时码")
    @SystemControllerLog(description = "生成临时码", operationType = "增加")
    @Authorization
    @RequestMapping(value = "/temporaryCode")
    public Map<String, String> temporaryCode(@RequestHeader String token, int mainManagementBody, int organizationForms, String mainBodyNumber, String projectCategoryCode,String currentindustry) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, String> map = new HashMap<String, String>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE+"");
            return map;
        }
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
            }else if(mainManagementBody == 3){
                mainBodyCategoriesCode = IdGenerator.MainBodyCategories.AgriculturalMaterialsSubject;//农资主体
            }
            if (organizationForms == 0) {
                organizationFormsCode = IdGenerator.OrganizationForms.Enterprise;//企业
            } else if (organizationForms == 1) {
                organizationFormsCode = IdGenerator.OrganizationForms.Individual;//个人
            } else if (organizationForms == 2){
                organizationFormsCode = IdGenerator.OrganizationForms.Familyfarm;//家庭农场
            } else if (organizationForms == 3){
                organizationFormsCode = IdGenerator.OrganizationForms.Cooperative;//合作社
            }
            //获取农产品名称编码
            String productCode = null; //产品编码
            QueryParameter queryParameter = new QueryParameter();
            queryParameter.setLength(1);
            PageInfo<Map<String, Object>> object =  alesWtTaskMonitorService.productList(currentindustry,queryParameter,projectCategoryCode);
          List<Map<String, Object>> list = object.getList();
            for(int i = 0 ; i < list.size(); ++ i){
                SysArgiProduct sysArgiProduct = (SysArgiProduct)list.get(i);
                 productCode = sysArgiProduct.getProductCode();
            }
            String productTemporaryCode = idGen.getProductTemporaryCode(IdGenerator.ServiceType.Retrospect, mainBodyCategoriesCode, 
                    organizationFormsCode, mainBodyNumber, productCode);//临时码
            Map<String, String> map = new HashedMap();
            map.put("productTemporaryCode", productTemporaryCode);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "判断样品编码是否存在")
    @SystemControllerLog(description = "判断样品编码是否存在", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findAdsMonitorSample", method = RequestMethod.POST)
    public Object findAdsMonitorSample(String sampleCode,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        AlesTaskSample alesTaskSample = service.findAdsMonitorSample(sampleCode);
        if (alesTaskSample != null) {
            return setSuccessModelMap(new ModelMap(), "false");
        } else {
            return setSuccessModelMap(new ModelMap(), "true");
        }

    }

    @ApiOperation(value = "样品及检测信息")
    @SystemControllerLog(description = "样品及检测信息", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/sampleResultList", method = RequestMethod.POST)
    public Object sampleResultList(AlesTaskSample alesTaskSample, QueryParameter queryParameter,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.sampleAndResultList(alesTaskSample, queryParameter));
    }

    @ApiOperation(value = "检测结果列表")
    @SystemControllerLog(description = "检测结果列表", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/resultInfomation", method = RequestMethod.POST)
    public Object resultInfomation(AdsInfoProject adsInfoProject,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        return setSuccessModelMap(new ModelMap(), service.sampleResultList(adsInfoProject));
    }

    @ApiOperation(value = "获取消息")
    @SystemControllerLog(description = "获取消息", operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getAmqMessage", method = RequestMethod.POST)
    public Object getAmqMessage(String token) {
        CurrentUser u= WebUtil.getCurrentUser(token);
        String organId=u.getOrganization().getOrgId();
        List<MQInfo> infos = alesMQInfoService.getForConsumer(organId);
        return setSuccessModelMap(new ModelMap(),infos.size());
    }

}
