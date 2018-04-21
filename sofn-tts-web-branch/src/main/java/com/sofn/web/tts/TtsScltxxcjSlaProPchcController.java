package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjSlaProPchc;
import com.sofn.service.tts.TtsScltxxcjSlaProPchcService;
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
*	屠宰后产品批次合成 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "屠宰后产品批次合成", description = "屠宰后产品批次合成")
@RequestMapping(value = "/ttsScltxxcjSlaProPchc",method = RequestMethod.POST)
public class TtsScltxxcjSlaProPchcController extends BaseController {

	@Autowired
    public TtsScltxxcjSlaProPchcService ttsScltxxcjSlaProPchcService;
    
     /**
     * 根据条件获取屠宰后产品批次合成列表
     * @param ttsScltxxcjSlaProPchc
     * @return
     */
    @ApiOperation(value = "获取屠宰后产品批次合成信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(TtsScltxxcjSlaProPchc ttsScltxxcjSlaProPchc, int start, int length, String productType, String productName) {
    	try{
    		PageInfo<TtsScltxxcjSlaProPchc> pageInfo =ttsScltxxcjSlaProPchcService.getPageInfo(ttsScltxxcjSlaProPchc,((start+1)/length)+1,length,productType,productName);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增屠宰后产品批次合成记录
     * @param ttsScltxxcjSlaProPchc
     * @return
     */
    @ApiOperation(value = "新增屠宰后产品批次合成数据")
    @RequestMapping(value = "/addTtsScltxxcjSlaProPchc",method = RequestMethod.POST)
    public Object addTtsScltxxcjSlaProPchc(@RequestBody TtsScltxxcjSlaProPchc  ttsScltxxcjSlaProPchc){
    	try{
    		ttsScltxxcjSlaProPchcService.add(ttsScltxxcjSlaProPchc);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条屠宰后产品批次合成数据信息
     * @param ttsScltxxcjSlaProPchc
     * @return
     */
    @ApiOperation(value = "获取单条屠宰后产品批次合成数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjSlaProPchc  ttsScltxxcjSlaProPchc){
        ttsScltxxcjSlaProPchc = ttsScltxxcjSlaProPchcService.queryById(ttsScltxxcjSlaProPchc.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjSlaProPchc",ttsScltxxcjSlaProPchc);
        return map;
    }
    
     /**
     * 修改屠宰后产品批次合成数据信息
     * @param ttsScltxxcjSlaProPchc
     * @return
     */
    @ApiOperation(value = "修改屠宰后产品批次合成数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjSlaProPchc",method = RequestMethod.POST)
    public Object updateTtsScltxxcjSlaProPchc(@RequestBody TtsScltxxcjSlaProPchc ttsScltxxcjSlaProPchc){
      	try{
    		ttsScltxxcjSlaProPchcService.update(ttsScltxxcjSlaProPchc);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除屠宰后产品批次合成信息
     * @param ttsScltxxcjSlaProPchc
     * @return
     */
    @ApiOperation(value = " 删除屠宰后产品批次合成信息")
    @RequestMapping(value = "/deleteTtsScltxxcjSlaProPchc",method = RequestMethod.POST)
    public Object deleteTtsScltxxcjSlaProPchc(TtsScltxxcjSlaProPchc ttsScltxxcjSlaProPchc){
   	    try{
    		ttsScltxxcjSlaProPchcService.delete(ttsScltxxcjSlaProPchc.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

