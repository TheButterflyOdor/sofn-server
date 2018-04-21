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
import com.sofn.model.generator.AdsModelCheckItem;
import com.sofn.model.generator.AdsModelCheckObject;
import com.sofn.model.generator.AdsModelObjectItemMapping;
import com.sofn.service.ads.AdsModelCheckItemService;
import com.sofn.service.ads.AdsModelCheckObjectService;
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
import java.util.List;
import java.util.Map;

/**
*	模型_检测对象 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "模型_检测对象", description = "模型_检测对象")
@RequestMapping(value = "/adsModelCheckObject",method = RequestMethod.POST)
public class AdsModelCheckObjectController extends BaseController {

	@Autowired
    private AdsModelCheckObjectService adsModelCheckObjectService;
    @Autowired
    private AdsModelCheckItemService adsModelCheckItemService;

     /**
     * 根据条件获取模型_检测对象列表
     * @param adsModelCheckObject
     * @return
     */
    @ApiOperation(value = "获取模型_检测对象信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(AdsModelCheckObject adsModelCheckObject, int start, int length) {
    	try{
    		PageInfo<AdsModelCheckObject> pageInfo =adsModelCheckObjectService.getPageInfo(adsModelCheckObject,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增模型_检测对象记录
     * @param adsModelCheckObject
     * @return
     */
    @ApiOperation(value = "新增模型_检测对象数据")
    @RequestMapping(value = "/addAdsModelCheckObject",method = RequestMethod.POST)
    public Object addAdsModelCheckObject(AdsModelCheckObject  adsModelCheckObject,String token){
    	try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckObject.setOrganId(u.getOrganizationId());
                adsModelCheckObject.setCreateBy(u.getId());
                int result = adsModelCheckObjectService.insert(adsModelCheckObject);
                if(result == 0){
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }else if(result == 1){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.CONFLICT);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelCheckObjectController.addAdsModelCheckObject:新增模型_检测对象数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条模型_检测对象数据信息
     * @param adsModelCheckObject
     * @return
     */
    @ApiOperation(value = "获取单条模型_检测对象数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody AdsModelCheckObject  adsModelCheckObject){
        adsModelCheckObject = adsModelCheckObjectService.queryById(adsModelCheckObject.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsModelCheckObject",adsModelCheckObject);
        return map;
    }
    
     /**
     * 修改模型_检测对象数据信息
     * @param adsModelCheckObject
     * @return
     */
    @ApiOperation(value = "修改模型_检测对象数据信息")
        @RequestMapping(value = "/updateAdsModelCheckObject",method = RequestMethod.POST)
    public Object updateAdsModelCheckObject(AdsModelCheckObject adsModelCheckObject,String token){
      	try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckObject.setUpdateBy(u.getId());
                int result = adsModelCheckObjectService.updateByPrimaryKey(adsModelCheckObject);
                if(result == 0){
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }else if(result == 1){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.CONFLICT);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelCheckObjectController.updateAdsModelCheckObject:修改模型_检测对象数据信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除模型_检测对象信息
     * @param adsModelCheckObject
     * @return
     */
    @ApiOperation(value = " 删除模型_检测对象信息")
    @RequestMapping(value = "/deleteAdsModelCheckObject",method = RequestMethod.POST)
    public Object deleteAdsModelCheckObject(AdsModelCheckObject adsModelCheckObject,String token) {
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckObject.setUpdateBy(u.getId());
                int result = adsModelCheckObjectService.deleteByID(adsModelCheckObject);
                if (result > 0) {
                    return setSuccessModelMap(new ModelMap());
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsModelCheckObjectController.deleteAdsModelCheckObject:删除模型_检测对象信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取全部模型_检测对象数据条数
     * @param
     * @return
     */
    @ApiOperation(value = "获取全部模型_检测对象数据条数")
    @RequestMapping(value = "/getPageInfoAll",method = RequestMethod.POST)
    public Object getPageInfoAll(AdsModelCheckObject adsModelCheckObject, int start, int length, String name,String token) {
        try{
            PageInfo<AdsModelCheckObject> pageInfo =adsModelCheckObjectService.getPageInfoAll(adsModelCheckObject,((start+1)/length)+1,length,name);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取监测模型下的检测对象数据
     * @param
     * @return
     */
    @ApiOperation(value = "获取监测模型下的检测对象数据")
    @RequestMapping(value = "/getPageInfoByModel",method = RequestMethod.POST)
    public Object getPageInfoByModel(AdsModelCheckObject adsModelCheckObject, int start, int length) {
        try{
            //查询模型下的检测对象
            PageInfo<AdsModelCheckObject> pageInfo =adsModelCheckObjectService.getPageInfoByModelId(adsModelCheckObject,((start+1)/length)+1,length);
            //查询模型下的检测项目并集
            List<AdsModelCheckItem> items = adsModelCheckItemService.getCheckItemListByModelId(adsModelCheckObject.getModel_id());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pageInfo",pageInfo);
            map.put("items",items);
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            return map;
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量删除模型_检测对象数据
     *
     * @param adsModelCheckObject
     * @return
     */
    @ApiOperation(value = "批量删除模型_检测对象数据")
    @SystemControllerLog(description = "批量删除模型_检测对象数据")
    @RequestMapping(value = "/delAdsModelCheckObject", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> delAdsModelType(AdsModelCheckObject adsModelCheckObject,String token) {
        //TODO
        try {
            //FIX
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckObject.setUpdateBy(u.getId());
                int flag = adsModelCheckObjectService.deleteAll(adsModelCheckObject);
                if(flag > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsModelCheckObjectController.delAdsModelType:批量删除模型_检测对象数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取模型_检测对象含有的模型_检测项目列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取模型_检测对象含有的模型_检测项目列表")
    @RequestMapping(value = "/getPageInfoChecked",method = RequestMethod.POST)
    public Object getPageInfoChecked(AdsModelCheckItem adsModelCheckItem, int start, int length,String objectId, String name) {
        try{
            PageInfo<AdsModelCheckItem> pageInfo =adsModelCheckItemService.getPageInfoChecked(adsModelCheckItem,((start+1)/length)+1,length,objectId,name);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取模型_检测对象未含有的模型_检测项目列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取模型_检测对象未含有的模型_检测项目列表")
    @RequestMapping(value = "/getPageInfoUnChecked",method = RequestMethod.POST)
    public Object getPageInfoUnChecked(AdsModelObjectItemMapping adsModelObjectItemMapping, int start, int length, String objectId, String name) {
        try{
            PageInfo<AdsModelCheckItem> pageInfo =adsModelCheckItemService.getPageInfoUnChecked(adsModelObjectItemMapping,((start+1)/length)+1,length,objectId,name);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量添加模型_检测项目数据
     *
     * @param adsModelObjectItemMapping
     * @return
     */
    @ApiOperation(value = "批量添加模型_检测项目数据")
    @SystemControllerLog(description = "批量添加模型_检测项目数据")
    @RequestMapping(value = "/addAdsModelObjectItemMapping", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> addAdsModelObjectItemMapping(AdsModelObjectItemMapping adsModelObjectItemMapping,String token) {
        try {
            //FIX
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelObjectItemMapping.setCreateBy(u.getId());
                int flag = adsModelCheckItemService.insertIntoMapping(adsModelObjectItemMapping);
                if(flag > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsModelCheckObjectController.addAdsModelObjectItemMapping:批量添加模型_检测项目数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据ID删除单条模型_检测项目中的检测对象
     * @param adsModelObjectItemMapping
     * @return
     */
    @ApiOperation(value = " 根据ID删除单条模型_检测项目中的检测对象")
    @RequestMapping(value = "/deleteAdsModelObjectItemMapping",method = RequestMethod.POST)
    public Object deleteAdsModelObjectItemMapping(@RequestBody AdsModelObjectItemMapping adsModelObjectItemMapping){
        try{
            int result = adsModelCheckItemService.deleteMappingByID(adsModelObjectItemMapping);
            if(result > 0){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            logger.error("AdsModelCheckObjectController.deleteAdsModelObjectItemMapping:根据ID删除单条模型_检测项目中的检测对象异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 批量删除模型_检测项目中的检测对象
     * @param adsModelObjectItemMapping
     * @return
     */
    @ApiOperation(value = " 批量删除模型_检测项目中的检测对象")
    @RequestMapping(value = "/delAdsModelObjectItemMapping",method = RequestMethod.POST)
    public Object delAdsModelObjectItemMapping(AdsModelObjectItemMapping adsModelObjectItemMapping){
        try{
            int flag = adsModelCheckItemService.deleteMappingAll(adsModelObjectItemMapping);
            if(flag > 0){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            logger.error("AdsModelCheckObjectController.delAdsModelObjectItemMapping:批量删除模型_检测项目中的检测对象异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

