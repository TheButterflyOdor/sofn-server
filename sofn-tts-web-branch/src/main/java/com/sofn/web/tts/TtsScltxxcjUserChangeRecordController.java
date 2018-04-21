package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjUserChangeRecord;
import com.sofn.service.tts.TtsScltxxcjRegiterService;
import com.sofn.service.tts.TtsScltxxcjUserChangeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	注册主体信息表变更记录表 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "注册主体信息表变更记录表", description = "注册主体信息表变更记录表")
@RequestMapping(value = "/ttsScltxxcjUserChangeRecord",method = RequestMethod.POST)
public class TtsScltxxcjUserChangeRecordController extends BaseController {

	@Autowired
    public TtsScltxxcjUserChangeRecordService ttsScltxxcjUserChangeRecordService;

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;
     /**
     * 根据条件获取注册主体信息表变更记录表列表
     * @param ttsScltxxcjUserChangeRecord
     * @return
     */
    @ApiOperation(value = "获取注册主体信息表变更记录表信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取注册主体信息表变更记录表信息列表",operationType="查询主体变更记录")
    public Object getPageInfo(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord, int start, int length,String entityId) {
    	try{
            ttsScltxxcjUserChangeRecord.setEntityId(entityId);
    		PageInfo<TtsScltxxcjUserChangeRecord> pageInfo =ttsScltxxcjUserChangeRecordService.getPageInfo(ttsScltxxcjUserChangeRecord,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增注册主体信息表变更记录表记录
     * @param ttsScltxxcjUserChangeRecord
     * @return
     */
    @ApiOperation(value = "新增注册主体信息表变更记录表数据")
    @RequestMapping(value = "/addTtsScltxxcjUserChangeRecord",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增注册主体信息表变更记录表数据",operationType="新增变更记录")
    public Object addTtsScltxxcjUserChangeRecord(@RequestBody TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord){
        List<String> resutls = new ArrayList<String>();
        try{
            Map<String, Object> params = new HashMap<String, Object>();
            if (ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("企业/个体工商户")
                    || ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("合作社")){
                if(!ttsScltxxcjUserChangeRecord.getInfoUnique().equals(ttsScltxxcjUserChangeRecord.getCreditCode())){
                    params.put("uniqueKey",ttsScltxxcjUserChangeRecord.getCreditCode());
                    params.put("account",ttsScltxxcjUserChangeRecord.getAccount());
                    if(ttsScltxxcjUserChangeRecord.getEntityScale() == null
                            || ttsScltxxcjUserChangeRecord.getEntityScale().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityScaleName() == null
                            || ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("")
                            || ttsScltxxcjUserChangeRecord.getAccount() == null || ttsScltxxcjUserChangeRecord.getAccount().equals("")
                            || ttsScltxxcjUserChangeRecord.getEnterpriseName() == null
                            || ttsScltxxcjUserChangeRecord.getEnterpriseName().equals("")
                            || ttsScltxxcjUserChangeRecord.getCardType() == null || ttsScltxxcjUserChangeRecord.getCardType().equals("")
                            || ttsScltxxcjUserChangeRecord.getCreditCode() == null || ttsScltxxcjUserChangeRecord.getCreditCode().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityType() == null || ttsScltxxcjUserChangeRecord.getEntityType().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityTypeName() == null
                            || ttsScltxxcjUserChangeRecord.getEntityTypeName().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityProperty() == null
                            || ttsScltxxcjUserChangeRecord.getEntityProperty().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityPropertyName() == null
                            || ttsScltxxcjUserChangeRecord.getEntityPropertyName().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityIndustry() == null
                            || ttsScltxxcjUserChangeRecord.getEntityIndustry().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityIndustryName() == null
                            || ttsScltxxcjUserChangeRecord.getEntityIndustryName().equals("")
                            || ttsScltxxcjUserChangeRecord.getAddress() == null || ttsScltxxcjUserChangeRecord.getAddress().equals("")
                            || ttsScltxxcjUserChangeRecord.getArea() == null || ttsScltxxcjUserChangeRecord.getArea().equals("")
                            || ttsScltxxcjUserChangeRecord.getLongitude() == null || ttsScltxxcjUserChangeRecord.getLongitude().equals("")
                            || ttsScltxxcjUserChangeRecord.getLatitude() == null || ttsScltxxcjUserChangeRecord.getLatitude().equals("")
                            || ttsScltxxcjUserChangeRecord.getLegalName() == null || ttsScltxxcjUserChangeRecord.getLegalName().equals("")
                            || ttsScltxxcjUserChangeRecord.getLegalIdnumber() == null
                            || ttsScltxxcjUserChangeRecord.getLegalIdnumber().equals("")
                            || ttsScltxxcjUserChangeRecord.getLegalPhone() == null || ttsScltxxcjUserChangeRecord.getLegalPhone().equals("")
                            || ttsScltxxcjUserChangeRecord.getLegalImages() == null || ttsScltxxcjUserChangeRecord.getLegalImages().equals("")
                            || ttsScltxxcjUserChangeRecord.getContactName() == null || ttsScltxxcjUserChangeRecord.getContactName().equals("")
                            || ttsScltxxcjUserChangeRecord.getContactPhone() == null || ttsScltxxcjUserChangeRecord.getContactPhone().equals("")
                            || ttsScltxxcjUserChangeRecord.getContactEmail() == null || ttsScltxxcjUserChangeRecord.getContactEmail().equals("")
                            || ttsScltxxcjUserChangeRecord.getIdcode() == null || ttsScltxxcjUserChangeRecord.getIdcode().equals("")
                            || ttsScltxxcjUserChangeRecord.getBusinessLicenceimg() == null
                            || ttsScltxxcjUserChangeRecord.getBusinessLicenceimg().equals("")
                            || ttsScltxxcjUserChangeRecord.getPositiveIdcardeimg() == null
                            || ttsScltxxcjUserChangeRecord.getPositiveIdcardeimg().equals("")
                            || ttsScltxxcjUserChangeRecord.getNegativeIdcardimg() == null
                            || ttsScltxxcjUserChangeRecord.getNegativeIdcardimg().equals("")){
                        return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                    }
                    if(ttsScltxxcjUserChangeRecord.getCardType().equals("普通营业执照（有独立组织机构代码证）")){
                        if(ttsScltxxcjUserChangeRecord.getOrganizationCode() == null
                                || ttsScltxxcjUserChangeRecord.getOrganizationCode().equals("")
                                || ttsScltxxcjUserChangeRecord.getOrganizationCertificateimg() == null
                                || ttsScltxxcjUserChangeRecord.getOrganizationCertificateimg().equals("")){
                            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                        }
                    }
                    if((ttsScltxxcjUserChangeRecord.getBusinessOperationStart() == null
                            || ttsScltxxcjUserChangeRecord.getBusinessOperationStart().equals(""))
                            && (ttsScltxxcjUserChangeRecord.getIsLong().equals("false")
                            || ttsScltxxcjUserChangeRecord.getIsLong() == null
                            || ttsScltxxcjUserChangeRecord.getIsLong().equals(""))){
                        return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                    }
                    resutls = ttsScltxxcjRegiterService.queryByInfounique(params);
                    if (resutls.size() > 0) {
                        return setModelMap(new ModelMap(), HttpCode.REPEAT_DATA);
                    }
                }
            }
            if(ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("个人")
                    || ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("家庭农场")){
                if(!ttsScltxxcjUserChangeRecord.getInfoUnique().equals(ttsScltxxcjUserChangeRecord.getLegalIdnumber())){
                    params.put("uniqueKey",ttsScltxxcjUserChangeRecord.getLegalIdnumber());
                    params.put("account",ttsScltxxcjUserChangeRecord.getAccount());
                    if(ttsScltxxcjUserChangeRecord.getEntityScale() == null
                            || ttsScltxxcjUserChangeRecord.getEntityScale().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityScaleName() == null
                            || ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("")
                            || ttsScltxxcjUserChangeRecord.getAccount() == null || ttsScltxxcjUserChangeRecord.getAccount().equals("")
                            || ttsScltxxcjUserChangeRecord.getEnterpriseName() == null
                            || ttsScltxxcjUserChangeRecord.getEnterpriseName().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityType() == null || ttsScltxxcjUserChangeRecord.getEntityType().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityTypeName() == null
                            || ttsScltxxcjUserChangeRecord.getEntityTypeName().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityProperty() == null
                            || ttsScltxxcjUserChangeRecord.getEntityProperty().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityPropertyName() == null
                            || ttsScltxxcjUserChangeRecord.getEntityPropertyName().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityIndustry() == null
                            || ttsScltxxcjUserChangeRecord.getEntityIndustry().equals("")
                            || ttsScltxxcjUserChangeRecord.getEntityIndustryName() == null
                            || ttsScltxxcjUserChangeRecord.getEntityIndustryName().equals("")
                            || ttsScltxxcjUserChangeRecord.getAddress() == null || ttsScltxxcjUserChangeRecord.getAddress().equals("")
                            || ttsScltxxcjUserChangeRecord.getArea() == null || ttsScltxxcjUserChangeRecord.getArea().equals("")
                            || ttsScltxxcjUserChangeRecord.getLongitude() == null || ttsScltxxcjUserChangeRecord.getLongitude().equals("")
                            || ttsScltxxcjUserChangeRecord.getLatitude() == null || ttsScltxxcjUserChangeRecord.getLatitude().equals("")
                            || ttsScltxxcjUserChangeRecord.getLegalName() == null || ttsScltxxcjUserChangeRecord.getLegalName().equals("")
                            || ttsScltxxcjUserChangeRecord.getLegalIdnumber() == null
                            || ttsScltxxcjUserChangeRecord.getLegalIdnumber().equals("")
                            || ttsScltxxcjUserChangeRecord.getLegalPhone() == null || ttsScltxxcjUserChangeRecord.getLegalPhone().equals("")
                            || ttsScltxxcjUserChangeRecord.getLegalImages() == null || ttsScltxxcjUserChangeRecord.getLegalImages().equals("")
                            || ttsScltxxcjUserChangeRecord.getContactEmail() == null || ttsScltxxcjUserChangeRecord.getContactEmail().equals("")
                            || ttsScltxxcjUserChangeRecord.getPositiveIdcardeimg() == null
                            || ttsScltxxcjUserChangeRecord.getPositiveIdcardeimg().equals("")
                            || ttsScltxxcjUserChangeRecord.getNegativeIdcardimg() == null
                            || ttsScltxxcjUserChangeRecord.getNegativeIdcardimg().equals("")){
                        return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                    }
                    resutls = ttsScltxxcjRegiterService.queryByInfounique(params);
                    if (resutls.size() > 0) {
                        return setModelMap(new ModelMap(), HttpCode.REPEAT_DATA);
                    }
                }
            }
            boolean isOk = ttsScltxxcjUserChangeRecordService.updateEntityInfoBy(ttsScltxxcjUserChangeRecord);
            if (isOk){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条注册主体信息表变更记录表数据信息
     * @param ttsScltxxcjUserChangeRecord
     * @return
     */
    @ApiOperation(value = "获取单条注册主体信息表变更记录表数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条注册主体信息表变更记录表数据信息",operationType="查询单条变更记录")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjUserChangeRecord  ttsScltxxcjUserChangeRecord){
        ttsScltxxcjUserChangeRecord = ttsScltxxcjUserChangeRecordService.queryById(ttsScltxxcjUserChangeRecord.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjUserChangeRecord",ttsScltxxcjUserChangeRecord);
        return map;
    }
    
     /**
     * 修改注册主体信息表变更记录表数据信息
     * @param ttsScltxxcjUserChangeRecord
     * @return
     */
    @ApiOperation(value = "修改注册主体信息表变更记录表数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjUserChangeRecord",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改注册主体信息表变更记录表数据信息",operationType="修改变更记录")
    public Object updateTtsScltxxcjUserChangeRecord(@RequestBody TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord){
      	try{
    		ttsScltxxcjUserChangeRecordService.update(ttsScltxxcjUserChangeRecord);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除注册主体信息表变更记录表信息
     * @param ttsScltxxcjUserChangeRecord
     * @return
     */
    @ApiOperation(value = " 删除注册主体信息表变更记录表信息")
    @RequestMapping(value = "/deleteTtsScltxxcjUserChangeRecord",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除注册主体信息表变更记录表信息",operationType="删除变更记录")
    public Object deleteTtsScltxxcjUserChangeRecord(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord){
   	    try{
    		ttsScltxxcjUserChangeRecordService.delete(ttsScltxxcjUserChangeRecord.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

