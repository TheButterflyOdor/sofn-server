package com.sofn.web.ales;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.Assert;
import com.sofn.core.util.SecurityUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.encrypt.RSAUtils;
import com.sofn.model.generator.AsmsSubjElCancel;
import com.sofn.model.generator.AsmsSubjElChange;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import com.sofn.service.ales.SubjEnforceLawService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**

 /**
 * @author zhangdong
 * @version 2016年09月20日 下午 5:05
 */
@RestController
@Api(value = "执法机构账号信息",description = "执法机构账号信息")
@RequestMapping(value = "/subjEnforceLaw")
public class SubjEnforceLawController extends BaseController{
    @Autowired
    private SubjEnforceLawService subjEnforceLawService;

    /**
     * 根据ID获取本执法机构变更历史列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取本执法机构变更历史列表")
    @SystemControllerLog(description = "获取本执法机构变更历史列表",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getChangeListByElId",method = RequestMethod.POST)
    public Object getChangeListByElId(AsmsSubjElChange subjElChange,Page page,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<AsmsSubjElChange> pageInfo = subjEnforceLawService.getChangeListByElId(subjElChange, page,token);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * "新增执法机构备案修改申请
     * @param
     * @return
     */
    @ApiOperation(value = "新增执法机构备案修改申请")
    @SystemControllerLog(description = "新增执法机构备案修改申请",operationType = "添加")
    @Authorization
    @RequestMapping(value = "/addSubjElChange",method = RequestMethod.POST)
    public Object addSubjElChange(@RequestHeader String token,@RequestBody AsmsSubjElChange subjElChange){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        int result = subjEnforceLawService.addSubjElChange(token,subjElChange);
        if(result==2) {
            return setSuccessModelMap(new ModelMap());
        }else if(result==0){
            return setModelMap(new ModelMap(),HttpCode.CONFLICT);
        }else{
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
    }

    /**
     * 新增执法机构注销申请
     * @param subjElCancel
     * @return
     */
    @ApiOperation(value = "新增执法机构注销申请")
    @SystemControllerLog(description = "新增执法机构注销申请",operationType = "添加")
    @Authorization
    @RequestMapping(value = "/addSubjElCancel")
    public Object addSubjElCancel(@RequestHeader String token,@RequestBody AsmsSubjElCancel subjElCancel){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        int result = subjEnforceLawService.addSubjElCancel(token,subjElCancel);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据ID获取执法机构备案信息
     * @param
     * @return
     */
    @ApiOperation(value = "获取执法机构备案信息")
    @SystemControllerLog(description = "获取执法机构备案信息",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findSubjEnforceLawById",method = RequestMethod.POST)
    public Object findSubjEnforceLawById(@RequestBody AsmsSubjEnforceLaw subjEnforceLaw,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        subjEnforceLaw = subjEnforceLawService.findSubjEnforceLawById(subjEnforceLaw.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjEl",subjEnforceLaw);
        //subjElChange用来关联执法机构备案修改
        modelMap.addAttribute("subjElChange",subjEnforceLaw);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据登录用户获取登录用户对应执法机构主体详情
     * @param token
     * @return
     */
    @ApiOperation(value = "获取登录用户对应执法机构主体详情")
    @SystemControllerLog(description = "获取登录用户对应执法机构主体详情",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findSubjElByUser")
    public Object findSubjElByUser(@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        Organization org = subjEnforceLawService.findSysOrganizationByToken(token);
        AsmsSubjEnforceLaw subjEnforceLaw = subjEnforceLawService.findSubjEnforceLawById(org.getOrgId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjEl",subjEnforceLaw);
        modelMap.addAttribute("subjElChange",subjEnforceLaw);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据ID获取单个变更详情
     * @param
     * @return
     */
    @ApiOperation(value = "获取执法机构变更详情")
    @SystemControllerLog(description = "获取执法机构变更详情",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findSubjElChangeById",method = RequestMethod.POST)
    public Object findSubjElChangeById(@RequestBody AsmsSubjElChange alesSubjElChange ,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser currentUser =WebUtil.getCurrentUser(token);
        if(currentUser == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        Assert.isNotBlank(alesSubjElChange.getId(),"ID");
        alesSubjElChange = subjEnforceLawService.findSubjElChangeById(alesSubjElChange.getId());
        JSONObject jsonObject = JSONArray.parseObject(alesSubjElChange.getChangeBeforeField());
        AsmsSubjEnforceLaw subjEnforceLaw = jsonObject.getObject("before", AsmsSubjEnforceLaw.class);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjElChange", alesSubjElChange);
        modelMap.addAttribute("subjEl", subjEnforceLaw);
        return setSuccessModelMap(modelMap);
    }


    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @ApiOperation(value = "根据token获取用户信息")
    @SystemControllerLog(description = "根据token获取用户信息",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findCurrentUserByToken")
    public Object findCurrentUserByToken(@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
      //  CurrentUser user = WebUtil.getCurrentUser(token);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("user",user);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 修改密码
     * @param data
     * @return
     */
    @ApiOperation(value = "修改密码")
    @SystemControllerLog(description = "修改密码",operationType = "修改")
    @Authorization
    @RequestMapping(value = "/changePwd")
    public Object changePwd(@RequestHeader String token,@RequestBody String data){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
   //     CurrentUser u= WebUtil.getCurrentUser(token);
        if(u==null||u.getId()==null||"".equals(u.getId())){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        JSONObject jsonObject = JSONArray.parseObject(data);
        boolean result = false;
        if(jsonObject.get("ciphertext")!=null&& StringUtils.isNotBlank(jsonObject.get("ciphertext").toString())){
            //将原有参数定义为变量
            String account=null;
            String oldPwd = "";
            String newPwd = "";
            //密码加密
            String ciphertext= jsonObject.get("ciphertext").toString();
            String desKey= jsonObject.get("desKey").toString();
            //解密-----------------------------------------------------------------------------
            try {
                //将字符串转换为JSON对象
                JSONObject json= RSAUtils.decrypt(ciphertext,RSAUtils.Module.ALES,desKey);
                //将解密后的json值赋值给变量
                if(json.get("account")!=null)account=json.get("account").toString();
                if(json.get("oldPwd")!=null)oldPwd=json.get("oldPwd").toString();
                if(json.get("newPwd")!=null)newPwd=json.get("newPwd").toString();
            }catch (Exception ex){
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
            //密码加密
            String encryptOldPwd= SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(oldPwd));
            if(!u.getPassword().equals(encryptOldPwd)){
                return setModelMap(new ModelMap(),HttpCode.PASSWORD_ERROR);
            }
            result = subjEnforceLawService.changePwd(u.getAccount(),oldPwd,newPwd,token);
        }else{
            //密码加密
            String oldPwd=SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(jsonObject.get("oldPwd").toString()));
            if(!u.getPassword().equals(oldPwd)){
                return setModelMap(new ModelMap(),HttpCode.PASSWORD_ERROR);
            }
            result = subjEnforceLawService.changePwd(u.getAccount(),jsonObject.get("oldPwd").toString(),jsonObject.get("newPwd").toString(),token);
        }
        if(result==true){
            return setSuccessModelMap(new ModelMap(),HttpCode.OK);
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}
