package com.sofn.web.ales;

/**
 * Created by Administrator on 2016/9/20.
 */

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.*;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AlesDailyEnforceLaw;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.sys.SysUserBean;
import com.sofn.service.ales.DailyEnforceLawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 5:05
 */
@RestController
@Api(value = "日常执法", description = "日常执法")
@RequestMapping(value = "/dailyEnforceLaw",method = RequestMethod.POST)
public class DailyEnforceLawController extends BaseController {

    @Autowired
    private DailyEnforceLawService dailyEnforceLawService;

    @ApiOperation(value = "验证任务名称重复")
    @SystemControllerLog(description = "验证任务名称重复",operationType="查询")
    @Authorization
    @RequestMapping(value = "/equalsTaskName", method = RequestMethod.POST)
    public Map<String, Object> equalsTaskName(String taskName, @RequestHeader String token ){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        boolean b = dailyEnforceLawService.equalsTaskName(taskName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("b",b);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * "新增日常执法任务--修改日常执法
     *
     * @param
     * @return
     */
    @ApiOperation(value = "新增日常执法任务")
    @SystemControllerLog(description = "新增日常执法任务",operationType="新增")
    @Authorization
    @RequestMapping(value = "/addDailyEnforceLaw", method = RequestMethod.POST)
    public Map<String, Object> addDailyEnforceLaw(@RequestBody AlesDailyEnforceLaw dailyEnforceLaw,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        dailyEnforceLawService.addDailyEnforceLaw(dailyEnforceLaw);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    @ApiOperation(value = "删除")
    @SystemControllerLog(description = "删除", operationType = "删除")
    @Authorization
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public Object del(String jsonString ,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        JSONArray a = JSONArray.parseArray(jsonString);
        for (Object id : a) {
            dailyEnforceLawService.delete(id.toString());
        }
        setSuccessModelMap(new ModelMap());
        return setSuccessModelMap(new ModelMap());
    }

//    /**
//     * 删除日常执法任务
//     *
//     * @param
//     * @return
//     */
//    @ApiOperation(value = "删除日常执法任务")
//    @SystemControllerLog(description = "删除日常执法任务",operationType = "删除")
//    @Authorization
//    @RequestMapping(value = "/deleteDailyEnforceLaw", method = RequestMethod.POST)
//    public Map<String, Object> deleteDailyEnforceLaw(String jsonString) {

//    }


    /**
     * 根据条件获取日常执法任务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取日常执法任务列表")
    @SystemControllerLog(description = "获取日常执法任务列表",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getDailyEnforceLawList", method = RequestMethod.POST)
    public Map<String, Object> getDailyEnforceLawList(AlesDailyEnforceLaw aisDailyEnforceLaw,String areaId,String taskYear,String dateBegin,String dateEnd,int start,int length, String queryCon,String createOrgId,String enforceLawResultFlag,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
          //  return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<AlesDailyEnforceLaw> pageInfo = dailyEnforceLawService.getDailyEnforceLawList(aisDailyEnforceLaw,areaId,taskYear,dateBegin,dateEnd,
                ((start+1)/length)+1,length,queryCon,createOrgId,enforceLawResultFlag);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

    /**
     * 根据ID获取单个日常执法任务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取单个日常执法任务")
    @SystemControllerLog(description = "获取单个日常执法任务",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getDailyEnforceLawById", method = RequestMethod.POST)
    public Map<String, Object> findDailyEnforceLawById(@RequestBody AlesDailyEnforceLaw dailyEnforceLaw ,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
            //  return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        dailyEnforceLaw = dailyEnforceLawService.findDailyEnforceLaw(dailyEnforceLaw.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("dailyEnforceLaw",dailyEnforceLaw);
        return map;
    }

    /**
     * 修改日常执法任务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "修改日常执法任务")
    @SystemControllerLog(description = "修改日常执法任务",operationType="修改")
    @Authorization
    @RequestMapping(value = "/updateDailyEnforceLaw", method = RequestMethod.POST)
    public Map<String, Object> updateDailyEnforceLaw(@RequestBody AlesDailyEnforceLaw dailyEnforceLaw ,@RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        dailyEnforceLawService.addDailyEnforceLaw(dailyEnforceLaw);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    /**
     * 删除日常执法任务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "删除日常执法任务")
    @SystemControllerLog(description = "删除日常执法任务",operationType = "删除")
    @Authorization
    @RequestMapping(value = "/deleteDailyEnforceLaw", method = RequestMethod.POST)
    public Map<String, Object> deleteDailyEnforceLaw() {

        return null;
    }

    /**
     * 获取登录用户
     * @param token
     * @return
     */
    @ApiOperation(value = "根据登录用户获取用户所属机构信息")
    @SystemControllerLog(description = "根据登录用户获取用户所属机构信息",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getOrgbyToken")
    public Object getOrgbyToken( @RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        Organization o = dailyEnforceLawService.getOrganizationByToken(token);
        logger.info("返回查询的数据集为===>>>"+o);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data",o);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据用户token获取当前用户所属机构下所有用户")
    @SystemControllerLog(description = "根据用户token获取当前用户所属机构下所有用户",operationType = "查询")
    @Authorization
    //@RequiresPermissions("asms.tools.getUsersListByUserToken")
    @RequestMapping(value = "/getUsersListByUserToken")
    public Object getUsersListByUserToken(@RequestHeader String token, AsmsSubjEnforceLaw task, QueryParameter p,String keyword) {
//        int count = dailyEnforceLawService.getUsersCount(p.getToken());
//        Page page = new Page();
//        page.setDraw(1);
//        page.setStart((long)(p.getStart()));
//        page.setLength((long)p.getLength());
//        page.setRecordsTotal(count);
//        page.doPage();
//        List<SysUserBean> beans = null;
//        if (count>0){
//            beans = dailyEnforceLawService.getUserList(page,keyword,p.getToken());
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
//        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
//        map.put("data", beans);
//        map.put("page", page);
//        return map;
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        com.sofn.util.Page page = new com.sofn.util.Page();
        page.setStart((long)(p.getStart()));
        page.setLength((long)p.getLength());
        PageInfo<SysUserBean> pageInfo = dailyEnforceLawService.getSysUserListByOrgId(page,user.getOrgId(),keyword);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("data",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    /**
     * 检测机构列表
     * ***********JcOrgTools**************
     * */

    @ApiOperation(value = "根据用户token获取用户所属机构下所有检测机构列表")
    @SystemControllerLog(description = "根据用户token获取用户所属机构下所有检测机构列表",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getOrgsListByUserToken")
    public Object getOrgsListByUserToken(AsmsSubjDetection subjDetection, com.sofn.util.Page page, @RequestHeader String token) {
        PageInfo pageInfo = dailyEnforceLawService.getOrgs(subjDetection,page,token);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        return setSuccessModelMap(modelMap,pageInfo.getList());
    }

    /**
     * 通过ID获取生产经营主体详情
     * @param register
     * @return
     */
    @ApiOperation(value = "通过ID获取生产经营主体详情")
    @SystemControllerLog(description = "通过ID获取生产经营主体详情",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findSubjEntById")
    public Object findSubjEntById(@RequestBody TtsScltxxcjRegiter register,@RequestHeader String token ){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        register = dailyEnforceLawService.findSubjEntById(register);
        return setSuccessModelMap(new ModelMap(),register);
    }


    @ApiOperation(value = "根据IDCODE获取生产经营主体")
    @SystemControllerLog(description = "根据IDCODE获取生产经营主体",operationType = "查询")
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
        map.put("enterprise",dailyEnforceLawService.findEnterpriseById(entityIdCode));
        return map;
    }
    @ApiOperation(value = "根据IDCODE获取临时生产经营主体")
    @SystemControllerLog(description = "根据IDCODE获取临时生产经营主体",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findEnterpriseTmpById",method = RequestMethod.POST)
    public Map<String,Object> findEnterpriseTmpById( String entityIdCode,@RequestHeader String token){
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
        map.put("dataSubjEntTemp",dailyEnforceLawService.findEnterpriseTmpById(entityIdCode));
        return map;
    }

}
