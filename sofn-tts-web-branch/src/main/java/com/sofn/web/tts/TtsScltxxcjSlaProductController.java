package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjSlaProduct;
import com.sofn.service.tts.TtsScltxxcjSlaProductService;
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
*	屠宰后产品 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "屠宰后产品", description = "屠宰后产品")
@RequestMapping(value = "/ttsScltxxcjSlaProduct",method = RequestMethod.POST)
public class TtsScltxxcjSlaProductController extends BaseController {

	@Autowired
    public TtsScltxxcjSlaProductService ttsScltxxcjSlaProductService;

	/**
     * 新增屠宰后产品记录
     * @param ttsScltxxcjSlaProduct
     * @return
     */
    @ApiOperation(value = "新增屠宰后产品数据")
    @RequestMapping(value = "/addTtsScltxxcjSlaProduct",method = RequestMethod.POST)
    public Object addTtsScltxxcjSlaProduct(@RequestBody TtsScltxxcjSlaProduct  ttsScltxxcjSlaProduct){
    	try{
    		ttsScltxxcjSlaProductService.add(ttsScltxxcjSlaProduct);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条屠宰后产品数据信息
     * @param ttsScltxxcjSlaProduct
     * @return
     */
    @ApiOperation(value = "获取单条屠宰后产品数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjSlaProduct  ttsScltxxcjSlaProduct){
        ttsScltxxcjSlaProduct = ttsScltxxcjSlaProductService.queryById(ttsScltxxcjSlaProduct.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjSlaProduct",ttsScltxxcjSlaProduct);
        return map;
    }
    
     /**
     * 修改屠宰后产品数据信息
     * @param ttsScltxxcjSlaProduct
     * @return
     */
    @ApiOperation(value = "修改屠宰后产品数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjSlaProduct",method = RequestMethod.POST)
    public Object updateTtsScltxxcjSlaProduct(@RequestBody TtsScltxxcjSlaProduct ttsScltxxcjSlaProduct){
      	try{
    		ttsScltxxcjSlaProductService.update(ttsScltxxcjSlaProduct);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除屠宰后产品信息
     * @param ttsScltxxcjSlaProduct
     * @return
     */
    @ApiOperation(value = " 删除屠宰后产品信息")
    @RequestMapping(value = "/deleteTtsScltxxcjSlaProduct",method = RequestMethod.POST)
    public Object deleteTtsScltxxcjSlaProduct(TtsScltxxcjSlaProduct ttsScltxxcjSlaProduct){
   	    try{
    		ttsScltxxcjSlaProductService.delete(ttsScltxxcjSlaProduct.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

