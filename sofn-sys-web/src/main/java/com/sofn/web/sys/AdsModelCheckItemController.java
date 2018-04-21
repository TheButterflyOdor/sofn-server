package com.sofn.web.sys;

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
import com.sofn.model.generator.AdsModelObjectItemMapping;
import com.sofn.service.sys.AdsModelCheckItemService;
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
*	模型_检测项目 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "模型_检测项目", description = "模型_检测项目")
@RequestMapping(value = "/adsModelCheckItem",method = RequestMethod.POST)
public class AdsModelCheckItemController extends BaseController {

	@Autowired
    private AdsModelCheckItemService adsModelCheckItemService;
    
     /**
     * 根据条件获取模型_检测项目列表
     * @param adsModelCheckItem
     * @return
     */
    @ApiOperation(value = "获取模型_检测项目信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取模型_检测项目信息列表",operationType="查询")
    public Object getPageInfo(AdsModelCheckItem adsModelCheckItem, int start, int length) {
    	try{
    		PageInfo<AdsModelCheckItem> pageInfo =adsModelCheckItemService.getPageInfo(adsModelCheckItem,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增模型_检测项目记录
     * @param adsModelCheckItem
     * @return
     */
    @ApiOperation(value = "新增模型_检测项目数据")
    @SystemControllerLog(description = "新增模型_检测项目数据",operationType="新增")
    @RequestMapping(value = "/addAdsModelCheckItem",method = RequestMethod.POST)
    public Object addAdsModelCheckItem(AdsModelCheckItem  adsModelCheckItem,String token){
    	try{
    	    //token
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckItem.setCreateBy(u.getId());
                int flag = adsModelCheckItemService.insert(adsModelCheckItem);
                if(flag > 0){
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
    }


	 /**
     * 根据ID获取单条模型_检测项目数据信息
     * @param adsModelCheckItem
     * @return
     */
    @ApiOperation(value = "获取单条模型_检测项目数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条模型_检测项目数据信息",operationType="查询")
    public Map<String,Object> queryById(@RequestBody AdsModelCheckItem  adsModelCheckItem){
        adsModelCheckItem = adsModelCheckItemService.queryById(adsModelCheckItem.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsModelCheckItem",adsModelCheckItem);
        return map;
    }
    
     /**
     * 修改模型_检测项目数据信息
     * @param adsModelCheckItem
     * @return
     */
    @ApiOperation(value = "修改模型_检测项目数据信息")
    @SystemControllerLog(description = "修改模型_检测项目数据信息",operationType="修改")
    @RequestMapping(value = "/updateAdsModelCheckItem",method = RequestMethod.POST)
    public Object updateAdsModelCheckItem(AdsModelCheckItem adsModelCheckItem,String token){
      	try{
      	    //token
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckItem.setUpdateBy(u.getId());
                adsModelCheckItemService.update(adsModelCheckItem);
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除模型_检测项目信息
     * @param adsModelCheckItem
     * @return
     */
    @ApiOperation(value = "删除模型_检测项目信息")
    @SystemControllerLog(description = "删除模型_检测项目信息",operationType="删除")
    @RequestMapping(value = "/deleteAdsModelCheckItem",method = RequestMethod.POST)
    public Object deleteAdsModelCheckItem(AdsModelCheckItem adsModelCheckItem){
   	    try{
    		adsModelCheckItemService.delete(adsModelCheckItem.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除模型_检测项目信息
     * @param adsModelCheckItem
     * @return
     */
    @ApiOperation(value = " 批量删除模型_检测项目信息")
    @SystemControllerLog(description = "批量删除模型_检测项目信息",operationType="删除")
    @RequestMapping(value = "/batchDeleteCheckItem",method = RequestMethod.POST)
    public Object batchDeleteCheckItem(AdsModelCheckItem adsModelCheckItem,String token){
        try{
            //token
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelCheckItem.setUpdateBy(u.getId());
                int flag = adsModelCheckItemService.batchDelete(adsModelCheckItem);
                if(flag > 0){
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
    }

    /**
     * 获取模型_检测对象含有的模型_检测项目列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取模型_检测对象含有的模型_检测项目列表")
    @RequestMapping(value = "/getPageInfoChecked",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取模型_检测对象含有的模型_检测项目列表",operationType="查询")
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
    public Object getPageInfoUnChecked(AdsModelObjectItemMapping adsModelObjectItemMapping, int start, int length,String objectId, String name) {
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
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

