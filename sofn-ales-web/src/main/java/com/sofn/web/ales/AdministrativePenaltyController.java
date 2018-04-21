package com.sofn.web.ales;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AlesProduceAdminPunish;
import com.sofn.service.ales.ProduceAdminPunishService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

/**
  * 行政处罚
  * @author sofn
  * @version 2016年09月20日 下午 5:05
  */
@RestController
@Api(value = "行政处罚",description = "行政处罚")
@RequestMapping(value = "/administrativePenalty")
public class AdministrativePenaltyController extends BaseController {
    @Autowired
    private ProduceAdminPunishService produceAdminPunishService;
    /**
     * 新增行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "新增行政处罚")
    @SystemControllerLog(description = "新增行政处罚",operationType="新增")
    @Authorization
    @RequestMapping(value = "/addAdministrativePenalty",method = RequestMethod.POST)
    public Map<String, Object> addAdministrativePenalty(@RequestHeader String token,@RequestBody AlesProduceAdminPunish produceAdminPunish){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        produceAdminPunishService.addProduceAdminPunish(token,produceAdminPunish);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * 修改行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "修改行政处罚")
    @SystemControllerLog(description = "修改行政处罚",operationType="修改")
    @Authorization
    @RequestMapping(value = "/updateAdministrativePenalty",method = RequestMethod.POST)
    public Map<String, Object> updateAdministrativePenalty(@RequestBody AlesProduceAdminPunish produceAdminPunish,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        produceAdminPunishService.update(produceAdminPunish);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * 根据条件获取行政处罚列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取行政处罚列表")
    @SystemControllerLog(description = "获取行政处罚列表",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getAdministrativePenaltyList",method = RequestMethod.POST)
    public Map<String, Object> getAdministrativePenaltyList(AlesProduceAdminPunish produceAdminPunish, String taskYear,String dateBegin,String area, String dateEnd, int start, int length, String queryCon,String createOrgId,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        PageInfo<AlesProduceAdminPunish> pageInfo = produceAdminPunishService.getProduceAdminPunishList(produceAdminPunish,taskYear,dateBegin,area,dateEnd, ((start+1)/length)+1,length,queryCon,createOrgId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }
    /**
     * 1
     * 根据ID获取单个行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "获取单个行政处罚")
    @SystemControllerLog(description = "获取单个行政处罚",operationType="查询")
    @Authorization
    @RequestMapping(value = "/findAdministrativePenaltyById",method = RequestMethod.POST)
    public Map<String, Object> findAdministrativePenaltyById(@RequestBody AlesProduceAdminPunish produceAdminPunish,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        produceAdminPunish = produceAdminPunishService.findProduceAdminPunish(produceAdminPunish.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("produceAdminPunish",produceAdminPunish);
        return map;
    }
    /**
     * 暂加的根据ID获取生产经营主体enterpriseId
     * @param
     * @return
     */
    @ApiOperation(value = "根据ID获取生产经营主体")
    @SystemControllerLog(description = "根据ID获取生产经营主体",operationType="查询")
    @Authorization
    @RequestMapping(value = "/findEnterpriseById",method = RequestMethod.POST)
    public Map<String,Object> findEnterpriseById( String entityIdCode,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        Map<String,Object> map = new HashMap<>();
        map.put(ApiConstants.CODE,ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("enterprise",produceAdminPunishService.findEnterpriseById(entityIdCode));
        return map;
    }

     /**
      * 删除
      * @param jsonString
      * @return
      */
    @ApiOperation(value = "删除")
    @SystemControllerLog(description = "删除", operationType = "删除")
    @Authorization
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public Object del(String jsonString ,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        JSONArray a = JSONArray.parseArray(jsonString);
       for (Object id : a) {
           String delId = id.toString();
            produceAdminPunishService.deleteInfo(delId);

       }
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 删除行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "删除行政处罚")
    @SystemControllerLog(description = "删除行政处罚",operationType="删除")
    @Authorization
    @RequestMapping(value = "/deleteAdministrativePenalty",method = RequestMethod.POST)
    public Map<String, Object> deleteAdministrativePenalty(@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        //----------------
        return null;
    }
    /**
     * 打印行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "打印行政处罚")
    @SystemControllerLog(description = "打印行政处罚",operationType="操作")
    @Authorization
    @RequestMapping(value = "/printAdministrativePenalty",method = RequestMethod.POST)
    public Map<String, Object> printAdministrativePenalty(@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        //-----
        return null;
    }

    @ApiOperation(value = "根据ID获取生产经营主体的不良记录列表")
    @SystemControllerLog(description = "根据ID获取生产经营主体的不良记录列表",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getAsmsSubjEntBadrecordByIdList",method = RequestMethod.POST)
    public Object getAsmsSubjEntBadrecordByIdList(Page page, String enterpriseId,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = produceAdminPunishService.getAsmsSubjEntBadrecordByIdList(page, enterpriseId);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    /**
     * 单纯上传文件--暂加
     * @param request
     * @return
     */
    @ApiOperation(value = "上传文件")
    @SystemControllerLog(description = "上传文件",operationType = "添加")
    @Authorization
    @RequestMapping(value = "/upload")
    public Object upload(HttpServletRequest request ,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        String address = "";
        try {
            address = super.uploadFile(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("path",address);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 2
     * 根据ID获取单个行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "获取单个行政处罚")
    @SystemControllerLog(description = "获取单个行政处罚",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getPunishiById",method = RequestMethod.POST)
    public Map<String, Object> getPunishiById(String id,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        AlesProduceAdminPunish produceAdminPunish = produceAdminPunishService.getPunishById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("produceAdminPunish",produceAdminPunish);
        return map;
    }

}
