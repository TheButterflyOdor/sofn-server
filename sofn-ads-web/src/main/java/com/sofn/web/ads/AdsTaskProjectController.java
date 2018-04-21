package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.AdsTaskProject;
import com.sofn.service.ads.AdsTaskProjectService;
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
*	监测任务_检测项目 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "监测任务_检测项目", description = "监测任务_检测项目")
@RequestMapping(value = "/adsTaskProject",method = RequestMethod.POST)
public class AdsTaskProjectController extends BaseController {

	@Autowired
    private AdsTaskProjectService adsTaskProjectService;
    
     /**
     * 根据条件获取监测任务_检测项目列表
     * @param adsTaskProject
     * @return
     */
    @ApiOperation(value = "获取监测任务_检测项目信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(AdsTaskProject adsTaskProject, int start, int length) {
    	try{
    		PageInfo<AdsTaskProject> pageInfo =adsTaskProjectService.getPageInfo(adsTaskProject,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增监测任务_检测项目记录
     * @param adsTaskProject
     * @return
     */
    @ApiOperation(value = "新增监测任务_检测项目数据")
    @RequestMapping(value = "/addAdsTaskProject",method = RequestMethod.POST)
    public Object addAdsTaskProject(@RequestBody AdsTaskProject  adsTaskProject){
    	try{
    		adsTaskProjectService.add(adsTaskProject);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条监测任务_检测项目数据信息
     * @param adsTaskProject
     * @return
     */
    @ApiOperation(value = "获取单条监测任务_检测项目数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody AdsTaskProject  adsTaskProject){
        adsTaskProject = adsTaskProjectService.queryById(adsTaskProject.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsTaskProject",adsTaskProject);
        return map;
    }
    
     /**
     * 修改监测任务_检测项目数据信息
     * @param adsTaskProject
     * @return
     */
    @ApiOperation(value = "修改监测任务_检测项目数据信息")
    @RequestMapping(value = "/updateAdsTaskProject",method = RequestMethod.POST)
    public Object updateAdsTaskProject(@RequestBody AdsTaskProject adsTaskProject){
      	try{
    		adsTaskProjectService.update(adsTaskProject);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     /**
     * 删除监测任务_检测项目信息
     * @param adsTaskProject
     * @return
     */
    @ApiOperation(value = " 删除监测任务_检测项目信息")
    @RequestMapping(value = "/deleteAdsTaskProject",method = RequestMethod.POST)
    public Object deleteAdsTaskProject(AdsTaskProject adsTaskProject){
   	    try{
    		adsTaskProjectService.delete(adsTaskProject.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

