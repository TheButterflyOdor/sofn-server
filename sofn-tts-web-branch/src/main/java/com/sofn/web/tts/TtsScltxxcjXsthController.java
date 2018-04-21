package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;


import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjXsth;
import com.sofn.model.generator.TtsScltxxcjXsthAndCustomer;
import com.sofn.service.tts.TtsScltxxcjXsthService;
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
*	销售退回 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "销售退回", description = "销售退回")
@RequestMapping(value = "/ttsScltxxcjXsth",method = RequestMethod.POST)
public class TtsScltxxcjXsthController extends BaseController {

	@Autowired
    public TtsScltxxcjXsthService ttsScltxxcjXsthService;
    
     /**
     * 根据条件获取销售退回列表
     * @param ttsScltxxcjXsth
     * @return
     */
    @ApiOperation(value = "获取销售退回信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取销售退回信息列表",operationType="查询")
    public Object getPageInfo(TtsScltxxcjXsth ttsScltxxcjXsth, int start, int length) {
    	try{
    		PageInfo<TtsScltxxcjXsth> pageInfo =ttsScltxxcjXsthService.getPageInfo(ttsScltxxcjXsth,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增销售退回记录
     * @param ttsScltxxcjXsth
     * @return
     */
    @ApiOperation(value = "新增销售退回数据")
    @RequestMapping(value = "/addTtsScltxxcjXsth",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增销售退回数据",operationType="新增")
    public Object addTtsScltxxcjXsth(@RequestBody TtsScltxxcjXsth  ttsScltxxcjXsth){
    	try{
    		ttsScltxxcjXsthService.add(ttsScltxxcjXsth);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据销售登记ID获取单条销售退回数据信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据销售登记ID获取单条销售退回数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据销售登记ID获取单条销售退回数据信息",operationType="查询")
    public Map<String,Object> queryById(String id){
        TtsScltxxcjXsthAndCustomer ttsScltxxcjXsthAndCustomer = ttsScltxxcjXsthService.queryByXsjlId(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("vo",ttsScltxxcjXsthAndCustomer);
        return map;
    }
    
     /**
     * 修改销售退回数据信息
     * @param ttsScltxxcjXsth
     * @return
     */
    @ApiOperation(value = "修改销售退回数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjXsth",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改销售退回数据信息",operationType="修改")
    public Object updateTtsScltxxcjXsth(@RequestBody TtsScltxxcjXsth ttsScltxxcjXsth){
      	try{
    		ttsScltxxcjXsthService.update(ttsScltxxcjXsth);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除销售退回信息
     * @param ttsScltxxcjXsth
     * @return
     */
    @ApiOperation(value = " 删除销售退回信息")
    @RequestMapping(value = "/deleteTtsScltxxcjXsth",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除销售退回信息",operationType="删除")
    public Object deleteTtsScltxxcjXsth(TtsScltxxcjXsth ttsScltxxcjXsth){
   	    try{
    		ttsScltxxcjXsthService.delete(ttsScltxxcjXsth.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据销售登记ID获取上游主体信息及退回原因
     * @param id
     * @return
     */
    @ApiOperation(value = "根据销售登记ID获取上游主体信息及退回原因")
    @RequestMapping(value = "/querySaleInfoById",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据销售登记ID获取上游主体信息及退回原因",operationType="查询")
    public Map<String,Object> querySaleInfoById(String id){
        TtsScltxxcjXsthAndCustomer ttsScltxxcjXsthAndCustomer = ttsScltxxcjXsthService.querySaleInfoById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("vo",ttsScltxxcjXsthAndCustomer);
        return map;
    }
}

