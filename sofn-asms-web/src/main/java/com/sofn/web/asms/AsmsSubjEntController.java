package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.AsmsSubjEntTemp;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjUserChangeRecord;
import com.sofn.model.tts.TtsScltxxcjRegiterDto;
import com.sofn.service.asms.AsmsSubjEntService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sofn
 * @version 2016年10月17日 下午 2:09
 */
@RestController
@Api(value = "主体管理-生产经营主体",description = "主体管理-生产经营主体")
@RequestMapping(value = "/subjEnt",method = RequestMethod.POST)
public class AsmsSubjEntController extends BaseController{

    @Autowired
    private AsmsSubjEntService subjEntService;

    /**
     * 通过查询条件获取生产经营主体列表
     * @param entity
     * @param page
     * @return
     */
    @ApiOperation(value = "获取生产经营主体列表")
    @SystemControllerLog(description = "获取生产经营主体列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEntList")
    @Authorization
    public Object getSubjEntList(@RequestHeader String token,TtsScltxxcjRegiter entity, Page page){
        PageInfo pageInfo = subjEntService.getSubjEntList(entity,page,token);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        //监管界面隐藏密码
        TtsScltxxcjRegiterDto ttsScltxxcjRegiter = null;
        List list1 = new ArrayList();
        for(int i=0; i < list.size();i++){
            ttsScltxxcjRegiter = (TtsScltxxcjRegiterDto)list.get(i);
            ttsScltxxcjRegiter.setUserPassword(null);
            list1.add(ttsScltxxcjRegiter);
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list1);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 通过查询条件获取生产经营主体列表--系统管理
     * @param entity
     * @param page
     * @return
     */
    @ApiOperation(value = "获取生产经营主体列表--系统管理")
    @SystemControllerLog(description = "获取生产经营主体列表--系统管理",operationType = "查询")
    @RequestMapping(value = "/getSubjEntListForSys")
    @Authorization
    public Object getSubjEntListForSys(@RequestHeader String token,TtsScltxxcjRegiter entity, Page page){
        PageInfo pageInfo = subjEntService.getSubjEntList(entity,page,token);
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
    @RequestMapping(value = "/getSubjEntTempList")
    @Authorization
    public Object getSubjEntTempList(@RequestHeader String token, AsmsSubjEntTemp subjEntTemp,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjEntService.getSubjEntTempList(subjEntTemp,page,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过查询条件获取生产经营主体备案待审核列表
     * @param register
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "获取生产经营主体备案待审核列表")
    @SystemControllerLog(description = "获取生产经营主体备案待审核列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEntRegisterList")
    @Authorization
    public Object getSubjEntRegisterList(@RequestHeader String token, TtsScltxxcjRegiter register,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjEntService.getSubjEntRegisterList(register,page,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    //备案待审核数量
    @ApiOperation(value = "统计管辖内生产经营主体备案待审核总数量")
    @SystemControllerLog(description = "统计管辖内生产经营主体备案待审核总数量",operationType = "查询")
    @RequestMapping(value = "/countRegisterToAudit")
    @Authorization
    public Object countRegisterToAudit(@RequestHeader String token,@RequestBody TtsScltxxcjRegiter regiter){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",subjEntService.countRegisterToAudit(regiter));
        return setSuccessModelMap(modelMap);
    }



    /**
     * 批量导入监管机构主体
     * @param request
     * @return
     */
    @ApiOperation(value = "批量导入生产经营主体")
    @SystemControllerLog(description = "批量导入生产经营主体",operationType = "添加")
    @RequestMapping(value = "/importSubjEnt")
    @Authorization
    public Object importSubjEnt(@RequestHeader String token, @ApiParam(value = "file", required = true) @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception{
        if(!file.isEmpty()) {
            List<String> msgList = subjEntService.importSubjEnt(file, request);
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
     * 通过查询条件获取生产经营主体备案变更申请列表
     * @param entity
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "获取生产经营主体变更申请列表")
    @SystemControllerLog(description = "获取生产经营主体变更申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEntChangeList")
    @Authorization
    public Object getSubjEntChangeList(@RequestHeader String token, TtsScltxxcjRegiter entity,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjEntService.getSubjEntChangeList(entity, page, dateBegin, dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //变更待审核数量
    @ApiOperation(value = "统计管辖内生产经营主体备案变更待审核总数量")
    @SystemControllerLog(description = "统计管辖内生产经营主体备案变更待审核总数量",operationType = "查询")
    @RequestMapping(value = "/countChangeToAudit")
    @Authorization
    public Object countChangeToAudit(@RequestHeader String token,@RequestBody TtsScltxxcjRegiter regiter){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",subjEntService.countChangeToAudit(regiter));
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过查询条件获取生产经营主体备案撤销申请列表
     * @param entity
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "获取生产经营主体撤销申请列表")
    @SystemControllerLog(description = "获取生产经营主体撤销申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEntRevokeList")
    @Authorization
    public Object getSubjEntRevokeList(@RequestHeader String token, TtsScltxxcjRegiter entity,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjEntService.getSubjEntRevokeList(entity, page, dateBegin, dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //撤销待审核数量
    @ApiOperation(value = "统计管辖内生产经营主体备案撤销待审核总数量")
    @SystemControllerLog(description = "统计管辖内生产经营主体备案撤销待审核总数量",operationType = "查询")
    @RequestMapping(value = "/countRevokeToAudit")
    @Authorization
    public Object countRevokeToAudit(@RequestHeader String token,@RequestBody TtsScltxxcjRegiter regiter){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",subjEntService.countRevokeToAudit(regiter));
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过查询条件获取生产经营主体备案注销申请列表
     * @param entity
     * @param page
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    @ApiOperation(value = "获取生产经营主体注销申请列表")
    @SystemControllerLog(description = "获取生产经营主体注销申请列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEntCancelList")
    @Authorization
    public Object getSubjEntCancelList(@RequestHeader String token, TtsScltxxcjRegiter entity,Page page,String dateBegin,String dateEnd){
        PageInfo pageInfo = subjEntService.getSubjEntCancelList(entity, page, dateBegin, dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    //注销待审核数量
    @ApiOperation(value = "统计管辖内生产经营主体备案注销待审核总数量")
    @SystemControllerLog(description = "统计管辖内生产经营主体备案注销待审核总数量",operationType = "查询")
    @RequestMapping(value = "/countCancelToAudit")
    @Authorization
    public Object countCancelToAudit(@RequestHeader String token, @RequestBody TtsScltxxcjRegiter regiter){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("count",subjEntService.countCancelToAudit(regiter));
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过ID获取生产经营主体详情
     * @param register
     * @return
     */
    @ApiOperation(value = "通过ID获取生产经营主体详情")
    @SystemControllerLog(description = "通过ID获取生产经营主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntById")
    @Authorization
    public Object findSubjEntById(@RequestHeader String token, @RequestBody TtsScltxxcjRegiter register){
        register = subjEntService.findSubjEntById(register);
        return setSuccessModelMap(new ModelMap(),register);
    }

    @ApiOperation(value = "通过主体用户码获取生产经营主体详情")
    @SystemControllerLog(description = "通过主体用户码获取生产经营主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntByEntityIdCode")
    @Authorization
    public Object findSubjEntByEntityIdCode(@RequestHeader String token, @RequestBody TtsScltxxcjRegiter register){
        register = subjEntService.findSubjEntByEntityIdCode(register);
        if(register!=null&&register.getId()!=null&&!"".equals(register.getId())){
            return setSuccessModelMap(new ModelMap(),register);
        }
        return setModelMap(new ModelMap(),HttpCode.NOT_FOUND_DATA);
    }

    @ApiOperation(value = "通过主体身份码获取生产经营主体详情")
    @SystemControllerLog(description = "通过主体身份码获取生产经营主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntByUserIdCode")
    @Authorization
    public Object findSubjEntByUserIdCode(@RequestHeader String token, @RequestBody TtsScltxxcjRegiter register){
        register = subjEntService.findSubjEntByUserIdCode(register);
        if(register!=null&&register.getId()!=null&&!"".equals(register.getId())){
            return setSuccessModelMap(new ModelMap(),register);
        }
        return setModelMap(new ModelMap(),HttpCode.NOT_FOUND_DATA);
    }

    @ApiOperation(value = "通过生产经营主体ID获取变更列表")
    @SystemControllerLog(description = "通过生产经营主体ID获取变更列表",operationType = "查询")
    @RequestMapping(value = "/getChangeListByEntityId")
    @Authorization
    public Object getChangeListByEntityId(@RequestHeader String token, String entityId,Page page){
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
    @RequestMapping(value = "/findSubjEntTempById")
    @Authorization
    public Object findSubjEntTempById(@RequestHeader String token, @RequestBody AsmsSubjEntTemp subjEntTemp){
        subjEntTemp = subjEntService.findSubjEntTempById(subjEntTemp);
        return setSuccessModelMap(new ModelMap(),subjEntTemp);
    }

    /**
     * 通过ID获取生产经营主体变更详情
     * @param subjEntChange
     * @return
     */
    @ApiOperation(value = "通过ID获取生产经营主体变更详情")
    @SystemControllerLog(description = "通过ID获取生产经营主体变更详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntChangeById")
    @Authorization
    public Object findSubjEntChangeById(@RequestHeader String token, @RequestBody TtsScltxxcjUserChangeRecord subjEntChange){
        subjEntChange = subjEntService.findSubjEntChangeById(subjEntChange.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjEntChange", subjEntChange);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 通过ID获取生产经营主体撤销详情
     * @param subjEntRevoke
     * @return
     */
    @ApiOperation(value = "通过ID获取生产经营主体撤销详情")
    @SystemControllerLog(description = "通过ID获取生产经营主体撤销详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntRevokeById")
    @Authorization
    public Object findSubjEntRevokeById(@RequestHeader String token, @RequestBody TtsScltxxcjUserChangeRecord subjEntRevoke){
        subjEntRevoke = subjEntService.findSubjEntChangeById(subjEntRevoke.getId());
        if(subjEntRevoke!=null&&!StringUtils.isNullEmpty(subjEntRevoke.getEntityIdcode())) {
            TtsScltxxcjRegiter entity = new TtsScltxxcjRegiter();
            entity = subjEntService.findSubjEntById(entity);
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("subjEnt", entity);
            modelMap.addAttribute("subjEntRevoke", subjEntRevoke);
            return setSuccessModelMap(modelMap);
        }else{
            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND);
        }
    }

    /**
     * 通过ID获取生产经营主体注销详情
     * @param subjEntCancel
     * @return
     */
    @ApiOperation(value = "通过ID获取生产经营主体注销详情")
    @SystemControllerLog(description = "通过ID获取生产经营主体注销详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntCancelById")
    @Authorization
    public Object findSubjEntCancelById(@RequestHeader String token, @RequestBody TtsScltxxcjUserChangeRecord subjEntCancel){
        subjEntCancel = subjEntService.findSubjEntChangeById(subjEntCancel.getId());
        if(subjEntCancel!=null&&!StringUtils.isNullEmpty(subjEntCancel.getEntityIdcode())) {
            TtsScltxxcjRegiter entity = new TtsScltxxcjRegiter();
            entity = subjEntService.findSubjEntById(entity);
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("subjEnt", entity);
            modelMap.addAttribute("subjEntCancel", subjEntCancel);
            return setSuccessModelMap(modelMap);
        }else{
            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND);
        }
    }

    /**
     * 新增生产经营主体撤销申请
     * @param token
     * @param subjEntRevoke
     * @return
     */
    @ApiOperation(value = "新增生产经营主体撤销申请")
    @SystemControllerLog(description = "新增生产经营主体撤销申请",operationType = "添加")
    @RequestMapping(value = "/addSubjEntRevokeApply")
    @Authorization
    public Object addSubjEntRevokeApply(@RequestHeader String token,@RequestBody TtsScltxxcjUserChangeRecord subjEntRevoke){
        int result = subjEntService.addSubjEntRevokeApply(token, subjEntRevoke);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增生产经营主体撤销申请APP调用
     * @param token
     * @param id
     * @param reason
     * @return
     */
    @ApiOperation(value = "新增生产经营主体撤销申请APP调用")
    @SystemControllerLog(description = "新增生产经营主体撤销申请APP调用",operationType = "添加")
    @RequestMapping(value = "/addSubjEntRevokeApplyForAPP")
    @Authorization
    public Object addSubjEntRevokeApplyForApp(@RequestHeader String token,@RequestParam(required = true) String id,@RequestParam(required = true) String reason){
        try {
            TtsScltxxcjRegiter  ttsScltxxcjRegiter = new TtsScltxxcjRegiter();
            ttsScltxxcjRegiter.setId(id);
            //获取主体信息
            ttsScltxxcjRegiter = subjEntService.findSubjEntById(ttsScltxxcjRegiter);
            if(ttsScltxxcjRegiter==null){
                return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
            }
            TtsScltxxcjUserChangeRecord subjEntRevoke = new TtsScltxxcjUserChangeRecord();
            //将主体信息复制到变更对象
            BeanUtils.copyProperties(subjEntRevoke,ttsScltxxcjRegiter);
            subjEntRevoke.setEntityId(id);
            subjEntRevoke.setReason(reason);
            int result = subjEntService.addSubjEntRevokeApply(token, subjEntRevoke);
            if(result==1){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception e){
            e.printStackTrace();
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *生产经营主体备案审核
     * @param register
     * @return
     */
    @ApiOperation(value = "生产经营主体备案审核")
    @SystemControllerLog(description = "生产经营主体备案审核",operationType = "修改")
    @RequestMapping(value = "/auditSubjEntRegister")
    @Authorization
    public Object auditSubjEntRegister(@RequestHeader String token,@RequestBody TtsScltxxcjRegiter register){
        try {
            subjEntService.auditSubjEntRegister(token,register);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            e.printStackTrace();
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 生产经营主体变更审核
     * @param subjEntChange
     * @return
     */
    @ApiOperation(value = "生产经营主体变更审核")
    @SystemControllerLog(description = "生产经营主体变更审核",operationType = "修改")
    @RequestMapping(value = "/auditSubjEntChange")
    @Authorization
    public Object auditSubjEntChange(@RequestHeader String token,@RequestBody TtsScltxxcjUserChangeRecord subjEntChange){
        try {
            int result = subjEntService.auditSubjEntChange(token,subjEntChange);
            if(result==1) {
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增生产经营主体临时备案
     * @param subjEntTemp
     * @return
     */
    @ApiOperation(value = "新增生产经营主体临时备案")
    @SystemControllerLog(description = "新增生产经营主体临时备案",operationType = "添加")
    @RequestMapping(value = "/addSubjEntTemp")
    @Authorization
    public Object addSubjEntTemp(@RequestHeader String token,@RequestBody AsmsSubjEntTemp subjEntTemp){
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

    @ApiOperation(value = "判断生产经营主体是否满足注销条件")
    @SystemControllerLog(description = "判断生产经营主体是否满足注销条件", operationType = "查询")
    @RequestMapping(value = "/findSubjEntStateInfoForRevokeApply")
    @Authorization
    public Object findSubjEntStateInfoForRevokeApply(ModelMap modelMap,
                                                     @RequestHeader(value="token",required = true) String token,
                                                     @RequestParam(value="subjEntId",required = true) @ApiParam(value = "生产经营主体id", required = true)String subjEntId){
        return setSuccessModelMap(modelMap, subjEntService.findSubjEntStateInfoForRevokeApply(subjEntId));
    }
}
