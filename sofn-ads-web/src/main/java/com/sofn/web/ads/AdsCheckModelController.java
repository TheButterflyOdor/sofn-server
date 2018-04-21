package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsModelAttributeMapping;
import com.sofn.model.generator.SysUser;
import com.sofn.service.ads.AdsCheckModelService;
import com.sofn.service.ads.AdsSysUserService;
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
*	检测模型 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "检测模型", description = "检测模型")
@RequestMapping(value = "/adsCheckModel",method = RequestMethod.POST)
public class AdsCheckModelController extends BaseController {

	@Autowired
    private AdsCheckModelService adsCheckModelService;
    
     /**
     * 根据条件获取检测模型列表
     * @param adsCheckModel
     * @return
     */
    @ApiOperation(value = "获取检测模型信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsCheckModel adsCheckModel, int start, int length) {
    	try{
    		PageInfo<AdsCheckModel> pageInfo =adsCheckModelService.getPageInfo(adsCheckModel,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增检测模型记录
     * @param adsCheckModel
     * @return
     */
    @ApiOperation(value = "新增检测模型数据")
    @RequestMapping(value = "/addAdsCheckModel",method = RequestMethod.POST)
    public Object addAdsCheckModel(AdsCheckModel adsCheckModel, String token){
    	try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsCheckModel.setOrganId(u.getOrganizationId());
                adsCheckModel.setCreateBy(u.getId());
                int result = adsCheckModelService.insert(adsCheckModel);
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
            logger.error("AdsCheckModelController.addAdsCheckModel:新增检测模型数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条检测模型数据信息
     * @param adsCheckModel
     * @return
     */
    @ApiOperation(value = "获取单条检测模型数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody AdsCheckModel  adsCheckModel){
        adsCheckModel = adsCheckModelService.queryById(adsCheckModel.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsCheckModel",adsCheckModel);
        return map;
    }
    
     /**
     * 修改检测模型数据信息
     * @param adsCheckModel
     * @return
     */
    @ApiOperation(value = "修改检测模型数据信息")
    @RequestMapping(value = "/updateAdsCheckModel",method = RequestMethod.POST)
    public Object updateAdsCheckModel(AdsCheckModel adsCheckModel,String token){
      	try{
      	    Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsCheckModel.setUpdateBy(u.getId());
                int result = adsCheckModelService.updateByPrimaryKey(adsCheckModel);
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
            logger.error("AdsCheckModelController.updateAdsCheckModel:修改检测模型数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除检测模型信息
     * @param adsCheckModel
     * @return
     */
    @ApiOperation(value = " 删除检测模型信息")
    @RequestMapping(value = "/deleteAdsCheckModel",method = RequestMethod.POST)
    public Object deleteAdsCheckModel(AdsCheckModel adsCheckModel,String token){
   	    try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsCheckModel.setUpdateBy(u.getId());
                int result = adsCheckModelService.deleteByID(adsCheckModel);
                if (result > 0) {
                    return setSuccessModelMap(new ModelMap());
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsCheckModelController.deleteAdsCheckModel:删除检测模型数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取全部检测模型
     * @param
     * @return
     */
    @ApiOperation(value = "获取全部检测模型")
    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    public Object selectAll() {
        try{
            List<Map<String,Object>> checkModel =adsCheckModelService.selectAll();
            return setSuccessModelMap(new ModelMap(),checkModel);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取全部检测模型数据列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取全部检测模型数据列表")
    @RequestMapping(value = "/getPageInfoAll",method = RequestMethod.POST)
    public Object getPageInfoAll(AdsCheckModel adsCheckModel, int start, int length, String modelName, String monitorType,String token) {
        try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsCheckModel.setOrganId(u.getOrganizationId());
                PageInfo<AdsCheckModel> pageInfo =adsCheckModelService.getPageInfoAll(adsCheckModel,((start+1)/length)+1,length,modelName,monitorType);
                return setSuccessModelMap(new ModelMap(),pageInfo);
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量删除检测模型数据
     *
     * @param adsCheckModel
     * @return
     */
    @ApiOperation(value = "批量删除检测模型数据")
    @SystemControllerLog(description = "批量删除检测模型数据")
    @RequestMapping(value = "/delAdsCheckModel", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> delAdsCheckModel(AdsCheckModel adsCheckModel,String token) {
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsCheckModel.setUpdateBy(u.getId());
                int flag = adsCheckModelService.deleteAll(adsCheckModel);
                if(flag > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsCheckModelController.delAdsCheckModel:批量删除检测模型数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取检测模型含有的子表数据列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取检测模型含有的子表数据列表")
    @RequestMapping(value = "/getPageInfoChecked",method = RequestMethod.POST)
    public Object getPageInfoChecked(AdsModelAttributeMapping adsModelAttributeMapping, int start, int length, String modelId, String type, String name) {
        try{
            PageInfo<AdsModelAttributeMapping> pageInfo =adsCheckModelService.getPageInfoChecked(adsModelAttributeMapping,((start+1)/length)+1,length,modelId,type,name);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取检测模型未含有的子表数据列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取检测模型未含有的子表数据列表")
    @RequestMapping(value = "/getPageInfoUnChecked",method = RequestMethod.POST)
    public Object getPageInfoUnChecked(AdsModelAttributeMapping adsModelAttributeMapping, int start, int length, String modelId, String type, String name) {
        try{
            PageInfo<AdsModelAttributeMapping> pageInfo =adsCheckModelService.getPageInfoUnChecked(adsModelAttributeMapping,((start+1)/length)+1,length,modelId,type,name);

            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量添加子表数据
     *
     * @param adsModelAttributeMapping
     * @return
     */
    @ApiOperation(value = "批量添加子表数据")
    @SystemControllerLog(description = "批量添加子表数据")
    @RequestMapping(value = "/addAdsModelAttributeMapping", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> addAdsModelAttributeMapping(AdsModelAttributeMapping adsModelAttributeMapping,String token) {
        //TODO
        try {
            //FIX
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelAttributeMapping.setCreateBy(u.getId());
                int flag = adsCheckModelService.insertIntoMapping(adsModelAttributeMapping);
                if(flag > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsCheckModelController.addAdsModelAttributeMapping:批量添加子表数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据ID删除单条子表中的数据
     * @param adsModelAttributeMapping
     * @return
     */
    @ApiOperation(value = " 根据ID删除单条子表中的数据")
    @RequestMapping(value = "/deleteAdsModelAttributeMapping",method = RequestMethod.POST)
    public Object deleteAdsModelAttributeMapping(@RequestBody AdsModelAttributeMapping adsModelAttributeMapping){
        try{
            int result = adsCheckModelService.deleteMappingByID(adsModelAttributeMapping);
            if(result > 0){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            logger.error("AdsCheckModelController.deleteAdsModelAttributeMapping:根据ID删除单条子表中的数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 批量删除模子表中的数据
     * @param adsModelAttributeMapping
     * @return
     */
    @ApiOperation(value = " 批量删除模子表中的数据")
    @RequestMapping(value = "/delAdsModelAttributeMapping",method = RequestMethod.POST)
    public Object delAdsModelAttributeMapping(AdsModelAttributeMapping adsModelAttributeMapping){
        try{
            int flag = adsCheckModelService.deleteMappingAll(adsModelAttributeMapping);
            if(flag > 0){
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            logger.error("AdsCheckModelController.delAdsModelAttributeMapping:批量删除模子表中的数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 是否启用检测模型信息
     * @param adsCheckModel
     * @return
     */
    @ApiOperation(value = "是否启用检测模型信息")
    @RequestMapping(value = "/enableAdsCheckModel",method = RequestMethod.POST)
    public Object enableAdsCheckModel(AdsCheckModel adsCheckModel,String token){
        try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsCheckModel.setUpdateBy(u.getId());
                int result = adsCheckModelService.enableAdsCheckModel(adsCheckModel);
                if (result > 0) {
                    return setSuccessModelMap(new ModelMap());
                }else if(result == 0){
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        }catch (Exception e){
            logger.error("AdsCheckModelController.enableAdsCheckModel:是否启用检测模型信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取检测模型相关的判定标准数据
     * @param
     * @return
     */
    @ApiOperation(value = "获取检测模型相关的判定标准数据")
    @RequestMapping(value = "/getPageInfoCheckedAndJudgeStandard",method = RequestMethod.POST)
    public Object getPageInfoCheckedAndJudgeStandard(AdsModelAttributeMapping adsModelAttributeMapping, int start, int length, String modelId, String type, String name) {
        try{
            PageInfo<AdsModelAttributeMapping> pageInfo =adsCheckModelService.getPageInfoCheckedAndJudgeStandard(adsModelAttributeMapping,((start+1)/length)+1,length,modelId,type,name);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}
