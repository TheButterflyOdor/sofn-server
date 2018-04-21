package com.sofn.web.tts;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.SysCodeConvert;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.service.tts.PcService;
import com.sofn.service.tts.TtsScltxxcjXsdjService;
import com.sofn.service.tts.ZsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
@RestController
@Api(value = "追溯码查询", description = "追溯码查询")
@RequestMapping(value = "/zsm",method = RequestMethod.POST)
public class ZsmController extends BaseController {
    @Autowired
    public PcService pcService;

    @Autowired
    public ZsmService zsmService;

    @Autowired
    public TtsScltxxcjXsdjService ttsScltxxcjXsdjService;
    /**
     * 追溯码获取本级追溯信息
     * @param zsm
     * @return
     */
    @ApiOperation(value = "追溯码获取本级追溯信息")
    @SystemControllerLog(description = "追溯码信息",operationType = "查询本级")
    @RequestMapping(value = "/getBaseZsm",method = RequestMethod.POST)
    public Map<String,Object> getBaseZsm(String zsm){
        //保存搜索信息
        String userId = getCurrUser();
        pcService.saveSearchData(zsm, userId);

        List<TtsScltxxcjCgglAndCustomer> vo = zsmService.getBaseZsm(zsm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("base",vo);
        return map;
    }


    /**
     * 追溯码获取上级追溯信息
     * @param zsm
     * @return
     */
    @ApiOperation(value = "追溯码获取上级追溯信息")
    @SystemControllerLog(description = "追溯码信息",operationType = "查询上级")
    @RequestMapping(value = "/getUpZsm",method = RequestMethod.POST)
    public Map<String,Object> getUpZsm(String zsm){
        List<TtsScltxxcjXsdjAndCustomer> vo = zsmService.getUpZsm(zsm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("up",vo);
        return map;
    }


    /**
     * 追溯码获取下游追溯信息
     * @param fromzsm  tozsm
     * @return
     */
    @ApiOperation(value = "追溯码获取下游追溯信息")
    @SystemControllerLog(description = "追溯码信息",operationType = "查询下游")
    @RequestMapping(value = "/getDownZsm",method = RequestMethod.POST)
    public Map<String,Object> getDownZsm(String fromzsm,String tozsm) {
        List<Map<String,Object>> pageInfo =zsmService.getDownZsm(fromzsm,tozsm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }


    /**
     * 销售是本级的时候，查询上级追溯信息
     * @param zsm
     * @return
     */
    @ApiOperation(value = "销售是本级的时候，查询上级追溯信息")
    @SystemControllerLog(description = "追溯码信息",operationType = "查询销售本级")
    @RequestMapping(value = "/getXsUpZsm",method = RequestMethod.POST)
    public Map<String,Object> getXsUpZsm(String zsm) {
        List<Map<String,Object>> pageInfo =zsmService.getXsUpZsm(zsm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }


    /**
     * 追溯查询，本级是销售
     * @param fromzsm  tozsm
     * @return
     */
    @ApiOperation(value = "追溯码获取下游追溯信息")
    @SystemControllerLog(description = "追溯码信息",operationType = "查询下游")
    @RequestMapping(value = "/getXsbaseZsm",method = RequestMethod.POST)
    public Map<String,Object> getXsbaseZsm(String fromzsm,String tozsm,String entityId) {
        List<Map<String,Object>> pageInfo =zsmService.getXsbaseZsm(fromzsm,tozsm,entityId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

    /**
     * 追溯查询，本级是销售
     * @return
     */
    @ApiOperation(value = "追溯码获取下游追溯信息")
    @SystemControllerLog(description = "追溯码信息",operationType = "查询下游")
    @RequestMapping(value = "/existsTrace",method = RequestMethod.POST)
    public Object existsTrace(String zsm,String entityId) {
        try{
            long result = ttsScltxxcjXsdjService.existsTrace(zsm,entityId);
            return setSuccessModelMap(new ModelMap(),result);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 通过短码获取长码
     * @param longCode
     * @param shortCode
     * @return
     */
    @ApiOperation(value = "获取追溯凭证打印详细信息(批次码)")
    @RequestMapping(value = "/getLongByShort",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取追溯凭证打印详细信息(批次码)",operationType="查询")
    public Map<String,Object> getLongByShort(String longCode,String shortCode){
        SysCodeConvert vo  = ttsScltxxcjXsdjService.getLongByShort(longCode,shortCode);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",vo);
        return map;
    }
}
