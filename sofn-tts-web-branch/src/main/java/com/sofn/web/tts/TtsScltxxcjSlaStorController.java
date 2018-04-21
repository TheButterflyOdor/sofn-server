package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.service.tts.TtsScltxxcjScglService;
import com.sofn.service.tts.TtsScltxxcjSlaStorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
*	屠宰库存 controller 控制器实现
 * Created by tanli
 */
@RestController
@Api(value = "屠宰库存", description = "屠宰库存")
@RequestMapping(value = "/ttsScltxxcjSlaStor",method = RequestMethod.POST)
public class TtsScltxxcjSlaStorController extends BaseController {

	@Autowired
    public TtsScltxxcjSlaStorService ttsScltxxcjSlaStorService;
    @Autowired
    public TtsScltxxcjScglService ttsScltxxcjScglService;

    /**
     * 根据条件获取屠宰库存列表
     * @param ttsScltxxcjScgl 参数对象
     * @param start
     * @param length
     * @return
     */
    @ApiOperation(value = "获取屠宰库存信息列表")
    @RequestMapping(value = "/getSlaughterPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取屠宰库存信息列表",operationType="查询")
    public Object getSlaughterPageInfo(TtsScltxxcjScgl ttsScltxxcjScgl, int start, int length,String productName,
                                       String status,String productSort,String shrq_q,String shrq_h,
                                       String sortFlag,String userId, String keyWord) {
        try{
    		PageInfo<TtsScltxxcjScgl> pageInfo =ttsScltxxcjScglService.getSlaughterPageInfo(ttsScltxxcjScgl,((start+1)/length)+1,length,productName,status,productSort,
                    shrq_q,shrq_h,sortFlag,userId, keyWord);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增屠宰批次合成
     * @param spids
     * @return
     */
    @ApiOperation(value = "新增屠宰批次合成")
    @RequestMapping(value = "/addSlaPchc",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增屠宰批次合成",operationType="新增")
    public Object addTtsScltxxcjSlaStor(String spids,String userId){
    	try{
    		ttsScltxxcjSlaStorService.addSlaPchc(spids,userId);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取可进行批次合成的屠宰产品库存
     * @param ttsScltxxcjScgl 参数对象
     * @param productName 产品名称
     * @return
     */
    @ApiOperation(value = "根据条件获取可进行批次合成的屠宰产品库存")
    @RequestMapping(value = "/getPchcPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件获取可进行批次合成的屠宰产品库存",operationType="查询")
    public Object getPchcPageInfo(TtsScltxxcjScgl ttsScltxxcjScgl,String userId, int start, int length,String productName,String productType) {
        try{
            PageInfo<TtsScltxxcjScgl> pageInfo =ttsScltxxcjScglService.getStorPageInfo(ttsScltxxcjScgl,userId,((start+1)/length)+1,length,productName,productType);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 根据条件获取屠宰产品库存
     * @param ttsScltxxcjScgl 参数对象
     * @param productName 产品名称
     * @return
     */
    @ApiOperation(value = "根据条件获取屠宰产品库存")
    @RequestMapping(value = "/getStorPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件获取屠宰产品库存",operationType="查询")
    public Object getStorPageInfo(TtsScltxxcjScgl ttsScltxxcjScgl, int start, int length,String productType,String productName,String status,String startTime,String endTime,String userId,String keyWord) {
        try{
            PageInfo<TtsScltxxcjScgl> pageInfo =ttsScltxxcjScglService.getStor(ttsScltxxcjScgl,((start+1)/length)+1,length,productType,productName,status,startTime,endTime,userId,keyWord);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

