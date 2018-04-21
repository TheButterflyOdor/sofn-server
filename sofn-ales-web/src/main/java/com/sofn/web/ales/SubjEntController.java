package com.sofn.web.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AsmsSubjEntTemp;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.service.ales.SubjEntService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sofn
 * @version 2016年10月17日 下午 2:09
 */
@RestController
@Api(value = "生产经营主体",description = "生产经营主体")
@RequestMapping(value = "/subjEnt",method = RequestMethod.POST)
public class SubjEntController extends BaseController{

    @Autowired
    private SubjEntService subjEntService;

    /**
     * 通过查询条件获取生产经营主体列表
     * @param entity
     * @param page
     * @return
     */
    @ApiOperation(value = "获取生产经营主体列表")
    @SystemControllerLog(description = "获取生产经营主体列表",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getSubjEntList")
    public Object getSubjEntList(TtsScltxxcjRegiter entity, Page page ,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = subjEntService.getSubjEntList(entity,page);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过查询条件获取临时生产经营主体列表
     * @param subjEntTemp
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "获取临时生产经营主体列表")
    @SystemControllerLog(description = "获取临时生产经营主体列表",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getSubjEntTempList")
    public Object getSubjEntTempList(AsmsSubjEntTemp subjEntTemp,Page page,String dateBegin,String dateEnd,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = subjEntService.getSubjEntTempList(subjEntTemp,page,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
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
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        register = subjEntService.findSubjEntById(register);
        return setSuccessModelMap(new ModelMap(),register);
    }

    @ApiOperation(value = "通过主体身份码获取生产经营主体详情")
    @SystemControllerLog(description = "通过主体身份码获取生产经营主体详情",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findSubjEntByEntityIdCode")
    public Object findSubjEntByEntityIdCode(@RequestBody TtsScltxxcjRegiter register,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        register = subjEntService.findSubjEntByEntityIdCode(register);
        if(register!=null&&register.getId()!=null&&!"".equals(register.getId())){
            return setSuccessModelMap(new ModelMap(),register);
        }
        return setModelMap(new ModelMap(),HttpCode.NOT_FOUND_DATA);
    }

    @ApiOperation(value = "通过生产经营主体ID获取变更列表")
    @SystemControllerLog(description = "通过生产经营主体ID获取变更列表",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getChangeListByEntityId")
    public Object getChangeListByEntityId(String entityId,Page page,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = subjEntService.getChangeListByEntityId(entityId,page.getStart(),page.getLength());
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过ID获取临时生产经营主体详情
     * @param subjEntTemp
     * @return
     */
    @ApiOperation(value = "通过ID获取临时生产经营主体详情")
    @SystemControllerLog(description = "通过ID获取临时生产经营主体详情",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/findSubjEntTempById")
    public Object findSubjEntTempById(@RequestBody AsmsSubjEntTemp subjEntTemp,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        subjEntTemp = subjEntService.findSubjEntTempById(subjEntTemp);
        return setSuccessModelMap(new ModelMap(),subjEntTemp);
    }

    /**
     * 新增生产经营主体临时备案
     * @param subjEntTemp
     * @return
     */
    @ApiOperation(value = "新增生产经营主体临时备案")
    @SystemControllerLog(description = "新增生产经营主体临时备案",operationType = "添加")
    @Authorization
    @RequestMapping(value = "/addSubjEntTemp")
    public Object addSubjEntTemp(@RequestHeader String token,@RequestBody AsmsSubjEntTemp subjEntTemp){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        //判断证件号码是否重复
        List<AsmsSubjEntTemp> list = subjEntService.getSubjEntTempListByCode(subjEntTemp.getOrgCode());
        if(list!=null&&!list.isEmpty()){
            return setModelMap(new ModelMap(),HttpCode.REPEAT_DATA);
        }
        subjEntTemp = subjEntService.addSubjEntTemp(token,subjEntTemp);
        if(subjEntTemp!=null) {
            return setSuccessModelMap(new ModelMap(), subjEntTemp);
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 临时登记单位主体信息查询
     * @param subj
     * @return
     */
    @ApiOperation(value = "临时登记单位主体信息")
    @SystemControllerLog(description = "临时登记单位主体信息",operationType = "查询")
    @Authorization
    @RequestMapping(value = "/getSubjEntTempInfo")
    public Object getSubjEntTempInfo(String subj,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        AsmsSubjEntTemp  asmsSubjEntTemp = subjEntService.getSubjEntTempListByCode(subj).get(0);
        return setSuccessModelMap(new ModelMap(),asmsSubjEntTemp);
    }
}
