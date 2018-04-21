package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AdsModelCheckStandard;
import com.sofn.service.ads.AdsModelCheckStandardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
*	模型_检测标准 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "模型_检测标准", description = "模型_检测标准")
@RequestMapping(value = "/adsModelCheckStandard",method = RequestMethod.POST)
public class AdsModelCheckStandardController extends BaseController {

	@Autowired
    private AdsModelCheckStandardService adsModelCheckStandardService;
    
     /**
     * 根据条件获取模型_检测标准列表
     * @param adsModelCheckStandard
     * @return
     */
    @ApiOperation(value = "获取模型_检测标准信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(AdsModelCheckStandard adsModelCheckStandard, int start, int length) {
    	try{
    		PageInfo<AdsModelCheckStandard> pageInfo =adsModelCheckStandardService.getPageInfo(adsModelCheckStandard,((start+1)/length)+1,length);
            return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增模型_检测标准记录
     * @param adsModelCheckStandard
     * @return
     *//*
    @ApiOperation(value = "新增模型_检测标准数据")
    @RequestMapping(value = "/addAdsModelCheckStandard",method = RequestMethod.POST)
    public Object addAdsModelCheckStandard(AdsModelCheckStandard  adsModelCheckStandard,String token){
    	try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckStandard.setCreateBy(u.getId());
                int result = adsModelCheckStandardService.insert(adsModelCheckStandard);
                if(result > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }*/


	 /**
     * 根据ID获取单条模型_检测标准数据信息
     * @param adsModelCheckStandard
     * @return
     */
    @ApiOperation(value = "获取单条模型_检测标准数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody AdsModelCheckStandard  adsModelCheckStandard){
        adsModelCheckStandard = adsModelCheckStandardService.queryById(adsModelCheckStandard.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsModelCheckStandard",adsModelCheckStandard);
        return map;
    }
    
     /**
     * 修改模型_检测标准数据信息
     * @param adsModelCheckStandard
     * @return
     */
    @ApiOperation(value = "修改模型_检测标准数据信息")
    @RequestMapping(value = "/updateAdsModelCheckStandard",method = RequestMethod.POST)
    public Object updateAdsModelCheckStandard(AdsModelCheckStandard adsModelCheckStandard,String token){
      	try{
      	    Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckStandard.setUpdateBy(u.getId());
                int result =adsModelCheckStandardService.updateByPrimaryKey(adsModelCheckStandard);
                if(result >0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelCheckStandardController.updateAdsModelCheckStandard:修改模型_检测标准数据信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除模型_检测标准信息
     * @param adsModelCheckStandard
     * @return
     */
   /* @ApiOperation(value = " 删除模型_检测标准信息")
    @RequestMapping(value = "/deleteAdsModelCheckStandard",method = RequestMethod.POST)
    public Object deleteAdsModelCheckStandard(AdsModelCheckStandard adsModelCheckStandard,String token){
   	    try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckStandard.setUpdateBy(u.getId());
                int result = adsModelCheckStandardService.deleteByID(adsModelCheckStandard);
                if(result > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }*/

    /**
     * 获取全部模型_检测标准数据条数
     * @param
     * @return
     */
    @ApiOperation(value = "获取全部模型_检测标准数据条数")
    @RequestMapping(value = "/getPageInfoAll",method = RequestMethod.POST)
    public Object getPageInfoAll(AdsModelCheckStandard adsModelCheckStandard, int start, int length, String name) {
        try{
            PageInfo<AdsModelCheckStandard> pageInfo =adsModelCheckStandardService.getPageInfoAll(adsModelCheckStandard,((start+1)/length)+1,length,name);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量删除模型_检测标准数据
     *
     * @param adsModelCheckStandard
     * @return
     */
   /* @ApiOperation(value = "批量删除模型_检测标准数据")
    @SystemControllerLog(description = "批量删除模型_检测标准数据")
    @RequestMapping(value = "/delAdsModelCheckStandard", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> deleteAdsOrganTask(AdsModelCheckStandard adsModelCheckStandard,String token) {
        try {
            //FIX
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckStandard.setUpdateBy(u.getId());
                int flag = adsModelCheckStandardService.deleteAll(adsModelCheckStandard);
                if(flag > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }*/
}

