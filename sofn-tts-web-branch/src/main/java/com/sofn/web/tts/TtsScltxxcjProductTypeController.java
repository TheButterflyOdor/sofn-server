package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjProductType;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.TtsScltxxcjProductTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
*	产品品种管理 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "产品品种管理", description = "产品品种管理")
@RequestMapping(value = "/ttsScltxxcjProductType",method = RequestMethod.POST)
public class TtsScltxxcjProductTypeController extends BaseController {

	@Autowired
    public TtsScltxxcjProductTypeService ttsScltxxcjProductTypeService;
    @Autowired
    public SSOLoginService ssoLoginService;
     /**
     * 根据条件获取产品品种管理列表
     * @param ttsScltxxcjProductType
     * @return
     */
    @ApiOperation(value = "获取产品品种管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取产品品种信息",operationType = "查询")
    public Object getPageInfo(TtsScltxxcjProductType ttsScltxxcjProductType, int start, int length,String id) {
    	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
    		PageInfo<TtsScltxxcjProductType> pageInfo =ttsScltxxcjProductTypeService.getPageInfo(ttsScltxxcjProductType,((start+1)/length)+1,length,user);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增产品品种管理记录
     * @param ttsScltxxcjProductType
     * @return
     */
    @ApiOperation(value = "新增产品品种管理数据")
    @RequestMapping(value = "/addTtsScltxxcjProductType",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增一条产品品种信息",operationType = "新增")
    public Object addTtsScltxxcjProductType(@RequestBody TtsScltxxcjProductType  ttsScltxxcjProductType,String id){
    	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            ttsScltxxcjProductType.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjProductType.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjProductType.setCreateTime(new Date());
    		ttsScltxxcjProductTypeService.add(ttsScltxxcjProductType);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条产品品种管理数据信息
     * @param ttsScltxxcjProductType
     * @return
     */
    @ApiOperation(value = "获取单条产品品种管理数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取一条产品品种信息",operationType = "查询")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjProductType  ttsScltxxcjProductType){
        ttsScltxxcjProductType = ttsScltxxcjProductTypeService.queryById(ttsScltxxcjProductType.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjProductType",ttsScltxxcjProductType);
        return map;
    }
    
     /**
     * 修改产品品种管理数据信息
     * @param ttsScltxxcjProductType
     * @return
     */
    @ApiOperation(value = "修改产品品种管理数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjProductType",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改一条产品品种信息",operationType = "修改")
    public Object updateTtsScltxxcjProductType(@RequestBody TtsScltxxcjProductType ttsScltxxcjProductType,String id){
      	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            ttsScltxxcjProductType.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjProductType.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjProductType.setUpdateTime(new Date());
    		ttsScltxxcjProductTypeService.update(ttsScltxxcjProductType);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除产品品种管理信息
     * @param ttsScltxxcjProductType
     * @return
     */
    @ApiOperation(value = " 删除产品品种管理信息")
    @RequestMapping(value = "/deleteTtsScltxxcjProductType",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除一条产品品种信息",operationType = "删除")
    public Object deleteTtsScltxxcjProductType(TtsScltxxcjProductType ttsScltxxcjProductType){
   	    try{
    		ttsScltxxcjProductTypeService.delete(ttsScltxxcjProductType.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "修改产品品种信息状态")
    @RequestMapping(value = "/updateProductStatus",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改一条产品品种信息状态",operationType = "修改")
    public Object updateProductStatus(TtsScltxxcjProductType ttsScltxxcjProductType){
        int result = ttsScltxxcjProductTypeService.changeStatus(ttsScltxxcjProductType);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "获取产品种类列表")
    @RequestMapping(value = "/getProTypeList",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取产品品种信息",operationType = "查询")
    public List<TtsScltxxcjProductType> getProTypeList(){
        List<TtsScltxxcjProductType> list = new ArrayList<TtsScltxxcjProductType>();
        list = ttsScltxxcjProductTypeService.getTypeList();
        return list;
    }
}

