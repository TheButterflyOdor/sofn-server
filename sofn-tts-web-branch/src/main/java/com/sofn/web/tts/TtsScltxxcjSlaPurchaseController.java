package com.sofn.web.tts;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjSlaCustomer;
import com.sofn.model.generator.TtsScltxxcjSlaPurchase;
import com.sofn.service.tts.TtsScltxxcjSlaPurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *	采购管理 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "采购管理", description = "采购管理")
@RequestMapping(value = "/ttsScltxxcjSlaPurchase",method = RequestMethod.POST)
public class TtsScltxxcjSlaPurchaseController extends BaseController{


    @Autowired
    public TtsScltxxcjSlaPurchaseService ttsScltxxcjSlaPurchaseService;
    /**
     * 根据条件获取获取采购管理信息列表
     * @param start
     * @param length
     * @param confirmState 销售是否确认
     * @return
     */
    @ApiOperation(value = "获取采购管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(int start, int length, String confirmState,String isCirculatesEnd) {
        try{
            PageInfo<TtsScltxxcjSlaCustomer> pageInfo = ttsScltxxcjSlaPurchaseService.queryPageForPurchase(start,length,confirmState,isCirculatesEnd);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 根据条件获取采购记录信息列表
     * @param start
     * @param length
     * @return
     */
    @ApiOperation(value = "获取采购记录信息列表")
    @RequestMapping(value = "/getPurchasePageInfo",method = RequestMethod.POST)
    public Object getPurchasePageInfo(TtsScltxxcjSlaPurchase ttsScltxxcjSlaPurchase, int start, int length, String productType, String productName, String startTime, String endTime, String keyWord, String confirmState) {
        try{
            PageInfo<TtsScltxxcjSlaPurchase> pageInfo =ttsScltxxcjSlaPurchaseService.getPurchasePageInfo(ttsScltxxcjSlaPurchase,((start+1)/length)+1,length,productType,productName,startTime,endTime,keyWord,confirmState);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 采购确认数据信息
     * @param id
     * @return
     */
    @ApiOperation(value = "采购确认数据信息")
    @RequestMapping(value = "/updatePurchase",method = RequestMethod.POST)
    public Object updatePurchase(String id){
        try{
            ttsScltxxcjSlaPurchaseService.updatePurchase(id);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 采购退回
     * @param id
     * @return
     */
    @ApiOperation(value = "采购退回")
    @RequestMapping(value = "/updatePurchaseRetreat",method = RequestMethod.POST)
    public Object updatePurchaseRetreat(String id,String xsthyy){
        try{
            ttsScltxxcjSlaPurchaseService.updatePurchaseRetreat(id,xsthyy);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}
