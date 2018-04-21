package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TempDemo;
import com.sofn.service.tts.TempDemoService;
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
 * Created by Administrator on 2016/10/14.
 */
@RestController
@Api(value = "测试数据信息", description = "测试")
@RequestMapping(value = "/testDemo",method = RequestMethod.POST)
public class TempDemoController extends BaseController {

    @Autowired
    public TempDemoService tempDemoService;

    /**
     * 根据条件获取就基地信息列表
     * @param demo
     * @return
     */
    @ApiOperation(value = "获取测试信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Map<String,Object> getPageInfo(TempDemo demo , String name, String tel, int start, int length){
    	PageInfo<TempDemo> pageInfo = tempDemoService.getPageInfo(demo,name,tel,((start+1)/length)+1,length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

    /**
     * 新增测试记录
     * @param demo
     * @return
     */
    @ApiOperation(value = "新增测试数据")
    @RequestMapping(value = "/addTempDemo",method = RequestMethod.POST)
    public Object addTempDemo(@RequestBody TempDemo demo){
        try {
          tempDemoService.add(demo);
          return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }




    }


    /**
     * 根据ID获取单个基地详情
     * @param demo
     * @return
     */
    @ApiOperation(value = "获取单个测试数据")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody TempDemo demo){
        demo = tempDemoService.queryById(demo.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("demo",demo);
        return map;
    }

    /**
     * 修改基地
     * @param demo
     * @return
     */
    @ApiOperation(value = "修改测试数据")
    @RequestMapping(value = "/updateTempDemo",method = RequestMethod.POST)
    public Map<String,Object> updateTempDemo(@RequestBody TempDemo demo){
        tempDemoService.update(demo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    /**
     * 删除基地
     * @param demo
     * @return
     */
    @ApiOperation(value = "删除测试数据")
    @RequestMapping(value = "/deleteTempDemo",method = RequestMethod.POST)
    public Map<String,Object> deleteTempDemo(TempDemo demo){
        tempDemoService.delete(demo.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
}
