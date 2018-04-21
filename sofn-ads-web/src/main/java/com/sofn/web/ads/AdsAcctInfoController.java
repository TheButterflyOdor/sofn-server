package com.sofn.web.ads;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.Assert;
import com.sofn.core.util.*;
import com.sofn.model.asms.UserAndSubj;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjDtCancel;
import com.sofn.model.generator.AsmsSubjDtChange;
import com.sofn.model.generator.SysUser;
import com.sofn.service.ads.AdsAcctInfoService;
import com.sofn.service.ads.AdsSubjSuperviseService;
import com.sofn.service.ads.AdsSysUserService;
import com.sofn.service.ads.SSOLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**

 /**
 * @author renjinghua  test
 * @version 2016年11月09日 下午 5:05
 */
@RestController
@Api(value = "监测系统账号信息",description = "监测系统账号信息")
@RequestMapping(value = "/acctInfo")
public class AdsAcctInfoController extends BaseController{
    @Autowired
    private AdsAcctInfoService adsAcctInfoService;
    @Autowired
    private AdsSysUserService adsSysUserService;
    @Autowired
    private SSOLoginService ssoLoginService;
    @Autowired
    private AdsSubjSuperviseService adsSubjSuperviseService;

    @ApiOperation(value = "获取检测机构备案信息 By Token")
    @RequestMapping(value = "/findDetectionByToken",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取检测机构备案信息By Token",operationType="查询")
    public Object findDetectionByToken(String token){
        AsmsSubjDetection subjDetection=new AsmsSubjDetection();
        SysUser sysUser=findCurrUser(token);
        subjDetection.setId(sysUser.getOrganizationId());
        subjDetection = adsAcctInfoService.findSubjDetectionById(subjDetection);
        return setSuccessModelMap(new ModelMap(),subjDetection);
    }

    @ApiOperation(value = "获取检测机构备案信息 by Id")
    @RequestMapping(value = "/findDetectionById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取检测机构备案信息by Id",operationType="查询")
    public Object findSubjDetectionById(@RequestBody AsmsSubjDetection subjDetection){

        subjDetection = adsAcctInfoService.findSubjDetectionById(subjDetection);
        return setSuccessModelMap(new ModelMap(),subjDetection);
    }

    @ApiOperation(value = "获取检测机构变更记录列表")
    @RequestMapping(value = "/getChangeListBySvId",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取检测机构变更记录列表",operationType="查询")
    public Object getChangeListBySvId(Page page, String svId){
        PageInfo pageInfo = adsAcctInfoService.getChangeListBySvId(page, svId);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据ID获取变更详情")
    @RequestMapping(value = "/findSubjDtChangeById")
    @SystemControllerLog(description = "根据ID获取变更详情",operationType="查询")
    public Object findSubjDtChangeById(@RequestBody AsmsSubjDtChange subjDtChange){
        Assert.isNotBlank(subjDtChange.getId(),"ID");
        subjDtChange = adsAcctInfoService.findSubjDtChangeById(subjDtChange.getId());
        JSONObject jsonObject = JSONArray.parseObject(subjDtChange.getChangeBeforeField());
        AsmsSubjDetection subjDetection = jsonObject.getObject("before", AsmsSubjDetection.class);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjDtChange", subjDtChange);
        modelMap.addAttribute("subjDt", subjDetection);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "新增检测机构变更申请")
    @RequestMapping(value = "/addSubjDtChange")
    @SystemControllerLog(description = "新增检测机构变更申请",operationType="添加")
    public Object addSubjDtChange(@RequestBody AsmsSubjDtChange subjDtChange,String token){
        try{
            CurrentUser u= WebUtil.getCurrentUser(token);
            adsAcctInfoService.addSubjDtChange(subjDtChange,u);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            logger.error("AdsAcctInfoController.addSubjDtChange:新增检测机构变更申请异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "新增检测机构注销申请")
    @RequestMapping(value = "/addSubjDtCancel")
    @SystemControllerLog(description = "新增检测机构注销申请",operationType="添加")
    public Object addSubjDtCancel(@RequestBody AsmsSubjDtCancel subjDtCancel){
        try{
            adsAcctInfoService.addSubjDtCancel(subjDtCancel);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            logger.error("AdsAcctInfoController.addSubjDtCancel:新增检测机构注销申请异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    public SysUser findCurrUser(String token){
        CurrentUser u= WebUtil.getCurrentUser(token);
        SysUser sysUser=new SysUser();
        sysUser.setOrganizationId(u.getOrgId());
        return sysUser;
    }

    @ApiOperation(value = "获取当前用户账号信息 By Token")
    @RequestMapping(value = "/findSysUserByToken",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取当前用户账号信息 By Token",operationType="查询")
    public Object findSysUserByToken(String token){
        try{
            Assert.isNotBlank(token,"token");
            ModelMap modelMap = new ModelMap();
            CurrentUser u= WebUtil.getCurrentUser(token);
            SysUser user = adsSysUserService.queryById(u.getId());
            modelMap.addAttribute("user", user);
            return setSuccessModelMap(modelMap);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "用户密码修改")
    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    @SystemControllerLog(description = "用户密码修改",operationType="修改")
    public Object changePwd(String account, String oldPwd, String newPwd,String token){
        try{
            ModelMap modelMap = new ModelMap();
            boolean flag = adsSysUserService.changePwd(account,oldPwd,newPwd,token);
            if(flag){
                //密码修改成功，登出用户
                ssoLoginService.logout(token, "pc");
                return setSuccessModelMap(modelMap);
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error("AdsAcctInfoController.changePwd:用户修改密码异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "用户信息变更")
    @RequestMapping(value = "/changeInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "用户信息变更",operationType="修改")
    public Object changeInfo(SysUser sysUser,String token){
        try{
            //参数检查
            Assert.isNotBlank(token,"token");
            ModelMap modelMap = new ModelMap();
            SysUser su = adsSysUserService.changeInfo(sysUser);
            if(su!=null){
                //更新缓存里的用户数据
                CurrentUser u= WebUtil.getCurrentUser(token);
                u.setPostId(su.getPostId());
                u.setPhone(su.getPhone());
                u.setEmail(su.getEmail());
                RedisUtil.set(token,u);
                return setSuccessModelMap(modelMap);
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error("AdsAcctInfoController.changeInfo:用户信息变更异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "用户备案注销")
    @RequestMapping(value = "/cancelInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "用户备案注销",operationType="注销")
    public Object cancelInfo(AsmsSubjDtCancel subjDtCancel,String token){
        try{
            //参数检查
            Assert.isNotBlank(token,"token");
            ModelMap modelMap = new ModelMap();
            int result = adsSysUserService.cancelInfo(token,subjDtCancel);
            if(result == 2){
                return setSuccessModelMap(modelMap);
            }else{
                return setModelMap(new ModelMap(), HttpCode.NOT_FOUND);
            }
        }catch (Exception e){
            logger.error("AdsAcctInfoController.cancelInfo:用户注销异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "忘记密码时根据条件获取用户")
    @SystemControllerLog(description = "忘记密码时根据条件获取用户",operationType = "查询")
    @RequestMapping(value = "/getUserByCondition")
    public Object getUserByCondition(@RequestBody UserAndSubj userAndSubj){
        List list = adsSubjSuperviseService.getUserByCondition(userAndSubj);
        ModelMap modelMap = new ModelMap();
        if(list==null || list.isEmpty() || list.size()!=1){
            modelMap.addAttribute("message","未找到相关用户");
            return setModelMap(modelMap,HttpCode.NOT_FOUND,list);
        }else {
            //加入认证 key
            String code = ValidateCodeUtil.generatorRandomString(4);
            String uuid = UUIDUtil.getUUID();
            RedisUtil.set(uuid,code, 60);   //验证码保存时间
            modelMap.addAttribute("uuid",uuid);
            modelMap.addAttribute("code",code);
            modelMap.addAttribute("user",list.get(0));
            return setSuccessModelMap(modelMap);
        }
    }

    @ApiOperation(value = "根据条件重置密码")
    @SystemControllerLog(description = "根据条件重置密码",operationType = "修改")
    @RequestMapping(value = "/resetPwd")
    public Object resetPwd(@RequestBody UserAndSubj userAndSubj,String uuid,String validateCode){
        //增加修改认证条件
        ModelMap modelMap = new ModelMap();
        String key = (String) RedisUtil.get(uuid);
        if(StringUtils.isNotBlank(key) && key.equalsIgnoreCase(validateCode)){
            boolean result = adsSubjSuperviseService.resetPwd(userAndSubj.getAccount(),userAndSubj.getPwd());
            if(result==true){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
            }
        }else{
            //认证不通过
            modelMap.addAttribute("msg","未通过认证");
            return setModelMap(modelMap,HttpCode.FORBIDDEN);
        }

    }

    @ApiOperation(value = "忘记账号时获取上级监管机构联系信息")
    @SystemControllerLog(description = "忘记账号时获取上级监管机构联系信息",operationType = "查询")
    @RequestMapping(value = "/getSuperior")
    public Object getSuperior(@RequestBody UserAndSubj userAndSubj){
        List list = adsSubjSuperviseService.getSuperiorList(userAndSubj);
        ModelMap modelMap = new ModelMap();
        if(list==null || list.isEmpty() || list.size()!=1){
            modelMap.addAttribute("message","未找到上级机构");
            return setModelMap(modelMap,HttpCode.NOT_FOUND,list);
        }else {
            modelMap.addAttribute("superior",list.get(0));
            return setSuccessModelMap(modelMap);
        }
    }

}
