package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjUser;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.SysCreateUserService;
import com.sofn.service.tts.TtsScltxxcjUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
*	企业用户及子账户信息表 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "企业用户及子账户信息表", description = "企业用户及子账户信息表")
@RequestMapping(value = "/ttsScltxxcjUser",method = RequestMethod.POST)
public class TtsScltxxcjUserController extends BaseController {

	@Autowired
    public TtsScltxxcjUserService ttsScltxxcjUserService;
    @Autowired
    public SSOLoginService ssoLoginService;
    @Autowired
    public SysCreateUserService sysCreateUserService;
     /**
     * 根据条件获取企业用户及子账户信息表列表
     * @param ttsScltxxcjUser
     * @return
     */
    @ApiOperation(value = "获取企业用户及子账户信息表信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取子账户信息",operationType = "查询")
    public Object getPageInfo(TtsScltxxcjUser ttsScltxxcjUser, int start, int length,String createTimeStart,String createTimeEnd,String keyWord,String id) {
        try{
            if(!StringUtils.isNullEmpty(keyWord)){
                ttsScltxxcjUser.setName(keyWord);
            }
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
    		PageInfo<TtsScltxxcjUser> pageInfo =ttsScltxxcjUserService.getPageInfo(ttsScltxxcjUser,((start+1)/length)+1,length,createTimeStart,createTimeEnd,user);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增企业用户及子账户信息表记录
     * @param ttsScltxxcjUser
     * @return
     */
    @ApiOperation(value = "新增企业用户及子账户信息表数据")
    @RequestMapping(value = "/addTtsScltxxcjUser",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增子账户信息",operationType = "新增")
    public Object addTtsScltxxcjUser(@RequestBody TtsScltxxcjUser  ttsScltxxcjUser,String id,String token){
    	try{
            boolean flag = ttsScltxxcjUserService.buildUser(ttsScltxxcjUser,id,token);
            if(flag){
                return setSuccessModelMap(new ModelMap());
            }else{
                return  setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
            }
    	}catch (Exception e){
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条企业用户及子账户信息表数据信息
     * @param ttsScltxxcjUser
     * @return
     */
    @ApiOperation(value = "获取单条企业用户及子账户信息表数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取一条子账户信息",operationType = "查询")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjUser  ttsScltxxcjUser){
        ttsScltxxcjUser = ttsScltxxcjUserService.queryById(ttsScltxxcjUser.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjUser",ttsScltxxcjUser);
        return map;
    }



    @ApiOperation(value = "根据账号修改用户信息")
    @RequestMapping(value = "/updateByAcc",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据账号修改用户信息",operationType = "修改")
    public Object updateUser(@RequestBody TtsScltxxcjUser  ttsScltxxcjUser){
        try {
            ttsScltxxcjUser.setUpdateTime(new Date());
            ttsScltxxcjUser.setUpdateBy(ttsScltxxcjUser.getAccount());
            ttsScltxxcjUserService.updateByAcc(ttsScltxxcjUser);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
     /**
     * 修改企业用户及子账户信息表数据信息
     * @param ttsScltxxcjUser
     * @return
     */
    @ApiOperation(value = "修改企业用户及子账户信息表数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjUser",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改一条子账户信息",operationType = "修改")
    public Object updateTtsScltxxcjUser(@RequestBody TtsScltxxcjUser ttsScltxxcjUser,String id){
      	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            /*ttsScltxxcjUser.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjUser.setEntityIdcode(user.getEntityIdcode());*/
            ttsScltxxcjUser.setUpdateTime(new Date());
    		ttsScltxxcjUserService.updateUserInfo(ttsScltxxcjUser);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除企业用户及子账户信息表信息
     * @param ttsScltxxcjUser
     * @return
     */
    @ApiOperation(value = " 删除企业用户及子账户信息表信息")
    @RequestMapping(value = "/deleteTtsScltxxcjUser",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除一条或多条子账户信息",operationType = "删除")
    public Object deleteTtsScltxxcjUser(TtsScltxxcjUser ttsScltxxcjUser){
   	    try{
    		//ttsScltxxcjUserService.deleteById(ttsScltxxcjUser.getId());
            String idsStr = ttsScltxxcjUser.getId();
            if (idsStr != null) {
                ttsScltxxcjUserService.batchDeleteByIds(idsStr.split(","));
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 重置用户密码
     * @param ttsScltxxcjUser
     * @return
     */
    @ApiOperation(value = "重置用户的密码为000000")
    @RequestMapping(value = "/resetTtsScltxxcjUser",method = RequestMethod.POST)
    @SystemControllerLog(description = "重置子账户密码",operationType = "修改")
    public Object resetTtsScltxxcjUser(@RequestBody TtsScltxxcjUser ttsScltxxcjUser,String token){
        try {
            boolean result = sysCreateUserService.resetPassword(ttsScltxxcjUser.getAccount(),token);
            if(result){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "根据身份证号查询用户是否存在(只适用于新增子账号时)")
    @RequestMapping(value = "/queryUserByIdCard",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据身份证查询子账户",operationType = "查询")
    public Object queryUserByIdCard(String idCard){
        boolean isEmpty = ttsScltxxcjUserService.getUserByIdCard(idCard);
        if (isEmpty){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "根据身份证号查询用户是否存在(只适用于修改子账号时)")
    @RequestMapping(value = "/findUserByIdCard",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据身份证查询子账户",operationType = "查询")
    public Object findUserByIdCard(String idCard,String account){
        boolean isEmpty = ttsScltxxcjUserService.findUserByIdCard(idCard,account);
        if (isEmpty){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据身份证号查询账户基本信息")
    @GetMapping(value = "selectUserById")
    @SystemControllerLog(description = "根据身份证号查询账户基本信息",operationType = "查询")
    public Object selectUserById(String id){
        ModelMap modelMap = new ModelMap();
        TtsScltxxcjUser ttsScltxxcjUser = ttsScltxxcjUserService.selectUserById(id);
        modelMap.addAttribute(ttsScltxxcjUser);
        return modelMap;
    }

    @ApiOperation(value = "编辑修改账户信息")
    @PostMapping(value = "updateUserInfoById")
    @SystemControllerLog(description = "编辑修改账户信息",operationType = "查询")
    public Object updateUserInfoById(@RequestBody TtsScltxxcjUser ttsScltxxcjUser){
        ttsScltxxcjUserService.updateUserInfoById(ttsScltxxcjUser);
        return setSuccessModelMap(new ModelMap());
    }
}

