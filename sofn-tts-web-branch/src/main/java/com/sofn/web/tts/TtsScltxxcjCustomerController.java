package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjCustomer;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.TtsScltxxcjCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	客户信息管理 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "客户信息管理", description = "客户信息管理")
@RequestMapping(value = "/ttsScltxxcjCustomer",method = RequestMethod.POST)
public class TtsScltxxcjCustomerController extends BaseController {

	@Autowired
    public TtsScltxxcjCustomerService ttsScltxxcjCustomerService;
    @Autowired
    public SSOLoginService ssoLoginService;
    
     /**
     * 根据条件获取客户信息管理列表
     * @param ttsScltxxcjCustomer
     * @return
     */
    @ApiOperation(value = "获取客户信息管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取客户信息列表",operationType = "查询")
    public Object getPageInfo(TtsScltxxcjCustomer ttsScltxxcjCustomer, int start, int length,String name,String id) {
        try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            if(!StringUtils.isNullEmpty(name)){
                ttsScltxxcjCustomer.setName(name);
            }
    		PageInfo<TtsScltxxcjCustomer> pageInfo =ttsScltxxcjCustomerService.getPageInfo(ttsScltxxcjCustomer,((start+1)/length)+1,length,user);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取客户信息管理列表
     * @param ttsScltxxcjCustomer
     * @return
     */
    @ApiOperation(value = "获取客户信息管理信息列表")
    @RequestMapping(value = "/getPageInfoForEntryId",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取客户信息列表",operationType = "查询")
    public Object getPageInfoForEntryId(TtsScltxxcjCustomer ttsScltxxcjCustomer, int start, int length,String name,String id,String isrs,String userName) {
        try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            if(!StringUtils.isNullEmpty(name)){
                ttsScltxxcjCustomer.setName(name);
            }
            PageInfo<TtsScltxxcjCustomer> pageInfo =ttsScltxxcjCustomerService.getPageInfoForEntryId(ttsScltxxcjCustomer,((start+1)/length)+1,length,user,isrs,userName);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }



	/**
     * 新增客户信息管理记录
     * @param ttsScltxxcjCustomer
     * @return
     */
    @ApiOperation(value = "新增客户信息管理数据")
    @RequestMapping(value = "/addTtsScltxxcjCustomer",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取一条客户信息",operationType = "新增")
    public Object addTtsScltxxcjCustomer(@RequestBody TtsScltxxcjCustomer  ttsScltxxcjCustomer,String id){
    	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            ttsScltxxcjCustomer.setEntityIdCode(user.getEntityIdcode());
            ttsScltxxcjCustomer.setUserIdCode(user.getUserIdcode());
            ttsScltxxcjCustomer.setCreateTime(new Date());
    		ttsScltxxcjCustomerService.add(ttsScltxxcjCustomer);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条客户信息管理数据信息
     * @param ttsScltxxcjCustomer
     * @return
     */
    @ApiOperation(value = "获取单条客户信息管理数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取一条客户信息",operationType = "查询")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjCustomer  ttsScltxxcjCustomer){
        ttsScltxxcjCustomer = ttsScltxxcjCustomerService.queryById(ttsScltxxcjCustomer.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjCustomer",ttsScltxxcjCustomer);
        return map;
    }
    
     /**
     * 修改客户信息管理数据信息
     * @param ttsScltxxcjCustomer
     * @return
     */
    @ApiOperation(value = "修改客户信息管理数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjCustomer",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改一条客户信息",operationType = "修改")
    public Object updateTtsScltxxcjCustomer(@RequestBody TtsScltxxcjCustomer ttsScltxxcjCustomer,String id){
      	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            ttsScltxxcjCustomer.setEntityIdCode(user.getEntityIdcode());
            ttsScltxxcjCustomer.setUserIdCode(user.getUserIdcode());
            ttsScltxxcjCustomer.setUpdateTime(new Date());
    		ttsScltxxcjCustomerService.update(ttsScltxxcjCustomer);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除客户信息管理信息
     * @param ttsScltxxcjCustomer
     * @return
     */
    @ApiOperation(value = " 删除客户信息管理信息")
    @RequestMapping(value = "/deleteTtsScltxxcjCustomer",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除一条或多条客户信息",operationType = "删除")
    public Object deleteTtsScltxxcjCustomer(TtsScltxxcjCustomer ttsScltxxcjCustomer){
        try{
            String idsStr = ttsScltxxcjCustomer.getId();
            if (idsStr != null) {
                ttsScltxxcjCustomerService.batchUpdateDelFlag(idsStr.split(","));
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 获取客户信息
     * @param id 主体身份码
     * @return
     */
    @ApiOperation(value = "获取客户信息")
    @RequestMapping(value = "/getCustomerList",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据主体身份码获取客户信息",operationType = "查询")
    public Object getCustomerList(String id){
        List<TtsScltxxcjCustomer> list = ttsScltxxcjCustomerService.getCustomerList(id);
        try{
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取客户信息，客户身份码是空")
    @RequestMapping(value = "/getCusromerListNotCustomerEntityID",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取客户信息，客户身份码是空",operationType = "查询")
    public Object getCusromerListNotCustomerEntityID(String id){
        List<TtsScltxxcjCustomer> list = ttsScltxxcjCustomerService.getCusromerListNotCustomerEntityID(id);
        try{
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

}

