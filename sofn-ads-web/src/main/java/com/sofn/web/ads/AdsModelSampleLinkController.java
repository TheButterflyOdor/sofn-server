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
import com.sofn.model.generator.AdsModelSampleLink;
import com.sofn.service.ads.AdsModelSampleLinkService;
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
*	模型_抽样环节 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "模型_抽样环节", description = "模型_抽样环节")
@RequestMapping(value = "/adsModelSampleLink",method = RequestMethod.POST)
public class AdsModelSampleLinkController extends BaseController {

	@Autowired
    private AdsModelSampleLinkService adsModelSampleLinkService;
    
     /**
     * 根据条件获取模型_抽样环节列表
     * @param adsModelSampleLink
     * @return
     */
    @ApiOperation(value = "获取模型_抽样环节信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(AdsModelSampleLink adsModelSampleLink, int start, int length) {
    	try{
    		PageInfo<AdsModelSampleLink> pageInfo =adsModelSampleLinkService.getPageInfo(adsModelSampleLink,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增模型_抽样环节记录
     * @param adsModelSampleLink
     * @return
     */
    @ApiOperation(value = "新增模型_抽样环节数据")
    @RequestMapping(value = "/addAdsModelSampleLink",method = RequestMethod.POST)
    public Object addAdsModelSampleLink(AdsModelSampleLink  adsModelSampleLink,String token){
    	try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelSampleLink.setCreateBy(u.getId());
                int result = adsModelSampleLinkService.insert(adsModelSampleLink);
                if(result > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelSampleLinkController.addAdsModelSampleLink:新增模型_抽样环节数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条模型_抽样环节数据信息
     * @param adsModelSampleLink
     * @return
     */
    @ApiOperation(value = "获取单条模型_抽样环节数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody AdsModelSampleLink  adsModelSampleLink){
        adsModelSampleLink = adsModelSampleLinkService.queryById(adsModelSampleLink.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsModelSampleLink",adsModelSampleLink);
        return map;
    }
    
     /**
     * 修改模型_抽样环节数据信息
     * @param adsModelSampleLink
     * @return
     */
    @ApiOperation(value = "修改模型_抽样环节数据信息")
    @RequestMapping(value = "/updateAdsModelSampleLink",method = RequestMethod.POST)
    public Object updateAdsModelSampleLink(AdsModelSampleLink adsModelSampleLink,String token){
      	try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelSampleLink.setUpdateBy(u.getId());
                int result = adsModelSampleLinkService.updateByPrimaryKey(adsModelSampleLink);
                if(result >0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelSampleLinkController.updateAdsModelSampleLink:修改模型_抽样环节数据信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除模型_抽样环节信息
     * @param adsModelSampleLink
     * @return
     */
    @ApiOperation(value = " 删除模型_抽样环节信息")
    @RequestMapping(value = "/deleteAdsModelSampleLink",method = RequestMethod.POST)
    public Object deleteAdsModelSampleLink(AdsModelSampleLink adsModelSampleLink,String token){
   	    try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelSampleLink.setUpdateBy(u.getId());
                int result = adsModelSampleLinkService.deleteByID(adsModelSampleLink);
                if(result > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
    	}catch (Exception e){
            logger.error("AdsModelSampleLinkController.deleteAdsModelSampleLink:删除模型_抽样环节信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取全部模型_抽样环节数据条数
     * @param
     * @return
     */
    @ApiOperation(value = "获取全部模型_抽样环节数据条数")
    @RequestMapping(value = "/getPageInfoAll",method = RequestMethod.POST)
    public Object getPageInfoAll(AdsModelSampleLink adsModelSampleLink, int start, int length, String name) {
        try{
            PageInfo<AdsModelSampleLink> pageInfo =adsModelSampleLinkService.getPageInfoAll(adsModelSampleLink,((start+1)/length)+1,length,name);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量删除模型_种类数据
     *
     * @param adsModelSampleLink
     * @return
     */
    @ApiOperation(value = "批量删除模型_抽样环节数据")
    @SystemControllerLog(description = "批量删除模型_抽样环节数据")
    @RequestMapping(value = "/delAdsModelSampleLink", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> delAdsModelType(AdsModelSampleLink adsModelSampleLink,String token) {
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsModelSampleLink.setUpdateBy(u.getId());
                int flag = adsModelSampleLinkService.deleteAll(adsModelSampleLink);
                if(flag > 0){
                    return setSuccessModelMap(new ModelMap());
                }else{
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsModelSampleLinkController.delAdsModelType:批量删除模型_抽样环节数据",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

