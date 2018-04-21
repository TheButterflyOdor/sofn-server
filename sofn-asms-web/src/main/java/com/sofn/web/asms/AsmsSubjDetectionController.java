package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.asms.AsmsSubjDetectionDto;
import com.sofn.model.asms.AsmsSubjEnforceLawDto;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjDtCancel;
import com.sofn.model.generator.AsmsSubjDtChange;
import com.sofn.model.generator.AsmsSubjDtRevoke;
import com.sofn.service.asms.AsmsSubjDetectionService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:28
 */
@RestController
@Api(value = "主体管理-检测机构",description = "主体管理-检测机构")
@RequestMapping(value = "/subjDetection",method = RequestMethod.POST)
public class AsmsSubjDetectionController extends BaseController{

    @Autowired
    private AsmsSubjDetectionService subjDetectionService;
    @Autowired
    private AsmsSubjSuperviseService subjSuperviseService;

    /**
     * 根据查询条件获取检测机构主体列表-系统管理
     * @param subjDetection
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "根据条件获取检测机构主体列表-系统管理")
    @SystemControllerLog(description = "根据条件获取检测机构主体列表--系统管理",operationType = "查询")
    @RequestMapping(value = "/getSubjDetectionList")
    public Object getSubjDetectionList(@RequestHeader String token,AsmsSubjDetection subjDetection,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjDetectionService.getSubjDetectionListForSys(token,subjDetection,page,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        List list = pageInfo.getList();
        List list1 = new ArrayList();
        //监管界面隐藏密码
        AsmsSubjDetectionDto asmsSubjDetection = null;
        for(int i=0; i < list.size();i++){
            asmsSubjDetection = (AsmsSubjDetectionDto)list.get(i);
            asmsSubjDetection.setPassword(null);
            list1.add(asmsSubjDetection);
        }
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list1);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据查询条件获取检测机构主体列表--系统管理
     * @param subjDetection
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "根据条件获取检测机构主体列表--系统管理")
    @SystemControllerLog(description = "根据条件获取检测机构主体列表--系统管理",operationType = "查询")
    @RequestMapping(value = "/getSubjDetectionListForSys")
    public Object getSubjDetectionListForSys(@RequestHeader String token,AsmsSubjDetection subjDetection,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjDetectionService.getSubjDetectionListForSys(token,subjDetection,page,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过条件获取检测机构主体变更申请
     * @param subjDtChange
     * @param page
     * @param date
     * @return
     */
    @ApiOperation(value = "获取检测机构主体变更申请列表")
    @SystemControllerLog(description = "获取检测机构主体变更申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjDtChangeList")
    public Object getSubjDtChangeList(@RequestHeader String token,AsmsSubjDtChange subjDtChange,Page page,String date){
        PageInfo pageInfo = subjDetectionService.getSubjDtChangeList(token,subjDtChange, page, date);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计变更待审核数量
    @ApiOperation(value = "统计检测机构主体变更待审核数量")
    @SystemControllerLog(description = "统计检测机构主体变更待审核数量",operationType = "查询")
    @RequestMapping(value = "/countChangeToAudit")
    public Object countChangeToAudit(@RequestHeader String token,@RequestBody AsmsSubjDetection subjDetection){
        long count = subjDetectionService.countChangeToAudit(token,subjDetection.getDtAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 获取检测机构主体注销申请列表
     * @param dtAreaId
     * @param page
     * @param dtName
     * @param date
     * @return
     */
    @ApiOperation(value = "获取检测机构主体注销申请列表")
    @SystemControllerLog(description = "获取检测机构主体注销申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjDtCancelList")
    public Object getSubjDtCancelList(@RequestHeader String token,String dtAreaId,Page page,String dtName,String date,String auditState){
        PageInfo pageInfo = subjDetectionService.getSubjDtCancelList(token,dtAreaId, page, dtName, date,auditState);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计注销待审核数量
    @ApiOperation(value = "统计检测机构主体注销待审核数量")
    @SystemControllerLog(description = "统计检测机构主体注销待审核数量",operationType = "查询")
    @RequestMapping(value = "/countCancelToAudit")
    public Object countCancelToAudit(@RequestHeader String token,@RequestBody AsmsSubjDetection subjDetection){
        long count = subjDetectionService.countCancelToAudit(token,subjDetection.getDtAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过条件获取检测机构主体撤销申请
     * @param dtAreaId
     * @param page
     * @param dtName
     * @param date
     * @return
     */
    @ApiOperation(value = "获取检测机构主体撤销申请")
    @SystemControllerLog(description = "获取检测机构主体撤销申请",operationType = "查询")
    @RequestMapping(value = "/getSubjDtRevokeList")
    public Object getSubjDtRevokeList(@RequestHeader String token, String dtAreaId,Page page,String dtName,String date,String auditState){
        PageInfo pageInfo = subjDetectionService.getSubjDtRevokeList(token,dtAreaId, page, dtName, date,auditState);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //统计撤销待审核数量
    @ApiOperation(value = "统计检测机构主体撤销待审核数量")
    @SystemControllerLog(description = "统计检测机构主体撤销待审核数量",operationType = "查询")
    @RequestMapping(value = "/countRevokeToAudit")
    public Object countRevokeToAudit(@RequestHeader String token,@RequestBody AsmsSubjDetection subjDetection){
        long count = subjDetectionService.countRevokeToAudit(token,subjDetection.getDtAreaId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",count);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 新增检测机构主体
     * @param subjDetection
     * @return
     */
    @ApiOperation(value = "新增检测机构主体备案")
    @SystemControllerLog(description = "新增检测机构主体",operationType = "添加")
    @RequestMapping(value = "/addSubjDetection")
    public Object addSubjDetection(@RequestHeader String token, @RequestBody AsmsSubjDetection subjDetection){
        int result = subjDetectionService.addSubjDetection(token,subjDetection);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else if(result==2){//表示有相同的检测机构
            return setModelMap(new ModelMap(),HttpCode.CONFLICT);
        }else {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增检测机构主体撤销申请
     * @param subjDtRevoke
     * @return
     */
    @ApiOperation(value = "新增检测机构主体撤销申请")
    @SystemControllerLog(description = "新增检测机构主体撤销申请",operationType = "添加")
    @RequestMapping(value = "/addSubjDtRevoke")
    public Object addSubjDtRevoke(@RequestHeader String token,@RequestBody AsmsSubjDtRevoke subjDtRevoke){
        int result = subjDetectionService.addSubjDtRevoke(token,subjDtRevoke);
        if(result==2){
            return setSuccessModelMap(new ModelMap());
        }else {
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量导入检测机构主体
     * @param request
     * @return
     */
    @ApiOperation(value = "批量导入检测机构主体")
    @SystemControllerLog(description = "批量导入检测机构主体",operationType = "添加")
    @RequestMapping(value = "/importSubjDetection")
    public Object importSubjDetection(@RequestHeader String token,@ApiParam(value = "file", required = true) @RequestParam(value = "file") MultipartFile file, HttpServletRequest request)throws Exception{
        if(!file.isEmpty()){
            List<String> msgList = subjDetectionService.importSubjDetection(file, request);
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("importMsg",msgList);
            if(msgList!=null&&msgList.size()>0) {
                return setModelMap(modelMap,HttpCode.BAD_REQUEST);
            }else{
                return setSuccessModelMap(modelMap);
            }
        }else {
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据ID获取单个检测机构主体详情
     * @param subjDetection
     * @return
     */
    @ApiOperation(value = "查看检测机构主体详情")
    @SystemControllerLog(description = "查看检测机构主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjDetectionById")
    public Object findSubjDetectionById(@RequestHeader String token,@RequestBody AsmsSubjDetection subjDetection){
        subjDetection = subjDetectionService.findSubjDetectionById(subjDetection);
        return setSuccessModelMap(new ModelMap(),subjDetection);
    }

    /**
     * 通过ID获取单个检测机构主体变更申请
     * @param subjDtChange
     * @return
     */
    @ApiOperation(value = "通过ID获取单个检测机构主体变更申请")
    @SystemControllerLog(description = "通过ID获取单个检测机构主体变更申请",operationType = "查询")
    @RequestMapping(value = "/findSubjDtChangeById")
    public Object findSubjDtChangeById(@RequestHeader String token,@RequestBody AsmsSubjDtChange subjDtChange){
        Map<String,Object> map = new HashMap<>();
        subjDtChange = subjDetectionService.findSubjDtChangeById(subjDtChange);
        map.put("subjDtChange",subjDtChange);
        AsmsSubjDetection subjDetection = new AsmsSubjDetection();
        subjDetection.setId(subjDtChange.getApplyDtId());
        subjDetection = subjDetectionService.findSubjDetectionById(subjDetection);
        map.put("subjDt", subjDetection);
        return setSuccessModelMap(new ModelMap(),map);
    }

    /**
     * 通过ID获取单个检测机构主体注销申请
     * @param subjDtCancel
     * @return
     */
    @ApiOperation(value = "通过ID获取单个检测机构主体注销申请")
    @SystemControllerLog(description = "通过ID获取单个检测机构主体注销申请",operationType = "查询")
    @RequestMapping(value = "/findSubjDtCancelById")
    public Object findSubjDtCancelById(@RequestHeader String token,@RequestBody AsmsSubjDtCancel subjDtCancel){
        Map<String,Object> map = new HashMap<>();
        subjDtCancel = subjDetectionService.findSubjDtCancelById(subjDtCancel);
        map.put("subjDtCancel",subjDtCancel);
        AsmsSubjDetection subjDetection = new AsmsSubjDetection();
        subjDetection.setId(subjDtCancel.getDtId());
        subjDetection = subjDetectionService.findSubjDetectionById(subjDetection);
        map.put("subjDt", subjDetection);
        return setSuccessModelMap(new ModelMap(),map);
    }

    /**
     * 通过ID获取单个检测机构主体撤销申请
     * @param subjDtRevoke
     * @return
     */
    @ApiOperation(value = "通过ID获取单个检测机构主体撤销申请")
    @SystemControllerLog(description = "通过ID获取单个检测机构主体撤销申请",operationType = "查询")
    @RequestMapping(value = "/findSubjDtRevokeById")
    public Object findSubjDtRevokeById(@RequestHeader String token,@RequestBody AsmsSubjDtRevoke subjDtRevoke){
        Map<String,Object> map = new HashMap<>();
        subjDtRevoke = subjDetectionService.findSubjDtRevokeById(subjDtRevoke);
        map.put("subjDtRevoke",subjDtRevoke);
        AsmsSubjDetection subjDetection = new AsmsSubjDetection();
        subjDetection.setId(subjDtRevoke.getDtId());
        subjDetection = subjDetectionService.findSubjDetectionById(subjDetection);
        map.put("subjDt", subjDetection);
        return setSuccessModelMap(new ModelMap(),map);
    }

    /**
     * 审核检测机构主体变更申请
     * @param subjDtChange
     * @return
     */
    @ApiOperation(value = "审核检测机构主体变更申请")
    @SystemControllerLog(description = "审核检测机构主体变更申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjDtChange")
    public Object auditSubjDtChange(@RequestHeader String token, @RequestBody AsmsSubjDtChange subjDtChange){
        subjDetectionService.auditSubjDtChange(token,subjDtChange);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 审核检测机构主体注销申请
     * @param subjDtCancel
     * @return
     */
    @ApiOperation(value = "审核检测机构主体注销申请")
    @SystemControllerLog(description = "审核检测机构主体注销申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjDtCancel")
    public Object auditSubjDtCancel(@RequestHeader String token, @RequestBody AsmsSubjDtCancel subjDtCancel){
        subjDetectionService.auditSubjDtCancel(token,subjDtCancel);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 审核检测机构主体撤销申请
     * @param subjDtRevoke
     * @return
     */
    @ApiOperation(value = "审核检测机构主体撤销申请")
    @SystemControllerLog(description = "审核检测机构主体撤销申请",operationType = "修改")
    @RequestMapping(value = "/auditSubjDtRevoke")
    public Object auditSubjDtRevoke(@RequestHeader String token, @RequestBody AsmsSubjDtRevoke subjDtRevoke){
        subjDetectionService.auditSubjDtRevoke(token,subjDtRevoke);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * 单纯上传文件--暂加
     * @param request
     * @return
     */
    @ApiOperation(value = "上传文件")
    @SystemControllerLog(description = "上传文件",operationType = "添加")
    @RequestMapping(value = "/upload")
    public Map<String,Object> upload(HttpServletRequest request){
        //返回对象
        Map<String,Object> map = new HashMap<>();
        try {
            map.putAll(subjDetectionService.upload(request));
        }catch (Exception e){
            map.put(ApiConstants.CODE,ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG,ApiMsgConstants.FAILED_MSG);
        }
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG,ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
}
