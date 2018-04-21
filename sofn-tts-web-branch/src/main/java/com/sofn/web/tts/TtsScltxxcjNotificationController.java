package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjNotification;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.TtsScltxxcjNotificationService;
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
import java.util.Map;

/**
*	投诉建议信息管理 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "通知消息", description = "通知消息")
@RequestMapping(value = "/ttsScltxxcjNotification",method = RequestMethod.POST)
public class TtsScltxxcjNotificationController extends BaseController {

	@Autowired
    public TtsScltxxcjNotificationService ttsScltxxcjNotificationService;
    @Autowired
    public SSOLoginService ssoLoginService;
    
     /**
     * 根据条件获取投诉建议信息管理列表
     * @param ttsScltxxcjNotification
     * @return
     */
    @ApiOperation(value = "获取通知消息列表")
    @SystemControllerLog(description = "通知消息",operationType = "分页查询")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(TtsScltxxcjNotification ttsScltxxcjNotification, int start, int length,String createTimeStart,String createTimeEnd,String keyWord,String id) {
    	try{
            if(!StringUtils.isNullEmpty(keyWord)){
                ttsScltxxcjNotification.setTitle(keyWord);
            }
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
    		PageInfo<TtsScltxxcjNotification> pageInfo =ttsScltxxcjNotificationService.getPageInfo(ttsScltxxcjNotification,((start+1)/length)+1,length,createTimeStart,createTimeEnd,user);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增投诉建议信息管理记录
     * @param ttsScltxxcjNotification
     * @return
     */
    @ApiOperation(value = "新增通知消息管理数据")
    @SystemControllerLog(description = "通知消息",operationType = "新增")
    @RequestMapping(value = "/addTtsScltxxcjNotification",method = RequestMethod.POST)
    public Object addTtsScltxxcjNotification(@RequestBody TtsScltxxcjNotification  ttsScltxxcjNotification,String id){
    	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            ttsScltxxcjNotification.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjNotification.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjNotification.setCreateTime(new Date());
    		ttsScltxxcjNotificationService.add(ttsScltxxcjNotification);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条投诉建议信息管理数据信息
     * @param ttsScltxxcjNotification
     * @return
     */
    @ApiOperation(value = "获取单条通知消息信息")
    @SystemControllerLog(description = "通知消息",operationType = "查询")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjNotification  ttsScltxxcjNotification){
        ttsScltxxcjNotification = ttsScltxxcjNotificationService.queryById(ttsScltxxcjNotification.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjNotification",ttsScltxxcjNotification);
        return map;
    }
    
     /**
     * 修改投诉建议信息管理数据信息
     * @param ttsScltxxcjNotification
     * @return
     */
    @ApiOperation(value = "修改通知消息信息")
    @SystemControllerLog(description = "通知消息",operationType = "修改")
    @RequestMapping(value = "/updateTtsScltxxcjNotification",method = RequestMethod.POST)
    public Object updateTtsScltxxcjNotification(@RequestBody TtsScltxxcjNotification ttsScltxxcjNotification,String id){
      	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            ttsScltxxcjNotification.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjNotification.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjNotification.setUpdateTime(new Date());
    		ttsScltxxcjNotificationService.update(ttsScltxxcjNotification);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除投诉建议信息管理信息
     * @param ttsScltxxcjNotification
     * @return
     */
    @ApiOperation(value = " 删除通知消息信息")
    @SystemControllerLog(description = "通知消息",operationType = "删除")
    @RequestMapping(value = "/deleteTtsScltxxcjNotification",method = RequestMethod.POST)
    public Object deleteTtsScltxxcjNotification(TtsScltxxcjNotification ttsScltxxcjNotification){
   	    try{
    		ttsScltxxcjNotificationService.delete(ttsScltxxcjNotification.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

