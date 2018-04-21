package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjProductType;
import com.sofn.model.generator.TtsScltxxcjSlaProduct;
import com.sofn.model.generator.TtsScltxxcjSlaRecord;
import com.sofn.model.generator.TtsScltxxcjSlaStor;
import com.sofn.model.tts.TtsSaleEntity;
import com.sofn.service.tts.TtsScltxxcjSlaProductService;
import com.sofn.service.tts.TtsScltxxcjSlaRecordService;
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
*	屠宰记录 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "屠宰记录", description = "屠宰记录")
@RequestMapping(value = "/ttsScltxxcjSlaRecord",method = RequestMethod.POST)
public class TtsScltxxcjSlaRecordController extends BaseController {

	@Autowired
    public TtsScltxxcjSlaRecordService ttsScltxxcjSlaRecordService;

    @Autowired
    public TtsScltxxcjSlaProductService ttsScltxxcjSlaProductService;
    
     /**
     * 根据条件获取屠宰记录列表
     * @param ttsScltxxcjSlaRecord
     * @return
     */
    @ApiOperation(value = "获取屠宰记录信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取屠宰记录信息列表",operationType="查询")
    public Object getPageInfo(TtsScltxxcjSlaRecord ttsScltxxcjSlaRecord, int start, int length) {
    	try{
    		PageInfo<TtsScltxxcjSlaRecord> pageInfo =ttsScltxxcjSlaRecordService.getPageInfo(ttsScltxxcjSlaRecord,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取屠宰后产品列表
     * @param productName
     * @return
     */
    @ApiOperation(value = "获取屠宰后产品列表")
    @RequestMapping(value = "/getSlaProductPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取屠宰后产品列表",operationType="查询")
    public Object getProductTypeInfo(TtsScltxxcjSlaProduct ttsScltxxcjSlaProduct, String productName, int start, int length) {
        try{
            PageInfo<TtsScltxxcjSlaProduct> pageInfo =ttsScltxxcjSlaProductService.getPageInfo(ttsScltxxcjSlaProduct,((start+1)/length)+1,length,productName);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取产品种类列表
     * @param
     * @return list
     *
     */
    @ApiOperation(value = "获取产品种类列表")
    @RequestMapping(value = "/getProList",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取产品种类列表",operationType="查询")
    public List<TtsScltxxcjSlaProduct> getProList(){
        List<TtsScltxxcjSlaProduct> list = new ArrayList<TtsScltxxcjSlaProduct>();
        list = ttsScltxxcjSlaProductService.getProductList();
        return list;
    }
	/**
     * 新增屠宰记录记录
     * @param ttsSaleEntity
     * @return
     */
    @ApiOperation(value = "新增屠宰记录数据")
    @RequestMapping(value = "/addTtsScltxxcjSlaRecord",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增屠宰记录数据",operationType="新增")
    public Object addTtsScltxxcjSlaRecord(@RequestBody TtsSaleEntity ttsSaleEntity){
    	try{
    		ttsScltxxcjSlaRecordService.addSlaRecord(ttsSaleEntity.getProductList(),ttsSaleEntity.getCustomerList(),ttsSaleEntity.getEntityId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条屠宰记录数据信息
     * @param ttsScltxxcjSlaRecord
     * @return
     */
    @ApiOperation(value = "获取单条屠宰记录数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条屠宰记录数据信息",operationType="查询")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjSlaRecord  ttsScltxxcjSlaRecord){
        ttsScltxxcjSlaRecord = ttsScltxxcjSlaRecordService.queryById(ttsScltxxcjSlaRecord.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjSlaRecord",ttsScltxxcjSlaRecord);
        return map;
    }

    @ApiOperation(value = "获取屠宰记录及收获基地和质检情况")
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取屠宰记录及收获基地和质检情况",operationType="查询")
    public Map<String,Object> findById(TtsScltxxcjSlaRecord  ttsScltxxcjSlaRecord, int start, int length){
            PageInfo<Map<String,Object>> pageInfo = ttsScltxxcjSlaRecordService.findById(ttsScltxxcjSlaRecord.getId(),((start+1)/length)+1,length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

}

