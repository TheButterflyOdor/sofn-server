package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.service.tts.PcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/21.
 */
@RestController
@Api(value = "追溯码查询", description = "追溯码查询")
@RequestMapping(value = "/pc",method = RequestMethod.POST)
public class PcController extends BaseController {

    @Autowired
    public PcService pcService;

    /**
     * 追溯码获取本级追溯信息
     * @param pc
     * @return
     */
    @ApiOperation(value = "批次码获取本级产品信息")
    @RequestMapping(value = "/getBaseForPc",method = RequestMethod.POST)
    public Map<String,Object> getBaseForPc(String pc,String entityId){
        //保存搜索信息
        String userId = getCurrUser();
        pcService.saveSearchData(pc, userId);


        List<Map<String,Object>> vo = pcService.getBaseForPc(pc,entityId);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("base",vo);
        return map;
    }


    /**
     * 通过批次信息查询对应上级产品信息
     * @param pc
     * @param status
     * @param billStatus
     * @param fromzsm
     * @return
     */
    @ApiOperation(value = "批次码获取上级产品信息")
    @RequestMapping(value = "/getTopForPc",method = RequestMethod.POST)
    public Map<String,Object> getTopForPc(String pc,String status,String billStatus,String fromzsm) {
        List<Map<String,Object>> pageInfo = null;
        if("1".equals(billStatus)){
            pageInfo = pcService.getIfhc(pc);
        } else {
            pageInfo = pcService.getTopForPc(pc,status,billStatus,fromzsm);
        }
        //
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }


    @ApiOperation(value = "批次码获取下游产品信息")
    @RequestMapping(value = "/getDownForPc",method = RequestMethod.POST)
    public Map<String,Object> getDownForPc(String pc,String entityId) {
        List<Map<String,Object>> pageInfo = pcService.getInfoforpc(pc,entityId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }


    @ApiOperation(value = "批次码合成后流向")
    @RequestMapping(value = "/getHclx",method = RequestMethod.POST)
    public Map<String,Object> getHclx(String pc) {
        List<Map<String,Object>> vo = pcService.getHclx(pc);
        //
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",vo);
        return map;
    }


}
