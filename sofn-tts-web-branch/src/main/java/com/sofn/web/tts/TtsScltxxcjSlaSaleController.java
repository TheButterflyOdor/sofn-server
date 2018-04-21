package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjSlaCustomer;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import com.sofn.service.tts.TtsScltxxcjSlaSaleService;
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
*	屠宰产品销售 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "屠宰产品销售", description = "屠宰产品销售")
@RequestMapping(value = "/ttsScltxxcjSlaSale",method = RequestMethod.POST)
public class TtsScltxxcjSlaSaleController extends BaseController {

	@Autowired
    public TtsScltxxcjSlaSaleService ttsScltxxcjSlaSaleService;
    
     /**
     * 根据条件获取屠宰产品销售列表
     * @param ttsScltxxcjSlaSale
     * @return
     */
    @ApiOperation(value = "获取屠宰产品销售信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(TtsScltxxcjSlaSale ttsScltxxcjSlaSale, int start, int length,String productType,String productName,String startTime,String endTime,String keyWord,String confirmState) {
    	try{
    		PageInfo<TtsScltxxcjSlaSale> pageInfo =ttsScltxxcjSlaSaleService.getPageInfo(ttsScltxxcjSlaSale,((start+1)/length)+1,length,productType,productName,startTime,endTime,keyWord,confirmState);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 销售登记-直接入市
     * @param jsonStr
     * @param jsonCustomer
     * @return
     */
    @ApiOperation(value = "销售登记-直接入市")
    @RequestMapping(value = "/addXsdjrs",method = RequestMethod.POST)
    public Object addXsdjrs(String jsonStr,String jsonCustomer){
        try{
            ttsScltxxcjSlaSaleService.addSale(jsonStr,jsonCustomer);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 销售登记-流通环节
     * @param jsonStr
     * @param customerId
     * @return
     */
    @ApiOperation(value = "销售登记-流通环节")
    @RequestMapping(value = "/addXsdjlt",method = RequestMethod.POST)
    public Object addXsdjlt(String jsonStr,String customerId){
        try{
            ttsScltxxcjSlaSaleService.addSaleLt(jsonStr,customerId);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条屠宰产品销售数据信息
     * @param ttsScltxxcjSlaSale
     * @return
     */
    @ApiOperation(value = "获取单条屠宰产品销售数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjSlaSale  ttsScltxxcjSlaSale){
        ttsScltxxcjSlaSale = ttsScltxxcjSlaSaleService.queryById(ttsScltxxcjSlaSale.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjSlaSale",ttsScltxxcjSlaSale);
        return map;
    }
    /**
     * 根据ID获取单条销售登记数据及对应的客户信息信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单条销售登记数据及对应的客户信息信息")
    @RequestMapping(value = "/getDetailsById",method = RequestMethod.POST)
    public Map<String,Object> getDetailsById(String id){
        TtsScltxxcjSlaCustomer vo  = ttsScltxxcjSlaSaleService.getSaleAndCustomerById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("vo",vo);
        return map;
    }
    
    
     /**
     * 删除屠宰产品销售信息
     * @param ttsScltxxcjSlaSale
     * @return
     */
    @ApiOperation(value = " 删除屠宰产品销售信息")
    @RequestMapping(value = "/deleteTtsScltxxcjSlaSale",method = RequestMethod.POST)
    public Object deleteTtsScltxxcjSlaSale(TtsScltxxcjSlaSale ttsScltxxcjSlaSale){
   	    try{
    		ttsScltxxcjSlaSaleService.delete(ttsScltxxcjSlaSale.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

