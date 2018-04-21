package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjSlaSrecord;
import com.sofn.service.tts.TtsScltxxcjSlaSrecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
*	屠宰产品销售记录 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "屠宰产品销售记录", description = "屠宰产品销售记录")
@RequestMapping(value = "/ttsScltxxcjSlaSrecord",method = RequestMethod.POST)
public class TtsScltxxcjSlaSrecordController extends BaseController {

	@Autowired
    public TtsScltxxcjSlaSrecordService ttsScltxxcjSlaSrecordService;
    
     /**
     * 根据条件获取屠宰产品销售记录列表
     * @param ttsScltxxcjSlaSrecord
     * @return
     */
    @ApiOperation(value = "获取屠宰产品销售记录信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取屠宰产品销售记录信息列表",operationType="查询")
    public Object getPageInfo(TtsScltxxcjSlaSrecord ttsScltxxcjSlaSrecord, int start, int length, String productXspc) {
    	try{
    		PageInfo<TtsScltxxcjSlaSrecord> pageInfo =ttsScltxxcjSlaSrecordService.getPageInfo(ttsScltxxcjSlaSrecord,((start+1)/length)+1,length,productXspc);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增屠宰产品销售记录记录
     * @param ttsScltxxcjSlaSrecord
     * @return
     */
    @ApiOperation(value = "新增屠宰产品销售记录数据")
    @RequestMapping(value = "/addTtsScltxxcjSlaSrecord",method = RequestMethod.POST)
    public Object addTtsScltxxcjSlaSrecord(@RequestBody TtsScltxxcjSlaSrecord  ttsScltxxcjSlaSrecord){
    	try{
    		ttsScltxxcjSlaSrecordService.add(ttsScltxxcjSlaSrecord);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条屠宰产品销售记录数据信息
     * @param ttsScltxxcjSlaSrecord
     * @return
     */
    @ApiOperation(value = "获取单条屠宰产品销售记录数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjSlaSrecord  ttsScltxxcjSlaSrecord){
        ttsScltxxcjSlaSrecord = ttsScltxxcjSlaSrecordService.queryById(ttsScltxxcjSlaSrecord.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjSlaSrecord",ttsScltxxcjSlaSrecord);
        return map;
    }

     /**
     * 修改屠宰产品销售记录数据信息
     * @param ttsScltxxcjSlaSrecord
     * @return
     */
    @ApiOperation(value = "修改屠宰产品销售记录数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjSlaSrecord",method = RequestMethod.POST)
    public Object updateTtsScltxxcjSlaSrecord(@RequestBody TtsScltxxcjSlaSrecord ttsScltxxcjSlaSrecord){
      	try{
    		ttsScltxxcjSlaSrecordService.update(ttsScltxxcjSlaSrecord);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


     /**
     * 删除屠宰产品销售记录信息
     * @param ttsScltxxcjSlaSrecord
     * @return
     */
    @ApiOperation(value = " 删除屠宰产品销售记录信息")
    @RequestMapping(value = "/deleteTtsScltxxcjSlaSrecord",method = RequestMethod.POST)
    public Object deleteTtsScltxxcjSlaSrecord(TtsScltxxcjSlaSrecord ttsScltxxcjSlaSrecord){
   	    try{
    		ttsScltxxcjSlaSrecordService.delete(ttsScltxxcjSlaSrecord.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

