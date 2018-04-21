package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.*;
import com.sofn.model.tts.TtsSaleEntity;
import com.sofn.service.tts.TtsScltxxcjRegiterService;
import com.sofn.service.tts.TtsScltxxcjXsdjService;
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
*	销售登记 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "销售登记", description = "销售登记")
@RequestMapping(value = "/ttsScltxxcjXsdj",method = RequestMethod.POST)
public class TtsScltxxcjXsdjController extends BaseController {

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;


	@Autowired
    public TtsScltxxcjXsdjService ttsScltxxcjXsdjService;
    
     /**
     * 根据条件获取销售登记列表
     * @param ttsScltxxcjXsdj
     * @return
     */
    @ApiOperation(value = "获取销售登记信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取销售登记信息列表",operationType="查询")
    public Object getPageInfo(TtsScltxxcjXsdj ttsScltxxcjXsdj, int start, int length,String confirmState,String entity_id,String productName,String productSort,String shrq_q,String shrq_h, String keyWord,String isCirculates) {
    	try{
    		PageInfo<TtsScltxxcjXsdj> pageInfo =ttsScltxxcjXsdjService.getPageInfo(ttsScltxxcjXsdj,((start+1)/length)+1,length,confirmState,entity_id,productName,shrq_q,shrq_h,keyWord, productSort,isCirculates);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据ID获取单条销售登记数据及对应的客户信息信息（销售历史）
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单条销售登记数据及对应的客户信息信息")
    @RequestMapping(value = "/getXsdjAndCustomerById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条销售登记数据及对应的客户信息信息",operationType="查询")
    public Map<String,Object> getXsdjAndCustomerById(String id){
        TtsScltxxcjXsdjAndCustomer vo  = ttsScltxxcjXsdjService.getXsdjAndCustomerById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("vo",vo);
        return map;
    }
    /**
     * 根据ID获取单条销售登记数据及对应的客户信息信息（采购确认）
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单条销售登记数据及对应的客户信息信息")
    @RequestMapping(value = "/getCgqrAndCustomerById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条销售登记数据及对应的客户信息信息",operationType="查询")
    public Map<String,Object> getCgqrAndCustomerById(String id,String userIdCode){
        TtsScltxxcjXsdjAndCustomer vo  = ttsScltxxcjXsdjService.getCgqrAndCustomerById(id,userIdCode);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("vo",vo);
        return map;
    }

    /**
     * 销售登记-直接入市
     * @param ttsSaleEntity
     * @return
     */
    @ApiOperation(value = "销售登记-直接入市")
    @RequestMapping(value = "/addXsdjrs",method = RequestMethod.POST)
    @SystemControllerLog(description = "销售登记-直接入市",operationType="新增")
    public Object addXsdjrs(@RequestBody TtsSaleEntity ttsSaleEntity){
        try{
            ttsScltxxcjXsdjService.xsdjrs(ttsSaleEntity.getProductList(),ttsSaleEntity.getCustomerList(),ttsSaleEntity.getEntityId(),ttsSaleEntity.getType());
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 销售登记-流通入市
     * @param ttsSaleEntity
     * @return
     */
    @ApiOperation(value = "销售登记-流通入市")
    @RequestMapping(value = "/addXsdjlt",method = RequestMethod.POST)
    @SystemControllerLog(description = "销售登记-流通入市",operationType="新增")
    public Object addXsdjlt(@RequestBody TtsSaleEntity ttsSaleEntity){
        try{
            ttsScltxxcjXsdjService.xsdjlt(ttsSaleEntity.getProductList(),ttsSaleEntity.getCustomerId(),ttsSaleEntity.getCustomerUserIdCode(),ttsSaleEntity.getEntityId(),ttsSaleEntity.getType());
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 通过主体用户码查询用户信息
     * @param userIdcode
     * @return
     */
    @ApiOperation(value = "通过主体用户码查询用户信息")
    @RequestMapping(value = "/selectByUserIdCode",method = RequestMethod.POST)
    @SystemControllerLog(description = "通过主体用户码查询用户信息",operationType="查询")
    public Object selectByUserIdCode(String userIdcode){
        try{
            TtsScltxxcjRegiterAndUser vo = ttsScltxxcjRegiterService.queryByUserIdCode(userIdcode);
            return setSuccessModelMap(new ModelMap(),vo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 根据来源标识获取来源追溯信息
     *
     * @param sourceEntity
     * @return
     */
    @ApiOperation(value = "根据来源标识获取来源追溯信息")
    @RequestMapping(value = "/querySource", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据来源标识获取来源追溯信息",operationType="查询")
    public Map<String, Object> querySource(String sourceEntity) {
        List<Map<String,Object>> vo;
        Map<String, Object> map = new HashMap<String, Object>();

        if(null != sourceEntity && "" != sourceEntity){
            vo = ttsScltxxcjXsdjService.querySource(sourceEntity);
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            map.put("data",vo);
        }else{
            map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
        }
        return map;
    }
}

