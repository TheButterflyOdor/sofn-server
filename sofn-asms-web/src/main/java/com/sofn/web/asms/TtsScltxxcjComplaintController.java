package com.sofn.web.asms;

/**
 * Created by Administrator on 2016/9/20.
 */

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.model.generator.TtsScltxxcjComplain;
import com.sofn.service.asms.TtsScltxxcjComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 5:05
 */
@RestController
@Api(value = "投诉处理", description = "投诉处理")
@RequestMapping(value = "/ttsScltxxcjComplaint",method = RequestMethod.POST)
public class TtsScltxxcjComplaintController extends BaseController {

    @Autowired
    private TtsScltxxcjComplaintService ttsScltxxcjComplaintService;
    /**
     * 投诉处理
     *
     * @param
     * @return
     */
    @ApiOperation(value = "投诉处理")
    @SystemControllerLog(description = "处理投诉信息",operationType="添加处理意见")
    @RequestMapping(value = "/updateTtsScltxxcjComplaint", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> updateTtsScltxxcjComplaint(@RequestHeader String token, @RequestBody TtsScltxxcjComplain ttsScltxxcjComplain) {
        TtsScltxxcjComplain ttsScltxxcjComplains=ttsScltxxcjComplaintService.findTtsScltxxcjComplaint(ttsScltxxcjComplain.getId());
        ttsScltxxcjComplains.setAcceptance(ttsScltxxcjComplain.getAcceptance());
        ttsScltxxcjComplains.setStatus(AsmsEnum.HANDLE.getCode());
        ttsScltxxcjComplains.setAccEntityIdcode(ttsScltxxcjComplain.getAccEntityIdcode());
        ttsScltxxcjComplains.setAccUserIdcode(ttsScltxxcjComplain.getAccUserIdcode());
        int result = ttsScltxxcjComplaintService.updatettsScltxxcjComplaint(token, ttsScltxxcjComplains);
        Map<String, Object> map = new HashMap<String, Object>();
        if(result == -1){
            map.put("httpCode", ApiMsgConstants.FAILED_CODE);
            map.put("msg", "受理成功,消息推送失败");
        }else{
            map.put("httpCode", ApiMsgConstants.SUCCESS_CODE);
            map.put("msg", ApiMsgConstants.SUCCESS_MSG);
        }
        return map;
    }

    /**
     * 根据条件获取日常执法任务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取投诉处理列表")
    @SystemControllerLog(description = "根据条件获取投诉列表",operationType="查询")
    @RequestMapping(value = "/getTtsScltxxcjComplaintList", method = RequestMethod.POST)
    @Authorization
        public Map<String, Object> getTtsScltxxcjComplaintList(@RequestHeader String token, TtsScltxxcjComplain ttsScltxxcjComplain, String areaId,
                                                               String complaintTitle, String status, String type, int start, int length, String queryCon) {
        PageInfo<TtsScltxxcjComplain> pageInfo = ttsScltxxcjComplaintService.getTtsScltxxcjComplaintList(ttsScltxxcjComplain,areaId,complaintTitle,status,type,
                ((start+1)/length)+1,length,queryCon);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

    /**
     * 根据ID获取单个专家信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取单个投诉处理")
    @SystemControllerLog(description = "获取单个投诉处理信息",operationType="查询")
    @RequestMapping(value = "/getTtsScltxxcjComplaintById", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> findTtsScltxxcjComplaintById(@RequestHeader String token, @RequestBody TtsScltxxcjComplain ttsScltxxcjComplain) {
        ttsScltxxcjComplain = ttsScltxxcjComplaintService.findTtsScltxxcjComplaint(ttsScltxxcjComplain.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjComplaint",ttsScltxxcjComplain);
        return map;
    }
}
