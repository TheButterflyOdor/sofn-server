package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.AdsCheckProject;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.service.ads.AdsCheckProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	检测项目 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "检测项目", description = "检测项目")
@RequestMapping(value = "/adsCheckProject",method = RequestMethod.POST)
public class AdsCheckProjectController extends BaseController {

	@Autowired
    private AdsCheckProjectService adsCheckProjectService;
    
     /**
     * 根据条件获取检测项目列表
     * @param adsCheckProject
     * @return
     */
    @ApiOperation(value = "获取检测项目信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsCheckProject adsCheckProject, int start, int length) {
    	try{
    		PageInfo<AdsCheckProject> pageInfo =adsCheckProjectService.getPageInfo(adsCheckProject,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增检测项目记录
     * @param adsCheckProject
     * @return
     */
    @ApiOperation(value = "新增检测项目数据")
    @RequestMapping(value = "/addAdsCheckProject",method = RequestMethod.POST)
    public Object addAdsCheckProject(@RequestBody AdsCheckProject  adsCheckProject){
    	try{
    		adsCheckProjectService.add(adsCheckProject);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            logger.error("AdsCheckProjectController.addAdsCheckProject:新增检测项目数据异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条检测项目数据信息
     * @param adsCheckProject
     * @return
     */
    @ApiOperation(value = "获取单条检测项目数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody AdsCheckProject  adsCheckProject){
        adsCheckProject = adsCheckProjectService.queryById(adsCheckProject.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsCheckProject",adsCheckProject);
        return map;
    }
    
     /**
     * 修改检测项目数据信息
     * @param adsCheckProject
     * @return
     */
    @ApiOperation(value = "修改检测项目数据信息")
    @RequestMapping(value = "/updateAdsCheckProject",method = RequestMethod.POST)
    public Object updateAdsCheckProject(@RequestBody AdsCheckProject adsCheckProject){
      	try{
    		adsCheckProjectService.update(adsCheckProject);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            logger.error("AdsCheckProjectController.updateAdsCheckProject:修改检测项目数据信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除检测项目信息
     * @param adsCheckProject
     * @return
     */
    @ApiOperation(value = " 删除检测项目信息")
    @RequestMapping(value = "/deleteAdsCheckProject",method = RequestMethod.POST)
    public Object deleteAdsCheckProject(AdsCheckProject adsCheckProject){
   	    try{
    		adsCheckProjectService.delete(adsCheckProject.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            logger.error("AdsCheckProjectController.deleteAdsCheckProject:删除检测项目信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


}

