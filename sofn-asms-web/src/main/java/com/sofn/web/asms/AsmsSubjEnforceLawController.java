package com.sofn.web.asms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.asms.AsmsSubjEnforceLawDto;
import com.sofn.model.asms.AsmsSubjSuperviseDto;
import com.sofn.model.generator.AsmsSubjElCancel;
import com.sofn.model.generator.AsmsSubjElChange;
import com.sofn.model.generator.AsmsSubjElRevoke;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import com.sofn.service.asms.AsmsSubjEnforceLawService;
import com.sofn.service.asms.AsmsSubjSuperviseService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:28
 */
@RestController
@Api(value = "主体管理-执法机构",description = "主体管理-执法机构")
@RequestMapping(value = "/subjEnforceLaw",method = RequestMethod.POST)
public class AsmsSubjEnforceLawController extends BaseController{

    @Autowired
    private AsmsSubjEnforceLawService subjEnforceLawService;
    @Autowired
    private AsmsSubjSuperviseService subjSuperviseService;



    /**
     * 根据查询条件获取执法机构主体列表--系统管理
     * @param subjEnforceLaw
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "根据条件获取执法机构主体列表--系统管理")
    @SystemControllerLog(description = "根据条件获取执法机构主体列表--系统管理",operationType = "查询")
    @RequestMapping(value = "/getSubjEnforceLawListForSys")
    public Object getSubjEnforceLawListForSys(@RequestHeader String token,AsmsSubjEnforceLaw subjEnforceLaw,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjEnforceLawService.getSubjEnforceLawListForSys(token,subjEnforceLaw, page, dateBegin, dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据查询条件获取执法机构主体列表
     * @param subjEnforceLaw
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "根据条件获取执法机构主体列表")
    @SystemControllerLog(description = "根据条件获取执法机构主体列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEnforceLawList")
    public Object getSubjEnforceLawList(@RequestHeader String token,AsmsSubjEnforceLaw subjEnforceLaw,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjEnforceLawService.getSubjEnforceLawListForSys(token,subjEnforceLaw, page, dateBegin, dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        List list = pageInfo.getList();
        List list1 = new ArrayList();
        //监管界面隐藏密码
        AsmsSubjEnforceLawDto asmsSubjEnforceLaw = null;
        for(int i=0; i < list.size();i++){
            asmsSubjEnforceLaw = (AsmsSubjEnforceLawDto)list.get(i);
            asmsSubjEnforceLaw.setPassword(null);
            list1.add(asmsSubjEnforceLaw);
        }
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list1);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过条件获取执法机构主体变更申请
     * @param subjElChange
     * @param page
     * @param date
     * @return
     */
    @ApiOperation(value = "获取执法机构主体变更申请")
    @SystemControllerLog(description = "获取执法机构主体变更申请",operationType = "查询")
    @RequestMapping(value = "/getSubjElChangeList")
    public Object getSubjElChangeList(@RequestHeader String token,AsmsSubjElChange subjElChange, Page page, String date){
        PageInfo pageInfo = subjEnforceLawService.getSubjElChangeList(token,subjElChange, page,date);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计变更待审核数量
    @ApiOperation(value = "统计执法机构主体变更待审核数量")
    @SystemControllerLog(description = "统计执法机构主体变更待审核数量",operationType = "查询")
    @RequestMapping(value = "/countChangeToAudit")
    public Object countChangeToAudit(@RequestHeader String token,@RequestBody AsmsSubjEnforceLaw subjEnforceLaw){
        long count = subjEnforceLawService.countChangeToAudit(token,subjEnforceLaw.getElAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 获取执法机构主体注销申请列表
     * @param elAreaId
     * @param page
     * @param elName
     * @param date
     * @return
     */
    @ApiOperation(value = "获取执法机构主体注销申请列表")
    @SystemControllerLog(description = "获取执法机构主体注销申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjElCancelList")
    public Object getSubjElCancelList(@RequestHeader String token,String elAreaId, Page page, String elName, String date,String auditState){
        PageInfo pageInfo = subjEnforceLawService.getSubjElCancelList(token,elAreaId, page,elName,date,auditState);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计注销待审核数量
    @ApiOperation(value = "统计执法机构主体注销待审核数量")
    @SystemControllerLog(description = "统计执法机构主体注销待审核数量",operationType = "查询")
    @RequestMapping(value = "/countCancelToAudit")
    public Object countCancelToAudit(@RequestHeader String token,@RequestBody AsmsSubjEnforceLaw subjEnforceLaw){
        long count = subjEnforceLawService.countCancelToAudit(token,subjEnforceLaw.getElAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过条件获取执法机构主体撤销申请
     * @param elAreaId
     * @param page
     * @param elName
     * @param date
     * @return
     */
    @ApiOperation(value = "获取执法机构主体撤销申请列表")
    @SystemControllerLog(description = "获取执法机构主体撤销申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjElRevokeList")
    public Object getSubjElRevokeList(@RequestHeader String token,String elAreaId,Page page,String elName,String date,String auditState){
        PageInfo pageInfo = subjEnforceLawService.getSubjElRevokeList(token,elAreaId,page,elName,date,auditState);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计撤销待审核数量
    @ApiOperation(value = "统计执法机构主体撤销待审核数量")
    @SystemControllerLog(description = "统计执法机构主体撤销待审核数量",operationType = "查询")
    @RequestMapping(value = "/countRevokeToAudit")
    public Object countRevokeToAudit(@RequestHeader String token,@RequestBody AsmsSubjEnforceLaw subjEnforceLaw){
        long count = subjEnforceLawService.countRevokeToAudit(token,subjEnforceLaw.getElAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 新增执法机构主体
     * @param subjEnforceLaw
     * @return
     */
    @ApiOperation(value = "执法机构主体备案")
    @SystemControllerLog(description = "新增执法机构主体",operationType = "添加")
    @RequestMapping(value = "/addSubjEnforceLaw")
    public Object addSubjEnforceLaw(@RequestHeader String token, @RequestBody AsmsSubjEnforceLaw subjEnforceLaw){
        int result = subjEnforceLawService.addSubjEnforceLaw(token,subjEnforceLaw);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else if(result==2){//表示有相同的执法机构
            return setModelMap(new ModelMap(),HttpCode.CONFLICT);
        }else {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量导入执法机构主体
     * @param request
     * @return
     */
    @ApiOperation(value = "批量导入执法机构主体")
    @SystemControllerLog(description = "批量导入执法机构主体",operationType = "添加")
    @RequestMapping(value = "/importSubjEnforceLaw")
    public Object importSubjEnforceLaw(@RequestHeader String token,@ApiParam(value = "file", required = true) @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception{
        if(!file.isEmpty()){//file不为空
            List<String> msgList = subjEnforceLawService.importSubjEnforceLaw(file, request);
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("importMsg",msgList);
            if(msgList!=null&&msgList.size()>0) {//file文件中有不符合规定数据项
                return setModelMap(modelMap,HttpCode.BAD_REQUEST);
            }else{//正常
                return setSuccessModelMap(modelMap);
            }
        }else {//file为空
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增执法机构主体撤销申请
     * @param subjElRevoke
     * @return
     */
    @ApiOperation(value = "新增执法机构主体撤销申请")
    @SystemControllerLog(description = "新增执法机构主体撤销申请",operationType = "添加")
    @RequestMapping(value = "/addSubjElRevoke")
    public Object addSubjElRevoke(@RequestHeader String token,@RequestBody AsmsSubjElRevoke subjElRevoke){
        int result = subjEnforceLawService.addSubjElRevoke(token,subjElRevoke);
        if(result==2){
            return setSuccessModelMap(new ModelMap());
        }else {
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据ID获取单个执法机构主体详情
     * @param subjEnforceLaw
     * @return
     */
    @ApiOperation(value = "查看执法机构主体详情")
    @SystemControllerLog(description = "查看执法机构主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEnforceLawById")
    public Object findSubjEnforceLawById(@RequestHeader String token,@RequestBody AsmsSubjEnforceLaw subjEnforceLaw){
        subjEnforceLaw = subjEnforceLawService.findSubjEnforceLawById(subjEnforceLaw);
        return setSuccessModelMap(new ModelMap(),subjEnforceLaw);
    }

    /**
     * 通过ID获取单个执法机构主体变更申请
     * @param subjElChange
     * @return
     */
    @ApiOperation(value = "通过ID获取单个执法机构主体变更申请")
    @SystemControllerLog(description = "通过ID获取单个执法机构主体变更申请",operationType = "查询")
    @RequestMapping(value = "/findSubjElChangeById")
    public Object findSubjElChangeById(@RequestHeader String token,@RequestBody AsmsSubjElChange subjElChange){
        subjElChange = subjEnforceLawService.findSubjElChangeById(subjElChange);
        JSONObject jsonObject = JSONArray.parseObject(subjElChange.getChangeBeforeField());
        AsmsSubjEnforceLaw subjEnforceLaw = jsonObject.getObject("before",AsmsSubjEnforceLaw.class);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjElChange",subjElChange);
        modelMap.addAttribute("subjEl",subjEnforceLaw);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过ID获取单个执法机构主体注销申请
     * @param subjElCancel
     * @return
     */
    @ApiOperation(value = "通过ID获取单个执法机构主体注销申请")
    @SystemControllerLog(description = "通过ID获取单个执法机构主体注销申请",operationType = "查询")
    @RequestMapping(value = "/findSubjElCancelById")
    public Object findSubjElCancelById(@RequestHeader String token,@RequestBody AsmsSubjElCancel subjElCancel){
        subjElCancel = subjEnforceLawService.findSubjElCancelById(subjElCancel);
        AsmsSubjEnforceLaw subjEnforceLaw = new AsmsSubjEnforceLaw();
        subjEnforceLaw.setId(subjElCancel.getElId());
        subjEnforceLaw = subjEnforceLawService.findSubjEnforceLawById(subjEnforceLaw);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjElCancel",subjElCancel);
        modelMap.addAttribute("subjEl", subjEnforceLaw);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过ID获取单个执法机构主体撤销申请
     * @param subjElRevoke
     * @return
     */
    @ApiOperation(value = "通过ID获取单个执法机构主体撤销申请")
    @SystemControllerLog(description = "通过ID获取单个执法机构主体撤销申请",operationType = "查询")
    @RequestMapping(value = "/findSubjElRevokeById")
    public Object findSubjElRevokeById(@RequestHeader String token,@RequestBody AsmsSubjElRevoke subjElRevoke){
        subjElRevoke = subjEnforceLawService.findSubjElRevokeById(subjElRevoke);
        AsmsSubjEnforceLaw subjEnforceLaw = new AsmsSubjEnforceLaw();
        subjEnforceLaw.setId(subjElRevoke.getElId());
        subjEnforceLaw = subjEnforceLawService.findSubjEnforceLawById(subjEnforceLaw);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjElRevoke",subjElRevoke);
        modelMap.addAttribute("subjEl", subjEnforceLaw);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 审核执法机构主体变更申请
     * @param subjElChange
     * @return
     */
    @ApiOperation(value = "审核执法机构主体变更申请")
    @SystemControllerLog(description = "审核执法机构主体变更申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjElChange")
    public Object auditSubjElChange(@RequestHeader String token,@RequestBody AsmsSubjElChange subjElChange){
        subjEnforceLawService.auditSubjElChange(token,subjElChange);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 审核执法机构主体注销申请
     * @param subjElCancel
     * @return
     */
    @ApiOperation(value = "审核执法机构主体注销申请")
    @SystemControllerLog(description = "审核执法机构主体注销申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjElCancel")
    public Object auditSubjElCancel(@RequestHeader String token,@RequestBody AsmsSubjElCancel subjElCancel){
        subjEnforceLawService.auditSubjElCancel(token,subjElCancel);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 审核执法机构主体撤销申请
     * @param subjElRevoke
     * @return
     */
    @ApiOperation(value = "审核执法机构主体撤销申请")
    @SystemControllerLog(description = "审核执法机构主体撤销申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjElRevoke")
    public Object auditSubjElRevoke(@RequestHeader String token,@RequestBody AsmsSubjElRevoke subjElRevoke){
        subjEnforceLawService.auditSubjElRevoke(token,subjElRevoke);
        return setSuccessModelMap(new ModelMap());
    }
}
