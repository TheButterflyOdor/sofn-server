package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;


import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjXcdjjl;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.service.tts.TtsScltxxcjXcdjjlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	销售记录 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "销售记录", description = "销售记录")
@RequestMapping(value = "/ttsScltxxcjXcdjjl",method = RequestMethod.POST)
public class TtsScltxxcjXcdjjlController extends BaseController {

	@Autowired
    public TtsScltxxcjXcdjjlService ttsScltxxcjXcdjjlService;
    
     /**
     * 根据条件获取销售记录列表
     * @param ttsScltxxcjXcdjjl
     * @return
     */
    @ApiOperation(value = "获取销售记录信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取销售记录信息列表",operationType="查询")
    public Object getPageInfo(TtsScltxxcjXcdjjl ttsScltxxcjXcdjjl, int start, int length,String productXspc) {
    	try{
    		PageInfo<TtsScltxxcjXcdjjl> pageInfo =ttsScltxxcjXcdjjlService.getPageInfo(ttsScltxxcjXcdjjl,((start+1)/length)+1,length,productXspc);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取销售记录信息列表forPrint
     * @param ttsScltxxcjXcdjjl
     * @return
     */
    @ApiOperation(value = "获取销售记录信息列表forPrint")
    @RequestMapping(value = "/getPageInfoPrint",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取销售记录信息列表forPrint",operationType="查询")
    public Object getPageInfoPrint(TtsScltxxcjXcdjjl ttsScltxxcjXcdjjl, int start, int length,String entityId,String shrq_q, String shrq_h, String keyWord, String productSort,String confirmState,String isCirculates) {
        try{
            PageInfo<TtsScltxxcjXcdjjl> pageInfo =ttsScltxxcjXcdjjlService.getPageInfoPrint(ttsScltxxcjXcdjjl,((start+1)/length)+1,length, entityId, shrq_q, shrq_h, keyWord, productSort,confirmState,isCirculates);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增销售记录记录
     * @param ttsScltxxcjXcdjjl
     * @return
     */
    @ApiOperation(value = "新增销售记录数据")
    @RequestMapping(value = "/addTtsScltxxcjXcdjjl",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增销售记录数据",operationType="新增")
    public Object addTtsScltxxcjXcdjjl(@RequestBody TtsScltxxcjXcdjjl  ttsScltxxcjXcdjjl){
    	try{
    		ttsScltxxcjXcdjjlService.add(ttsScltxxcjXcdjjl);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条销售记录数据信息
     * @param ttsScltxxcjXcdjjl
     * @return
     */
    @ApiOperation(value = "获取单条销售记录数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条销售记录数据信息",operationType="查询")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjXcdjjl  ttsScltxxcjXcdjjl){
        ttsScltxxcjXcdjjl = ttsScltxxcjXcdjjlService.queryById(ttsScltxxcjXcdjjl.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjXcdjjl",ttsScltxxcjXcdjjl);
        return map;
    }
    
     /**
     * 修改销售记录数据信息
     * @param ttsScltxxcjXcdjjl
     * @return
     */
    @ApiOperation(value = "修改销售记录数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjXcdjjl",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改销售记录数据信息",operationType="修改")
    public Object updateTtsScltxxcjXcdjjl(@RequestBody TtsScltxxcjXcdjjl ttsScltxxcjXcdjjl){
      	try{
    		ttsScltxxcjXcdjjlService.update(ttsScltxxcjXcdjjl);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除销售记录信息
     * @param ttsScltxxcjXcdjjl
     * @return
     */
    @ApiOperation(value = " 删除销售记录信息")
    @RequestMapping(value = "/deleteTtsScltxxcjXcdjjl",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除销售记录信息",operationType="删除")
    public Object deleteTtsScltxxcjXcdjjl(TtsScltxxcjXcdjjl ttsScltxxcjXcdjjl){
   	    try{
    		ttsScltxxcjXcdjjlService.delete(ttsScltxxcjXcdjjl.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取来源追溯码打印信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取来源追溯码打印信息")
    @RequestMapping(value = "/getProofByRraceProof",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取来源追溯码打印信息",operationType="查询")
    public Map<String,Object> getCgqrAndCustomerById(String id){
        List<Map<String,Object>> vo  = ttsScltxxcjXcdjjlService.getProofByRraceProof(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",vo);
        return map;
    }
    /**
     * 获取批次码打印详细信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取批次码打印详细信息")
    @RequestMapping(value = "/getPrintInfoForPc",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取批次码打印详细信息",operationType="查询")
    public Map<String,Object> getPrintInfoForPc(String id){
        List<Map<String,Object>> vo  = ttsScltxxcjXcdjjlService.getPrintInfoForPc(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",vo);
        return map;
    }

    /**
     * 获取追溯凭证打印详细信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取追溯凭证打印详细信息")
    @RequestMapping(value = "/getPrintForProof",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取追溯凭证打印详细信息",operationType="查询")
    public Map<String,Object> getPrintForProof(String id,String code){
        List<Map<String,Object>> vo  = ttsScltxxcjXcdjjlService.getPrintForProof(id,code);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",vo);
        return map;
    }
}

