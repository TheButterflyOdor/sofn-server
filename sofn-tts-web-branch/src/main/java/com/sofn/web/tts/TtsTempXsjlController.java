package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;


import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsTempXsjl;
import com.sofn.service.tts.TtsTempXsjlService;
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
*	销售记录临时数据表 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "销售记录临时数据表", description = "销售记录临时数据表")
@RequestMapping(value = "/ttsTempXsjl",method = RequestMethod.POST)
public class TtsTempXsjlController extends BaseController {

	@Autowired
    public TtsTempXsjlService ttsTempXsjlService;
    
     /**
     * 根据条件获取销售记录临时数据表列表
     * @param ttsTempXsjl
     * @return
     */
    @ApiOperation(value = "获取销售记录临时数据表信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取销售记录临时数据表信息列表",operationType="查询")
    public Object getPageInfo(TtsTempXsjl ttsTempXsjl, int start, int length) {
    	try{
    		PageInfo<TtsTempXsjl> pageInfo =ttsTempXsjlService.getPageInfo(ttsTempXsjl,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增销售记录临时数据表记录
     * @param ttsTempXsjl
     * @return
     */
    @ApiOperation(value = "新增销售记录临时数据表数据")
    @RequestMapping(value = "/addTtsTempXsjl",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增销售记录临时数据表数据",operationType="新增")
    public Object addTtsTempXsjl(@RequestBody TtsTempXsjl  ttsTempXsjl){
    	try{
    		ttsTempXsjlService.add(ttsTempXsjl);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条销售记录临时数据表数据信息
     * @param ttsTempXsjl
     * @return
     */
    @ApiOperation(value = "获取单条销售记录临时数据表数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条销售记录临时数据表数据信息",operationType="查询")
    public Map<String,Object> queryById(@RequestBody TtsTempXsjl  ttsTempXsjl){
        ttsTempXsjl = ttsTempXsjlService.queryById(ttsTempXsjl.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsTempXsjl",ttsTempXsjl);
        return map;
    }
    
     /**
     * 修改销售记录临时数据表数据信息
     * @param ttsTempXsjl
     * @return
     */
    @ApiOperation(value = "修改销售记录临时数据表数据信息")
    @RequestMapping(value = "/updateTtsTempXsjl",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改销售记录临时数据表数据信息",operationType="修改")
    public Object updateTtsTempXsjl(@RequestBody TtsTempXsjl ttsTempXsjl){
      	try{
    		ttsTempXsjlService.update(ttsTempXsjl);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除销售记录临时数据表信息
     * @param ttsTempXsjl
     * @return
     */
    @ApiOperation(value = " 删除销售记录临时数据表信息")
    @RequestMapping(value = "/deleteTtsTempXsjl",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除销售记录临时数据表信息",operationType="删除")
    public Object deleteTtsTempXsjl(TtsTempXsjl ttsTempXsjl){
   	    try{
    		ttsTempXsjlService.delete(ttsTempXsjl.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

