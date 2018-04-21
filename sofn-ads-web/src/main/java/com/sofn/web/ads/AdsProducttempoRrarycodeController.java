package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;


import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.AdsProducttempoRrarycode;
import com.sofn.service.ads.AdsProducttempoRrarycodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
*	产品临时码 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "产品临时码", description = "产品临时码")
@RequestMapping(value = "/adsProducttempoRrarycode",method = RequestMethod.POST)
public class AdsProducttempoRrarycodeController extends BaseController {

	@Autowired
    public AdsProducttempoRrarycodeService adsProducttempoRrarycodeService;
    
     /**
     * 根据条件获取产品临时码列表
     * @param adsProducttempoRrarycode
     * @return
     */
    @ApiOperation(value = "获取产品临时码信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsProducttempoRrarycode adsProducttempoRrarycode, int start, int length) {
    	try{
    		PageInfo<AdsProducttempoRrarycode> pageInfo =adsProducttempoRrarycodeService.getPageInfo(adsProducttempoRrarycode,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增产品临时码记录
     * @param adsProducttempoRrarycode
     * @return
     */
    @ApiOperation(value = "新增产品临时码数据")
    @RequestMapping(value = "/addAdsProducttempoRrarycode",method = RequestMethod.POST)
    public Object addAdsProducttempoRrarycode(@RequestBody AdsProducttempoRrarycode  adsProducttempoRrarycode){
    	try{
    		adsProducttempoRrarycodeService.add(adsProducttempoRrarycode);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条产品临时码数据信息
     * @param adsProducttempoRrarycode
     * @return
     */
    @ApiOperation(value = "获取单条产品临时码数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody AdsProducttempoRrarycode  adsProducttempoRrarycode){
        adsProducttempoRrarycode = adsProducttempoRrarycodeService.queryById(adsProducttempoRrarycode.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsProducttempoRrarycode",adsProducttempoRrarycode);
        return map;
    }
    
     /**
     * 修改产品临时码数据信息
     * @param adsProducttempoRrarycode
     * @return
     */
    @ApiOperation(value = "修改产品临时码数据信息")
    @RequestMapping(value = "/updateAdsProducttempoRrarycode",method = RequestMethod.POST)
    public Object updateAdsProducttempoRrarycode(@RequestBody AdsProducttempoRrarycode adsProducttempoRrarycode){
      	try{
    		adsProducttempoRrarycodeService.update(adsProducttempoRrarycode);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除产品临时码信息
     * @param adsProducttempoRrarycode
     * @return
     */
    @ApiOperation(value = " 删除产品临时码信息")
    @RequestMapping(value = "/deleteAdsProducttempoRrarycode",method = RequestMethod.POST)
    public Object deleteAdsProducttempoRrarycode(AdsProducttempoRrarycode adsProducttempoRrarycode){
   	    try{
    		adsProducttempoRrarycodeService.delete(adsProducttempoRrarycode.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

