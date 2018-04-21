package com.sofn.web.asms;

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
import com.sofn.core.util.*;
import com.sofn.core.util.encrypt.RSAUtils;
import com.sofn.model.asms.AsmsSubjSuperviseDto;
import com.sofn.model.asms.UserAndSubj;
import com.sofn.model.generator.AsmsSubjSupervise;
import com.sofn.model.generator.AsmsSubjSvCancel;
import com.sofn.model.generator.AsmsSubjSvChange;
import com.sofn.model.generator.AsmsSubjSvRevoke;
import com.sofn.service.asms.AsmsSubjSuperviseService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:28
 */
@RestController
@Api(value = "主体管理-监管机构",description = "主体管理-监管机构")
@RequestMapping(value = "/subjSupervise",method = RequestMethod.POST)
public class AsmsSubjSuperviseController extends BaseController{

    @Autowired
    private AsmsSubjSuperviseService subjSuperviseService;

    /**
     * 根据查询条件获取监管机构主体列表--系统管理
     * @param subjSupervise
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "根据条件获取监管机构主体列表")
    @SystemControllerLog(description = "根据条件获取监管机构主体列表",operationType = "查询")
    @RequestMapping(value = "/getSubjSuperviseList")
    @Authorization
    public Object getSubjSuperviseList(@RequestHeader String token,AsmsSubjSupervise subjSupervise,Page page,String areaId,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjSuperviseService.getSubjSuperviseListForSys(token,subjSupervise,page,areaId,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        List list1 = new ArrayList();
        //监管界面隐藏密码
        AsmsSubjSuperviseDto asmsSubjSupervise = null;
        for(int i=0; i < list.size();i++){
            asmsSubjSupervise = (AsmsSubjSuperviseDto)list.get(i);
            asmsSubjSupervise.setPassword(null);
            list1.add(asmsSubjSupervise);
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list1);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据查询条件获取监管机构主体列表
     * @param subjSupervise
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "根据条件获取监管机构主体列表-系统管理")
    @SystemControllerLog(description = "根据条件获取监管机构主体列表",operationType = "查询")
    @RequestMapping(value = "/getSubjSuperviseListForSys")
    @Authorization
    public Object getSubjSuperviseListForSys(@RequestHeader String token,AsmsSubjSupervise subjSupervise,Page page,String areaId,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjSuperviseService.getSubjSuperviseListForSys(token,subjSupervise,page,areaId,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过条件获取监管机构主体变更申请
     * @param subjSvChange
     * @param page
     * @param date
     * @return
     */
    @ApiOperation(value = "获取监管机构主体变更申请列表")
    @SystemControllerLog(description = "获取监管机构主体变更申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjSvChangeList")
    @Authorization
    public Object getSubjSvChangeList(@RequestHeader String token,AsmsSubjSvChange subjSvChange,Page page,String date){
        PageInfo pageInfo = subjSuperviseService.getSubjSvChangeList(token,subjSvChange, page, date);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计变更待审核数量
    @ApiOperation(value = "统计监管机构主体变更待审核数量")
    @SystemControllerLog(description = "统计监管机构主体变更待审核数量",operationType = "查询")
    @RequestMapping(value = "/countChangeToAudit")
    @Authorization
    public Object countChangeToAudit(@RequestHeader String token,@RequestBody AsmsSubjSupervise subjSupervise){
        long count = subjSuperviseService.countChangeToAudit(token,subjSupervise.getSvAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取监管机构自己的变更记录列表")
    @SystemControllerLog(description = "获取监管机构自己的变更记录列表",operationType = "查询")
    @RequestMapping(value = "/getChangeListBySvId")
    @Authorization
    public Object getChangeListBySvId(@RequestHeader String token,Page page){
        PageInfo pageInfo = subjSuperviseService.getChangeListBySvId(page, token);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 获取监管机构主体注销申请列表
     * @param page
     * @param date
     * @param svName
     * @param areaId
     * @return
     */
    @ApiOperation(value = "获取监管机构主体注销申请列表")
    @SystemControllerLog(description = "获取监管机构主体注销申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjSvCancelList")
    @Authorization
    public Object getSubjSvCancelList(@RequestHeader String token,Page page,String date,String svName,String areaId,String auditState){
        PageInfo pageInfo = subjSuperviseService.getSubjSvCancelList(token,page, date, svName,areaId,auditState);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计注销待审核数量
    @ApiOperation(value = "统计监管机构主体注销待审核数量")
    @SystemControllerLog(description = "统计监管机构主体注销待审核数量",operationType = "查询")
    @RequestMapping(value = "/countCancelToAudit")
    @Authorization
    public Object countCancelToAudit(@RequestHeader String token,@RequestBody AsmsSubjSupervise subjSupervise){
        long count = subjSuperviseService.countCancelToAudit(token,subjSupervise.getSvAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过条件获取监管机构主体撤销申请
     * @param page
     * @param date
     * @param svName
     * @param areaId
     * @return
     */
    @ApiOperation(value = "获取监管机构主体撤销申请列表")
    @SystemControllerLog(description = "获取监管机构主体撤销申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjSvRevokeList")
    @Authorization
    public Object getSubjSvRevokeList(@RequestHeader String token,Page page,String date,String svName,String areaId,String auditState){
        PageInfo pageInfo = subjSuperviseService.getSubjSvRevokeList(token,page, date, svName,areaId,auditState);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计撤销待审核数量
    @ApiOperation(value = "统计监管机构主体撤销待审核数量")
    @SystemControllerLog(description = "统计监管机构主体撤销待审核数量",operationType = "查询")
    @RequestMapping(value = "/countRevokeToAudit")
    @Authorization
    public Object countRevokeToAudit(@RequestHeader String token,@RequestBody AsmsSubjSupervise subjSupervise){
        long count = subjSuperviseService.countRevokeToAudit(token,subjSupervise.getSvAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 新增监管机构主体
     * @param subjSupervise
     * @return
     */
    @ApiOperation(value = "监管机构主体备案")
    @SystemControllerLog(description = "新增监管机构主体",operationType = "添加")
    @RequestMapping(value = "/addSubjSupervise")
    @Authorization
    public Object addSubjSupervise(@RequestHeader String token,@RequestBody AsmsSubjSupervise subjSupervise){
        int result = subjSuperviseService.addSubjSupervise(token,subjSupervise);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else if(result==2){//表示有相同区域和机构级别的监管机构
            return setModelMap(new ModelMap(),HttpCode.CONFLICT);
        }else{
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量导入监管机构主体
     * @param request
     * @return
     */
    @ApiOperation(value = "批量导入监管机构主体")
    @SystemControllerLog(description = "批量导入监管机构主体",operationType = "添加")
    @RequestMapping(value = "/importSubjSupervise")
    @Authorization
    public Object importSubjSupervise(@RequestHeader String token,@ApiParam(value = "file", required = true) @RequestParam(value = "file") MultipartFile file,HttpServletRequest request) throws Exception{
        if(!file.isEmpty()) {
            List<String> msgList = subjSuperviseService.importSubjSupervise(file, request);
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("importMsg",msgList);
            if(msgList!=null&&msgList.size()>0) {
                return setModelMap(modelMap,HttpCode.BAD_REQUEST);
            }else{
                return setSuccessModelMap(modelMap);
            }
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增监管机构主体撤销申请
     * @param subjSvRevoke
     * @return
     */
    @ApiOperation(value = "新增监管机构主体撤销申请")
    @SystemControllerLog(description = "新增撤销监管机构主体申请",operationType = "添加")
    @RequestMapping(value = "/addSubjSvRevoke")
    @Authorization
    public Object addSubjSvRevoke(@RequestHeader String token,@RequestBody AsmsSubjSvRevoke subjSvRevoke){
        Assert.isNotBlank(subjSvRevoke.getSvId(), "svId");
        int result = subjSuperviseService.addSubjSvRevoke(token,subjSvRevoke);
        if(result==2){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND);
        }
    }

    /**
     * 新增监管机构主体变更申请
     * @param subjSvChange
     * @return
     */
    @ApiOperation(value = "新增监管机构主体变更申请")
    @SystemControllerLog(description = "新增监管机构主体变更申请",operationType = "添加")
    @RequestMapping(value = "/addSubjSvChange")
    @Authorization
    public Object addSubjSvChange(@RequestHeader String token,@RequestBody AsmsSubjSvChange subjSvChange){
        subjSuperviseService.addSubjSvChange(token,subjSvChange);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 新增监管机构主体注销申请
     * @param subjSvCancel
     * @return
     */
    @ApiOperation(value = "新增监管机构主体注销申请")
    @SystemControllerLog(description = "新增监管机构主体注销申请",operationType = "添加")
    @RequestMapping(value = "/addSubjSvCancel")
    @Authorization
    public Object addSubjSvCancel(@RequestHeader String token,@RequestBody AsmsSubjSvCancel subjSvCancel){
        int result = subjSuperviseService.addSubjSvCancel(token,subjSvCancel);
        if(result==2){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND);
        }
    }

    @ApiOperation(value = "新增监管机构主体注销申请--APP调用")
    @SystemControllerLog(description = "新增监管机构主体注销申请--APP调用",operationType = "添加")
    @RequestMapping(value = "/addSubjSvCancelForApp")
    @Authorization
    public Object addSubjSvCancelForApp(@RequestHeader String token,HttpServletRequest request,@RequestParam String applyReason){
        String attachment = "";
        String attachmentName = "";
        try {
            //将当前上下文初始化
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request)){
                //将request变成多部分request
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
                //获取multiRequest中所有的文件名
                Iterator iter = multipartRequest.getFileNames();
                String path = request.getSession().getServletContext().getRealPath("upload");
                while(iter.hasNext()){
                    //一次便利所有文件
                    MultipartFile file = multipartRequest.getFile(iter.next().toString());
                    attachmentName = file.getOriginalFilename();
                    attachment = FastDFSUtil.getUpliadFileAddress2(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int result = subjSuperviseService.addSubjSvCancelForApp(token,applyReason,attachment,attachmentName);
        if(result==2){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND);
        }
    }

    /**
     * 根据ID获取单个监管机构主体详情
     * @param subjSupervise
     * @return
     */
    @ApiOperation(value = "查看监管机构主体详情")
    @SystemControllerLog(description = "查看监管机构主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjSuperviseById")
    @Authorization
    public Object findSubjSuperviseById(@RequestHeader String token,@RequestBody AsmsSubjSupervise subjSupervise){
        Assert.isNotBlank(subjSupervise.getId(),"ID");
        subjSupervise = subjSuperviseService.findSubjSuperviseById(subjSupervise.getId());
        return setSuccessModelMap(new ModelMap(), subjSupervise);
    }

    /**
     * 根据登录用户获取登录用户对应监管机构主体详情
     * @param token
     * @return
     */
    @ApiOperation(value = "获取登录用户对应监管机构主体详情")
    @SystemControllerLog(description = "获取登录用户对应监管机构主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjSvByUser")
    @Authorization
    public Object findSubjSvByUser(@RequestHeader String token){
        CurrentUser user = WebUtil.getCurrentUser(token);
        Organization org = subjSuperviseService.findSysOrganizationByToken(token);
        AsmsSubjSupervise subjSupervise = subjSuperviseService.findSubjSuperviseById(org.getOrgId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("subjSv",subjSupervise);
        modelMap.addAttribute("subjSvChange",subjSupervise);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @ApiOperation(value = "根据token获取用户信息")
    @SystemControllerLog(description = "根据token获取用户信息",operationType = "查询")
    @RequestMapping(value = "/findCurrentUserByToken")
    @Authorization
    public Object findCurrentUserByToken(@RequestHeader String token){
        CurrentUser user = WebUtil.getCurrentUser(token);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("user",user);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据token获取用户行业角色信息")
    @SystemControllerLog(description = "根据token获取用户行业角色信息",operationType = "查询")
    @RequestMapping(value = "/getUserIndustryRoleByToken")
    @Authorization
    public Object getUserIndustryRoleByToken(@RequestHeader String token){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null||user.getId().isEmpty()){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        Set set = subjSuperviseService.getUserIndustryRoleByToken(user.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("industryRole",set);
        return setModelMap(modelMap,HttpCode.OK);
    }

    @ApiOperation(value = "根据token获取用户行业角色信息APP调用")
    @SystemControllerLog(description = "根据token获取用户行业角色信息APP调用",operationType = "查询")
    @RequestMapping(value = "/getUserIndustryByToken")
    @Authorization
    public Object getUserIndustryByToken(@RequestHeader String token){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null||user.getId().isEmpty()){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        String industry = subjSuperviseService.getUserIndustryByToken(user.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("industry",industry);
        return setModelMap(modelMap,HttpCode.OK);
    }




    /**
     * 通过ID获取单个监管机构主体变更申请
     * @param subjSvChange
     * @return
     */
    @ApiOperation(value = "通过ID获取单个监管机构主体变更申请")
    @SystemControllerLog(description = "通过ID获取单个监管机构主体变更申请",operationType = "查询")
    @RequestMapping(value = "/findSubjSvChangeById")
    @Authorization
    public Object findSubjSvChangeById(@RequestHeader String token,@RequestBody AsmsSubjSvChange subjSvChange){
        Assert.isNotBlank(subjSvChange.getId(),"ID");
        subjSvChange = subjSuperviseService.findSubjSvChangeById(subjSvChange.getId());
        JSONObject jsonObject = JSONArray.parseObject(subjSvChange.getChangeBeforeField());
        AsmsSubjSupervise subjSupervise = jsonObject.getObject("before",AsmsSubjSupervise.class);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjSvChange", subjSvChange);
        modelMap.addAttribute("subjSv", subjSupervise);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过ID获取单个监管机构主体注销申请
     * @param subjSvCancel
     * @return
     */
    @ApiOperation(value = "通过ID获取单个监管机构主体注销申请")
    @SystemControllerLog(description = "通过ID获取单个监管机构主体注销申请",operationType = "查询")
    @RequestMapping(value = "/findSubjSvCancelById")
    @Authorization
    public Object findSubjSvCancelById(@RequestHeader String token,@RequestBody AsmsSubjSvCancel subjSvCancel){
        subjSvCancel = subjSuperviseService.findSubjSvCancelById(subjSvCancel.getId());
        AsmsSubjSupervise subjSupervise = subjSuperviseService.findSubjSuperviseById(subjSvCancel.getSvId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjSvCancel",subjSvCancel);
        modelMap.addAttribute("subjSv", subjSupervise);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过ID获取单个监管机构主体撤销申请
     * @param subjSvRevoke
     * @return
     */
    @ApiOperation(value = "通过ID获取单个监管机构主体撤销申请")
    @SystemControllerLog(description = "通过ID获取单个监管机构主体撤销申请",operationType = "查询")
    @RequestMapping(value = "/findSubjSvRevokeById")
    @Authorization
    public Object findSubjSvRevokeById(@RequestHeader String token,@RequestBody AsmsSubjSvRevoke subjSvRevoke){
        subjSvRevoke = subjSuperviseService.findSubjSvRevokeById(subjSvRevoke.getId());
        AsmsSubjSupervise subjSupervise = subjSuperviseService.findSubjSuperviseById(subjSvRevoke.getSvId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjSvRevoke",subjSvRevoke);
        modelMap.addAttribute("subjSv", subjSupervise);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 审核监管机构主体变更申请
     * @param subjSvChange
     * @return
     */
    @ApiOperation(value = "审核监管机构主体变更申请")
    @SystemControllerLog(description = "审核监管机构主体变更申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjSvChange")
    @Authorization
    public Object auditSubjSvChange(@RequestHeader String token,@RequestBody AsmsSubjSvChange subjSvChange){
        subjSuperviseService.auditSubjSvChange(token,subjSvChange);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 审核监管机构主体注销申请
     * @param subjSvCancel
     * @return
     */
    @ApiOperation(value = "审核监管机构主体注销申请")
    @SystemControllerLog(description = "审核监管机构主体注销申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjSvCancel")
    @Authorization
    public Object auditSubjSvCancel(@RequestHeader String token,@RequestBody AsmsSubjSvCancel subjSvCancel){
        subjSuperviseService.auditSubjSvCancel(token,subjSvCancel);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 审核监管机构主体撤销申请
     * @param subjSvRevoke
     * @return
     */
    @ApiOperation(value = "审核监管机构主体撤销申请")
    @SystemControllerLog(description = "审核监管机构主体撤销申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjSvRevoke")
    @Authorization
    public Object auditSubjSvRevoke(@RequestHeader String token,@RequestBody AsmsSubjSvRevoke subjSvRevoke){
        subjSuperviseService.auditSubjSvRevoke(token,subjSvRevoke);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 修改密码
     * @param data
     * @return
     */
    @ApiOperation(value = "修改密码")
    @SystemControllerLog(description = "修改密码",operationType = "修改")
    @RequestMapping(value = "/changePwd")
    @Authorization
    public Object changePwd(@RequestHeader String token,@RequestBody String data){
        CurrentUser u= WebUtil.getCurrentUser(token);
        if(u==null||u.getId()==null||"".equals(u.getId())){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        JSONObject jsonObject = JSONArray.parseObject(data);
        boolean result = false;
        if(jsonObject.get("ciphertext")!=null&&StringUtils.isNotBlank(jsonObject.get("ciphertext").toString())){
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
                JSONObject json= RSAUtils.decrypt(ciphertext,RSAUtils.Module.ASMS,desKey);
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
            result = subjSuperviseService.changePwd(u.getAccount(),oldPwd,newPwd,token);
        }else{
            //密码加密
            String oldPwd=SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(jsonObject.get("oldPwd").toString()));
            if(!u.getPassword().equals(oldPwd)){
                return setModelMap(new ModelMap(),HttpCode.PASSWORD_ERROR);
            }
            result = subjSuperviseService.changePwd(u.getAccount(),jsonObject.get("oldPwd").toString(),jsonObject.get("newPwd").toString(),token);
        }
        if(result==true){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "忘记密码时根据条件获取用户")
    @SystemControllerLog(description = "忘记密码时根据条件获取用户",operationType = "查询")
    @RequestMapping(value = "/getUserByCondition")
    public Object getUserByCondition(@RequestBody UserAndSubj userAndSubj){
        List list = subjSuperviseService.getUserByCondition(userAndSubj);
        ModelMap modelMap = new ModelMap();
        if(list==null || list.isEmpty() || list.size()!=1){
            modelMap.addAttribute("message","未找到相关用户");
            return setModelMap(modelMap,HttpCode.NOT_FOUND,list);
        }else {
            //加入认证 key
            String validateCode = ValidateCodeUtil.generatorRandomString(4);
            String uuid = UUIDUtil.getUUID();
            RedisUtil.set(uuid,validateCode, 60);   //验证码保存时间
            modelMap.addAttribute("uuid",uuid);
            modelMap.addAttribute("validateCode",validateCode);
            modelMap.addAttribute("user",list.get(0));
            return setSuccessModelMap(modelMap);
        }
    }

    @ApiOperation(value = "忘记账号时获取上级监管机构联系信息")
    @SystemControllerLog(description = "忘记账号时获取上级监管机构联系信息",operationType = "查询")
    @RequestMapping(value = "/getSuperior")
    public Object getSuperior(@RequestBody UserAndSubj userAndSubj){
        List list = subjSuperviseService.getSuperiorList(userAndSubj);
        ModelMap modelMap = new ModelMap();
        if(list==null || list.isEmpty()){
            modelMap.addAttribute("message","未找到上级机构");
            return setModelMap(modelMap,HttpCode.NOT_FOUND,list);
        }else {
            modelMap.addAttribute("superior",list);
            return setSuccessModelMap(modelMap);
        }
    }

    @ApiOperation(value = "根据条件重置密码")
    @SystemControllerLog(description = "根据条件重置密码",operationType = "修改")
    @RequestMapping(value = "/resetPwd")
    public Object resetPwd(@RequestBody UserAndSubj userAndSubj,String validateCode,String uuid){
        //增加修改认证条件
        ModelMap modelMap = new ModelMap();
        String key = (String) RedisUtil.get(uuid);
        if(StringUtils.isNotBlank(key) && key.equalsIgnoreCase(validateCode)){
            boolean result = subjSuperviseService.resetPwd(userAndSubj.getAccount(),userAndSubj.getPwd());
            if(result==true){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
            }
        }else{
            //认证不通过
            modelMap.addAttribute("message","已超时");
            return setModelMap(modelMap,HttpCode.FORBIDDEN);
        }
    }

}
