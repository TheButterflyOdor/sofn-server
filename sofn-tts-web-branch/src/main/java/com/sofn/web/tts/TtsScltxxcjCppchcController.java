package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjCppchc;
import com.sofn.service.tts.TtsScltxxcjCppchcService;
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
*	产品批次合成 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "产品批次合成", description = "产品批次合成")
@RequestMapping(value = "/ttsScltxxcjCppchc",method = RequestMethod.POST)
public class TtsScltxxcjCppchcController extends BaseController {

	@Autowired
    public TtsScltxxcjCppchcService ttsScltxxcjCppchcService;
    
     /**
     * 根据条件获取产品批次合成列表
     * @param ttsScltxxcjCppchc
     * @return
     */
    @ApiOperation(value = "获取产品批次合成信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取产品批次合成信息列表",operationType="查询")
    public Object getPageInfo(TtsScltxxcjCppchc ttsScltxxcjCppchc, int start, int length,String hcid) {
    	try{
    		PageInfo<TtsScltxxcjCppchc> pageInfo = ttsScltxxcjCppchcService.getPageInfo(ttsScltxxcjCppchc,((start+1)/length)+1,length,hcid);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增产品批次合成记录
     * @param ttsScltxxcjCppchc
     * @return
     */
    @ApiOperation(value = "新增产品批次合成数据")
    @RequestMapping(value = "/addTtsScltxxcjCppchc",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增产品批次合成数据",operationType="新增")
    public Object addTtsScltxxcjCppchc(@RequestBody TtsScltxxcjCppchc  ttsScltxxcjCppchc){
    	try{
    		ttsScltxxcjCppchcService.add(ttsScltxxcjCppchc);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条产品批次合成数据信息
     * @param ttsScltxxcjCppchc
     * @return
     */
    @ApiOperation(value = "获取单条产品批次合成数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条产品批次合成数据信息",operationType="查询")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjCppchc  ttsScltxxcjCppchc){
        ttsScltxxcjCppchc = ttsScltxxcjCppchcService.queryById(ttsScltxxcjCppchc.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjCppchc",ttsScltxxcjCppchc);
        return map;
    }
    
     /**
     * 修改产品批次合成数据信息
     * @param ttsScltxxcjCppchc
     * @return
     */
    @ApiOperation(value = "修改产品批次合成数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjCppchc",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改产品批次合成数据信息",operationType="修改")
    public Object updateTtsScltxxcjCppchc(@RequestBody TtsScltxxcjCppchc ttsScltxxcjCppchc){
      	try{
    		ttsScltxxcjCppchcService.update(ttsScltxxcjCppchc);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除产品批次合成信息
     * @param ttsScltxxcjCppchc
     * @return
     */
    @ApiOperation(value = " 删除产品批次合成信息")
    @RequestMapping(value = "/deleteTtsScltxxcjCppchc",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除产品批次合成信息",operationType="删除")
    public Object deleteTtsScltxxcjCppchc(TtsScltxxcjCppchc ttsScltxxcjCppchc){
   	    try{
    		ttsScltxxcjCppchcService.delete(ttsScltxxcjCppchc.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

