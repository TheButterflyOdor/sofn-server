package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AdsModelType;
import com.sofn.service.ads.AdsModelTypeService;
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
*	模型_种类 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "模型_种类", description = "模型_种类")
@RequestMapping(value = "/adsModelType",method = RequestMethod.POST)
public class AdsModelTypeController extends BaseController {

	@Autowired
    private AdsModelTypeService adsModelTypeService;
    
     /**
     * 根据条件获取模型_种类列表
     * @param adsModelType
     * @return
     */
    @ApiOperation(value = "获取模型_种类信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(AdsModelType adsModelType, int start, int length) {
    	try{
    		PageInfo<AdsModelType> pageInfo =adsModelTypeService.getPageInfo(adsModelType,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增模型_种类记录
     * @param adsModelType
     * @return
     */
    @ApiOperation(value = "新增模型_种类数据")
    @RequestMapping(value = "/addAdsModelType",method = RequestMethod.POST)
    public Object addAdsModelType(AdsModelType  adsModelType,String token){
    	try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelType.setCreateBy(u.getId());
                int result = adsModelTypeService.insert(adsModelType);
                if(result > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelTypeController.addAdsModelType:新增模型_种类数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条模型_种类数据信息
     * @param adsModelType
     * @return
     */
    @ApiOperation(value = "获取单条模型_种类数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody AdsModelType  adsModelType){
        adsModelType = adsModelTypeService.queryById(adsModelType.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsModelType",adsModelType);
        return map;
    }
    
     /**
     * 修改模型_种类数据信息
     * @param adsModelType
     * @return
     */
    @ApiOperation(value = "修改模型_种类数据信息")
    @RequestMapping(value = "/updateAdsModelType",method = RequestMethod.POST)
    public Object updateAdsModelType(AdsModelType adsModelType,String token){
      	try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelType.setUpdateBy(u.getId());
                int result =adsModelTypeService.updateByPrimaryKey(adsModelType);
                if(result >0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelTypeController.updateAdsModelType:修改模型_种类数据信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除模型_种类信息
     * @param adsModelType
     * @return
     */
    @ApiOperation(value = " 删除模型_种类信息")
    @RequestMapping(value = "/deleteAdsModelType",method = RequestMethod.POST)
    public Object deleteAdsModelType(AdsModelType adsModelType,String token){
   	    try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelType.setUpdateBy(u.getId());
                int result = adsModelTypeService.deleteByID(adsModelType);
                if(result > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelTypeController.deleteAdsModelType:删除模型_种类信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取全部模型_检测标准数据条数
     * @param
     * @return
     */
    @ApiOperation(value = "获取全部模型_检测标准数据条数")
    @RequestMapping(value = "/getPageInfoAll",method = RequestMethod.POST)
    public Object getPageInfoAll(AdsModelType adsModelType, int start, int length, String name) {
        try{
            PageInfo<AdsModelType> pageInfo =adsModelTypeService.getPageInfoAll(adsModelType,((start+1)/length)+1,length,name);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量删除模型_种类数据
     *
     * @param adsModelType
     * @return
     */
    @ApiOperation(value = "批量删除模型_种类数据")
    @SystemControllerLog(description = "批量删除模型_种类数据")
    @RequestMapping(value = "/delAdsModelType", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> delAdsModelType(AdsModelType adsModelType,String token) {
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelType.setUpdateBy(u.getId());
                int flag = adsModelTypeService.deleteAll(adsModelType);
                if(flag > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsModelTypeController.delAdsModelType:批量删除模型_种类数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

