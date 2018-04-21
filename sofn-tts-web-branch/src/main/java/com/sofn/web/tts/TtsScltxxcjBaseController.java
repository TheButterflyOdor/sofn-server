package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjBase;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjUser;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.TtsScltxxcjBaseService;
import com.sofn.service.tts.TtsScltxxcjUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
*	基地信息管理 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "基地信息管理", description = "基地信息管理")
@RequestMapping(value = "/ttsScltxxcjBase",method = RequestMethod.POST)
public class TtsScltxxcjBaseController extends BaseController {

	@Autowired
    public TtsScltxxcjBaseService ttsScltxxcjBaseService;
    @Autowired
    public SSOLoginService ssoLoginService;
    @Autowired
    public TtsScltxxcjUserService ttsScltxxcjUserService;

     /**
     * 根据条件获取基地信息管理列表
     * @param ttsScltxxcjBase
     * @return
     */
    @ApiOperation(value = "获取基地信息管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取基地信息列表",operationType = "查询")
    public Object getPageInfo(TtsScltxxcjBase ttsScltxxcjBase, int start, int length,String id,String status) {
        try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            PageInfo<TtsScltxxcjBase> pageInfo =ttsScltxxcjBaseService.getPageInfo(ttsScltxxcjBase,((start+1)/length)+1,length,user,status);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增基地信息管理记录
     *
     * @param ttsScltxxcjBase
     * @return
     */
    @ApiOperation(value = "新增基地信息管理数据")
    @RequestMapping(value = "/addTtsScltxxcjBase", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增基地信息", operationType = "新增")
    public Object addTtsScltxxcjBase(@RequestBody TtsScltxxcjBase ttsScltxxcjBase, String id) {
        try {
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            //封装基地信息
            ttsScltxxcjBase.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjBase.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjBase.setCreateTime(new Date());
            //判断该基地是否已存在
            boolean isExistedBase = ttsScltxxcjBaseService.isExistedBase(ttsScltxxcjBase);
            if (!isExistedBase) {
                ttsScltxxcjBaseService.add(ttsScltxxcjBase);
            } else {
                ModelMap modelMap = new ModelMap();
                modelMap.put("msg", "已存在此数据");
                return setModelMap(modelMap, HttpCode.REPEAT_DATA);
            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	 /**
     * 根据ID获取单条基地信息管理数据信息
     * @param ttsScltxxcjBase
     * @return
     */
    @ApiOperation(value = "获取单条基地信息管理数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取一条基地信息列表",operationType = "查询")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjBase  ttsScltxxcjBase){
        ttsScltxxcjBase = ttsScltxxcjBaseService.queryById(ttsScltxxcjBase.getId());
        if(StringUtils.isNullEmpty(ttsScltxxcjBase.getPicture())){
            ttsScltxxcjBase.setPicture("../../../images/baseImage/noDelete.jpg");
        }
        if(StringUtils.isNullEmpty(ttsScltxxcjBase.getPictureTwo())){
            ttsScltxxcjBase.setPictureTwo("../../../images/baseImage/noDelete.jpg");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjBase",ttsScltxxcjBase);
        return map;
    }
    
     /**
     * 修改基地信息管理数据信息
     * @param ttsScltxxcjBase
     * @return
     */
    @ApiOperation(value = "修改基地信息管理数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjBase",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改基地信息",operationType = "修改")
    public Object updateTtsScltxxcjBase(@RequestBody TtsScltxxcjBase ttsScltxxcjBase,String id){
      	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            ttsScltxxcjBase.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjBase.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjBase.setUpdateTime(new Date());
            //判断该基地是否已存在
            boolean isExistedBase = ttsScltxxcjBaseService.isExistedBase(ttsScltxxcjBase);
            if (!isExistedBase) {
                ttsScltxxcjBaseService.update(ttsScltxxcjBase);
            } else {
                ModelMap modelMap = new ModelMap();
                modelMap.put("msg", "已存在此数据");
                return setModelMap(modelMap, HttpCode.REPEAT_DATA);
            }
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            File img = new File(ttsScltxxcjBase.getPicture());
            File img2 = new File(ttsScltxxcjBase.getPictureTwo());
            if(img.exists()){
                img.delete();
            }
            if(img2.exists()){
                img2.delete();
            }
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除基地信息管理信息
     * @param ttsScltxxcjBase
     * @return
     */
    @ApiOperation(value = " 删除基地信息管理信息")
    @RequestMapping(value = "/deleteTtsScltxxcjBase",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除一条或多条基地信息",operationType = "删除")
    public Object deleteTtsScltxxcjBase(TtsScltxxcjBase ttsScltxxcjBase) {
        try {
            //ttsScltxxcjBaseService.deleteById(ttsScltxxcjBase.getId());
            String idsStr = ttsScltxxcjBase.getId();
            if (idsStr != null) {
                ttsScltxxcjBaseService.batchDeleteByIds(idsStr.split(","));
                return setSuccessModelMap(new ModelMap());
            } else {
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改基地状态
     * @param tscltxxcjbase
     * @return
     */
    @ApiOperation(value = "修改基地状态")
    @RequestMapping(value = "/changeBaseStatus",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改基地信息状态",operationType = "修改")
    public Object changeBaseStatus(TtsScltxxcjBase tscltxxcjbase){
        tscltxxcjbase.setUpdateTime(new Date());
        int result = ttsScltxxcjBaseService.changeBaseStatus(tscltxxcjbase);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "基地图片上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @SystemControllerLog(description = "上传基地图片",operationType = "图片上传")
    public Map<String,Object> upload(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            map.put("path",this.uploadFile(request));
            map.put("httpCode", ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG,ApiMsgConstants.SUCCESS_MSG);
        }catch (Exception e){
            e.printStackTrace();
            map.put("httpCode", ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG,ApiMsgConstants.FAILED_MSG);
        }
        return map;
    }
}

