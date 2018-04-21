package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.service.tts.TtsScltxxcjCgglService;
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
@RequestMapping(value = "/ttsScltxxcjCggl",method = RequestMethod.POST)
public class TtsScltxxcjCgglController  extends BaseController {


    @Autowired
    public TtsScltxxcjCgglService ttsScltxxcjCgglService;



    /**
     * 根据条件获取获取采购管理信息列表
     * @param start
     * @param length
     * @param confirmState 销售是否确认
     * @return
     */
    @ApiOperation(value = "获取采购管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "采购管理信息",operationType = "查询")
    public Object getPageInfo(int start, int length, String confirmState,String isCirculatesEnd,String entity_id,String productName,String productSort,String shrq_q,String shrq_h, String keyWord,String purchaseRecord) {
        try{
            PageInfo<TtsScltxxcjXsdjAndCustomer> pageInfo = ttsScltxxcjCgglService.queryPageForCg(start,length,confirmState,
                    isCirculatesEnd,entity_id,productName,shrq_q,shrq_h, keyWord, productSort,purchaseRecord);
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
    @RequestMapping(value = "/updateTtsScltxxcjCgqr",method = RequestMethod.POST)
    @SystemControllerLog(description = "采购确认数据信息",operationType = "修改")
    public Object updateTtsScltxxcjCgqr(String id,String entity_id,String customerUserId){
        try{
            ttsScltxxcjCgglService.updateTtsScltxxcjCgqr(id,entity_id,customerUserId);
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
    @RequestMapping(value = "/updateTtsScltxxcjCgth",method = RequestMethod.POST)
    @SystemControllerLog(description = "采购退回",operationType = "修改")
    public Object updateTtsScltxxcjCgth(String id,String xsthyy,String type){
        try{
            ttsScltxxcjCgglService.updateTtsScltxxcjCgth(id,xsthyy,type);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 查询追溯码和主体身份码在采购管理中是否存在数据
     * @param zsm
     * @return
     */
    @ApiOperation(value = "查询追溯码和主体身份码在采购管理中是否存在数据")
    @RequestMapping(value = "/zsmIsCgOrXs",method = RequestMethod.POST)
    public Object zsmIsCgOrXs(String zsm,String entity_id){
        try{
            long result = ttsScltxxcjCgglService.zsmIsCgOrXs(zsm,entity_id);
            return setSuccessModelMap(new ModelMap(),result);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }



}
